package com.zwg2.CreateLexiconSystem.service;

import java.util.ArrayList;
import java.util.List;

public interface GetDifferentTypeClassService {
	//将字符串使用\\进行分割，并返回其中第i个
	public  String RecursionProcessAllClass(String classname, int i);
	//获取第一级别的类名，如人物
	public  ArrayList<String> getFirstClass(List<String> classList);
	//获取某个一类名下的所有二级类名，如人文领域
	public  ArrayList<String> getSecondClass(String most_Class,List<String> classList);
	//在上一函数功能的基础上递推
	public  ArrayList<String> getThirdClass(String second_Class,List<String> classList);
	//在上一函数功能的基础上递推
	public  ArrayList<String> getFourthClass(String fourth_Class,List<String> classList);

}
