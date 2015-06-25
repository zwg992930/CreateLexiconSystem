package com.zwg2.CreateLexiconSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

public interface ReClassifyService {
	public String maxEqualAttributes(ArrayList<String> list, List<Map.Entry<String, ArrayList<String>>> info);
	public Map<String, ArrayList<String>> insertKB_catergoryy_Facts(Element root, int limit);

}
