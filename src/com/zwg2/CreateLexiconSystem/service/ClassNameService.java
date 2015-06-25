package com.zwg2.CreateLexiconSystem.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zwg2.CreateLexiconSystem.model.ClassName;

public interface ClassNameService {
	public List<String> getAttributesByClassName(String classname);

	public void clearClassName_table();

	public HashMap<String, ArrayList<String>> getEachAttributesList();

	public List<String> classNamelist();

	public void addClassName(ClassName classname);

	public ArrayList<ClassName> query();

}
