package com.zwg2.CreateLexiconSystem.service;

import java.util.Map;

public interface CreateVariousFileService {
	public void buildhtml(String path, Map<String, String> map,
			String projecturl, String name);

	public void buildhtml(String savepath, String content);

}
