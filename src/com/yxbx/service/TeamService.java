package com.yxbx.service;

import java.util.List;

import com.yxbx.pojo.TabTeam;;

public interface TeamService {
	public abstract TabTeam queryTabTeamById(Integer Id);
	public abstract List<TabTeam> queryTabTeamAll();
	public abstract TabTeam addTabTeam(TabTeam ti);
	public abstract TabTeam updateTabTeam(TabTeam ti);
	public abstract List<TabTeam>  queryAllTabTeam(TabTeam ta, int  pageSize,int page);
	
}
