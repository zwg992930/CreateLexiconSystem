package com.zwg2.CreateLexiconSystem.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//实体类，注解
@Entity
@Table(name = "managers")
public class Manager implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 姓名 */
	private String mname;
	/** 密码 */
	private String mpassword;

	@Id
	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMpassword() {
		return mpassword;
	}

	public void setMpassword(String mpassword) {
		this.mpassword = mpassword;
	}



}