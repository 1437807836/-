package com.yxbx.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import javax.persistence.Entity;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.yxbx.util.Common;

public class Util {


	
	private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
	//static String version="v1.0";
	 public static String  sign(String  version,String str1){
	     
	      String  str=version+str1+"willhappy_2016";
	        return md5(str);
	    }
	 
	    public static String md5(String string) {
	        byte[] hash;
	        try {
	            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
	        } catch (NoSuchAlgorithmException e) {
	        	
	            throw new RuntimeException("Huh, MD5 should be supported?", e);
	            
	        } catch (UnsupportedEncodingException e) {
	            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
	        }

	        StringBuilder hex = new StringBuilder(hash.length * 2);
	        for (byte b : hash) {
	            if ((b & 0xFF) < 0x10) hex.append("0");
	            hex.append(Integer.toHexString(b & 0xFF));
	        }
	        return hex.toString();
	    }
	    public static String getRandomString(int length) { //length表示生成字符串的长度
	        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
	        Random random = new Random();
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < length; i++) {
	            int number = random.nextInt(base.length());
	            sb.append(base.charAt(number));
	        }
	        return sb.toString();
	    }
	    
	    public static String sendsms(String mobile){
	    	String msg ="";
	    	String code="-1";
	    	HttpClient client = new HttpClient(); 
			PostMethod method = new PostMethod(Url);

			client.getParams().setContentCharset("GBK");
			method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=GBK");

			int mobile_code = (int)((Math.random()*9+1)*100000);

		    String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");

			NameValuePair[] data = {//提交短信
				    new NameValuePair("account", "cf_aiou99xin"), 
				    new NameValuePair("password", "93bfb8e0d067b4c32856e5f4104b5108"), //查看密码请登录用户中心->验证码、通知短信->帐户及签名设置->APIKEY
				    //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),
				    new NameValuePair("mobile", mobile), 
				    new NameValuePair("content", content),
			};
			method.setRequestBody(data);

			try {
				client.executeMethod(method);
				
				String SubmitResult =method.getResponseBodyAsString();

				//System.out.println(SubmitResult);

				Document doc = DocumentHelper.parseText(SubmitResult);
				Element root = doc.getRootElement();

				 code = root.elementText("code");
				 msg = root.elementText("msg");
				String smsid = root.elementText("smsid");

				System.out.println(code);
				System.out.println("msg:"+msg);
				System.out.println(smsid);

				// if("2".equals(code)){
				
					System.out.println("短信提交成功:"+mobile_code);
					 return "success:"+mobile_code;
			  // 	}

			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			return msg;
	    	
	    }
		public static String getPostfix(String path) {
			if (path == null || Common.EMPTY.equals(path.trim())) {
				return Common.EMPTY;
			}
			if (path.contains(Common.POINT)) {
				return path.substring(path.lastIndexOf(Common.POINT) + 1, path.length());
			}
			return Common.EMPTY;
		}
		public static Date toDate(String dateStr){
			  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			  try {
				Date  date=df.parse(dateStr);
			
				return date;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   
			   return null;
		}
		@SuppressWarnings("static-access")
		public static Timestamp toTimestamp(String dateStr){
			  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			  try {
				Date  date=df.parse(dateStr);
				         
				           Timestamp ts=new Timestamp(System.currentTimeMillis());
				           ts= ts.valueOf(  df.format(date));
				return ts;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   
			   return null;
		}
		
		 /**
		    * @Method: makeFileName
		    * @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
		    * @Anthor:孤傲苍狼
		    * @param filename 文件的原始名称
		    * @return uuid+"_"+文件的原始名称
		    */ 
		    public static String makeFileName(String filename){  //2.jpg
		        //为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
		        return UUID.randomUUID().toString() + "_" + filename;
		    }
		    
		    /**
		     * 为防止一个目录下面出现太多文件，要使用hash算法打散存储
		    * @Method: makePath
		    * @Description: 
		    * @Anthor:孤傲苍狼
		    *
		    * @param filename 文件名，要根据文件名生成存储目录
		    * @param savePath 文件存储路径
		    * @return 新的存储目录
		    */ 
		    public static String makePath(String filename,String savePath){
		        //得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
		        int hashcode = filename.hashCode();
		        int dir1 = hashcode&0xf;  //0--15
		        int dir2 = (hashcode&0xf0)>>4;  //0-15
		        //构造新的保存目录
		        String dir = savePath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
		        //File既可以代表文件也可以代表目录
		        File file = new File(dir);
		        //如果目录不存在
		        if(!file.exists()){
		            //创建目录
		            file.mkdirs();
		        }
		        return dir;
		    }
		    public static BigDecimal toBigDecimal(String  str){
		    	BigDecimal bd =null;
		    	
		    	if(str!=null&&str.trim().equals("")){
		    		bd=new BigDecimal(0);
		    	}else if(str==null){
		    		bd=new BigDecimal(0);
		    	}else{
		    		bd=new BigDecimal(str);
		    	}
		    	 bd =bd.setScale(2, RoundingMode.HALF_UP);//保留两位小数
		    	   return bd;
		    }
}
