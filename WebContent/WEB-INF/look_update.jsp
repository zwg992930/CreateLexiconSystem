<%@page import="com.zwg2.CreateLexiconSystem.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看和更新用户信息</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/look_update.css" type="text/css">
<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">

//修改用户信息

$(function(){
	var isupdating = false;
	var password_isvalid_mod = true, email_isvalid_mod = true, phone_isvalid_mod = true, QQ_isvalid_mod = true;
	
	$('form[name="userUpdate"]').submit(function(){
		//跳转回到当前页面
		if(password_isvalid_mod && email_isvalid_mod && phone_isvalid_mod && QQ_isvalid_mod){
		isupdating = true;
	}
		return isupdating;
	});
	
	//密码的确认
	$('.modify .password').blur(function () {
		 var v=$(".modify .password").val(),  
   		len=v.replace(/[^\x00-\xff]/g, 'xx').length,  
   		isNum=/^\d+$/.test(v); 
		if(!v){
			  $('.modify .pwd_label').html('！密码不能为空');
			  $('.modify .pwd_label').css("color", "red");
			  password_isvalid_mod = false;

	    }else if(len<6||len>12){  
		 	$('.modify .pwd_label').html('！密码必须是6-12位');
		    $('.modify .pwd_label').css("color", "red");
		    password_isvalid_mod = false;

	    }else if(isNum){  
		      $('.modify .pwd_label').html('！密码不能以纯数字组成');
		      $('.modify .pwd_label').css("color", "red");	
		      password_isvalid_mod = false;

		}else{
			 $('.modify .pwd_label').html('&nbsp;&nbsp;密码输入正确！');
			 $('.modify .pwd_label').css("color", "green");	
			 password_isvalid_mod = true;

			 
		}
   
});
	//确认密码的验证
	$(".modify .confirmpwd, .modify .password").blur(function(){  
			 var v = $(".modify .confirmpwd").val();  
			 var c = $(".modify .password").val();  
		if(!v){
			  	$('.modify .confirmpwd_label').html('密码不能为空');
			    $('.modify .confirmpwd_label').css("color", "red");
			    password_isvalid_mod = false;

		}
	   if( v != c){   
			  $('.modify .confirmpwd_label').html('两次密码不一致');
			  $('.modify .confirmpwd_label').css("color", "red");	
			  password_isvalid_mod = false;

		 }else{
			 $('.modify .confirmpwd_label').html('密码一致');
			  	 $('.modify .confirmpwd_label').css("color", "green");
			  	password_isvalid_mod = true;

		 }
	});	
	
	//邮箱的确认
	 $('.modify .email').blur(function () {
   var email = $.trim($(this).val().trim());
   var cur_user_uid = $.trim($('.modify .uid').val().trim());
   var reg = /^[_a-zA-Z\d\-\.]+@[_a-zA-Z\d\-]+(\.[_a-zA-Z\d\-]+)+$/; //邮箱验证正则表达式

   if (email == "") {
       $('.modify .email_label').html('邮箱不能为空');
		       $('.modify .email_label').css("color", "red");	
		    	email_isvalid_mod = false;

   }
   else if (!reg.test($(this).val())) {
       $('.modify .email_label').html('邮箱格式不正确');
			   $('.modify .email_label').css("color", "red");	
				email_isvalid_mod = false;

   }
   else {
	   
	   $.ajax({
	    		url:"${pageContext.request.contextPath}/user/ajaxcheckemail_mod.do",
	    		async:false,
	    		dataType : "text",
				data : "email=" + email +"&cur_user_uid="+cur_user_uid,
				type : "post",
			    success : function(data) {
			    	if(data.trim() == "ok"){
			    		$('.modify .email_label').html('该邮箱可以使用');
     			 	$('.modify .email_label').css("color", "green");
     			    email_isvalid_mod = true;
			    		

			    	}else{
			    		 $('.modify .email_label').html('邮箱已存在');
	        			 $('.modify .email_label').css("color", "red");
	        			 email_isvalid_mod = false;

			    	}
			    	
			    },
			    
			    error : function(data){
			    	alert("查询失败");
			    }
			    
		});
   }
});
	
	 $('.modify .phone').blur(function () {
 var phone = $.trim($(this).val().trim());
 var cur_user_uid = $.trim($('.modify .uid').val().trim());
 var reg= /^(13[0-9]{9})|(15[89][0-9]{8})$/;
 if (phone == "") {
     $('.modify .phone_label').html('手机号码不能为空');
		$('.modify .phone_label').css("color", "red");	
		phone_isvalid_mod = false;

 }
 else if (!reg.test(phone)) {
     $('.modify .phone_label').html('手机号格式不正确');
		$('.modify .phone_label').css("color", "red");	
		phone_isvalid_mod = false;

 }
 else {
	   
	   $.ajax({
	    		url:"${pageContext.request.contextPath}/user/ajaxcheckphone_mod.do",
	    		async:false,
	    		dataType : "text",
				data : "phone=" + phone +"&cur_user_uid="+cur_user_uid,
				type : "post",
			    success : function(data) {
			    	if(data.trim() == "ok"){
			    		$('.modify .phone_label').html('该手机号可以使用');
	        			$('.modify .phone_label').css("color", "green");
	        			phone_isvalid_mod = true;

			    	}else{
     				$('.modify .phone_label').html('手机号已存在');
    					$('.modify .phone_label').css("color", "red");
    					phone_isvalid_mod = false;

			    	}
			    	
			    },
			    
			    error : function(data){
			    	alert("查询失败");
			    }
			    
		});
 }
});
	
	 
	$('.modify .QQ').blur(function () {
var QQ = $.trim($(this).val().trim());
var cur_user_uid = $.trim($('.modify .uid').val().trim());
var reg = RegExp(/^[1-9][0-9]{4,9}$/);
if (QQ == "") {
     $('.modify .QQ_label').html('QQ号码不能为空');
		$('.modify .QQ_label').css("color", "red");	
		QQ_isvalid_mod = false;

}
else if (!reg.test(QQ)) {
     $('.modify .QQ_label').html('QQ号格式不正确');
		$('.modify .QQ_label').css("color", "red");	
		QQ_isvalid_mod = false;

}
else {
	   
	   $.ajax({
 		url:"${pageContext.request.contextPath}/user/ajaxcheckQQ_mod.do",
 		async:false,
 		dataType : "text",
			data : "QQ=" + QQ +"&cur_user_uid="+cur_user_uid,
			type : "post",
		    success : function(data) {
		    	if(data.trim() == "ok"){
		    		$('.modify .QQ_label').html('该QQ号可以使用');
	       			$('.modify .QQ_label').css("color", "green");
	       			QQ_isvalid_mod = true;

		    	}else{
	       			$('.modify .QQ_label').html('QQ号已存在');
	   			 	$('.modify .QQ_label').css("color", "red");
	   			 	QQ_isvalid_mod = false;

		    	}
		    	
		    },
		    
		    error : function(data){
		    	alert("查询失败");
		    }
		    
	});
	   
	   
	   
}
});
	
	
	 $(".updateuser .close a").click(function(){
      $(".updateuser, .shade, .modify").hide();
  });
	/*
	首先判断词库是否已经被创建
	*/
	$("a.createLexicon").click(function(){
		$.ajax({
			type:"post",
			url:"<c:url value='/manager/createlexicodatabase.do'/>",
			dataType:"text",
			async: false,
			cache : false,
			success : function(result){
				alert(JSON.parse(result.trim()));
				
			}, 
			error : function(){
				alert("数据库链接失败");
			}
			
		});
		
		
	});
	
	
	$("a.deleeLexicon").click(function(){
		$.ajax({
			type:"post",
			url:"<c:url value='/manager/cleardata_del_root.do'/>",
			dataType:"text",
			async: false,
			cache : false,
			success : function(result){
				alert(JSON.parse(result.trim()));
				
			}, 
			error : function(){
				alert("数据库链接失败");
			}
			
		});
		
		
	});
	
});

</script>
</head>
<body>
	<div class="shade">
	</div>
	<div class="updateuser">
	<div class="modify">
        <div class="close"><a href="javascript:;">
        	<img alt="" src="${pageContext.request.contextPath}/images/closebutton.png">
        </a></div>
        <form action="${pageContext.request.contextPath}/manager/adminuserupdate.do" id ="form" method="post" name="userUpdate">
            <table>
            	<tr>
                    <th >用户Id：</th>
                    <td><input type="text" class="uid textInput" name="uid" readonly="readonly" value="${lookuser.uid}"/><label class ="uid_label"></label></td>
                </tr>
                <tr>
                    <th >用户名：</th>
                    <td><input type="text" class="name textInput" name="name" readonly="readonly"  value="${lookuser.name}"/><label class ="name_label"></label></td>
                </tr>
                <tr>
                    <th > 密&nbsp;&nbsp;码：</th>
                    <td><input id="password" type="password" name="password" class="password textInput"  value="${lookuser.password}"/> <label class="pwd_label">&nbsp;&nbsp;6-12位数字字母组合</label> </td>
                </tr>
               
                <tr>
                    <th >确认密码：</th>
                    <td><input type="password" class="confirmpwd textInput"  value="${lookuser.password}"/><label class="confirmpwd_label" >&nbsp;</label></td>
                </tr>
                <tr>
                    <th >性&nbsp;&nbsp;别：</th>
                    <td>
                    	<c:choose>
							<c:when test='${lookuser.sex == "male"}'>
								<input name="sex" class="male" type="radio" value="male" checked="checked"/>男&nbsp;&nbsp;
		                    	<input class="female" name="sex" value="female" type="radio" /> 女
							</c:when>
							<c:otherwise>
								<input name="sex" class="male" type="radio" value="male"/>男&nbsp;&nbsp;
		                    	<input class="female" name="sex" value="female" type="radio"  checked="checked"/> 女
							</c:otherwise>
						</c:choose>
                    </td>
                </tr>
                <tr>
                    <th >电&nbsp;&nbsp;话：</th>
                    <td><input name="phone" type ="text" class="phone textInput"  value="${lookuser.phone}"/><label class ="phone_label"></label></td>
                </tr>
                <tr>
                    <th >Q&nbsp;&nbsp;Q：</th>
                    <td><input name="QQ" type="text" class="QQ textInput"  value="${lookuser.QQ}"/><label class ="QQ_label"></label></td>
                </tr>
                <tr>
                    <th>邮&nbsp;&nbsp;箱：</th>
                    <td><input name="email" type ="text" class="email textInput"  value="${lookuser.email}"/><label class ="email_label"></label></td>
                </tr>
                <tr>
                    <th >联系地址：</th>
                    <td><input name="addr" type ="text" class="addr textInput"  value="${lookuser.addr}"/></td>
                </tr>

            </table>
            <input type="submit" value="修改" class="btn submit"  />
        </form>
    </div>
	</div>

</body>
</html>