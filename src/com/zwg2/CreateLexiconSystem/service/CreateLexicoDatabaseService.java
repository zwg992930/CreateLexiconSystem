package com.zwg2.CreateLexiconSystem.service;

public interface CreateLexicoDatabaseService {
	public void InsertAllClassAndFiveCharacteristicAttribute(String projectpath);

	public void rematchKBsclass_and_modifyxml(String projectpath);

	public void insertallkb(String projectpath);

	public void MCreateFolderStructure(String projectpath);

	public void createhtmlfiletofolder(String projecturl, String projectpath);

	public void treeIntoDatabase(String projecturl, String projectpath);

	public void createLexicoDatabase(String projecturl, String projectpath);
}
