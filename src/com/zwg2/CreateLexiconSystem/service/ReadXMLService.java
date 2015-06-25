package com.zwg2.CreateLexiconSystem.service;

import java.net.MalformedURLException;

import org.dom4j.DocumentException;

public interface ReadXMLService {
	public void createAllHtmls(String fileName,String projecturl,String projectpath) throws MalformedURLException,DocumentException;
}
