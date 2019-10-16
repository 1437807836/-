package com.yxbx.servicelmpl;



import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.Entity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yxbx.dao.DataDao;
import com.yxbx.pojo.TabTeam;
import com.yxbx.pojo.TabUser;
import com.yxbx.service.AgentService;
import com.yxbx.service.InsurerService;
import com.yxbx.service.TeamService;
import com.yxbx.service.UserService;



@Entity
@Service("TeamService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)	
public class TeamServicempl implements  TeamService {
	
		
	@Resource
	DataDao datadao;



	@Override
	public TabTeam queryTabTeamById(Integer Id) {
		// TODO Auto-generated method stub
		TabTeam ti=datadao.getObjectById( TabTeam.class,Id);
		return ti;
	}


	@Override
	public List<TabTeam> queryTabTeamAll() {
		// TODO Auto-generated method stub
		String  hql="from  TabTeam where tab_is_delete=0 order by tab_write_date desc";
		         @SuppressWarnings("unchecked")
				List<TabTeam> list=    (List<TabTeam>) datadao.getAllObjects1(hql, null, null);
		return list;
	}


	@Override
	public List<TabTeam> queryAllTabTeam(TabTeam ta,
			int pageSize, int page) {
		 List<TabTeam> list1=null;
		// TODO Auto-generated method stub
		StringBuffer  hql=new StringBuffer("from  TabTeam where 1=1 and tab_is_delete=0");
		if(ta==null){
			hql.append("  order by tab_write_date desc");
			  @SuppressWarnings("unchecked")
				List<TabTeam> list=(List<TabTeam>) datadao.pageQueryViaParam1(hql.toString(), pageSize, page, null, null);
			  list1=list;
			  return list;
		}else{
			hql.append(" and tab_team_name=:tab_team_name and tab_is_delete=0  order by tab_write_date desc");
		
			String  params[]=new String[]{"tab_team_name"};
	    	String  values[]=new String[]{ta.getTabTeamName()};
	    	
	    	hql.append("  and  order by tab_write_date desc");
	        @SuppressWarnings("unchecked")
		    List<TabTeam> list=(List<TabTeam>) datadao.pageQueryViaParam1(hql.toString(), pageSize, page, params, values);
	        list1=list;
		}
		
		
        
		
		return list1;
	}



	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)	
	@Override
	public TabTeam addTabTeam(TabTeam ti) {
		// TODO Auto-generated method stub
		datadao.addObject(ti);
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)	
	@Override
	public TabTeam updateTabTeam(TabTeam ti) {
		// TODO Auto-generated method stub
		datadao.updateObject(ti);
		return null;
	}




	

	
	
	
		
}
