package com.yxbx.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;

public class SeachInsurance {
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getTabStartDateStart() {
		return tabStartDateStart;
	}
	public void setTabStartDateStart(Timestamp tabStartDateStart) {
		this.tabStartDateStart = tabStartDateStart;
	}
	public Timestamp getTabStartDateEnd() {
		return tabStartDateEnd;
	}
	public void setTabStartDateEnd(Timestamp tabStartDateEnd) {
		this.tabStartDateEnd = tabStartDateEnd;
	}
	public Timestamp getTabEndDateStart() {
		return tabEndDateStart;
	}
	public void setTabEndDateStart(Timestamp tabEndDateStart) {
		this.tabEndDateStart = tabEndDateStart;
	}
	public Timestamp getTabEndDateEnd() {
		return tabEndDateEnd;
	}
	public void setTabEndDateEnd(Timestamp tabEndDateEnd) {
		this.tabEndDateEnd = tabEndDateEnd;
	}
	public Timestamp getTabQdDateStart() {
		return tabQdDateStart;
	}
	public void setTabQdDateStart(Timestamp tabQdDateStart) {
		this.tabQdDateStart = tabQdDateStart;
	}
	public Timestamp getTabQdDateEnd() {
		return tabQdDateEnd;
	}
	public void setTabQdDateEnd(Timestamp tabQdDateEnd) {
		this.tabQdDateEnd = tabQdDateEnd;
	}
	public Timestamp getTab_write_date_start() {
		return tab_write_date_start;
	}
	public void setTab_write_date_start(Timestamp tab_write_date_start) {
		this.tab_write_date_start = tab_write_date_start;
	}
	public Timestamp getTab_write_date_end() {
		return tab_write_date_end;
	}
	public void setTab_write_date_end(Timestamp tab_write_date_end) {
		this.tab_write_date_end = tab_write_date_end;
	}
	public String getTabCarNum() {
		return tabCarNum;
	}
	public void setTabCarNum(String tabCarNum) {
		this.tabCarNum = tabCarNum;
	}
	public String getTabSafePerson() {
		return tabSafePerson;
	}
	public void setTabSafePerson(String tabSafePerson) {
		this.tabSafePerson = tabSafePerson;
	}
	public String getTabYxState() {
		return tabYxState;
	}
	public void setTabYxState(String tabYxState) {
		this.tabYxState = tabYxState;
	}
	public String getTabCarState() {
		return tabCarState;
	}
	public void setTabCarState(String tabCarState) {
		this.tabCarState = tabCarState;
	}
	public String getTabChannel() {
		return tabChannel;
	}
	public void setTabChannel(String tabChannel) {
		this.tabChannel = tabChannel;
	}
	public String getTabInsuranceCompanyName() {
		return tabInsuranceCompanyName;
	}
	public void setTabInsuranceCompanyName(String tabInsuranceCompanyName) {
		this.tabInsuranceCompanyName = tabInsuranceCompanyName;
	}
	public String getTabCdPerson() {
		return tabCdPerson;
	}
	public void setTabCdPerson(String tabCdPerson) {
		this.tabCdPerson = tabCdPerson;
	}
	public String getTabWhPerson() {
		return tabWhPerson;
	}
	public void setTabWhPerson(String tabWhPerson) {
		this.tabWhPerson = tabWhPerson;
	}
	public String getTabAgentName() {
		return tabAgentName;
	}
	public void setTabAgentName(String tabAgentName) {
		this.tabAgentName = tabAgentName;
	}
	public String getTabAmountSate() {
		return tabAmountSate;
	}
	public void setTabAmountSate(String tabAmountSate) {
		this.tabAmountSate = tabAmountSate;
	}
	public String getTabTeamOwnership() {
		return tabTeamOwnership;
	}
	public void setTabTeamOwnership(String tabTeamOwnership) {
		this.tabTeamOwnership = tabTeamOwnership;
	}
	public String getTabIsAuditing() {
		return tabIsAuditing;
	}
	public void setTabIsAuditing(String tabIsAuditing) {
		this.tabIsAuditing = tabIsAuditing;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getTabAllAmount() {
		return tabAllAmount;
	}
	public void setTabAllAmount(BigDecimal tabAllAmount) {
		this.tabAllAmount = tabAllAmount;
	}
	public String getFalg() {
		return falg;
	}
	public void setFalg(String falg) {
		this.falg = falg;
	}
	public String getSxfalg() {
		if(StringUtils.isEmpty(sxfalg)){
			return "1";
		}
		return sxfalg;
	}
	public void setSxfalg(String sxfalg) {
		this.sxfalg = sxfalg;
	}

	public Timestamp getTabPayDateStart() {
		return tabPayDateStart;
	}

	public void setTabPayDateStart(Timestamp tabPayDateStart) {
		this.tabPayDateStart = tabPayDateStart;
	}

	public Timestamp getTabPayDateEnd() {
		return tabPayDateEnd;
	}

	public void setTabPayDateEnd(Timestamp tabPayDateEnd) {
		this.tabPayDateEnd = tabPayDateEnd;
	}

	public String getTabAgentPay() {
		return tabAgentPay;
	}

	public void setTabAgentPay(String tabAgentPay) {
		this.tabAgentPay = tabAgentPay;
	}

	public String getTabIsRenewal() {
		return tabIsRenewal;
	}

	public void setTabIsRenewal(String tabIsRenewal) {
		this.tabIsRenewal = tabIsRenewal;
	}

	public String getTabIsCommit() {
		return tabIsCommit;
	}

	public void setTabIsCommit(String tabIsCommit) {
		this.tabIsCommit = tabIsCommit;
	}

	private Integer id;
	private Timestamp tabStartDateStart;//批单日期 开始
	private Timestamp tabStartDateEnd;//批单日期 结束
	private Timestamp tabEndDateStart;//到期日 开始
	private Timestamp tabEndDateEnd;//到期日 结束
	private Timestamp tabQdDateStart;//签单日期 开始
	private Timestamp tabQdDateEnd;//签单日期 结束
	private Timestamp tabPayDateStart;//打款日期 开始
	private Timestamp tabPayDateEnd;//打款日期 结束
	private Timestamp tab_write_date_start;//录入时间 开始
	private Timestamp tab_write_date_end;//录入时间 结束
	private String tabCarNum;//车牌
	private String tabSafePerson;//被保险人
	private String tabYxState;//业务性质保批单
	private String tabAgentPay;//结算方式
	private String tabIsRenewal;//转续保
	private String tabCarState;//车辆使用性质
	private String tabChannel;//渠道归属
	private String tabInsuranceCompanyName;//保险公司名字
	private String tabCdPerson;//出单人
	private String tabWhPerson;//维护人
	private String tabAgentName;//代理人姓名
	private String tabAmountSate;//收款状态
	private String tabIsAuditing; //打款状态
	private String tabTeamOwnership;//团队归属
	private String tabIsCommit;//是否已提交
	private String type;//是否取消重复
	private BigDecimal tabAllAmount;
	private String falg;
	private String sxfalg="1";
}
