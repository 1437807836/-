package com.yxbx.action;


import java.io.*;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import java.util.Map;


import javax.annotation.Resource;
import javax.persistence.IdClass;
import javax.servlet.http.HttpServletRequest;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yxbx.bean.BxState;
import com.yxbx.bean.SeachInsurance;
import com.yxbx.pojo.TabAgent;
import com.yxbx.pojo.TabBxState;
import com.yxbx.pojo.TabInsuranceFile;
import com.yxbx.pojo.TabInsurer;
import com.yxbx.pojo.TabInsurerPolicy;
import com.yxbx.pojo.TabPayPerson;
import com.yxbx.pojo.TabScheduleOfInsurance;
import com.yxbx.pojo.TabScheduleOfInsuranceTrue;
import com.yxbx.pojo.TabTeam;
import com.yxbx.pojo.TabUser;
import com.yxbx.service.AgentService;
import com.yxbx.service.InsuranceFileService;
import com.yxbx.service.InsurerPolicyService;
import com.yxbx.service.InsurerService;
import com.yxbx.service.PayPersonService;
import com.yxbx.service.ScheduleOfInsuranceService;
import com.yxbx.service.ScheduleOfInsuranceTrueService;
import com.yxbx.service.TeamService;
import com.yxbx.service.UserService;
import com.yxbx.util.ExcelUtil;
import com.yxbx.util.Util;


@Controller
@Scope("prototype")
@Namespace("/")
@ParentPackage("default")
@InterceptorRef("defStack")
public class NewFileAction extends BaseAction {
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    int sFileId;
    String mobile;
    int user_type = 0;

    String username;//用户名
    String password;//用户密码
    String name;//用户姓名
    String SeachInsuranceJsonStr = null;
    String updateParams = "{\"tabYxState\":\"帅气\"}";

    String wh_person = "admin";//维护人
    int userId = 0;

    int soiId = 0;//要修改保险单的Id
    String insuranceName;//保险公司名字

    int limit;//每页限制条数

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getFalseSId() {
        return falseSId;
    }

    public void setFalseSId(int falseSId) {
        this.falseSId = falseSId;
    }

    int falseSId;
    String insuranceId;//保险公司id
    @Resource
    UserService userService;
    @Resource
    ScheduleOfInsuranceService soiService;
    @Resource
    ScheduleOfInsuranceTrueService soiTrueService;
    @Resource
    InsurerService insurerService;
    @Resource
    PayPersonService payService;
    @Resource
    TeamService tService;
    @Resource
    AgentService aService;
    @Resource
    InsuranceFileService ifService;
    @Resource
    InsurerPolicyService ipSrevice;
    @Resource
    InsurerService inSrevice;

    String soiLists;//  确认保存的 json
    String tsoiStrJson;
    int payPersonId;
    int pageSize = 10;
    int page;
    String tabPayName;
    String tabBankName;
    String tabPayCard;//卡号
    int teamId;
    int agentId;


    int trueSId;


    String tabAgentSex;//性别
    String TeamName;
    String agentName;
    String IdCard;//身份证号
    String channelName;//渠道名
    String startTime;//开始时间
    String endTime;//到期时间
    String expenditure;//支出比例
    String income;//收入比例
    String invoice;//开票比例
    String isSuccessive;//是否续保
    String nature;//性质
    String quality;//核定质量
    int insurerPolicyId;//政策id;
    int insurerId;//保险公司id
    String payId;//付款回单附件
    String payOpenId;//付款回单附件

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    String imgUrl;

    public String getPayOpenId() {
        return payOpenId;
    }

    public void setPayOpenId(String payOpenId) {
        this.payOpenId = payOpenId;
    }

    String jsonInsurerPolicyStr;//搜索str

    String bxName;//保险名称
    String bxJson;//保险种类

    int bxId;//保险Id
    int auditingState;//1通过审核 并向代理人打款，2像代理人打款 未完成审核
    String annotationsStr;
    int payFileState;

    //查询附件
    public int getPayFileState() {
        return payFileState;
    }

    public void setPayFileState(int payFileState) {
        this.payFileState = payFileState;
    }

    @Action(value = "queryFile")
    public String queryFile() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<TabInsuranceFile> tif = new ArrayList<TabInsuranceFile>();
        if (userId > 0) {
            TabUser t = userService.queryAllByUserId(userId);
            if (t != null && t.getTabIsDelete() == 0) {
                if (trueSId > 0) {
                    tif = ifService.queryInsuranceFileByInsuranceTureId(trueSId);

                } else if (falseSId > 0) {
                    tif = ifService.queryInsuranceFileByInsuranceId(falseSId);
                } else if (payFileState > 0) {
                    if (payFileState == 1) {
                        tif = ifService.queryInsuranceFileByPayId(Integer.parseInt(payId));
                    } else if (payFileState == 2) {
                        tif = ifService.queryInsuranceFileByPayOpenId(Integer.parseInt(payOpenId));
                    }
                }
                map.put("data", tif);
                map.put("code", 0);
                map.put("msg", "查询成功");
            } else {
                map.put("code", 2);
                map.put("msg", "权限不足");
            }
        } else {
            map.put("code", 3);
            map.put("msg", "请登录");
        }

        strToJson(map);
        return null;
    }

    //更新险种
    @Action(value = "queryAllfile")
    public String queryAllfile() {
        Map<String, Object> map = new HashMap<String, Object>();


        if (userId > 0) {
            TabUser t = userService.queryAllByUserId(userId);
            if (t != null && t.getTabIsDelete() == 0) {
                if (trueSId > 0) {

                } else if (falseSId > 0) {

                }
            } else {
                map.put("code", 2);
                map.put("msg", "权限不足");
            }
        } else {
            map.put("code", 3);
            map.put("msg", "请登录");
        }

        strToJson(map);
        return null;
    }

    //更新险种
    @Action(value = "updateBxState")
    public String updateBxState() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userId > 0) {
            TabUser t = userService.queryAllByUserId(userId);
            if (t != null && t.getTabIsDelete() == 0) {
                ipSrevice.updateBxStateById(bxName, bxId);
                map.put("code", 0);
                map.put("msg", "更新成功");
            } else {
                map.put("code", 0);
                map.put("msg", "权限不足");
            }
        } else {
            map.put("code", 0);
            map.put("msg", "请登录");
        }

        strToJson(map);
        return null;
    }

    //添加险种
    @Action(value = "AddBxState")
    public String AddBxState() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userId > 0) {
            TabUser t = userService.queryAllByUserId(userId);
            if (t != null && t.getTabIsDelete() == 0) {
                ipSrevice.addBxStateById(bxName);
                map.put("code", 0);
                map.put("msg", "添加成功");
            } else {
                map.put("code", 0);
                map.put("msg", "权限不足");
            }
        } else {
            map.put("code", 0);
            map.put("msg", "请登录");
        }

        strToJson(map);
        return null;
    }

    //查询险种
    @Action(value = "queryBxState")
    public String queryBxState() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<TabBxState> list = ipSrevice.queryBxStateAll();
        map.put("code", 0);
        map.put("msg", "查询成功");
        map.put("data", list);
        strToJson(map);
        return null;
    }

    //查询保险政策
    @Action(value = "queryInsurerPolicy")
    public String queryInsurerPolicy() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (jsonInsurerPolicyStr != null && !jsonInsurerPolicyStr.equals("")) {
            Gson gson = new Gson();
            if (jsonInsurerPolicyStr != null && jsonInsurerPolicyStr.contains("{") && jsonInsurerPolicyStr.length() > 3) {
                //String[] s=	jsonInsurerPolicyStr.split("{");
                String s = String.valueOf(jsonInsurerPolicyStr.charAt(1));//判斷前端拼接是否有錯誤,多個逗號
                if (s.trim().equals(",")) {
                    jsonInsurerPolicyStr = "{" + jsonInsurerPolicyStr.substring(2, jsonInsurerPolicyStr.length());
                }

            }
            TabInsurerPolicy tabSoi = gson.fromJson(jsonInsurerPolicyStr, TabInsurerPolicy.class);

            List<TabInsurerPolicy> list = ipSrevice.queryAllInsurerPolicy(tabSoi, pageSize, page);
            List<TabInsurerPolicy> list1 = ipSrevice.queryInsurerPolicyAll(tabSoi);
            map.put("code", 0);
            map.put("msg", "查询成功");
            map.put("data", list);
            map.put("count", list1.size());
            //  map.put("data", count);
        } else {

            List<TabInsurerPolicy> list = ipSrevice.queryAllInsurerPolicy(null, pageSize, page);
            List<TabInsurerPolicy> list1 = ipSrevice.queryInsurerPolicyAll();
            // map.put("data", count);
            map.put("code", 0);
            map.put("msg", "查询成功");
            map.put("data", list);
            map.put("count", list1.size());
        }


        strToJson(map);
        return null;
    }

    @Action(value = "queryUnInsurerPolicy")
    public String queryUnInsurerPolicy() {
        Map<String, Object> map = new HashMap<String, Object>();
        TabInsurerPolicy list = ipSrevice.queryInsurerPolicyById(insurerId);

        map.put("code", 0);
        map.put("msg", "查询成功");
        map.put("data", list);
        strToJson(map);
        return null;
    }

    //审核保险政策
    @Action(value = "updateInsurerPolicy")
    public String updateInsurerPolicy() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userId > 0) {
            TabUser tu = userService.queryAllByUserId(userId);
            if (tu != null && tu.getTabIsDelete() == 0 && tu.getTabUserType() < 3) {
                TabInsurerPolicy tip = ipSrevice.queryInsurerPolicyById(insurerPolicyId);
                if (trueSId == 1) {
                    if (tip.getTabIsDelete().equals("0")) {
                        tip.setTabExamineAndVerify("是");
                        tip.setTabExaminePerson(tu.getTabUsername());
                        tip.setTabExamineTime(new Timestamp(System.currentTimeMillis()));
                        ipSrevice.updateInsurerPolicy(tip);
                        map.put("code", 0);
                        map.put("msg", "审核通过");
                    } else {
                        map.put("code", 4);
                        map.put("msg", "该政策已经被删除");
                    }
                } else if (trueSId == 2) {
                    if (tip.getTabIsDelete().equals("0")) {
                        tip.setTabExamineAndVerify("未通过");
                        tip.setTabExaminePerson(tu.getTabUsername());
                        tip.setTabExamineTime(new Timestamp(System.currentTimeMillis()));
                        ipSrevice.updateInsurerPolicy(tip);
                        map.put("code", 0);
                        map.put("msg", "操作成功，已驳回");
                    } else {
                        map.put("code", 4);
                        map.put("msg", "该政策已经被删除");
                    }

                }

            } else {
                map.put("code", 3);
                map.put("msg", "权限不足");
            }
        } else {
            map.put("code", 2);
            map.put("msg", "请登录");
        }
        strToJson(map);
        return null;

    }

    //删除保险政策
    @Action(value = "deleteInsurerPolicy")
    public String deleteInsurerPolicy() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userId > 0) {
            TabUser tu = userService.queryAllByUserId(userId);
            if (tu != null) {
                TabInsurerPolicy tip = ipSrevice.queryInsurerPolicyById(insurerPolicyId);
                if (tip.getTabIsDelete().equals("0")) {
                    tip.setTabIsDelete("1");
                    ipSrevice.updateInsurerPolicy(tip);
                    map.put("code", 0);
                    map.put("msg", "删除成功");
                } else {
                    map.put("code", 4);
                    map.put("msg", "该政策已经被删除");
                }
            } else {
                map.put("code", 3);
                map.put("msg", "权限不足");
            }
        } else {
            map.put("code", 2);
            map.put("msg", "请登录");
        }
        strToJson(map);
        return null;

    }

    //增加保险政策
    @Action(value = "addinsurerPolicy")
    public String addinsurerPolicy() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userId > 0) {
            TabUser tu = userService.queryAllByUserId(userId);
            if (tu != null && tu.getTabUserType() < 3 && tu.getTabIsDelete() == 0) {
                TabInsurerPolicy tip = new TabInsurerPolicy();
                TabInsurer tb = insurerService.queryInsurerById(insurerId);

                if (agentId > 0) {
                    TabAgent ta = aService.queryagentById(agentId);
                    if (ta != null) {
                        Gson gson = new Gson();
                        BxState bx = gson.fromJson(bxJson, BxState.class);
                        List<TabBxState> list = bx.getListBxState();
                        for (TabBxState tabBxState : list) {
                            tip.setTabAgent(ta.getTabAgentName());
                            tip.setTabInsurerId(insurerId);
                            tip.setTabInsurerName(tb.getTabInsurerName());
                            tip.setTabInsurerId(tb.getId());
                            tip.setTabAgentId(ta.getTabAgentId());
                            tip.setTabState(tabBxState.getTabState());
                            tip.setTabChannelName(channelName);
                            tip.setTabEndTime(Util.toTimestamp(endTime));
                            tip.setTabExamineAndVerify("否");//审核状态
                            tip.setTabExaminePerson("待审核");
                            tip.setTabExamineTime(null);
                            tip.setTabBxStateId(tabBxState.getId());

                            tip.setTabExpenditure(tabBxState.getTabExpenditure());//支出
                            tip.setTabIncome(tabBxState.getTabIncome());//收入
                            tip.setTabInvoice(tabBxState.getTabInvoice());//开票
                            tip.setTabIsSuccessive(isSuccessive);
                            tip.setTabNature(nature);//性质
                            tip.setTabQuality(quality);
                            tip.setTabStartTime(new Timestamp(System.currentTimeMillis()));
                            tip.setTabSubmitTime(new Timestamp(System.currentTimeMillis()));
                            tip.setTabWritePerson(tu.getTabUsername());
                            tip.setTabIsDelete("0");
                            ipSrevice.addInsurerPolicy(tip);
                        }

                        map.put("code", 0);
                        map.put("msg", "添加成功");

                    }

                } else {
                    map.put("code", 4);
                    map.put("msg", "请添加代理人");
                }

            } else {
                map.put("code", 3);
                map.put("msg", "权限不足");
            }
        } else {
            map.put("code", 2);
            map.put("msg", "请登录");
        }

        strToJson(map);
        return null;
    }

    //查詢团队
    @Action(value = "findTeam")
    public String findTeam() {
        Map<String, Object> map = new HashMap<String, Object>();
        TabTeam tt = new TabTeam();
        tt.setTabTeamName(TeamName);
        if (tt.getTabTeamName() == null || !tt.getTabTeamName().equals("")) {
            tt = null;
        }
        List<TabTeam> list = tService.queryAllTabTeam(tt, pageSize, page);
        List<TabTeam> list1 = tService.queryTabTeamAll();
        if (list != null && !list.isEmpty()) {

            map.put("data", list);
            map.put("count", list1.size());
            map.put("code", 0);
            map.put("msg", "查詢成功");
        } else {
            map.put("count", 0);
            map.put("code", 0);
            map.put("msg", "无结果集合");
        }


        strToJson(map);
        return null;
    }

    //删除团队
    @Action(value = "deleteTeam")
    public String deleteTeam() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userId > 0) {
            TabUser t = userService.queryAllByUserId(userId);
            if (t != null && t.getTabIsDelete() == 0) {
                if (t.getTabUserType() < 3) {
                    TabTeam tt = tService.queryTabTeamById(teamId);
                    tt.setTabFounder(t.getTabUsername());
                    tt.setTabIsDelete(1);
                    tt.setTabWriteDate(new Timestamp(System.currentTimeMillis()));
                    tService.updateTabTeam(tt);
                    List<TabAgent> list = aService.queryAgentById(teamId);
                    for (TabAgent tabAgent : list) {
                        tabAgent.setTabTeamId(null);
                        tabAgent.setTabTeamName(null);
                        aService.updateAgent(tabAgent);
                    }

                    map.put("code", 0);
                    map.put("msg", "删除成功");

                } else {
                    map.put("code", 3);
                    map.put("msg", "权限不足");
                }
            } else {
                map.put("code", 3);
                map.put("msg", "你的账户不存在，或者已被删除");
            }
        } else {
            map.put("code", 2);
            map.put("msg", "请登录");
        }

        strToJson(map);
        return null;
    }

    //创建团队
    @Action(value = "addTeam")
    public String addTeam() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userId > 0) {
            TabUser t = userService.queryAllByUserId(userId);
            if (t != null && t.getTabIsDelete() == 0) {
                if (t.getTabUserType() < 3) {
                    TabTeam tt = new TabTeam();
                    tt.setTabFounder(t.getTabUsername());
                    tt.setTabFounderTime(new Timestamp(System.currentTimeMillis()));
                    tt.setTabIsDelete(0);
                    tt.setTabWriteDate(new Timestamp(System.currentTimeMillis()));
                    tt.setTabTeamName(TeamName);
                    tService.addTabTeam(tt);
                    map.put("code", 0);
                    map.put("msg", "创建成功");

                } else {
                    map.put("code", 3);
                    map.put("msg", "权限不足");
                }
            } else {
                map.put("code", 3);
                map.put("msg", "你的账户不存在，或者已被删除");
            }
        } else {
            map.put("code", 2);
            map.put("msg", "请登录");
        }

        strToJson(map);
        return null;
    }

    //查詢代理人
    @Action(value = "findAgent")
    public String findAgent() {
        Map<String, Object> map = new HashMap<String, Object>();
        TabAgent ta = new TabAgent();
        ta.setTabAgentName(agentName);
        ta.setTabMobile(mobile);
        ta.setTabTeamName(TeamName);
        List<TabAgent> list = aService.queryAllAgent(ta, limit, page);
        List<TabAgent> list1 = aService.queryAgentAll();
        if (list != null && !list.isEmpty()) {

            map.put("data", list);
            map.put("count", list1.size());
            map.put("code", 0);
            map.put("msg", "查詢成功");
        } else {
            map.put("count", 0);
            map.put("code", 0);
            map.put("msg", "无结果集合");
        }


        strToJson(map);
        return null;
    }

    //刪除
    @Action(value = "deleteAgent")
    public String deleteAgent() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userId != 0) {
            TabUser t = userService.queryAllByUserId(userId);
            int userType = t.getTabUserType();
            if (userType < 3 && t.getTabIsDelete() == 0) {
                TabAgent ta = aService.queryagentById(agentId);
                ta.setTabIsDelete(1);
                ta.setTabWriteDate(new Timestamp(System.currentTimeMillis()));
                ta.setTabAgentFounder(t.getTabUsername());
                aService.updateAgent(ta);
                map.put("code", 0);
                map.put("msg", "删除成功");

            } else {
                map.put("code", 3);
                map.put("msg", "权限不足");
            }


        } else {
            map.put("code", 2);
            map.put("msg", "请登录");
        }


        strToJson(map);
        return null;
    }

    //修改
    @Action(value = "updateAgent")
    public String updateAgent() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userId != 0) {
            TabUser t = userService.queryAllByUserId(userId);
            int userType = t.getTabUserType();
            if (t.getTabIsDelete() == 0) {
                if (teamId > 0) {
                    TabTeam tt = tService.queryTabTeamById(teamId);
                    TabAgent ta = aService.queryagentById(agentId);
                    if (agentName != null && !agentName.equals("")) {
                        ta.setTabAgentName(agentName);
                    }
                    if (tabPayCard != null && !tabPayCard.equals("")) {
                        ta.setTabBankCardNum(tabPayCard);
                    }
                    if (IdCard != null && !IdCard.equals("")) {
                        ta.setTabIdCardNum(IdCard);
                    }
                    if (tabBankName != null && !tabBankName.equals("")) {
                        ta.setTabBankName(tabBankName);
                    }
                    if (tabAgentSex != null && !tabAgentSex.equals("")) {
                        ta.setTabAgentSex(tabAgentSex);
                    }
                    if (mobile != null && !mobile.equals("")) {
                        ta.setTabMobile(mobile);
                    }


                    ta.setTabTeamId(tt.getId());
                    ta.setTabWriteDate(new Timestamp(System.currentTimeMillis()));
                    ta.setTabAgentFounder(t.getTabUsername());
                    ta.setTabTeamName(tt.getTabTeamName());
                    aService.updateAgent(ta);
                    List<TabAgent> list1 = aService.queryAgentAll();
                    map.put("code", 0);
                    map.put("data", list1);
                    map.put("msg", "修改成功");

                } else {

                    TabAgent ta = aService.queryagentById(agentId);
                    if (agentName != null && !agentName.equals("")) {
                        ta.setTabAgentName(agentName);
                    }
                    if (tabPayCard != null && !tabPayCard.equals("")) {
                        ta.setTabBankCardNum(tabPayCard);
                    }
                    if (IdCard != null && !IdCard.equals("")) {
                        ta.setTabIdCardNum(IdCard);
                    }
                    if (tabBankName != null && !tabBankName.equals("")) {
                        ta.setTabBankName(tabBankName);
                    }
                    if (tabAgentSex != null && !tabAgentSex.equals("")) {
                        ta.setTabAgentSex(tabAgentSex);
                    }
                    if (mobile != null && !mobile.equals("")) {
                        ta.setTabMobile(mobile);
                    }

                    ta.setTabWriteDate(new Timestamp(System.currentTimeMillis()));
                    ta.setTabAgentFounder(t.getTabUsername());
                    aService.updateAgent(ta);
                    List<TabAgent> list1 = aService.queryAgentAll();
                    map.put("code", 0);
                    map.put("data", list1);
                    map.put("msg", "修改成功");
                }
            } else {
                map.put("code", 3);
                map.put("msg", "权限不足");
            }


        } else {
            map.put("code", 2);
            map.put("msg", "请登录");
        }


        strToJson(map);
        return null;
    }

    //创建代理人
    @Action(value = "addAgent")
    public String addAgent() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userId != 0) {

            TabUser t = userService.queryAllByUserId(userId);
            int userType = t.getTabUserType();
            if (t.getTabIsDelete() == 0) {
                TabAgent ta1 = aService.queryagentByAgentName(agentName);
                if (ta1 == null) {


                    if (teamId > 0) {
                        TabTeam tt = tService.queryTabTeamById(teamId);
                        TabAgent ta = new TabAgent();

                        ta.setTabAgentName(agentName);
                        ta.setTabIsDelete(0);
                        ta.setTabIdCardNum(IdCard);
                        ta.setTabBankCardNum(tabPayCard);
                        ta.setTabBankName(tabBankName);
                        ta.setTabAgentSex(tabAgentSex);
                        ta.setTabAgentFounder(t.getTabUsername());
                        ta.setTabMobile(mobile);
                        ta.setTabTeamId(tt.getId());
                        ta.setTabWriteDate(new Timestamp(System.currentTimeMillis()));
                        ta.setTabTeamName(tt.getTabTeamName());
                        aService.addAgent(ta);
                        map.put("code", 0);
                        map.put("msg", "添加成功");
                    } else {

                        TabAgent ta = new TabAgent();
                        ta.setTabAgentName(agentName);
                        ta.setTabIsDelete(0);
                        ta.setTabIdCardNum(IdCard);
                        ta.setTabBankName(tabBankName);

                        ta.setTabAgentSex(tabAgentSex);
                        ta.setTabAgentFounder(t.getTabUsername());
                        ta.setTabMobile(mobile);
                        ta.setTabBankCardNum(tabPayCard);
                        ta.setTabWriteDate(new Timestamp(System.currentTimeMillis()));
                        aService.addAgent(ta);
                        map.put("code", 0);
                        map.put("msg", "添加成功");
                    }
                } else {

                    map.put("code", 4);
                    map.put("msg", agentName + ",这个代理人已存在");
                }
            } else {
                map.put("code", 3);
                map.put("msg", "权限不足");
            }


        } else {
            map.put("code", 2);
            map.put("msg", "请登录");
        }


        strToJson(map);
        return null;
    }

    //删除付款人账号
    @Action(value = "deletePayPersonAcount")
    public String deletePayPersonAcount() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userId != 0) {
            TabUser tu = userService.queryAllByUserId(userId);
            if (tu != null && tu.getTabIsDelete() == 0 && tu.getTabUserType() < 3) {
                TabPayPerson tp = payService.queryInsurerById(payPersonId);
                tp.setTabIsDelete(1);
                payService.updateInsurer(tp);
                map.put("code", 0);
                map.put("msg", "删除成功");
            } else {
                map.put("code", 3);
                map.put("msg", "权限不足");
            }

        } else {
            map.put("code", 2);
            map.put("msg", "请登录");
        }


        strToJson(map);
        return null;
    }

    //查询付款人账号
    @Action(value = "queryPayPersonAcount")
    public String queryPayPersonAcount() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (tabBankName != null && tabBankName.equals("")) {
            tabBankName = null;
        }
        List<TabPayPerson> list = payService.queryAllPayPerson(tabBankName, pageSize, page);
        List<TabPayPerson> list1 = payService.queryInsurerAll();
        if (list != null && !list.isEmpty()) {
            map.put("code", 0);
            map.put("msg", "查询");
            map.put("data", list);
            map.put("count", list1.size());
        } else {
            map.put("code", 2);
            map.put("msg", "无结果集");
        }
        strToJson(map);
        return null;
    }

    //增加付款人账号
    @Action(value = "addPayPersonAcount")
    public String addPayPersonAcount() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userId != 0) {
            TabUser tu = userService.queryAllByUserId(userId);

            if (tu != null && tu.getTabIsDelete() == 0) {
                if (tu.getTabUserType() < 3) {
                    TabPayPerson t = new TabPayPerson();
                    t.setTabPayCard(tabPayCard);
                    t.setTabPayName(tabPayName);
                    t.setTabBankName(tabBankName);
                    t.setTabWritePerson(tu.getTabUsername());
                    t.setTabIsDelete(0);
                    t.setTabWriteDate(new Timestamp(System.currentTimeMillis()));
                    payService.addInsurer(t);
                    map.put("code", 0);
                    map.put("msg", "操作成功");
                } else {
                    map.put("code", 4);
                    map.put("msg", "未知错误");
                }
            } else {
                map.put("code", 4);
                map.put("msg", "权限不足");
            }


        } else {
            map.put("code", 3);
            map.put("msg", "请登录");

        }
        strToJson(map);
        return null;
    }

    //修改保险公司
    @Action(value = "updateInsurance")
    public String updateInsurance() {
        Map<String, Object> map = new HashMap<String, Object>();
        int userType = 0;
        if (userId == 0) {
            map.put("code", 3);
            map.put("msg", "请登录");
            strToJson(map);
            return null;
        } else {
            TabUser tu = userService.queryAllByUserId(userId);
            userType = tu.getTabUserType();
            if (userType < 3 && tu.getTabIsDelete() == 0) {
                TabInsurer ti = insurerService.queryInsurerById(Integer.valueOf(insuranceId));
                ti.setTabInsurerName(insuranceName);
                ti.setTabUpdatePerson(tu.getTabUsername());

                ti.setTabWriteDate(new Timestamp(System.currentTimeMillis()));
                insurerService.updateInsurer(ti);
                map.put("code", 0);
                map.put("msg", "修改成功");
            } else {
                map.put("code", 2);
                map.put("msg", "权限不足");
            }
        }


        strToJson(map);

        return null;
    }

    //查询保险公司
    @Action(value = "queryInsurance")
    public String queryInsurance() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<TabInsurer> list = insurerService.queryAllInsurer(insuranceName, limit, page);
        List<TabInsurer> list1 = insurerService.queryInsurerAll();
        map.put("code", 0);
        map.put("data", list);
        map.put("msg", "查询成功");
        map.put("count", list1.size());

        strToJson(map);

        return null;
    }

    //增加保险公司
    @Action(value = "addInsurance")
    public String addInsurance() {
        Map<String, Object> map = new HashMap<String, Object>();
        int userType = 0;
        if (userId == 0) {
            map.put("code", 3);
            map.put("msg", "请登录");
            strToJson(map);
            return null;
        } else {
            TabUser tu = userService.queryAllByUserId(userId);
            userType = tu.getTabUserType();
            if (userType < 3 && tu.getTabIsDelete() == 0) {
                TabInsurer ti = new TabInsurer();
                ti.setTabInsurerName(insuranceName);
                ti.setTabWhPerson(tu.getTabUsername());

                ti.setTabWriteDate(new Timestamp(System.currentTimeMillis()));
                insurerService.addInsurer(ti);

                map.put("code", 0);
                map.put("msg", "增加成功");

            } else {
                map.put("code", 2);
                map.put("msg", "权限不足");
            }
        }


        strToJson(map);
        return null;
    }

    //修改保险单 正式
    @Action(value = "uploadSoi")
    public String uploadSoi() {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        if (userId > 0) {
            TabUser tu = userService.queryAllByUserId(userId);
            if (tu == null || tu.getTabIsDelete() != 0) {
                mapResult.put("code", 3);
                mapResult.put("msg", "登录已失效，请重新登录");
                strToJson(mapResult);
                return null;
            }
            if (tu.getTabUserType() > 2) {
                mapResult.put("code", 6);
                mapResult.put("msg", "权限不足");
                strToJson(mapResult);
                return null;
            }
            if (updateParams != null && updateParams.contains("{") && updateParams.length() > 3) {
                //String[] s=	jsonInsurerPolicyStr.split("{");
                String s = String.valueOf(updateParams.charAt(1));//判斷前端拼接是否有錯誤,多個逗號
                if (s.trim().equals(",")) {
                    updateParams = "{" + updateParams.substring(2, updateParams.length());
                }

            }
            TabScheduleOfInsuranceTrue tso = soiTrueService.queryAllById(soiId);
            if (tso != null) {
                Gson gson = new Gson();
                if (userId != tso.getTabUserId()) {
                    if (tu.getTabUserType() < 3 && tu.getTabIsDelete() == 0) {
                        TabScheduleOfInsuranceTrue tabSoi = gson.fromJson(updateParams, TabScheduleOfInsuranceTrue.class);
                        tso.setTabAgentName(tabSoi.getTabAgentName());
                        //tso.setTabAgentPay(tabSoi.getTabAgentPay());
                        tso.setTabAllAmount(tabSoi.getTabAllAmount());
                        tso.setTabAmountSate(tabSoi.getTabAmountSate());
                        // tso.setTabAnnotations(tabSoi.getTabAnnotations());
                        // tso.setTabAnnotationsPerson(tabSoi.getTabAnnotationsPerson());
                        tso.setTabCardState(tabSoi.getTabCardState());
                        tso.setTabCarNum(tabSoi.getTabCarNum());
                        tso.setTabCarState(tabSoi.getTabCarState());
                        // tso.setTabCdPerson(tabSoi.getTabCdPerson());
                        tso.setTabChannel(tabSoi.getTabChannel());
                        tso.setTabCommercialInsurance(tabSoi.getTabCommercialInsurance());
                        tso.setTabCommissionAmount(tabSoi.getTabCommissionAmount());
                        tso.setTabCommissionOther(tabSoi.getTabCommissionOther());
                        tso.setTabCompulsoryInsurance(tabSoi.getTabCompulsoryInsurance());
                        tso.setTabCustomerMobile(tabSoi.getTabCustomerMobile());
                        tso.setTabInsuranceCompanyName(tabSoi.getTabInsuranceCompanyName());
                        // tso.setTabIllustrate(tabSoi.getTabIllustrate());
                        tso.setTabEndDate(tabSoi.getTabEndDate());
                        // tso.setTabIsAuditing(tabSoi.getTabIsAuditing());
                        tso.setTabJbfjql(tabSoi.getTabJbfjql());
                        tso.setTabJbfsyl(tabSoi.getTabJbfsyl());
                        tso.setTabJfysAmount(tabSoi.getTabJfysAmount());
                        tso.setTabMessage(tabSoi.getTabMessage());
                        tso.setTabQdDate(tabSoi.getTabQdDate());
                        tso.setTabRealAmount(tabSoi.getTabRealAmount());
                        tso.setTabSafePerson(tabSoi.getTabSafePerson());
                        tso.setTabStartDate(tabSoi.getTabStartDate());
                        tso.setTabTax(tabSoi.getTabTax());
                        tso.setTabTeamOwnership(tabSoi.getTabTeamOwnership());
                        tso.setTabWriteDate(new Timestamp(System.currentTimeMillis()));
                        tso.setTabYgzhjqAmount(tabSoi.getTabYgzhjqAmount());
                        tso.setTabYgzhsyAmount(tabSoi.getTabYgzhsyAmount());
                        tso.setTabYxState(tabSoi.getTabYxState());
                        tso.setTabZbfJ(tabSoi.getTabZbfJ());
                        soiTrueService.updateScheduleOfInsurance(tso);
                        mapResult.put("code", 0);
                        mapResult.put("msg", "修改完成");
                    } else {
                        mapResult.put("code", 2);
                        mapResult.put("msg", "权限不足");
                    }
                } else {
                    TabScheduleOfInsuranceTrue tabSoi = gson.fromJson(updateParams, TabScheduleOfInsuranceTrue.class);
                    tso.setTabAgentName(tabSoi.getTabAgentName());
                    //tso.setTabAgentPay(tabSoi.getTabAgentPay());
                    tso.setTabAllAmount(tabSoi.getTabAllAmount());
                    tso.setTabAmountSate(tabSoi.getTabAmountSate());
                    // tso.setTabAnnotations(tabSoi.getTabAnnotations());
                    // tso.setTabAnnotationsPerson(tabSoi.getTabAnnotationsPerson());
                    tso.setTabCardState(tabSoi.getTabCardState());
                    tso.setTabCarNum(tabSoi.getTabCarNum());
                    tso.setTabCarState(tabSoi.getTabCarState());
                    // tso.setTabCdPerson(tabSoi.getTabCdPerson());
                    tso.setTabChannel(tabSoi.getTabChannel());
                    tso.setTabCommercialInsurance(tabSoi.getTabCommercialInsurance());
                    tso.setTabCommissionAmount(tabSoi.getTabCommissionAmount());
                    tso.setTabCommissionOther(tabSoi.getTabCommissionOther());
                    tso.setTabCompulsoryInsurance(tabSoi.getTabCompulsoryInsurance());
                    tso.setTabCustomerMobile(tabSoi.getTabCustomerMobile());
                    tso.setTabInsuranceCompanyName(tabSoi.getTabInsuranceCompanyName());
                    // tso.setTabIllustrate(tabSoi.getTabIllustrate());
                    tso.setTabEndDate(tabSoi.getTabEndDate());
                    // tso.setTabIsAuditing(tabSoi.getTabIsAuditing());
                    tso.setTabJbfjql(tabSoi.getTabJbfjql());
                    tso.setTabJbfsyl(tabSoi.getTabJbfsyl());
                    tso.setTabJfysAmount(tabSoi.getTabJfysAmount());
                    tso.setTabMessage(tabSoi.getTabMessage());
                    tso.setTabQdDate(tabSoi.getTabQdDate());
                    tso.setTabRealAmount(tabSoi.getTabRealAmount());
                    tso.setTabSafePerson(tabSoi.getTabSafePerson());
                    tso.setTabStartDate(tabSoi.getTabStartDate());
                    tso.setTabTax(tabSoi.getTabTax());
                    tso.setTabTeamOwnership(tabSoi.getTabTeamOwnership());
                    tso.setTabWriteDate(new Timestamp(System.currentTimeMillis()));
                    tso.setTabYgzhjqAmount(tabSoi.getTabYgzhjqAmount());
                    tso.setTabYgzhsyAmount(tabSoi.getTabYgzhsyAmount());
                    tso.setTabYxState(tabSoi.getTabYxState());
                    tso.setTabZbfJ(tabSoi.getTabZbfJ());
                    soiTrueService.updateScheduleOfInsurance(tso);
                    mapResult.put("code", 0);
                    mapResult.put("msg", "修改完成");
                }


            } else {
                mapResult.put("code", 4);
                mapResult.put("msg", "该条数据已被审核员提交或者删除");
            }
        } else {
            mapResult.put("code", 3);
            mapResult.put("msg", "登录已失效，请重新登录");
        }

        strToJson(mapResult);

        return null;
    }

    //新增 保险清单
    @Action(value = "addSoiToUser")
    public String addSoiToUser() {
        Map<String, Object> mapResult = new HashMap<String, Object>();

        if (userId > 0) {
            TabUser tu = userService.queryAllByUserId(userId);
            if (tu == null || tu.getTabIsDelete() != 0) {
                mapResult.put("code", 3);
                mapResult.put("msg", "登录已失效，请重新登录");
                strToJson(mapResult);
                return null;
            }
            if (updateParams != null && updateParams.contains("{") && updateParams.length() > 3) {
                //String[] s=	jsonInsurerPolicyStr.split("{");
                String s = String.valueOf(updateParams.charAt(1));//判斷前端拼接是否有錯誤,多個逗號
                if (s.trim().equals(",")) {
                    updateParams = "{" + updateParams.substring(2, updateParams.length());
                }

            }
            Gson gson = new Gson();
            TabScheduleOfInsurance tabSoi = gson.fromJson(updateParams, TabScheduleOfInsurance.class);
            if (tabSoi != null) {
                tabSoi.setTabWriteDate(new Timestamp(System.currentTimeMillis()));
                tabSoi.setTabUserId(userId);
                tabSoi.setTabWhPerson(tu.getTabUsername());
                tabSoi.setTabManagerUser(tu.getTabUsername());
                tabSoi.setTabCdPerson(tu.getTabUsername());
                soiService.addScheduleOfInsurance(tabSoi);
                mapResult.put("code", 0);
                mapResult.put("msg", "修改完成");
            }


        } else {
            mapResult.put("code", 3);
            mapResult.put("msg", "登录已失效，请重新登录");
        }

        strToJson(mapResult);

        return null;
    }


    //修改表 临时
    @Action(value = "uploadSoiToUser")
    public String uploadSoiToUser() {
        Map<String, Object> mapResult = new HashMap<String, Object>();

        if (userId > 0) {
            TabUser tu = userService.queryAllByUserId(userId);
            if (tu == null || tu.getTabIsDelete() != 0) {
                mapResult.put("code", 3);
                mapResult.put("msg", "登录已失效，请重新登录");
                strToJson(mapResult);
                return null;
            }
            if (updateParams != null && updateParams.contains("{") && updateParams.length() > 3) {
                //String[] s=	jsonInsurerPolicyStr.split("{");
                String s = String.valueOf(updateParams.charAt(1));//判斷前端拼接是否有錯誤,多個逗號
                if (s.trim().equals(",")) {
                    updateParams = "{" + updateParams.substring(2, updateParams.length());
                }

            }
            TabScheduleOfInsurance tso = soiService.queryAllById(soiId);
            if (tso != null) {
                Gson gson = new Gson();
                if (userId != tso.getTabUserId() && tu.getTabUserType() > 2) {
                    if (tu.getTabUserId() != tso.getTabUserId() && tu.getTabIsDelete() == 0) {
    				  /* String  tsoJsonStr=   gson.toJson(tso);//转成 json 格式
    	    			 JsonObject returnData = new JsonParser().parse(tsoJsonStr).getAsJsonObject();
    	    			 JsonObject updateParamsJsonObject = new JsonParser().parse(updateParams).getAsJsonObject();//得到Json
    	    			  String updateParams1[]=  updateParams.split(":");
    	    			 String  str1= updateParams1[0].substring(2, updateParams1[0].length()-1);//得到参数名
    	    			 returnData.addProperty(str1, updateParamsJsonObject.get(str1).getAsString());*/
                        TabScheduleOfInsurance tabSoi = gson.fromJson(updateParams, TabScheduleOfInsurance.class);
                        tso.setTabAgentName(tabSoi.getTabAgentName());
                        //tso.setTabAgentPay(tabSoi.getTabAgentPay());
                        tso.setTabAllAmount(tabSoi.getTabAllAmount());
                        tso.setTabAmountSate(tabSoi.getTabAmountSate());
                        // tso.setTabAnnotations(tabSoi.getTabAnnotations());
                        // tso.setTabAnnotationsPerson(tabSoi.getTabAnnotationsPerson());
                        tso.setTabCardState(tabSoi.getTabCardState());
                        tso.setTabCarNum(tabSoi.getTabCarNum());
                        tso.setTabCarState(tabSoi.getTabCarState());
                        // tso.setTabCdPerson(tabSoi.getTabCdPerson());
                        tso.setTabChannel(tabSoi.getTabChannel());
                        tso.setTabCommercialInsurance(tabSoi.getTabCommercialInsurance());
                        tso.setTabCommissionAmount(tabSoi.getTabCommissionAmount());
                        tso.setTabCommissionOther(tabSoi.getTabCommissionOther());
                        tso.setTabCompulsoryInsurance(tabSoi.getTabCompulsoryInsurance());
                        tso.setTabCustomerMobile(tabSoi.getTabCustomerMobile());
                        tso.setTabInsuranceCompanyName(tabSoi.getTabInsuranceCompanyName());
                        // tso.setTabIllustrate(tabSoi.getTabIllustrate());
                        tso.setTabEndDate(tabSoi.getTabEndDate());
                        // tso.setTabIsAuditing(tabSoi.getTabIsAuditing());
                        tso.setTabJbfjql(tabSoi.getTabJbfjql());
                        tso.setTabJbfsyl(tabSoi.getTabJbfsyl());
                        tso.setTabJfysAmount(tabSoi.getTabJfysAmount());
                        tso.setTabMessage(tabSoi.getTabMessage());
                        tso.setTabQdDate(tabSoi.getTabQdDate());
                        tso.setTabRealAmount(tabSoi.getTabRealAmount());
                        tso.setTabSafePerson(tabSoi.getTabSafePerson());
                        tso.setTabStartDate(tabSoi.getTabStartDate());
                        tso.setTabTax(tabSoi.getTabTax());
                        tso.setTabTeamOwnership(tabSoi.getTabTeamOwnership());
                        tso.setTabWriteDate(new Timestamp(System.currentTimeMillis()));
                        tso.setTabYgzhjqAmount(tabSoi.getTabYgzhjqAmount());
                        tso.setTabYgzhsyAmount(tabSoi.getTabYgzhsyAmount());
                        tso.setTabYxState(tabSoi.getTabYxState());
                        tso.setTabZbfJ(tabSoi.getTabZbfJ());
                        soiService.updateScheduleOfInsurance(tso);
                        mapResult.put("code", 0);
                        mapResult.put("msg", "修改完成");
                        // mapResult.put("updateParams", updateParamsJsonObject.get(str1).getAsString());
                    } else {
                        mapResult.put("code", 2);
                        mapResult.put("msg", "权限不足,无法修改他人的");
                    }
                } else {
    			/* String  tsoJsonStr=   gson.toJson(tso);//转成 json 格式
    			 JsonObject returnData = new JsonParser().parse(tsoJsonStr).getAsJsonObject();
    			 JsonObject updateParamsJsonObject = new JsonParser().parse(updateParams).getAsJsonObject();//得到Json
    			  String updateParams1[]=  updateParams.split(":");
    			 String  str1= updateParams1[0].substring(2, updateParams1[0].length()-1);//得到参数名
    			 returnData.addProperty(str1, updateParamsJsonObject.get(str1).getAsString());
    			 TabScheduleOfInsurance tabSoi= gson.fromJson(returnData, TabScheduleOfInsurance.class);
    			 SeachInsurance s=gson.fromJson(returnData, SeachInsurance.class);
    			 s.setTabStartDateStart(tabSoi.getTabStartDate());
    			 System.out.println( "格式" +gson.toJson(s));//转成 json 格式);

*/
                    TabScheduleOfInsurance tabSoi = gson.fromJson(updateParams, TabScheduleOfInsurance.class);
                    tso.setTabAgentName(tabSoi.getTabAgentName());
                    //tso.setTabAgentPay(tabSoi.getTabAgentPay());
                    tso.setTabAllAmount(tabSoi.getTabAllAmount());
                    tso.setTabAmountSate(tabSoi.getTabAmountSate());
                    // tso.setTabAnnotations(tabSoi.getTabAnnotations());
                    // tso.setTabAnnotationsPerson(tabSoi.getTabAnnotationsPerson());
                    tso.setTabCardState(tabSoi.getTabCardState());
                    tso.setTabCarNum(tabSoi.getTabCarNum());
                    tso.setTabCarState(tabSoi.getTabCarState());
                    // tso.setTabCdPerson(tabSoi.getTabCdPerson());
                    tso.setTabChannel(tabSoi.getTabChannel());
                    tso.setTabCommercialInsurance(tabSoi.getTabCommercialInsurance());
                    tso.setTabCommissionAmount(tabSoi.getTabCommissionAmount());
                    tso.setTabCommissionOther(tabSoi.getTabCommissionOther());
                    tso.setTabCompulsoryInsurance(tabSoi.getTabCompulsoryInsurance());
                    tso.setTabCustomerMobile(tabSoi.getTabCustomerMobile());
                    tso.setTabInsuranceCompanyName(tabSoi.getTabInsuranceCompanyName());
                    // tso.setTabIllustrate(tabSoi.getTabIllustrate());
                    tso.setTabEndDate(tabSoi.getTabEndDate());
                    // tso.setTabIsAuditing(tabSoi.getTabIsAuditing());
                    tso.setTabJbfjql(tabSoi.getTabJbfjql());
                    tso.setTabJbfsyl(tabSoi.getTabJbfsyl());
                    tso.setTabJfysAmount(tabSoi.getTabJfysAmount());
                    tso.setTabMessage(tabSoi.getTabMessage());
                    tso.setTabQdDate(tabSoi.getTabQdDate());
                    tso.setTabRealAmount(tabSoi.getTabRealAmount());
                    tso.setTabSafePerson(tabSoi.getTabSafePerson());
                    tso.setTabStartDate(tabSoi.getTabStartDate());
                    tso.setTabTax(tabSoi.getTabTax());
                    tso.setTabTeamOwnership(tabSoi.getTabTeamOwnership());
                    tso.setTabWriteDate(new Timestamp(System.currentTimeMillis()));
                    tso.setTabYgzhjqAmount(tabSoi.getTabYgzhjqAmount());
                    tso.setTabYgzhsyAmount(tabSoi.getTabYgzhsyAmount());
                    tso.setTabYxState(tabSoi.getTabYxState());
                    tso.setTabZbfJ(tabSoi.getTabZbfJ());
                    soiService.updateScheduleOfInsurance(tso);
                    mapResult.put("code", 0);
                    mapResult.put("msg", "修改完成");
                    // mapResult.put("updateParams", updateParamsJsonObject.get(str1).getAsString());
                }


            } else {
                mapResult.put("code", 4);
                mapResult.put("msg", "该条数据已被审核员提交或者删除");
            }
        } else {
            mapResult.put("code", 3);
            mapResult.put("msg", "登录已失效，请重新登录");
        }

        strToJson(mapResult);

        return null;
    }

    //新建
    @Action(value = "addTabScheduleOfInsurance")
    public String addTabScheduleOfInsurance() {
        Map<String, Object> mapResult = new HashMap<String, Object>();
        Gson gson = new Gson();


        TabScheduleOfInsurance tabSoi = gson.fromJson(tsoiStrJson, TabScheduleOfInsurance.class);
        if (tabSoi != null) {
            TabUser tu = userService.queryAllByUserId(tabSoi.getTabUserId());
            if (tu != null && tu.getTabIsDelete() == 0) {
                tabSoi.setTabWhPerson(tu.getTabUsername());
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                String date1 = df.format(new Date());
                tabSoi.setTabWriteDate(Timestamp.valueOf(date1));
                tabSoi.setId(null);
                soiService.addScheduleOfInsurance(tabSoi);

                mapResult.put("code", 0);
                mapResult.put("msg", "操作成功");
            } else {
                mapResult.put("code", 3);
                mapResult.put("msg", "上传者不存在");
            }

        } else {
            mapResult.put("code", 3);
            mapResult.put("msg", "上传失败");
        }


        strToJson(mapResult);
        return null;
    }

    @Action(value = "AuditingPost")    //查询待审核表单
    public String AuditingPost() {
        Map<String, Object> map = new HashMap<String, Object>();
        if (userId > 0) {
            TabUser tu = userService.queryAllByUserId(userId);
            if (tu != null) {
                if (tu.getTabIsDelete() != 0) {
                    map.put("code", 4);
                    map.put("msg", "没有足够的权限");
                    strToJson(map);
                    return null;
                }

            }
            Gson gson = new Gson();
            if (tu.getTabUserType() < 3) {
                TabScheduleOfInsurance t = soiService.queryAllById(soiId);
                if (t == null) {
                    map.put("code", 6);
                    map.put("msg", "该单号不存在");
                    strToJson(map);
                    return null;
                }
                t.setTabIsAuditing("已审核");
                if (auditingState == 1) {
                    tsoiStrJson = gson.toJson(t);
                    TabScheduleOfInsuranceTrue tabSoiTrue = gson.fromJson(tsoiStrJson, TabScheduleOfInsuranceTrue.class);

                    SeachInsurance sij = gson.fromJson(tsoiStrJson, SeachInsurance.class);
                    sij.setId(null);

                    List<TabScheduleOfInsuranceTrue> list = soiTrueService.queryAllByObject(sij, "");

                    if (list == null) {


                        tabSoiTrue.setTabIsAuditing("已审核");
                        tabSoiTrue.setTabAgentPay("未结算");
                        tabSoiTrue.setTabWhPerson(tu.getTabUsername());
                        tabSoiTrue.setTabWriteDate(new Timestamp(System.currentTimeMillis()));
                        tabSoiTrue.setTabAnnotationsPerson(tu.getTabUsername());

                        soiTrueService.addScheduleOfInsurance(tabSoiTrue);
                        int id = tabSoiTrue.getId();

                        List<TabInsuranceFile> listFile = ifService.queryInsuranceFileByInsuranceId(soiId);
                        for (TabInsuranceFile tabInsuranceFile : listFile) {
                            tabInsuranceFile.setTabInsuranceId(null);
                            tabInsuranceFile.setTabInsuranceTrueId(id);
                            ifService.updateInsuranceFile(tabInsuranceFile);
                        }
                        soiService.deleteTabScheduleOfInsurance(t);

                        map.put("code", 0);
                        map.put("msg", "通过审核");
                    } else if (list.isEmpty() || list.size() == 0) {


                        tabSoiTrue.setTabIsAuditing("已审核");
                        tabSoiTrue.setTabAgentPay("未结算");
                        tabSoiTrue.setTabWhPerson(tu.getTabUsername());
                        tabSoiTrue.setTabWriteDate(new Timestamp(System.currentTimeMillis()));
                        tabSoiTrue.setTabAnnotationsPerson(tu.getTabUsername());
                        soiTrueService.addScheduleOfInsurance(tabSoiTrue);
                        int id = tabSoiTrue.getId();

                        List<TabInsuranceFile> listFile = ifService.queryInsuranceFileByInsuranceId(soiId);
                        for (TabInsuranceFile tabInsuranceFile : listFile) {
                            tabInsuranceFile.setTabInsuranceId(null);
                            tabInsuranceFile.setTabInsuranceTrueId(id);
                            ifService.updateInsuranceFile(tabInsuranceFile);
                        }
                        soiService.deleteTabScheduleOfInsurance(t);
                        map.put("code", 0);
                        map.put("msg", "通过审核");
                    } else {
                        map.put("code", 3);
                        map.put("msg", "库中存在" + list.size() + "条类似保险单,请确认提交，操作失败");
                        map.put("data", list);

                    }

                } else if (auditingState == 2) {
                    t.setTabIsAuditing("已审核/待补充附件");
                    t.setTabAgentPay("未结算");
                    t.setTabWhPerson(tu.getTabUsername());
                    t.setTabWriteDate(new Timestamp(System.currentTimeMillis()));
                    t.setTabAnnotationsPerson(tu.getTabUsername());
                    soiService.updateScheduleOfInsurance(t);
                    map.put("code", 0);
                    map.put("msg", "已结清代理人款项");
                } else if (auditingState == 3) {
                    t.setTabIsAuditing("审核未通过");
                    t.setTabAgentPay("未结算");
                    t.setTabAnnotations(t.getTabAnnotations() + annotationsStr);
                    t.setTabWhPerson(tu.getTabUsername());
                    t.setTabWriteDate(new Timestamp(System.currentTimeMillis()));
                    t.setTabAnnotationsPerson(tu.getTabUsername());
                    soiService.updateScheduleOfInsurance(t);
                    map.put("code", 0);
                    map.put("msg", "驳回成功");
                }


            } else {
                map.put("code", 2);
                map.put("msg", "权限不足");
            }

        } else {
            map.put("code", 1);
            map.put("msg", "请登录");
        }

        strToJson(map);
        return null;
    }

    //一键上传
    @Action(value = "uploadMsg_read")
    public String queryDeviceState() throws IOException {


        Map<String, Object> mapResult = new HashMap<String, Object>();
        if (userId > 0) {
            TabUser tu = userService.queryAllByUserId(userId);
            if (tu.getTabIsDelete() != 0) {
                mapResult.put("code", 7);
                mapResult.put("msg", "无权操作");


                strToJson(mapResult);

                return null;
            }
            wh_person = tu.getTabUsername();

            File dir = new File(ServletActionContext.getServletContext().getRealPath("files"));
            //判断文件是否上传，如果上传的话将会创建该目录
            if (!dir.exists()) {
                dir.mkdir(); //创建该目录
            }

            //第一种文件上传的方法
            //声明文件输入流，为输入流指定文件路径
            //获取输出流，获取文件的文件地址及名称
            FileInputStream in = null;
            FileOutputStream out = null;
            Date date = new Date();
            String filePath = dir + "\\" + date.getTime() + "_" + wh_person + "_" + fileFileName;
            try {
                in = new FileInputStream(file);


                out = new FileOutputStream(filePath);

                byte[] b = new byte[1024 * 1024];//每次写入的大小
                int i = 0;
                while ((i = in.read(b)) > 0) {
                    out.write(b, 0, i);
                }
                in.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
            ExcelUtil el = new ExcelUtil();
            //	el.readXls("H:\\MobileFile\\工作状态记录1.xls");
            List<Map<String, Object>> list = el.readXlsx(filePath);
            List<TabScheduleOfInsurance> tsiList = new ArrayList<TabScheduleOfInsurance>();

            int i = 0;

            for (i = 1; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                if (map == null || map.isEmpty()) {
                    break;
                }


                TabAgent ttta = aService.queryagentByAgentName(String.valueOf(map.get("代理人姓名")));
			/*if(ttta==null){
				mapResult.put("code", 5);
				mapResult.put("msg", "发现单中第"+i+"行代理人数据库中不存在");
		     	strToJson(mapResult);
				return null;
			}else if(!String.valueOf(map.get("团队归属")).equals("")&&ttta.getTabTeamName().equals(String.valueOf(map.get("团队归属")))){
				mapResult.put("code", 5);
				mapResult.put("msg", "发现单中第"+i+"行代理人与团队归属不匹配");
		     	strToJson(mapResult);
		     	return null;
			}*/
                TabScheduleOfInsurance tsoi = new TabScheduleOfInsurance();
                String startDate = String.valueOf(map.get("批单日期"));
                if (startDate != null && !startDate.trim().equals("")) {
                    Timestamp t = Timestamp.valueOf(startDate + " 00:00:00");
                    tsoi.setTabStartDate(t);
                } else {
                    tsoi.setTabStartDate(null);
                }
                String endDate = String.valueOf(map.get("到期日"));
                if (endDate != null && !endDate.trim().equals("")) {
                    Timestamp t = Timestamp.valueOf(endDate + " 00:00:00");
                    tsoi.setTabEndDate(t);
                } else {
                    tsoi.setTabEndDate(null);
                }
                String qdDate = String.valueOf(map.get("签单日期"));
                if (qdDate != null && !qdDate.trim().equals("")) {
                    Timestamp t = Timestamp.valueOf(qdDate + " 00:00:00");
                    tsoi.setTabQdDate(t);
                } else {
                    tsoi.setTabQdDate(null);
                }
                tsoi.setTabCarNum(String.valueOf(map.get("车牌")));
                tsoi.setTabSafePerson(String.valueOf(map.get("被保险人")));
                tsoi.setTabYxState(String.valueOf(map.get("业务性质/保批单")));
                tsoi.setTabCardState(String.valueOf(map.get("刷卡性质")));
                tsoi.setTabChannel(String.valueOf(map.get("渠道归属")));
                String insuranceCompanyName = String.valueOf(map.get("保险公司/名   称"));
                List<TabInsurer> listI = inSrevice.queryAllInsurerByName(insuranceCompanyName);
                if (listI == null || listI.size() == 0) {
                    TabInsurer ti = new TabInsurer();
                    ti.setTabInsurerName(insuranceCompanyName);
                    ti.setTabUpdatePerson(wh_person);
                    ti.setTabWhPerson(wh_person);
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                    String date1 = df.format(new Date());
                    ti.setTabWriteDate(Timestamp.valueOf(date1));
                    inSrevice.addInsurer(ti);

                }
                tsoi.setTabInsuranceCompanyName(insuranceCompanyName);
                tsoi.setTabCompulsoryInsurance(Util.toBigDecimal(String.valueOf(map.get("交强险"))));
                tsoi.setTabCommercialInsurance(Util.toBigDecimal(String.valueOf(map.get("商业险"))));
                tsoi.setTabTax(Util.toBigDecimal(String.valueOf(map.get("车船税"))));
                tsoi.setTabAllAmount(Util.toBigDecimal(String.valueOf(map.get("总保费"))));
                tsoi.setTabAgentName(String.valueOf(map.get("代理人姓名")));
                tsoi.setTabTeamOwnership(String.valueOf(map.get("团队归属")));
                tsoi.setTabCarState(String.valueOf(map.get("车辆使用/性质")));
                tsoi.setTabBankNum(String.valueOf(map.get("开户行/卡号")));
                tsoi.setTabAmountSate(String.valueOf(map.get("收款状态")));
                tsoi.setTabMessage(String.valueOf(map.get("备注")));
                tsoi.setTabCdPerson(String.valueOf(map.get("出单员")));

                tsoi.setTabWhPerson(String.valueOf(map.get("维护人")));
                tsoi.setTabCustomerMobile(String.valueOf(map.get("客户电话")));
                tsoi.setTabYgzhjqAmount(Util.toBigDecimal(String.valueOf(map.get("营改增后/交强保费"))));
                tsoi.setTabYgzhsyAmount(Util.toBigDecimal(String.valueOf(map.get("营改增后/商业保费"))));
                tsoi.setTabJbfjql(Util.toBigDecimal(String.valueOf(map.get("净保费/交强率"))));
                tsoi.setTabJbfsyl(Util.toBigDecimal(String.valueOf(map.get("净保费/商业率"))));
                tsoi.setTabCommissionAmount(Util.toBigDecimal(String.valueOf(map.get("佣金合计"))));
                tsoi.setTabCommissionOther(String.valueOf(map.get("其他佣金")));
                tsoi.setTabJfysAmount(Util.toBigDecimal(String.valueOf(map.get("净费应收"))));
                tsoi.setTabXlsxName(filePath);
                String realAmount = String.valueOf(map.get("实收"));

                tsoi.setTabRealAmount(Util.toBigDecimal(realAmount));


                tsoi.setTabZbfJ(String.valueOf(map.get("总保费/净费")));
                tsoi.setTabAnnotations(String.valueOf(map.get("附注")));
                tsoi.setTabIllustrate(String.valueOf(map.get("说明")));
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                String date1 = df.format(new Date());
                tsoi.setTabWriteDate(Timestamp.valueOf(date1));

                tsoi.setTabManagerUser(wh_person);

                tsoi.setTabUserId(userId);
                tsiList.add(tsoi);
            }


            soiService.addScheduleOfInsuranceList(tsiList);


            if (!list.isEmpty()) {
                mapResult.put("code", 0);
                mapResult.put("msg", "上传成功");
                mapResult.put("data", tsiList);

            } else {
                mapResult.put("code", 2);
                mapResult.put("msg", "读取失败");

            }


        } else {
            mapResult.put("code", 3);
            mapResult.put("msg", "请重新登录");
        }

        strToJson(mapResult);


        return null;
    }

    @Action(value = "deletefile")
    public String deletefile() {
        Map<String, Object> mapResult = new HashMap<String, Object>();


        if (userId > 0) {
            TabUser tu = userService.queryAllByUserId(userId);
            if (tu.getTabIsDelete() != 0) {
                mapResult.put("code", 7);
                mapResult.put("msg", "无权操作");


                strToJson(mapResult);

                return null;
            }

            List<TabInsuranceFile> listFile = ifService.queryInsuranceFileByImgUrl(imgUrl);
            if (listFile == null || listFile.size() == 0) {
                mapResult.put("code", 3);
                mapResult.put("msg", "该附件不存在");
                strToJson(mapResult);
                return null;
            }
            if (falseSId > 0) {
                TabScheduleOfInsurance toi = soiService.queryAllById(falseSId);
                if (toi != null) {
                    if (tu.getTabUserType() < 3 || toi.getTabUserId() == userId) {
                        for (TabInsuranceFile tabInsuranceFile : listFile) {
                            ifService.deleteInsuranceFile(tabInsuranceFile);
                        }
                        mapResult.put("code", 0);
                        mapResult.put("msg", "操作成功");
                    } else {
                        mapResult.put("code", 7);
                        mapResult.put("msg", "无权操作");
                    }
                } else {
                    mapResult.put("code", 2);
                    mapResult.put("msg", "该清单没找到，可能移民去火星了");
                }

            } else if (trueSId > 0) {
                if (tu.getTabUserType() < 3) {
                    TabScheduleOfInsuranceTrue toi = soiTrueService.queryAllById(trueSId);
                    if (toi != null) {
                        if (tu.getTabUserType() < 3) {
                            for (TabInsuranceFile tabInsuranceFile : listFile) {
                                ifService.deleteInsuranceFile(tabInsuranceFile);
                            }
                            mapResult.put("code", 0);
                            mapResult.put("msg", "操作成功");
                        } else {
                            mapResult.put("code", 7);
                            mapResult.put("msg", "无权操作");
                        }
                    } else {
                        mapResult.put("code", 2);
                        mapResult.put("msg", "该清单没找到，可能移民去火星了");
                    }
                } else {
                    mapResult.put("code", 7);
                    mapResult.put("msg", "无权操作");
                }
            }


        } else {
            mapResult.put("code", 7);
            mapResult.put("msg", "请登录");
        }
        strToJson(mapResult);
        return null;
    }

    private File file;//文件
    private String fileFileName;//文件名称
    private String fileContentType; //文件类型
    private String filename;
    private InputStream inputStream;


    /**
     * 导入保单
     * @return
     * @throws Exception
     */
    @Action(value = "importiInsurance")
    public String importiInsurance() throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        String f = df.format(new Date());

        String returnMessage = "";
        String filePath = ServletActionContext.getServletContext().getRealPath("files") + "\\importiInsurance\\" + f + "\\";// + File.separator;
        File dir = new File(filePath);
        //判断文件是否上传，如果上传的话将会创建该目录
        if (!dir.exists()) {
            System.out.println("文件不存在1");
            dir.mkdir();
        }
        File fullFile = file;
        String fileName = fileFileName;
        String realFilePath = filePath + fileName;
        try {
            BufferedInputStream is = new BufferedInputStream(new FileInputStream(fullFile));
            BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(realFilePath));
            IOUtils.copy(is, os);
            is.close();
            os.flush();
            os.close();
            // 开始解析文件，添加保单
            returnMessage = "上传成功！后台正在添加保单，请稍后查看！";
            returnMessage = soiTrueService.addScheduleOfInsuranceTrueOfExcel(filePath, fileName, userId, username);
            if ("".equals(returnMessage.trim())) {
//                map.put("data", "");
                map.put("code", 0);
                map.put("msg", "导入成功！");
            } else {
//                map.put("data", "");
                map.put("code", 0);
                map.put("msg", returnMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace();
//            map.put("data", "");
            map.put("code", 2);
            map.put("msg", "上传失败！");
        }
        strToJson(map);
        return null;
    }

    @Action(value = "uploadfile")
    public String uploadfile() {
        Map<String, Object> mapResult = new HashMap<String, Object>();

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        String f = df.format(new Date());


        String path = ServletActionContext.getServletContext().getRealPath("files") + "\\" + f + "\\";
        File dir = new File(path);
        //判断文件是否上传，如果上传的话将会创建该目录
        if (!dir.exists()) {
            System.out.println("文件不存在");
            dir.mkdir();
        }
        System.out.println(path);
        System.out.println(dir);
        System.out.println(file);
        System.out.println(fileFileName);

        //第一种文件上传的方法
        //声明文件输入流，为输入流指定文件路径
        //获取输出流，获取文件的文件地址及名称
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(file);
            fileFileName = fileFileName.substring(fileFileName.length() - 4, fileFileName.length());

            fileFileName = (int) (1 + Math.random() * (10000000 - 1 + 1)) + System.currentTimeMillis() + fileFileName;
            out = new FileOutputStream(dir + "\\" + fileFileName);
            byte[] b = new byte[1024 * 1024];//每次写入的大小
            int i = 0;
            while ((i = in.read(b)) > 0) {
                out.write(b, 0, i);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }


        if (userId > 0) {

            TabUser tu = userService.queryAllByUserId(userId);
            if (tu != null && tu.getTabIsDelete() == 0) {
                if (falseSId > 0) {
                    TabScheduleOfInsurance soi = soiService.queryAllById(falseSId);
                    if (soi != null) {
                        if (soi.getTabUserId() == userId || tu.getTabUserType() < 3) {
                            TabInsuranceFile ti = new TabInsuranceFile();
                            ti.setTabFileAddress(f + "\\" + fileFileName);
                            ti.setTabInsuranceId(soi.getId());
                            ti.setTabUploadDate(new Timestamp(System.currentTimeMillis()));
                            ti.setTabUserId(userId);
                            ifService.addInsuranceFile(ti);
                            Map<String, Object> map1 = new HashMap<String, Object>();
                            map1.put("src", f + "\\" + fileFileName);
                            mapResult.put("data", map1);
                            mapResult.put("code", 0);
                            mapResult.put("msg", "上传成功");
                        } else {
                            mapResult.put("code", 2);
                            mapResult.put("msg", "无权操作");
                        }
                    }


                } else if (trueSId > 0) {
                    TabScheduleOfInsuranceTrue soit = soiTrueService.queryAllById(trueSId);
                    if (soit.getTabUserId() == userId || tu.getTabUserType() < 3) {
                        TabInsuranceFile ti = new TabInsuranceFile();
                        ti.setTabFileAddress(f + "\\" + fileFileName);
                        ti.setTabInsuranceTrueId(soit.getId());
                        ti.setTabUploadDate(new Timestamp(System.currentTimeMillis()));
                        ti.setTabUserId(userId);

                        ifService.addInsuranceFile(ti);
                        mapResult.put("code", 0);
                        mapResult.put("msg", "上传成功");
                        Map<String, Object> map1 = new HashMap<String, Object>();
                        map1.put("src", f + "\\" + fileFileName);
                        mapResult.put("msg", "上传成功");
                        mapResult.put("data", map1);
                    } else {
                        mapResult.put("code", 2);
                        mapResult.put("msg", "无权操作");
                    }

                } else if (payId != null && !payId.equals("")) {//上传付款凭证

                    if (tu.getTabUserType() < 3) {
                        String ps[] = payId.split(",");
                        for (String string : ps) {
                            if (string.trim().equals("0")) {
                                continue;
                            }

                            TabInsuranceFile ti = new TabInsuranceFile();
                            ti.setTabFileAddress(f + "\\" + fileFileName);
                            ti.setTabPayId(Integer.parseInt(string));
                            ti.setTabUploadDate(new Timestamp(System.currentTimeMillis()));
                            ti.setTabUserId(userId);
                            ifService.addInsuranceFile(ti);
                            mapResult.put("code", 0);
                            mapResult.put("msg", "上传成功");
                            Map<String, Object> map1 = new HashMap<String, Object>();
                            map1.put("src", f + "\\" + fileFileName);
                            mapResult.put("msg", "上传成功");
                            mapResult.put("data", map1);
                        }


                    } else {
                        mapResult.put("code", 2);
                        mapResult.put("msg", "无权操作");
                    }
                } else if (payOpenId != null && !payOpenId.equals("")) {//上传付款凭证

                    if (tu.getTabUserType() < 3) {
                        String ps[] = payOpenId.split(",");
                        for (String string : ps) {
                            if (string.trim().equals("0")) {
                                continue;
                            }

                            TabInsuranceFile ti = new TabInsuranceFile();
                            ti.setTabFileAddress(f + "\\" + fileFileName);
                            ti.setTabPayOpenId(Integer.parseInt(string));
                            ti.setTabUploadDate(new Timestamp(System.currentTimeMillis()));
                            ti.setTabUserId(userId);
                            ifService.addInsuranceFile(ti);
                            mapResult.put("code", 0);
                            mapResult.put("msg", "上传成功");
                            Map<String, Object> map1 = new HashMap<String, Object>();
                            map1.put("src", f + "\\" + fileFileName);
                            mapResult.put("msg", "上传成功");
                            mapResult.put("data", map1);
                        }


                    } else {
                        mapResult.put("code", 2);
                        mapResult.put("msg", "无权操作");
                    }
                } else {
                    mapResult.put("code", 3);
                    mapResult.put("msg", "无id");
                }
            } else {
                mapResult.put("code", 2);
                mapResult.put("msg", "无权操作");
            }

        } else {
            mapResult.put("code", 1);
            mapResult.put("msg", "请登录");
        }

        strToJson(mapResult);
        return null;
    }

    @Action(value = "newfile")
    public String newFile() {
        File dir = new File(ServletActionContext.getServletContext().getRealPath("files"));
        //判断文件是否上传，如果上传的话将会创建该目录
        if (!dir.exists()) {
            dir.mkdir(); //创建该目录
        }
        System.out.println(file);
        System.out.println(fileFileName);

        //第一种文件上传的方法
        //声明文件输入流，为输入流指定文件路径
        //获取输出流，获取文件的文件地址及名称
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(file);
            out = new FileOutputStream(dir + "\\" + fileFileName);
            byte[] b = new byte[1024 * 1024];//每次写入的大小
            int i = 0;
            while ((i = in.read(b)) > 0) {
                out.write(b, 0, i);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return SUCCESS;
    }

    public String getUpdateParams() {
        return updateParams;
    }

    public void setUpdateParams(String updateParams) {
        this.updateParams = updateParams;
    }

    public int getSoiId() {
        return soiId;
    }

    public void setSoiId(int soiId) {
        this.soiId = soiId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSoiLists() {
        return soiLists;
    }

    public void setSoiLists(String soiLists) {
        this.soiLists = soiLists;
    }


    public String downFile() {
        System.out.println(filename);
        inputStream = ServletActionContext.getServletContext().getResourceAsStream("/files/" + filename);
        System.out.println(inputStream);
        return SUCCESS;
    }


    public File getFile() {
        return file;
    }


    public void setFile(File file) {
        this.file = file;
    }


    public String getFileFileName() {
        return fileFileName;
    }


    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }


    public String getFileContentType() {
        return fileContentType;
    }


    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getWh_person() {
        return wh_person;
    }

    public void setWh_person(String wh_person) {
        this.wh_person = wh_person;
    }

    public String getTsoiStrJson() {
        return tsoiStrJson;
    }

    public void setTsoiStrJson(String tsoiStrJson) {
        this.tsoiStrJson = tsoiStrJson;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getTabPayName() {
        return tabPayName;
    }

    public void setTabPayName(String tabPayName) {
        this.tabPayName = tabPayName;
    }

    public String getTabPayCard() {
        return tabPayCard;
    }

    public void setTabPayCard(String tabPayCard) {
        this.tabPayCard = tabPayCard;
    }

    public int getPayPersonId() {
        return payPersonId;
    }

    public void setPayPersonId(int payPersonId) {
        this.payPersonId = payPersonId;
    }

    public String getTabBankName() {
        return tabBankName;
    }

    public void setTabBankName(String tabBankName) {
        this.tabBankName = tabBankName;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getTabAgentSex() {
        return tabAgentSex;
    }

    public void setTabAgentSex(String tabAgentSex) {
        this.tabAgentSex = tabAgentSex;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getIdCard() {
        return IdCard;
    }

    public void setIdCard(String idCard) {
        IdCard = idCard;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
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

    public String getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(String expenditure) {
        this.expenditure = expenditure;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getIsSuccessive() {
        return isSuccessive;
    }

    public void setIsSuccessive(String isSuccessive) {
        this.isSuccessive = isSuccessive;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public int getInsurerPolicyId() {
        return insurerPolicyId;
    }

    public void setInsurerPolicyId(int insurerPolicyId) {
        this.insurerPolicyId = insurerPolicyId;
    }

    public int getInsurerId() {
        return insurerId;
    }

    public void setInsurerId(int insurerId) {
        this.insurerId = insurerId;
    }

    public String getJsonInsurerPolicyStr() {
        return jsonInsurerPolicyStr;
    }

    public void setJsonInsurerPolicyStr(String jsonInsurerPolicyStr) {
        this.jsonInsurerPolicyStr = jsonInsurerPolicyStr;
    }

    public String getBxName() {
        return bxName;
    }

    public void setBxName(String bxName) {
        this.bxName = bxName;
    }

    public String getBxJson() {
        return bxJson;
    }

    public void setBxJson(String bxJson) {
        this.bxJson = bxJson;
    }

    public int getBxId() {
        return bxId;
    }

    public void setBxId(int bxId) {
        this.bxId = bxId;
    }

    public int getAuditingState() {
        return auditingState;
    }

    public void setAuditingState(int auditingState) {
        this.auditingState = auditingState;
    }

    public String getAnnotationsStr() {
        return annotationsStr;
    }

    public void setAnnotationsStr(String annotationsStr) {
        this.annotationsStr = annotationsStr;
    }

    public int getTrueSId() {
        return trueSId;
    }

    public void setTrueSId(int trueSId) {
        this.trueSId = trueSId;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

}
