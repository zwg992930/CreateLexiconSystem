package com.zwg2.servlet;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zwg.CreateLexiconSystem.service.impl.Seek_PATH_by_KBServiceImpl;
import com.zwg2.CreateLexiconSystem.service.Seek_PATH_by_KBService;
/*
 *用户通过点击index.jsp页面中的某个文件名,就会跳转到对应的URL,由于乱码问题,程序通过只传送KB.html,
 *根据该值通过递归遍历目录结构的的方式找出文件的真是路径,将其中的内容读取到Servlet，并通过浏览器进行展示,
 *在词基础上,用户一旦打开某个文件,就能对其中的fact属性进行修改,点击提交即能完成对实体内容的修改
 *
 *将文件中的内容读入到servlet中，并且通过浏览器打开。相当于将硬盘中的文件读取到内存中
 * */
@Component
public class Access_HTML_file extends HttpServlet {
	@Autowired
	Seek_PATH_by_KBService seek_path_by_kbservice;
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		String projectpath = request.getSession().getServletContext().getRealPath("/");
		PrintWriter out = response.getWriter();
		if(!(null==request.getParameter("id"))){
			String KBhtml = request.getParameter("id");
			seek_path_by_kbservice.read(projectpath+"root",KBhtml);
			FileReader reader = null; 
			try {
				System.out.println(Seek_PATH_by_KBServiceImpl.absolutePath);
				reader = new FileReader(Seek_PATH_by_KBServiceImpl.absolutePath);
				int read = -1; 
	            while ((read = reader.read()) != -1) { 
	            	out.write(read); 
	            } 
				out.close();
			} catch (Exception ioe) {
				request.getServletContext().log("error",ioe);
			}
			reader.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
