package com.zwg2.CreateLexiconSystem.web.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dom4j.DocumentException;
import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.zwg2.CreateLexiconSystem.model.Manager;
import com.zwg2.CreateLexiconSystem.model.User;
import com.zwg2.CreateLexiconSystem.service.CreateLexicoDatabaseService;
import com.zwg2.CreateLexiconSystem.service.KBIntService;
import com.zwg2.CreateLexiconSystem.service.ManagerService;
import com.zwg2.CreateLexiconSystem.service.UserService;
import com.zwg2.CreateLexiconSystem.util.WwjException;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	
	@Autowired
	UserService userservice;
	
	@Autowired
	KBIntService kbintservice;
	
	@Autowired
	ManagerService managerservice;
	
	@Autowired
	CreateLexicoDatabaseService createlexicodatabaseservice;	
	
	@RequestMapping(value="/cleardata_del_root", method = RequestMethod.POST)
	public void clearDatabaseDelRoot(HttpServletRequest request, HttpServletResponse response) throws MalformedURLException, DocumentException {
		
		String showresult = "";
		if(request.getSession().getAttribute("manager") != null){
			if(kbintservice.getKB_count() == 0){
				showresult = "词库不存在";
			}else{
				String projectpath = request.getSession().getServletContext().getRealPath("/");
				System.out.println("开始删除词库");
				managerservice.clearDatabaseDelRoot(projectpath);
				showresult = "词库删除成功";
				System.out.println(showresult);
				
			}
		}else{
			showresult = "权限不足， 不能删除";
			
		}
		
		String s = JSON.toJSONString(showresult);
		
		try {
			response.getWriter().write(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/createlexicodatabase", method = RequestMethod.POST)
	public void createLexicoDatabase(HttpServletRequest request, HttpServletResponse response) throws MalformedURLException, DocumentException {
//		1、判断管理元是否已经登录
//		2、判断词库是否已经被创建
		
		String showresult = "";
		if(request.getSession().getAttribute("manager") != null){
			if(kbintservice.getKB_count() > 1){
				showresult = "不能重复创建词库";
			}else{
				String projectpath = request.getSession().getServletContext().getRealPath("/");
				String projecturl = request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/"));
				File file_most = new File(projectpath+"root");
				file_most.mkdir();
				createlexicodatabaseservice.createLexicoDatabase(projecturl, projectpath);
				showresult = "词库创建成功";
				
			}
		}else{
			showresult = "权限不足， 不能创建";
			
		}
		
		String s = JSON.toJSONString(showresult);
		try {
			response.getWriter().write(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
				
	}
	
	

	@RequestMapping("/login")
	public ModelAndView managerLogin() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin");
		return mav;
	}
	
	
	@RequestMapping(value="/loginprocess" ,method=RequestMethod.POST)
	public ModelAndView managerLoginProcess(HttpServletRequest request,
			HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if(null != request.getSession().getAttribute("manager")){
			mav.setViewName("adminProcess");
		}else{
			String mname = request.getParameter("mname");
			String mpassword = request.getParameter("mpassword");
			Manager manager = null;
			if(null != mname && null != mpassword){
				manager = managerservice.login(mname, mpassword);
				if(null != manager){
					request.getSession().setAttribute("manager", manager);
					mav.setViewName("adminProcess");
				}
				
			}
		}
		
		return mav;
	}
	
	

		@RequestMapping("/adminuserreg")
		public ModelAndView adminuserreg(HttpServletRequest request,
				HttpSession session) {
			User user = new User();
			user.setName(request.getParameter("name"));
			user.setPassword(request.getParameter("password"));
			user.setSex(request.getParameter("sex"));
			user.setPhone(request.getParameter("phone"));
			user.setQQ(request.getParameter("QQ"));
			user.setEmail(request.getParameter("email"));
			user.setAddr(request.getParameter("addr"));
			user.setIP(request.getRemoteAddr());
			user.setRegDate(new Timestamp(System.currentTimeMillis()));
			try {
				User u = userservice.register(user);
				if (null != u) {
					session.setAttribute("user", u);
				}
			} catch (IndexOutOfBoundsException e) {
			} catch (WwjException e) {
				e.printStackTrace();
			}
			ModelAndView mav = new ModelAndView();// 新建视图
			mav.setViewName("adminProcess");

			return mav;
		}
	

	@RequestMapping(value="adduser",method=RequestMethod.GET)
	public ModelAndView AddUser(HttpServletRequest request) {
		User user = new User(), user2 = new User();
		user.setName("wangjie");
		user.setPassword("zwgeea0Z");
		user.setSex("male");
		user.setIsmember(true);
		user.setPhone("13658313797");
		user.setAddr("贵州");
		user.setEmail("Zweigang2016@126.com");
		user.setQQ("1551306262");
		user.setIP(request.getRemoteAddr());
		user.setRegDate(new Timestamp(System.currentTimeMillis()));
		
		user2.setName("zwg646");
		user2.setPassword("zwgeea0Z");
		user2.setSex("male");
		user2.setIsmember(true);
		user2.setPhone("13658313796");
		user2.setAddr("贵州");
		user2.setEmail("1551306261@qq.com");
		user2.setQQ("1551306261");
		user2.setIP(request.getRemoteAddr());
		user2.setRegDate(new Timestamp(System.currentTimeMillis()));
		try {
			userservice.register(user);
			userservice.register(user2);
			
		} catch (WwjException e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("test");
		return mav;
	}
	
	@RequestMapping(value="deleteuser", method=RequestMethod.POST)
	public void deleteUser(@Param Integer cur_user_uid,HttpServletResponse response) {
		System.out.println("***"+cur_user_uid+"***");
		boolean boo = userservice.deluserbyuid(cur_user_uid);
		if(boo){
			System.out.println("删除成功");
			List<User> list = userservice.getAllUser();
			String s = JSON.toJSONString(list);
			try {
				response.getWriter().write(s);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			
		}
		
		
	}
	
	/**
	 * 用户名被作为主键使用不能更改
	 * 邮箱、电话、QQ验证合法性， 同时保证其不与其他用户的这些信息出现重复
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="adminuserupdate",method=RequestMethod.POST)
	public ModelAndView adminUserUpdate(HttpServletRequest request,
			HttpSession session) {
		User user = new User();
		user.setUid(Integer.parseInt(request.getParameter("uid")));
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		user.setSex(request.getParameter("sex"));
		user.setPhone(request.getParameter("phone"));
		user.setQQ(request.getParameter("QQ"));
		user.setEmail(request.getParameter("email"));
		user.setAddr(request.getParameter("addr"));
		user.setIP(request.getRemoteAddr());
		user.setRegDate(new Timestamp(System.currentTimeMillis()));
		try {
			userservice.modify(user);
			
		} catch (Exception e) {
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("adminProcess");
		return mav;
	}
	
	@RequestMapping("queryuserlist")
	public void queryAllUser(HttpServletResponse response) {
		List<User> list = userservice.getAllUser();
		String s = JSON.toJSONString(list);
		try {
			response.getWriter().write(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

}
