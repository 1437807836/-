package com.yxbx.action;


import java.io.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.google.gson.Gson;
import com.yxbx.bean.SeachInsurance;
import com.yxbx.pojo.TabAgent;
import com.yxbx.pojo.TabInsuranceFile;
import com.yxbx.pojo.TabInsurerPolicy;
import com.yxbx.pojo.TabPayPerson;
import com.yxbx.pojo.TabPayRecord;
import com.yxbx.pojo.TabScheduleOfInsurance;
import com.yxbx.pojo.TabScheduleOfInsuranceTrue;
import com.yxbx.pojo.TabUser;
import com.yxbx.service.AgentService;
import com.yxbx.service.InsuranceFileService;
import com.yxbx.service.InsurerPolicyService;
import com.yxbx.service.PayPersonService;
import com.yxbx.service.PayRecordService;
import com.yxbx.service.ScheduleOfInsuranceService;
import com.yxbx.service.ScheduleOfInsuranceTrueService;
import com.yxbx.service.UserService;
import com.yxbx.util.Util;


@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("default")
@InterceptorRef("defStack")
public class UserAction extends BaseAction {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // 供导入保单时上传文件使用
    private File insurancefile;
    private String insuranceFileName;

    public File getInsurancefile() {
        return insurancefile;
    }

    public void setInsurancefile(File insurancefile) {
        this.insurancefile = insurancefile;
    }

    public String getInsuranceFileName() {
        return insuranceFileName;
    }

    public void setInsuranceFileName(String insuranceFileName) {
        this.insuranceFileName = insuranceFileName;
    }

    int limit;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getLimits() {
        return limits;
    }

    public void setLimits(int limits) {
        this.limits = limits;
    }

    int limits;
    String mobile;
    int user_type = 0;
    int userId;
    String startTime;
    String endTime;
    String zcId;
    String shouru;
    String zhichu;
    String AllAmount;
    int userIdUpload;//要被修改的用户Id
    String username;//用户名
    String password;//用户密码
    String rePassword;//新用户密码
    String realAmount;//实付金额
    public String sxfalg;

    public String getSxfalg() {
        return sxfalg;
    }

    public void setSxfalg(String sxfalg) {
        this.sxfalg = sxfalg;
    }

    public String getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(String realAmount) {
        this.realAmount = realAmount;
    }

    String SeachInsuranceJsonStr = null;
    int page = 0;
    int payPersonId;//付款账号id
    String payPerson;

    public String getPayPerson() {
        return payPerson;
    }

    public void setPayPerson(String payPerson) {
        this.payPerson = payPerson;
    }

    public int getPayPersonId() {
        return payPersonId;
    }

    public void setPayPersonId(int payPersonId) {
        this.payPersonId = payPersonId;
    }

    String tids;//被付款的全部订单
    String payState;


    public String getPayState() {
        return payState;
    }

    public void setPayState(String payState) {
        this.payState = payState;
    }

    public String getTids() {
        return tids;
    }

    public void setTids(String tids) {
        this.tids = tids;
    }

    String fiIds;

    public String getFiId() {
        return fiIds;
    }

    public void setFiId(String fiIds) {
        this.fiIds = fiIds;
    }

    @Resource
    UserService userService;
    @Resource
    InsuranceFileService ifsService;
    @Resource
    ScheduleOfInsuranceService soiService;
    @Resource
    ScheduleOfInsuranceTrueService soiTrueService;
    @Resource
    PayRecordService prdService;
    @Resource
    PayPersonService pService;
    @Resource
    AgentService aService;
    @Resource
    InsurerPolicyService ipService;
    @Resource
    PayRecordService prService;

    @Action(value = "deleteInsuranceToUser")
    public String deleteInsuranceToUser() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userId < 1) {
            map.put("code", 3);
            map.put("msg", "请登录");
            strToJson(map);
            return null;
        }
        TabUser tu = userService.queryAllByUserId(userId);

        if (tu.getTabIsDelete() == 0 || tu.getTabUserType() < 3) {
            String[] split = fiIds.split(",");
            if (split.length > 0) {
                for (int i = 0; i < split.length; i++) {
                    if (split[i] != null && !split[i].equals("")) {
                        TabScheduleOfInsuranceTrue tso = soiTrueService.queryAllById(Integer.parseInt(split[i]));
                        soiTrueService.deleteScheduleOfInsuranceTrue(tso);
                        map.put("code", 0);
                        map.put("msg", "删除成功");
                    }
                }
            }
        } else {
            map.put("code", 3);
            map.put("msg", "权限不足");
        }
        strToJson(map);
        return null;
    }

    //查询我的清单
    @Action(value = "findInsuranceToUser")
    public String findInsuranceToUser() {

        Map<String, Object> map = new HashMap<String, Object>();
        if (userId < 1) {
            map.put("code", 3);
            map.put("msg", "请登录");
            strToJson(map);
            return null;
        }
        Gson g = new Gson();
        if (SeachInsuranceJsonStr != null && SeachInsuranceJsonStr.contains("{") && SeachInsuranceJsonStr.length() > 3) {
            //String[] s=	jsonInsurerPolicyStr.split("{");
            String s = String.valueOf(SeachInsuranceJsonStr.charAt(1));//判斷前端拼接是否有錯誤,多個逗號
            if (s.trim().equals(",")) {
                SeachInsuranceJsonStr = "{" + SeachInsuranceJsonStr.substring(2, SeachInsuranceJsonStr.length());
            }

        }
        SeachInsurance si = g.fromJson(SeachInsuranceJsonStr, SeachInsurance.class);
        if (si == null) {
            si = new SeachInsurance();
            si.setSxfalg("1");
        }
        if ("sx".equals(si.getSxfalg())) {
            limit = limit * 2;
        }

        List<TabScheduleOfInsurance> list = soiService.queryAllByObject(userId, si, limit, page);
        List<TabScheduleOfInsurance> lists = new ArrayList<TabScheduleOfInsurance>();

        boolean cState = false;
        int colorState = 1;
        for (int i = 0; i < list.size(); i++) {
            TabScheduleOfInsurance tof = list.get(i);
            for (int y = 0; y < list.size(); y++) {
                TabScheduleOfInsurance tof1 = list.get(y);
                if (i == y || tof1.getAgainState() != 0) {
                    continue;
                } else {
                    if (tof.getTabAgentName().equals(tof1.getTabAgentName())
                            && tof.getTabAllAmount().compareTo(tof1.getTabAllAmount()) == 0
                            && tof.getTabCarNum().equals(tof1.getTabCarNum())
                    ) {
                        if ("0".equals(tof.getTabtype())) {
                            tof.setAgainStates("重复");
                            tof1.setAgainStates("重复");
                            tof.setAgainState(colorState);
                            tof1.setAgainState(colorState);
                            lists.add(list.get(y));
                            lists.add(list.get(i));
                        }
                        cState = true;

                    }
                }

            }
            if (cState) {
                colorState++;
            }


        }
        List<TabScheduleOfInsurance> list1 = soiService.queryAllByObject(userId, si);
        if (list != null && !list.isEmpty()) {

            map.put("count", list1.size());
            map.put("code", 0);
            map.put("msg", "查询成功");
            if ("sx".equals(si.getSxfalg())) {
                map.put("data", lists);
            } else {
                map.put("data", list);
            }
            map.put("limit", 10);
        } else {
            list = new ArrayList<TabScheduleOfInsurance>();
            map.put("code", 0);
            map.put("data", list);
            map.put("msg", "无结果集");
            map.put("limit", 10);
        }
        strToJson(map);
        return null;
    }

    //修改重复状态
    @Action(value = "updatetype")
    public void updatetype() {
        Gson g = new Gson();
        if (SeachInsuranceJsonStr != null && SeachInsuranceJsonStr.contains("{") && SeachInsuranceJsonStr.length() > 3) {
            //String[] s=	jsonInsurerPolicyStr.split("{");
            String s = String.valueOf(SeachInsuranceJsonStr.charAt(1));//判斷前端拼接是否有錯誤,多個逗號
            if (s.trim().equals(",")) {
                SeachInsuranceJsonStr = "{" + SeachInsuranceJsonStr.substring(2, SeachInsuranceJsonStr.length());
            }
        }
        SeachInsurance si = g.fromJson(SeachInsuranceJsonStr, SeachInsurance.class);
        TabScheduleOfInsuranceTrue tof = new TabScheduleOfInsuranceTrue();
        tof.setTabAgentName(si.getTabAgentName());
        tof.setTabAllAmount(si.getTabAllAmount());
        tof.setTabCarNum(si.getTabCarNum());
        String falg = "tab_schedule_of_insurance_true";
        if (!StringUtils.isEmpty(si.getFalg())) {
            falg = si.getFalg();
        }
        if ("0".equals(si.getType())) {
            soiTrueService.updateType(tof, falg);
        }
    }

    /**
     * 导出CSV，即导出excel
     */
    @Action(value = "exportCSV")
    public void exportCSV() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String idStr = request.getParameter("idStr");
//        System.out.println(exportItem + "..." + idStr);
        ExcelUtils testExcel = new ExcelUtils();
        Map<String, Object> map = new HashMap<String, Object>();
        List<TabScheduleOfInsuranceTrue> list1 = soiTrueService.queryAllTabScheduleOfInsuranceTrue(page, idStr);
        try {
            LinkedHashMap<String, List<List<String>>> dataMap = new LinkedHashMap<String, List<List<String>>>();
            List<List<String>> list = new ArrayList<List<String>>();
            //第一行标题
            List<String> lista = new ArrayList<String>();
            lista.add("保批单日期");
            lista.add("到期日");
            lista.add("签单日期");
            lista.add("车牌号");
            lista.add("被保险人");
            lista.add("业务性质");
            lista.add("转续保");
            lista.add("使用性质");
            lista.add("渠道");
            lista.add("保险公司");
            lista.add("交强险");
            lista.add("商业险");
            lista.add("车船税");
            lista.add("小计");
            lista.add("代理人");
            lista.add("团队");
            lista.add("结算方式");
            lista.add("备注");
            lista.add("出单员");
            lista.add("维护人");
            lista.add("客户电话");
            lista.add("交强净保费");
            lista.add("交强费用率");
            lista.add("交强险费用");
            lista.add("商业净保费");
            lista.add("商业费用率");
            lista.add("商业险费用");
            lista.add("其他应付");
            lista.add("佣金合计");
            list.add(lista);
            for (TabScheduleOfInsuranceTrue o : list1) {
                List<String> listb = new ArrayList<String>();
                listb.add(o.getTabQdDate() + "");
                listb.add(o.getTabEndDate() + "");
                listb.add(o.getTabStartDate() + "");
                listb.add(o.getTabCarNum() + "");
                listb.add(o.getTabSafePerson() + "");
                listb.add(o.getTabBusinessNature() + "");
                listb.add(o.getTabIsRenewal() + "");
                listb.add(o.getTabCarState() + "");
                listb.add(o.getTabChannel() + "");
                listb.add(o.getTabInsuranceCompanyName() + "");
                listb.add(o.getTabCompulsoryInsurance() + "");
                listb.add(o.getTabCommercialInsurance() + "");
                listb.add(o.getTabTax() + "");
                listb.add(o.getTabSubtotal() + "");
                listb.add(o.getTabAgentName() + "");
                listb.add(o.getTabTeamOwnership() + "");
                listb.add(o.getTabAgentPay() + "");
                listb.add(o.getTabMessage() + "");
                listb.add(o.getTabCdPerson() + "");
                listb.add(o.getTabWhPerson() + "");
                listb.add(o.getTabCustomerMobile() + "");
                listb.add(o.getTabJqjbf() + "");
                listb.add(o.getTabJqfyl() + "");
                listb.add(o.getTabJqxfy() + "");
                listb.add(o.getTabSyjbf() + "");
                listb.add(o.getTabSyfyl() + "");
                listb.add(o.getTabSyxfy() + "");
                listb.add(o.getTabCommissionOther() + "");
                listb.add(o.getTabCommissionAmount() + "");
                list.add(listb);
            }
            //设置文件的题目，下面的题目
            dataMap.put("题目", list);
            //将第一行设置成题目里面内容也添加传入方法中
            Workbook wb = testExcel.writeExcel(dataMap, "保险清单");
            //加个时间戳
            long f = System.currentTimeMillis();

            String path = ServletActionContext.getServletContext().getRealPath("files") + "/excel/ScheduleOfInsurance_" + f + ".xls";
            FileOutputStream fileOut = new FileOutputStream(path);
            wb.write(fileOut);
            fileOut.close();

            System.out.println(path);

            map.put("code", 0);
            map.put("data", "ScheduleOfInsurance_" + f + ".xls");
            map.put("msg", "导出成功");
            strToJson(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //导出
    @Action(value = "testWrite")
    public void testWrite() {
        ExcelUtils testExcel = new ExcelUtils();
        Map<String, Object> map = new HashMap<String, Object>();
        Gson g = new Gson();
        if (SeachInsuranceJsonStr != null && SeachInsuranceJsonStr.contains("{") && SeachInsuranceJsonStr.length() > 3) {
            //String[] s=	jsonInsurerPolicyStr.split("{");
            String s = String.valueOf(SeachInsuranceJsonStr.charAt(1));//判斷前端拼接是否有錯誤,多個逗號
            if (s.trim().equals(",")) {
                SeachInsuranceJsonStr = "{" + SeachInsuranceJsonStr.substring(2, SeachInsuranceJsonStr.length());
            }
        }
        SeachInsurance si = g.fromJson(SeachInsuranceJsonStr, SeachInsurance.class);
        List<TabScheduleOfInsuranceTrue> list1 = soiTrueService.queryAllByObjectqueryWaitingPay(si);
        try {
            // 测试 导出
            LinkedHashMap<String, List<List<String>>> dataMap = new LinkedHashMap<String, List<List<String>>>();
            List<List<String>> list = new ArrayList<List<String>>();
            //第一行标题
            List<String> lista = new ArrayList<String>();
            lista.add("客户名");
            lista.add("代理人");
            lista.add("团队");
            lista.add("收款状态");
            lista.add("备注");
            lista.add("申请人");
            lista.add("维护人");
            lista.add("佣金合计");
            lista.add("其他佣金");
            lista.add("付款状态");
            lista.add("批次实付");
            lista.add("写入时间");
            lista.add("审核结果");
            lista.add("审核人");
            list.add(lista);
            for (TabScheduleOfInsuranceTrue o : list1) {
                List<String> listb = new ArrayList<String>();
                listb.add(o.getTabSafePerson() + "");
                listb.add(o.getTabAgentName() + "");
                listb.add(o.getTabTeamOwnership() + "");
                listb.add(o.getTabAmountSate() + "");
                listb.add(o.getTabMessage() + "");
                listb.add(o.getTabCdPerson() + "");
                listb.add(o.getTabWhPerson() + "");
                listb.add(o.getTabCommissionAmount() + "");
                listb.add(o.getTabCommissionOther() + "");
                listb.add(o.getTabAgentPay() + "");
                listb.add(o.getTabIsPayAmount() + "");
                listb.add(o.getTabWriteDate() + "");
                listb.add(o.getTabIsAuditing() + "");
                listb.add(o.getTabAnnotationsPerson() + "");
                list.add(listb);
            }
            //设置文件的题目，下面的题目
            dataMap.put("题目", list);
            //将第一行设置成题目里面内容也添加传入方法中
            Workbook wb = testExcel.writeExcel(dataMap, "待付款");
            FileOutputStream fileOut = new FileOutputStream("D:/待付款.xls");
            wb.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Action(value = "waitingPay")
    public String waitingPay() {

        Map<String, Object> map = new HashMap<String, Object>();
        if (userId < 1) {
            map.put("code", 3);
            map.put("msg", "请登录");
            strToJson(map);
            return null;
        }
        TabUser tu = userService.queryAllByUserId(userId);
        if (tu.getTabIsDelete() != 0 || tu.getTabUserType() > 2) {
            map.put("code", 6);
            map.put("msg", "权限不足");
            strToJson(map);
            return null;
        }
        Gson g = new Gson();
        if (SeachInsuranceJsonStr != null && SeachInsuranceJsonStr.contains("{") && SeachInsuranceJsonStr.length() > 3) {
            //String[] s=	jsonInsurerPolicyStr.split("{");
            String s = String.valueOf(SeachInsuranceJsonStr.charAt(1));//判斷前端拼接是否有錯誤,多個逗號
            if (s.trim().equals(",")) {
                SeachInsuranceJsonStr = "{" + SeachInsuranceJsonStr.substring(2, SeachInsuranceJsonStr.length());
            }

        }
        SeachInsurance si = g.fromJson(SeachInsuranceJsonStr, SeachInsurance.class);
        List<TabScheduleOfInsuranceTrue> list = soiTrueService.queryAllByObjectWaitingPay(0, si, limit, page);
        List<TabScheduleOfInsuranceTrue> list1 = soiTrueService.queryAllByObjectqueryWaitingPay(si);
        if (list != null) {
            for (TabScheduleOfInsuranceTrue tabScheduleOfInsuranceTrue : list) {
                List<TabInsuranceFile> tif = ifsService.queryInsuranceFileByInsuranceId(tabScheduleOfInsuranceTrue.getId());
                tabScheduleOfInsuranceTrue.setTif(tif);
            }
            map.put("count", list1.size());
            map.put("code", 0);
            map.put("msg", "查询成功");
            map.put("data", list);
        } else {
            list = new ArrayList<TabScheduleOfInsuranceTrue>();
            map.put("code", 2);
            map.put("result", list);
            map.put("count", 0);
            map.put("data", "无结果集");
        }
        strToJson(map);
        return null;
    }

    @Action(value = "creatCL")
    public String creatCL() {
        BigDecimal yongjhej = Util.toBigDecimal("0.00");
        Map<String, Object> map = new HashMap<String, Object>();
        if (zcId == null || zcId.equals("")) {
            map.put("code", 3);
            map.put("msg", "请选择政策");
            strToJson(map);
            return null;
        }
        if (userId > 0) {
            TabUser tu = userService.queryAllByUserId(userId);
            if (tu != null && tu.getTabUserType() < 3) {
                SeachInsurance si = new SeachInsurance();
                si.setTabQdDateEnd(Util.toTimestamp(endTime));
                si.setTabQdDateStart(Util.toTimestamp(startTime));

                TabInsurerPolicy tip = ipService.queryInsurerPolicyById(Integer.parseInt(zcId));
                if (tip == null) {
                    map.put("code", 4);
                    map.put("msg", "该清单内保险公司在政策中找不到");
                    strToJson(map);
                    return null;
                }
                si.setTabInsuranceCompanyName(tip.getTabInsurerName());
                List<TabScheduleOfInsuranceTrue> list1 = soiTrueService.queryAllByObjectOrderByAllAmount(si);
                if (list1 == null) {
                    map.put("code", 6);
                    map.put("msg", "该政策与该公司不对应");
                    strToJson(map);
                    return null;
                }
                if (AllAmount == null) {
                    for (TabScheduleOfInsuranceTrue tabScheduleOfInsuranceTrue : list1) {
                        tabScheduleOfInsuranceTrue.setRealOpenPayState("待收款");
                        soiTrueService.updateScheduleOfInsurance(tabScheduleOfInsuranceTrue);
                    }
                    map.put("code", 0);
                    map.put("msg", "生成成功");

                } else {

                    for (TabScheduleOfInsuranceTrue tabScheduleOfInsuranceTrue : list1) {//计算总保险费
                        System.out.println(tabScheduleOfInsuranceTrue.getTabAllAmount());
                        yongjhej = yongjhej.add(tabScheduleOfInsuranceTrue.getTabAllAmount());
                    }
                    if (yongjhej.compareTo(Util.toBigDecimal(AllAmount)) == 0 || yongjhej.compareTo(Util.toBigDecimal(AllAmount)) == -1) {// 如果想结算的金额小于或等于总保单金额
                        for (TabScheduleOfInsuranceTrue tabScheduleOfInsuranceTrue : list1) {
                            tabScheduleOfInsuranceTrue.setRealOpenPayState("待收款");
                            soiTrueService.updateScheduleOfInsurance(tabScheduleOfInsuranceTrue);
                        }

                        map.put("code", 0);
                        map.put("msg", "生成成功");
                    } else {//粗略计算出个最接近该数值的保单

                        yongjhej = Util.toBigDecimal("0.00");
                        for (TabScheduleOfInsuranceTrue tabScheduleOfInsuranceTrue : list1) {
                            yongjhej = yongjhej.add(tabScheduleOfInsuranceTrue.getTabAllAmount());
                            if (yongjhej.compareTo(Util.toBigDecimal(AllAmount)) > 0) {
                                yongjhej = yongjhej.subtract(tabScheduleOfInsuranceTrue.getTabAllAmount());
                                continue;
                            } else {
                                System.out.println("--" + yongjhej);
                                tabScheduleOfInsuranceTrue.setRealOpenPayState("待收款");
                                soiTrueService.updateScheduleOfInsurance(tabScheduleOfInsuranceTrue);
                            }
                        }
                        map.put("code", 0);
                        map.put("msg", "生成成功");

                    }

                }

            } else {
                map.put("code", 2);
                map.put("msg", "权限不足");
                map.put("data", 0);
            }
        } else {
            map.put("code", 1);
            map.put("msg", "请登录");
            map.put("data", 0);
        }
        System.out.println(yongjhej);
        strToJson(map);
        return null;
    }

    @Action(value = "waitingOpenPay")
    public String waitingOpenPay() {

        Map<String, Object> map = new HashMap<String, Object>();
        if (userId < 1) {
            map.put("code", 3);
            map.put("msg", "请登录");
            strToJson(map);
            return null;
        }
        TabUser tu = userService.queryAllByUserId(userId);
        if (tu.getTabIsDelete() != 0 || tu.getTabUserType() > 2) {
            map.put("code", 6);
            map.put("msg", "权限不足");
            strToJson(map);
            return null;
        }
        Gson g = new Gson();
        if (SeachInsuranceJsonStr != null && SeachInsuranceJsonStr.contains("{") && SeachInsuranceJsonStr.length() > 3) {
            //String[] s=	jsonInsurerPolicyStr.split("{");
            String s = String.valueOf(SeachInsuranceJsonStr.charAt(1));//判斷前端拼接是否有錯誤,多個逗號
            if (s.trim().equals(",")) {
                SeachInsuranceJsonStr = "{" + SeachInsuranceJsonStr.substring(2, SeachInsuranceJsonStr.length());
            }

        }
        SeachInsurance si = g.fromJson(SeachInsuranceJsonStr, SeachInsurance.class);
        List<TabScheduleOfInsuranceTrue> list = soiTrueService.queryAllByObjectWaitingOpenPay(0, si, limit, page);
        List<TabScheduleOfInsuranceTrue> list1 = soiTrueService.queryAllByObjectqueryWaitingOpenPay(si);
        if (list != null) {
            for (TabScheduleOfInsuranceTrue tabScheduleOfInsuranceTrue : list) {
                List<TabInsuranceFile> tif = ifsService.queryInsuranceFileByInsuranceId(tabScheduleOfInsuranceTrue.getId());
                tabScheduleOfInsuranceTrue.setTif(tif);
            }

            map.put("count", list1.size());
            map.put("code", 0);
            map.put("msg", "查询成功");
            map.put("data", list);
        } else {
            list = new ArrayList<TabScheduleOfInsuranceTrue>();
            map.put("code", 2);
            map.put("result", list);
            map.put("count", 0);
            map.put("data", "无结果集");
        }
        strToJson(map);
        return null;
    }

    @Action(value = "PayPost")
    public String PayPost() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userId < 1) {
            map.put("code", 3);
            map.put("msg", "请登录");
            strToJson(map);
            return null;
        }
        TabUser tu = userService.queryAllByUserId(userId);
        if (tu.getTabIsDelete() != 0 || tu.getTabUserType() > 2) {
            map.put("code", 6);
            map.put("msg", "权限不足");
            strToJson(map);
            return null;
        }
        String versionPay = String.valueOf("h" + System.currentTimeMillis());

        if (tids != null && !tids.equals("")) {
            if (tids.contains(",")) {//如果是批量
                String ts[] = tids.split(",");//得到所有要被付款的订单

                for (String string : ts) {
                    if (string.trim().equals("0")) {
                        continue;
                    }
                    TabPayRecord tpr = new TabPayRecord();
                    tpr.setPayState("付款");
                    if (payPerson == null || payPerson.equals("")) {
                        if (payPersonId > 0) {
                            TabPayPerson tpp = pService.queryInsurerById(payPersonId);
                            tpr.setTabPayNum(tpp.getTabPayCard());
                            tpr.setTabPayPerson(tpp.getTabPayName());
                            tpr.setTabPayPersonId(tpp.getId());
                        } else {
                            map.put("code", 7);
                            map.put("msg", "请选择付款账号，或填写付款人");
                            strToJson(map);
                            return null;
                        }

                    } else {
                        tpr.setTabPayNum(payPerson);
                        tpr.setTabPayPerson(payPerson);
                        tpr.setTabPayPersonId(-1);
                    }


                    tpr.setTabInsuranceTrueId(Integer.parseInt(string));
                    TabScheduleOfInsuranceTrue tsoi = soiTrueService.queryAllById(Integer.parseInt(string));
                    tpr.setTabPayee(tsoi.getTabAgentName());
                    TabAgent ta = aService.queryagentByAgentName(tsoi.getTabAgentName());
                    if (ta != null) {
                        tpr.setTabBankNum(ta.getTabBankCardNum());
                        tpr.setTabUserId(tu.getTabUserId());
                        tpr.setTabWriteDate(new Timestamp(System.currentTimeMillis()));
                        tpr.setTabPayDates(new Timestamp(System.currentTimeMillis()));
                        tpr.setTabVersionPay(versionPay);
                        tpr.setTabPayFileCode("888");
                        prdService.addPayRecord(tpr);
                        tsoi.setTabAgentPay("已付款");
                        if (realAmount != null && !realAmount.equals("")) {
                            tsoi.setTabIsPayAmount(Util.toBigDecimal(realAmount));
                        } else {
                            tsoi.setTabIsPayAmount(tsoi.getTabCommissionAmount());
                        }

                        soiTrueService.updateScheduleOfInsurance(tsoi);
                        map.put("code", 0);
                        map.put("msg", "操作成功");
                    } else {
                        map.put("code", 1);
                        map.put("msg", "部分失败了,找不到代理人" + tsoi.getTabAgentName());
                        strToJson(map);
                        return null;
                    }
                }
            } else {
                TabPayRecord tpr = new TabPayRecord();
                tpr.setPayState("付款");
                if (payPerson == null || payPerson.equals("")) {
                    if (payPersonId > 0) {
                        TabPayPerson tpp = pService.queryInsurerById(payPersonId);
                        tpr.setTabPayNum(tpp.getTabPayCard());
                        tpr.setTabPayPerson(tpp.getTabPayName());
                        tpr.setTabPayPersonId(tpp.getId());
                    } else {
                        map.put("code", 7);
                        map.put("msg", "请选择付款账号，或填写付款人");
                        strToJson(map);
                        return null;
                    }

                } else {
                    tpr.setTabPayNum(payPerson);
                    tpr.setTabPayPerson(payPerson);
                    tpr.setTabPayPersonId(-1);
                }


                tpr.setTabInsuranceTrueId(Integer.parseInt(tids));
                TabScheduleOfInsuranceTrue tsoi = soiTrueService.queryAllById(Integer.parseInt(tids));
                tpr.setTabPayee(tsoi.getTabAgentName());
                TabAgent ta = aService.queryagentByAgentName(tsoi.getTabAgentName());
                if (ta != null) {
                    tpr.setTabBankNum(ta.getTabBankCardNum());
                    tpr.setTabUserId(tu.getTabUserId());
                    tpr.setTabWriteDate(new Timestamp(System.currentTimeMillis()));
                    tpr.setTabVersionPay(versionPay);
                    tpr.setTabPayFileCode("888");

                    prdService.addPayRecord(tpr);
                    tsoi.setTabAgentPay("已付款");
                    if (realAmount != null && !realAmount.equals("")) {
                        tsoi.setTabIsPayAmount(Util.toBigDecimal(realAmount));
                    } else {
                        tsoi.setTabIsPayAmount(tsoi.getTabCommissionAmount());
                    }
                    soiTrueService.updateScheduleOfInsurance(tsoi);
                    map.put("code", 0);
                    map.put("msg", "操作成功");
                } else {
                    map.put("code", 1);
                    map.put("msg", "找不到该代理人" + tsoi.getTabAgentName());
                }

            }
        }


        strToJson(map);
        return null;
    }

    @Action(value = "openPayPost")
    public String openPayPost() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userId < 1) {
            map.put("code", 3);
            map.put("msg", "请登录");
            strToJson(map);
            return null;
        }
        TabUser tu = userService.queryAllByUserId(userId);
        if (tu.getTabIsDelete() != 0 || tu.getTabUserType() > 2) {
            map.put("code", 6);
            map.put("msg", "权限不足");
            strToJson(map);
            return null;
        }
        String versionPay = String.valueOf("h" + System.currentTimeMillis());

        if (tids != null && !tids.equals("")) {
            if (tids.contains(",")) {//如果是批量
                String ts[] = tids.split(",");//得到所有要被收款的订单

                for (String string : ts) {
                    if (string.trim().equals("0")) {
                        continue;
                    }
                    TabPayRecord tpr = new TabPayRecord();
                    tpr.setPayState("收款");
                    TabScheduleOfInsuranceTrue tsoi = soiTrueService.queryAllById(Integer.parseInt(string));
                    if (payPerson == null || payPerson.equals("")) {
                        if (payPersonId > 0) {
                            TabPayPerson tpp = pService.queryInsurerById(payPersonId);    //卡Id
                            tpr.setTabPayNum(tpp.getTabPayCard());
                            tpr.setTabBankNum(tpp.getTabPayCard());
                            tpr.setTabPayPerson(tpp.getTabPayName());
                            tpr.setTabPayPersonId(tpp.getId());
                            tpr.setTabPayee(tpp.getTabPayName());
                        } else {
                            map.put("code", 7);
                            map.put("msg", "请选择收款账号，或填写收款人");
                            strToJson(map);
                            return null;
                        }

                    } else {
                        tpr.setTabPayNum(payPerson);
                        tpr.setTabPayPerson(payPerson);
                        tpr.setTabPayPersonId(-1);
                        tpr.setTabPayee(payPerson);
                    }


                    tpr.setTabInsuranceTrueId(Integer.parseInt(string));


                    tpr.setTabUserId(tu.getTabUserId());
                    tpr.setTabWriteDate(new Timestamp(System.currentTimeMillis()));
                    tpr.setTabVersionPay(versionPay);
                    tpr.setTabPayFileCode("888");

                    prdService.addPayRecord(tpr);
                    tsoi.setRealOpenPayState("已结算");
                    if (realAmount != null && !realAmount.equals("")) {
                        tsoi.setRealOpenPay(Util.toBigDecimal(realAmount));
                    } else {
                        tsoi.setRealOpenPay(tsoi.getTabAllAmount());
                    }

                    soiTrueService.updateScheduleOfInsurance(tsoi);
                    map.put("code", 0);
                    map.put("msg", "操作成功");

                }
            } else {
                TabPayRecord tpr = new TabPayRecord();
                tpr.setPayState("收款");
                TabScheduleOfInsuranceTrue tsoi = soiTrueService.queryAllById(Integer.parseInt(tids));
                if (payPerson == null || payPerson.equals("")) {
                    if (payPersonId > 0) {
                        TabPayPerson tpp = pService.queryInsurerById(payPersonId);    //卡Id
                        tpr.setTabPayNum(tpp.getTabPayCard());
                        tpr.setTabBankNum(tpp.getTabPayCard());
                        tpr.setTabPayPerson(tpp.getTabPayName());
                        tpr.setTabPayPersonId(tpp.getId());
                        tpr.setTabPayee(tpp.getTabPayName());
                    } else {
                        map.put("code", 7);
                        map.put("msg", "请选择收款账号，或填写收款人");
                        strToJson(map);
                        return null;
                    }

                } else {
                    tpr.setTabPayNum(payPerson);
                    tpr.setTabPayPerson(payPerson);
                    tpr.setTabPayPersonId(-1);
                    tpr.setTabPayee(payPerson);
                }


                tpr.setTabInsuranceTrueId(Integer.parseInt(tids));


                tpr.setTabUserId(tu.getTabUserId());
                tpr.setTabWriteDate(new Timestamp(System.currentTimeMillis()));
                tpr.setTabVersionPay(versionPay);
                tpr.setTabPayFileCode("888");

                prdService.addPayRecord(tpr);
                tsoi.setRealOpenPayState("已结算");
                if (realAmount != null && !realAmount.equals("")) {
                    tsoi.setRealOpenPay(Util.toBigDecimal(realAmount));
                } else {
                    tsoi.setRealOpenPay(tsoi.getTabAllAmount());
                }

                soiTrueService.updateScheduleOfInsurance(tsoi);
                map.put("code", 0);
                map.put("msg", "操作成功");

            }
        }


        strToJson(map);
        return null;
    }

    /**
     * 修改付款状态
     * @return
     */
    @Action(value = "updateInsuranceTabIsAuditing")
    public String updateInsuranceTabIsAuditing() {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpServletRequest request = ServletActionContext.getRequest();
        String id = request.getParameter("id");
        String tabIsAuditing = request.getParameter("tabIsAuditing");
        String result = soiTrueService.updateInsuranceTabIsAuditing(id, tabIsAuditing);
        map.put("code", 0);
        map.put("msg", result);
        strToJson(map);
        return null;
    }

    /**
     * 特殊政策提交保单
     * @return
     */
    @Action(value = "commitSpecialInsuranceTabIsAuditing")
    public String commitSpecialInsuranceTabIsAuditing() {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpServletRequest request = ServletActionContext.getRequest();
        String idStr = request.getParameter("idStr");
        String result = soiTrueService.commitSpecialInsuranceTabIsAuditing(idStr);
        map.put("code", 0);
        map.put("msg", result);
        strToJson(map);
        return null;
    }

    /**
     * 提交单个保单
     * @return
     */
    @Action(value = "commitInsuranceTabIsAuditing")
    public String commitInsuranceTabIsAuditing() {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpServletRequest request = ServletActionContext.getRequest();
        String id = request.getParameter("id");
        System.out.println("---------" + id);
        String result = soiTrueService.commitInsuranceTabIsAuditing(id);
        if (result.equals("0")) {
            map.put("msg", "提交成功！");
        } else if (result.equals("1")) {
            map.put("msg", "提交失败！该数据疑似重复！");
        } else if (result.equals("2")) {
            map.put("msg", "该数据已经提交过了！请勿重复提交！");
        }
        map.put("code", 0);
        strToJson(map);
        return null;
    }

    /**
     * 提交多个保单
     * @return
     */
    @Action(value = "commitManyInsuranceTabIsAuditing")
    public String commitManyInsuranceTabIsAuditing() {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpServletRequest request = ServletActionContext.getRequest();
        String idStr = request.getParameter("idStr");
        String result = soiTrueService.commitManyInsuranceTabIsAuditing(idStr);
        map.put("code", 0);
        map.put("msg", result);
        strToJson(map);
        return null;
    }

    /**
     * 根据id查询保单
     * @return
     */
    @Action(value = "findInsuranceById")
    public String findInsuranceById() {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpServletRequest request = ServletActionContext.getRequest();
        String id = request.getParameter("id");
        if (id == null || id.equals("")) {
            id = "0";
        }
        TabScheduleOfInsuranceTrue tabScheduleOfInsuranceTrue = soiTrueService.queryAllById(Integer.parseInt(id));
        if (tabScheduleOfInsuranceTrue != null) {
            List<TabInsuranceFile> tif = ifsService.queryInsuranceFileByInsuranceId(tabScheduleOfInsuranceTrue.getId());
            tabScheduleOfInsuranceTrue.setTif(tif);
            map.put("code", 0);
            map.put("msg", "查询成功！");
            map.put("data", tabScheduleOfInsuranceTrue);
        } else {
            map.put("code", 1);
            map.put("msg", "查询失败！");
            map.put("data", null);
        }
        strToJson(map);
        return null;
    }

    @Action(value = "findInsuranceTrue")    //查询正式表单
    public String findInsuranceTrue() {

        Map<String, Object> map = new HashMap<String, Object>();
        Gson g = new Gson();
        if (SeachInsuranceJsonStr != null && SeachInsuranceJsonStr.contains("{") && SeachInsuranceJsonStr.length() > 3) {
            //String[] s=	jsonInsurerPolicyStr.split("{");
            String s = String.valueOf(SeachInsuranceJsonStr.charAt(1));//判斷前端拼接是否有錯誤,多個逗號
            if (s.trim().equals(",")) {
                SeachInsuranceJsonStr = "{" + SeachInsuranceJsonStr.substring(2, SeachInsuranceJsonStr.length());
            }

        }

        SeachInsurance si = g.fromJson(SeachInsuranceJsonStr, SeachInsurance.class);
        if (si == null) {
            si = new SeachInsurance();
            si.setSxfalg("1");
        }
        if ("sx".equals(si.getSxfalg())) {
            limit = limit * 2;
        }
        List<TabScheduleOfInsuranceTrue> list = soiTrueService.queryAllByObject(0, si, limit, page);
//        List<TabScheduleOfInsuranceTrue> lists = new ArrayList<TabScheduleOfInsuranceTrue>();
        /*boolean cState = false;
        int colorState = 1;
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
                        if ("0".equals(tof.getTabtype())) {
                            tof.setAgainStates("重复");
                            tof1.setAgainStates("重复");
                            tof.setAgainState(colorState);
                            tof1.setAgainState(colorState);
                            lists.add(list.get(y));
                            lists.add(list.get(i));
                        }
                        cState = true;

                    }
                }

            }
            if (cState) {
                colorState++;
            }
        }*/
        List<TabScheduleOfInsuranceTrue> list1 = soiTrueService.queryAllByObject(si, "");
        if (list != null) {
//            for (TabScheduleOfInsuranceTrue tabScheduleOfInsuranceTrue : list) {
//                List<TabInsuranceFile> tif = ifsService.queryInsuranceFileByInsuranceId(tabScheduleOfInsuranceTrue.getId());
//                tabScheduleOfInsuranceTrue.setTif(tif);
//            }
            for (int i = 0; i < list.size(); i++) {
                List<TabInsuranceFile> tif = ifsService.queryInsuranceFileByInsuranceId(list.get(i).getId());
                list.get(i).setTif(tif);
            }

            map.put("count", list1.size());
            map.put("code", 0);
            map.put("msg", "查询成功");
//            if ("sx".equals(si.getSxfalg())) {
//                map.put("data", lists);
//            } else {
                map.put("data", list);
//            }
        } else {
            list = new ArrayList<TabScheduleOfInsuranceTrue>();
            map.put("code", 2);
            map.put("result", list);
            map.put("count", 0);
            map.put("data", "无结果集");
        }
        strToJson(map);
        return null;
    }

    @Action(value = "findInsuranceWhole")    //新查询所有清单
    public String findInsuranceWhole() {
        Map<String, Object> map = new HashMap<String, Object>();
        Gson g = new Gson();
        if (SeachInsuranceJsonStr != null && SeachInsuranceJsonStr.contains("{") && SeachInsuranceJsonStr.length() > 3) {
            //String[] s=	jsonInsurerPolicyStr.split("{");
            String s = String.valueOf(SeachInsuranceJsonStr.charAt(1));//判斷前端拼接是否有錯誤,多個逗號
            if (s.trim().equals(",")) {
                SeachInsuranceJsonStr = "{" + SeachInsuranceJsonStr.substring(2, SeachInsuranceJsonStr.length());
            }

        }
        SeachInsurance si = g.fromJson(SeachInsuranceJsonStr, SeachInsurance.class);
        if (si == null) {
            si = new SeachInsurance();
            si.setSxfalg("1");
        }
        if ("sx".equals(si.getSxfalg())) {
            limit = limit * 2;
        }
        List<TabScheduleOfInsuranceTrue> list = soiTrueService.queryAllByObjectAll(0, si, limit, page);
        List<TabScheduleOfInsuranceTrue> lists = new ArrayList<TabScheduleOfInsuranceTrue>();

        boolean cState = false;
        int colorState = 1;
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
                        if ("0".equals(tof.getTabtype())) {
                            tof.setAgainStates("重复");
                            tof1.setAgainStates("重复");
                            tof.setAgainState(colorState);
                            tof1.setAgainState(colorState);
                            lists.add(list.get(y));
                            lists.add(list.get(i));
                        }
                        cState = true;

                    }
                }

            }
            if (cState) {
                colorState++;
            }


        }
        List<TabScheduleOfInsuranceTrue> list1 = soiTrueService.queryAllByObject(si, "1");
        if (list != null) {
            for (TabScheduleOfInsuranceTrue tabScheduleOfInsuranceTrue : list) {
                List<TabInsuranceFile> tif = ifsService.queryInsuranceFileByInsuranceId(tabScheduleOfInsuranceTrue.getId());
                tabScheduleOfInsuranceTrue.setTif(tif);
            }

            map.put("count", list1.size());
            map.put("code", 0);
            map.put("msg", "查询成功");

            if ("sx".equals(si.getSxfalg())) {
                map.put("data", lists);
            } else {
                map.put("data", list);
            }
        } else {
            list = new ArrayList<TabScheduleOfInsuranceTrue>();
            map.put("code", 2);
            map.put("result", list);
            map.put("count", 0);
            map.put("data", "无结果集");
        }
        strToJson(map);
        return null;
    }

    //导出
    @Action(value = "testWritetj")
    public void testWritetj() {
        ExcelUtils testExcel = new ExcelUtils();
        Map<String, Object> map = new HashMap<String, Object>();
        Gson g = new Gson();
        if (SeachInsuranceJsonStr != null && SeachInsuranceJsonStr.contains("{") && SeachInsuranceJsonStr.length() > 3) {
            //String[] s=	jsonInsurerPolicyStr.split("{");
            String s = String.valueOf(SeachInsuranceJsonStr.charAt(1));//判斷前端拼接是否有錯誤,多個逗號
            if (s.trim().equals(",")) {
                SeachInsuranceJsonStr = "{" + SeachInsuranceJsonStr.substring(2, SeachInsuranceJsonStr.length());
            }

        }
        SeachInsurance si = g.fromJson(SeachInsuranceJsonStr, SeachInsurance.class);
        List<TabPayRecord> list1 = prService.queryTabPayRecordByPayState(payState);
        for (TabPayRecord t : list1) {
            TabScheduleOfInsuranceTrue sot = soiTrueService.queryAllById(t.getTabInsuranceTrueId());
            t.setList(sot);
            t.setPcRealAmount(sot.getTabIsPayAmount());//批次实付
            TabUser tu = userService.queryAllByUserId(t.getTabUserId());
            t.setWhPerson(tu.getTabUsername());
            t.setRealOpenPay(sot.getRealOpenPay());
        }
        try {
            // 测试 导出
            LinkedHashMap<String, List<List<String>>> dataMap = new LinkedHashMap<String, List<List<String>>>();
            List<List<String>> list = new ArrayList<List<String>>();
            //第一行标题
            List<String> lista = new ArrayList<String>();
            lista.add("款项批次");
            lista.add("款项类型");
            lista.add("收款卡号");
            lista.add("收款人");
            lista.add("处理卡号");
            lista.add("操作人");
            lista.add("批次付款");
            lista.add("批次收款");
            lista.add("写入时间");
            list.add(lista);
            for (TabPayRecord o : list1) {
                List<String> listb = new ArrayList<String>();
                listb.add(o.getTabVersionPay() + "");
                listb.add(o.getPayState() + "");
                listb.add(o.getTabBankNum() + "");
                listb.add(o.getTabPayee() + "");
                listb.add(o.getTabPayNum() + "");
                listb.add(o.getWhPerson() + "");
                listb.add(o.getPcRealAmount() + "");
                listb.add(o.getRealOpenPay() + "");
                listb.add(o.getTabWriteDate() + "");
                list.add(listb);
            }
            //设置文件的题目，下面的题目
            dataMap.put("题目", list);
            //将第一行设置成题目里面内容也添加传入方法中
            Workbook wb = testExcel.writeExcel(dataMap, "统计");
            FileOutputStream fileOut = new FileOutputStream("D:/统计.xls");
            wb.write(fileOut);
            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Action(value = "findInsuranceTrueOpenPay")    //查询正式表单
    public String findInsuranceTrueOpenPay() {
        Map<String, Object> map = new HashMap<String, Object>();
        Gson g = new Gson();
        if (SeachInsuranceJsonStr != null && SeachInsuranceJsonStr.contains("{") && SeachInsuranceJsonStr.length() > 3) {
            //String[] s=	jsonInsurerPolicyStr.split("{");
            String s = String.valueOf(SeachInsuranceJsonStr.charAt(1));//判斷前端拼接是否有錯誤,多個逗號
            if (s.trim().equals(",")) {
                SeachInsuranceJsonStr = "{" + SeachInsuranceJsonStr.substring(2, SeachInsuranceJsonStr.length());
            }

        }
        SeachInsurance si = g.fromJson(SeachInsuranceJsonStr, SeachInsurance.class);

        List<TabPayRecord> list = prService.queryTabPayRecordByPayStatePage(payState, limit, page);
        List<TabPayRecord> list1 = prService.queryTabPayRecordByPayState(payState);
        if (list != null) {

            for (TabPayRecord t : list) {
                TabScheduleOfInsuranceTrue sot = soiTrueService.queryAllById(t.getTabInsuranceTrueId());
                t.setList(sot);
                t.setPcRealAmount(sot.getTabIsPayAmount());//批次实付
                TabUser tu = userService.queryAllByUserId(t.getTabUserId());
                t.setWhPerson(tu.getTabUsername());
                t.setRealOpenPay(sot.getRealOpenPay());
            }
            map.put("count", list1.size());
            map.put("code", 0);
            map.put("msg", "查询成功");
            map.put("data", list);
        } else {
            list = new ArrayList<TabPayRecord>();
            map.put("code", 2);
            map.put("result", list);
            map.put("count", 0);
            map.put("data", "无结果集");
        }
        strToJson(map);
        return null;
    }

    @Action(value = "findInsuranceTrueUnOpenPay")    //查询正式表单
    public String findInsuranceTrueUnOpenPay() {
        Map<String, Object> map = new HashMap<String, Object>();
        Gson g = new Gson();
        if (SeachInsuranceJsonStr != null && SeachInsuranceJsonStr.contains("{") && SeachInsuranceJsonStr.length() > 3) {
            //String[] s=	jsonInsurerPolicyStr.split("{");
            String s = String.valueOf(SeachInsuranceJsonStr.charAt(1));//判斷前端拼接是否有錯誤,多個逗號
            if (s.trim().equals(",")) {
                SeachInsuranceJsonStr = "{" + SeachInsuranceJsonStr.substring(2, SeachInsuranceJsonStr.length());
            }

        }
        SeachInsurance si = g.fromJson(SeachInsuranceJsonStr, SeachInsurance.class);

        TabPayRecord t = prService.queryRecordByById(Integer.parseInt(zcId));

        if (t != null) {


            TabScheduleOfInsuranceTrue sot = soiTrueService.queryAllById(t.getTabInsuranceTrueId());
            t.setList(sot);
            t.setPcRealAmount(sot.getTabIsPayAmount());//批次实付
            TabUser tu = userService.queryAllByUserId(t.getTabUserId());
            t.setWhPerson(tu.getTabUsername());
            t.setRealOpenPay(sot.getRealOpenPay());


            map.put("code", 0);
            map.put("msg", "查询成功");
            map.put("data", t);
        }
        strToJson(map);
        return null;
    }

    @Action(value = "findInsuranceWait")    //查询待审核表单
    public String findInsuranceWait() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userId < 1) {
            map.put("code", 3);
            map.put("msg", "请登录");
            strToJson(map);
            return null;
        }
        TabUser tu = userService.queryAllByUserId(userId);
        if (tu.getTabUserType() > 2) {
            map.put("code", 4);
            map.put("msg", "无权操作");
            strToJson(map);
            return null;
        }
        Gson g = new Gson();
        if (SeachInsuranceJsonStr != null && SeachInsuranceJsonStr.contains("{") && SeachInsuranceJsonStr.length() > 3) {
            //String[] s=	jsonInsurerPolicyStr.split("{");
            String s = String.valueOf(SeachInsuranceJsonStr.charAt(1));//判斷前端拼接是否有錯誤,多個逗號
            if (s.trim().equals(",")) {
                SeachInsuranceJsonStr = "{" + SeachInsuranceJsonStr.substring(2, SeachInsuranceJsonStr.length());
            }

        }


        SeachInsurance si = g.fromJson(SeachInsuranceJsonStr, SeachInsurance.class);
        if (si == null) {
            si = new SeachInsurance();
            si.setSxfalg("1");
        }
        if ("sx".equals(si.getSxfalg())) {
            limit = limit * 2;
        }
        List<TabScheduleOfInsurance> list = soiService.queryAllByObject(0, si, limit, page);
        List<TabScheduleOfInsurance> lists = new ArrayList<TabScheduleOfInsurance>();

        for (TabScheduleOfInsurance tabScheduleOfInsurance : list) {
            List<TabInsuranceFile> tif = ifsService.queryInsuranceFileByInsuranceId(tabScheduleOfInsurance.getId());
            tabScheduleOfInsurance.setTif(tif);
        }
        boolean cState = false;
        int colorState = 1;
        for (int i = 0; i < list.size(); i++) {
            TabScheduleOfInsurance tof = list.get(i);
            for (int y = 0; y < list.size(); y++) {
                TabScheduleOfInsurance tof1 = list.get(y);
                if (i == y || tof1.getAgainState() != 0) {
                    continue;
                } else {
                    if (tof.getTabAgentName().equals(tof1.getTabAgentName())
                            && tof.getTabAllAmount().compareTo(tof1.getTabAllAmount()) == 0
                            && tof.getTabCarNum().equals(tof1.getTabCarNum())
                    ) {
                        if ("0".equals(tof.getTabtype())) {
                            tof.setAgainStates("重复");
                            tof1.setAgainStates("重复");
                            tof.setAgainState(colorState);
                            tof1.setAgainState(colorState);
                            lists.add(list.get(y));
                            lists.add(list.get(i));
                        }
                        cState = true;
                    }
                }
            }
            if (cState) {
                colorState++;
            }


        }
        List<TabScheduleOfInsurance> list1 = soiService.queryAllByObject(si);
        if (list1 != null && !list1.isEmpty()) {

            map.put("count", list1.size());
            map.put("code", 0);
            map.put("msg", "查询成功");
            if ("sx".equals(si.getSxfalg())) {
                map.put("data", lists);
            } else {
                map.put("data", list);
            }
        } else {
            list = new ArrayList<TabScheduleOfInsurance>();
            map.put("code", 0);
            map.put("data", list);
            map.put("msg", "无结果集");
            map.put("count", 0);
        }
        strToJson(map);
        return null;
    }

    @Action(value = "login")
    public String login() {
        TabUser tu = null;
        if (username != null) {
            tu = userService.findUser(username, password);
        }
        if (tu == null) {
            tu = userService.findUser(username); //在查下手机号码
            if (tu != null && !tu.getTabPassword().equals(password)) {
                tu = null;
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        boolean bl = tu == null ? false : true;
        if (bl) {
            map.put("code", 0);
            map.put("msg", "登录成功");
            map.put("username", username);
            map.put("user_type", tu.getTabUserType());
            map.put("userId", tu.getTabUserId());
        } else {
            map.put("code", 2);
            map.put("msg", "账号密码错误或用户不存在");
        }
        strToJson(map);
        return null;
    }

    @Action(value = "addUser")
    public String addUser() {
        Map<String, Object> map = new HashMap<String, Object>();
        TabUser tUser1 = userService.findUser(mobile);

        if (tUser1 == null) {
            TabUser tUser2 = userService.findUserByUsername(username);
            if (tUser2 != null) {
                map.put("code", 3);
                map.put("msg", "该用户名已存在");
                map.put("username", tUser2.getTabUsername());
            } else {
                if (userId > 0) {
                    TabUser tUser3 = userService.queryAllByUserId(userId);//当前操作者
                    int userType = tUser3.getTabUserType();
                    if (userType == 1) {
                        TabUser tUser = new TabUser();
                        tUser.setTabMobile(mobile);
                        tUser.setTabUserType(user_type);
                        tUser.setTabUsername(username);
                        tUser.setTabPassword(password);
                        tUser.setTabIsDelete(0);
                        userService.addClient(tUser);

                        map.put("code", 0);
                        map.put("msg", "添加成功");
                        map.put("username", username);
                    } else if (userType == 2) {
                        if (user_type > 2) {
                            TabUser tUser = new TabUser();
                            tUser.setTabMobile(mobile);
                            tUser.setTabUserType(user_type);
                            tUser.setTabUsername(username);
                            tUser.setTabPassword(password);
                            tUser.setTabIsDelete(0);
                            userService.addClient(tUser);

                            map.put("code", 0);
                            map.put("msg", "添加成功");
                            map.put("username", username);
                        } else {
                            map.put("code", 5);
                            map.put("msg", "权限不足");

                        }
                    }

                } else {
                    map.put("code", 4);
                    map.put("msg", "请重新登录");

                }


            }


        } else {
            map.put("code", 2);
            map.put("msg", "该用户手机已存在");
            map.put("username", tUser1.getTabUsername());
        }


        strToJson(map);
        return null;
    }

    @Action(value = "updateUser")
    public String updateUser() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userId > 0 && userIdUpload > 0) {
            TabUser tUser1 = userService.queryAllByUserId(userId);
            TabUser tUser2 = userService.queryAllByUserId(userIdUpload);
            TabUser tUser3 = userService.findUser(mobile);
            TabUser tUser4 = userService.findUser(username);
            if (tUser3 != null || tUser4 != null) {
                map.put("code", 6);
                map.put("msg", "手机号或用户名已经存在");
                strToJson(map);
                return null;
            }
            if (tUser1 != null && tUser2 != null && tUser1.getTabUserType() == 1) {
                if (username != null) {
                    tUser2.setTabUsername(username);
                }
                if (password != null) {
                    tUser2.setTabPassword(password);
                }
                if (mobile != null) {
                    tUser2.setTabMobile(mobile);
                }
                if (user_type > 0) {
                    tUser2.setTabUserType(user_type);
                }
                userService.updateClient(tUser2);

                map.put("code", 0);
                map.put("msg", "修改成功");
                map.put("username", username);
                strToJson(map);
                return null;
            }
            if (tUser2.getTabUserType() > tUser1.getTabUserType()) {
                map.put("code", 2);
                map.put("msg", "权限不足");
                strToJson(map);
                return null;
            } else {
                if (user_type > tUser1.getTabUserType()) {
                    map.put("code", 2);
                    map.put("msg", "权限不足");
                    strToJson(map);
                    return null;
                } else {
                    if (username != null) {
                        tUser2.setTabUsername(username);
                    }
                    if (password != null) {
                        tUser2.setTabPassword(password);
                    }
                    if (mobile != null) {
                        tUser2.setTabMobile(mobile);
                    }
                    if (user_type > 0) {
                        tUser2.setTabUserType(user_type);
                    }
                    userService.updateClient(tUser2);

                    map.put("code", 0);
                    map.put("msg", "修改成功");
                    map.put("username", username);
                    strToJson(map);
                    return null;
                }
            }

        } else {
            map.put("code", 5);
            map.put("msg", "请重新登录");

            strToJson(map);
            return null;
        }


    }

    @Action(value = "deleteUser")
    public String deleteUser() {
        Map<String, Object> map = new HashMap<String, Object>();
        TabUser tUser2 = userService.queryAllByUserId(userId);

        if (tUser2 != null && tUser2.getTabUserType() == 1) {
            TabUser tUser1 = userService.queryAllByUserId(userIdUpload);
            if (tUser1 != null) {

                tUser1.setTabIsDelete(1);

                userService.updateClient(tUser1);

                map.put("code", 0);
                map.put("msg", "删除成功");


            } else {
                map.put("code", 2);
                map.put("msg", "无此用户");

            }

        } else {
            map.put("code", 0);
            map.put("msg", "只有总管理员能删除成员");
            map.put("username", username);
        }


        strToJson(map);
        return null;
    }

    //查询全部用户
    @Action(value = "queryAllUser")
    public String queryAllUser() throws IOException {
        List<TabUser> list = userService.queryAllUser(limit, page);
        List<TabUser> list1 = userService.queryAllUser();
        if (userId > 0) {
            TabUser tu = userService.queryAllByUserId(userId);
            for (TabUser tabUser : list) {
                if (tu != null && tu.getTabUserType() > 2) {
                    tabUser.setTabPassword("******");
                    tabUser.setTabMobile(tabUser.getTabMobile().substring(0, 3) + "****" + tabUser.getTabMobile().substring(7, tabUser.getTabMobile().length()));
                } else if (tu == null) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("code", 4);
                    map.put("msg", "请重新登录");
                    strToJson(map);
                    return null;
                }

            }
        }


        Map<String, Object> map = new HashMap<String, Object>();
        if (!list.isEmpty()) {
            map.put("code", 0);
            map.put("msg", "获取成功");
            map.put("data", list);

            map.put("count", list1.size());

        } else {
            map.put("code", 2);
            map.put("msg", "读取失败");

        }


        strToJson(map);


        return null;
    }

    //修改密码
    @Action(value = "updateUserPwd")
    public String updateUserPwd() {

        TabUser tu = null;
        if (username != null) {
            tu = userService.findUser(username, password);
        }
        if (tu == null) {

            tu = userService.findUser(username); //在查下手机号码

            if (tu != null && !tu.getTabPassword().equals(password)) {
                tu = null;
            }
        }

        Map<String, Object> map = new HashMap<String, Object>();
        if (tu != null) {
            tu.setTabPassword(rePassword);
            userService.updateClient(tu);
            map.put("code", 0);
            map.put("msg", "修改成功");


        } else {
            map.put("code", 2);
            map.put("msg", "用户密码错误或用户已被删除");

        }


        strToJson(map);
        return null;
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }


    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public int getUser_type() {
        return user_type;
    }


    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public String getSeachInsuranceJsonStr() {
        return SeachInsuranceJsonStr;
    }

    public void setSeachInsuranceJsonStr(String seachInsuranceJsonStr) {
        SeachInsuranceJsonStr = seachInsuranceJsonStr;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserIdUpload() {
        return userIdUpload;
    }

    public void setUserIdUpload(int userIdUpload) {
        this.userIdUpload = userIdUpload;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getZcId() {
        return zcId;
    }

    public void setZcId(String zcId) {
        this.zcId = zcId;
    }

    public String getShouru() {
        return shouru;
    }

    public void setShouru(String shouru) {
        this.shouru = shouru;
    }

    public String getZhichu() {
        return zhichu;
    }

    public void setZhichu(String zhichu) {
        this.zhichu = zhichu;
    }

    public String getAllAmount() {
        return AllAmount;
    }

    public void setAllAmount(String allAmount) {
        AllAmount = allAmount;
    }
}
