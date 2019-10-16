package com.yxbx.servicelmpl;


import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.Entity;

import com.yxbx.pojo.TabInsurer;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yxbx.bean.SeachInsurance;
import com.yxbx.dao.DataDao;
import com.yxbx.pojo.TabScheduleOfInsuranceTrue;

import com.yxbx.service.ScheduleOfInsuranceTrueService;


@Entity
@Service("ScheduleOfInsuranceTrueServicempl")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ScheduleOfInsuranceTrueServicempl implements ScheduleOfInsuranceTrueService {


    @Resource
    DataDao datadao;


    @Override
    public TabScheduleOfInsuranceTrue queryAllById(Integer Id) {
        // TODO Auto-generated method stub
        TabScheduleOfInsuranceTrue tsg = datadao.getObjectById(TabScheduleOfInsuranceTrue.class, Id);
        return tsg;
    }

    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    @Override
    public void addScheduleOfInsurance(TabScheduleOfInsuranceTrue ydi) {
        datadao.addObject(ydi);
        List<TabScheduleOfInsuranceTrue> list = (List<TabScheduleOfInsuranceTrue>) datadao.getObjectList("select * FROM tab_schedule_of_insurance_true where tabtype!='1'");
        for (int i = 0; i < list.size(); i++) {
            TabScheduleOfInsuranceTrue tof = list.get(i);
            for (int y = 0; y < list.size(); y++) {
                TabScheduleOfInsuranceTrue tof1 = list.get(y);
                if (i == y || tof1.getAgainState() != 0) {
                    continue;
                } else {
                    if (tof.getTabAgentName().equals(tof1.getTabAgentName())
                            && tof.getTabAllAmount().compareTo(tof1.getTabAllAmount()) == 0
                            && tof.getTabCarNum().equals(tof1.getTabCarNum())
                    ) {
                        String sql = "update tab_schedule_of_insurance_true set tabtype=0 where tab_agent_name='" + ydi.getTabAgentName() + "' and tab_all_amount=" + ydi.getTabAllAmount() + " and tab_car_num='" + ydi.getTabCarNum() + "'";
                        int executeBySql = datadao.executeBySql(sql);

                    }
                }

            }
        }
        // TODO Auto-generated method stub


    }

    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    @Override
    public void updateScheduleOfInsurance(TabScheduleOfInsuranceTrue ydi) {
        // TODO Auto-generated method stub
        datadao.updateObject(ydi);
    }


    @Override
    public TabScheduleOfInsuranceTrue findScheduleOfInsurance(String username,
                                                              String password) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public TabScheduleOfInsuranceTrue findScheduleOfInsurance(String mobile) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public List<TabScheduleOfInsuranceTrue> queryAllByObject(
            SeachInsurance si, String falg) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式2
        String date = df.format(new Date());

        StringBuffer sql = new StringBuffer("from TabScheduleOfInsuranceTrue where 1=1  ");
        StringBuffer sql1 = new StringBuffer("from TabScheduleOfInsurance where 1=1  ");
//		String  params[]=null;
//		String values[]=null;
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> listValues = new ArrayList<String>();

        if (si != null) {
            if (si.getId() != null) {
                sql.append(" and id=:Id");
                list.add("Id");
                listValues.add(String.valueOf(si.getId()));

            }
			/*sql.append(" and  tab_start_date  >=:stime  and tab_start_date<=:date ");
			 list.add("stime");	
			 list.add("date");
			 listValues.add(String.valueOf(si.getTabStartDateStart()==null?"1800-01-01":si.getTabStartDateStart()));
			 listValues.add(String.valueOf(si.getTabStartDateEnd()==null?new Timestamp(System.currentTimeMillis()):si.getTabStartDateEnd()));
			*/

            sql.append(" and  tab_end_date  >=:TabEndDateStart and tab_end_date<=:TabEndDateEnd ");
            list.add("TabEndDateStart");
            list.add("TabEndDateEnd");
            listValues.add(String.valueOf(si.getTabEndDateStart() == null ? "1800-01-01" : si.getTabEndDateStart()));
            listValues.add(String.valueOf(si.getTabEndDateEnd() == null ? "4040-01-01" : si.getTabEndDateEnd()));


            sql.append(" and  tab_qd_date  >=:TabQdDateStart and tab_qd_date <=:TabQdDateEnd  ");
            list.add("TabQdDateStart");
            list.add("TabQdDateEnd");
            listValues.add(String.valueOf(si.getTabQdDateStart() == null ? "1800-01-01" : si.getTabQdDateStart()));
            listValues.add(String.valueOf(si.getTabQdDateEnd() == null ? "4040-01-01" : si.getTabQdDateEnd()));


            sql.append(" and  tab_write_date >=:TabWriteDateStart and tab_write_date<=:TabWriteDateEnd  ");
            list.add("TabWriteDateStart");
            list.add("TabWriteDateEnd");
            listValues.add(String.valueOf(si.getTab_write_date_start() == null ? "1800-01-01" : si.getTab_write_date_start()));
            listValues.add(String.valueOf(si.getTab_write_date_end() == null ? new Timestamp(System.currentTimeMillis()) : si.getTab_write_date_end()));

            if (si.getTabCarNum() != null) {
                sql.append(" and   tab_car_num=:tabCarNum");
                list.add("tabCarNum");

                listValues.add(String.valueOf(si.getTabCarNum()));


            }
            if (si.getTabSafePerson() != null) {
                sql.append(" and  tab_safe_person=:TabSafePerson");
                list.add("TabSafePerson");

                listValues.add(String.valueOf(si.getTabSafePerson()));

            }
            if (si.getTabYxState() != null) {
                sql.append(" and  tab_yx_state=:TabYxState");
                list.add("TabYxState");

                listValues.add(String.valueOf(si.getTabYxState()));


            }
            if (si.getTabCarState() != null) {
                sql.append(" and  tab_car_state=:TabCarState");
                list.add("TabCarState");

                listValues.add(String.valueOf(si.getTabCarState()));

            }
            if (si.getTabChannel() != null) {
                sql.append(" and  tab_channel=:TabChannel");
                list.add("TabChannel");

                listValues.add(String.valueOf(si.getTabChannel()));

            }
            if (si.getTabInsuranceCompanyName() != null) {
                sql.append(" and  tab_insurance_company_name=:TabInsuranceCompanyName");
                list.add("TabInsuranceCompanyName");
                listValues.add(String.valueOf(si.getTabInsuranceCompanyName()));

            }
            if (si.getTabCdPerson() != null) {
                sql.append(" and  tab_cd_person=:TabCdPerson");
                list.add("TabCdPerson");

                listValues.add(String.valueOf(si.getTabCdPerson()));

            }
            if (si.getTabWhPerson() != null) {
                sql.append(" and  tab_manager_user=:TabWhPerson");
                list.add("TabWhPerson");
                listValues.add(String.valueOf(si.getTabWhPerson()));

            }
            if (si.getTabAgentName() != null) {
                sql.append(" and  tab_agent_name=:TabAgentName");
                list.add("TabAgentName");
                listValues.add(String.valueOf(si.getTabAgentName()));

            }
            if (si.getTabTeamOwnership() != null) {
                sql.append(" and  tab_team_ownership=:TabTeamOwnership");
                list.add("TabTeamOwnership");
                listValues.add(String.valueOf(si.getTabTeamOwnership()));
            }
        }

        sql.append(" order by  tab_write_date desc");
        if (list.isEmpty()) {
            @SuppressWarnings("unchecked")
            List<TabScheduleOfInsuranceTrue> list1 = (List<TabScheduleOfInsuranceTrue>) datadao.getAllObjects1(sql.toString(), null, null);
            if ("1".equals(falg)) {
                List<TabScheduleOfInsuranceTrue> list2 = (List<TabScheduleOfInsuranceTrue>) datadao.getAllObjects1(sql1.toString(), null, null);
                list1.addAll(list2);
            }
            return list1;
        }
        String params[] = new String[list.size()];
        String values[] = new String[list.size()];
        for (int i = 0; i < params.length; i++) {
            params[i] = list.get(i);
            values[i] = listValues.get(i);
        }
        String sqlStr = sql.toString();
        @SuppressWarnings("unchecked")
        List<TabScheduleOfInsuranceTrue> list1 = (List<TabScheduleOfInsuranceTrue>) datadao.getAllObjects1(sqlStr, params, values);
        if ("1".equals(falg)) {
            List<TabScheduleOfInsuranceTrue> list2 = (List<TabScheduleOfInsuranceTrue>) datadao.getAllObjects1(sql1.toString(), null, null);
            list1.addAll(list2);
        }
        //		private Integer id;
//		private Timestamp tabStartDate;//批单日期
//		private Timestamp tabEndDate;//到期日
//		private Timestamp tabQdDate;//签单日期
//		private String tabCarNum;//车牌
//		private String tabSafePerson;//被保险人				
//		private String tabYxState;//业务性质保批单		
//		private String tabCarState;//车辆使用性质
//		private String tabChannel;//渠道归属
//		private String tabInsuranceCompanyName;//保险公司名字
//		private String tabCdPerson;//出单人
//		private String tabWhPerson;//维护人
//		private String tabAgentName;//代理人姓名
//		private String tabAmountSate;//收款状态
//		private String tabTeamOwnership;//团队归属						
        return list1;
    }


    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    @Override
    public void addScheduleOfInsuranceList(List<TabScheduleOfInsuranceTrue> toi) {
        // TODO Auto-generated method stub
        for (int i = 0; i < toi.size(); i++) {
            datadao.addObject(toi.get(i));
        }
        List<TabScheduleOfInsuranceTrue> list = (List<TabScheduleOfInsuranceTrue>) datadao.getObjectList("select * FROM tab_schedule_of_insurance_true where tabtype!='1'");
        for (int i = 0; i < list.size(); i++) {
            TabScheduleOfInsuranceTrue tof = list.get(i);
            for (int y = 0; y < list.size(); y++) {
                TabScheduleOfInsuranceTrue tof1 = list.get(y);
                if (i == y || tof1.getAgainState() != 0) {
                    continue;
                } else {
                    if (tof.getTabAgentName().equals(tof1.getTabAgentName())
                            && tof.getTabAllAmount().compareTo(tof1.getTabAllAmount()) == 0
                            && tof.getTabCarNum().equals(tof1.getTabCarNum())
                    ) {
                        String sql = "update tab_schedule_of_insurance_true set tabtype=0 where tab_agent_name='" + tof.getTabAgentName() + "' and tab_all_amount=" + tof.getTabAllAmount() + " and tab_car_num='" + tof.getTabCarNum() + "'";
                        int executeBySql = datadao.executeBySql(sql);

                    }
                }

            }
        }


    }

    @Override
    public List<TabScheduleOfInsuranceTrue> queryAllTabScheduleOfInsuranceTrue(int page, String idStr) {
        StringBuffer sql = new StringBuffer("from TabScheduleOfInsuranceTrue ");
        if (idStr != null && !"".equals(idStr)) {
            sql.append(" where id in(").append(idStr).append(") ");
        }
        sql.append("order by tab_write_date desc");
        List<TabScheduleOfInsuranceTrue> list = (List<TabScheduleOfInsuranceTrue>) datadao.getAllObjects1(sql.toString(), null, null);
        return list;
    }

    @Override
    public String addScheduleOfInsuranceTrueOfExcel(String filePath, String fileName, int userId, String name) {
        String theFilePath = filePath + fileName;
        File theFile = new File(theFilePath);
        if (theFile.exists()) {
            String result = readXLS(theFilePath, userId, name);
            if (theFile.exists()) {
                theFile.delete();
            }
            return result;
        } else {
            return "文件不存在2";
        }
    }

    /***
     * 读取EXCEL文件中的内容
     *
     * @param path
     * @param userId
     * @param name
     */
    private String readXLS(String path, int userId, String name) {
        StringBuffer result = new StringBuffer();// 存放返回结果
        try {
            Workbook workbook = null;
            workbook = Workbook.getWorkbook(new File(path));
            result.append("文件名为'" + new File(path).getName() + "<br/>");
            int num = workbook.getNumberOfSheets();
            result.append("Sheet个数：" + num + "<br/>");
            // 循环读取Sheet
            for (int k = 0; k < num; k++) {
                /** 存放添加失败的行数 */
                StringBuffer sbStr = new StringBuffer();
                Sheet sheet = workbook.getSheet(k);
                String sheetName = sheet.getName().toString().trim();// sheet名称
                result.append("第" + (k + 1) + "个Sheet名：" + sheetName + "<br/>");
                int rowCount = sheet.getRows();// 行
                int columnCount = sheet.getColumns();// 列
                result.append("行数：" + rowCount + "，列数：" + columnCount + "<br/>");
                if (columnCount == 29) {// 列有29列
                    StringBuffer sb2 = new StringBuffer();// 存放第一行数据，即列名称
                    Cell cell = null;
                    if (rowCount > 0 && columnCount > 0) {// 有数据
                        int successNum = 0;
                        if (rowCount > 0) {// 至少存在第一行
                            String headerStr = "保批单日期,到期日,签单日期,车牌号,被保险人,业务性质,转续保,使用性质,渠道,保险公司,交强险,商业险,车船税,小计,代理人,团队,结算方式,备注,出单员,维护人,客户电话,交强净保费,交强费用率,交强险费用,商业净保费,商业费用率,商业险费用,其他应付,佣金合计,";
                            // 读取表的第一行
                            for (int j = 0; j < columnCount; j++) {
                                Cell headerRowCell = sheet.getCell(j, 0);// 第二行，行数为1
                                String temp1 = headerRowCell.getContents().toString().trim();
                                sb2.append(temp1);
                                sb2.append(",");
                            }
                            result.append("第一行:" + sb2.toString() + "<br/>");
                            if (!headerStr.equals(sb2.toString().trim())) {// 没有按照给定的规格来
                                result.append("'" + sheetName + "' 第一行标题栏，不符合格式！<br/>");
                                break;
                            } else {
                                // 从第二行开始循环读取Cell，读一行处理一行数据
                                forNum:
                                for (int m = 1; m < rowCount; m++) {// 从第二行开始循环行
                                    /** 试题类型，放在最外面，便于答案的判断 */
                                    int type = 0;
                                    // 建立空的对象
                                    TabScheduleOfInsuranceTrue tabScheduleOfInsuranceTrue = new TabScheduleOfInsuranceTrue();
                                    for (int n = 0; n < columnCount; n++) {// 循环列
                                        String temp2 = "";// 存放内容
                                        cell = sheet.getCell(n, m);
                                        temp2 = cell.getContents().toString().trim();
                                        SimpleDateFormat df1 = new SimpleDateFormat("yyyy/MM/dd");//设置日期格式1
                                        SimpleDateFormat df2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置日期格式2
                                        Date date = null;
                                        if (n == 0) {// 保批单日期
                                            try {
                                                if (temp2.length() > 10) {
                                                    date = df2.parse(temp2);
                                                } else {
                                                    date = df1.parse(temp2);
                                                }
                                                Timestamp timestamp = new Timestamp(date.getTime());
                                                tabScheduleOfInsuranceTrue.setTabQdDate(timestamp);
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                                result.append("第" + (m + 1) + "行，第" + (n + 1) + "列: '" + temp2 + "' 格式不正确！<br/>");
                                                sbStr.append((m + 1) + ",");
                                                continue forNum;
                                            }
                                        } else if (n == 1) {// 到期日
                                            try {
                                                if (temp2.length() > 10) {
                                                    date = df2.parse(temp2);
                                                } else {
                                                    date = df1.parse(temp2);
                                                }
                                                Timestamp timestamp = new Timestamp(date.getTime());
                                                tabScheduleOfInsuranceTrue.setTabEndDate(timestamp);
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                                result.append("第" + (m + 1) + "行，第" + (n + 1) + "列: '" + temp2 + "' 格式不正确！<br/>");
                                                sbStr.append((m + 1) + ",");
                                                continue forNum;
                                            }
                                        } else if (n == 2) {// 签单日期
                                            try {
                                                if (temp2.length() > 10) {
                                                    date = df2.parse(temp2);
                                                } else {
                                                    date = df1.parse(temp2);
                                                }
                                                Timestamp timestamp = new Timestamp(date.getTime());
                                                tabScheduleOfInsuranceTrue.setTabStartDate(timestamp);
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                                result.append("第" + (m + 1) + "行，第" + (n + 1) + "列: '" + temp2 + "' 格式不正确！<br/>");
                                                sbStr.append((m + 1) + ",");
                                                continue forNum;
                                            }
                                        } else if (n == 3) {// 车牌号
                                            if (temp2.equals("")) {
                                                result.append("第" + (m + 1) + "行，第" + (n + 1) + "列: '" + temp2 + "' 车牌号为空！<br/>");
                                                sbStr.append((m + 1) + ",");
                                                continue forNum;
                                            } else {
                                                tabScheduleOfInsuranceTrue.setTabCarNum(temp2);
                                            }
                                        } else if (n == 4) {// 被保险人
                                            if (temp2.equals("")) {
                                                result.append("第" + (m + 1) + "行，第" + (n + 1) + "列: '" + temp2 + "' 被保险人为空！<br/>");
                                                sbStr.append((m + 1) + ",");
                                                continue forNum;
                                            } else {
                                                tabScheduleOfInsuranceTrue.setTabSafePerson(temp2);
                                            }
                                        } else if (n == 5) {// 业务性质
                                            if (temp2.equals("")) {
                                                result.append("第" + (m + 1) + "行，第" + (n + 1) + "列: '" + temp2 + "' 业务性质为空！<br/>");
                                                sbStr.append((m + 1) + ",");
                                                continue forNum;
                                            } else {
                                                tabScheduleOfInsuranceTrue.setTabBusinessNature(temp2);
                                            }
                                        } else if (n == 6) {// 转续保
                                            if (temp2.equals("")) {
                                                result.append("第" + (m + 1) + "行，第" + (n + 1) + "列: '" + temp2 + "' 转续保为空！<br/>");
                                                sbStr.append((m + 1) + ",");
                                                continue forNum;
                                            } else {
                                                tabScheduleOfInsuranceTrue.setTabIsRenewal(temp2);
                                            }
                                        } else if (n == 7) {// 使用性质
                                            if (temp2.equals("")) {
                                                result.append("第" + (m + 1) + "行，第" + (n + 1) + "列: '" + temp2 + "' 使用性质为空！<br/>");
                                                sbStr.append((m + 1) + ",");
                                                continue forNum;
                                            } else {
                                                tabScheduleOfInsuranceTrue.setTabCarState(temp2);
                                            }
                                        } else if (n == 8) {// 渠道
                                            if (temp2.equals("")) {
                                                result.append("第" + (m + 1) + "行，第" + (n + 1) + "列: '" + temp2 + "' 渠道为空！<br/>");
                                                sbStr.append((m + 1) + ",");
                                                continue forNum;
                                            } else {
                                                tabScheduleOfInsuranceTrue.setTabChannel(temp2);
                                            }
                                        } else if (n == 9) {// 保险公司
                                            if (temp2.equals("")) {
                                                result.append("第" + (m + 1) + "行，第" + (n + 1) + "列: '" + temp2 + "' 保险公司为空！<br/>");
                                                sbStr.append((m + 1) + ",");
                                                continue forNum;
                                            } else {
                                                tabScheduleOfInsuranceTrue.setTabInsuranceCompanyName(temp2);
                                            }
                                        } else if (n == 10) {// 交强险
                                            try {
                                                tabScheduleOfInsuranceTrue.setTabCompulsoryInsurance(BigDecimal.valueOf(Double.parseDouble(temp2)));
                                            } catch (NumberFormatException e) {
                                                e.printStackTrace();
                                                result.append("第" + (m + 1) + "行，第" + (n + 1) + "列: '" + temp2 + "' 交强险格式错误！<br/>");
                                                sbStr.append((m + 1) + ",");
                                                continue forNum;
                                            }
                                        } else if (n == 11) {// 商业险
                                            try {
                                                tabScheduleOfInsuranceTrue.setTabCommercialInsurance(BigDecimal.valueOf(Double.parseDouble(temp2)));
                                            } catch (NumberFormatException e) {
                                                e.printStackTrace();
                                                result.append("第" + (m + 1) + "行，第" + (n + 1) + "列: '" + temp2 + "' 商业险格式错误！<br/>");
                                                sbStr.append((m + 1) + ",");
                                                continue forNum;
                                            }
                                        } else if (n == 12) {// 车船税
                                            try {
                                                tabScheduleOfInsuranceTrue.setTabTax(BigDecimal.valueOf(Double.parseDouble(temp2)));
                                            } catch (NumberFormatException e) {
                                                e.printStackTrace();
                                                result.append("第" + (m + 1) + "行，第" + (n + 1) + "列: '" + temp2 + "' 车船税格式错误！<br/>");
                                                sbStr.append((m + 1) + ",");
                                                continue forNum;
                                            }
                                        } else if (n == 13) {// 小计
                                            try {
                                                tabScheduleOfInsuranceTrue.setTabSubtotal(BigDecimal.valueOf(Double.parseDouble(temp2)));
                                            } catch (NumberFormatException e) {
                                                e.printStackTrace();
                                                result.append("第" + (m + 1) + "行，第" + (n + 1) + "列: '" + temp2 + "' 小计格式错误！<br/>");
                                                sbStr.append((m + 1) + ",");
                                                continue forNum;
                                            }
                                        } else if (n == 14) {// 代理人
                                            if (temp2.equals("")) {
                                                result.append("第" + (m + 1) + "行，第" + (n + 1) + "列: '" + temp2 + "' 代理人为空！<br/>");
                                                sbStr.append((m + 1) + ",");
                                                continue forNum;
                                            } else {
                                                tabScheduleOfInsuranceTrue.setTabAgentName(temp2);
                                            }
                                        } else if (n == 15) {// 团队
                                            if (temp2.equals("")) {
                                                result.append("第" + (m + 1) + "行，第" + (n + 1) + "列: '" + temp2 + "' 团队为空！<br/>");
                                                sbStr.append((m + 1) + ",");
                                                continue forNum;
                                            } else {
                                                tabScheduleOfInsuranceTrue.setTabTeamOwnership(temp2);
                                            }
                                        } else if (n == 16) {// 结算方式
                                            if (temp2.equals("")) {
                                                result.append("第" + (m + 1) + "行，第" + (n + 1) + "列: '" + temp2 + "' 结算方式为空！<br/>");
                                                sbStr.append((m + 1) + ",");
                                                continue forNum;
                                            } else {
                                                tabScheduleOfInsuranceTrue.setTabAgentPay(temp2);
                                            }
                                        } else if (n == 17) {// 备注
                                            tabScheduleOfInsuranceTrue.setTabMessage(temp2);
                                        } else if (n == 18) {// 出单员
                                            if (temp2.equals("")) {
                                                result.append("第" + (m + 1) + "行，第" + (n + 1) + "列: '" + temp2 + "' 出单员为空！<br/>");
                                                sbStr.append((m + 1) + ",");
                                                continue forNum;
                                            } else {
                                                tabScheduleOfInsuranceTrue.setTabCdPerson(temp2);
                                            }
                                        } else if (n == 19) {// 维护人
                                            if (temp2.equals("")) {
                                                result.append("第" + (m + 1) + "行，第" + (n + 1) + "列: '" + temp2 + "' 维护人为空！<br/>");
                                                sbStr.append((m + 1) + ",");
                                                continue forNum;
                                            } else {
                                                tabScheduleOfInsuranceTrue.setTabWhPerson(temp2);
                                            }
                                        } else if (n == 20) {// 客户电话
                                            tabScheduleOfInsuranceTrue.setTabCustomerMobile(temp2);
                                        } else if (n == 21) {// 交强净保费
                                            try {
                                                tabScheduleOfInsuranceTrue.setTabJqjbf(BigDecimal.valueOf(Double.parseDouble(temp2)));
                                            } catch (NumberFormatException e) {
                                                e.printStackTrace();
                                                result.append("第" + (m + 1) + "行，第" + (n + 1) + "列: '" + temp2 + "' 交强净保费格式错误！<br/>");
                                                sbStr.append((m + 1) + ",");
                                                continue forNum;
                                            }
                                        } else if (n == 22) {// 交强费用率
                                            try {
                                                Double aDouble = new Double(temp2.substring(0, temp2.indexOf("%")));
                                                tabScheduleOfInsuranceTrue.setTabJqfyl(BigDecimal.valueOf(aDouble / 100));
                                            } catch (NumberFormatException e) {
                                                e.printStackTrace();
                                                result.append("第" + (m + 1) + "行，第" + (n + 1) + "列: '" + temp2 + "' 交强费用率格式错误！<br/>");
                                                sbStr.append((m + 1) + ",");
                                                continue forNum;
                                            }
                                        } else if (n == 23) {// 交强险费用
                                            try {
                                                tabScheduleOfInsuranceTrue.setTabJqxfy(BigDecimal.valueOf(Double.parseDouble(temp2)));
                                            } catch (NumberFormatException e) {
                                                e.printStackTrace();
                                                result.append("第" + (m + 1) + "行，第" + (n + 1) + "列: '" + temp2 + "' 交强险费用格式错误！<br/>");
                                                sbStr.append((m + 1) + ",");
                                                continue forNum;
                                            }
                                        } else if (n == 24) {// 商业净保费
                                            try {
                                                tabScheduleOfInsuranceTrue.setTabSyjbf(BigDecimal.valueOf(Double.parseDouble(temp2)));
                                            } catch (NumberFormatException e) {
                                                e.printStackTrace();
                                                result.append("第" + (m + 1) + "行，第" + (n + 1) + "列: '" + temp2 + "' 商业净保费格式错误！<br/>");
                                                sbStr.append((m + 1) + ",");
                                                continue forNum;
                                            }
                                        } else if (n == 25) {// 商业费用率
                                            try {
                                                Double aDouble = new Double(temp2.substring(0, temp2.indexOf("%")));
                                                tabScheduleOfInsuranceTrue.setTabSyfyl(BigDecimal.valueOf(aDouble / 100));
                                            } catch (NumberFormatException e) {
                                                e.printStackTrace();
                                                result.append("第" + (m + 1) + "行，第" + (n + 1) + "列: '" + temp2 + "' 商业费用率格式错误！<br/>");
                                                sbStr.append((m + 1) + ",");
                                                continue forNum;
                                            }
                                        } else if (n == 26) {// 商业险费用
                                            try {
                                                tabScheduleOfInsuranceTrue.setTabSyxfy(BigDecimal.valueOf(Double.parseDouble(temp2)));
                                            } catch (NumberFormatException e) {
                                                e.printStackTrace();
                                                result.append("第" + (m + 1) + "行，第" + (n + 1) + "列: '" + temp2 + "' 商业险费用格式错误！<br/>");
                                                sbStr.append((m + 1) + ",");
                                                continue forNum;
                                            }
                                        } else if (n == 27) {// 其他应付
                                            tabScheduleOfInsuranceTrue.setTabCommissionOther(temp2);
                                        } else if (n == 28) {// 佣金合计
                                            try {
                                                tabScheduleOfInsuranceTrue.setTabCommissionAmount(BigDecimal.valueOf(Double.parseDouble(temp2)));
                                                String hql1 = "from TabInsurer where tab_insurer_name=:tabInsurerName";
                                                TabInsurer tabInsurer = (TabInsurer) datadao.getObjects(hql1, new String[]{"tabInsurerName"}, new String[]{tabScheduleOfInsuranceTrue.getTabInsuranceCompanyName()});
                                                if (tabInsurer == null) {
                                                    tabInsurer = new TabInsurer();
                                                    tabInsurer.setTabInsurerName(tabScheduleOfInsuranceTrue.getTabInsuranceCompanyName());
                                                    tabInsurer.setTabWriteDate(new Timestamp(new Date().getTime()));
                                                    tabInsurer.setTabWhPerson(name);
                                                    datadao.addObject(tabInsurer);
                                                }
                                                datadao.addObject(tabScheduleOfInsuranceTrue);
                                                result.append("添加成功！对应的行数：第" + (m + 1) + "行。<br/>");
                                                successNum++;
                                            } catch (NumberFormatException e) {
                                                e.printStackTrace();
                                                result.append("第\" + (m + 1) + \"行，第\" + (n + 1) + \"列: '" + temp2 + "' 佣金合计格式错误！<br/>");
                                                sbStr.append((m + 1) + ",");
                                                continue forNum;
                                            }
                                        }
                                    }
                                }
                                String failRowStr = sbStr.toString().trim();
                                result.append("'" + sheetName + "'：");
                                result.append("添加成功的行数：" + successNum + "<br/>");
                                if (!"".equals(failRowStr)) {// 存在添加失败的记录
                                    result.append("添加失败的行数：" + failRowStr.substring(0, failRowStr.length() - 1) + "<br/>");
//                                    logger.info("添加失败的行数：" + failRowStr);
                                }
                            }
                        } else {
                            result.append("只有一行！！即没有数据<br/>");
                        }
                    } else {// 行列都为0，没有数据
                        result.append("'" + sheetName + "' 为空！<br/>");
                    }
                } else if (rowCount > 0 && columnCount > 0) {// 列数不是29列，不符合要求
                    result.append("'" + sheetName + "' 列数不是29列，不符合格式！<br/>");
                }
            }
            workbook.close();// 关闭
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString().trim();
    }

    @Override
    public List<TabScheduleOfInsuranceTrue> queryAllByObject(int userId, SeachInsurance si,
                                                             int pageSize, int page) {
        // TODO Auto-generated method stub
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式1
        String date = df.format(new Date());

        StringBuffer sql = new StringBuffer("from TabScheduleOfInsuranceTrue where 1=1  ");

//		String  params[]=null;
//		String values[]=null;
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> listValues = new ArrayList<String>();
        if (userId > 0) {
            sql.append(" and tab_user_id=" + userId);
        }
        if (si != null) {
            if (si.getId() != null) {
                sql.append(" and id=:Id");
                list.add("Id");
                listValues.add(String.valueOf(si.getId()));

            }
			/*sql.append(" and  tab_start_date  >=:stime  and tab_start_date<=:date ");
			 list.add("stime");	
			 list.add("date");
			 listValues.add(String.valueOf(si.getTabStartDateStart()==null?"1800-01-01":si.getTabStartDateStart()));
			 listValues.add(String.valueOf(si.getTabStartDateEnd()==null?new Timestamp(System.currentTimeMillis()):si.getTabStartDateEnd()));
			
		        */
            sql.append(" and  tab_end_date  >=:TabEndDateStart and tab_end_date<=:TabEndDateEnd ");
            list.add("TabEndDateStart");
            list.add("TabEndDateEnd");
            listValues.add(String.valueOf(si.getTabEndDateStart() == null ? "1800-01-01" : si.getTabEndDateStart()));
            listValues.add(String.valueOf(si.getTabEndDateEnd() == null ? "4040-01-01" : si.getTabEndDateEnd()));


            sql.append(" and  tab_qd_date  >=:TabQdDateStart and tab_qd_date <=:TabQdDateEnd  ");
            list.add("TabQdDateStart");
            list.add("TabQdDateEnd");
            listValues.add(String.valueOf(si.getTabQdDateStart() == null ? "1800-01-01" : si.getTabQdDateStart()));
            listValues.add(String.valueOf(si.getTabQdDateEnd() == null ? "4040-01-01" : si.getTabQdDateEnd()));


            sql.append(" and  tab_write_date >=:TabWriteDateStart and tab_write_date<=:TabWriteDateEnd  ");
            list.add("TabWriteDateStart");
            list.add("TabWriteDateEnd");
            listValues.add(String.valueOf(si.getTab_write_date_start() == null ? "1800-01-01" : si.getTab_write_date_start()));
            listValues.add(String.valueOf(si.getTab_write_date_end() == null ? new Timestamp(System.currentTimeMillis()) : si.getTab_write_date_end()));

            sql.append(" and  tab_start_date >=:tabStartDateStart and tab_start_date<=:tabStartDateEnd  ");
            list.add("tabStartDateStart");
            list.add("tabStartDateEnd");
            listValues.add(String.valueOf(si.getTabStartDateStart() == null ? "1800-01-01" : si.getTabStartDateStart()));
            listValues.add(String.valueOf(si.getTabStartDateEnd() == null ? "4040-01-01" : si.getTabStartDateEnd()));

            sql.append(" and  tab_pay_date >=:tabPayDateStart and tab_pay_date<=:tabPayDateEnd  ");
            list.add("tabPayDateStart");
            list.add("tabPayDateEnd");
            listValues.add(String.valueOf(si.getTabPayDateStart() == null ? "1800-01-01" : si.getTabPayDateStart()));
            listValues.add(String.valueOf(si.getTabPayDateEnd() == null ? "4040-01-01" : si.getTabPayDateEnd()));

            if (si.getTabCarNum() != null) {
                sql.append(" and   tab_car_num=:tabCarNum");
                list.add("tabCarNum");
                listValues.add(String.valueOf(si.getTabCarNum()));
            }
            if (si.getTabIsRenewal() != null) {
                sql.append(" and   tab_isRenewal=:tabIsRenewal");
                list.add("tabIsRenewal");
                listValues.add(String.valueOf(si.getTabIsRenewal()));
            }
            if (si.getTabCarNum() != null) {
                sql.append(" and   tab_car_num=:tabCarNum");
                list.add("tabCarNum");
                listValues.add(String.valueOf(si.getTabCarNum()));
            }
            if (si.getTabAgentPay() != null) {
                sql.append(" and  tab_agent_pay=:tabAgentPay");
                list.add("tabAgentPay");
                listValues.add(String.valueOf(si.getTabAgentPay()));
            }
            if (si.getTabYxState() != null) {
                sql.append(" and  tab_yx_state=:TabYxState");
                list.add("TabYxState");
                listValues.add(String.valueOf(si.getTabYxState()));
            }
            if (si.getTabCarState() != null) {
                sql.append(" and  tab_car_state=:TabCarState");
                list.add("TabCarState");
                listValues.add(String.valueOf(si.getTabCarState()));
            }
            if (si.getTabChannel() != null) {
                sql.append(" and  tab_channel=:TabChannel");
                list.add("TabChannel");
                listValues.add(String.valueOf(si.getTabChannel()));
            }
            if (si.getTabInsuranceCompanyName() != null) {
                sql.append(" and  tab_insurance_company_name=:TabInsuranceCompanyName");
                list.add("TabInsuranceCompanyName");
                listValues.add(String.valueOf(si.getTabInsuranceCompanyName()));
            }
            if (si.getTabCdPerson() != null) {
                sql.append(" and  tab_cd_person=:TabCdPerson");
                list.add("TabCdPerson");
                listValues.add(String.valueOf(si.getTabCdPerson()));
            }
            if (si.getTabWhPerson() != null) {
                sql.append(" and  tab_manager_user=:TabWhPerson");
                list.add("TabWhPerson");
                listValues.add(String.valueOf(si.getTabWhPerson()));
            }
            if (si.getTabAgentName() != null) {
                sql.append(" and  tab_agent_name like :TabAgentName");
                list.add("TabAgentName");
                listValues.add(String.valueOf("%" + si.getTabAgentName() + "%"));
            }
            if (si.getTabTeamOwnership() != null) {
                sql.append(" and  tab_team_ownership=:TabTeamOwnership");
                list.add("TabTeamOwnership");
                listValues.add(String.valueOf(si.getTabTeamOwnership()));
            }
            if (si.getTabAmountSate() != null) {
                sql.append(" and  tab_amount_sate=:tabAmountSate");
                list.add("tabAmountSate");
                listValues.add(String.valueOf(si.getTabAmountSate()));
            }
            if (si.getTabIsAuditing() != null) {
                sql.append(" and  tab_is_auditing=:tabIsAuditing");
                list.add("tabIsAuditing");
                listValues.add(String.valueOf(si.getTabIsAuditing()));
            }
        }

        sql.append(" order by  tab_write_date desc  ");
        if (list.isEmpty()) {
            @SuppressWarnings("unchecked")
            List<TabScheduleOfInsuranceTrue> list1 = (List<TabScheduleOfInsuranceTrue>) datadao.pageQueryViaParam1(sql.toString(), pageSize, page, null, null);
            return list1;
        }
        String params[] = new String[list.size()];
        String values[] = new String[list.size()];
        for (int i = 0; i < params.length; i++) {
            params[i] = list.get(i);
            values[i] = listValues.get(i);
        }

        String sqlStr = sql.toString();
        System.out.println(sqlStr);

        @SuppressWarnings("unchecked")
        List<TabScheduleOfInsuranceTrue> list1 = (List<TabScheduleOfInsuranceTrue>) datadao.pageQueryViaParam1(sqlStr, pageSize, page, params, values);


//		private Integer id;
//		private Timestamp tabStartDate;//批单日期
//		private Timestamp tabEndDate;//到期日
//		private Timestamp tabQdDate;//签单日期
//		private String tabCarNum;//车牌
//		private String tabSafePerson;//被保险人				
//		private String tabYxState;//业务性质保批单		
//		private String tabCarState;//车辆使用性质
//		private String tabChannel;//渠道归属
//		private String tabInsuranceCompanyName;//保险公司名字
//		private String tabCdPerson;//出单人
//		private String tabWhPerson;//维护人
//		private String tabAgentName;//代理人姓名
//		private String tabAmountSate;//收款状态
//		private String tabTeamOwnership;//团队归属						
        return list1;
    }

    @Override
    public List<TabScheduleOfInsuranceTrue> queryAllByObjectAll(int userId, SeachInsurance si,
                                                                int pageSize, int page) {
        // TODO Auto-generated method stub
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式1
        String date = df.format(new Date());
        StringBuffer sql = new StringBuffer("");
//		   sql.append("SELECT id,tabAgentName,tabAgentPay,tabAllAmount,tabAmountSate,tabAnnotations,tabAnnotationsPerson,tabBankNum,tabCarNum,tabCarState,tabCardState,tabCdPerson,tabChannel,tabCommercialInsurance,tabCommissionOther,tabCompulsoryInsurance,tabCustomerMobile,tabEndDate,tabIllustrate,tabInsuranceCompanyName,tabIsAuditing,tabIsPayAmount,tabJbfjql,tabJbfsyl,tabJfysAmount,tabManagerUser,tabMessage,tabRealAmount,tabSafePerson,tabStartDate,tabTax,tabTeamOwnership,tabUserId,tabWhPerson,tabWriteDate,tabXlsxName,tabYgzhjqAmount,tabYgzhsyAmount,tabYxState,tabZbfJ ,realOpenPay ");
//		   sql.append("FROM	(SELECT ");
//		   sql.append("id  as id,tab_agent_name  as tabAgentName,tab_agent_pay as tabAgentPay,tab_all_amount as tabAllAmount,tab_amount_sate as tabAmountSate,tab_annotations as tabAnnotations,tab_annotations_person as tabAnnotationsPerson,tab_bank_num as tabBankNum,tab_car_num as tabCarNum,tab_car_state as tabCarState,tab_card_state as tabCardState,tab_cd_person as tabCdPerson,tab_channel as tabChannel,tab_commercial_insurance as tabCommercialInsurance,tab_commission_amount as tabCommissionAmount,tab_commission_other as tabCommissionOther,tab_compulsory_insurance as tabCompulsoryInsurance,tab_customer_mobile as tabCustomerMobile,tab_end_date as tabEndDate,tab_illustrate as tabIllustrate,tab_insurance_company_name as tabInsuranceCompanyName,tab_is_auditing as tabIsAuditing,tab_is_pay_amount as tabIsPayAmount,tab_jbfjql as tabJbfjql,tabJbfsyl as tabJbfsyl,tab_jfys_amount as tabJfysAmount,tab_manager_user as tabManagerUser,tab_message as tabMessage,tab_qd_date as tabQdDate,tab_real_amount as tabRealAmount,tab_safe_person as tabSafePerson,tab_start_date as tabStartDate,tab_tax as tabTax,tab_team_ownership as tabTeamOwnership,tab_user_id as tabUserId,tab_wh_person as tabWhPerson,tab_write_date as tabWriteDate,tab_xlsx_name as tabXlsxName,tab_ygzhjq_amount as tabYgzhjqAmount,tab_ygzhsy_amount as tabYgzhsyAmount,tab_yx_state as tabYxState,tab_zbf_j as tabZbfJ ,tab_real_open_pay as realOpenPay ");
//		   sql.append("FROM tab_schedule_of_insurance UNION ALL ");
//		   sql.append(" SELECT ");
//		   sql.append("id  as id,tab_agent_name  as tabAgentName,tab_agent_pay as tabAgentPay,tab_all_amount as tabAllAmount,tab_amount_sate as tabAmountSate,tab_annotations as tabAnnotations,tab_annotations_person as tabAnnotationsPerson,tab_bank_num as tabBankNum,tab_car_num as tabCarNum,tab_car_state as tabCarState,tab_card_state as tabCardState,tab_cd_person as tabCdPerson,tab_channel as tabChannel,tab_commercial_insurance as tabCommercialInsurance,tab_commission_amount as tabCommissionAmount,tab_commission_other as tabCommissionOther,tab_compulsory_insurance as tabCompulsoryInsurance,tab_customer_mobile as tabCustomerMobile,tab_end_date as tabEndDate,tab_illustrate as tabIllustrate,tab_insurance_company_name as tabInsuranceCompanyName,tab_is_auditing as tabIsAuditing,tab_is_pay_amount as tabIsPayAmount,tab_jbfjql as tabJbfjql,tabJbfsyl as tabJbfsyl,tab_jfys_amount as tabJfysAmount,tab_manager_user as tabManagerUser,tab_message as tabMessage,tab_qd_date as tabQdDate,tab_real_amount as tabRealAmount,tab_safe_person as tabSafePerson,tab_start_date as tabStartDate,tab_tax as tabTax,tab_team_ownership as tabTeamOwnership,tab_user_id as tabUserId,tab_wh_person as tabWhPerson,tab_write_date as tabWriteDate,tab_xlsx_name as tabXlsxName,tab_ygzhjq_amount as tabYgzhjqAmount,tab_ygzhsy_amount as tabYgzhsyAmount,tab_yx_state as tabYxState,tab_zbf_j as tabZbfJ ,tab_real_open_pay as realOpenPay "); 
//		   sql.append("FROM tab_schedule_of_insurance_true ) TabScheduleOfInsuranceTrue");SELECT id,tabAgentName FROM(

        sql.append("SELECT * FROM (SELECT * FROM tab_schedule_of_insurance UNION ALL select * FROM tab_schedule_of_insurance_true) tab_schedule_of_insurance_true where 1=1");
        //sql.append(" FROM tab_schedule_of_insurance UNION ALL SELECT id  as id, tab_agent_name  as tabAgentName");
        // sql.append("   FROM tab_schedule_of_insurance_true ) TabScheduleOfInsuranceTrue ");
//		String  params[]=null;
//		String values[]=null;
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> listValues = new ArrayList<String>();
        if (userId > 0) {
            sql.append(" and tab_user_id='" + userId + "'");
        }
        if (si != null) {
            if (si.getId() != null) {
                sql.append(" and id='" + String.valueOf(si.getId()) + "'");
            }
			/*sql.append(" and  tab_start_date  >=:stime  and tab_start_date<=:date ");
			 list.add("stime");	
			 list.add("date");
			 listValues.add(String.valueOf(si.getTabStartDateStart()==null?"1800-01-01":si.getTabStartDateStart()));
			 listValues.add(String.valueOf(si.getTabStartDateEnd()==null?new Timestamp(System.currentTimeMillis()):si.getTabStartDateEnd()));
			
		        */
            sql.append(" and  tab_end_date  >='" + String.valueOf(si.getTabEndDateStart() == null ? "1800-01-01" : si.getTabEndDateStart()) + "' and tab_end_date<='" + String.valueOf(si.getTabEndDateEnd() == null ? "4040-01-01" : si.getTabEndDateEnd()) + "' ");
            sql.append(" and  tab_qd_date  >='" + String.valueOf(si.getTabQdDateStart() == null ? "1800-01-01" : si.getTabQdDateStart()) + "' and tab_qd_date <='" + String.valueOf(si.getTabQdDateEnd() == null ? "4040-01-01" : si.getTabQdDateEnd()) + "'  ");
            sql.append(" and  tab_write_date >='" + String.valueOf(si.getTab_write_date_start() == null ? "1800-01-01" : si.getTab_write_date_start()) + "' and tab_write_date<='" + String.valueOf(si.getTab_write_date_end() == null ? new Timestamp(System.currentTimeMillis()) : si.getTab_write_date_end()) + "'");
            if (si.getTabCarNum() != null) {
                sql.append(" and   tab_car_num='" + String.valueOf(si.getTabCarNum()) + "'");
            }
            if (si.getTabSafePerson() != null) {
                sql.append(" and  tab_safe_person='" + String.valueOf(si.getTabSafePerson()) + "'");
            }
            if (si.getTabYxState() != null) {
                sql.append(" and  tab_yx_state='" + String.valueOf(si.getTabYxState()) + "'");
            }
            if (si.getTabCarState() != null) {
                sql.append(" and  tab_car_state='" + String.valueOf(si.getTabCarState()) + "'");
            }
            if (si.getTabChannel() != null) {
                sql.append(" and  tab_channel='" + String.valueOf(si.getTabChannel()) + "'");
            }
            if (si.getTabInsuranceCompanyName() != null) {
                sql.append(" and  tab_insurance_company_name='" + String.valueOf(si.getTabInsuranceCompanyName()) + "'");
            }
            if (si.getTabCdPerson() != null) {
                sql.append(" and  tab_cd_person='" + String.valueOf(si.getTabCdPerson()) + "'");
            }
            if (si.getTabWhPerson() != null) {
                sql.append(" and  tab_manager_user='" + String.valueOf(si.getTabWhPerson()) + "'");
            }
            if (si.getTabAgentName() != null) {
                sql.append(" and  tab_agent_name like " + String.valueOf("'%" + si.getTabAgentName() + "%'"));

            }
            if (si.getTabTeamOwnership() != null) {
                sql.append(" and  tab_team_ownership='" + String.valueOf(si.getTabTeamOwnership()) + "'");
            }
            if (si.getTabAmountSate() != null) {
                sql.append(" and  tab_amount_sate='" + String.valueOf(si.getTabAmountSate()) + "'");
            }
            if (si.getTabIsAuditing() != null) {
                sql.append(" and  tab_is_auditing='" + String.valueOf(si.getTabIsAuditing()) + "'");
            }
        }

        sql.append(" order by  tab_write_date desc  limit " + (page - 1) * 10 + "," + pageSize);

        @SuppressWarnings("unchecked")
        List<TabScheduleOfInsuranceTrue> list1 = (List<TabScheduleOfInsuranceTrue>) datadao.getObjectList(sql.toString());
//		private Integer id;
//		private Timestamp tabStartDate;//批单日期
//		private Timestamp tabEndDate;//到期日
//		private Timestamp tabQdDate;//签单日期
//		private String tabCarNum;//车牌
//		private String tabSafePerson;//被保险人				
//		private String tabYxState;//业务性质保批单		
//		private String tabCarState;//车辆使用性质
//		private String tabChannel;//渠道归属
//		private String tabInsuranceCompanyName;//保险公司名字
//		private String tabCdPerson;//出单人
//		private String tabWhPerson;//维护人
//		private String tabAgentName;//代理人姓名
//		private String tabAmountSate;//收款状态
//		private String tabTeamOwnership;//团队归属						
        return list1;
    }


    @Override
    public List<TabScheduleOfInsuranceTrue> queryAllByObject(int userId,
                                                             SeachInsurance si) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteScheduleOfInsuranceTrue(TabScheduleOfInsuranceTrue toit) {
        // TODO Auto-generated method stub
        datadao.deleteObject(toit);
    }

    @Override
    public List<TabScheduleOfInsuranceTrue> queryAllByObjectqueryWaitingPay(
            SeachInsurance si) {
        // TODO Auto-generated method stub
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());

        StringBuffer sql = new StringBuffer("from TabScheduleOfInsuranceTrue where 1=1 and tabAgentPay='未结算' ");
//		String  params[]=null;
//		String values[]=null;
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> listValues = new ArrayList<String>();

        if (si != null) {
            if (si.getId() != null) {
                sql.append(" and id=:Id");
                list.add("Id");
                listValues.add(String.valueOf(si.getId()));

            }
			/*sql.append(" and  tab_start_date  >=:stime  and tab_start_date<=:date ");
			 list.add("stime");	
			 list.add("date");
			 listValues.add(String.valueOf(si.getTabStartDateStart()==null?"1800-01-01":si.getTabStartDateStart()));
			 listValues.add(String.valueOf(si.getTabStartDateEnd()==null?new Timestamp(System.currentTimeMillis()):si.getTabStartDateEnd()));
			*/

            sql.append(" and  tab_end_date  >=:TabEndDateStart and tab_end_date<=:TabEndDateEnd ");
            list.add("TabEndDateStart");
            list.add("TabEndDateEnd");
            listValues.add(String.valueOf(si.getTabEndDateStart() == null ? "1800-01-01" : si.getTabEndDateStart()));
            listValues.add(String.valueOf(si.getTabEndDateEnd() == null ? "4040-01-01" : si.getTabEndDateEnd()));


            sql.append(" and  tab_qd_date  >=:TabQdDateStart and tab_qd_date <=:TabQdDateEnd  ");
            list.add("TabQdDateStart");
            list.add("TabQdDateEnd");
            listValues.add(String.valueOf(si.getTabQdDateStart() == null ? "1800-01-01" : si.getTabQdDateStart()));
            listValues.add(String.valueOf(si.getTabQdDateEnd() == null ? "4040-01-01" : si.getTabQdDateEnd()));


            sql.append(" and  tab_write_date >=:TabWriteDateStart and tab_write_date<=:TabWriteDateEnd  ");
            list.add("TabWriteDateStart");
            list.add("TabWriteDateEnd");
            listValues.add(String.valueOf(si.getTab_write_date_start() == null ? "1800-01-01" : si.getTab_write_date_start()));
            listValues.add(String.valueOf(si.getTab_write_date_end() == null ? new Timestamp(System.currentTimeMillis()) : si.getTab_write_date_end()));

            if (si.getTabCarNum() != null) {
                sql.append(" and   tab_car_num=:tabCarNum");
                list.add("tabCarNum");

                listValues.add(String.valueOf(si.getTabCarNum()));


            }
            if (si.getTabSafePerson() != null) {
                sql.append(" and  tab_safe_person=:TabSafePerson");
                list.add("TabSafePerson");

                listValues.add(String.valueOf(si.getTabSafePerson()));

            }
            if (si.getTabYxState() != null) {
                sql.append(" and  tab_yx_state=:TabYxState");
                list.add("TabYxState");

                listValues.add(String.valueOf(si.getTabYxState()));


            }
            if (si.getTabCarState() != null) {
                sql.append(" and  tab_car_state=:TabCarState");
                list.add("TabCarState");

                listValues.add(String.valueOf(si.getTabCarState()));

            }
            if (si.getTabChannel() != null) {
                sql.append(" and  tab_channel=:TabChannel");
                list.add("TabChannel");

                listValues.add(String.valueOf(si.getTabChannel()));

            }
            if (si.getTabInsuranceCompanyName() != null) {
                sql.append(" and  tab_insurance_company_name=:TabInsuranceCompanyName");
                list.add("TabInsuranceCompanyName");
                listValues.add(String.valueOf(si.getTabInsuranceCompanyName()));

            }
            if (si.getTabCdPerson() != null) {
                sql.append(" and  tab_cd_person=:TabCdPerson");
                list.add("TabCdPerson");

                listValues.add(String.valueOf(si.getTabCdPerson()));

            }
            if (si.getTabWhPerson() != null) {
                sql.append(" and  tab_manager_user=:TabWhPerson");
                list.add("TabWhPerson");
                listValues.add(String.valueOf(si.getTabWhPerson()));

            }
            if (si.getTabAgentName() != null) {
                sql.append(" and  tab_agent_name=:TabAgentName");
                list.add("TabAgentName");
                listValues.add(String.valueOf(si.getTabAgentName()));

            }
            if (si.getTabTeamOwnership() != null) {
                sql.append(" and  tab_team_ownership=:TabTeamOwnership");
                list.add("TabTeamOwnership");
                listValues.add(String.valueOf(si.getTabTeamOwnership()));
            }
        }

        sql.append(" order by  tab_write_date desc");
        if (list.isEmpty()) {
            @SuppressWarnings("unchecked")
            List<TabScheduleOfInsuranceTrue> list1 = (List<TabScheduleOfInsuranceTrue>) datadao.getAllObjects1(sql.toString(), null, null);
            return list1;
        }
        String params[] = new String[list.size()];
        String values[] = new String[list.size()];
        for (int i = 0; i < params.length; i++) {
            params[i] = list.get(i);
            values[i] = listValues.get(i);
        }

        String sqlStr = sql.toString();
        System.out.println(sqlStr);

        @SuppressWarnings("unchecked")
        List<TabScheduleOfInsuranceTrue> list1 = (List<TabScheduleOfInsuranceTrue>) datadao.getAllObjects1(sqlStr, params, values);
//		private Integer id;
//		private Timestamp tabStartDate;//批单日期
//		private Timestamp tabEndDate;//到期日
//		private Timestamp tabQdDate;//签单日期
//		private String tabCarNum;//车牌
//		private String tabSafePerson;//被保险人				
//		private String tabYxState;//业务性质保批单		
//		private String tabCarState;//车辆使用性质
//		private String tabChannel;//渠道归属
//		private String tabInsuranceCompanyName;//保险公司名字
//		private String tabCdPerson;//出单人
//		private String tabWhPerson;//维护人
//		private String tabAgentName;//代理人姓名
//		private String tabAmountSate;//收款状态
//		private String tabTeamOwnership;//团队归属						
        return list1;
    }


    @Override
    public List<TabScheduleOfInsuranceTrue> queryAllByObjectWaitingPay(
            int userId, SeachInsurance si, int pageSize, int page) {
        // TODO Auto-generated method stub
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());

        StringBuffer sql = new StringBuffer("from TabScheduleOfInsuranceTrue  where 1=1 and  tabAgentPay='未结算'  ");
//		String  params[]=null;
//		String values[]=null;
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> listValues = new ArrayList<String>();
        if (userId > 0) {
            sql.append(" and tab_user_id=" + userId);
        }
        if (si != null) {
            if (si.getId() != null) {
                sql.append(" and id=:Id");
                list.add("Id");
                listValues.add(String.valueOf(si.getId()));

            }
		/*	sql.append(" and  tab_start_date  >=:stime  and tab_start_date<=:date ");
			 list.add("stime");	
			 list.add("date");
			 listValues.add(String.valueOf(si.getTabStartDateStart()==null?"1800-01-01":si.getTabStartDateStart()));
			 listValues.add(String.valueOf(si.getTabStartDateEnd()==null?new Timestamp(System.currentTimeMillis()):si.getTabStartDateEnd()));
			*/

            sql.append(" and  tab_end_date  >=:TabEndDateStart and tab_end_date<=:TabEndDateEnd ");
            list.add("TabEndDateStart");
            list.add("TabEndDateEnd");
            listValues.add(String.valueOf(si.getTabEndDateStart() == null ? "1800-01-01" : si.getTabEndDateStart()));
            listValues.add(String.valueOf(si.getTabEndDateEnd() == null ? "4040-01-01" : si.getTabEndDateEnd()));


            sql.append(" and  tab_qd_date  >=:TabQdDateStart and tab_qd_date <=:TabQdDateEnd  ");
            list.add("TabQdDateStart");
            list.add("TabQdDateEnd");
            listValues.add(String.valueOf(si.getTabQdDateStart() == null ? "1800-01-01" : si.getTabQdDateStart()));
            listValues.add(String.valueOf(si.getTabQdDateEnd() == null ? "4040-01-01" : si.getTabQdDateEnd()));


            sql.append(" and  tab_write_date >=:TabWriteDateStart and tab_write_date<=:TabWriteDateEnd  ");
            list.add("TabWriteDateStart");
            list.add("TabWriteDateEnd");
            listValues.add(String.valueOf(si.getTab_write_date_start() == null ? "1800-01-01" : si.getTab_write_date_start()));
            listValues.add(String.valueOf(si.getTab_write_date_end() == null ? new Timestamp(System.currentTimeMillis()) : si.getTab_write_date_end()));

            if (si.getTabCarNum() != null) {
                sql.append(" and   tab_car_num=:tabCarNum");
                list.add("tabCarNum");

                listValues.add(String.valueOf(si.getTabCarNum()));


            }
            if (si.getTabSafePerson() != null) {
                sql.append(" and  tab_safe_person=:TabSafePerson");
                list.add("TabSafePerson");

                listValues.add(String.valueOf(si.getTabSafePerson()));

            }
            if (si.getTabYxState() != null) {
                sql.append(" and  tab_yx_state=:TabYxState");
                list.add("TabYxState");

                listValues.add(String.valueOf(si.getTabYxState()));


            }
            if (si.getTabCarState() != null) {
                sql.append(" and  tab_car_state=:TabCarState");
                list.add("TabCarState");

                listValues.add(String.valueOf(si.getTabCarState()));

            }
            if (si.getTabChannel() != null) {
                sql.append(" and  tab_channel=:TabChannel");
                list.add("TabChannel");

                listValues.add(String.valueOf(si.getTabChannel()));

            }
            if (si.getTabInsuranceCompanyName() != null) {
                sql.append(" and  tab_insurance_company_name=:TabInsuranceCompanyName");
                list.add("TabInsuranceCompanyName");
                listValues.add(String.valueOf(si.getTabInsuranceCompanyName()));

            }
            if (si.getTabCdPerson() != null) {
                sql.append(" and  tab_cd_person=:TabCdPerson");
                list.add("TabCdPerson");

                listValues.add(String.valueOf(si.getTabCdPerson()));

            }
            if (si.getTabWhPerson() != null) {
                sql.append(" and  tab_manager_user=:TabWhPerson");
                list.add("TabWhPerson");
                listValues.add(String.valueOf(si.getTabWhPerson()));

            }
            if (si.getTabAgentName() != null) {
                sql.append(" and  tab_agent_name like:TabAgentName");
                list.add("TabAgentName");
                listValues.add(String.valueOf("'%" + si.getTabAgentName() + "%'"));

            }
            if (si.getTabTeamOwnership() != null) {
                sql.append(" and  tab_team_ownership=:TabTeamOwnership");
                list.add("TabTeamOwnership");
                listValues.add(String.valueOf(si.getTabTeamOwnership()));
            }
        }

        sql.append(" order by  tab_write_date desc");
        if (list.isEmpty()) {
            @SuppressWarnings("unchecked")
            List<TabScheduleOfInsuranceTrue> list1 = (List<TabScheduleOfInsuranceTrue>) datadao.pageQueryViaParam1(sql.toString(), pageSize, page, null, null);
            return list1;
        }
        String params[] = new String[list.size()];
        String values[] = new String[list.size()];
        for (int i = 0; i < params.length; i++) {
            params[i] = list.get(i);
            values[i] = listValues.get(i);
        }

        String sqlStr = sql.toString();
        System.out.println(sqlStr);

        @SuppressWarnings("unchecked")
        List<TabScheduleOfInsuranceTrue> list1 = (List<TabScheduleOfInsuranceTrue>) datadao.pageQueryViaParam1(sqlStr, pageSize, page, params, values);
//		private Integer id;
//		private Timestamp tabStartDate;//批单日期
//		private Timestamp tabEndDate;//到期日
//		private Timestamp tabQdDate;//签单日期
//		private String tabCarNum;//车牌
//		private String tabSafePerson;//被保险人				
//		private String tabYxState;//业务性质保批单		
//		private String tabCarState;//车辆使用性质
//		private String tabChannel;//渠道归属
//		private String tabInsuranceCompanyName;//保险公司名字
//		private String tabCdPerson;//出单人
//		private String tabWhPerson;//维护人
//		private String tabAgentName;//代理人姓名
//		private String tabAmountSate;//收款状态
//		private String tabTeamOwnership;//团队归属						
        return list1;

    }

    @Override
    public List<TabScheduleOfInsuranceTrue> queryAllByObjectqueryWaitingOpenPay(
            SeachInsurance si) {
        // TODO Auto-generated method stub
        // TODO Auto-generated method stub
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());

        StringBuffer sql = new StringBuffer("from TabScheduleOfInsuranceTrue where 1=1 and tab_real_open_pay_state='待收款'  ");
//		String  params[]=null;
//		String values[]=null;
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> listValues = new ArrayList<String>();

        if (si != null) {
            if (si.getId() != null) {
                sql.append(" and id=:Id");
                list.add("Id");
                listValues.add(String.valueOf(si.getId()));

            }
			/*sql.append(" and  tab_start_date  >=:stime  and tab_start_date<=:date ");
			 list.add("stime");	
			 list.add("date");
			 listValues.add(String.valueOf(si.getTabStartDateStart()==null?"1800-01-01":si.getTabStartDateStart()));
			 listValues.add(String.valueOf(si.getTabStartDateEnd()==null?new Timestamp(System.currentTimeMillis()):si.getTabStartDateEnd()));
			*/

            sql.append(" and  tab_end_date  >=:TabEndDateStart and tab_end_date<=:TabEndDateEnd ");
            list.add("TabEndDateStart");
            list.add("TabEndDateEnd");
            listValues.add(String.valueOf(si.getTabEndDateStart() == null ? "1800-01-01" : si.getTabEndDateStart()));
            listValues.add(String.valueOf(si.getTabEndDateEnd() == null ? "4040-01-01" : si.getTabEndDateEnd()));


            sql.append(" and  tab_qd_date  >=:TabQdDateStart and tab_qd_date <=:TabQdDateEnd  ");
            list.add("TabQdDateStart");
            list.add("TabQdDateEnd");
            listValues.add(String.valueOf(si.getTabQdDateStart() == null ? "1800-01-01" : si.getTabQdDateStart()));
            listValues.add(String.valueOf(si.getTabQdDateEnd() == null ? "4040-01-01" : si.getTabQdDateEnd()));


            sql.append(" and  tab_write_date >=:TabWriteDateStart and tab_write_date<=:TabWriteDateEnd  ");
            list.add("TabWriteDateStart");
            list.add("TabWriteDateEnd");
            listValues.add(String.valueOf(si.getTab_write_date_start() == null ? "1800-01-01" : si.getTab_write_date_start()));
            listValues.add(String.valueOf(si.getTab_write_date_end() == null ? new Timestamp(System.currentTimeMillis()) : si.getTab_write_date_end()));

            if (si.getTabCarNum() != null) {
                sql.append(" and   tab_car_num=:tabCarNum");
                list.add("tabCarNum");

                listValues.add(String.valueOf(si.getTabCarNum()));


            }
            if (si.getTabSafePerson() != null) {
                sql.append(" and  tab_safe_person=:TabSafePerson");
                list.add("TabSafePerson");

                listValues.add(String.valueOf(si.getTabSafePerson()));

            }
            if (si.getTabYxState() != null) {
                sql.append(" and  tab_yx_state=:TabYxState");
                list.add("TabYxState");

                listValues.add(String.valueOf(si.getTabYxState()));


            }
            if (si.getTabCarState() != null) {
                sql.append(" and  tab_car_state=:TabCarState");
                list.add("TabCarState");

                listValues.add(String.valueOf(si.getTabCarState()));

            }
            if (si.getTabChannel() != null) {
                sql.append(" and  tab_channel=:TabChannel");
                list.add("TabChannel");

                listValues.add(String.valueOf(si.getTabChannel()));

            }
            if (si.getTabInsuranceCompanyName() != null) {
                sql.append(" and  tab_insurance_company_name=:TabInsuranceCompanyName");
                list.add("TabInsuranceCompanyName");
                listValues.add(String.valueOf(si.getTabInsuranceCompanyName()));

            }
            if (si.getTabCdPerson() != null) {
                sql.append(" and  tab_cd_person=:TabCdPerson");
                list.add("TabCdPerson");

                listValues.add(String.valueOf(si.getTabCdPerson()));

            }
            if (si.getTabWhPerson() != null) {
                sql.append(" and  tab_manager_user=:TabWhPerson");
                list.add("TabWhPerson");
                listValues.add(String.valueOf(si.getTabWhPerson()));

            }
            if (si.getTabAgentName() != null) {
                sql.append(" and  tab_agent_name=:TabAgentName");
                list.add("TabAgentName");
                listValues.add(String.valueOf(si.getTabAgentName()));

            }
            if (si.getTabTeamOwnership() != null) {
                sql.append(" and  tab_team_ownership=:TabTeamOwnership");
                list.add("TabTeamOwnership");
                listValues.add(String.valueOf(si.getTabTeamOwnership()));
            }
        }

        sql.append(" order by  tab_write_date desc");
        if (list.isEmpty()) {
            @SuppressWarnings("unchecked")
            List<TabScheduleOfInsuranceTrue> list1 = (List<TabScheduleOfInsuranceTrue>) datadao.getAllObjects1(sql.toString(), null, null);
            return list1;
        }
        String params[] = new String[list.size()];
        String values[] = new String[list.size()];
        for (int i = 0; i < params.length; i++) {
            params[i] = list.get(i);
            values[i] = listValues.get(i);
        }

        String sqlStr = sql.toString();
        System.out.println(sqlStr);

        @SuppressWarnings("unchecked")
        List<TabScheduleOfInsuranceTrue> list1 = (List<TabScheduleOfInsuranceTrue>) datadao.getAllObjects1(sqlStr, params, values);
//		private Integer id;
//		private Timestamp tabStartDate;//批单日期
//		private Timestamp tabEndDate;//到期日
//		private Timestamp tabQdDate;//签单日期
//		private String tabCarNum;//车牌
//		private String tabSafePerson;//被保险人				
//		private String tabYxState;//业务性质保批单		
//		private String tabCarState;//车辆使用性质
//		private String tabChannel;//渠道归属
//		private String tabInsuranceCompanyName;//保险公司名字
//		private String tabCdPerson;//出单人
//		private String tabWhPerson;//维护人
//		private String tabAgentName;//代理人姓名
//		private String tabAmountSate;//收款状态
//		private String tabTeamOwnership;//团队归属						
        return list1;
    }


    @Override
    public List<TabScheduleOfInsuranceTrue> queryAllByObjectWaitingOpenPay(
            int userId, SeachInsurance si, int pageSize, int page) {
        // TODO Auto-generated method stub
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());

        StringBuffer sql = new StringBuffer("from TabScheduleOfInsuranceTrue  where 1=1 and  tab_real_open_pay_state='待收款'  ");
//		String  params[]=null;
//		String values[]=null;
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> listValues = new ArrayList<String>();
        if (userId > 0) {
            sql.append(" and tab_user_id=" + userId);
        }
        if (si != null) {
            if (si.getId() != null) {
                sql.append(" and id=:Id");
                list.add("Id");
                listValues.add(String.valueOf(si.getId()));

            }
		/*	sql.append(" and  tab_start_date  >=:stime  and tab_start_date<=:date ");
			 list.add("stime");	
			 list.add("date");
			 listValues.add(String.valueOf(si.getTabStartDateStart()==null?"1800-01-01":si.getTabStartDateStart()));
			 listValues.add(String.valueOf(si.getTabStartDateEnd()==null?new Timestamp(System.currentTimeMillis()):si.getTabStartDateEnd()));
			*/

            sql.append(" and  tab_end_date  >=:TabEndDateStart and tab_end_date<=:TabEndDateEnd ");
            list.add("TabEndDateStart");
            list.add("TabEndDateEnd");
            listValues.add(String.valueOf(si.getTabEndDateStart() == null ? "1800-01-01" : si.getTabEndDateStart()));
            listValues.add(String.valueOf(si.getTabEndDateEnd() == null ? "4040-01-01" : si.getTabEndDateEnd()));


            sql.append(" and  tab_qd_date  >=:TabQdDateStart and tab_qd_date <=:TabQdDateEnd  ");
            list.add("TabQdDateStart");
            list.add("TabQdDateEnd");
            listValues.add(String.valueOf(si.getTabQdDateStart() == null ? "1800-01-01" : si.getTabQdDateStart()));
            listValues.add(String.valueOf(si.getTabQdDateEnd() == null ? "4040-01-01" : si.getTabQdDateEnd()));


            sql.append(" and  tab_write_date >=:TabWriteDateStart and tab_write_date<=:TabWriteDateEnd  ");
            list.add("TabWriteDateStart");
            list.add("TabWriteDateEnd");
            listValues.add(String.valueOf(si.getTab_write_date_start() == null ? "1800-01-01" : si.getTab_write_date_start()));
            listValues.add(String.valueOf(si.getTab_write_date_end() == null ? new Timestamp(System.currentTimeMillis()) : si.getTab_write_date_end()));

            if (si.getTabCarNum() != null) {
                sql.append(" and   tab_car_num=:tabCarNum");
                list.add("tabCarNum");

                listValues.add(String.valueOf(si.getTabCarNum()));


            }
            if (si.getTabSafePerson() != null) {
                sql.append(" and  tab_safe_person=:TabSafePerson");
                list.add("TabSafePerson");

                listValues.add(String.valueOf(si.getTabSafePerson()));

            }
            if (si.getTabYxState() != null) {
                sql.append(" and  tab_yx_state=:TabYxState");
                list.add("TabYxState");

                listValues.add(String.valueOf(si.getTabYxState()));


            }
            if (si.getTabCarState() != null) {
                sql.append(" and  tab_car_state=:TabCarState");
                list.add("TabCarState");

                listValues.add(String.valueOf(si.getTabCarState()));

            }
            if (si.getTabChannel() != null) {
                sql.append(" and  tab_channel=:TabChannel");
                list.add("TabChannel");

                listValues.add(String.valueOf(si.getTabChannel()));

            }
            if (si.getTabInsuranceCompanyName() != null) {
                sql.append(" and  tab_insurance_company_name=:TabInsuranceCompanyName");
                list.add("TabInsuranceCompanyName");
                listValues.add(String.valueOf(si.getTabInsuranceCompanyName()));

            }
            if (si.getTabCdPerson() != null) {
                sql.append(" and  tab_cd_person=:TabCdPerson");
                list.add("TabCdPerson");

                listValues.add(String.valueOf(si.getTabCdPerson()));

            }
            if (si.getTabWhPerson() != null) {
                sql.append(" and  tab_manager_user=:TabWhPerson");
                list.add("TabWhPerson");
                listValues.add(String.valueOf(si.getTabWhPerson()));

            }
            if (si.getTabAgentName() != null) {
                sql.append(" and  tab_agent_name like :TabAgentName");
                list.add("TabAgentName");
                listValues.add("'" + String.valueOf(si.getTabAgentName()) + "'");

            }
            if (si.getTabTeamOwnership() != null) {
                sql.append(" and  tab_team_ownership=:TabTeamOwnership");
                list.add("TabTeamOwnership");
                listValues.add(String.valueOf(si.getTabTeamOwnership()));
            }
        }

        sql.append(" order by  tab_write_date desc");
        if (list.isEmpty()) {
            @SuppressWarnings("unchecked")
            List<TabScheduleOfInsuranceTrue> list1 = (List<TabScheduleOfInsuranceTrue>) datadao.pageQueryViaParam1(sql.toString(), pageSize, page, null, null);
            return list1;
        }
        String params[] = new String[list.size()];
        String values[] = new String[list.size()];
        for (int i = 0; i < params.length; i++) {
            params[i] = list.get(i);
            values[i] = listValues.get(i);
        }

        String sqlStr = sql.toString();
        System.out.println(sqlStr);

        @SuppressWarnings("unchecked")
        List<TabScheduleOfInsuranceTrue> list1 = (List<TabScheduleOfInsuranceTrue>) datadao.pageQueryViaParam1(sqlStr, pageSize, page, params, values);
//		private Integer id;
//		private Timestamp tabStartDate;//批单日期
//		private Timestamp tabEndDate;//到期日
//		private Timestamp tabQdDate;//签单日期
//		private String tabCarNum;//车牌
//		private String tabSafePerson;//被保险人				
//		private String tabYxState;//业务性质保批单		
//		private String tabCarState;//车辆使用性质
//		private String tabChannel;//渠道归属
//		private String tabInsuranceCompanyName;//保险公司名字
//		private String tabCdPerson;//出单人
//		private String tabWhPerson;//维护人
//		private String tabAgentName;//代理人姓名
//		private String tabAmountSate;//收款状态
//		private String tabTeamOwnership;//团队归属						
        return list1;

    }

    @Override
    public int queryAllByObjectCountAmount(SeachInsurance si) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<TabScheduleOfInsuranceTrue> queryAllByObjectOrderByAllAmount(
            SeachInsurance si) {
        // TODO Auto-generated method stub
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());

        StringBuffer sql = new StringBuffer("from TabScheduleOfInsuranceTrue where 1=1  ");
//		String  params[]=null;
//		String values[]=null;
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> listValues = new ArrayList<String>();

        if (si != null) {
            if (si.getId() != null) {
                sql.append(" and id=:Id");
                list.add("Id");
                listValues.add(String.valueOf(si.getId()));

            }
			/*sql.append(" and  tab_start_date  >=:stime  and tab_start_date<=:date ");
			 list.add("stime");	
			 list.add("date");
			 listValues.add(String.valueOf(si.getTabStartDateStart()==null?"1800-01-01":si.getTabStartDateStart()));
			 listValues.add(String.valueOf(si.getTabStartDateEnd()==null?new Timestamp(System.currentTimeMillis()):si.getTabStartDateEnd()));
			*/

            sql.append(" and  tab_end_date  >=:TabEndDateStart and tab_end_date<=:TabEndDateEnd ");
            list.add("TabEndDateStart");
            list.add("TabEndDateEnd");
            listValues.add(String.valueOf(si.getTabEndDateStart() == null ? "1800-01-01" : si.getTabEndDateStart()));
            listValues.add(String.valueOf(si.getTabEndDateEnd() == null ? "4040-01-01" : si.getTabEndDateEnd()));


            sql.append(" and  tab_qd_date  >=:TabQdDateStart and tab_qd_date <=:TabQdDateEnd  ");
            list.add("TabQdDateStart");
            list.add("TabQdDateEnd");
            listValues.add(String.valueOf(si.getTabQdDateStart() == null ? "1800-01-01" : si.getTabQdDateStart()));
            listValues.add(String.valueOf(si.getTabQdDateEnd() == null ? "4040-01-01" : si.getTabQdDateEnd()));


            sql.append(" and  tab_write_date >=:TabWriteDateStart and tab_write_date<=:TabWriteDateEnd  ");
            list.add("TabWriteDateStart");
            list.add("TabWriteDateEnd");
            listValues.add(String.valueOf(si.getTab_write_date_start() == null ? "1800-01-01" : si.getTab_write_date_start()));
            listValues.add(String.valueOf(si.getTab_write_date_end() == null ? new Timestamp(System.currentTimeMillis()) : si.getTab_write_date_end()));

            if (si.getTabCarNum() != null) {
                sql.append(" and   tab_car_num=:tabCarNum");
                list.add("tabCarNum");

                listValues.add(String.valueOf(si.getTabCarNum()));


            }
            if (si.getTabSafePerson() != null) {
                sql.append(" and  tab_safe_person=:TabSafePerson");
                list.add("TabSafePerson");

                listValues.add(String.valueOf(si.getTabSafePerson()));

            }
            if (si.getTabYxState() != null) {
                sql.append(" and  tab_yx_state=:TabYxState");
                list.add("TabYxState");

                listValues.add(String.valueOf(si.getTabYxState()));


            }
            if (si.getTabCarState() != null) {
                sql.append(" and  tab_car_state=:TabCarState");
                list.add("TabCarState");

                listValues.add(String.valueOf(si.getTabCarState()));

            }
            if (si.getTabChannel() != null) {
                sql.append(" and  tab_channel=:TabChannel");
                list.add("TabChannel");

                listValues.add(String.valueOf(si.getTabChannel()));

            }
            if (si.getTabInsuranceCompanyName() != null) {
                sql.append(" and  tab_insurance_company_name=:TabInsuranceCompanyName");
                list.add("TabInsuranceCompanyName");
                listValues.add(String.valueOf(si.getTabInsuranceCompanyName()));

            }
            if (si.getTabCdPerson() != null) {
                sql.append(" and  tab_cd_person=:TabCdPerson");
                list.add("TabCdPerson");

                listValues.add(String.valueOf(si.getTabCdPerson()));

            }
            if (si.getTabWhPerson() != null) {
                sql.append(" and  tab_manager_user=:TabWhPerson");
                list.add("TabWhPerson");
                listValues.add(String.valueOf(si.getTabWhPerson()));

            }
            if (si.getTabAgentName() != null) {
                sql.append(" and  tab_agent_name=:TabAgentName");
                list.add("TabAgentName");
                listValues.add(String.valueOf(si.getTabAgentName()));

            }
            if (si.getTabTeamOwnership() != null) {
                sql.append(" and  tab_team_ownership=:TabTeamOwnership");
                list.add("TabTeamOwnership");
                listValues.add(String.valueOf(si.getTabTeamOwnership()));
            }
        }

        sql.append(" ORDER BY tab_all_amount DESC");
        if (list.isEmpty()) {
            @SuppressWarnings("unchecked")
            List<TabScheduleOfInsuranceTrue> list1 = (List<TabScheduleOfInsuranceTrue>) datadao.getAllObjects1(sql.toString(), null, null);
            return list1;
        }
        String params[] = new String[list.size()];
        String values[] = new String[list.size()];
        for (int i = 0; i < params.length; i++) {
            params[i] = list.get(i);
            values[i] = listValues.get(i);
        }

        String sqlStr = sql.toString();
        System.out.println(sqlStr);

        @SuppressWarnings("unchecked")
        List<TabScheduleOfInsuranceTrue> list1 = (List<TabScheduleOfInsuranceTrue>) datadao.getAllObjects1(sqlStr, params, values);
//		private Integer id;
//		private Timestamp tabStartDate;//批单日期
//		private Timestamp tabEndDate;//到期日
//		private Timestamp tabQdDate;//签单日期
//		private String tabCarNum;//车牌
//		private String tabSafePerson;//被保险人
//		private String tabYxState;//业务性质保批单
//		private String tabCarState;//车辆使用性质
//		private String tabChannel;//渠道归属
//		private String tabInsuranceCompanyName;//保险公司名字
//		private String tabCdPerson;//出单人
//		private String tabWhPerson;//维护人
//		private String tabAgentName;//代理人姓名
//		private String tabAmountSate;//收款状态
//		private String tabTeamOwnership;//团队归属
        return list1;
    }

    @Override
    public List<TabScheduleOfInsuranceTrue> updateType(
            TabScheduleOfInsuranceTrue tab, String tableName) {
        if (!tableName.equals("1")) {
            String sql = "update " + tableName + " set tabtype=1 where tab_agent_name='" + tab.getTabAgentName() + "' and tab_all_amount=" + tab.getTabAllAmount() + " and tab_car_num='" + tab.getTabCarNum() + "'";
            int executeBySql = datadao.executeBySql(sql);
        } else {
            String sql = "update tab_schedule_of_insurance_true set tabtype=1 where tab_agent_name='" + tab.getTabAgentName() + "' and tab_all_amount=" + tab.getTabAllAmount() + " and tab_car_num='" + tab.getTabCarNum() + "'";
            int executeBySql = datadao.executeBySql(sql);
            String sql1 = "update tab_schedule_of_insurance set tabtype=1 where tab_agent_name='" + tab.getTabAgentName() + "' and tab_all_amount=" + tab.getTabAllAmount() + " and tab_car_num='" + tab.getTabCarNum() + "'";
            int executeBySql1 = datadao.executeBySql(sql1);
        }
        return null;
    }

}
