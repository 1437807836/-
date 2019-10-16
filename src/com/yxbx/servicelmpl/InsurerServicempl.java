package com.yxbx.servicelmpl;



import java.util.List;
import javax.annotation.Resource;
import javax.persistence.Entity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yxbx.dao.DataDao;
import com.yxbx.pojo.TabInsurer;
import com.yxbx.pojo.TabUser;
import com.yxbx.service.InsurerService;
import com.yxbx.service.UserService;



@Entity
@Service("InsurerService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)	
public class InsurerServicempl implements  InsurerService {
	
		
	@Resource
	DataDao datadao;



	@Override
	public TabInsurer queryInsurerById(Integer Id) {
		// TODO Auto-generated method stub
		TabInsurer ti=datadao.getObjectById( TabInsurer.class,Id);
		return ti;
	}


	@Override
	public List<TabInsurer> queryInsurerAll() {
		// TODO Auto-generated method stub
		String  hql="from  TabInsurer";
		         @SuppressWarnings("unchecked")
				List<TabInsurer> list=    (List<TabInsurer>) datadao.getAllObjects1(hql, null, null);
		return list;
	}


	@Override
	public List<TabInsurer> queryAllInsurer(String insurerintName,
			int pageSize, int page) {
		 List<TabInsurer> list1=null;
		// TODO Auto-generated method stub
		String  hql="from  TabInsurer where  tab_insurer_name like'%"+insurerintName+"%' order by tab_write_date desc";
		if(insurerintName==null){
			  hql="from  TabInsurer order by tab_write_date desc";
			  @SuppressWarnings("unchecked")
				List<TabInsurer> list=(List<TabInsurer>) datadao.pageQueryViaParam1(hql, pageSize, page, null, null);
			  list1=list;
		}else{
				String  params[]={"tab_insurer_name"};
	        	String  values[]={insurerintName};
		
              @SuppressWarnings("unchecked")
		    List<TabInsurer> list=(List<TabInsurer>) datadao.pageQueryViaParam1(hql, pageSize, page, null, null);
              list1=list;
		}
	
        
		return list1;
	}



	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)	
	@Override
	public TabInsurer addInsurer(TabInsurer ti) {
		// TODO Auto-generated method stub
		datadao.addObject(ti);
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)	
	@Override
	public TabInsurer updateInsurer(TabInsurer ti) {
		// TODO Auto-generated method stub
		datadao.updateObject(ti);
		return null;
	}


	@Override
	public List<TabInsurer> queryAllInsurerByName(String insurerintName) {
		// TODO Auto-generated method stub
		String  hql="from  TabInsurer where  tab_insurer_name =:tab_insurer_name order by tab_write_date desc";
				String  params[]={"tab_insurer_name"};
	        	String  values[]={insurerintName};
		
              @SuppressWarnings("unchecked")
		    List<TabInsurer> list=(List<TabInsurer>) datadao.getAllObjects1(hql, params, values);
             return  list;
		
	}

	

	
	
	
		
}
