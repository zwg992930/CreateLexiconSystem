package com.zwg2.servlet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zwg.CreateLexiconSystem.service.impl.Seek_PATH_by_KBServiceImpl;
import com.zwg2.CreateLexiconSystem.service.CreateVariousFileService;
import com.zwg2.CreateLexiconSystem.service.Seek_PATH_by_KBService;

@Component
public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	Seek_PATH_by_KBService seek_path_by_kbservice;
	@Autowired
	CreateVariousFileService createvariousfileservice;
      /* 当用户打开文件后,如果用户点击提交,ControlServlet就会找出PATH属性,根据该属性重新创建该文件,相当于新文件覆盖旧文件.
       * 此处可以使用ajax进行控制。添加时也需要对文件名进行相应的控制
       * 寻找和新产生的文件的名称，为零或者大于1则给予提示。
       * */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Map<String, String[]> map = request.getParameterMap();
		Map<String, String> map2 = new HashMap<String, String>();
		List<Map.Entry<String, String[]>> info = new ArrayList<Map.Entry<String, String[]>>(
				map.entrySet());
		String path = request.getParameter("path");
		for(int i=0;i<info.size();i++){
			map2.put(info.get(i).getKey(), info.get(i).getValue()[0]);
		}
		/*
		 * 找到文件名并且将文件打开,根据所得到的值重新创建该文件
		 * */
		String projecturl = request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/"));
		createvariousfileservice.buildhtml(path,map2,projecturl,request.getParameter("filename"));
		
		String KBhtml = path.substring(path.lastIndexOf("-")+1);
		
		PrintWriter out = response.getWriter();
		seek_path_by_kbservice.read("c:/root",KBhtml);
		InputStream is = new FileInputStream(Seek_PATH_by_KBServiceImpl.absolutePath);
		InputStreamReader ir = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(ir);
		String value = null;
		try {
			while((value=br.readLine())!=null){
				out.println(value);
			}
			out.close();
		} catch (Exception ioe) {
			request.getServletContext().log("error",ioe);
		}
		is.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
