package com.yxbx.action;


import java.io.IOException;
import java.io.PrintWriter;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;

@Entity
public class BaseAction extends ActionSupport implements ServletRequestAware{

	@Id
	@GeneratedValue
	private static final long serialVersionUID = 1L;

	protected HttpServletRequest request;
	
	public HttpServletRequest getRequest() {
		
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	private HttpServletResponse response;

	
	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}

	// json输出
	public void strToJson(Object obj) {
		HttpServletResponse response = ServletActionContext.getResponse();
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
		response.setContentType("text/html;charset=UTF-8");
		Gson gson = gsonBuilder.create();

		String json = gson.toJson(obj);
		System.out.println(json);
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//获得httpSession
	public HttpSession getHttpSession(){
		HttpServletRequest request = ServletActionContext.getRequest();

		HttpSession se=request.getSession();

		return se;
	}
	

	public void setResultStr(String result) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out;
		response.setContentType("text/html;charset=UTF-8");
		try {
			out = response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setResponseStr(String responseString){
		HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out;
		try {
			out = response.getWriter();
	        out.print(responseString);	        
	        out.flush();
	        out.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public Cookie getCookieByName(HttpServletRequest request,String name){
	    Map<String,Cookie> cookieMap = ReadCookieMap(request);
	    if(cookieMap.containsKey(name)){
	        Cookie cookie = (Cookie)cookieMap.get(name);
	        return cookie;
	    }else{
	        return null;
	    }   
	}
	
	private Map<String,Cookie> ReadCookieMap(HttpServletRequest request){  
	    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
	    Cookie[] cookies = request.getCookies();
	    if(null!=cookies){
	        for(Cookie cookie : cookies){
	            cookieMap.put(cookie.getName(), cookie);
	        }
	    }
	    return cookieMap;
	}
}
