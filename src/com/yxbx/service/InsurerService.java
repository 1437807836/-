package com.yxbx.service;

import java.util.List;

import com.yxbx.pojo.TabInsurer;;

public interface InsurerService {
	public abstract TabInsurer queryInsurerById(Integer Id);
	public abstract List<TabInsurer> queryInsurerAll();
	public abstract TabInsurer addInsurer(TabInsurer ti);
	public abstract TabInsurer updateInsurer(TabInsurer ti);
	public abstract List<TabInsurer>  queryAllInsurer(String insurerintName, int  pageSize,int page);
	public abstract List<TabInsurer>  queryAllInsurerByName(String insurerintName);
	
}
