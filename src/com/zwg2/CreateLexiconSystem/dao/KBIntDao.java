package com.zwg2.CreateLexiconSystem.dao;

import com.zwg2.CreateLexiconSystem.model.KB;

public interface KBIntDao {

	public boolean add(int intvalue);

	public boolean add(KB kb);

	public int getMaxKB_intvalue();

	public void clearkbsint();
	
	public  long getKB_count();

}
