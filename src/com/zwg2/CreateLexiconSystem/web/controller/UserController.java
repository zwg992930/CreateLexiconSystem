package com.zwg2.CreateLexiconSystem.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zwg2.CreateLexiconSystem.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userservice;

	// 检查用户名是否已存在
	@RequestMapping(value = "/ajaxcheckusername", method = RequestMethod.POST)
	public void checkusername(@Param String username,
			HttpServletResponse response) throws IOException {
		System.out.println(username);
		boolean boo = userservice.checkUserName(username);
		if (boo) {
			response.getWriter().println("ok");
		} else {
			response.getWriter().println("no");
		}

	}

	// 检查邮箱是否已存在
	@RequestMapping(value = "/ajaxcheckemail", method = RequestMethod.POST)
	public void checkEmail(@Param String email, HttpServletResponse response)
			throws IOException {
		boolean boo = userservice.checkEmail(email);
		if (boo) {
			response.getWriter().println("ok");
		} else {
			response.getWriter().println("no");
		}
	}

	// 检查QQ号是否重复
	@RequestMapping(value = "/ajaxcheckQQ", method = RequestMethod.POST)
	public void checkQQ(@Param String QQ, HttpServletResponse response)
			throws IOException {
		boolean boo = userservice.checkQQ(QQ);
		if (boo) {
			response.getWriter().println("ok");
		} else {
			response.getWriter().println("no");
		}
	}

	// 检查电话是否已存在
	@RequestMapping(value = "/ajaxcheckphone", method = RequestMethod.POST)
	public void checkPhone(@Param String phone, HttpServletResponse response)
			throws IOException {
		boolean boo = userservice.checkPhone(phone);
		if (boo) {
			response.getWriter().println("ok");
		} else {
			response.getWriter().println("no");
		}
	}
	
	
		// 检查邮箱是否已存在
		@RequestMapping(value = "/ajaxcheckemail_mod", method = RequestMethod.POST)
		public void checkEmailMod(@Param Integer cur_user_uid, @Param String email, HttpServletResponse response)
				throws IOException {
			boolean boo = userservice.checkEmailMod(cur_user_uid, email);
			if (boo) {
				response.getWriter().println("ok");
			} else {
				response.getWriter().println("no");
			}
		}

		// 检查QQ号是否重复
		@RequestMapping(value = "/ajaxcheckQQ_mod", method = RequestMethod.POST)
		public void checkQQMod(@Param Integer cur_user_uid, @Param String QQ, HttpServletResponse response)
				throws IOException {
			boolean boo = userservice.checkQQMod(cur_user_uid,QQ);
			if (boo) {
				response.getWriter().println("ok");
			} else {
				response.getWriter().println("no");
			}
		}

		// 检查电话是否已存在
		@RequestMapping(value = "/ajaxcheckphone_mod", method = RequestMethod.POST)
		public void checkPhoneMod(@Param Integer cur_user_uid, @Param String phone, HttpServletResponse response)
				throws IOException {
			boolean boo = userservice.checkPhoneMod(cur_user_uid, phone);
			if (boo) {
				response.getWriter().println("ok");
			} else {
				response.getWriter().println("no");
			}
		}


}
