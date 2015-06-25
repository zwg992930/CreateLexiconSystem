package com.zwg2.CreateLexiconSystem.service;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.DocumentException;

public interface InsertAllClassService {
	public  Map<String, ArrayList<String>> getElementList(String fileName) throws MalformedURLException, DocumentException;
	public  Map<String, Integer>  arrayToMap(ArrayList<String> perClaAttributes);
	public  void ListAllClassName(Map<String, ArrayList<String>> map);
	public HashMap<String,ArrayList<String>> getEachAttributesList();
}
