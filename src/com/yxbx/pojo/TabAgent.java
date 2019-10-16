package com.yxbx.pojo;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TabAgent entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tab_agent", catalog = "bxqd")
public class TabAgent implements java.io.Serializable {

	// Fields

	private Integer tabAgentId;
	private String tabAgentName;
	private String tabAgentSex;
	private String tabBankCardNum;
	private String tabIdCardNum;
	private String tabAgentFounder;
	private String tabMobile;
	private Integer tabTeamId;
	private String tabBankName;
	private Timestamp tabWriteDate;
	private Integer tabIsDelete;
	private String tabTeamName;

	// Constructors

	/** default constructor */
	public TabAgent() {
	}

	/** full constructor */
	public TabAgent(String tabAgentName, String tabAgentSex,
			String tabBankCardNum, String tabIdCardNum, String tabAgentFounder,
			String tabMobile, Integer tabTeamId, String tabBankName,
			Timestamp tabWriteDate, Integer tabIsDelete, String tabTeamName) {
		this.tabAgentName = tabAgentName;
		this.tabAgentSex = tabAgentSex;
		this.tabBankCardNum = tabBankCardNum;
		this.tabIdCardNum = tabIdCardNum;
		this.tabAgentFounder = tabAgentFounder;
		this.tabMobile = tabMobile;
		this.tabTeamId = tabTeamId;
		this.tabBankName = tabBankName;
		this.tabWriteDate = tabWriteDate;
		this.tabIsDelete = tabIsDelete;
		this.tabTeamName = tabTeamName;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "tab_agent_id", unique = true, nullable = false)
	public Integer getTabAgentId() {
		return this.tabAgentId;
	}

	public void setTabAgentId(Integer tabAgentId) {
		this.tabAgentId = tabAgentId;
	}

	@Column(name = "tab_agent_name", length = 100)
	public String getTabAgentName() {
		return this.tabAgentName;
	}

	public void setTabAgentName(String tabAgentName) {
		this.tabAgentName = tabAgentName;
	}

	@Column(name = "tab_agent_sex", length = 1)
	public String getTabAgentSex() {
		return this.tabAgentSex;
	}

	public void setTabAgentSex(String tabAgentSex) {
		this.tabAgentSex = tabAgentSex;
	}

	@Column(name = "tab_bank_card_num", length = 50)
	public String getTabBankCardNum() {
		return this.tabBankCardNum;
	}

	public void setTabBankCardNum(String tabBankCardNum) {
		this.tabBankCardNum = tabBankCardNum;
	}

	@Column(name = "tab_id_card_num", length = 50)
	public String getTabIdCardNum() {
		return this.tabIdCardNum;
	}

	public void setTabIdCardNum(String tabIdCardNum) {
		this.tabIdCardNum = tabIdCardNum;
	}

	@Column(name = "tab_agent_founder", length = 50)
	public String getTabAgentFounder() {
		return this.tabAgentFounder;
	}

	public void setTabAgentFounder(String tabAgentFounder) {
		this.tabAgentFounder = tabAgentFounder;
	}

	@Column(name = "tab_mobile", length = 20)
	public String getTabMobile() {
		return this.tabMobile;
	}

	public void setTabMobile(String tabMobile) {
		this.tabMobile = tabMobile;
	}

	@Column(name = "tab_team_id")
	public Integer getTabTeamId() {
		return this.tabTeamId;
	}

	public void setTabTeamId(Integer tabTeamId) {
		this.tabTeamId = tabTeamId;
	}

	@Column(name = "tab_bank_name")
	public String getTabBankName() {
		return this.tabBankName;
	}

	public void setTabBankName(String tabBankName) {
		this.tabBankName = tabBankName;
	}

	@Column(name = "tab_write_date", length = 19)
	public Timestamp getTabWriteDate() {
		return this.tabWriteDate;
	}

	public void setTabWriteDate(Timestamp tabWriteDate) {
		this.tabWriteDate = tabWriteDate;
	}

	@Column(name = "tab_is_delete")
	public Integer getTabIsDelete() {
		return this.tabIsDelete;
	}

	public void setTabIsDelete(Integer tabIsDelete) {
		this.tabIsDelete = tabIsDelete;
	}

	@Column(name = "tab_team_name", length = 30)
	public String getTabTeamName() {
		return this.tabTeamName;
	}

	public void setTabTeamName(String tabTeamName) {
		this.tabTeamName = tabTeamName;
	}

}