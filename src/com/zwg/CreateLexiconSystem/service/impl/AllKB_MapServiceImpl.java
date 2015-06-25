package com.zwg.CreateLexiconSystem.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwg2.CreateLexiconSystem.dao.AllKB_MapDao;
import com.zwg2.CreateLexiconSystem.model.ThisKB_Belong;
import com.zwg2.CreateLexiconSystem.service.AllKB_MapService;

@Service
@Transactional
public class AllKB_MapServiceImpl implements AllKB_MapService {
	@Autowired
	AllKB_MapDao allkb_mapdao;

	@Override
	public List<Integer> getAllKB_intvalue() {
		return allkb_mapdao.getAllKB_intvalue();
	}

	@Override
	public Map<String, String> getAllKBAndItsAttributes() {
		return allkb_mapdao.getAllKBAndItsAttributes();
	}

	@Override
	public void add_AllKB(Map<String, String> map) {
		allkb_mapdao.add_AllKB(map);
	}

	@Override
	public boolean add_KB(ThisKB_Belong kb_belong) {
		return allkb_mapdao.add_KB(kb_belong);
	}

	@Override
	public void ClearAll_kb() {
		allkb_mapdao.ClearAll_kb();
	}

}
