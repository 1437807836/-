package com.yxbx.pojo;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TabTeam entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tab_team", catalog = "bxqd")
public class TabTeam implements java.io.Serializable {

	// Fields

	private Integer id;
	private String tabTeamName;
	private String tabFounder;
	private Timestamp tabFounderTime;
	private Timestamp tabWriteDate;
	private Integer tabIsDelete;

	// Constructors

	/** default constructor */
	public TabTeam() {
	}

	/** full constructor */
	public TabTeam(String tabTeamName, String tabFounder,
			Timestamp tabFounderTime, Timestamp tabWriteDate,
			Integer tabIsDelete) {
		this.tabTeamName = tabTeamName;
		this.tabFounder = tabFounder;
		this.tabFounderTime = tabFounderTime;
		this.tabWriteDate = tabWriteDate;
		this.tabIsDelete = tabIsDelete;
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

	@Column(name = "tab_team_name", length = 100)
	public String getTabTeamName() {
		return this.tabTeamName;
	}

	public void setTabTeamName(String tabTeamName) {
		this.tabTeamName = tabTeamName;
	}

	@Column(name = "tab_founder", length = 100)
	public String getTabFounder() {
		return this.tabFounder;
	}

	public void setTabFounder(String tabFounder) {
		this.tabFounder = tabFounder;
	}

	@Column(name = "tab_founder_time", length = 19)
	public Timestamp getTabFounderTime() {
		return this.tabFounderTime;
	}

	public void setTabFounderTime(Timestamp tabFounderTime) {
		this.tabFounderTime = tabFounderTime;
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

}