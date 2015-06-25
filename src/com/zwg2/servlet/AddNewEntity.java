package com.zwg2.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zwg2.CreateLexiconSystem.model.ZTreeInfo;
import com.zwg2.CreateLexiconSystem.service.CreateVariousFileService;
import com.zwg2.CreateLexiconSystem.service.KBIntService;
import com.zwg2.CreateLexiconSystem.service.ZTreeInfoService;

@Component
public class AddNewEntity  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired(required=true)
	KBIntService kbintservice;
	@Autowired(required=true)
	ZTreeInfoService ztreeinfoservice;
	@Autowired(required=true)
	CreateVariousFileService createvariousfileservice;
		
     /**
      * 创建一个起始界面
      * 既然前端可以用于提交数据
      * 则只需要将所有数据都交由前端进行填写，后端只需要对前端提交过来的数据进行检验再考虑是否创建文件填充数据，在这个过程中，只需要带着类名就行了,居然是增添属性，那么必然有增添属性行和删除属性行
      * 接收到的值会按照原来前端所放置的顺序进行排序吗？
      * 可以考虑通过js提取键值对放入到隐藏域中进行提交。
      * input中的name可以是中文名称
      * 
      */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			Enumeration<String> paranames = request.getParameterNames();
			ArrayList<String> array = new ArrayList<String>();
			Map<String,String> map = new LinkedHashMap<String,String>();
			while(paranames.hasMoreElements()){
				String currentname = paranames.nextElement();
				array.add(currentname);
			}
			
			for (int i = array.size() - 1; i >= 0; i--) {
				map.put(array.get(i), request.getParameter(array.get(i)));
			}
			int newkb_intvalue = kbintservice.getMaxKB_intvalue() + 1;
			String newKB = "KBBD"+newkb_intvalue;
			int zerocount = 10 - newKB.length();
			newKB = "KBBD";
			for(int i=0;i<zerocount;i++){
				newKB +="0";
			}
			newKB += newkb_intvalue;
			ZTreeInfo  ztreeinfo = new ZTreeInfo();
			ztreeinfo.setId(ztreeinfoservice.getMaxZtreeID()+1);
			ZTreeInfo ztreeinfoParent  = ztreeinfoservice.getZtreeInfoByClassPath(map.get("类路径").trim());
			String projecturl = request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/"));
			String projectpath = request.getSession().getServletContext().getRealPath("/");
			ztreeinfo.setpId(ztreeinfoParent.getId());
			ztreeinfo.setFilepath(map.get("类路径").trim()+"/"+map.get("实体名称")+"-"+newKB+".html");
			ztreeinfo.setIsparent(false);
			int level = ztreeinfoParent.getLevel()+1;
			ztreeinfo.setLevel(level);
			ztreeinfo.setName(map.get("实体名称")+"-"+ newKB+".html");
			ztreeinfo.setUrl(projecturl + "/Access_HTML_file?id=" + newKB+".html");
			ztreeinfo.setNoR(false);
			ztreeinfo.setTarget("quest");
			ztreeinfo.setTitle(map.get("实体名称")+"-"+ newKB+".html");
			ztreeinfo.setOpen(false);
			String path = projectpath+"root\\" + map.get("类路径").trim().replace("/", "\\") + "\\" + map.get("实体名称")
					+ "-" + newKB + ".html";
			String name = map.get("实体名称");
			map.remove("类路径");
			map.remove("实体名称");
			createvariousfileservice.buildhtml(path,map,projecturl,name);
			kbintservice.add(newkb_intvalue);
			//向数据库写入当前的实体信息
			ztreeinfoservice.add(ztreeinfo);
			request.setAttribute("success", "添加成功!");
			request.getRequestDispatcher("/WEB-INF/aboutus.jsp").forward(request, response);
	
	}
	
//	经济/金融/投资
//	c:/root/自然/人类/人体/春梦-KBBD042892.html
	//当检验合格后，在此处先行打开一个servlet
//	String path = "c:/root/"+classname;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
