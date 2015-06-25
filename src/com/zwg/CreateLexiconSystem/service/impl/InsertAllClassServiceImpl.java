package com.zwg.CreateLexiconSystem.service.impl;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwg2.CreateLexiconSystem.dao.ClassNameDao;
import com.zwg2.CreateLexiconSystem.model.ClassName;
import com.zwg2.CreateLexiconSystem.service.InsertAllClassService;
import com.zwg2.CreateLexiconSystem.util.AnalysisXML;

@Service
@Transactional
public class InsertAllClassServiceImpl implements InsertAllClassService {
	@Autowired
	ClassNameDao classnamedao;

	@Override
	public Map<String, ArrayList<String>> getElementList(String fileName)
			throws MalformedURLException, DocumentException {
		Element root = AnalysisXML.getRootElement(AnalysisXML
				.getDocElement(fileName));
		ArrayList<String> perClaAttributes = new ArrayList<String>();
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		int count = 0;
		boolean boo = true;
		String classStringRemain = "";
		String classStr2 = "";
		for (Iterator<?> i = root.elementIterator(); i.hasNext();) {
			Element element = (Element) i.next();
			for (Iterator<?> j = element.elementIterator(); j.hasNext();) {
				count = count + 1;
				Element e2 = (Element) j.next();

				if (count == 2) {
					// 找出最后一根短线然后将其放入到
					String classStr = e2.getText();
					classStr2 = e2.getText().substring(0,
							classStr.lastIndexOf("\\"));

					if (classStringRemain.equals(classStr2)) {
						boo = true;
					} else {
						// 这种情况下必须保留原串
						if (classStringRemain.equals("")) {
							boo = true;
							classStringRemain = classStr2;
						} else {
							boo = false;

						}
					}
				}

				if (count == 4) {
					// 找出最后一根短线然后将其放入到
					for (Iterator<?> k = e2.elementIterator(); k.hasNext();) {
						Element e3 = (Element) k.next();
						// 怎样重新改变 perClaAttributes
						if (boo) {
							perClaAttributes.add(e3.getName());

						} else {
							map.put(classStringRemain, perClaAttributes);
							classStringRemain = classStr2;
							perClaAttributes = new ArrayList<String>();
							perClaAttributes.add(e3.getName());
							boo = true;

						}
					}
				}
			}
			count = 0;
		}
		System.out.println(classStr2);
		map.put(classStr2, perClaAttributes);
		perClaAttributes = new ArrayList<String>();
		return map;
	}

	@Override
	public Map<String, Integer> arrayToMap(ArrayList<String> perClaAttributes) {
		ArrayList<String> array = new ArrayList<String>();
		Map<String, Integer> map = new HashMap<String, Integer>();

		for (int i = 0; i < perClaAttributes.size(); i++) {
			if (array.contains(perClaAttributes.get(i))) {
				int PerCount = map.get(perClaAttributes.get(i)) + 1;
				map.put(perClaAttributes.get(i), PerCount);
			} else {
				perClaAttributes.get(i);
				map.put(perClaAttributes.get(i), 1);
				array.add(perClaAttributes.get(i));
			}
		}
		array = new ArrayList<String>();
		return map;
	}

	@Override
	public void ListAllClassName(Map<String, ArrayList<String>> map) {
		ArrayList<String> perClaAttributes = null;
		List<Map.Entry<String, ArrayList<String>>> info = new ArrayList<Map.Entry<String, ArrayList<String>>>(
				map.entrySet());

		for (int i = 0; i < map.size(); i++) {
			perClaAttributes = new ArrayList<String>();
			perClaAttributes = info.get(i).getValue();

			Map<String, Integer> map2 = arrayToMap(perClaAttributes);
			List<Map.Entry<String, Integer>> info2 = new ArrayList<Map.Entry<String, Integer>>(
					map2.entrySet());
			Collections.sort(info2,
					new Comparator<Map.Entry<String, Integer>>() {
						public int compare(Map.Entry<String, Integer> obj1,
								Map.Entry<String, Integer> obj2) {
							return obj2.getValue() - obj1.getValue();
						}
					});
			ClassName newClassName = new ClassName();
			// 找出最大的5个，并且将其输出。
			if (info2.size() >= 5) {
				info2 = info2.subList(0, 5);
				newClassName.setClassname(info.get(i).getKey());
				for (int j = 0; j < info2.size(); j++) {
					if (j == 0) {
						newClassName.setAttribute1(info2.get(j).getKey());

					} else if (j == 1) {
						newClassName.setAttribute2(info2.get(j).getKey());

					} else if (j == 2) {
						newClassName.setAttribute3(info2.get(j).getKey());

					} else if (j == 3) {
						newClassName.setAttribute4(info2.get(j).getKey());

					} else if (j == 4) {
						newClassName.setAttribute5(info2.get(j).getKey());
					}
				}
				classnamedao.addClassName(newClassName);
			} else if (info2.size() == 4) {

				info2 = info2.subList(0, 4);
				newClassName.setClassname(info.get(i).getKey());
				for (int j = 0; j < info2.size(); j++) {
					if (j == 0) {
						newClassName.setAttribute1(info2.get(j).getKey());

					} else if (j == 1) {
						newClassName.setAttribute2(info2.get(j).getKey());

					} else if (j == 2) {
						newClassName.setAttribute3(info2.get(j).getKey());

					} else if (j == 3) {
						newClassName.setAttribute4(info2.get(j).getKey());

					}
				}
				classnamedao.addClassName(newClassName);
			} else if (info2.size() == 3) {

				info2 = info2.subList(0, 3);
				newClassName.setClassname(info.get(i).getKey());
				for (int j = 0; j < info2.size(); j++) {
					if (j == 0) {
						newClassName.setAttribute1(info2.get(j).getKey());

					} else if (j == 1) {
						newClassName.setAttribute2(info2.get(j).getKey());

					} else if (j == 2) {
						newClassName.setAttribute3(info2.get(j).getKey());

					}
				}
				classnamedao.addClassName(newClassName);
			} else if (info2.size() == 2) {

				info2 = info2.subList(0, 2);
				newClassName.setClassname(info.get(i).getKey());
				for (int j = 0; j < info2.size(); j++) {
					if (j == 0) {
						newClassName.setAttribute1(info2.get(j).getKey());

					} else if (j == 1) {
						newClassName.setAttribute2(info2.get(j).getKey());

					}
				}
				classnamedao.addClassName(newClassName);
			} else if (info2.size() == 1) {

				info2 = info2.subList(0, 1);
				newClassName.setClassname(info.get(i).getKey());
				for (int j = 0; j < info2.size(); j++) {
					if (j == 0) {
						newClassName.setAttribute1(info2.get(j).getKey());

					}
				}
				classnamedao.addClassName(newClassName);
			}
		}
	}

	@Override
	public HashMap<String, ArrayList<String>> getEachAttributesList() {
		return classnamedao.getEachAttributesList();
	}

}
