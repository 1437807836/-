package com.yxbx.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TabInsurerPolicy entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tab_insurer_policy", catalog = "bxqd")
public class TabInsurerPolicy implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer tabInsurerId;
	private String tabChannelName;
	private String tabNature;
	private Timestamp tabStartTime;
	private Timestamp tabEndTime;
	private String tabState;
	private String tabIsSuccessive;
	private String tabQuality;
	private BigDecimal tabIncome;
	private BigDecimal tabExpenditure;
	private BigDecimal tabInvoice;
	private Timestamp tabSubmitTime;
	private String tabAgent;
	private String tabExamineAndVerify;
	private Timestamp tabExamineTime;
	private String tabExaminePerson;
	private Integer tabAgentId;
	private String tabWritePerson;
	private String tabIsDelete;
	private String tabInsurerName;
	private Integer tabBxStateId;
	private Timestamp tabWriteTime;
	private String tabRemark;

	//-----------

	private Timestamp tabSubmitTimeStart;
	public Timestamp getTabSubmitTimeStart() {
		return tabSubmitTimeStart;
	}

	public void setTabSubmitTimeStart(Timestamp tabSubmitTimeStart) {
		this.tabSubmitTimeStart = tabSubmitTimeStart;
	}

	public Timestamp getTabSubmitTimeEnd() {
		return tabSubmitTimeEnd;
	}

	public void setTabSubmitTimeEnd(Timestamp tabSubmitTimeEnd) {
		this.tabSubmitTimeEnd = tabSubmitTimeEnd;
	}

	public Timestamp getTabStartTimeStart() {
		return tabStartTimeStart;
	}

	public void setTabStartTimeStart(Timestamp tabStartTimeStart) {
		this.tabStartTimeStart = tabStartTimeStart;
	}

	public Timestamp getTabStartTimeEnd() {
		return tabStartTimeEnd;
	}

	public void setTabStartTimeEnd(Timestamp tabStartTimeEnd) {
		this.tabStartTimeEnd = tabStartTimeEnd;
	}

	public Timestamp getTabEndTimeStart() {
		return tabEndTimeStart;
	}

	public void setTabEndTimeStart(Timestamp tabEndTimeStart) {
		this.tabEndTimeStart = tabEndTimeStart;
	}

	public Timestamp getTabEndTimeEnd() {
		return tabEndTimeEnd;
	}

	public void setTabEndTimeEnd(Timestamp tabEndTimeEnd) {
		this.tabEndTimeEnd = tabEndTimeEnd;
	}

	public Timestamp getTabExamineTimeStart() {
		return tabExamineTimeStart;
	}

	public void setTabExamineTimeStart(Timestamp tabExamineTimeStart) {
		this.tabExamineTimeStart = tabExamineTimeStart;
	}

	public Timestamp getTabExamineTimeEnd() {
		return tabExamineTimeEnd;
	}

	public void setTabExamineTimeEnd(Timestamp tabExamineTimeEnd) {
		this.tabExamineTimeEnd = tabExamineTimeEnd;
	}

	private Timestamp tabSubmitTimeEnd;
	private Timestamp tabStartTimeStart;
	private Timestamp tabStartTimeEnd;
	private Timestamp tabEndTimeStart;
	private Timestamp tabEndTimeEnd;
	private Timestamp tabExamineTimeStart;
	private Timestamp tabExamineTimeEnd;
//	{"tabChannelName":"传统","tabNature":"非运营客车",
//		"tabStartTimeStart":"2018-12-24 07:56:30",
//		"tabStartTimeEnd":"2018-12-25 07:56:30",
//		"tabSubmitTimeStart":"2018-12-24 07:56:30",
//		"tabSubmitTimeEnd":"2018-12-24 08:56:30",
//		"tabExamineTimeStart":"2018-12-24 06:56:30",
//		"tabExamineTimeEnd":"2018-12-24 23:56:30",
//		"tabExamineAndVerify":"否",
//		"tabInsurerName":"君逸科技2"} 
	// Constructors

	/** default constructor */
	public TabInsurerPolicy() {
	}

	/** full constructor */
	public TabInsurerPolicy(Integer tabInsurerId, String tabChannelName,
			String tabNature, Timestamp tabStartTime, Timestamp tabEndTime,
			String tabState, String tabIsSuccessive, String tabQuality,
			BigDecimal tabIncome, BigDecimal tabExpenditure,
			BigDecimal tabInvoice, Timestamp tabSubmitTime, String tabAgent,
			String tabExamineAndVerify, Timestamp tabExamineTime,
			String tabExaminePerson, Integer tabAgentId, String tabWritePerson,
			String tabIsDelete, String tabInsurerName, Integer tabBxStateId,
			Timestamp tabWriteTime) {
		this.tabInsurerId = tabInsurerId;
		this.tabChannelName = tabChannelName;
		this.tabNature = tabNature;
		this.tabStartTime = tabStartTime;
		this.tabEndTime = tabEndTime;
		this.tabState = tabState;
		this.tabIsSuccessive = tabIsSuccessive;
		this.tabQuality = tabQuality;
		this.tabIncome = tabIncome;
		this.tabExpenditure = tabExpenditure;
		this.tabInvoice = tabInvoice;
		this.tabSubmitTime = tabSubmitTime;
		this.tabAgent = tabAgent;
		this.tabExamineAndVerify = tabExamineAndVerify;
		this.tabExamineTime = tabExamineTime;
		this.tabExaminePerson = tabExaminePerson;
		this.tabAgentId = tabAgentId;
		this.tabWritePerson = tabWritePerson;
		this.tabIsDelete = tabIsDelete;
		this.tabInsurerName = tabInsurerName;
		this.tabBxStateId = tabBxStateId;
		this.tabWriteTime = tabWriteTime;
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

	@Column(name = "tab_insurer_id")
	public Integer getTabInsurerId() {
		return this.tabInsurerId;
	}

	public void setTabInsurerId(Integer tabInsurerId) {
		this.tabInsurerId = tabInsurerId;
	}

	@Column(name = "tab_channel_name", length = 100)
	public String getTabChannelName() {
		return this.tabChannelName;
	}

	public void setTabChannelName(String tabChannelName) {
		this.tabChannelName = tabChannelName;
	}

	@Column(name = "tab_nature")
	public String getTabNature() {
		return this.tabNature;
	}

	public void setTabNature(String tabNature) {
		this.tabNature = tabNature;
	}

	@Column(name = "tab_start_time", length = 19)
	public Timestamp getTabStartTime() {
		return this.tabStartTime;
	}

	public void setTabStartTime(Timestamp tabStartTime) {
		this.tabStartTime = tabStartTime;
	}

	@Column(name = "tab_end_time", length = 19)
	public Timestamp getTabEndTime() {
		return this.tabEndTime;
	}

	public void setTabEndTime(Timestamp tabEndTime) {
		this.tabEndTime = tabEndTime;
	}

	@Column(name = "tab_state", length = 20)
	public String getTabState() {
		return this.tabState;
	}

	public void setTabState(String tabState) {
		this.tabState = tabState;
	}

	@Column(name = "tab_is_successive", length = 20)
	public String getTabIsSuccessive() {
		return this.tabIsSuccessive;
	}

	public void setTabIsSuccessive(String tabIsSuccessive) {
		this.tabIsSuccessive = tabIsSuccessive;
	}

	@Column(name = "tab_quality", length = 30)
	public String getTabQuality() {
		return this.tabQuality;
	}

	public void setTabQuality(String tabQuality) {
		this.tabQuality = tabQuality;
	}

	@Column(name = "tab_income", precision = 50, scale = 2)
	public BigDecimal getTabIncome() {
		return this.tabIncome;
	}

	public void setTabIncome(BigDecimal tabIncome) {
		this.tabIncome = tabIncome;
	}

	@Column(name = "tab_expenditure", precision = 50, scale = 2)
	public BigDecimal getTabExpenditure() {
		return this.tabExpenditure;
	}

	public void setTabExpenditure(BigDecimal tabExpenditure) {
		this.tabExpenditure = tabExpenditure;
	}

	@Column(name = "tab_invoice", precision = 50, scale = 2)
	public BigDecimal getTabInvoice() {
		return this.tabInvoice;
	}

	public void setTabInvoice(BigDecimal tabInvoice) {
		this.tabInvoice = tabInvoice;
	}

	@Column(name = "tab_submit_time", length = 19)
	public Timestamp getTabSubmitTime() {
		return this.tabSubmitTime;
	}

	public void setTabSubmitTime(Timestamp tabSubmitTime) {
		this.tabSubmitTime = tabSubmitTime;
	}

	@Column(name = "tab_agent")
	public String getTabAgent() {
		return this.tabAgent;
	}

	public void setTabAgent(String tabAgent) {
		this.tabAgent = tabAgent;
	}

	@Column(name = "tab_examine_and_verify", length = 10)
	public String getTabExamineAndVerify() {
		return this.tabExamineAndVerify;
	}

	public void setTabExamineAndVerify(String tabExamineAndVerify) {
		this.tabExamineAndVerify = tabExamineAndVerify;
	}

	@Column(name = "tab_examine_time", length = 19)
	public Timestamp getTabExamineTime() {
		return this.tabExamineTime;
	}

	public void setTabExamineTime(Timestamp tabExamineTime) {
		this.tabExamineTime = tabExamineTime;
	}

	@Column(name = "tab_examine_person")
	public String getTabExaminePerson() {
		return this.tabExaminePerson;
	}

	public void setTabExaminePerson(String tabExaminePerson) {
		this.tabExaminePerson = tabExaminePerson;
	}

	@Column(name = "tab_agent_id")
	public Integer getTabAgentId() {
		return this.tabAgentId;
	}

	public void setTabAgentId(Integer tabAgentId) {
		this.tabAgentId = tabAgentId;
	}

	@Column(name = "tab_write_person")
	public String getTabWritePerson() {
		return this.tabWritePerson;
	}

	public void setTabWritePerson(String tabWritePerson) {
		this.tabWritePerson = tabWritePerson;
	}

	@Column(name = "tab_is_delete")
	public String getTabIsDelete() {
		return this.tabIsDelete;
	}

	public void setTabIsDelete(String tabIsDelete) {
		this.tabIsDelete = tabIsDelete;
	}

	@Column(name = "tab_insurer_name", length = 30)
	public String getTabInsurerName() {
		return this.tabInsurerName;
	}

	public void setTabInsurerName(String tabInsurerName) {
		this.tabInsurerName = tabInsurerName;
	}

	@Column(name = "tab_bx_state_id")
	public Integer getTabBxStateId() {
		return this.tabBxStateId;
	}

	public void setTabBxStateId(Integer tabBxStateId) {
		this.tabBxStateId = tabBxStateId;
	}

	@Column(name = "tab_write_time", length = 19)
	public Timestamp getTabWriteTime() {
		return this.tabWriteTime;
	}

	public void setTabWriteTime(Timestamp tabWriteTime) {
		this.tabWriteTime = tabWriteTime;
	}

	@Column(name = "tab_remark")
	public String getTabRemark() {
		return tabRemark;
	}

	public void setTabRemark(String tabRemark) {
		this.tabRemark = tabRemark;
	}
}