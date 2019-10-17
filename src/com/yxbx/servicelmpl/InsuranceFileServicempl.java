package com.yxbx.servicelmpl;



import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.Entity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yxbx.dao.DataDao;
import com.yxbx.pojo.TabInsuranceFile;
import com.yxbx.pojo.TabUser;
import com.yxbx.service.AgentService;
import com.yxbx.service.InsuranceFileService;
import com.yxbx.service.InsurerService;
import com.yxbx.service.UserService;



@Entity
@Service("InsuranceFileService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)	
public class InsuranceFileServicempl implements  InsuranceFileService {
	
		
	@Resource
	DataDao datadao;





	@Override
	public List<TabInsuranceFile> queryInsuranceFileAll() {
		// TODO Auto-generated method stub
		String  hql="from  TabInsuranceFile where tab_is_delete=0  order by tab_write_date desc";
		         @SuppressWarnings("unchecked")
				List<TabInsuranceFile> list=    (List<TabInsuranceFile>) datadao.getAllObjects1(hql, null, null);
		return list;
	}


	



	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)	
	@Override
	public TabInsuranceFile addInsuranceFile(TabInsuranceFile ti) {
		// TODO Auto-generated method stub
		datadao.addObject(ti);
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)	
	@Override
	public TabInsuranceFile updateInsuranceFile(TabInsuranceFile ti) {
		// TODO Auto-generated method stub
		datadao.updateObject(ti);
		return null;
	}






	@Override
	public TabInsuranceFile queryInsuranceFileById(Integer Id) {
		// TODO Auto-generated method stub
		TabInsuranceFile ti=datadao.getObjectById( TabInsuranceFile.class,Id);
		return ti;
	}






	@Override
	public List<TabInsuranceFile> queryInsuranceFileByInsuranceId(
			Integer insuranceFileId) {
		// TODO Auto-generated method stub
		String   sql="from  TabInsuranceFile where  tab_insurance_true_id=:tabInsuranceId";
		String[] params={"tabInsuranceId"};
	    String[] values={String.valueOf(insuranceFileId)};
	    @SuppressWarnings("unchecked")
		List<TabInsuranceFile>  list=(List<TabInsuranceFile>) datadao.getAllObjects1(sql, params, values);
		return list;
	}






	@Override
	public List<TabInsuranceFile> queryInsuranceFileByInsuranceTureId(
			Integer insuranceFileTrueId) {
		String   sql="from  TabInsuranceFile where  tabInsuranceTrueId=:tabInsuranceId";
		String[] params={"tabInsuranceId"};
	    String[] values={String.valueOf(insuranceFileTrueId)};
	    @SuppressWarnings("unchecked")
		List<TabInsuranceFile>  list=(List<TabInsuranceFile>) datadao.getAllObjects1(sql, params, values);
		return list;
	}






	@Override
	public List<TabInsuranceFile> queryInsuranceFileByPayId(Integer payId) {
		// TODO Auto-generated method stub
		String   sql="from  TabInsuranceFile where  tabPayId=:tabPayId";
		String[] params={"tabPayId"};
	    String[] values={String.valueOf(payId)};
	    @SuppressWarnings("unchecked")
		List<TabInsuranceFile>  list=(List<TabInsuranceFile>) datadao.getAllObjects1(sql, params, values);
		return list;
	}






	@Override
	public List<TabInsuranceFile> queryInsuranceFileByPayOpenId(
			Integer payOpenId) {
		// TODO Auto-generated method stub
		String   sql="from  TabInsuranceFile where  tabPayOpenId=:tabPayOpenId";
		String[] params={"tabPayOpenId"};
	    String[] values={String.valueOf(payOpenId)};
	    @SuppressWarnings("unchecked")
		List<TabInsuranceFile>  list=(List<TabInsuranceFile>) datadao.getAllObjects1(sql, params, values);
		return list;
	}






	@Override
	public List<TabInsuranceFile> queryInsuranceFileByImgUrl(String imgUrl) {
		// TODO Auto-generated method stub
		String   sql="from  TabInsuranceFile where  tabFileAddress=:imgUrl";
		String[] params={"imgUrl"};
	    String[] values={imgUrl};
	    @SuppressWarnings("unchecked")
		List<TabInsuranceFile>  list=(List<TabInsuranceFile>) datadao.getAllObjects1(sql, params, values);
		return list;
	}





	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)	
	@Override
	public int  deleteInsuranceFile(TabInsuranceFile tf) {
		// TODO Auto-generated method stub
		datadao.deleteObject(tf);
		return 0;
	}






	

	
	
	
		
}
