package com.yxbx.pojo;

import java.sql.Timestamp;
import java.util.List;


public class TabScheduleOfInsuranceTrues {

	// Fields

	private Integer id;
	private String tabAgentName;
	private String tabAgentPay;
	private String tabAllAmount;
	private String tabAmountSate;
	private String tabAnnotations;
	private String tabAnnotationsPerson;
	private String tabBankNum;
	private String tabCarNum;
	private String tabCarState;
	private String tabCardState;
	private String tabCdPerson;
	private String tabChannel;
	private String tabCommercialInsurance;
	private String tabCommissionAmount;
	private String tabCommissionOther;
	private String tabCompulsoryInsurance;
	private String tabCustomerMobile;
	private Timestamp tabEndDate;
	private String tabIllustrate;
	private String tabInsuranceCompanyName;
	private String tabIsAuditing;
	private String tabIsPayAmount;
	private String tabJbfjql;
	private String tabJbfsyl;
	private String tabJfysAmount;
	private String tabManagerUser;
	private String tabMessage;
	private Timestamp tabQdDate;
	private String tabRealAmount;
	private String tabSafePerson;
	private Timestamp tabStartDate;
	private String tabTax;
	private String tabTeamOwnership;
	private Integer tabUserId;
	private String tabWhPerson;
	private Timestamp tabWriteDate;
	private String tabXlsxName;
	private String tabYgzhjqAmount;
	private String tabYgzhsyAmount;
	private String tabYxState;
	private String tabZbfJ;

	private String realOpenPay;// 批次实收
	private String realOpenPayState;// 公司状态

	private List<TabInsuranceFile> tif = null;// 附件
	private TabPayRecord ypt = null;// 附件
	private int againState;// 可能重复

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTabAgentName() {
		return tabAgentName;
	}

	public void setTabAgentName(String tabAgentName) {
		this.tabAgentName = tabAgentName;
	}

	public String getTabAgentPay() {
		return tabAgentPay;
	}

	public void setTabAgentPay(String tabAgentPay) {
		this.tabAgentPay = tabAgentPay;
	}

	public String getTabAllAmount() {
		return tabAllAmount;
	}

	public void setTabAllAmount(String tabAllAmount) {
		this.tabAllAmount = tabAllAmount;
	}

	public String getTabAmountSate() {
		return tabAmountSate;
	}

	public void setTabAmountSate(String tabAmountSate) {
		this.tabAmountSate = tabAmountSate;
	}

	public String getTabAnnotations() {
		return tabAnnotations;
	}

	public void setTabAnnotations(String tabAnnotations) {
		this.tabAnnotations = tabAnnotations;
	}

	public String getTabAnnotationsPerson() {
		return tabAnnotationsPerson;
	}

	public void setTabAnnotationsPerson(String tabAnnotationsPerson) {
		this.tabAnnotationsPerson = tabAnnotationsPerson;
	}

	public String getTabBankNum() {
		return tabBankNum;
	}

	public void setTabBankNum(String tabBankNum) {
		this.tabBankNum = tabBankNum;
	}

	public String getTabCarNum() {
		return tabCarNum;
	}

	public void setTabCarNum(String tabCarNum) {
		this.tabCarNum = tabCarNum;
	}

	public String getTabCarState() {
		return tabCarState;
	}

	public void setTabCarState(String tabCarState) {
		this.tabCarState = tabCarState;
	}

	public String getTabCardState() {
		return tabCardState;
	}

	public void setTabCardState(String tabCardState) {
		this.tabCardState = tabCardState;
	}

	public String getTabCdPerson() {
		return tabCdPerson;
	}

	public void setTabCdPerson(String tabCdPerson) {
		this.tabCdPerson = tabCdPerson;
	}

	public String getTabChannel() {
		return tabChannel;
	}

	public void setTabChannel(String tabChannel) {
		this.tabChannel = tabChannel;
	}

	public String getTabCommercialInsurance() {
		return tabCommercialInsurance;
	}

	public void setTabCommercialInsurance(String tabCommercialInsurance) {
		this.tabCommercialInsurance = tabCommercialInsurance;
	}

	public String getTabCommissionAmount() {
		return tabCommissionAmount;
	}

	public void setTabCommissionAmount(String tabCommissionAmount) {
		this.tabCommissionAmount = tabCommissionAmount;
	}

	public String getTabCommissionOther() {
		return tabCommissionOther;
	}

	public void setTabCommissionOther(String tabCommissionOther) {
		this.tabCommissionOther = tabCommissionOther;
	}

	public String getTabCompulsoryInsurance() {
		return tabCompulsoryInsurance;
	}

	public void setTabCompulsoryInsurance(String tabCompulsoryInsurance) {
		this.tabCompulsoryInsurance = tabCompulsoryInsurance;
	}

	public String getTabCustomerMobile() {
		return tabCustomerMobile;
	}

	public void setTabCustomerMobile(String tabCustomerMobile) {
		this.tabCustomerMobile = tabCustomerMobile;
	}

	public Timestamp getTabEndDate() {
		return tabEndDate;
	}

	public void setTabEndDate(Timestamp tabEndDate) {
		this.tabEndDate = tabEndDate;
	}

	public String getTabIllustrate() {
		return tabIllustrate;
	}

	public void setTabIllustrate(String tabIllustrate) {
		this.tabIllustrate = tabIllustrate;
	}

	public String getTabInsuranceCompanyName() {
		return tabInsuranceCompanyName;
	}

	public void setTabInsuranceCompanyName(String tabInsuranceCompanyName) {
		this.tabInsuranceCompanyName = tabInsuranceCompanyName;
	}

	public String getTabIsAuditing() {
		return tabIsAuditing;
	}

	public void setTabIsAuditing(String tabIsAuditing) {
		this.tabIsAuditing = tabIsAuditing;
	}

	public String getTabIsPayAmount() {
		return tabIsPayAmount;
	}

	public void setTabIsPayAmount(String tabIsPayAmount) {
		this.tabIsPayAmount = tabIsPayAmount;
	}

	public String getTabJbfjql() {
		return tabJbfjql;
	}

	public void setTabJbfjql(String tabJbfjql) {
		this.tabJbfjql = tabJbfjql;
	}

	public String getTabJbfsyl() {
		return tabJbfsyl;
	}

	public void setTabJbfsyl(String tabJbfsyl) {
		this.tabJbfsyl = tabJbfsyl;
	}

	public String getTabJfysAmount() {
		return tabJfysAmount;
	}

	public void setTabJfysAmount(String tabJfysAmount) {
		this.tabJfysAmount = tabJfysAmount;
	}

	public String getTabManagerUser() {
		return tabManagerUser;
	}

	public void setTabManagerUser(String tabManagerUser) {
		this.tabManagerUser = tabManagerUser;
	}

	public String getTabMessage() {
		return tabMessage;
	}

	public void setTabMessage(String tabMessage) {
		this.tabMessage = tabMessage;
	}

	public Timestamp getTabQdDate() {
		return tabQdDate;
	}

	public void setTabQdDate(Timestamp tabQdDate) {
		this.tabQdDate = tabQdDate;
	}

	public String getTabRealAmount() {
		return tabRealAmount;
	}

	public void setTabRealAmount(String tabRealAmount) {
		this.tabRealAmount = tabRealAmount;
	}

	public String getTabSafePerson() {
		return tabSafePerson;
	}

	public void setTabSafePerson(String tabSafePerson) {
		this.tabSafePerson = tabSafePerson;
	}

	public Timestamp getTabStartDate() {
		return tabStartDate;
	}

	public void setTabStartDate(Timestamp tabStartDate) {
		this.tabStartDate = tabStartDate;
	}

	public String getTabTax() {
		return tabTax;
	}

	public void setTabTax(String tabTax) {
		this.tabTax = tabTax;
	}

	public String getTabTeamOwnership() {
		return tabTeamOwnership;
	}

	public void setTabTeamOwnership(String tabTeamOwnership) {
		this.tabTeamOwnership = tabTeamOwnership;
	}

	public Integer getTabUserId() {
		return tabUserId;
	}

	public void setTabUserId(Integer tabUserId) {
		this.tabUserId = tabUserId;
	}

	public String getTabWhPerson() {
		return tabWhPerson;
	}

	public void setTabWhPerson(String tabWhPerson) {
		this.tabWhPerson = tabWhPerson;
	}

	public Timestamp getTabWriteDate() {
		return tabWriteDate;
	}

	public void setTabWriteDate(Timestamp tabWriteDate) {
		this.tabWriteDate = tabWriteDate;
	}

	public String getTabXlsxName() {
		return tabXlsxName;
	}

	public void setTabXlsxName(String tabXlsxName) {
		this.tabXlsxName = tabXlsxName;
	}

	public String getTabYgzhjqAmount() {
		return tabYgzhjqAmount;
	}

	public void setTabYgzhjqAmount(String tabYgzhjqAmount) {
		this.tabYgzhjqAmount = tabYgzhjqAmount;
	}

	public String getTabYgzhsyAmount() {
		return tabYgzhsyAmount;
	}

	public void setTabYgzhsyAmount(String tabYgzhsyAmount) {
		this.tabYgzhsyAmount = tabYgzhsyAmount;
	}

	public String getTabYxState() {
		return tabYxState;
	}

	public void setTabYxState(String tabYxState) {
		this.tabYxState = tabYxState;
	}

	public String getTabZbfJ() {
		return tabZbfJ;
	}

	public void setTabZbfJ(String tabZbfJ) {
		this.tabZbfJ = tabZbfJ;
	}

	public String getRealOpenPay() {
		return realOpenPay;
	}

	public void setRealOpenPay(String realOpenPay) {
		this.realOpenPay = realOpenPay;
	}

	public String getRealOpenPayState() {
		return realOpenPayState;
	}

	public void setRealOpenPayState(String realOpenPayState) {
		this.realOpenPayState = realOpenPayState;
	}

	public List<TabInsuranceFile> getTif() {
		return tif;
	}

	public void setTif(List<TabInsuranceFile> tif) {
		this.tif = tif;
	}

	public TabPayRecord getYpt() {
		return ypt;
	}

	public void setYpt(TabPayRecord ypt) {
		this.ypt = ypt;
	}

	public int getAgainState() {
		return againState;
	}

	public void setAgainState(int againState) {
		this.againState = againState;
	}

	@Override
	public String toString() {
		return "TabScheduleOfInsuranceTrues [id=" + id + ", tabAgentName="
				+ tabAgentName + ", tabAgentPay=" + tabAgentPay
				+ ", tabAllAmount=" + tabAllAmount + ", tabAmountSate="
				+ tabAmountSate + ", tabAnnotations=" + tabAnnotations
				+ ", tabAnnotationsPerson=" + tabAnnotationsPerson
				+ ", tabBankNum=" + tabBankNum + ", tabCarNum=" + tabCarNum
				+ ", tabCarState=" + tabCarState + ", tabCardState="
				+ tabCardState + ", tabCdPerson=" + tabCdPerson
				+ ", tabChannel=" + tabChannel + ", tabCommercialInsurance="
				+ tabCommercialInsurance + ", tabCommissionAmount="
				+ tabCommissionAmount + ", tabCommissionOther="
				+ tabCommissionOther + ", tabCompulsoryInsurance="
				+ tabCompulsoryInsurance + ", tabCustomerMobile="
				+ tabCustomerMobile + ", tabEndDate=" + tabEndDate
				+ ", tabIllustrate=" + tabIllustrate
				+ ", tabInsuranceCompanyName=" + tabInsuranceCompanyName
				+ ", tabIsAuditing=" + tabIsAuditing + ", tabIsPayAmount="
				+ tabIsPayAmount + ", tabJbfjql=" + tabJbfjql + ", tabJbfsyl="
				+ tabJbfsyl + ", tabJfysAmount=" + tabJfysAmount
				+ ", tabManagerUser=" + tabManagerUser + ", tabMessage="
				+ tabMessage + ", tabQdDate=" + tabQdDate + ", tabRealAmount="
				+ tabRealAmount + ", tabSafePerson=" + tabSafePerson
				+ ", tabStartDate=" + tabStartDate + ", tabTax=" + tabTax
				+ ", tabTeamOwnership=" + tabTeamOwnership + ", tabUserId="
				+ tabUserId + ", tabWhPerson=" + tabWhPerson
				+ ", tabWriteDate=" + tabWriteDate + ", tabXlsxName="
				+ tabXlsxName + ", tabYgzhjqAmount=" + tabYgzhjqAmount
				+ ", tabYgzhsyAmount=" + tabYgzhsyAmount + ", tabYxState="
				+ tabYxState + ", tabZbfJ=" + tabZbfJ + ", realOpenPay="
				+ realOpenPay + ", realOpenPayState=" + realOpenPayState
				+ ", tif=" + tif + ", ypt=" + ypt + ", againState="
				+ againState + "]";
	}

}