package com.yxbx.action;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yxbx.bean.BxState;
import com.yxbx.bean.SeachInsurance;
import com.yxbx.dao.DataDao;
import com.yxbx.pojo.TabBxState;
import com.yxbx.pojo.TabInsurerPolicy;
import com.yxbx.pojo.TabScheduleOfInsurance;
import com.yxbx.service.InsurerPolicyService;
import com.yxbx.util.Util;

public class Test {
	
	@Resource
	DataDao datadao;

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataDao d=new DataDao();
		
		
			Gson  g=new Gson();
			BxState b=new BxState();
			TabBxState tbs=new TabBxState();
			tbs.setTabState("商业险");
			tbs.setTabExpenditure(Util.toBigDecimal("0.01"));
			tbs.setTabIncome(Util.toBigDecimal("0.01"));
			tbs.setTabInvoice(Util.toBigDecimal("0.02"));
			List<TabBxState> list=new ArrayList<TabBxState>();
			list.add(tbs);
			list.add(tbs);
			b.setListBxState(list);
			
		//	 TabInsurerPolicy tabSoi= g.toJson(jsonElement)
			System.out.println(g.toJson(b));
//			String str="{\"tabYxState\":\"你是个傻逼\"}";
//			TabScheduleOfInsurance si=  g.fromJson(str, TabScheduleOfInsurance.class);
//			System.out.println(si.getTabYxState());
//			 JsonObject returnData = new JsonParser().parse(str).getAsJsonObject();
//			 returnData.addProperty("tabYxState", "帅气");
//			 returnData.addProperty("tabYxState", "超帅气");
//			 System.out.println(returnData.get("tabYxState").getAsString());
//			 保批单日期,到期日,签单日期,车牌号,被保险人,业务性质,转续保,使用性质,
//			渠道,保险公司,交强险,商业险,车船税,小计,代理人,团队,结算方式,备注,出单员,
//			维护人,客户电话,交强净保费,交强费用率,交强险费用,商业净保费,商业费用率,
//			商业险费用,其他应付,佣金合计,备用,说明,事项
	}*/

	public static void main(String[] args) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");//设置日期格式
		String s = " 2020/11/11 11:22 ";
		System.out.println(s.trim());
		System.out.println(s.trim().length());
		Double aDouble = new Double("2%".substring(0, "2%".indexOf("%")));
		System.out.println(aDouble);
		System.out.println(aDouble / 100.0);
		System.out.println(aDouble / 100);
		Date date = null;
		try {
			date = df.parse("2020/11/11 ");
			Timestamp timestamp = new Timestamp(date.getTime());
			System.out.println(timestamp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
