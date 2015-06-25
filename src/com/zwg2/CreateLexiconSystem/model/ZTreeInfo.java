package com.zwg2.CreateLexiconSystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ZTreeInfos")
public class ZTreeInfo {
	// 唯一id。父元素。元素名称都必须具备
	@Id
	private int id;
	private int pId;
	private String name;

	// 这两个属性是同时出现的，即只有在一个元素为父元素时，才有可能谈及是否打开
	private boolean open;
	private boolean isparent;

	// 在当前情况下，只考虑子元素被打开的情况
	private String url;
	private String target;

	// 元素在第几层
	private int level;
	// 光标移上去时的提示信息
	private String title;
	// 不支持右键吗
	private boolean noR;
	// 节点所对应的文件夹或者文件路径
	private String filepath;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isIsparent() {
		return isparent;
	}

	public void setIsparent(boolean isparent) {
		this.isparent = isparent;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public boolean isNoR() {
		return noR;
	}

	public void setNoR(boolean noR) {
		this.noR = noR;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	@Override
	public String toString() {
		return "ZTreeInfo [id=" + id + ", pId=" + pId + ", name=" + name
				+ ", open=" + open + ", isparent=" + isparent + ", url=" + url
				+ ", target=" + target + ", level=" + level + ", title="
				+ title + ", noR=" + noR + ", filepath=" + filepath + "]";
	}

}