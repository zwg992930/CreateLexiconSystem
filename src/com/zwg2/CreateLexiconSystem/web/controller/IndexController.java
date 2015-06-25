package com.zwg2.CreateLexiconSystem.web.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zwg2.CreateLexiconSystem.model.User;
import com.zwg2.CreateLexiconSystem.service.UserService;
import com.zwg2.CreateLexiconSystem.util.WwjException;

//标识为控制层
@Controller
public class IndexController {
	@Autowired
	UserService userService;
	
	@RequestMapping("/top")
	public ModelAndView accessTop() {
		ModelAndView mav = new ModelAndView();
		//判断用户是否登录
		mav.setViewName("top");
		return mav;
	}
	
	@RequestMapping("/aboutus")
	public ModelAndView aboutus() {
		ModelAndView mav = new ModelAndView();
		//判断用户是否登录
		mav.setViewName("aboutus");
		return mav;
	}

	@RequestMapping("/login_reg")
	public ModelAndView login_reg(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String type = request.getParameter("type");
		//判断用户是否登录
		mav.setViewName("login_reg");
		mav.addObject("type", type);
		return mav;
	}
	
	// 前台请求到login.do的时候会加载到这里
	@RequestMapping("/login")
	public ModelAndView userLogin(HttpServletRequest request,
			HttpSession session) throws Exception {
		String name = request.getParameter("uname");
		String password = request.getParameter("upwd");
		ModelAndView mav = new ModelAndView();// 新建视图
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		try {
			User u = userService.login(name, password);
			
			if (null != u) {
				session.setAttribute("user", u);
				mav.addObject("isloginsuccess", true);
			} else {
				//提示用户密码错误
				mav.addObject("isloginsuccess", false);
			}
		} catch (IndexOutOfBoundsException e) {
		}
		mav.setViewName("aboutus");
		return mav;
	}

		@RequestMapping(value="/logout", method = RequestMethod.POST)
		public void userLogout(HttpServletRequest request,HttpServletResponse response,
				HttpSession session) throws Exception {
			session.removeAttribute("user");
			response.getWriter().println("ok");

		}
		
		@RequestMapping(value="/finduser", method = RequestMethod.GET)
		public ModelAndView findUser(@Param  Integer uid, HttpServletRequest request,HttpServletResponse response,
				HttpSession session) throws Exception {
			User user = userService.fingUserByUid(uid);
			ModelAndView mav = new ModelAndView();// 新建视图
			mav.setViewName("look_update");
			mav.addObject("lookuser", user);
			return mav;
			

		}

	
	
	// 提交注册信息时的action的请求处理
	@RequestMapping("/register")
	public ModelAndView userRegister(HttpServletRequest request,
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

		ModelAndView mav = new ModelAndView();// 新建视图
		

		try {
			User u = userService.register(user);
			if (null != u) {
				session.setAttribute("user", u);
				mav.addObject("isloginsuccess", true);
			}
		} catch (IndexOutOfBoundsException e) {
		} catch (WwjException e) {
			e.printStackTrace();
		}
		mav.setViewName("aboutus");

		return mav;
	}
	
	
	
	
	
	
}
