package com.zwg.CreateLexiconSystem.service.impl;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwg2.CreateLexiconSystem.model.ZTreeInfo;
import com.zwg2.CreateLexiconSystem.service.Seek_PATH_by_KBService;

@Service
@Transactional
public class Seek_PATH_by_KBServiceImpl implements Seek_PATH_by_KBService {

	public static int level = 0;
	public static String absolutePath = "";

	@Override
	public void read(String parent, String KBhtml) {

		File dir = new File(parent);
		if (dir.isDirectory()) {
			level++;
			File files[] = dir.listFiles();
			ZTreeInfo dirArray[] = new ZTreeInfo[files.length];
			for (int i = 0; i < dirArray.length; i++) {
				// 针对目录中的每个文件设置其参数
				String splitKB[] = files[i].getAbsolutePath().split("-");
				if (splitKB.length == 2) {
					if (splitKB[1].equals(KBhtml)) {
						absolutePath = files[i].getAbsolutePath();
					}
				}
				if (files[i].isDirectory()) {
					read(files[i].getAbsolutePath(), KBhtml);
				}
			}
			level--;
		}
	}

}
