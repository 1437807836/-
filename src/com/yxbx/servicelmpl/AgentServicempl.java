package com.yxbx.servicelmpl;



import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.Entity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yxbx.dao.DataDao;
import com.yxbx.pojo.TabAgent;
import com.yxbx.pojo.TabUser;
import com.yxbx.service.AgentService;
import com.yxbx.service.InsurerService;
import com.yxbx.service.UserService;



@Entity
@Service("AgentService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)	
public class AgentServicempl implements  AgentService {
	
		
	@Resource
	DataDao datadao;



	@Override
	public TabAgent queryagentById(Integer Id) {
		// TODO Auto-generated method stub
		TabAgent ti=datadao.getObjectById( TabAgent.class,Id);
		return ti;
	}


	@Override
	public List<TabAgent> queryAgentAll() {
		// TODO Auto-generated method stub
		String  hql="from  TabAgent where tab_is_delete=0  order by tab_write_date desc";
		         @SuppressWarnings("unchecked")
				List<TabAgent> list=    (List<TabAgent>) datadao.getAllObjects1(hql, null, null);
		return list;
	}


	@Override
	public List<TabAgent> queryAllAgent(TabAgent ta,
			int pageSize, int page) {
		 List<TabAgent> list1=null;
		// TODO Auto-generated method stub
		StringBuffer  hql=new StringBuffer("from  TabAgent where 1=1 and  tab_is_delete=0 ");
		if(ta==null){
			hql.append("  order by tab_write_date desc");
			  @SuppressWarnings("unchecked")
				List<TabAgent> list=(List<TabAgent>) datadao.pageQueryViaParam1(hql.toString(), pageSize, page, null, null);
			  list1=list;
			  return list;
		}
		ArrayList<String> paramsList=new ArrayList<String>(); 
		ArrayList<String> valuesList=new ArrayList<String>(); 
		
		if(ta.getTabAgentName()!=null&&!ta.getTabAgentName().equals("")){			
			hql.append( " and  tab_agent_name=:tab_agent_name ");
			
			paramsList.add("tab_agent_name");
			valuesList.add(ta.getTabAgentName());			
		}
		if(ta.getTabMobile()!=null&&!ta.getTabMobile().equals("")){
			hql.append( " and tab_mobile=:tab_mobile ");
			paramsList.add("tab_mobile");
			valuesList.add(ta.getTabMobile());	
			
		}
		if(ta.getTabTeamName()!=null&&!ta.getTabTeamName().equals("")){
			hql.append( " and tab_team_name=:TabTeamName ");
			paramsList.add("TabTeamName");
			valuesList.add(ta.getTabTeamName());	
		}
		String  params[]=new String[paramsList.size()];
    	String  values[]=new String[paramsList.size()];
    	for(int i=0;i<params.length;i++){
    		 params[i]=paramsList.get(i);
    		 values[i]=valuesList.get(i);
    	}
    	hql.append("   order by tab_write_date desc");
    	System.out.println(hql.toString());
        @SuppressWarnings("unchecked")
	    List<TabAgent> list=(List<TabAgent>) datadao.pageQueryViaParam1(hql.toString(), pageSize, page, params, values);
        list1=list;
        
		
		return list1;
	}



	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)	
	@Override
	public TabAgent addAgent(TabAgent ti) {
		// TODO Auto-generated method stub
		datadao.addObject(ti);
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)	
	@Override
	public TabAgent updateAgent(TabAgent ti) {
		// TODO Auto-generated method stub
		datadao.updateObject(ti);
		return null;
	}


	@Override
	public List<TabAgent> queryAgentById(Integer teamId) {
		// TODO Auto-generated method stub
		String  hql="from  TabAgent where tab_team_id=:teamId   order by tab_write_date desc";
		String  params[]={"teamId"};
		String  values[]={String.valueOf(teamId)};
        @SuppressWarnings("unchecked")
		List<TabAgent> list=    (List<TabAgent>) datadao.getAllObjects1(hql, params, values);
        return list;
	}


	@Override
	public TabAgent queryagentByAgentName(String AgentName) {
		// TODO Auto-generated method stub
		
		String  hql="from  TabAgent where tab_agent_name=:AgentName";
		String  params[]={"AgentName"};
		String  values[]={AgentName};
		TabAgent  ta=(TabAgent) datadao.getObjects(hql, params, values);
		return ta;
	}




	

	
	
	
		
}
