package com.zwg2.CreateLexiconSystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ThisKB_Belongs")
public class ThisKB_Belong {
	@Id
	private String KB;
	private String className;

	public String getKB() {
		return KB;
	}

	public void setKB(String kB) {
		KB = kB;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
