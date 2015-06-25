package com.zwg.CreateLexiconSystem.service.impl;

import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwg2.CreateLexiconSystem.dao.AllKB_MapDao;
import com.zwg2.CreateLexiconSystem.model.ThisKB_Belong;
import com.zwg2.CreateLexiconSystem.service.GetAllKB_andItsClassService;

@Service
@Transactional
public class GetAllKB_andItsClassServiceImpl implements
		GetAllKB_andItsClassService {
	@Autowired
	AllKB_MapDao allkb_mapdao;

	@Override
	public void execute_insertKB(ArrayList<ThisKB_Belong> array,
			int startindex, int endindex) {
		for (int i = startindex; i < endindex; i++) {
			allkb_mapdao.add_KB(array.get(i));
		}

	}

	@Override
	public ArrayList<ThisKB_Belong> getElementList(Element root) {

		ArrayList<ThisKB_Belong> array = new ArrayList<ThisKB_Belong>();
		ThisKB_Belong kb_belong = new ThisKB_Belong();
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
					// 得到类
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

			}
			kb_belong.setKB(name + "-" + classStrKB);
			kb_belong.setClassName(classStrClass);
			array.add(kb_belong);
			kb_belong = new ThisKB_Belong();
			count = 0;
		}

		return array;
	}

}
