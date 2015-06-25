package com.zwg2.CreateLexiconSystem.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zwg2.CreateLexiconSystem.service.ZTreeInfoService;

@Controller
public class IndexAboutController {
	@Autowired
	ZTreeInfoService ztreeinfoservice;
	/*
	 * 访问目录，体验在对实体进行增删改查的功能
	 * 
	 * */
	@RequestMapping(value="/index", method= RequestMethod.GET)
	public ModelAndView index() throws IOException {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping(value="/delentitybyid", method= RequestMethod.POST)
	public void delentitybyid(@Param String id,@Param String filepath,HttpServletResponse response,HttpServletRequest request) throws IOException {
		ztreeinfoservice.deleteZtreeInfoById(Integer.parseInt(id));
		String projectpath = request.getSession().getServletContext().getRealPath("/");
		
		String delfilepath = projectpath+"root\\"+filepath;
		File filedelete = new File(delfilepath);
		System.out.println(delfilepath);
		if(filedelete.exists()){
			filedelete.delete();
			response.getWriter().println("ok");
		}else{
			response.getWriter().println("no");
		}
	}
	
	@RequestMapping("/TreeInfodata")
	public void initTreeInfo(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		String idStr = request.getParameter("id");
		StringBuffer SB = ztreeinfoservice.getZtreeInfoJsons(idStr);
	    try {
			response.getWriter().print(SB.toString().substring(0, SB.toString().length()-1) +"]");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/accessInit")
	public ModelAndView accessInit() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("initTreeInfo");
		return mav;
	}
	
	@RequestMapping(value="/moni", method= RequestMethod.GET)
	public ModelAndView accessAddEntity(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String filepath = new String(request.getParameter("filepath").getBytes("ISO8859_1"),"utf-8");
		ModelAndView mav = new ModelAndView();
		mav.addObject("filepath", filepath);
		mav.setViewName("addEntity");
		return mav;
	}
	
	@RequestMapping("/isRepeat")
	public void quest(
			@Param String questingname,
			@Param String thisEntityBelong,
			HttpServletRequest request,
			HttpServletResponse response
			) {
		String projectpath = request.getSession().getServletContext().getRealPath("/");
		String wholeBelongFolder = projectpath+"root/"+thisEntityBelong;
		System.out.println(wholeBelongFolder);
		//遍历文件夹中的所有文件
		File dir = new File(wholeBelongFolder);
		int count = 0;
		if(dir.isDirectory()){
			File files[] = dir.listFiles();
			for(int i=0;i<files.length;i++){
				String eachfilename = files[i].getName();
				if(questingname.equals(eachfilename.substring(0, eachfilename.lastIndexOf("-")))){
					count++;
				}
			}
		}
		boolean boo = false;
		if(count==0){
			boo = true;
		}else{
			boo = false;
		}
		String s = JSON.toJSONString(boo);
		try {
			response.getWriter().write(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
