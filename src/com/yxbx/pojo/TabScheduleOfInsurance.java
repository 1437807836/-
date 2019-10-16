package com.yxbx.pojo;



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



/**
 * TabScheduleOfInsurance entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tab_schedule_of_insurance", catalog = "bxqd")
public class TabScheduleOfInsurance implements java.io.Serializable {

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
	private BigDecimal tabJbfsyl;
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
	private  int  againState;//可能重复
	private  String  againStates;//可能重复
	private  String tabtype;
	@Transient
	public int getAgainState() {
		return againState;
	}

	public void setAgainState(int againState) {
		this.againState = againState;
	}

	private List<TabInsuranceFile> tif=null;//附件
	
	@Transient
	public List<TabInsuranceFile> getTif() {
		return tif;
	}

	public void setTif(List<TabInsuranceFile> tif) {
		this.tif = tif;
	}

	/** default constructor */
	public TabScheduleOfInsurance() {
	}

	/** full constructor */
	public TabScheduleOfInsurance(String tabAgentName, BigDecimal tabAllAmount,
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
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "tab_agent_name", length = 50)
	public String getTabAgentName() {
		return this.tabAgentName;
	}

	public void setTabAgentName(String tabAgentName) {
		this.tabAgentName = tabAgentName;
	}

	@Column(name = "tab_all_amount", precision = 30, scale = 2)
	public BigDecimal getTabAllAmount() {
		return this.tabAllAmount;
	}

	public void setTabAllAmount(BigDecimal tabAllAmount) {
		this.tabAllAmount = tabAllAmount;
	}

	@Column(name = "tab_amount_sate", length = 10)
	public String getTabAmountSate() {
		return this.tabAmountSate;
	}

	public void setTabAmountSate(String tabAmountSate) {
		this.tabAmountSate = tabAmountSate;
	}

	@Column(name = "tab_annotations", length = 99)
	public String getTabAnnotations() {
		return this.tabAnnotations;
	}

	public void setTabAnnotations(String tabAnnotations) {
		this.tabAnnotations = tabAnnotations;
	}

	@Column(name = "tab_bank_num", length = 50)
	public String getTabBankNum() {
		return this.tabBankNum;
	}

	public void setTabBankNum(String tabBankNum) {
		this.tabBankNum = tabBankNum;
	}

	@Column(name = "tab_car_num")
	public String getTabCarNum() {
		return this.tabCarNum;
	}

	public void setTabCarNum(String tabCarNum) {
		this.tabCarNum = tabCarNum;
	}

	@Column(name = "tab_car_state", length = 50)
	public String getTabCarState() {
		return this.tabCarState;
	}

	public void setTabCarState(String tabCarState) {
		this.tabCarState = tabCarState;
	}

	@Column(name = "tab_card_state", length = 50)
	public String getTabCardState() {
		return this.tabCardState;
	}

	public void setTabCardState(String tabCardState) {
		this.tabCardState = tabCardState;
	}

	@Column(name = "tab_cd_person", length = 30)
	public String getTabCdPerson() {
		return this.tabCdPerson;
	}

	public void setTabCdPerson(String tabCdPerson) {
		this.tabCdPerson = tabCdPerson;
	}

	@Column(name = "tab_channel", length = 50)
	public String getTabChannel() {
		return this.tabChannel;
	}

	public void setTabChannel(String tabChannel) {
		this.tabChannel = tabChannel;
	}

	@Column(name = "tab_commercial_insurance", precision = 50, scale = 2)
	public BigDecimal getTabCommercialInsurance() {
		return this.tabCommercialInsurance;
	}

	public void setTabCommercialInsurance(BigDecimal tabCommercialInsurance) {
		this.tabCommercialInsurance = tabCommercialInsurance;
	}

	@Column(name = "tab_commission_amount", precision = 30, scale = 2)
	public BigDecimal getTabCommissionAmount() {
		return this.tabCommissionAmount;
	}

	public void setTabCommissionAmount(BigDecimal tabCommissionAmount) {
		this.tabCommissionAmount = tabCommissionAmount;
	}

	@Column(name = "tab_commission_other", length = 30)
	public String getTabCommissionOther() {
		return this.tabCommissionOther;
	}

	public void setTabCommissionOther(String tabCommissionOther) {
		this.tabCommissionOther = tabCommissionOther;
	}

	@Column(name = "tab_compulsory_insurance", precision = 50, scale = 2)
	public BigDecimal getTabCompulsoryInsurance() {
		return this.tabCompulsoryInsurance;
	}

	public void setTabCompulsoryInsurance(BigDecimal tabCompulsoryInsurance) {
		this.tabCompulsoryInsurance = tabCompulsoryInsurance;
	}

	@Column(name = "tab_customer_mobile", length = 20)
	public String getTabCustomerMobile() {
		return this.tabCustomerMobile;
	}

	public void setTabCustomerMobile(String tabCustomerMobile) {
		this.tabCustomerMobile = tabCustomerMobile;
	}

	@Column(name = "tab_end_date", length = 19)
	public Timestamp getTabEndDate() {
		return this.tabEndDate;
	}

	public void setTabEndDate(Timestamp tabEndDate) {
		this.tabEndDate = tabEndDate;
	}

	@Column(name = "tab_illustrate", length = 99)
	public String getTabIllustrate() {
		return this.tabIllustrate;
	}

	public void setTabIllustrate(String tabIllustrate) {
		this.tabIllustrate = tabIllustrate;
	}

	@Column(name = "tab_insurance_company_name", length = 50)
	public String getTabInsuranceCompanyName() {
		return this.tabInsuranceCompanyName;
	}

	public void setTabInsuranceCompanyName(String tabInsuranceCompanyName) {
		this.tabInsuranceCompanyName = tabInsuranceCompanyName;
	}

	@Column(name = "tab_jbfjql", precision = 30, scale = 2)
	public BigDecimal getTabJbfjql() {
		return this.tabJbfjql;
	}

	public void setTabJbfjql(BigDecimal tabJbfjql) {
		this.tabJbfjql = tabJbfjql;
	}
	@Column(name = "tabJbfsyl", precision = 30, scale = 2)
	public BigDecimal getTabJbfsyl() {
		return this.tabJbfsyl;
	}

	public void setTabJbfsyl(BigDecimal tabJbfsyl) {
		this.tabJbfsyl = tabJbfsyl;
	}
	
	@Column(name = "tab_jfys_amount", precision = 65, scale = 2)
	public BigDecimal getTabJfysAmount() {
		return this.tabJfysAmount;
	}

	public void setTabJfysAmount(BigDecimal tabJfysAmount) {
		this.tabJfysAmount = tabJfysAmount;
	}

	@Column(name = "tab_message", length = 60)
	public String getTabMessage() {
		return this.tabMessage;
	}

	public void setTabMessage(String tabMessage) {
		this.tabMessage = tabMessage;
	}

	@Column(name = "tab_qd_date", length = 19)
	public Timestamp getTabQdDate() {
		return this.tabQdDate;
	}

	public void setTabQdDate(Timestamp tabQdDate) {
		this.tabQdDate = tabQdDate;
	}

	@Column(name = "tab_real_amount", precision = 30, scale = 2)
	public BigDecimal getTabRealAmount() {
		return this.tabRealAmount;
	}

	public void setTabRealAmount(BigDecimal tabRealAmount) {
		this.tabRealAmount = tabRealAmount;
	}

	@Column(name = "tab_safe_person", length = 99)
	public String getTabSafePerson() {
		return this.tabSafePerson;
	}

	public void setTabSafePerson(String tabSafePerson) {
		this.tabSafePerson = tabSafePerson;
	}

	@Column(name = "tab_start_date", length = 19)
	public Timestamp getTabStartDate() {
		return this.tabStartDate;
	}

	public void setTabStartDate(Timestamp tabStartDate) {
		this.tabStartDate = tabStartDate;
	}

	@Column(name = "tab_tax", precision = 30, scale = 2)
	public BigDecimal getTabTax() {
		return this.tabTax;
	}

	public void setTabTax(BigDecimal tabTax) {
		this.tabTax = tabTax;
	}

	@Column(name = "tab_team_ownership", length = 50)
	public String getTabTeamOwnership() {
		return this.tabTeamOwnership;
	}

	public void setTabTeamOwnership(String tabTeamOwnership) {
		this.tabTeamOwnership = tabTeamOwnership;
	}

	@Column(name = "tab_wh_person", length = 30)
	public String getTabWhPerson() {
		return this.tabWhPerson;
	}

	public void setTabWhPerson(String tabWhPerson) {
		this.tabWhPerson = tabWhPerson;
	}

	@Column(name = "tab_write_date", length = 19)
	public Timestamp getTabWriteDate() {
		return this.tabWriteDate;
	}

	public void setTabWriteDate(Timestamp tabWriteDate) {
		this.tabWriteDate = tabWriteDate;
	}

	@Column(name = "tab_ygzhjq_amount", precision = 30, scale = 2)
	public BigDecimal getTabYgzhjqAmount() {
		return this.tabYgzhjqAmount;
	}

	public void setTabYgzhjqAmount(BigDecimal tabYgzhjqAmount) {
		this.tabYgzhjqAmount = tabYgzhjqAmount;
	}

	@Column(name = "tab_ygzhsy_amount", precision = 30, scale = 2)
	public BigDecimal getTabYgzhsyAmount() {
		return this.tabYgzhsyAmount;
	}

	public void setTabYgzhsyAmount(BigDecimal tabYgzhsyAmount) {
		this.tabYgzhsyAmount = tabYgzhsyAmount;
	}

	@Column(name = "tab_yx_state", length = 99)
	public String getTabYxState() {
		return this.tabYxState;
	}

	public void setTabYxState(String tabYxState) {
		this.tabYxState = tabYxState;
	}

	@Column(name = "tab_zbf_j", length = 65)
	public String getTabZbfJ() {
		return this.tabZbfJ;
	}

	public void setTabZbfJ(String tabZbfJ) {
		this.tabZbfJ = tabZbfJ;
	}

	@Column(name = "tab_manager_user", length = 50)
	public String getTabManagerUser() {
		return this.tabManagerUser;
	}

	public void setTabManagerUser(String tabManagerUser) {
		this.tabManagerUser = tabManagerUser;
	}

	@Column(name = "tab_user_id")
	public Integer getTabUserId() {
		return this.tabUserId;
	}

	public void setTabUserId(Integer tabUserId) {
		this.tabUserId = tabUserId;
	}

	@Column(name = "tab_xlsx_name")
	public String getTabXlsxName() {
		return this.tabXlsxName;
	}

	public void setTabXlsxName(String tabXlsxName) {
		this.tabXlsxName = tabXlsxName;
	}

	@Column(name = "tab_is_auditing", length = 10)
	public String getTabIsAuditing() {
		return this.tabIsAuditing;
	}

	public void setTabIsAuditing(String tabIsAuditing) {
		this.tabIsAuditing = tabIsAuditing;
	}

	@Column(name = "tab_agent_pay", length = 30)
	public String getTabAgentPay() {
		return this.tabAgentPay;
	}

	public void setTabAgentPay(String tabAgentPay) {
		this.tabAgentPay = tabAgentPay;
	}

	@Column(name = "tab_annotations_person")
	public String getTabAnnotationsPerson() {
		return this.tabAnnotationsPerson;
	}

	public void setTabAnnotationsPerson(String tabAnnotationsPerson) {
		this.tabAnnotationsPerson = tabAnnotationsPerson;
	}

	public String getAgainStates() {
		return againStates;
	}

	public void setAgainStates(String againStates) {
		this.againStates = againStates;
	}

	public String getTabtype() {
		return tabtype;
	}

	public void setTabtype(String tabtype) {
		this.tabtype = tabtype;
	}

}