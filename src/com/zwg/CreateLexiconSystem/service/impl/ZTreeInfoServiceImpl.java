package com.zwg.CreateLexiconSystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwg2.CreateLexiconSystem.dao.ZTreeInfoDao;
import com.zwg2.CreateLexiconSystem.model.ZTreeInfo;
import com.zwg2.CreateLexiconSystem.service.AcquireZtreeInfoService;
import com.zwg2.CreateLexiconSystem.service.ZTreeInfoService;

@Service
@Transactional
public class ZTreeInfoServiceImpl implements ZTreeInfoService {
	@Autowired
	AcquireZtreeInfoService acquireztreeinfoservice;

	@Autowired
	ZTreeInfoDao ztreeinfodao;

	@Override
	public void addZtreeInfo(String projecturl,String projectpath) {
		ZTreeInfo ztreeinfo = new ZTreeInfo();
		ztreeinfo.setId(1);
		ztreeinfo.setpId(0);
		ztreeinfo.setName("root");
		ztreeinfo.setOpen(true);
		ztreeinfo.setIsparent(true);
		ztreeinfo.setLevel(0);
		ztreeinfo.setTitle("root");
		ztreeinfo.setFilepath(null);
		acquireztreeinfoservice.getList().add(ztreeinfo);
		String parent = projectpath+"root";
		acquireztreeinfoservice.read(parent, projecturl);
		// 将所得到的list写入数据库
		List<ZTreeInfo> ztreeList = acquireztreeinfoservice.getList();
		// 先给noR一个初始值true，再考虑修改
		ZTreeInfo ztreeinfo2 = new ZTreeInfo();

		for (int i = 0, l = ztreeList.size(); i < l; i++) {
			ztreeList.get(i).setNoR(false);
		}

		for (int i = 0, l = ztreeList.size(); i < l; i++) {
			ztreeinfo = ztreeList.get(i);
			if (ztreeinfo.isIsparent()) {
				int thisentityid = ztreeinfo.getId();
				for (int j = 0, l2 = ztreeList.size(); j < l2; j++) {
					ztreeinfo2 = ztreeList.get(j);
					if (ztreeinfo2.getpId() == thisentityid) {
						if (ztreeinfo2.isIsparent()) {
							// 在对应的地方将其改为false
							ztreeList.get(i).setNoR(true);
							break;
						}
					}
				}
			} else {
				// 在对应的地方将其改为false
				ztreeList.get(i).setNoR(false);
			}
		}

		for (int i = 0, l = ztreeList.size(); i < l; i++) {
			ztreeinfodao.add(ztreeList.get(i));
		}
	}

	public StringBuffer getZtreeInfoJsons(String idStr) {
		ZTreeInfo ztreeinfo = new ZTreeInfo();
		List<ZTreeInfo> ztreeList = null;
		StringBuffer SB = new StringBuffer();
		SB.append("[");
		if (null == idStr) {
			ztreeList = ztreeinfodao.getZtreeInfoByLevel(1);
			for (int i = 0, l = ztreeList.size(); i < l; i++) {
				ztreeinfo = ztreeList.get(i);
				SB.append("{id:" + ztreeinfo.getId() + ", pId:"
						+ ztreeinfo.getpId() + ", name:'" + ztreeinfo.getName()
						+ "', isParent:" + ztreeinfo.isIsparent() + ", open:"
						+ ztreeinfo.isOpen() + ", title:'"
						+ ztreeinfo.getTitle() + "', level:"
						+ ztreeinfo.getLevel() + ", noR:" + ztreeinfo.isNoR()
						+ "},");
			}
		} else {
			int pid = Integer.parseInt(idStr);
			ztreeList = ztreeinfodao.getZtreeInfoBypId(pid);

			for (int i = 0, l = ztreeList.size(); i < l; i++) {
				ztreeinfo = ztreeList.get(i);
				if (ztreeinfo.isIsparent()) {
					SB.append("{id:" + ztreeinfo.getId() + ", pId:"
							+ ztreeinfo.getpId() + ", name:'"
							+ ztreeinfo.getName() + "', isParent:"
							+ ztreeinfo.isIsparent() + ", open:"
							+ ztreeinfo.isOpen() + ", title:'"
							+ ztreeinfo.getTitle() + "', level:"
							+ ztreeinfo.getLevel() + ", noR:"
							+ ztreeinfo.isNoR() + "}");
				} else {
					String name = ztreeinfo.getName();
					String title = ztreeinfo.getTitle();
					if (name.contains("\'")) {
						name = name.split("\'")[0] + "\\'"
								+ name.split("\'")[1];
						title = title.split("\'")[0] + "\\'"
								+ title.split("\'")[1];
					}

					SB.append("{id:" + ztreeinfo.getId() + ", pId:"
							+ ztreeinfo.getpId() + ", name:'" + name
							+ "', isParent:" + ztreeinfo.isIsparent()
							+ ", open:" + ztreeinfo.isOpen() + ", level:"
							+ ztreeinfo.getLevel() + ", url:'"
							+ ztreeinfo.getUrl() + "', target:'"
							+ ztreeinfo.getTarget() + "', title:'" + title
							+ "', noR:" + ztreeinfo.isNoR() + "}");
				}

				SB.append(",\n");
			}

		}
		return SB;
	}

	@Override
	public boolean add(ZTreeInfo ztreeinfo) {
		return ztreeinfodao.add(ztreeinfo);
	}

	@Override
	public void deleteZtreeInfoById(int id) {

		ztreeinfodao.deleteZtreeInfoById(id);
	}

	@Override
	public void clearZtreeInfos() {

		ztreeinfodao.clearZtreeInfos();
	}

	@Override
	public List<ZTreeInfo> getZtreeInfoByLevel(int level) {
		return ztreeinfodao.getZtreeInfoByLevel(level);
	}

	@Override
	public List<ZTreeInfo> getZtreeInfoBypId(int pid) {
		return ztreeinfodao.getZtreeInfoBypId(pid);
	}

	@Override
	public List<ZTreeInfo> getAllZtreeInfos() {
		return ztreeinfodao.getAllZtreeInfos();
	}

	@Override
	public int getMaxZtreeID() {
		return ztreeinfodao.getMaxZtreeID();
	}

	@Override
	public ZTreeInfo getZtreeInfoByClassPath(String classname) {
		return ztreeinfodao.getZtreeInfoByClassPath(classname);
	}

}
