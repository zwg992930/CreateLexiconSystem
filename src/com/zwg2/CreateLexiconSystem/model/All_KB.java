package com.zwg2.CreateLexiconSystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "All_KB")
public class All_KB {

	@Id
	private String KB;
	private String classname;

	public All_KB() {
		super();
	}

	public All_KB(String kB, String classname) {
		super();
		KB = kB;
		this.classname = classname;
	}

	public String getKB() {
		return KB;
	}

	public void setKB(String kB) {
		KB = kB;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

}
