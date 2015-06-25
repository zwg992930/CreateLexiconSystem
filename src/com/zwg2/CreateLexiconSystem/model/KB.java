package com.zwg2.CreateLexiconSystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "KBs")
public class KB {
	@Id
	private int kb_intvalue;

	public int getKb_intvalue() {
		return kb_intvalue;
	}

	public void setKb_intvalue(int kb_intvalue) {
		this.kb_intvalue = kb_intvalue;
	}

}
