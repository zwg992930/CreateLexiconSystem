package com.zwg2.CreateLexiconSystem.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zwg2.CreateLexiconSystem.model.ThisKB_Belong;

public interface ThisKB_BelongDao {
	public Map<String, String> getAllKBAndItsAttributes();

	public Map<String, String> getAllKBAndItsAttributes2();

	public String getAttributesByKB(String KB);

	public List<String> KBlist();

	public boolean addKB(ThisKB_Belong thiskb_Belong);

	public ArrayList<ThisKB_Belong> query();

	public void clearKB_Belong();

}
