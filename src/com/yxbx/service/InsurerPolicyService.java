package com.yxbx.service;

import java.util.List;

import com.yxbx.pojo.TabBxState;
import com.yxbx.pojo.TabInsurerPolicy;;

public interface InsurerPolicyService {
	public abstract TabInsurerPolicy queryInsurerPolicyById(Integer Id);
	public abstract int queryCount();
	public abstract TabBxState queryBxStateById(Integer Id);
	public abstract List<TabBxState> queryBxStateAll();
	public abstract  List<TabInsurerPolicy>  queryInsurerPolicyAll(TabInsurerPolicy ip);

	public abstract void addBxStateById(String stateName);
	public abstract void updateBxStateById(String stateName,int id);
	public abstract List<TabInsurerPolicy> queryInsurerPolicyAll();
	public abstract TabInsurerPolicy addInsurerPolicy(TabInsurerPolicy ti);
	public abstract TabInsurerPolicy updateInsurerPolicy(TabInsurerPolicy ti);
	public abstract List<TabInsurerPolicy>  queryAllInsurerPolicy(TabInsurerPolicy ip, int  pageSize,int page);
	
}
