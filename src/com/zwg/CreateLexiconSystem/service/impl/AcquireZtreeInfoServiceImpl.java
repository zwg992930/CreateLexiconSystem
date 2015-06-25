package com.zwg.CreateLexiconSystem.service.impl;

import java.io.File;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwg2.CreateLexiconSystem.model.ZTreeInfo;
import com.zwg2.CreateLexiconSystem.service.AcquireZtreeInfoService;

/*
 * 达到将java中读取出来的的目录结构信息放到jsp文件中进行显示
 * */

@Service
@Transactional
public class AcquireZtreeInfoServiceImpl implements AcquireZtreeInfoService {
	private ArrayList<ZTreeInfo> list = new ArrayList<ZTreeInfo>();
	int level = 0;
	String dirname = "";

	@Override
	public void read(String parent, String projecturl) {
		File dir = new File(parent);
		if (dir.isDirectory()) {
			// 相当于进入了更深一层的目录
			level++;
			File files[] = dir.listFiles();
			ZTreeInfo dirArray[] = new ZTreeInfo[files.length];
			for (int i = 0; i < dirArray.length; i++) {
				// 针对目录中的每个文件设置其参数
				dirArray[i] = new ZTreeInfo();
				dirArray[i].setId(getList().size() + 1);
				dirArray[i].setpId(getparentid(dir.getName(), level - 1));
				dirArray[i].setName(files[i].getName());
				dirArray[i].setTitle(files[i].getName());
				dirArray[i].setLevel(level);

				String splitKB[] = files[i].getAbsolutePath().split("-");

				if (splitKB.length >= 2) {
					dirArray[i].setUrl(projecturl.split("CreateLexiconSystem")[0]+ "CreateLexiconSystem" + "/Access_HTML_file?id="
							+ splitKB[splitKB.length - 1]);
					
					dirArray[i].setIsparent(false);
					dirArray[i].setOpen(false);
					dirArray[i].setTarget("quest");
					dirArray[i].setFilepath(null);
				} else {
					dirArray[i].setUrl("javascript:void(0)");
					dirArray[i].setTarget(null);
					dirArray[i].setIsparent(true);
					dirArray[i].setOpen(false);
					dirname = files[i].getAbsolutePath().replace("\\", "/");
					dirArray[i].setFilepath(dirname.substring(dirname
							.indexOf("/root/") + 6));
				}
				getList().add(dirArray[i]);
				if (files[i].isDirectory()) {
					read(files[i].getAbsolutePath(), projecturl);
				}
			}
			level--;
		}
	}

	// 根据level和目录名称查找
	@Override
	public int getparentid(String name, int level) {
		int parentid = 0;
		for (int i = 0; i < getList().size(); i++) {
			if (name.equals(getList().get(i).getName())
					&& level == getList().get(i).getLevel()) {
				parentid = getList().get(i).getId();
				break;
			}
		}
		return parentid;
	}

	@Override
	public ArrayList<ZTreeInfo> getList() {
		return list;
	}

	@Override
	public void setList(ArrayList<ZTreeInfo> list) {
		this.list = list;
	}

}