package com.yxbx.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TabInsuranceFile entity. @author MyEclipse Persistence Tools
 */

public class InsuranceFile  {

	// Fields

	private Integer id;
	private Integer tabInsuranceId;
	private Integer tabInsuranceTrueId;
	private Integer tabUserId;
	private Timestamp tabUploadDate;
	private String tabFileAddress;

	// Constructors

	/** default constructor */
	public InsuranceFile() {
	}

	/** full constructor */
	public InsuranceFile(Integer tabInsuranceId, Integer tabInsuranceTrueId,
			Integer tabUserId, Timestamp tabUploadDate, String tabFileAddress) {
		this.tabInsuranceId = tabInsuranceId;
		this.tabInsuranceTrueId = tabInsuranceTrueId;
		this.tabUserId = tabUserId;
		this.tabUploadDate = tabUploadDate;
		this.tabFileAddress = tabFileAddress;
	}

	// Property accessors
	@Transient
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Transient
	public Integer getTabInsuranceId() {
		return this.tabInsuranceId;
	}

	public void setTabInsuranceId(Integer tabInsuranceId) {
		this.tabInsuranceId = tabInsuranceId;
	}

	@Transient
	public Integer getTabInsuranceTrueId() {
		return this.tabInsuranceTrueId;
	}

	public void setTabInsuranceTrueId(Integer tabInsuranceTrueId) {
		this.tabInsuranceTrueId = tabInsuranceTrueId;
	}

	@Transient
	public Integer getTabUserId() {
		return this.tabUserId;
	}

	public void setTabUserId(Integer tabUserId) {
		this.tabUserId = tabUserId;
	}

	@Transient
	public Timestamp getTabUploadDate() {
		return this.tabUploadDate;
	}

	public void setTabUploadDate(Timestamp tabUploadDate) {
		this.tabUploadDate = tabUploadDate;
	}

	@Transient
	public String getTabFileAddress() {
		return this.tabFileAddress;
	}

	public void setTabFileAddress(String tabFileAddress) {
		this.tabFileAddress = tabFileAddress;
	}

}