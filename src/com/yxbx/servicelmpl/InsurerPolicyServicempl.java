package com.yxbx.servicelmpl;



import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.Entity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yxbx.dao.DataDao;
import com.yxbx.pojo.TabBxState;
import com.yxbx.pojo.TabInsurerPolicy;
import com.yxbx.pojo.TabUser;
import com.yxbx.service.InsurerPolicyService;
import com.yxbx.service.InsurerService;
import com.yxbx.service.UserService;



@Entity
@Service("InsurerPolicy")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)	
public class InsurerPolicyServicempl implements  InsurerPolicyService {
	
		
	@Resource
	DataDao datadao;



	@Override
	public TabInsurerPolicy queryInsurerPolicyById(Integer Id) {
		// TODO Auto-generated method stub
		TabInsurerPolicy ti=datadao.getObjectById( TabInsurerPolicy.class,Id);
		return ti;
	}


	@Override
	public List<TabInsurerPolicy> queryInsurerPolicyAll() {
		// TODO Auto-generated method stub
		String  hql="from  TabInsurerPolicy where  tab_is_delete=0";
		         @SuppressWarnings("unchecked")
				List<TabInsurerPolicy> list=    (List<TabInsurerPolicy>) datadao.getAllObjects1(hql, null, null);
		return list;
	}


	@Override
	public List<TabInsurerPolicy> queryAllInsurerPolicy(TabInsurerPolicy ip,
			int pageSize, int page) {
		 List<TabInsurerPolicy> list1=null;
		 ArrayList<String>  params=new ArrayList<String>();
		 ArrayList<String>  values=new ArrayList<String>();
		// TODO Auto-generated method stub
		StringBuffer  hql=new StringBuffer("from  TabInsurerPolicy  where 1=1 and tab_is_delete=0 ");
		if(ip==null){
			  hql=new StringBuffer("from  TabInsurerPolicy where tab_is_delete=0 order by tab_submit_time desc");
			  @SuppressWarnings("unchecked")
				List<TabInsurerPolicy> list=(List<TabInsurerPolicy>) datadao.pageQueryViaParam1(hql.toString(), pageSize, page, null, null);
			  list1=list;
				return list1;
		}
		  		
		if(ip.getTabInsurerId()!=null){
		
			hql.append(" and tab_insurer_id=:tab_insurer_name  ");
			params.add("tab_insurer_name");
			values.add(String.valueOf(ip.getTabInsurerId()));			
         
		}
		  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		   String date=df.format(new Date());
		   String  sTime=String.valueOf(ip.getTabStartTimeStart()==null?"1900-08-08":ip.getTabStartTimeStart());
		   if(ip.getTabStartTimeEnd()!=null||ip.getTabStartTimeStart()!=null){
			   hql.append(" and  tab_start_time  >=:stime  and tab_start_time<=:date ");		
				params.add("stime");
				params.add("date");
				values.add(sTime);	
				values.add(String.valueOf(ip.getTabStartTimeEnd()==null?new Timestamp(System.currentTimeMillis()):ip.getTabStartTimeEnd()));	
				
		   }
			

		   	if(ip.getTabExamineTimeStart()!=null||ip.getTabExamineTimeEnd()!=null){
		   		hql.append(" and  tab_examine_time >=:exTimeStart and tab_examine_time <=:exTimeEnd ");
				params.add("exTimeStart");
				params.add("exTimeEnd");
				values.add(String.valueOf(ip.getTabExamineTimeStart()==null?"1900-08-08":ip.getTabExamineTimeStart()));	
				values.add(String.valueOf(ip.getTabExamineTimeEnd()==null?new Timestamp(System.currentTimeMillis()):ip.getTabExamineTimeEnd()));	
			
		   	}
			if(ip.getTabSubmitTimeStart()!=null||ip.getTabSubmitTimeEnd()!=null){
					hql.append(" and  tab_submit_time  >=:SubmitTimeStart and tab_submit_time <=:SubmitTimeEnd ");	
			params.add("SubmitTimeStart");
			params.add("SubmitTimeEnd");
			values.add(String.valueOf(ip.getTabSubmitTimeStart()==null?"1900-08-08":ip.getTabSubmitTimeStart()));	
			values.add(String.valueOf(ip.getTabSubmitTimeEnd()==null?new Timestamp(System.currentTimeMillis()):ip.getTabSubmitTimeEnd()));
		
			}
		
		if(ip.getTabChannelName()!=null){
			hql.append(" and tab_channel_name=:TabChannelName ");
			params.add("TabChannelName");
			values.add(ip.getTabChannelName());	
		}
		if(ip.getTabNature()!=null){
			hql.append(" and tab_nature=:TabNature ");
			params.add("TabNature");
			values.add(ip.getTabNature());	
		}
		if(ip.getTabExamineAndVerify()!=null){
			hql.append(" and tab_examine_and_verify=:tab_examine_and_verify ");
			params.add("tab_examine_and_verify");
			values.add(ip.getTabExamineAndVerify());	
		}
		hql.append("order by tab_submit_time desc ");
		System.out.println(hql.toString());
		String  p[]=new String[params.size()];
		String  v[]=new String[params.size()];
			for (int i = 0; i < params.size(); i++) {
				p[i]=params.get(i);
				v[i]=values.get(i);
			}
	     @SuppressWarnings("unchecked")
		    List<TabInsurerPolicy> list=(List<TabInsurerPolicy>) datadao.pageQueryViaParam1(hql.toString(), pageSize, page, p, v);
           
           list1=list;
		return list1;
	}
	@Override
	public List<TabInsurerPolicy> queryInsurerPolicyAll(TabInsurerPolicy ip) {
		 List<TabInsurerPolicy> list1=null;
		 ArrayList<String>  params=new ArrayList<String>();
		 ArrayList<String>  values=new ArrayList<String>();
		// TODO Auto-generated method stub
		StringBuffer  hql=new StringBuffer("from  TabInsurerPolicy  where 1=1 and tab_is_delete=0 ");
		if(ip==null){
			  hql=new StringBuffer("from  TabInsurerPolicy where tab_is_delete=0 order by tab_submit_time desc");
			  @SuppressWarnings("unchecked")
				List<TabInsurerPolicy> list=(List<TabInsurerPolicy>) datadao.getAllObjects1(hql.toString(), null, null);
			  list1=list;
				return list1;
		}
		  		
		if(ip.getTabInsurerId()!=null){
		
			hql.append(" and tab_insurer_id=:tab_insurer_name  ");
			params.add("tab_insurer_name");
			values.add(String.valueOf(ip.getTabInsurerId()));			
         
		}
		  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		   String date=df.format(new Date());
		   String  sTime=String.valueOf(ip.getTabStartTimeStart()==null?"1900-08-08":ip.getTabStartTimeStart());
		
		   if(ip.getTabStartTimeEnd()!=null||ip.getTabStartTimeStart()!=null){
			   hql.append(" and  tab_start_time  >=:stime  and tab_start_time<=:date ");		
				params.add("stime");
				params.add("date");
				values.add(sTime);	
				values.add(String.valueOf(ip.getTabStartTimeEnd()==null?new Timestamp(System.currentTimeMillis()):ip.getTabStartTimeEnd()));	
				
		   }			
		   	if(ip.getTabExamineTimeStart()!=null||ip.getTabExamineTimeEnd()!=null){
		   		hql.append(" and  tab_examine_time >=:exTimeStart and tab_examine_time <=:exTimeEnd ");
				params.add("exTimeStart");
				params.add("exTimeEnd");
				values.add(String.valueOf(ip.getTabExamineTimeStart()==null?"1900-08-08":ip.getTabExamineTimeStart()));	
				values.add(String.valueOf(ip.getTabExamineTimeEnd()==null?new Timestamp(System.currentTimeMillis()):ip.getTabExamineTimeEnd()));	
			
		   	}
			if(ip.getTabSubmitTimeStart()!=null||ip.getTabSubmitTimeEnd()!=null){
					hql.append(" and  tab_submit_time  >=:SubmitTimeStart and tab_submit_time <=:SubmitTimeEnd ");	
			params.add("SubmitTimeStart");
			params.add("SubmitTimeEnd");
			values.add(String.valueOf(ip.getTabSubmitTimeStart()==null?"1900-08-08":ip.getTabSubmitTimeStart()));	
			values.add(String.valueOf(ip.getTabSubmitTimeEnd()==null?new Timestamp(System.currentTimeMillis()):ip.getTabSubmitTimeEnd()));
		
			}
		if(ip.getTabChannelName()!=null){
			hql.append(" and tab_channel_name=:TabChannelName ");
			params.add("TabChannelName");
			values.add(ip.getTabChannelName());	
		}
		if(ip.getTabNature()!=null){
			hql.append(" and tab_nature=:TabNature ");
			params.add("TabNature");
			values.add(ip.getTabNature());	
		}
		if(ip.getTabExamineAndVerify()!=null){
			hql.append(" and tab_examine_and_verify=:tab_examine_and_verify ");
			params.add("tab_examine_and_verify");
			values.add(ip.getTabExamineAndVerify());	
		}
		hql.append("order by tab_submit_time desc ");
		System.out.println(hql.toString());
		String  p[]=new String[params.size()];
		String  v[]=new String[params.size()];
			for (int i = 0; i < params.size(); i++) {
				p[i]=params.get(i);
				v[i]=values.get(i);
			}
	     @SuppressWarnings("unchecked")
		    List<TabInsurerPolicy> list=(List<TabInsurerPolicy>) datadao.getAllObjects1(hql.toString(), p, v);           
           list1=list;
		return list1;
	}


	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)	
	@Override
	public TabInsurerPolicy addInsurerPolicy(TabInsurerPolicy ti) {
		// TODO Auto-generated method stub
		datadao.addObject(ti);
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)	
	@Override
	public TabInsurerPolicy updateInsurerPolicy(TabInsurerPolicy ti) {
		// TODO Auto-generated method stub
		datadao.updateObject(ti);
		return null;
	}


	@Override
	public TabBxState queryBxStateById(Integer Id) {
		// TODO Auto-generated method stub
		String  sql="from  TabBxState ";
		datadao.getObjects(sql, null, null);
		return null;
	}


	@Override
	public void addBxStateById(String stateName) {
		// TODO Auto-generated method stub
		TabBxState t=new TabBxState();
		t.setTabState(stateName);
		datadao.addObject(t);
		
	}


	@Override
	public List<TabBxState> queryBxStateAll() {
		// TODO Auto-generated method stub
		String  sql="from  TabBxState ";
		@SuppressWarnings("unchecked")
		List<TabBxState> list=(List<TabBxState>) datadao.getAllObjects1(sql, null, null);
		return list;
	}


	@Override
	public void updateBxStateById(String stateName, int id) {
		// TODO Auto-generated method stub
		TabBxState ti=datadao.getObjectById( TabBxState.class,id);
		ti.setTabState(stateName);
		datadao.updateObject(ti);
		
	}


	@Override
	public int queryCount() {
		// TODO Auto-generated method stub
		int count=(int) datadao.getCount("from TabInsurerPolicy", null);
		
		return count;
	}

	

	
	
	
		
}
