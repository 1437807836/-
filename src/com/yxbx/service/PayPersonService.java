package com.yxbx.service;

import java.util.List;

import com.yxbx.pojo.TabPayPerson;;

public interface PayPersonService {
	public abstract TabPayPerson queryInsurerById(Integer Id);
	public abstract void deleteObject(TabPayPerson tp);
	public abstract List<TabPayPerson> queryInsurerAll();
	public abstract TabPayPerson addInsurer(TabPayPerson ti);
	public abstract TabPayPerson updateInsurer(TabPayPerson ti);
	public abstract List<TabPayPerson>  queryAllPayPerson(String insurerintName, int  pageSize,int page);
	
}
