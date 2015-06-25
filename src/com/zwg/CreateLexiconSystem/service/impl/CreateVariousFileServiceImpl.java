package com.zwg.CreateLexiconSystem.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zwg2.CreateLexiconSystem.service.CreateVariousFileService;

/*
 * CreateVariousFile的两个函数buildxml,buildhtml
 * 分别用于创建XML文件和HTML文件*/
@Service
@Transactional
public class CreateVariousFileServiceImpl implements CreateVariousFileService {

	@Override
	public void buildhtml(String path, Map<String, String> map,
			String projecturl, String name) {
		FileOutputStream fileOutputStream = null;
		try {
			List<Map.Entry<String, String>> info = new ArrayList<Map.Entry<String, String>>(
					map.entrySet());
			fileOutputStream = new FileOutputStream(path);
			PrintStream printxml = new PrintStream(fileOutputStream);
			StringBuffer sb = new StringBuffer();
			sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
			sb.append("<html>\n<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n<title>");
			sb.append(name);
			sb.append("</title><style>body {font-family: \"Microsoft YaHei\";}*{margin:0;padding:0;font-size: 16px;}table{border-collapse:collapse;border-spacing:0;margin:5px 10px;}textarea{resize: none;padding:4px; min-height:80px; min-width:400px;line-height: 20px;}td textarea,td input{border:0px;}td span{display: block;height:80px;line-height:20px;width:200px;padding:4px;} .show-title{width:90%;padding:20px 10px 2px; color:green; }.show-title input{color:green;}#submit-reset{padding-left:10px;}#submit-reset input{width:60px;height:30px;line-height:30px;text-align: center;margin-right:10px;background: #808080;cursor:pointer;}</style>\n</head>\n<body>\n<form action=\"");
			sb.append(projecturl);
			sb.append("/ControlServlet\" method=\"post\">\n<input type=\"text\" name=\"path\" style=\"display:none;\" value=");
			sb.append(path);
			sb.append(">");
			sb.append("<div class=\"show-title\">名称：<input type=\"text\" name=\"filename\" value=");
			sb.append(name);
			sb.append("></div>");
			sb.append("<table border=\"1\" style=\"text-align:left;\">\n");
			
			StringBuffer sb2 = null;
			for (int i = 0; i < map.size(); i++) {
				sb2 = new StringBuffer();
				if (!info.get(i).getKey().equals("filename")) {
					sb2.append("<tr><td style=\"text-align:left;\"><span>"); 
					sb2.append(info.get(i).getKey()); 
					sb2.append("</span></td><td><textarea style=\"text-align:left;\" name="); 
					sb2.append(info.get(i).getKey()); 
					sb2.append(">"); 
					sb2.append(info.get(i).getValue()); 
					sb2.append("</textarea></td></tr>"); 
					sb.append(sb2.toString());
				}

			}
			sb.append("</table><div id=\"submit-reset\"><input type=\"submit\" value=\"提交\">\t<input type=\"reset\" value=\"重置\"></div></form>\n</body>\n</html>\n");
			printxml.println(sb.toString());
			printxml.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void buildhtml(String savepath, String content) {
		FileOutputStream fileOutputStream = null;
		try {

			fileOutputStream = new FileOutputStream(savepath);
			PrintStream printxml = new PrintStream(fileOutputStream);
			printxml.println(content);
			printxml.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
