package com.zwg2.CreateLexiconSystem.service;

import java.util.List;

import com.zwg2.CreateLexiconSystem.model.KB;

public interface KBIntService {

	
	public  boolean add(int intvalue);
	
	public  boolean add(KB kb);
	
	public  int getMaxKB_intvalue();
	
	public  long getKB_count();
	
	
	public  void clearkbsint();
	
	public void add_batch(List<Integer> allkb_intvalues);


}
