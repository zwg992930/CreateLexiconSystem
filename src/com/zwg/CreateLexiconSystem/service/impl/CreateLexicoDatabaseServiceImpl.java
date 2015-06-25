package com.zwg.CreateLexiconSystem.service.impl;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwg2.CreateLexiconSystem.model.ThisKB_Belong;
import com.zwg2.CreateLexiconSystem.service.AllKB_MapService;
import com.zwg2.CreateLexiconSystem.service.ClassNameService;
import com.zwg2.CreateLexiconSystem.service.CreateLexicoDatabaseService;
import com.zwg2.CreateLexiconSystem.service.GetAllKB_andItsClassService;
import com.zwg2.CreateLexiconSystem.service.GetDifferentTypeClassService;
import com.zwg2.CreateLexiconSystem.service.InsertAllClassService;
import com.zwg2.CreateLexiconSystem.service.KBIntService;
import com.zwg2.CreateLexiconSystem.service.ReClassifyService;
import com.zwg2.CreateLexiconSystem.service.ReadXMLService;
import com.zwg2.CreateLexiconSystem.service.ThisKB_BelongService;
import com.zwg2.CreateLexiconSystem.service.UpdateXmlService;
import com.zwg2.CreateLexiconSystem.service.ZTreeInfoService;
import com.zwg2.CreateLexiconSystem.util.AnalysisXML;

@Service
@Transactional
public class CreateLexicoDatabaseServiceImpl implements CreateLexicoDatabaseService{
	@Autowired
	InsertAllClassService insertallclassservice;
	
	@Autowired
	ReClassifyService reclassifyservice;
	
	@Autowired
	ThisKB_BelongService thiskb_belongservice;
	
	@Autowired
	UpdateXmlService updatexmlservice;
	
	@Autowired
	GetAllKB_andItsClassService getallkb_anditsclassservice;
	
	@Autowired
	ClassNameService classnameservice;
	
	@Autowired
	GetDifferentTypeClassService getdifferenttypeclassservice;
	
	@Autowired
	ReadXMLService readxmlservice;
	
	@Autowired
	ZTreeInfoService ztreeinfoservice;
	
	@Autowired
	AllKB_MapService allkb_mapservice;
	
	@Autowired
	KBIntService kbintservice;
	
	private static String FILE_NAME = "xml/KB_BD.xml";
	private static ArrayList<String> arrayFirstClass = new ArrayList<String>();
	private static ArrayList<String> arraySecondClass = new ArrayList<String>();
	private static ArrayList<String> arrayThirdClass = new ArrayList<String>();
	private static ArrayList<String> arrayFourthClass = new ArrayList<String>();

	@Override
	public void InsertAllClassAndFiveCharacteristicAttribute(String projectpath) {
		Map<String, ArrayList<String>> maps;
		try {
			maps = insertallclassservice.getElementList(projectpath+FILE_NAME);
			insertallclassservice.ListAllClassName(maps);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void rematchKBsclass_and_modifyxml(String projectpath) {
		Map<String, String> map = new HashMap<String, String>();
		Map<String, ArrayList<String>> maps = null;
		try {
			maps = reclassifyservice.insertKB_catergoryy_Facts(AnalysisXML.getRootElement(AnalysisXML.getDocElement(projectpath+FILE_NAME)), 0);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		List<Map.Entry<String, ArrayList<String>>> info = new ArrayList<Map.Entry<String, ArrayList<String>>>(
				maps.entrySet());
		HashMap<String, ArrayList<String>> map2 = insertallclassservice.getEachAttributesList();
		
		List<Map.Entry<String, ArrayList<String>>> info2 = new ArrayList<Map.Entry<String, ArrayList<String>>>(
				map2.entrySet());
		
//		 所有类
//		通过遍历得到每一个元组
		for (int k = 0; k < info.size(); k++) {
//			 找出该元组的属性集合
			ArrayList<String> list = info.get(k).getValue();
			String className = reclassifyservice.maxEqualAttributes(list, info2);
			map.put(info.get(k).getKey(), className);
		}

		List<Map.Entry<String, String>> info3 = new ArrayList<Map.Entry<String, String>>(
				map.entrySet());
		
		
		thiskb_belongservice.addbatchKB(info3);
		
		updatexmlservice.xmlUpdateDemo(projectpath+FILE_NAME);
	}

	@Override
	public void insertallkb(String projectpath) {
		Element root = null;
		try {
			root = AnalysisXML.getRootElement(AnalysisXML.getDocElement(projectpath+FILE_NAME));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		ArrayList<ThisKB_Belong> array = getallkb_anditsclassservice.getElementList(root);
//		 分作5步
		getallkb_anditsclassservice.execute_insertKB(array, 0, array.size());
	
	}

	@Override
	public void MCreateFolderStructure(String projectpath) {
//		获取所有的类名
		List<String> classList = classnameservice.classNamelist();
//		获取最大的类，如人物
		arrayFirstClass = getdifferenttypeclassservice.getFirstClass(classList);
		for(int i=0;i<arrayFirstClass.size();i++){
//			使用最大的类创建第一级目录
			File file_most = new File(projectpath+"root\\"+arrayFirstClass.get(i));
			file_most.mkdir();
			arraySecondClass = getdifferenttypeclassservice.getSecondClass(arrayFirstClass.get(i), classList);
			for(int j=0;j<arraySecondClass.size();j++){
//				在第一级目录的基础上创建第二级目录
				File file_second = new File(projectpath+"root\\"+arrayFirstClass.get(i)+"\\"+arraySecondClass.get(j));
				file_second.mkdir();
//				获取三级的类，如儒学家
				arrayThirdClass = getdifferenttypeclassservice.getThirdClass(arraySecondClass.get(j), classList);
				for(int k=0;k<arrayThirdClass.size();k++){
//					在第二级目录的基础上创建第三级目录
					File file_third = new File(projectpath+"root\\"+arrayFirstClass.get(i)+"\\"+arraySecondClass.get(j)+"\\"+arrayThirdClass.get(k));
					file_third.mkdir();
//					如果存在四级目录，则创建之
					arrayFourthClass = getdifferenttypeclassservice.getFourthClass(arrayThirdClass.get(k), classList);
					if(!arrayFourthClass.isEmpty()){
						for(int n = 0;n<arrayFourthClass.size();n++){
							File file_fourth = new File(projectpath+"root\\"+arrayFirstClass.get(i)+"\\"+arraySecondClass.get(j)+"\\"+arrayThirdClass.get(k)+"\\"+arrayFourthClass.get(n));
							file_fourth.mkdir();
						}
					}
				}
			}
		}
		
	}

	@Override
	public void createhtmlfiletofolder(String projecturl, String projectpath) {
		try {
			readxmlservice.createAllHtmls(projectpath+FILE_NAME,projecturl.split("CreateLexiconSystem")[0]+"CreateLexiconSystem",projectpath);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void treeIntoDatabase(String projecturl, String projectpath) {
		ztreeinfoservice.addZtreeInfo(projecturl,projectpath);
		List<Integer> allkb_intvalues = allkb_mapservice.getAllKB_intvalue();
//		批量添加		
		kbintservice.add_batch(allkb_intvalues);
	}

	@Override
	public void createLexicoDatabase(String projecturl, String projectpath) {
//		首先创建一个root文件夹
		InsertAllClassAndFiveCharacteristicAttribute(projectpath);

		rematchKBsclass_and_modifyxml(projectpath);

		insertallkb(projectpath);

		MCreateFolderStructure(projectpath);

		createhtmlfiletofolder(projecturl, projectpath);

		treeIntoDatabase(projecturl, projectpath);
		
		System.out.println("词库创建成功");

	}
	
	

}
