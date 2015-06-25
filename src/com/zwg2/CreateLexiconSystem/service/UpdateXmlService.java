package com.zwg2.CreateLexiconSystem.service;

import org.w3c.dom.Document;

public interface UpdateXmlService {
	public boolean doc2XmlFile(Document document, String filename);
	public Document load(String filename);
	public void xmlUpdateDemo(String filename);
}
