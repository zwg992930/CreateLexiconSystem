package com.zwg.CreateLexiconSystem.service.impl;

import java.io.File;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.zwg2.CreateLexiconSystem.dao.ThisKB_BelongDao;
import com.zwg2.CreateLexiconSystem.service.UpdateXmlService;

/*
 * 根据步骤2中所插入数据库的内容,对原有的XML文件进行修改
 * */
@Service
@Transactional
public class UpdateXmlServiceImpl implements UpdateXmlService {
	@Autowired
	ThisKB_BelongDao thiskb_belongdao;

	@Override
	public boolean doc2XmlFile(Document document, String filename) {
		boolean flag = true;
		try {
			/** 将document中的内容写入文件中 */
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			/** 编码 */
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(filename));
			transformer.transform(source, result);
		} catch (Exception ex) {
			flag = false;
			ex.printStackTrace();
		}
		return flag;
	}

	@Override
	public Document load(String filename) {
		Document document = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(new File(filename));
			document.normalize();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return document;
	}

	@Override
	public void xmlUpdateDemo(String filename) {
		Document document = load(filename);
		Node root = document.getDocumentElement();
		Map<String, String> map = thiskb_belongdao.getAllKBAndItsAttributes();
		/** 如果root有子元素 */
		if (root.hasChildNodes()) {
			NodeList ftpnodes = root.getChildNodes();
			int count = 0;
			String classname = "";
			String KB = "";
			for (int i = 0; i < ftpnodes.getLength(); i++) {
				NodeList ftplist = ftpnodes.item(i).getChildNodes();

				for (int k = 0; k < ftplist.getLength(); k++) {

					Node subnode = ftplist.item(k);
					count++;

					if (count == 2) {
						KB = subnode.getTextContent();
					}

					if (count == 4) {
						String classStr2 = subnode.getTextContent().substring(
								subnode.getTextContent().lastIndexOf("\\"),
								subnode.getTextContent().length());
						if (map.keySet().contains(KB)) {
							classname = map.get(KB);
							classname += classStr2;
							subnode.setTextContent(classname);
						}
					}
				}
				count = 0;
			}
		}
		doc2XmlFile(document, filename);
	}
}
