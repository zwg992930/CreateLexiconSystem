package com.zwg.CreateLexiconSystem.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwg2.CreateLexiconSystem.service.GetDifferentTypeClassService;

/**
 * 经分析：将类名结构转换目录结构时，可能会转换为三层或四层目录
 * 
 * @author 曾维刚
 * 
 */
@Service
@Transactional
public class GetDifferentTypeClassServiceImpl implements
		GetDifferentTypeClassService {
	// 将字符串使用\\进行分割，并返回其中第i个
	@Override
	public String RecursionProcessAllClass(String classname, int i) {
		String first[] = classname.split("\\\\");
		return first[i];
	}

	// 获取第一级别的类名，如人物
	@Override
	public ArrayList<String> getFirstClass(List<String> classList) {
		ArrayList<String> array1 = new ArrayList<String>();
		for (int i = 0; i < classList.size(); i++) {
			String most_class = RecursionProcessAllClass(classList.get(i), 0);
			if (!array1.contains(most_class)) {
				array1.add(most_class);
			}
		}
		return array1;
	}

	// 获取某个一类名下的所有二级类名，如人文领域
	@Override
	public ArrayList<String> getSecondClass(String most_Class,
			List<String> classList) {
		ArrayList<String> array2 = new ArrayList<String>();
		for (int j = 0; j < classList.size(); j++) {
			String most_class2 = RecursionProcessAllClass(classList.get(j), 0);
			if (most_Class.equals(most_class2)) {
				String second_class = RecursionProcessAllClass(
						classList.get(j), 1);
				if (!array2.contains(second_class)) {
					array2.add(second_class);
				}
			}
		}
		return array2;
	}

	// 在上一函数功能的基础上递推
	@Override
	public ArrayList<String> getThirdClass(String second_Class,
			List<String> classList) {
		ArrayList<String> array3 = new ArrayList<String>();
		for (int j = 0; j < classList.size(); j++) {
			String second_class2 = RecursionProcessAllClass(classList.get(j), 1);
			if (second_Class.equals(second_class2)) {
				String third_class = RecursionProcessAllClass(classList.get(j),
						2);
				if (!array3.contains(third_class)) {
					array3.add(third_class);
				}
			}
		}
		return array3;
	}

	// 在上一函数功能的基础上递推
	@Override
	public ArrayList<String> getFourthClass(String fourth_Class,
			List<String> classList) {
		ArrayList<String> array4 = new ArrayList<String>();
		for (int j = 0; j < classList.size(); j++) {
			String Third_class2 = RecursionProcessAllClass(classList.get(j), 2);
			if (fourth_Class.equals(Third_class2)) {
				String fourthClass = classList.get(j);
				if (fourthClass.split("\\\\").length == 4) {
					String fourth_class = RecursionProcessAllClass(fourthClass,
							3);
					if (!array4.contains(fourth_class)) {
						array4.add(fourth_class);
					}
				}
			}
		}
		return array4;
	}

}
