package com.zwg2.CreateLexiconSystem.service;

import java.util.ArrayList;

import com.zwg2.CreateLexiconSystem.model.ZTreeInfo;

public interface AcquireZtreeInfoService {
	public void read(String parent, String projecturl);

	// 根据level和目录名称查找
	public int getparentid(String name, int level);

	public ArrayList<ZTreeInfo> getList();

	public void setList(ArrayList<ZTreeInfo> list);

}
