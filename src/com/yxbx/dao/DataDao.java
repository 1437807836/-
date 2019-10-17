package com.yxbx.dao;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yxbx.pojo.TabScheduleOfInsuranceTrue;


@Entity
@Repository("dataDao")
public class DataDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 添加对象记录
     *
     * @param o 需要添加的对象
     */
    public void addObject(Object o) {
        Session session = getSession();
        session.save(o);
    }

    /**
     * 删除数据库中指定对象
     *
     * @param o 需要删除的对象
     */
    public void deleteObject(Object o) {
        Session session = getSession();
        session.delete(o);
        session.flush();
        session.close();
    }

    /**
     * 更新数据库中指定对象
     *
     * @param o 需要更新的对象
     */
    public void updateObject(Object o) {
        Session session = getSession();
        session.update(o);
        session.flush();
    }

    public int executeBySql(String sql) {
        SQLQuery createSQLQuery = getSession().createSQLQuery(sql);
        int result = createSQLQuery.executeUpdate();
        return result;
    }

//	public List<?> excuteCardBySql(String sql){
//		SQLQuery createSQLQuery =getSession().createSQLQuery(sql).addEntity(CardInfo.class);
//		createSQLQuery.setString("account", account);
//		List<?> list = createSQLQuery.list();
//		return list;
//	}

    /**
     * 更新数据库中指定对象，如果对象记录不存在，则插入新的
     *
     * @param o 需要更新的对象
     */
    public void saveOrUpdateObject(Object o) {
        Session session = getSession();
        session.saveOrUpdate(o);
    }

    /**
     * 获取整张表的记录集合
     *
     * @param c 对象类型
     * @return 对象列表集合
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getAllObject(Class<T> c) {
        return getSession().createQuery("from " + c.getName()).list();
    }

    /**
     * 根据id查询对象记录
     *
     * @param c  查询对象类型
     * @param id 查询对象ID
     * @return 查询对象结果
     */
    @SuppressWarnings("unchecked")
    public <T> T getObjectById(Class<T> c, Serializable id) {
        return (T) getSession().get(c, id);
    }

    /**
     * 根据id删除对象记录
     *
     * @param c  删除对象类型
     * @param id 删除对象ID
     */
    public <T> void deleteObjectById(Class<T> c, Serializable id) {
        deleteObject(getObjectById(c, id));
    }

    /**
     * 条件检索对象数据集
     *
     * @param hql    检索hql语句
     * @param params 条件名称
     * @param p      条件值
     * @return 检索结果数据集
     */
    @SuppressWarnings("rawtypes")
    public List<?> getObjectsViaParam(String hql, String[] params, Object... p) {
        Query query = getSession().createQuery(hql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                if (p[i] instanceof List) {
                    query.setParameterList(params[i], (Collection) p[i]);
                } else {
                    query.setParameter(params[i], p[i]);
                }
            }
        }
        return query.list();
    }

    /**
     * Sql 专用
     *
     * @param sql
     * @return
     */
    public List<?> getObjectList(String sql) {
        SQLQuery createSQLQuery = getSession().createSQLQuery(sql);
        return createSQLQuery.addEntity(TabScheduleOfInsuranceTrue.class).list();
    }


    public List<?> getObjectsViaParam1(String hql, String[] params, String[] p) {
        Query query = getSession().createQuery(hql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                query.setString(params[i], p[i]);
            }
        }
        return query.list();
    }

    public List<?> getObjectList(String hql, String[] params, String values[]) {
        Query query = getSession().createQuery(hql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                query.setInteger(i, Integer.parseInt(values[i]));
            }
        }
        return query.list();
    }

    /**
     * 条件检索对象数据集
     *
     * @param hql    检索hql语句
     * @param params 条件名称
     * @param value  条件值
     * @return 检索结果数据集
     */

    public Object getObjects(String hql, String[] params, String[] value) {
        Query query = getSession().createQuery(hql);
        if (params != null) {
            for (int i = 0; i < value.length; i++) {
                query.setString(params[i], value[i]);
            }
        }
        List<?> list = query.list();
        if (list == null) {
            return null;
        } else if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }


    /**
     * @param hqlStr
     * @return
     */
    public int getHqlRecordCount(String hqlStr) {
        int fromIndex = hqlStr.indexOf("from");
        String hql = "select count(*) " + hqlStr.substring(fromIndex);
        Query query = getSession().createQuery(hql);
        return ((Long) query.uniqueResult()).intValue();
    }

    /**
     * 条件检索对象数据集
     *
     * @param hql    检索hql语句
     * @param params 条件名称
     * @param value  条件值
     * @return 检索结果数据集
     */

    public List<?> getAllObjects1(String hql, String[] params, String[] value) {
        Query query = getSession().createQuery(hql);
        if (params == null) {
            return query.list();
        }
        for (int i = 0; i < value.length; i++) {
            query.setString(params[i], value[i]);
        }
        List<?> list = query.list();
        return list;
    }

    /**
     * 条件检索对象数据集(省略参数名)
     *
     * @param hql 检索hql语句
     * @param p   条件值
     * @return 检索结果数据集
     * @deprecated 使用 {@link DataDao#getObjectsViaParam(String, String[], Object...)}
     */
    public List<?> getObjectByCondition(String hql, Object... p) {
        String[] params = new String[p.length];
        for (int i = 0; i < p.length; i++) {
            params[i] = ":p" + i;
            hql = hql.replaceFirst("?", params[i]);
        }
        return getObjectsViaParam(hql, params, p);
    }


    /**
     * @param hql 查询hql语句
     * @param p   条件值
     * @return 查询条数
     * @deprecated 按条件查询数据条数, 尽量使用{@link DataDao#getCount(String, Object...)}
     */
    public int getQueryCount(String hql, Object... p) {
        return getObjectByCondition(hql, p).size();
    }

    /**
     * 按条件查询数据条数
     *
     * @param hql 查询hql语句，select count 检索
     * @param p   条件值
     * @return 查询条数
     */
    public long getCount(String hql, Object... p) {
        return (Long) getObjectByCondition(hql, p).listIterator().next();
    }


    /**
     * 分页查询数据集
     *
     * @param hql      查询hql语句
     * @param pageSize 每页条数
     * @param page     当前页数，从1开始
     * @param p        条件值
     * @return 检索结果数据集
     * @deprecated 使用 {@link DataDao#pageQueryViaParam(String, Integer, Integer, String[], Object...)}
     */
    public List<?> pageQuery(String hql, Integer pageSize, Integer page, Object... p) {
        String[] params = null;
        if (p != null) {
            params = new String[p.length];
            for (int i = 0; i < p.length; i++) {
                params[i] = ":p" + i;
                hql = hql.replaceFirst("?", params[i]);
            }
        }

        return pageQueryViaParam(hql, pageSize, page, params, p);
    }

    /**
     * 分页查询数据集
     *
     * @param hql      查询hql语句
     * @param pageSize 每页条数
     * @param page     当前页数，从1开始
     * @param params   条件名称数组
     * @param p        条件值
     * @return 检索结果数据集
     */
    public List<?> pageQueryViaParam(String hql, Integer pageSize, Integer page, String[] params, Object... p) {
        Query query = getSession().createQuery(hql);
        if (p != null) {
            for (int i = 0; i < p.length; i++) {
                query.setParameter(params[i], p[i]);
            }
        }
        if (pageSize != null && pageSize > 0 && page != null && page > 0) {
            query.setFirstResult((page - 1) * pageSize).setMaxResults(pageSize);
        }
        return query.list();
    }

    public List<?> pageQueryViaParam1(String hql, Integer pageSize, Integer page, String[] params, String[] p) {
        Query query = getSession().createQuery(hql);
        if (p != null) {
            for (int i = 0; i < p.length; i++) {
                query.setString(params[i], p[i]);
            }
        }
        if (pageSize != null && pageSize > 0 && page != null && page > 0) {
            query.setFirstResult((page - 1) * pageSize).setMaxResults(pageSize);
        }
        return query.list();
    }

    /**
     * 按条件查询单条数据
     *
     * @param hql 查询hql语句
     * @param p   条件值
     * @return 检索结果数据集
     * @deprecated 使用 {@link DataDao#getFirstObjectViaParam(String, String[], Object...)}
     */
    public Object getFirstObject(String hql, Object... p) {
        String[] params = new String[p.length];
        for (int i = 0; i < p.length; i++) {
            params[i] = ":p" + i;
            hql = hql.replaceFirst("?", params[i]);
        }
        return getFirstObjectViaParam(hql, params, p);
    }

    /**
     * 按条件查询单条数据
     *
     * @param hql    查询hql语句
     * @param params 条件名称数组
     * @param p      条件值
     * @return 检索结果数据集
     */
    public Object getFirstObjectViaParam(String hql, String[] params, Object... p) {
        List<?> list = getObjectsViaParam(hql, params, p);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 按条件批量删除数据
     *
     * @param hql 删除hql语句，delete from 开头
     * @param p   条件值
     * @deprecated 使用 {@link DataDao#deleteObjectsViaParam(String, String[], Object...)}
     */
    public void deleteObjectByCondition(String hql, Object... p) {
        String[] params = new String[p.length];
        for (int i = 0; i < p.length; i++) {
            params[i] = ":p" + i;
            hql = hql.replaceFirst("?", params[i]);
        }
        deleteObjectsViaParam(hql, params, p);
    }

    /**
     * 按条件批量删除数据
     *
     * @param hql    删除hql语句，delete from 开头
     * @param params 条件名称数组
     * @param p      条件值
     */
    public void deleteObjectsViaParam(String hql, String[] params, Object... p) {
        Query query = getSession().createQuery(hql);
        if (p != null) {
            for (int i = 0; i < p.length; i++) {
                query.setParameter(params[i], p[i]);
            }
        }
        query.executeUpdate();
    }

    /**
     * 用SQL文批量删除数据集
     *
     * @param sql 删除sql语句
     */
    public void deleteBySql(String sql) {
        SQLQuery query = getSession().createSQLQuery(sql);
        query.executeUpdate();
    }

    public void queryManagerUser() {


    }

}
