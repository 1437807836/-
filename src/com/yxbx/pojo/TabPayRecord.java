package com.yxbx.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * TabPayRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tab_pay_record", catalog = "bxqd")
public class TabPayRecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private String tabBankNum;//收款卡号
	private Integer tabInsuranceTrueId;
	private String tabPayFileCode;
	private String tabPayNum;//付款或收款卡号
	private String tabPayPerson;//收款或付款人
	private Integer tabPayPersonId;//收款或付款人Id
	private String tabPayee;//实际收款人
	private Integer tabUserId;//操作者ID
	private String tabVersionPay;//批次号
	private Timestamp tabWriteDate;//写入时间
	private Timestamp tabPayDates;//付款时间
	private  String  payState;//收款还是付款
	@Transient
	public BigDecimal getPcRealAmount() {
		return pcRealAmount;
	}

	public void setPcRealAmount(BigDecimal pcRealAmount) {
		this.pcRealAmount = pcRealAmount;
	}
	@Transient
	public String getWhPerson() {
		return whPerson;
	}

	public void setWhPerson(String whPerson) {
		this.whPerson = whPerson;
	}

	private BigDecimal pcRealAmount;//批次金额 付
	@Transient
	public BigDecimal getRealOpenPay() {
		return realOpenPay;
	}

	public void setRealOpenPay(BigDecimal realOpenPay) {
		this.realOpenPay = realOpenPay;
	}

	private BigDecimal realOpenPay;//批次金额  收
	private String whPerson;//操作人； 
	@Transient
	 public TabScheduleOfInsuranceTrue getList() {
		return list;
	}

	public void setList(TabScheduleOfInsuranceTrue list) {
		this.list = list;
	}

	TabScheduleOfInsuranceTrue list;

	// Constructors

	

	/** default constructor */
	public TabPayRecord() {
	}

	/** full constructor */
	public TabPayRecord(String tabBankNum, Integer tabInsuranceTrueId,
			String tabPayFileCode, String tabPayNum, String tabPayPerson,
			Integer tabPayPersonId, String tabPayee, Integer tabUserId,
			String tabVersionPay, Timestamp tabWriteDate) {
		this.tabBankNum = tabBankNum;
		this.tabInsuranceTrueId = tabInsuranceTrueId;
		this.tabPayFileCode = tabPayFileCode;
		this.tabPayNum = tabPayNum;
		this.tabPayPerson = tabPayPerson;
		this.tabPayPersonId = tabPayPersonId;
		this.tabPayee = tabPayee;
		this.tabUserId = tabUserId;
		this.tabVersionPay = tabVersionPay;
		this.tabWriteDate = tabWriteDate;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "tab_bank_num", length = 30)
	public String getTabBankNum() {
		return this.tabBankNum;
	}

	public void setTabBankNum(String tabBankNum) {
		this.tabBankNum = tabBankNum;
	}

	@Column(name = "tab_insurance_true_id")
	public Integer getTabInsuranceTrueId() {
		return this.tabInsuranceTrueId;
	}

	public void setTabInsuranceTrueId(Integer tabInsuranceTrueId) {
		this.tabInsuranceTrueId = tabInsuranceTrueId;
	}

	@Column(name = "tab_pay_file_code")
	public String getTabPayFileCode() {
		return this.tabPayFileCode;
	}

	public void setTabPayFileCode(String tabPayFileCode) {
		this.tabPayFileCode = tabPayFileCode;
	}

	@Column(name = "tab_pay_num", length = 30)
	public String getTabPayNum() {
		return this.tabPayNum;
	}

	public void setTabPayNum(String tabPayNum) {
		this.tabPayNum = tabPayNum;
	}

	@Column(name = "tab_pay_person")
	public String getTabPayPerson() {
		return this.tabPayPerson;
	}

	public void setTabPayPerson(String tabPayPerson) {
		this.tabPayPerson = tabPayPerson;
	}

	@Column(name = "tab_pay_person_id")
	public Integer getTabPayPersonId() {
		return this.tabPayPersonId;
	}

	public void setTabPayPersonId(Integer tabPayPersonId) {
		this.tabPayPersonId = tabPayPersonId;
	}

	@Column(name = "tab_payee")
	public String getTabPayee() {
		return this.tabPayee;
	}

	public void setTabPayee(String tabPayee) {
		this.tabPayee = tabPayee;
	}

	@Column(name = "tab_user_id")
	public Integer getTabUserId() {
		return this.tabUserId;
	}

	public void setTabUserId(Integer tabUserId) {
		this.tabUserId = tabUserId;
	}

	@Column(name = "tab_version_pay", length = 200)
	public String getTabVersionPay() {
		return this.tabVersionPay;
	}

	public void setTabVersionPay(String tabVersionPay) {
		this.tabVersionPay = tabVersionPay;
	}

	@Column(name = "tab_write_date", length = 19)
	public Timestamp getTabWriteDate() {
		return this.tabWriteDate;
	}

	public void setTabWriteDate(Timestamp tabWriteDate) {
		this.tabWriteDate = tabWriteDate;
	}
	@Column(name = "tab_pay_state", length = 200)
	public String getPayState() {
		return payState;
	}

	public void setPayState(String payState) {
		this.payState = payState;
	}

	public Timestamp getTabPayDates() {
		return tabPayDates;
	}

	public void setTabPayDates(Timestamp tabPayDates) {
		this.tabPayDates = tabPayDates;
	}

}