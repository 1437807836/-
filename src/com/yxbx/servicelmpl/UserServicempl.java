package com.yxbx.servicelmpl;



import java.util.List;
import javax.annotation.Resource;
import javax.persistence.Entity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yxbx.dao.DataDao;
import com.yxbx.pojo.TabUser;
import com.yxbx.service.UserService;



@Entity
@Service("UserService1")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)	
public class UserServicempl implements  UserService {
	
		
	@Resource
	DataDao datadao;


	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)	
	public void addClient(TabUser t) {
		// TODO Auto-generated method stub
		datadao.addObject(t);
		
	}


	@Override
	public TabUser queryAllByUserId(Integer userId) {
		// TODO Auto-generated method stub
//		String  hql="from TabUser where id=:userId";
//		String  params[]={"userId"};
//		String  values[]={String .valueOf(userId)};
		TabUser t=datadao.getObjectById(TabUser.class, userId);
		return t;
	}


	@Override
	public TabUser findUser(String username, String password) {
		// TODO Auto-generated method stub
				String hql=" from TabUser where tab_username=:username  and  tab_password=:password  and tab_is_delete=0";
				String  params[]={"username","password"};
				String  value[]={username,password};
				@SuppressWarnings("unchecked")
				List<TabUser > list= (List<TabUser>) datadao.getAllObjects1(hql, params, value);
				 if( list!=null&&list.size()>0){
					 return list.get(0);
				 };
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)	
	@Override
	public void updateClient(TabUser tUser) {
		// TODO Auto-generated method stub
		datadao.updateObject(tUser);
	}


	@Override
	public TabUser findUser(String mobile) {
		// TODO Auto-generated method stub
		String hql=" from TabUser where tab_mobile=:mobile and tab_is_delete=0";
		String  params[]={"mobile"};
		String  value[]={mobile};
		TabUser t=   (TabUser) datadao.getObjects(hql, params, value);
		return t;
	}


	@Override
	public TabUser findUserByUsername(String userName) {
		// TODO Auto-generated method stub
		String hql=" from  TabUser where tab_username=:username   and tab_is_delete=0";
		String  params[]={"username"};
		String  value[]={userName};
		TabUser t=   (TabUser) datadao.getObjects(hql, params, value);
		return t;
		
	}


	@Override
	public List<TabUser>   queryAllUser(int pageSize,int page) {
		// TODO Auto-generated method stub
		String hql=" from  TabUser where  tab_is_delete=0";
		@SuppressWarnings("unchecked")
		//datadao.pageQuery(hql, pageSize, page, null);	
		List<TabUser> list= (List<TabUser>) datadao.pageQuery(hql, pageSize, page, null);
		//List<TabUser> list= (List<TabUser>) datadao.getAllObjects1(hql, null, null);
		return list;
	}


	@Override
	public List<TabUser> queryAllUser() {
		// TODO Auto-generated method stub
		String hql=" from  TabUser where  tab_is_delete=0";
		@SuppressWarnings("unchecked")
		//datadao.pageQuery(hql, pageSize, page, null);	
		List<TabUser> list= (List<TabUser>) datadao.getAllObjects1(hql, null, null);
		//List<TabUser> list= (List<TabUser>) datadao.getAllObjects1(hql, null, null);
		return list;
	}

	

	
	
	
		
}
