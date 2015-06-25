package com.zwg.CreateLexiconSystem.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwg2.CreateLexiconSystem.dao.ClassNameDao;
import com.zwg2.CreateLexiconSystem.service.ReClassifyService;

/*
 * 实现根据特征属性找出每个entity最适合的类,并以KB:类名的形式存入到数据库中
 * */
@Service
@Transactional
public class ReClassifyServiceImpl implements ReClassifyService {
	@Autowired
	ClassNameDao classnamedao;
	private static Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

	@Override
	public String maxEqualAttributes(ArrayList<String> list,
			List<Entry<String, ArrayList<String>>> info) {

		int max = 0;

		String classNameRemain = "";

		// 针对每个类得到一个count
		for (int i = 0; i < info.size(); i++) {
			int count = 0;
			String classname2 = info.get(i).getKey();
			ArrayList<String> li = info.get(i).getValue();
			for (int j = 0; j < list.size(); j++) {
				for (int k = 0; k < li.size(); k++) {
					if (list.contains(li.get(k))) {
						count++;
					}
				}
			}
			if (count >= max) {
				max = count;
				classNameRemain = classname2;
			}
		}
		return classNameRemain;

	}

	@Override
	public Map<String, ArrayList<String>> insertKB_catergoryy_Facts(
			Element root, int limit) {
		// 获取所有类的类名及其特征属性组成的map
		HashMap<String, ArrayList<String>> map2 = classnamedao
				.getEachAttributesList();
		for (Iterator<?> itr = root.elementIterator(); itr.hasNext();) {
			Element itr2 = (Element) itr.next();
			int count = 0;
			int count2 = 0;
			String KBString = "";
			String catergoryStr2 = "";
			ArrayList<String> list = new ArrayList<String>();
			List<String> list2 = null;
			for (Iterator<?> itr3 = itr2.elementIterator(); itr3.hasNext();) {
				count++;
				Element e2 = (Element) itr3.next();
				if (count == 1) {
					KBString = e2.getText();
				}
				if (count == 2) {
					catergoryStr2 = e2.getText().substring(0,
							e2.getText().lastIndexOf("\\"));
					// 将其全部取出来做成一个集合
					// 然后在根据键值对来取
					list2 = new ArrayList<String>();
					list2 = map2.get(catergoryStr2);

				}
				// fact子属性
				if (count == 4) {

					for (Iterator<?> k = e2.elementIterator(); k.hasNext();) {
						Element e3 = (Element) k.next();
						list.add(e3.getName());
						if (list2.contains(e3.getName())) {
							count2++;
						}
					}

				}
			}
			if (count2 <= limit) {
				map.put(KBString, list);
			}
			count = 0;
		}

		return map;

	}

}
