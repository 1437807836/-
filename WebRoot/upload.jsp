<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  //保险清单 上传附件
  		<form action="queryFile" method="post" enctype="multipart/form-data">               
         <input type="text" name="userId" value="3">//用户Id
          <input type="text" name="trueSId" value="3">//正式保险清单    两个保险清单 ID   传    一个就可以 改 正式传正式。。
           <input type="text" name="falseSId" value="3">//非正式保险清单    两个保险清单 ID   传    一个就可以 改 正式传正式。。
        
         <input type="submit" value="上传">
         </form>
    //保险清单 上传附件
  		<form action="uploadfile" method="post" enctype="multipart/form-data">
         <input type="file" name="file">//上传文件
         
         <input type="text" name="userId" value="3">//用户Id
          <input type="text" name="trueSId" value="3">//正式保险清单    两个保险清单 ID   传    一个就可以 改 正式传正式。。
           <input type="text" name="falseSId" value="3">//非正式保险清单    两个保险清单 ID   传    一个就可以 改 正式传正式。。
        
         <input type="submit" value="上传">
         </form>
  	//付款申请 审核
  	  <form action="AuditingPost" method="post" enctype="multipart/form-data">
    
         <input type="text" name="userId" value="">//userId 
           <input type="text" name="soiId" value="3">//临时表单Id
             <input type="text" name="auditingState" value="3">//业务类型 1通过审核并结清代理款项，2未代理人结款，保单等待附件上传 , 3为驳回重新提交
           <input type="text" name="annotationsStr" value="哪里哪里有问题">//审核失败原因内容		    	
         <input type="submit" value="查询">
          </form> 
  	//查询保险政策
  	  <form action="queryInsurerPolicy" method="post" enctype="multipart/form-data">
    
         <input type="text" name="jsonInsurerPolicyStr" value="">//政策Id 
           <input type="text" name="pageSize" value="3">//每页显示几个
             <input type="text" name="page" value="3">//当前第几页
              		    	
         <input type="submit" value="查询">
          </form> 
    //删除保险政策
      <form action="deleteInsurerPolicy" method="post" enctype="multipart/form-data">
    
         <input type="text" name="insurerPolicyId" value="3">//政策Id 
           <input type="text" name="userId" value="3">用户Id
              		    	
         <input type="submit" value="查询">
          </form> 
  
  //增加保险政策 addinsurerPolicy
  		<form action=addinsurerPolicy method="post" enctype="multipart/form-data">
    
         <input type="text" name="insurerId" value="3">//保险公司ID //查询保险公司获得           
           <input type="text" name="userId" value="3">用户Id 
           <input type="text" name="agentId" value="1">代理人Id  查询代理人获得                
           <input type="text" name="bxJson" value="{"listBxState":[{"id":1,"tabState":"商业险","tabIncome":0.01,"tabExpenditure":0.01,"tabInvoice":0.02},{"id":1,"tabState":"商业险","tabIncome":0.01,"tabExpenditure":0.01,"tabInvoice":0.02}]}">代理人Id 险种  收入 支出 开票       
             <input type="text" name="channelName" value="传统">渠道		
             <input type="text" name="isSuccessive" value="续保">是否续保
             <input type="text" name="quality" value="2吨以下">2吨以下 核定载质量
             <input type="text" name="nature" value="非运营客车">性质	 
              <input type="text" name="endTime" value="1991-12-8 08:00">//失效时间	
               
             
                         	
          <input type="submit" value="查询">
          </form> 														 						 	
  	//查询团队 findTeam
  		<form action=findTeam method="post" enctype="multipart/form-data">
    
         <input type="text" name="TeamName" value="3">团队名        //为空查全部         
           <input type="text" name="pageSize" value="1">每页显示几个 
             <input type="text" name="page" value="1">当前第几页                		    	
         <input type="submit" value="查询">
          </form> 
  
  //删除团队
  	<form action=deleteTeam method="post" enctype="multipart/form-data">
       <input type="text" name="teamId" value="1">团队名 
        <input type="text" name="userId" value="3">当前登录ID                           		    	
         <input type="submit" value="查询">
    </form> 
  //增加团队
  	<form action=addTeam method="post" enctype="multipart/form-data">
       <input type="text" name="TeamName" value="3">团队名 
             <input type="text" name="userId" value="3">当前登录ID                           		    	
         <input type="submit" value="查询">
          </form> 
  //查询 代理人 findAgent
  	<form action=findAgent method="post" enctype="multipart/form-data">
       <input type="text" name="TeamName" value="3">团队名 可传空  
       <input type="text" name="agentName" value="张三">代理人姓名 可传空           
       <input type="text" name="mobile" value="15868805514">代理人手机号 可传空             
            <input type="text" name="pageSize" value="1">每页显示几个 
             <input type="text" name="page" value="1">当前第几页                              		    	
         <input type="submit" value="查询">
          </form> 
  //修改代理人
  		<form action=updateAgent method="post" enctype="multipart/form-data">
       <input type="text" name="teamId" value="3">团队id 可传空
           <input type="text" name="agentName" value="张三">代理人姓名
            <input type="text" name="tabPayCard" value="622848212111212">代理人银行卡号
             <input type="text" name="IdCard" value="330121214545445">代理人身份证号
              <input type="text" name="tabBankName" value="农业银行">//代理银行账户名           
             <input type="text" name="userId" value="3">userId
               <input type="text" name="tabAgentSex" value="男">代理人性别
                 <input type="text" name="mobile" value="15868805514">代理人手机号
                    <input type="text" name="agentId" value="1">代理人id          
                           
         <input type="submit" value="增加">
         </form>   		  
  		//增加代理人
  		<form action="addAgent" method="post" enctype="multipart/form-data">
         <input type="text" name="teamId" value="3">团队id 可传空
           <input type="text" name="agentName" value="张三">代理人姓名
            <input type="text" name="tabPayCard" value="622848212111212">代理人银行卡号
             <input type="text" name="IdCard" value="330121214545445">代理人身份证号
              <input type="text" name="tabBankName" value="农业">//代理银行账户名           
             <input type="text" name="userId" value="3">userId
               <input type="text" name="tabAgentSex" value="男">代理人性别
                 <input type="text" name="mobile" value="15868805514">代理人手机号
                  
         <input type="submit" value="增加">
         </form>   		    			
  //删除付款人 deletePayPersonAcount
  <form action="deletePayPersonAcount" method="post" enctype="multipart/form-data">
         <input type="text" name="payPersonId" value="3">付款人id 根据查询付款人账号时得到
        
             <input type="text" name="userId" value="3">userId
         <input type="submit" value="查询">
         </form>
  //增加付款人账号
  		<form action="addPayPersonAcount" method="post" enctype="multipart/form-data">
         <input type="text" name="tabPayCard" value="3">卡号
           <input type="text" name="tabPayName" value="3">姓名
            <input type="text" name="tabBankName" value="3">银行名
             <input type="text" name="userId" value="3">userId
         <input type="submit" value="查询">
         </form>
   //查询付款人账号
  		<form action="queryPayPersonAcount" method="post" enctype="multipart/form-data">
         <input type="text" name="pageSize" value="3">每页显示几个
           <input type="text" name="page" value="3">当前第几页 
             <input type="text" name="tabBankName" value="3">tabBankName
         <input type="submit" value="查询">
         </form>
    //查询保险公司
  		<form action="queryInsurance" method="post" enctype="multipart/form-data">
         <input type="text" name="pageSize" value="3">每页显示几个
           <input type="text" name="page" value="3">当前第几页
         <input type="submit" value="查询">
         </form>
    //修改保险公司
  		<form action="updateInsurance" method="post" enctype="multipart/form-data">
         <input type="text" name="insuranceName">//保险公司名字	
          <input type="text" name="userId" value="3">userId
           <input type="text" name="insuranceId" value="3">保险公司表Id
         <input type="submit" value="提交">
         </form>
     //增加保险公司
  		<form action="addInsurance" method="post" enctype="multipart/form-data">
         <input type="text" name="insuranceName">
          <input type="text" name="userId" value="3">
         <input type="submit" value="增加">
         </form>
       //一键读取
  		<form action="uploadMsg_read" method="post" enctype="multipart/form-data">
         <input type="file" name="file">
          <input type="text" name="userId" value="3">
         <input type="submit" value="上传">
         </form>
            //新增保险单
  		<form action="addTabScheduleOfInsurance" method="post" enctype="multipart/form-data">
         <input type="text" name="tsoiStrJson" value="{"tabYxState":"超级帅气"}"/>                      
         <input type="submit" value="提交">
         </form>
       <%--  <a href="downFile?filename=${filename}" rel="external nofollow" >下载</a> --%>
       //修改临时保险单
  		<form action="uploadSoiToUser" method="post" enctype="multipart/form-data">
         <input type="text" name="updateParams" value="{"tabYxState":"超级帅气"}"/>
          <input type="text" name="userId" value="1"/>                 
           <input type="text" name="soiId" value="1"/>
         <input type="submit" value="修改">
         </form>
         
          //修改正式保险单
  		<form action="uploadSoi" method="post" enctype="multipart/form-data">
         <input type="text" name="updateParams" value="{"tabYxState":"超级帅气"}"/>
          <input type="text" name="userId" value="1"/>                 
           <input type="text" name="soiId" value="1"/>
         <input type="submit" value="修改">
         </form>
         
          //查询保险单/搜索 我上传的
  		<form action="findInsuranceToUser" method="post" enctype="multipart/form-data">
         <input type="text" name="SeachInsuranceJsonStr" value="{"tabCarNum":"浙CEJ688","tabSafePerson":"彭成帮","tabYxState":"超级帅气","tabCarState":"客车","tabChannel":"平阳永顺","tabInsuranceCompanyName":"龙港太保","tabCdPerson":"吴玉修","tabWhPerson":"李学聪","tabAgentName":"杨思伟杨宗波","tabAmountSate":"已收","tabTeamOwnership":"杨思伟"}"/>
          <input type="text" name="userId" value="1"/>     //userId            
          <input  type="text" name="page" value="1"/>//当前第几页
           <input  type="text" name="pageSize" value="1"/>//一页几个数据
         <input type="submit" value="查询">
         </form>
            //查询保险单/搜索  全部保险 清单 已完成
  		<form action="findInsuranceTrue" method="post" enctype="multipart/form-data">
         <input type="text" name="SeachInsuranceJsonStr" value="{"id":102,"tabCarNum":"浙CEJ688","tabSafePerson":"彭成帮","tabYxState":"超级帅气","tabCarState":"客车","tabChannel":"平阳永顺","tabInsuranceCompanyName":"龙港太保","tabCdPerson":"吴玉修","tabWhPerson":"李学聪","tabAgentName":"杨思伟杨宗波","tabAmountSate":"已收","tabTeamOwnership":"杨思伟"}"/>                   
          <input  type="text" name="page" value="1"/>//当前第几页
           <input  type="text" name="pageSize" value="1"/>//一页几个数据
         <input type="submit" value="查询">
         </form>
         
               //查询保险单/搜索   待审核表单
  		<form action="findInsuranceWait" method="post" enctype="multipart/form-data">
         <input type="text" name="SeachInsuranceJsonStr" value="{"id":102,"tabCarNum":"浙CEJ688","tabSafePerson":"彭成帮","tabYxState":"超级帅气","tabCarState":"客车","tabChannel":"平阳永顺","tabInsuranceCompanyName":"龙港太保","tabCdPerson":"吴玉修","tabWhPerson":"李学聪","tabAgentName":"杨思伟杨宗波","tabAmountSate":"已收","tabTeamOwnership":"杨思伟"}"/>                   
          <input  type="text" name="page" value="1"/>//当前第几页
           <input  type="text" name="pageSize" value="1"/>//一页几个数据
              <input type="text" name="userId" value="1"/>     //userId            
         <input type="submit" value="查询">
         </form>
         
        //登录
         
  		<form action="login" method="post" enctype="multipart/form-data">
         <input type="text" name="username" value="admin"/>//用户名
          <input type="text" name="password" value="123456"/>   //密码              
          
         <input type="submit" value="修改">
         </form>
          //增加用户        
  		<form action="addUser" method="post" enctype="multipart/form-data">
         <input type="text" name="username" value="admin"/>新增的用户名
          <input type="text" name="password" value="123456"/> 新增的密码
           <input type="text" name="mobile" value="15868814545"/>新增的手机号码
          <input type="text" name="user_type" value="2"/> //登陆者 的权限等级 
          <input type="text" name="userId" value="3"/>   登录者id                            
         
         <input type="submit" value="提交">
         </form>
           //更新用户     
  		<form action="updateUser" method="post" enctype="multipart/form-data">
         <input type="text" name="username" value="admin"/>//修改的用户名
          <input type="text" name="password" value="123456"/> //修改的密码
           <input type="text" name="mobile" value="15868814545"/>修改 的手机号
          <input type="text" name="user_type" value="2"/>  修改的用户权限等级
          <input type="text" name="userId" value="3"/>登陆者的用户ID
           <input type="text" name="userIdUpload" value="4"/>  //  要修改的用户UI                                
         
         <input type="submit" value="提交">
         </form>
           //删除用户 
  		<form action="deleteUser" method="post" enctype="multipart/form-data">     
          <input type="text" name="userId" value="3"/>登陆者的用户ID
           <input type="text" name="userIdUpload" value="4"/>  //  要删除的用户UI                                        
         <input type="submit" value="提交">
         </form>
           //获取所有用户
  		<form action="queryAllUser" method="post" enctype="multipart/form-data">     
           <input type="text" name="user_type" value="4"/>  // 当前用户权限 
            <input type="text" name="pageSize" value="4"/>  // 每页几条数据 
             <input type="text" name="page" value="4"/>  // 当前页                                                                 
         <input type="submit" value="提交">
         </form>
             //修改密码     
  		<form action="updateUserPwd" method="post" enctype="multipart/form-data">
         <input type="text" name="username" value="admin"/>//修改的用户名
          <input type="text" name="password" value="123456"/> //修改的密码
        
           <input type="text" name="rePassword" value="4"/>  //  新密码                        
         
         <input type="submit" value="提交">
         </form>
         <!-- {"id":102,"tabCarNum":"浙CEJ688","tabSafePerson":"彭成帮","tabYxState":"超级帅气","tabCarState":"客车","tabChannel":"平阳永顺","tabInsuranceCompanyName":"龙港太保","tabCdPerson":"吴玉修","tabWhPerson":"李学聪","tabAgentName":"杨思伟杨宗波","tabAmountSate":"已收","tabTeamOwnership":"杨思伟"} -->
     <!--    <form action="/servlet/UploadHandleServlet.servlet" enctype="multipart/form-data" method="post">
                   上传用户：<input type="text" name="username"><br/>
                   上传文件1：<input type="file" name="file1"><br/>
                   上传文件2：<input type="file" name="file2"><br/>
        <input type="submit" value="提交"> 
    </form>-->
  </body>
</html>
