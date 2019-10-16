package com.yxbx.service;

import java.util.List;

import com.yxbx.pojo.TabAgent;;

public interface AgentService {
	public abstract TabAgent queryagentById(Integer Id);
	public abstract TabAgent queryagentByAgentName(String AgentName);
	public abstract List<TabAgent> queryAgentById(Integer teamId);
	public abstract List<TabAgent> queryAgentAll();
	public abstract TabAgent addAgent(TabAgent ti);
	public abstract TabAgent updateAgent(TabAgent ti);
	public abstract List<TabAgent>  queryAllAgent(TabAgent ta, int  pageSize,int page);
	
}
