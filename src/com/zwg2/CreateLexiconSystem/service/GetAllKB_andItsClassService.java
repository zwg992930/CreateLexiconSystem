package com.zwg2.CreateLexiconSystem.service;

import java.util.ArrayList;

import org.dom4j.Element;

import com.zwg2.CreateLexiconSystem.model.ThisKB_Belong;
public interface GetAllKB_andItsClassService {
	public void execute_insertKB(ArrayList<ThisKB_Belong> array,int startindex, int endindex);
	public ArrayList<ThisKB_Belong> getElementList(Element root);
}
