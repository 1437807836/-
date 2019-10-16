package com.yxbx.service;

import java.util.List;

import com.yxbx.pojo.TabInsuranceFile;;

public interface InsuranceFileService {
	public abstract TabInsuranceFile queryInsuranceFileById(Integer Id);
	public abstract List<TabInsuranceFile> queryInsuranceFileByImgUrl(String imgUrl);
	public abstract int deleteInsuranceFile(TabInsuranceFile tf);
	public abstract List<TabInsuranceFile> queryInsuranceFileByInsuranceId(Integer insuranceFileId);
	public abstract List<TabInsuranceFile> queryInsuranceFileByInsuranceTureId(Integer insuranceFileTrueId);
	public abstract List<TabInsuranceFile> queryInsuranceFileByPayId(Integer payId);
	public abstract List<TabInsuranceFile> queryInsuranceFileByPayOpenId(Integer payOpenId);
	public abstract List<TabInsuranceFile> queryInsuranceFileAll();
	public abstract TabInsuranceFile addInsuranceFile(TabInsuranceFile ti);
	public abstract TabInsuranceFile updateInsuranceFile(TabInsuranceFile ti);

	
}
