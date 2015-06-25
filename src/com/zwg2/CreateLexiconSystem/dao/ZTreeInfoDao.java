package com.zwg2.CreateLexiconSystem.dao;

import java.util.List;

import com.zwg2.CreateLexiconSystem.model.ZTreeInfo;

public interface ZTreeInfoDao {
	public boolean add(ZTreeInfo ztreeinfo);

	public void deleteZtreeInfoById(int id);

	public void clearZtreeInfos();

	public List<ZTreeInfo> getZtreeInfoByLevel(int level);

	public List<ZTreeInfo> getZtreeInfoBypId(int pid);

	public List<ZTreeInfo> getAllZtreeInfos();

	public int getMaxZtreeID();

	public ZTreeInfo getZtreeInfoByClassPath(String classname);

}
