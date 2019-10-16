package com.yxbx.servicelmpl;



import java.util.List;
import javax.annotation.Resource;
import javax.persistence.Entity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yxbx.dao.DataDao;
import com.yxbx.pojo.TabPayPerson;
import com.yxbx.pojo.TabUser;
import com.yxbx.service.InsurerService;
import com.yxbx.service.PayPersonService;
import com.yxbx.service.UserService;



@Entity
@Service("PayPersonService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)	
public class PayPersonServicempl implements  PayPersonService {
	
		
	@Resource
	DataDao datadao;



	@Override
	public TabPayPerson queryInsurerById(Integer Id) {
		// TODO Auto-generated method stub
		TabPayPerson ti=datadao.getObjectById( TabPayPerson.class,Id);
		return ti;
	}


	@Override
	public List<TabPayPerson> queryInsurerAll() {
		// TODO Auto-generated method stub
		String  hql="from  TabPayPerson where  tab_is_delete=0";
		         @SuppressWarnings("unchecked")
				List<TabPayPerson> list=    (List<TabPayPerson>) datadao.getAllObjects1(hql, null, null);
		return list;
	}


	@Override
	public List<TabPayPerson> queryAllPayPerson(String tab_bank_name,
			int pageSize, int page) {
		 List<TabPayPerson> list1=null;
		// TODO Auto-generated method stub
		String  hql="from  TabPayPerson where  tab_pay_name like'%"+tab_bank_name+"%' and  tab_is_delete=0 order by tab_write_date desc";
		if(tab_bank_name==null){
			  hql="from  TabPayPerson  where tab_is_delete=0  order by tab_write_date desc";
			  @SuppressWarnings("unchecked")
				List<TabPayPerson> list=(List<TabPayPerson>) datadao.pageQueryViaParam1(hql, pageSize, page, null, null);
			  list1=list;
		}else{
				String  params[]={"tabBankName"};
	        	String  values[]={tab_bank_name};
		
              @SuppressWarnings("unchecked")
		    List<TabPayPerson> list=(List<TabPayPerson>) datadao.pageQueryViaParam1(hql, pageSize, page, null, null);
              list1=list;
		}
	
        
		return list1;
	}



	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)	
	@Override
	public TabPayPerson addInsurer(TabPayPerson ti) {
		// TODO Auto-generated method stub
		datadao.addObject(ti);
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)	
	@Override
	public TabPayPerson updateInsurer(TabPayPerson ti) {
		// TODO Auto-generated method stub
		datadao.updateObject(ti);
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	@Override
	public void deleteObject(TabPayPerson tp) {
		// TODO Auto-generated method stub
		datadao.deleteObject(tp);
		
	}

	

	
	
	
		
}
