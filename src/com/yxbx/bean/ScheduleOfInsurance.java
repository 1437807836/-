package com.yxbx.bean;



import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yxbx.bean.InsuranceFile;

/**
 * TabScheduleOfInsurance entity. @author MyEclipse Persistence Tools
 */

public class ScheduleOfInsurance implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Integer id;
	private String tabAgentName;
	private BigDecimal tabAllAmount;
	private String tabAmountSate;
	private String tabAnnotations;
	private String tabBankNum;
	private String tabCarNum;
	private String tabCarState;
	private String tabCardState;
	private String tabCdPerson;
	private String tabChannel;
	private BigDecimal tabCommercialInsurance;
	private BigDecimal tabCommissionAmount;
	private String tabCommissionOther;
	private BigDecimal tabCompulsoryInsurance;
	private String tabCustomerMobile;
	private Timestamp tabEndDate;
	private String tabIllustrate;
	private String tabInsuranceCompanyName;
	private BigDecimal tabJbfjql;
	private BigDecimal tabJfysAmount;
	private String tabMessage;
	private Timestamp tabQdDate;
	private BigDecimal tabRealAmount;
	private String tabSafePerson;
	private Timestamp tabStartDate;
	private BigDecimal tabTax;
	private String tabTeamOwnership;
	private String tabWhPerson;
	private Timestamp tabWriteDate;
	private BigDecimal tabYgzhjqAmount;
	private BigDecimal tabYgzhsyAmount;
	private String tabYxState;
	private String tabZbfJ;
	private String tabManagerUser;
	private Integer tabUserId;
	private String tabXlsxName;
	private String tabIsAuditing;
	private String tabAgentPay;
	private String tabAnnotationsPerson;
	
	private List<InsuranceFile> tif=null;//附件
	
	
	public List<InsuranceFile> getTif() {
		return tif;
	}

	public void setTif(List<InsuranceFile> tif) {
		this.tif = tif;
	}

	/** default constructor */
	public ScheduleOfInsurance() {
	}

	/** full constructor */
	public ScheduleOfInsurance(String tabAgentName, BigDecimal tabAllAmount,
			String tabAmountSate, String tabAnnotations, String tabBankNum,
			String tabCarNum, String tabCarState, String tabCardState,
			String tabCdPerson, String tabChannel,
			BigDecimal tabCommercialInsurance, BigDecimal tabCommissionAmount,
			String tabCommissionOther, BigDecimal tabCompulsoryInsurance,
			String tabCustomerMobile, Timestamp tabEndDate,
			String tabIllustrate, String tabInsuranceCompanyName,
			BigDecimal tabJbfjql, BigDecimal tabJfysAmount, String tabMessage,
			Timestamp tabQdDate, BigDecimal tabRealAmount,
			String tabSafePerson, Timestamp tabStartDate, BigDecimal tabTax,
			String tabTeamOwnership, String tabWhPerson,
			Timestamp tabWriteDate, BigDecimal tabYgzhjqAmount,
			BigDecimal tabYgzhsyAmount, String tabYxState, String tabZbfJ,
			String tabManagerUser, Integer tabUserId, String tabXlsxName,
			String tabIsAuditing, String tabAgentPay,
			String tabAnnotationsPerson) {
		this.tabAgentName = tabAgentName;
		this.tabAllAmount = tabAllAmount;
		this.tabAmountSate = tabAmountSate;
		this.tabAnnotations = tabAnnotations;
		this.tabBankNum = tabBankNum;
		this.tabCarNum = tabCarNum;
		this.tabCarState = tabCarState;
		this.tabCardState = tabCardState;
		this.tabCdPerson = tabCdPerson;
		this.tabChannel = tabChannel;
		this.tabCommercialInsurance = tabCommercialInsurance;
		this.tabCommissionAmount = tabCommissionAmount;
		this.tabCommissionOther = tabCommissionOther;
		this.tabCompulsoryInsurance = tabCompulsoryInsurance;
		this.tabCustomerMobile = tabCustomerMobile;
		this.tabEndDate = tabEndDate;
		this.tabIllustrate = tabIllustrate;
		this.tabInsuranceCompanyName = tabInsuranceCompanyName;
		this.tabJbfjql = tabJbfjql;
		this.tabJfysAmount = tabJfysAmount;
		this.tabMessage = tabMessage;
		this.tabQdDate = tabQdDate;
		this.tabRealAmount = tabRealAmount;
		this.tabSafePerson = tabSafePerson;
		this.tabStartDate = tabStartDate;
		this.tabTax = tabTax;
		this.tabTeamOwnership = tabTeamOwnership;
		this.tabWhPerson = tabWhPerson;
		this.tabWriteDate = tabWriteDate;
		this.tabYgzhjqAmount = tabYgzhjqAmount;
		this.tabYgzhsyAmount = tabYgzhsyAmount;
		this.tabYxState = tabYxState;
		this.tabZbfJ = tabZbfJ;
		this.tabManagerUser = tabManagerUser;
		this.tabUserId = tabUserId;
		this.tabXlsxName = tabXlsxName;
		this.tabIsAuditing = tabIsAuditing;
		this.tabAgentPay = tabAgentPay;
		this.tabAnnotationsPerson = tabAnnotationsPerson;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getTabAgentName() {
		return this.tabAgentName;
	}

	public void setTabAgentName(String tabAgentName) {
		this.tabAgentName = tabAgentName;
	}

	public BigDecimal getTabAllAmount() {
		return this.tabAllAmount;
	}

	public void setTabAllAmount(BigDecimal tabAllAmount) {
		this.tabAllAmount = tabAllAmount;
	}

	
	public String getTabAmountSate() {
		return this.tabAmountSate;
	}

	public void setTabAmountSate(String tabAmountSate) {
		this.tabAmountSate = tabAmountSate;
	}

	
	public String getTabAnnotations() {
		return this.tabAnnotations;
	}

	public void setTabAnnotations(String tabAnnotations) {
		this.tabAnnotations = tabAnnotations;
	}

	
	public String getTabBankNum() {
		return this.tabBankNum;
	}

	public void setTabBankNum(String tabBankNum) {
		this.tabBankNum = tabBankNum;
	}

	
	public String getTabCarNum() {
		return this.tabCarNum;
	}

	public void setTabCarNum(String tabCarNum) {
		this.tabCarNum = tabCarNum;
	}

	
	public String getTabCarState() {
		return this.tabCarState;
	}

	public void setTabCarState(String tabCarState) {
		this.tabCarState = tabCarState;
	}

	
	public String getTabCardState() {
		return this.tabCardState;
	}

	public void setTabCardState(String tabCardState) {
		this.tabCardState = tabCardState;
	}

	
	public String getTabCdPerson() {
		return this.tabCdPerson;
	}

	public void setTabCdPerson(String tabCdPerson) {
		this.tabCdPerson = tabCdPerson;
	}

	
	public String getTabChannel() {
		return this.tabChannel;
	}

	public void setTabChannel(String tabChannel) {
		this.tabChannel = tabChannel;
	}

	
	public BigDecimal getTabCommercialInsurance() {
		return this.tabCommercialInsurance;
	}

	public void setTabCommercialInsurance(BigDecimal tabCommercialInsurance) {
		this.tabCommercialInsurance = tabCommercialInsurance;
	}

	
	public BigDecimal getTabCommissionAmount() {
		return this.tabCommissionAmount;
	}

	public void setTabCommissionAmount(BigDecimal tabCommissionAmount) {
		this.tabCommissionAmount = tabCommissionAmount;
	}

	
	public String getTabCommissionOther() {
		return this.tabCommissionOther;
	}

	public void setTabCommissionOther(String tabCommissionOther) {
		this.tabCommissionOther = tabCommissionOther;
	}

	
	public BigDecimal getTabCompulsoryInsurance() {
		return this.tabCompulsoryInsurance;
	}

	public void setTabCompulsoryInsurance(BigDecimal tabCompulsoryInsurance) {
		this.tabCompulsoryInsurance = tabCompulsoryInsurance;
	}

	
	public String getTabCustomerMobile() {
		return this.tabCustomerMobile;
	}

	public void setTabCustomerMobile(String tabCustomerMobile) {
		this.tabCustomerMobile = tabCustomerMobile;
	}

	
	public Timestamp getTabEndDate() {
		return this.tabEndDate;
	}

	public void setTabEndDate(Timestamp tabEndDate) {
		this.tabEndDate = tabEndDate;
	}

	
	public String getTabIllustrate() {
		return this.tabIllustrate;
	}

	public void setTabIllustrate(String tabIllustrate) {
		this.tabIllustrate = tabIllustrate;
	}

	
	public String getTabInsuranceCompanyName() {
		return this.tabInsuranceCompanyName;
	}

	public void setTabInsuranceCompanyName(String tabInsuranceCompanyName) {
		this.tabInsuranceCompanyName = tabInsuranceCompanyName;
	}

	
	public BigDecimal getTabJbfjql() {
		return this.tabJbfjql;
	}

	public void setTabJbfjql(BigDecimal tabJbfjql) {
		this.tabJbfjql = tabJbfjql;
	}

	
	public BigDecimal getTabJfysAmount() {
		return this.tabJfysAmount;
	}

	public void setTabJfysAmount(BigDecimal tabJfysAmount) {
		this.tabJfysAmount = tabJfysAmount;
	}

	
	public String getTabMessage() {
		return this.tabMessage;
	}

	public void setTabMessage(String tabMessage) {
		this.tabMessage = tabMessage;
	}

	
	public Timestamp getTabQdDate() {
		return this.tabQdDate;
	}

	public void setTabQdDate(Timestamp tabQdDate) {
		this.tabQdDate = tabQdDate;
	}

	
	public BigDecimal getTabRealAmount() {
		return this.tabRealAmount;
	}

	public void setTabRealAmount(BigDecimal tabRealAmount) {
		this.tabRealAmount = tabRealAmount;
	}

	
	public String getTabSafePerson() {
		return this.tabSafePerson;
	}

	public void setTabSafePerson(String tabSafePerson) {
		this.tabSafePerson = tabSafePerson;
	}


	public Timestamp getTabStartDate() {
		return this.tabStartDate;
	}

	public void setTabStartDate(Timestamp tabStartDate) {
		this.tabStartDate = tabStartDate;
	}

	
	public BigDecimal getTabTax() {
		return this.tabTax;
	}

	public void setTabTax(BigDecimal tabTax) {
		this.tabTax = tabTax;
	}

	
	public String getTabTeamOwnership() {
		return this.tabTeamOwnership;
	}

	public void setTabTeamOwnership(String tabTeamOwnership) {
		this.tabTeamOwnership = tabTeamOwnership;
	}

	
	public String getTabWhPerson() {
		return this.tabWhPerson;
	}

	public void setTabWhPerson(String tabWhPerson) {
		this.tabWhPerson = tabWhPerson;
	}

	
	public Timestamp getTabWriteDate() {
		return this.tabWriteDate;
	}

	public void setTabWriteDate(Timestamp tabWriteDate) {
		this.tabWriteDate = tabWriteDate;
	}

	
	public BigDecimal getTabYgzhjqAmount() {
		return this.tabYgzhjqAmount;
	}

	public void setTabYgzhjqAmount(BigDecimal tabYgzhjqAmount) {
		this.tabYgzhjqAmount = tabYgzhjqAmount;
	}

	
	public BigDecimal getTabYgzhsyAmount() {
		return this.tabYgzhsyAmount;
	}

	public void setTabYgzhsyAmount(BigDecimal tabYgzhsyAmount) {
		this.tabYgzhsyAmount = tabYgzhsyAmount;
	}

	
	public String getTabYxState() {
		return this.tabYxState;
	}

	public void setTabYxState(String tabYxState) {
		this.tabYxState = tabYxState;
	}

	
	public String getTabZbfJ() {
		return this.tabZbfJ;
	}

	public void setTabZbfJ(String tabZbfJ) {
		this.tabZbfJ = tabZbfJ;
	}

	
	public String getTabManagerUser() {
		return this.tabManagerUser;
	}

	public void setTabManagerUser(String tabManagerUser) {
		this.tabManagerUser = tabManagerUser;
	}

	
	public Integer getTabUserId() {
		return this.tabUserId;
	}

	public void setTabUserId(Integer tabUserId) {
		this.tabUserId = tabUserId;
	}

	
	public String getTabXlsxName() {
		return this.tabXlsxName;
	}

	public void setTabXlsxName(String tabXlsxName) {
		this.tabXlsxName = tabXlsxName;
	}

	
	public String getTabIsAuditing() {
		return this.tabIsAuditing;
	}

	public void setTabIsAuditing(String tabIsAuditing) {
		this.tabIsAuditing = tabIsAuditing;
	}

	
	public String getTabAgentPay() {
		return this.tabAgentPay;
	}

	public void setTabAgentPay(String tabAgentPay) {
		this.tabAgentPay = tabAgentPay;
	}

	
	public String getTabAnnotationsPerson() {
		return this.tabAnnotationsPerson;
	}

	public void setTabAnnotationsPerson(String tabAnnotationsPerson) {
		this.tabAnnotationsPerson = tabAnnotationsPerson;
	}

}