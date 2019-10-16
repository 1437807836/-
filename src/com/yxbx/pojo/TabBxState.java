package com.yxbx.pojo;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TabBxState entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tab_bx_state", catalog = "bxqd")
public class TabBxState implements java.io.Serializable {

	// Fields

	private Integer id;
	private String tabState;
	private BigDecimal tabIncome;
	private BigDecimal tabExpenditure;
	private BigDecimal tabInvoice;

	// Constructors

	/** default constructor */
	public TabBxState() {
	}

	/** full constructor */
	public TabBxState(String tabState, BigDecimal tabIncome,
			BigDecimal tabExpenditure, BigDecimal tabInvoice) {
		this.tabState = tabState;
		this.tabIncome = tabIncome;
		this.tabExpenditure = tabExpenditure;
		this.tabInvoice = tabInvoice;
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

	@Column(name = "tab_state")
	public String getTabState() {
		return this.tabState;
	}

	public void setTabState(String tabState) {
		this.tabState = tabState;
	}

	@Column(name = "tab_income", precision = 30, scale = 0)
	public BigDecimal getTabIncome() {
		return this.tabIncome;
	}

	public void setTabIncome(BigDecimal tabIncome) {
		this.tabIncome = tabIncome;
	}

	@Column(name = "tab_expenditure", precision = 30, scale = 0)
	public BigDecimal getTabExpenditure() {
		return this.tabExpenditure;
	}

	public void setTabExpenditure(BigDecimal tabExpenditure) {
		this.tabExpenditure = tabExpenditure;
	}

	@Column(name = "tab_invoice", precision = 30, scale = 0)
	public BigDecimal getTabInvoice() {
		return this.tabInvoice;
	}

	public void setTabInvoice(BigDecimal tabInvoice) {
		this.tabInvoice = tabInvoice;
	}

}