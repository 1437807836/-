package com.yxbx.pojo;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TabInsurer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tab_insurer", catalog = "bxqd")
public class TabInsurer implements java.io.Serializable {

	// Fields

	private Integer id;
	private String tabInsurerName;
	private String tabWhPerson;
	private Timestamp tabWriteDate;
	private String tabUpdatePerson;

	// Constructors

	/** default constructor */
	public TabInsurer() {
	}

	/** full constructor */
	public TabInsurer(String tabInsurerName, String tabWhPerson,
			Timestamp tabWriteDate, String tabUpdatePerson) {
		this.tabInsurerName = tabInsurerName;
		this.tabWhPerson = tabWhPerson;
		this.tabWriteDate = tabWriteDate;
		this.tabUpdatePerson = tabUpdatePerson;
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

	@Column(name = "tab_insurer_name", length = 100)
	public String getTabInsurerName() {
		return this.tabInsurerName;
	}

	public void setTabInsurerName(String tabInsurerName) {
		this.tabInsurerName = tabInsurerName;
	}

	@Column(name = "tab_wh_person", length = 100)
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

	@Column(name = "tab_update_person", length = 20)
	public String getTabUpdatePerson() {
		return this.tabUpdatePerson;
	}

	public void setTabUpdatePerson(String tabUpdatePerson) {
		this.tabUpdatePerson = tabUpdatePerson;
	}

}