package com.yxbx.servicelmpl;



import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
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

import antlr.StringUtils;

import com.mchange.v2.lang.ObjectUtils;
import com.yxbx.bean.SeachInsurance;
import com.yxbx.dao.DataDao;
import com.yxbx.pojo.TabScheduleOfInsurance;
import com.yxbx.pojo.TabScheduleOfInsuranceTrue;

import com.yxbx.service.ScheduleOfInsuranceService;




@Entity
@Service("ScheduleOfInsuranceServicempl")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)	
public class ScheduleOfInsuranceServicempl implements  ScheduleOfInsuranceService {
	
		
	@Resource
	DataDao datadao;




	@Override
	public TabScheduleOfInsurance queryAllById(Integer Id) {
		// TODO Auto-generated method stub
		String  sql="from TabScheduleOfInsurance where id=:Id";
		String params[]={"Id"};
		String  values[]={String.valueOf(Id)};
		TabScheduleOfInsurance  tsg=(TabScheduleOfInsurance) datadao.getObjects(sql, params, values);
		return tsg;
	}

	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)	
	@Override
	public void addScheduleOfInsurance(TabScheduleOfInsurance ydi) {
		// TODO Auto-generated method stub
		datadao.addObject(ydi);
		List<TabScheduleOfInsuranceTrue> list=	(List<TabScheduleOfInsuranceTrue>) datadao.getObjectList("select * FROM tab_schedule_of_insurance where tabtype!='1'");
        for (int i = 0; i < list.size(); i++) {
     	   TabScheduleOfInsuranceTrue tof=list.get(i);
        	for (int y = 0; y < list.size(); y++) {
        		TabScheduleOfInsuranceTrue tof1=list.get(y);
            	if(i==y||tof1.getAgainState()!=0){
            		continue;
            	}else{
            		if(tof.getTabAgentName().equals(tof1.getTabAgentName())
            				&&tof.getTabAllAmount().compareTo(tof1.getTabAllAmount())==0
            				&&tof.getTabCarNum().equals(tof1.getTabCarNum())
            				){
            			String sql="update tab_schedule_of_insurance set tabtype=0 where tab_agent_name='"+ydi.getTabAgentName()+"' and tab_all_amount="+ydi.getTabAllAmount()+" and tab_car_num='"+ydi.getTabCarNum()+"'";
     				int executeBySql = datadao.executeBySql(sql);
            		
            		}
            	}
    			
				}
        }
	}

	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)	
	@Override
	public void updateScheduleOfInsurance(TabScheduleOfInsurance ydi) {
		// TODO Auto-generated method stub
		datadao.updateObject(ydi);
	}


	@Override
	public TabScheduleOfInsurance findScheduleOfInsurance(String username,
			String password) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public TabScheduleOfInsurance findScheduleOfInsurance(String mobile) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<TabScheduleOfInsurance> queryAllByObject(
			SeachInsurance si ) {
		  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		   String date=df.format(new Date());
	        
		StringBuffer  sql=new StringBuffer("from TabScheduleOfInsurance where 1=1  ");
//		String  params[]=null;
//		String values[]=null;
		ArrayList<String> list=new ArrayList<String>();
		ArrayList<String> listValues=new ArrayList<String>();
		
		if(si!=null){
			if(si.getId()!=null){
				sql.append(" and id=:Id");	
				list.add("Id");
				 listValues.add(String.valueOf(si.getId()));
				
			}
		/*	sql.append(" and  tab_start_date  >=:stime  and tab_start_date<=:date ");
			 list.add("stime");	
			 list.add("date");
			 listValues.add(String.valueOf(si.getTabStartDateStart()==null?"1800-01-01":si.getTabStartDateStart()));
			 listValues.add(String.valueOf(si.getTabStartDateEnd()==null?new Timestamp(System.currentTimeMillis()):si.getTabStartDateEnd()));
			
		        */
			 sql.append(" and  tab_end_date  >=:TabEndDateStart and tab_end_date<=:TabEndDateEnd ");
			 list.add("TabEndDateStart");	
			 list.add("TabEndDateEnd");
			 listValues.add(String.valueOf(si.getTabEndDateStart()==null?"1800-01-01":si.getTabEndDateStart()));
			 listValues.add(String.valueOf(si.getTabEndDateEnd()==null?"4040-01-01":si.getTabEndDateEnd()));
		
	    	       
			 sql.append(" and  tab_qd_date  >=:TabQdDateStart and tab_qd_date <=:TabQdDateEnd  ");
			 list.add("TabQdDateStart");	
			 list.add("TabQdDateEnd");
			 listValues.add(String.valueOf(si.getTabQdDateStart()==null?"1800-01-01":si.getTabQdDateStart()));
			 listValues.add(String.valueOf(si.getTabQdDateEnd()==null?"4040-01-01":si.getTabQdDateEnd()));
		
	
			 sql.append(" and  tab_write_date >=:TabWriteDateStart and tab_write_date<=:TabWriteDateEnd  ");	
			 list.add("TabWriteDateStart");	
			 list.add("TabWriteDateEnd");
			 listValues.add(String.valueOf(si.getTab_write_date_start()==null?"1800-01-01":si.getTab_write_date_start()));
			 listValues.add(String.valueOf(si.getTab_write_date_end()==null?new Timestamp(System.currentTimeMillis()):si.getTab_write_date_end()));
		
			if(si.getTabCarNum()!=null){
				 sql.append(" and   tab_car_num=:tabCarNum");
				 list.add("tabCarNum");
			
				 listValues.add(String.valueOf(si.getTabCarNum()));
				
				
			}
			if(si.getTabSafePerson()!=null){
				sql.append(" and  tab_safe_person=:TabSafePerson");
				 list.add("TabSafePerson");
				
				 listValues.add(String.valueOf(si.getTabSafePerson()));
				
			}
			if(si.getTabYxState()!=null){
				sql.append(" and  tab_yx_state=:TabYxState");
				 list.add("TabYxState");
				
				 listValues.add(String.valueOf(si.getTabYxState()));
				
				
			}
			if(si.getTabCarState()!=null){
				sql.append(" and  tab_car_state=:TabCarState");
				 list.add("TabCarState");
				
				 listValues.add(String.valueOf(si.getTabCarState()));
				
			}
			if(si.getTabChannel()!=null){
				sql.append(" and  tab_channel=:TabChannel");
				 list.add("TabChannel");
				
				 listValues.add(String.valueOf(si.getTabChannel()));
				
			}
			if(si.getTabInsuranceCompanyName()!=null){
				sql.append(" and  tab_insurance_company_name=:TabInsuranceCompanyName");
				 list.add("TabInsuranceCompanyName");			
				 listValues.add(String.valueOf(si.getTabInsuranceCompanyName()));
			
			}	
			if(si.getTabCdPerson()!=null){
				sql.append(" and  tab_cd_person=:TabCdPerson");
				 list.add("TabCdPerson");	
			
				 listValues.add(String.valueOf(si.getTabCdPerson()));
				
			}
			if(si.getTabWhPerson()!=null){
				sql.append(" and  tab_wh_person=:TabWhPerson");
				 list.add("TabWhPerson");
			
				 listValues.add(String.valueOf(si.getTabWhPerson()));
				 
			}
			if(si.getTabAgentName()!=null){
				sql.append(" and  tab_agent_name=:TabAgentName");
				 list.add("TabAgentName");
				 listValues.add(String.valueOf(si.getTabAgentName()));
				
			}
			if(si.getTabTeamOwnership()!=null){
				sql.append(" and  tab_team_ownership=:TabTeamOwnership");
				 list.add("TabTeamOwnership");
				 listValues.add(String.valueOf(si.getTabTeamOwnership()));
			}					
		}
			
			sql.append(" order by  tab_write_date desc");
			if(list.isEmpty()){
				@SuppressWarnings("unchecked")
				List<TabScheduleOfInsurance> list1=	(List<TabScheduleOfInsurance>) datadao.getAllObjects1(sql.toString(), null, null);
				return list1;
			}
			String  params[]=new String[list.size()];
			 String  values[]=new String[list.size()];
			for(int i=0;i<params.length;i++){
				params[i]=list.get(i);
				values[i]=listValues.get(i);
			}						
		
			String  sqlStr=sql.toString();
			
	
		@SuppressWarnings("unchecked")
		List<TabScheduleOfInsurance> list1=	(List<TabScheduleOfInsurance>) datadao.getAllObjects1(sqlStr, params, values);
//		private Integer id;
//		private Timestamp tabStartDate;//批单日期
//		private Timestamp tabEndDate;//到期日
//		private Timestamp tabQdDate;//签单日期
//		private String tabCarNum;//车牌
//		private String tabSafePerson;//被保险人				
//		private String tabYxState;//业务性质保批单		
//		private String tabCarState;//车辆使用性质
//		private String tabChannel;//渠道归属
//		private String tabInsuranceCompanyName;//保险公司名字
//		private String tabCdPerson;//出单人
//		private String tabWhPerson;//维护人
//		private String tabAgentName;//代理人姓名
//		private String tabAmountSate;//收款状态
//		private String tabTeamOwnership;//团队归属						
		return list1;
	}

	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)	
	@Override
	public void addScheduleOfInsuranceList(List<TabScheduleOfInsurance> toi) {
		// TODO Auto-generated method stub
		for(int i=0;i<toi.size();i++){
			datadao.addObject(toi.get(i));
		}
		List<TabScheduleOfInsuranceTrue> list=	(List<TabScheduleOfInsuranceTrue>) datadao.getObjectList("select * FROM tab_schedule_of_insurance where tabtype!='1'");
        for (int i = 0; i < list.size(); i++) {
     	   TabScheduleOfInsuranceTrue tof=list.get(i);
        	for (int y = 0; y < list.size(); y++) {
        		TabScheduleOfInsuranceTrue tof1=list.get(y);
            	if(i==y||tof1.getAgainState()!=0){
            		continue;
            	}else{
            		if(tof.getTabAgentName().equals(tof1.getTabAgentName())
            				&&tof.getTabAllAmount().compareTo(tof1.getTabAllAmount())==0
            				&&tof.getTabCarNum().equals(tof1.getTabCarNum())
            				){
            			String sql="update tab_schedule_of_insurance set tabtype=0 where tab_agent_name='"+tof.getTabAgentName()+"' and tab_all_amount="+tof.getTabAllAmount()+" and tab_car_num='"+tof.getTabCarNum()+"'";
     				int executeBySql = datadao.executeBySql(sql);
            		
            		}
            	}
    			
				}
		}
	}


	@Override
	public List<TabScheduleOfInsurance> queryAllByObject(int userId,SeachInsurance si,
			int limit, int page) {
		// TODO Auto-generated method stub
		   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		   String date=df.format(new Date());
	        
		StringBuffer  sql=new StringBuffer("from TabScheduleOfInsurance where 1=1  ");
//		String  params[]=null;
//		String values[]=null;
		ArrayList<String> list=new ArrayList<String>();
		ArrayList<String> listValues=new ArrayList<String>();
		if(userId>0){
			sql.append(" and tab_user_id="+userId);	
		}
		if(si!=null){
			if(si.getId()!=null){
				sql.append(" and id=:Id");	
				list.add("Id");
				 listValues.add(String.valueOf(si.getId()));
				
			}
			
		/*	sql.append(" and  tab_start_date  >=:stime  and tab_start_date<=:date ");
			 list.add("stime");	
			 list.add("date");
			 listValues.add(String.valueOf(si.getTabStartDateStart()==null?"1800-01-01":si.getTabStartDateStart()));
			 listValues.add(String.valueOf(si.getTabStartDateEnd()==null?new Timestamp(System.currentTimeMillis()):si.getTabStartDateEnd()));
			*/
		        
			 sql.append(" and  tab_end_date  >=:TabEndDateStart and tab_end_date<=:TabEndDateEnd ");
			 list.add("TabEndDateStart");	
			 list.add("TabEndDateEnd");
			 listValues.add(String.valueOf(si.getTabEndDateStart()==null?"1800-01-01":si.getTabEndDateStart()));
			 listValues.add(String.valueOf(si.getTabEndDateEnd()==null?"4040-01-01":si.getTabEndDateEnd()));
		
	    	       
			 sql.append(" and  tab_qd_date  >=:TabQdDateStart and tab_qd_date <=:TabQdDateEnd  ");
			 list.add("TabQdDateStart");	
			 list.add("TabQdDateEnd");
			 listValues.add(String.valueOf(si.getTabQdDateStart()==null?"1800-01-01":si.getTabQdDateStart()));
			 listValues.add(String.valueOf(si.getTabQdDateEnd()==null?"4040-01-01":si.getTabQdDateEnd()));
		
	
			 sql.append(" and  tab_write_date >=:TabWriteDateStart and tab_write_date<=:TabWriteDateEnd  ");	
			 list.add("TabWriteDateStart");	
			 list.add("TabWriteDateEnd");
			 listValues.add(String.valueOf(si.getTab_write_date_start()==null?"1800-01-01":si.getTab_write_date_start()));
			 listValues.add(String.valueOf(si.getTab_write_date_end()==null?new Timestamp(System.currentTimeMillis()):si.getTab_write_date_end()));
		
			if(si.getTabCarNum()!=null){
				 sql.append(" and   tab_car_num=:tabCarNum");
				 list.add("tabCarNum");
			
				 listValues.add(String.valueOf(si.getTabCarNum()));
				
				
			}
			if(si.getTabSafePerson()!=null){
				sql.append(" and  tab_safe_person=:TabSafePerson");
				 list.add("TabSafePerson");
				
				 listValues.add(String.valueOf(si.getTabSafePerson()));
				
			}
			if(si.getTabAmountSate()!=null){
				sql.append(" and  tab_amount_sate=:tabAmountSate");
				 list.add("tabAmountSate");
				 listValues.add(String.valueOf(si.getTabSafePerson()));
			}
			if(si.getTabYxState()!=null){
				sql.append(" and  tab_yx_state=:TabYxState");
				 list.add("TabYxState");
				
				 listValues.add(String.valueOf(si.getTabYxState()));
				
				
			}
			if(si.getTabCarState()!=null){
				sql.append(" and  tab_car_state=:TabCarState");
				 list.add("TabCarState");
				
				 listValues.add(String.valueOf(si.getTabCarState()));
				
			}
			if(si.getTabChannel()!=null){
				sql.append(" and  tab_channel=:TabChannel");
				 list.add("TabChannel");
				
				 listValues.add(String.valueOf(si.getTabChannel()));
				
			}
			if(si.getTabInsuranceCompanyName()!=null){
				sql.append(" and  tab_insurance_company_name=:TabInsuranceCompanyName");
				 list.add("TabInsuranceCompanyName");			
				 listValues.add(String.valueOf(si.getTabInsuranceCompanyName()));
			
			}	
			if(si.getTabCdPerson()!=null){
				sql.append(" and  tab_cd_person=:TabCdPerson");
				 list.add("TabCdPerson");	
			
				 listValues.add(String.valueOf(si.getTabCdPerson()));
				
			}
			if(si.getTabWhPerson()!=null){
				sql.append(" and  tab_wh_person=:TabWhPerson");
				 list.add("TabWhPerson");
			
				 listValues.add(String.valueOf(si.getTabWhPerson()));
				 
			}
			if(si.getTabAgentName()!=null){
				/*sql.append(" and  tab_agent_name=:TabAgentName");
				sql.append(" and  tab_agent_name like '%"+si.getTabAgentName()+"%' ");
				
				 list.add("TabAgentName");
				 listValues.add(String.valueOf(si.getTabAgentName()));*/
				sql.append(" and  tab_agent_name like :TabAgentName");
				 list.add("TabAgentName");
				 listValues.add("'%"+String.valueOf(si.getTabAgentName())+"%'");
			}
			if(si.getTabTeamOwnership()!=null){
				sql.append(" and  tab_team_ownership=:TabTeamOwnership");
				 list.add("TabTeamOwnership");
				 listValues.add(String.valueOf(si.getTabTeamOwnership()));
			}					
		}
			
			sql.append(" order by  tab_write_date desc");
			if(list.isEmpty()){
				@SuppressWarnings("unchecked")
				List<TabScheduleOfInsurance> list1=	(List<TabScheduleOfInsurance>) datadao.pageQueryViaParam1(sql.toString(), limit, page, null, null);
				return list1;
			}
			String  params[]=new String[list.size()];
			 String  values[]=new String[list.size()];
			for(int i=0;i<params.length;i++){
				params[i]=list.get(i);
				values[i]=listValues.get(i);
			}						
		
			String  sqlStr=sql.toString();
			
	
		@SuppressWarnings("unchecked")
		List<TabScheduleOfInsurance> list1=	(List<TabScheduleOfInsurance>) datadao.pageQueryViaParam1(sqlStr, limit, page, params, values);
//		private Integer id;
//		private Timestamp tabStartDate;//批单日期
//		private Timestamp tabEndDate;//到期日
//		private Timestamp tabQdDate;//签单日期
//		private String tabCarNum;//车牌
//		private String tabSafePerson;//被保险人				
//		private String tabYxState;//业务性质保批单		
//		private String tabCarState;//车辆使用性质
//		private String tabChannel;//渠道归属
//		private String tabInsuranceCompanyName;//保险公司名字
//		private String tabCdPerson;//出单人
//		private String tabWhPerson;//维护人
//		private String tabAgentName;//代理人姓名
//		private String tabAmountSate;//收款状态
//		private String tabTeamOwnership;//团队归属						
		return list1;
	}

	

	
	
	
		



	@Override
	public List<TabScheduleOfInsurance> queryAllByObject(int userId,
			SeachInsurance si) {
		// TODO Auto-generated method stub
		   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		   String date=df.format(new Date());
	        
		StringBuffer  sql=new StringBuffer("from TabScheduleOfInsurance where 1=1  ");
//		String  params[]=null;
//		String values[]=null;
		ArrayList<String> list=new ArrayList<String>();
		ArrayList<String> listValues=new ArrayList<String>();
		if(userId>0){
			sql.append(" and tab_user_id="+userId);	
		}
		if(si!=null){
			if(si.getId()!=null){
				sql.append(" and id=:Id");	
				list.add("Id");
				 listValues.add(String.valueOf(si.getId()));
				
			}
		/*	sql.append(" and  tab_start_date  >=:stime  and tab_start_date<=:date ");
			 list.add("stime");	
			 list.add("date");
			 listValues.add(String.valueOf(si.getTabStartDateStart()==null?"1800-01-01":si.getTabStartDateStart()));
			 listValues.add(String.valueOf(si.getTabStartDateEnd()==null?new Timestamp(System.currentTimeMillis()):si.getTabStartDateEnd()));
			*/
		        
			 sql.append(" and  tab_end_date  >=:TabEndDateStart and tab_end_date<=:TabEndDateEnd ");
			 list.add("TabEndDateStart");	
			 list.add("TabEndDateEnd");
			 listValues.add(String.valueOf(si.getTabEndDateStart()==null?"1800-01-01":si.getTabEndDateStart()));
			 listValues.add(String.valueOf(si.getTabEndDateEnd()==null?"4040-01-01":si.getTabEndDateEnd()));
		
	    	       
			 sql.append(" and  tab_qd_date  >=:TabQdDateStart and tab_qd_date <=:TabQdDateEnd  ");
			 list.add("TabQdDateStart");	
			 list.add("TabQdDateEnd");
			 listValues.add(String.valueOf(si.getTabQdDateStart()==null?"1800-01-01":si.getTabQdDateStart()));
			 listValues.add(String.valueOf(si.getTabQdDateEnd()==null?"4040-01-01":si.getTabQdDateEnd()));
		
	
			 sql.append(" and  tab_write_date >=:TabWriteDateStart and tab_write_date<=:TabWriteDateEnd  ");	
			 list.add("TabWriteDateStart");	
			 list.add("TabWriteDateEnd");
			 listValues.add(String.valueOf(si.getTab_write_date_start()==null?"1800-01-01":si.getTab_write_date_start()));
			 listValues.add(String.valueOf(si.getTab_write_date_end()==null?new Timestamp(System.currentTimeMillis()):si.getTab_write_date_end()));
		
			if(si.getTabCarNum()!=null){
				 sql.append(" and   tab_car_num=:tabCarNum");
				 list.add("tabCarNum");
			
				 listValues.add(String.valueOf(si.getTabCarNum()));
				
				
			}
			if(si.getTabSafePerson()!=null){
				sql.append(" and  tab_safe_person=:TabSafePerson");
				 list.add("TabSafePerson");
				
				 listValues.add(String.valueOf(si.getTabSafePerson()));
				
			}
			if(si.getTabYxState()!=null){
				sql.append(" and  tab_yx_state=:TabYxState");
				 list.add("TabYxState");
				
				 listValues.add(String.valueOf(si.getTabYxState()));
				
				
			}
			if(si.getTabCarState()!=null){
				sql.append(" and  tab_car_state=:TabCarState");
				 list.add("TabCarState");
				
				 listValues.add(String.valueOf(si.getTabCarState()));
				
			}
			if(si.getTabChannel()!=null){
				sql.append(" and  tab_channel=:TabChannel");
				 list.add("TabChannel");
				
				 listValues.add(String.valueOf(si.getTabChannel()));
				
			}
			if(si.getTabInsuranceCompanyName()!=null){
				sql.append(" and  tab_insurance_company_name=:TabInsuranceCompanyName");
				 list.add("TabInsuranceCompanyName");			
				 listValues.add(String.valueOf(si.getTabInsuranceCompanyName()));
			
			}	
			if(si.getTabCdPerson()!=null){
				sql.append(" and  tab_cd_person=:TabCdPerson");
				 list.add("TabCdPerson");	
			
				 listValues.add(String.valueOf(si.getTabCdPerson()));
				
			}
			if(si.getTabWhPerson()!=null){
				sql.append(" and  tab_wh_person=:TabWhPerson");
				 list.add("TabWhPerson");
			
				 listValues.add(String.valueOf(si.getTabWhPerson()));
				 
			}
			if(si.getTabAgentName()!=null){
				sql.append(" and  tab_agent_name like'%"+si.getTabAgentName()+"%' ");
				/* list.add("TabAgentName");
				 listValues.add(String.valueOf(si.getTabAgentName()));*/
				
			}
			if(si.getTabTeamOwnership()!=null){
				sql.append(" and  tab_team_ownership=:TabTeamOwnership");
				 list.add("TabTeamOwnership");
				 listValues.add(String.valueOf(si.getTabTeamOwnership()));
			}					
		}
			
			sql.append(" order by  tab_write_date desc");
			if(list.isEmpty()){
				@SuppressWarnings("unchecked")
				List<TabScheduleOfInsurance> list1=	(List<TabScheduleOfInsurance>) datadao.getAllObjects1(sql.toString(), null, null);
				return list1;
			}
			String  params[]=new String[list.size()];
			 String  values[]=new String[list.size()];
			for(int i=0;i<params.length;i++){
				params[i]=list.get(i);
				values[i]=listValues.get(i);
			}						
		
			String  sqlStr=sql.toString();
			
	
		@SuppressWarnings("unchecked")
		List<TabScheduleOfInsurance> list1=	(List<TabScheduleOfInsurance>) datadao.getAllObjects1(sqlStr, params, values);
//		private Integer id;
//		private Timestamp tabStartDate;//批单日期
//		private Timestamp tabEndDate;//到期日
//		private Timestamp tabQdDate;//签单日期
//		private String tabCarNum;//车牌
//		private String tabSafePerson;//被保险人				
//		private String tabYxState;//业务性质保批单		
//		private String tabCarState;//车辆使用性质
//		private String tabChannel;//渠道归属
//		private String tabInsuranceCompanyName;//保险公司名字
//		private String tabCdPerson;//出单人
//		private String tabWhPerson;//维护人
//		private String tabAgentName;//代理人姓名
//		private String tabAmountSate;//收款状态
//		private String tabTeamOwnership;//团队归属						
		return list1;
	}
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)	
	@Override
	public void deleteTabScheduleOfInsurance(TabScheduleOfInsurance toi) {
		// TODO Auto-generated method stub
		datadao.deleteObject(toi);
	}

	@Override
	public List<TabScheduleOfInsurance> queryAllByObjectWaitingPay(int userId,
			SeachInsurance si) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TabScheduleOfInsurance> queryAllByObjectWaitingPay(int userId,
			SeachInsurance si, int pageSize, int page) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		   String date=df.format(new Date());
	        
		StringBuffer  sql=new StringBuffer("from TabScheduleOfInsurance where 1=1  ");
//		String  params[]=null;
//		String values[]=null;
		ArrayList<String> list=new ArrayList<String>();
		ArrayList<String> listValues=new ArrayList<String>();
		if(userId>0){
			sql.append(" and tab_user_id="+userId);	
		}
		if(si!=null){
			if(si.getId()!=null){
				sql.append(" and id=:Id");	
				list.add("Id");
				 listValues.add(String.valueOf(si.getId()));
				
			}
			/*sql.append(" and  tab_start_date  >=:stime  and tab_start_date<=:date ");
			 list.add("stime");	
			 list.add("date");
			 listValues.add(String.valueOf(si.getTabStartDateStart()==null?"1800-01-01":si.getTabStartDateStart()));
			 listValues.add(String.valueOf(si.getTabStartDateEnd()==null?new Timestamp(System.currentTimeMillis()):si.getTabStartDateEnd()));
			*/
		        
			 sql.append(" and  tab_end_date  >=:TabEndDateStart and tab_end_date<=:TabEndDateEnd ");
			 list.add("TabEndDateStart");	
			 list.add("TabEndDateEnd");
			 listValues.add(String.valueOf(si.getTabEndDateStart()==null?"1800-01-01":si.getTabEndDateStart()));
			 listValues.add(String.valueOf(si.getTabEndDateEnd()==null?"4040-01-01":si.getTabEndDateEnd()));
		
	    	       
			 sql.append(" and  tab_qd_date  >=:TabQdDateStart and tab_qd_date <=:TabQdDateEnd  ");
			 list.add("TabQdDateStart");	
			 list.add("TabQdDateEnd");
			 listValues.add(String.valueOf(si.getTabQdDateStart()==null?"1800-01-01":si.getTabQdDateStart()));
			 listValues.add(String.valueOf(si.getTabQdDateEnd()==null?"4040-01-01":si.getTabQdDateEnd()));
		
	
			 sql.append(" and  tab_write_date >=:TabWriteDateStart and tab_write_date<=:TabWriteDateEnd  ");	
			 list.add("TabWriteDateStart");	
			 list.add("TabWriteDateEnd");
			 listValues.add(String.valueOf(si.getTab_write_date_start()==null?"1800-01-01":si.getTab_write_date_start()));
			 listValues.add(String.valueOf(si.getTab_write_date_end()==null?new Timestamp(System.currentTimeMillis()):si.getTab_write_date_end()));
		
			if(si.getTabCarNum()!=null){
				 sql.append(" and   tab_car_num=:tabCarNum");
				 list.add("tabCarNum");
			
				 listValues.add(String.valueOf(si.getTabCarNum()));
				
				
			}
			if(si.getTabSafePerson()!=null){
				sql.append(" and  tab_safe_person=:TabSafePerson");
				 list.add("TabSafePerson");
				
				 listValues.add(String.valueOf(si.getTabSafePerson()));
				
			}
			if(si.getTabYxState()!=null){
				sql.append(" and  tab_yx_state=:TabYxState");
				 list.add("TabYxState");
				
				 listValues.add(String.valueOf(si.getTabYxState()));
				
				
			}
			if(si.getTabCarState()!=null){
				sql.append(" and  tab_car_state=:TabCarState");
				 list.add("TabCarState");
				
				 listValues.add(String.valueOf(si.getTabCarState()));
				
			}
			if(si.getTabChannel()!=null){
				sql.append(" and  tab_channel=:TabChannel");
				 list.add("TabChannel");
				
				 listValues.add(String.valueOf(si.getTabChannel()));
				
			}
			if(si.getTabInsuranceCompanyName()!=null){
				sql.append(" and  tab_insurance_company_name=:TabInsuranceCompanyName");
				 list.add("TabInsuranceCompanyName");			
				 listValues.add(String.valueOf(si.getTabInsuranceCompanyName()));
			
			}	
			if(si.getTabCdPerson()!=null){
				sql.append(" and  tab_cd_person=:TabCdPerson");
				 list.add("TabCdPerson");	
			
				 listValues.add(String.valueOf(si.getTabCdPerson()));
				
			}
			if(si.getTabWhPerson()!=null){
				sql.append(" and  tab_wh_person=:TabWhPerson");
				 list.add("TabWhPerson");
			
				 listValues.add(String.valueOf(si.getTabWhPerson()));
				 
			}
			if(si.getTabAgentName()!=null){
				sql.append(" and  tab_agent_name=:TabAgentName");
				 list.add("TabAgentName");
				 listValues.add(String.valueOf(si.getTabAgentName()));
				
			}
			if(si.getTabTeamOwnership()!=null){
				sql.append(" and  tab_team_ownership=:TabTeamOwnership");
				 list.add("TabTeamOwnership");
				 listValues.add(String.valueOf(si.getTabTeamOwnership()));
			}					
		}
			
			sql.append(" order by  tab_write_date desc");
			if(list.isEmpty()){
				@SuppressWarnings("unchecked")
				List<TabScheduleOfInsurance> list1=	(List<TabScheduleOfInsurance>) datadao.pageQueryViaParam1(sql.toString(), pageSize, page, null, null);
				return list1;
			}
			String  params[]=new String[list.size()];
			 String  values[]=new String[list.size()];
			for(int i=0;i<params.length;i++){
				params[i]=list.get(i);
				values[i]=listValues.get(i);
			}						
		
			String  sqlStr=sql.toString();
			
	
		@SuppressWarnings("unchecked")
		List<TabScheduleOfInsurance> list1=	(List<TabScheduleOfInsurance>) datadao.pageQueryViaParam1(sqlStr, pageSize, page, params, values);
//		private Integer id;
//		private Timestamp tabStartDate;//批单日期
//		private Timestamp tabEndDate;//到期日
//		private Timestamp tabQdDate;//签单日期
//		private String tabCarNum;//车牌
//		private String tabSafePerson;//被保险人				
//		private String tabYxState;//业务性质保批单		
//		private String tabCarState;//车辆使用性质
//		private String tabChannel;//渠道归属
//		private String tabInsuranceCompanyName;//保险公司名字
//		private String tabCdPerson;//出单人
//		private String tabWhPerson;//维护人
//		private String tabAgentName;//代理人姓名
//		private String tabAmountSate;//收款状态
//		private String tabTeamOwnership;//团队归属						
		return list1;
	}

	@Override
	public List<TabScheduleOfInsurance> updateType(TabScheduleOfInsurance tab,
			String tableName) {
		// TODO Auto-generated method stub
		return null;
	}


	
	}
