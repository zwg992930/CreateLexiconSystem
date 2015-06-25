package com.zwg2.CreateLexiconSystem.service.impl;

import java.io.File;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.zwg2.CreateLexiconSystem.dao.AllKB_MapDao;
import com.zwg2.CreateLexiconSystem.dao.ClassNameDao;
import com.zwg2.CreateLexiconSystem.dao.KBIntDao;
import com.zwg2.CreateLexiconSystem.dao.ManagerDao;
import com.zwg2.CreateLexiconSystem.dao.ThisKB_BelongDao;
import com.zwg2.CreateLexiconSystem.dao.ZTreeInfoDao;
import com.zwg2.CreateLexiconSystem.model.Manager;
import com.zwg2.CreateLexiconSystem.service.ManagerService;

public class ManagerServiceImpl implements ManagerService {
	ManagerDao managerdao;
	AllKB_MapDao allkb_mapdao;
	ClassNameDao classnamedao;
	KBIntDao kbintdao;
	ThisKB_BelongDao thiskb_belongdao;
	ZTreeInfoDao ztreeinfodao;

	public ManagerDao getManagerdao() {
		return managerdao;
	}

	@Resource
	public void setManagerdao(ManagerDao managerdao) {
		this.managerdao = managerdao;
	}

	public AllKB_MapDao getAllkb_mapdao() {
		return allkb_mapdao;
	}

	@Resource
	public void setAllkb_mapdao(AllKB_MapDao allkb_mapdao) {
		this.allkb_mapdao = allkb_mapdao;
	}

	public ClassNameDao getClassnamedao() {
		return classnamedao;
	}

	@Resource
	public void setClassnamedao(ClassNameDao classnamedao) {
		this.classnamedao = classnamedao;
	}

	public KBIntDao getKbintdao() {
		return kbintdao;
	}

	@Resource
	public void setKbintdao(KBIntDao kbintdao) {
		this.kbintdao = kbintdao;
	}

	public ThisKB_BelongDao getThiskb_belongdao() {
		return thiskb_belongdao;
	}

	@Resource
	public void setThiskb_belongdao(ThisKB_BelongDao thiskb_belongdao) {
		this.thiskb_belongdao = thiskb_belongdao;
	}

	public ZTreeInfoDao getZtreeinfodao() {
		return ztreeinfodao;
	}

	@Resource
	public void setZtreeinfodao(ZTreeInfoDao ztreeinfodao) {
		this.ztreeinfodao = ztreeinfodao;
	}

	@Override
	public Manager login(String mname, String mpassword) {

		return managerdao.login(mname, mpassword);
	}

	@Override
	@Transactional
	public void clearDatabaseDelRoot(String projectpath) {
		allkb_mapdao.ClearAll_kb();
		classnamedao.clearClassName_table();
		thiskb_belongdao.clearKB_Belong();
		kbintdao.clearkbsint();
		ztreeinfodao.clearZtreeInfos();
		deleteDir(projectpath + "root");

	}

	@Override
	public void deleteDir(String filepath) {
		File f = new File(filepath);
		if (f.exists() && f.isDirectory()) {
			if (f.listFiles().length == 0) {
				f.delete();
			} else {
				File delFile[] = f.listFiles();
				int i = f.listFiles().length;
				for (int j = 0; j < i; j++) {
					if (delFile[j].isDirectory()) {
						deleteDir(delFile[j].getAbsolutePath());
					}
					delFile[j].delete();
				}
			}
		}
	}
	
}
