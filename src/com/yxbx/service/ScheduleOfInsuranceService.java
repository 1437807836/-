package com.yxbx.service;

import java.util.List;

import com.yxbx.bean.SeachInsurance;
import com.yxbx.pojo.TabScheduleOfInsurance;
import com.yxbx.pojo.TabScheduleOfInsuranceTrue;

public interface ScheduleOfInsuranceService {
	public abstract TabScheduleOfInsurance queryAllById(Integer Id);
	public abstract void deleteTabScheduleOfInsurance(TabScheduleOfInsurance toi);
	public abstract List<TabScheduleOfInsurance> queryAllByObject(SeachInsurance si);
	public abstract List<TabScheduleOfInsurance> queryAllByObject(int  userId,SeachInsurance si );
	public abstract List<TabScheduleOfInsurance> queryAllByObjectWaitingPay(int  userId,SeachInsurance si );
	public abstract List<TabScheduleOfInsurance> queryAllByObject(int userId,SeachInsurance si,int pageSize,int page);
	public abstract List<TabScheduleOfInsurance> queryAllByObjectWaitingPay(int userId,SeachInsurance si,int pageSize,int page);
	public abstract List<TabScheduleOfInsurance> updateType(TabScheduleOfInsurance tab,String tableName);

	public abstract void   addScheduleOfInsurance(TabScheduleOfInsurance toi);
	public abstract void   updateScheduleOfInsurance(TabScheduleOfInsurance ydi);
	public abstract TabScheduleOfInsurance  findScheduleOfInsurance(String  username,String  password);
	public abstract TabScheduleOfInsurance  findScheduleOfInsurance(String mobile);
	public abstract void   addScheduleOfInsuranceList(List<TabScheduleOfInsurance> toi);
}
