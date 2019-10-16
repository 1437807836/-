package com.yxbx.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts2.ServletActionContext;



import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.yxbx.util.Util;



@Entity
public class SessionInterceptorAction extends AbstractInterceptor {

	/**
	 * 
	 */
	@Id
	@GeneratedValue
	private static final long serialVersionUID = 6993098731302572901L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
	
	//	
	//	Object action = invocation.getAction();
//		// 不做该项拦截
//		if (action instanceof LoginAction) {
//			return invocation.invoke();
//		}
	//	Map<String, Object> session = invocation.getInvocationContext().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String version=request.getParameter("version");//获取版本�?
		String  randomNum=request.getParameter("randomNum");//随机�?
		String  sign=request.getParameter("sign");		
		String  sign1=Util.sign(version, randomNum);
	
//			if(version==null&&randomNum==null&&sign==null){
//				   HttpSession  session=request.getSession();
//				  String loginState=  (String) session.getAttribute("loginState");
//				  if("".equals(loginState)||loginState==null){
//					  HttpServletResponse  response=ServletActionContext.getResponse();
//					  response.sendRedirect("login1.jsp");
//					  return null;
//				  }
//				invocation.invoke();
//			}
		//invocation.invoke();
//		if(!sign1.equals(sign)){			
//			String jsonStr="{\"code\":\"99\"}";//验证签名失败，无权访�?
//			setResponseStr(jsonStr);			
//		}else{
			invocation.invoke();
//		}		
	
		
		//String userid = String.valueOf(session.get("userId"));
//		if (CommonUtils.parseInt(userid, 0) > 0) {
//			return invocation.invoke();
//		}
		
//		if (isAjaxRequest(request)) {
//			setResponseStr("sessionerror");
			return null;
//		} else {
//			return "sessionnull";
//		}
	}

	private boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		
		if (header != null && "XMLHttpRequest".equals(header))
			return true;
		else
			return false;
	}

	public void setResponseStr(String responseString) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
	
		try {
			PrintWriter 	out = response.getWriter();
			out.print(responseString);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
