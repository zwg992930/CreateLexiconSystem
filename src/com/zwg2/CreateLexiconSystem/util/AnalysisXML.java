package com.zwg2.CreateLexiconSystem.util;

import java.io.File;
import java.net.MalformedURLException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class AnalysisXML {
	public static Document getDocElement(String fileName)
			throws MalformedURLException, DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(fileName));
		return document;
	}

	public static Element getRootElement(Document doc) {
		return doc.getRootElement();
	}

}
