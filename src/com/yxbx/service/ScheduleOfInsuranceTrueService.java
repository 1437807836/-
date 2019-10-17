package com.yxbx.service;

import java.util.List;

import com.yxbx.bean.SeachInsurance;
import com.yxbx.pojo.TabScheduleOfInsuranceTrue;

public interface ScheduleOfInsuranceTrueService {
	public abstract TabScheduleOfInsuranceTrue queryAllById(Integer Id);
	public abstract void deleteScheduleOfInsuranceTrue(TabScheduleOfInsuranceTrue toit);
	public abstract List<TabScheduleOfInsuranceTrue> queryAllByObject(SeachInsurance si,String falg);
	public abstract List<TabScheduleOfInsuranceTrue> queryAllByObjectOrderByAllAmount(SeachInsurance si);
	public abstract int queryAllByObjectCountAmount(SeachInsurance si);
	public abstract List<TabScheduleOfInsuranceTrue> queryAllByObject(int  userId,SeachInsurance si);//queryAllByObjectWaitingPay
	public abstract List<TabScheduleOfInsuranceTrue> queryAllByObjectqueryWaitingPay(SeachInsurance si);//
	public abstract List<TabScheduleOfInsuranceTrue> queryAllByObjectqueryWaitingOpenPay(SeachInsurance si);//

	public abstract List<TabScheduleOfInsuranceTrue> queryAllByObject(int userId,SeachInsurance si,int pageSize,int page);
	public abstract List<TabScheduleOfInsuranceTrue> queryAllByObjectAll(int userId,SeachInsurance si,int pageSize,int page);
	public abstract List<TabScheduleOfInsuranceTrue> updateType(TabScheduleOfInsuranceTrue tab,String tableName);
	public abstract List<TabScheduleOfInsuranceTrue> queryAllByObjectWaitingPay(int userId,SeachInsurance si,int pageSize,int page);
	public abstract List<TabScheduleOfInsuranceTrue> queryAllByObjectWaitingOpenPay(int userId,SeachInsurance si,int pageSize,int page);
	public abstract void   addScheduleOfInsurance(TabScheduleOfInsuranceTrue toi);
	public abstract void   updateScheduleOfInsurance(TabScheduleOfInsuranceTrue ydi);
	public abstract TabScheduleOfInsuranceTrue  findScheduleOfInsurance(String  username,String  password);
	public abstract TabScheduleOfInsuranceTrue  findScheduleOfInsurance(String mobile);
	public abstract void   addScheduleOfInsuranceList(List<TabScheduleOfInsuranceTrue> toi);

	List<TabScheduleOfInsuranceTrue> queryAllTabScheduleOfInsuranceTrue(int page, String idStr);

	String addScheduleOfInsuranceTrueOfExcel(String filePath, String fileName, int userId, String name);

	String updateInsuranceTabIsAuditing(String id, String tabIsAuditing);

	String commitInsuranceTabIsAuditing(String id);

	String commitManyInsuranceTabIsAuditing(String idStr);

	String commitSpecialInsuranceTabIsAuditing(String idStr);
}
