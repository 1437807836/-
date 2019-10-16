package com.yxbx.service;

import java.util.List;

import com.yxbx.pojo.TabUser;

public interface UserService {
	public abstract TabUser queryAllByUserId(Integer userId);
	public abstract List<TabUser>  queryAllUser(int pageSize,int page);
	public abstract List<TabUser>  queryAllUser();
	public abstract void   addClient(TabUser tUser);
	public abstract void   updateClient(TabUser tUser);
	public abstract TabUser  findUser(String  username,String  password);
	public abstract TabUser  findUser(String mobile);
	public abstract TabUser  findUserByUsername(String userName);
}
