package com.zwg2.CreateLexiconSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zwg2.CreateLexiconSystem.model.ThisKB_Belong;

public interface ThisKB_BelongService {
	public Map<String, String> getAllKBAndItsAttributes();
	public Map<String, String> getAllKBAndItsAttributes2();	
	public String getAttributesByKB(String KB);
	public List<String> KBlist();
	public void addbatchKB(List<Map.Entry<String, String>> info);
	public ArrayList<ThisKB_Belong> query();
	public void clearKB_Belong();


}
