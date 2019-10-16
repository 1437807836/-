package com.yxbx.pojo;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TabInsuranceFile entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tab_insurance_file", catalog = "bxqd")
public class TabInsuranceFile implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer tabInsuranceId;
	private Integer tabInsuranceTrueId;
	private Integer tabPayId;
	private Integer tabPayOpenId;
	

	private Integer tabUserId;
	private Timestamp tabUploadDate;
	private String tabFileAddress;

	// Constructors

	/** default constructor */
	public TabInsuranceFile() {
	}

	/** full constructor */
	public TabInsuranceFile(Integer tabInsuranceId, Integer tabInsuranceTrueId,
			Integer tabUserId, Timestamp tabUploadDate, String tabFileAddress) {
		this.tabInsuranceId = tabInsuranceId;
		this.tabInsuranceTrueId = tabInsuranceTrueId;
		this.tabUserId = tabUserId;
		this.tabUploadDate = tabUploadDate;
		this.tabFileAddress = tabFileAddress;
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

	@Column(name = "tab_insurance_id")
	public Integer getTabInsuranceId() {
		return this.tabInsuranceId;
	}

	public void setTabInsuranceId(Integer tabInsuranceId) {
		this.tabInsuranceId = tabInsuranceId;
	}

	@Column(name = "tab_insurance_true_id")
	public Integer getTabInsuranceTrueId() {
		return this.tabInsuranceTrueId;
	}

	public void setTabInsuranceTrueId(Integer tabInsuranceTrueId) {
		this.tabInsuranceTrueId = tabInsuranceTrueId;
	}

	@Column(name = "tab_user_id")
	public Integer getTabUserId() {
		return this.tabUserId;
	}

	public void setTabUserId(Integer tabUserId) {
		this.tabUserId = tabUserId;
	}

	@Column(name = "tab_upload_date", length = 19)
	public Timestamp getTabUploadDate() {
		return this.tabUploadDate;
	}

	public void setTabUploadDate(Timestamp tabUploadDate) {
		this.tabUploadDate = tabUploadDate;
	}

	@Column(name = "tab_file_address", length = 200)
	public String getTabFileAddress() {
		return this.tabFileAddress;
	}

	public void setTabFileAddress(String tabFileAddress) {
		this.tabFileAddress = tabFileAddress;
	}
	@Column(name = "tab_pay_id")
	public Integer getTabPayId() {
		return tabPayId;
	}
	public void setTabPayId(Integer tabPayId) {
		this.tabPayId = tabPayId;
	}
	@Column(name = "tab_pay_open_id")
	public Integer getTabPayOpenId() {
		return tabPayOpenId;
	}
	public void setTabPayOpenId(Integer tabPayOpenId) {
		this.tabPayOpenId = tabPayOpenId;
	}
}