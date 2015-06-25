package com.zwg2.CreateLexiconSystem.service;

import java.util.List;

import com.zwg2.CreateLexiconSystem.model.ZTreeInfo;


public interface ZTreeInfoService {
	public void addZtreeInfo(String projecturl,String projectpath);
	public StringBuffer getZtreeInfoJsons(String idStr);
	public  boolean add(ZTreeInfo ztreeinfo);
	
	public  void deleteZtreeInfoById(int id);
	
	public  void clearZtreeInfos();
	
	public  List<ZTreeInfo> getZtreeInfoByLevel(int level); 
	
	public  List<ZTreeInfo> getZtreeInfoBypId(int pid);
	
	public  List<ZTreeInfo> getAllZtreeInfos();
	
	public  int getMaxZtreeID();
	
	public  ZTreeInfo getZtreeInfoByClassPath(String classname);
}
