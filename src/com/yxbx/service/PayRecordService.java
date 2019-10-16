package com.yxbx.service;

import java.util.List;


import com.yxbx.pojo.TabPayRecord;

public interface PayRecordService {
	public abstract List<TabPayRecord> queryTabPayRecordByuserId(Integer userId);
	public abstract List<TabPayRecord> queryTabPayRecordByTrueId(Integer trueId,String payState);
	public abstract List<TabPayRecord> queryTabPayRecordByPayState(String payState);
	public abstract List<TabPayRecord> queryTabPayRecordByPayStatePage(String payState, int  pageSize,int page);
	public abstract TabPayRecord queryByRecordByName(String AgentName);
	public abstract TabPayRecord queryRecordByById(Integer Id);
	public abstract List<TabPayRecord> queryPayRecordAll();
	public abstract TabPayRecord addPayRecord(TabPayRecord tpr);
	public abstract TabPayRecord updatePayRecord(TabPayRecord tpr);
	public abstract List<TabPayRecord>  queryAllTabPayRecord(TabPayRecord tpr, int  pageSize,int page);
	
}
