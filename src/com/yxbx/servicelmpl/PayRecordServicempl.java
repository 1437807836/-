package com.yxbx.servicelmpl;



import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.Entity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yxbx.dao.DataDao;

import com.yxbx.pojo.TabPayRecord;

import com.yxbx.service.PayRecordService;




@Entity
@Service("PayRecordService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)	
public class PayRecordServicempl implements  PayRecordService {
	
		
	@Resource
	DataDao datadao;

	@Override
	public List<TabPayRecord> queryTabPayRecordByuserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TabPayRecord queryByRecordByName(String AgentName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TabPayRecord queryRecordByById(Integer Id) {
		// TODO Auto-generated method stub
		String  hql="from  TabPayRecord where id=:id";
		String prams[]={"id"};
		String  values[]={String .valueOf(Id)};
		TabPayRecord tr=(TabPayRecord) datadao.getObjects(hql, prams, values);
		return tr;
	}

	@Override
	public List<TabPayRecord> queryPayRecordAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)	
	@Override
	public TabPayRecord addPayRecord(TabPayRecord tpr) {
		// TODO Auto-generated method stub
		datadao.addObject(tpr);
		return null;
	}
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)	
	@Override
	public TabPayRecord updatePayRecord(TabPayRecord tpr) {
		// TODO Auto-generated method stub
		datadao.updateObject(tpr);
		return null;
	}

	@Override
	public List<TabPayRecord> queryAllTabPayRecord(TabPayRecord tpr,
			int pageSize, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TabPayRecord> queryTabPayRecordByTrueId(Integer trueId ,String payState) {
		// TODO Auto-generated method stub
		String  hql="from  TabPayRecord where tab_insurance_true_id=:trueId and tab_pay_state=:payState  order by tab_write_date";
		String params[]={"trueId","payState"};
		String  values[]={String.valueOf(trueId),payState};
		 @SuppressWarnings("unchecked")
		List<TabPayRecord>  list=(List<TabPayRecord>) datadao.getAllObjects1(hql, params, values);
		return list;
	}

	@Override
	public List<TabPayRecord> queryTabPayRecordByPayState(String payState) {
		// TODO Auto-generated method stub
		String  hql="from  TabPayRecord where  tab_pay_state=:payState  order by tab_write_date";
		String params[]={"payState"};
		String  values[]={payState};
		 @SuppressWarnings("unchecked")
		List<TabPayRecord>  list=(List<TabPayRecord>) datadao.getAllObjects1(hql, params, values);
		return list;
	}

	@Override
	public List<TabPayRecord> queryTabPayRecordByPayStatePage(String payState,
			int pageSize, int page) {
		// TODO Auto-generated method stub
		String  hql="from  TabPayRecord where  tab_pay_state=:payState  order by tab_write_date";
		String params[]={"payState"};
		String  values[]={payState};
		 @SuppressWarnings("unchecked")
		List<TabPayRecord>  list=(List<TabPayRecord>) datadao.pageQueryViaParam1(hql, pageSize, page, params, values);
		return list;
	}



	
		
}
