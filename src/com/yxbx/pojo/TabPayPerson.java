package com.yxbx.pojo;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TabPayPerson entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tab_pay_person", catalog = "bxqd")
public class TabPayPerson implements java.io.Serializable {

	// Fields

	private Integer id;
	private String tabPayName;
	private String tabPayCard;
	private String tabWritePerson;
	private Timestamp tabWriteDate;
	private Integer tabIsDelete;
	private String tabBankName;

	// Constructors

	/** default constructor */
	public TabPayPerson() {
	}

	/** full constructor */
	public TabPayPerson(String tabPayName, String tabPayCard,
			String tabWritePerson, Timestamp tabWriteDate, Integer tabIsDelete,
			String tabBankName) {
		this.tabPayName = tabPayName;
		this.tabPayCard = tabPayCard;
		this.tabWritePerson = tabWritePerson;
		this.tabWriteDate = tabWriteDate;
		this.tabIsDelete = tabIsDelete;
		this.tabBankName = tabBankName;
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

	@Column(name = "tab_pay_name", length = 100)
	public String getTabPayName() {
		return this.tabPayName;
	}

	public void setTabPayName(String tabPayName) {
		this.tabPayName = tabPayName;
	}

	@Column(name = "tab_pay_card", length = 100)
	public String getTabPayCard() {
		return this.tabPayCard;
	}

	public void setTabPayCard(String tabPayCard) {
		this.tabPayCard = tabPayCard;
	}

	@Column(name = "tab_write_person", length = 100)
	public String getTabWritePerson() {
		return this.tabWritePerson;
	}

	public void setTabWritePerson(String tabWritePerson) {
		this.tabWritePerson = tabWritePerson;
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

	@Column(name = "tab_bank_name", length = 40)
	public String getTabBankName() {
		return this.tabBankName;
	}

	public void setTabBankName(String tabBankName) {
		this.tabBankName = tabBankName;
	}

}