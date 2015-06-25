package com.zwg2.CreateLexiconSystem.service;

import java.util.List;
import java.util.Map;

import com.zwg2.CreateLexiconSystem.model.ThisKB_Belong;

public interface AllKB_MapService {
	public List<Integer> getAllKB_intvalue();

	public Map<String, String> getAllKBAndItsAttributes();

	public void add_AllKB(Map<String, String> map);

	public boolean add_KB(ThisKB_Belong kb_belong);

	public void ClearAll_kb();

}
