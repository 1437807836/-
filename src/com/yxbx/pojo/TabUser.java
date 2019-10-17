package com.yxbx.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TabUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tab_user", catalog = "bxqd")
public class TabUser implements java.io.Serializable {

	// Fields

	private Integer tabUserId;
	private String tabPassword;
	private Integer tabUserType;
	private String tabUsername;
	private String tabMobile;
	private Integer tabIsDelete;

	// Constructors

	/** default constructor */
	public TabUser() {
	}

	/** minimal constructor */
	public TabUser(String tabPassword, Integer tabUserType, String tabUsername) {
		this.tabPassword = tabPassword;
		this.tabUserType = tabUserType;
		this.tabUsername = tabUsername;
	}

	/** full constructor */
	public TabUser(String tabPassword, Integer tabUserType, String tabUsername,
			String tabMobile, Integer tabIsDelete) {
		this.tabPassword = tabPassword;
		this.tabUserType = tabUserType;
		this.tabUsername = tabUsername;
		this.tabMobile = tabMobile;
		this.tabIsDelete = tabIsDelete;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "tab_user_id", unique = true, nullable = false)
	public Integer getTabUserId() {
		return this.tabUserId;
	}

	public void setTabUserId(Integer tabUserId) {
		this.tabUserId = tabUserId;
	}

	@Column(name = "tab_password", nullable = false)
	public String getTabPassword() {
		return this.tabPassword;
	}

	public void setTabPassword(String tabPassword) {
		this.tabPassword = tabPassword;
	}

	@Column(name = "tab_user_type", nullable = false)
	public Integer getTabUserType() {
		return this.tabUserType;
	}

	public void setTabUserType(Integer tabUserType) {
		this.tabUserType = tabUserType;
	}

	@Column(name = "tab_username", nullable = false)
	public String getTabUsername() {
		return this.tabUsername;
	}

	public void setTabUsername(String tabUsername) {
		this.tabUsername = tabUsername;
	}

	@Column(name = "tab_mobile", length = 30)
	public String getTabMobile() {
		return this.tabMobile;
	}

	public void setTabMobile(String tabMobile) {
		this.tabMobile = tabMobile;
	}

	@Column(name = "tab_is_delete")
	public Integer getTabIsDelete() {
		return this.tabIsDelete;
	}

	public void setTabIsDelete(Integer tabIsDelete) {
		this.tabIsDelete = tabIsDelete;
	}

}