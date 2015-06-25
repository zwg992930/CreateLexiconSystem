package com.zwg.CreateLexiconSystem.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwg2.CreateLexiconSystem.dao.ThisKB_BelongDao;
import com.zwg2.CreateLexiconSystem.model.ThisKB_Belong;
import com.zwg2.CreateLexiconSystem.service.ThisKB_BelongService;

@Service
@Transactional
public class ThisKB_BelongServiceImpl implements ThisKB_BelongService {
	@Autowired
	ThisKB_BelongDao thiskb_belongdao;

	@Override
	public Map<String, String> getAllKBAndItsAttributes() {
		return thiskb_belongdao.getAllKBAndItsAttributes();
	}

	@Override
	public Map<String, String> getAllKBAndItsAttributes2() {
		return thiskb_belongdao.getAllKBAndItsAttributes2();
	}

	@Override
	public String getAttributesByKB(String KB) {
		return thiskb_belongdao.getAttributesByKB(KB);
	}

	@Override
	public List<String> KBlist() {
		return thiskb_belongdao.KBlist();
	}

	@Override
	public void addbatchKB(List<Map.Entry<String, String>> info) {
		for (int i = 0; i < info.size(); i++) {
			ThisKB_Belong TB = new ThisKB_Belong();
			TB.setKB(info.get(i).getKey());
			TB.setClassName(info.get(i).getValue());
			thiskb_belongdao.addKB(TB);
		}
	}

	@Override
	public ArrayList<ThisKB_Belong> query() {
		return thiskb_belongdao.query();
	}

	@Override
	public void clearKB_Belong() {
		thiskb_belongdao.clearKB_Belong();

	}

}
