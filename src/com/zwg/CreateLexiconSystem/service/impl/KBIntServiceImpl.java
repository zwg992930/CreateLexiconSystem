package com.zwg.CreateLexiconSystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwg2.CreateLexiconSystem.dao.KBIntDao;
import com.zwg2.CreateLexiconSystem.model.KB;
import com.zwg2.CreateLexiconSystem.service.KBIntService;

@Service
@Transactional
public class KBIntServiceImpl implements KBIntService {

	@Autowired
	KBIntDao kbintdao;

	@Override
	public boolean add(int intvalue) {
		return kbintdao.add(intvalue);
	}

	@Override
	public boolean add(KB kb) {
		return kbintdao.add(kb);
	}

	@Override
	public int getMaxKB_intvalue() {
		return kbintdao.getMaxKB_intvalue();
	}

	@Override
	public void clearkbsint() {
		kbintdao.clearkbsint();
	}

	@Override
	public void add_batch(List<Integer> allkb_intvalues) {
		System.out.println(allkb_intvalues.size());
		int max = 0;
		int count = 0;
		for (int i = 0; i < allkb_intvalues.size(); i++) {
			count++;
			if (count % 5000 == 0) {
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			if (allkb_intvalues.get(i) > max) {
				max = allkb_intvalues.get(i);
			}
			kbintdao.add(allkb_intvalues.get(i));
		}
	}

	@Override
	public long getKB_count() {
		return kbintdao.getKB_count();
	}
	
	
	
}
