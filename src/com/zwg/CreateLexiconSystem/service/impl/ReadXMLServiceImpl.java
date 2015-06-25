package com.zwg.CreateLexiconSystem.service.impl;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwg2.CreateLexiconSystem.service.CreateVariousFileService;
import com.zwg2.CreateLexiconSystem.service.ReadXMLService;
import com.zwg2.CreateLexiconSystem.util.AnalysisXML;

@Service
@Transactional
public class ReadXMLServiceImpl implements ReadXMLService {
	@Autowired
	CreateVariousFileService createvariousfileservice;

	@Override
	public void createAllHtmls(String fileName, String projecturl, String projectpath)
			throws MalformedURLException, DocumentException {
		Element root = AnalysisXML.getRootElement(AnalysisXML
				.getDocElement(fileName));
		Map<String, String> map = new HashMap<String, String>();
		int count = 0;
		String classStrKB = "";
		String classStrClass = "";
		String name = "";
		for (Iterator<?> i = root.elementIterator(); i.hasNext();) {
			Element element = (Element) i.next();
			for (Iterator<?> j = element.elementIterator(); j.hasNext();) {
				count = count + 1;
				Element e2 = (Element) j.next();
				if (count == 1) {
					classStrKB = e2.getText();
				}
				if (count == 2) {
					// 得到类
					String classStr = e2.getText();
					classStrClass = e2.getText().substring(0,
							classStr.lastIndexOf("\\"));
				}
				if (count == 3) {
					name = e2.getText();
				}

				if (count == 4) {
					for (Iterator<?> k = e2.elementIterator(); k.hasNext();) {
						Element e3 = (Element) k.next();
						map.put(e3.getName(), e3.getText());
					}
				}
			}
//			String path = classStrClass.replace("\\", "/");
			createvariousfileservice.buildhtml(projectpath+"root\\" + classStrClass + "\\" + name
					+ "-" + classStrKB + ".html", map, projecturl, name);
			count = 0;
			map = new HashMap<String, String>();
		}
	}
}
