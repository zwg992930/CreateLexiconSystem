package com.zwg.CreateLexiconSystem.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwg2.CreateLexiconSystem.dao.ClassNameDao;
import com.zwg2.CreateLexiconSystem.model.ClassName;
import com.zwg2.CreateLexiconSystem.service.ClassNameService;

@Service
@Transactional
public class ClassNameServiceImpl implements ClassNameService {
	@Autowired
	ClassNameDao classnamedao;

	@Override
	public List<String> getAttributesByClassName(String classname) {
		return classnamedao.getAttributesByClassName(classname);
	}

	@Override
	public void clearClassName_table() {
		classnamedao.classNamelist();
	}

	@Override
	public HashMap<String, ArrayList<String>> getEachAttributesList() {
		return classnamedao.getEachAttributesList();
	}

	@Override
	public List<String> classNamelist() {
		return classnamedao.classNamelist();
	}

	@Override
	public void addClassName(ClassName classname) {
		classnamedao.addClassName(classname);
	}

	@Override
	public ArrayList<ClassName> query() {
		return classnamedao.query();
	}

}
