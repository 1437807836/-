package com.yxbx;

import com.google.gson.Gson;
import com.yxbx.pojo.TabScheduleOfInsurance;

public class asd {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String haha="{\"tabCarNum\":sda212,\"tabCardState\":公司刷卡,\"tabAmountSate\":未付,\"tabJfysAmount\":3512.01,\"tabYgzhjqAmount\":896.32,\"tabYgzhsyAmount\":2385.22,\"tabJbfjql\":0,\"tabJbfsyl\":0.24,\"tabZbfJ\":0.45,\"tabCustomerMobile\":13234981080,\"tabManagerUser\":admin,\"tabCommissionOther\":0.00,\"tabRealAmount\":3215.33,\"tabCommercialInsurance\":2973.11,\"tabCompulsoryInsurance\":950,\"tabTax\":300,\"tabCommissionAmount\":1198.12,\"tabInsuranceCompanyName\":haha,\"tabAllAmount\":4223.15,\"tabYxState\":传统,\"tabCarState\":客车,\"tabChannel\":1,\"tabAgentName\":哈哈,\"tabStartDate\":\"2019-12-27 12:00:00\",\"tabQdDate\":\"2019-12-27 12:00:00\",\"tabEndDate\":\"2019-12-27 12:00:00\",\"tabSafePerson\":哈哈,\"tabTeamOwnership\":哈哈 }";
		Gson gson=new Gson(); 
		TabScheduleOfInsurance tabSoi= gson.fromJson(haha, TabScheduleOfInsurance.class);
	}

}
