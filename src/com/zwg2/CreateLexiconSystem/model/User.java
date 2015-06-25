package com.zwg2.CreateLexiconSystem.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

//实体类，注解
@Entity
@Table(name = "users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer uid;
	/** 姓名 */
	private String name;
	/** 密码 */
	private String password;
	/** 性别 */
	private String sex;
	/** 电话 */
	private String phone;
	/** OICQ */
	private String QQ;
	/** 联系地址 */
	private String addr;
	/** 邮箱 */
	private String email;
	private String IP;
	private Timestamp regDate;
	private String regDateshow;

	// 是否为会员
	private boolean ismember;

	// 设置唯一主键
	
	@Id
	@GeneratedValue
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQQ() {
		return QQ;
	}

	public void setQQ(String qQ) {
		QQ = qQ;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public boolean isIsmember() {
		return ismember;
	}

	public void setIsmember(boolean ismember) {
		this.ismember = ismember;
	}
	
	
	@Transient
	public String getRegDateshow() {
		return regDateshow;
	}

	public void setRegDateshow(String regDateshow) {
		this.regDateshow = regDateshow;
	}

	public User() {
		super();
	}

	

	public User(Integer uid, String name, String password, String sex,
			String phone, String qQ, String addr, String email, String iP,
			Timestamp regDate, boolean ismember) {
		super();
		this.uid = uid;
		this.name = name;
		this.password = password;
		this.sex = sex;
		this.phone = phone;
		QQ = qQ;
		this.addr = addr;
		this.email = email;
		IP = iP;
		this.regDate = regDate;
		this.ismember = ismember;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", name=" + name + ", password=" + password
				+ ", sex=" + sex + ", phone=" + phone + ", QQ=" + QQ
				+ ", addr=" + addr + ", email=" + email + ", IP=" + IP
				+ ", regDate=" + regDate + ", ismember=" + ismember + "]";
	}

	
	
	

}
