<%@page import="com.zwg2.CreateLexiconSystem.model.Manager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="Author" content="wts_crazy">
<title>管理员操作界面</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminProcess.css" type="text/css">
<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/adminProcess.js"></script>

<script type="text/javascript">
$(function(){
	//默认情况下显示第一个
	
	$("#tab a.manager").each(function(){
		$(this).click(function(){
			click_index = $("#tab a.manager").index($(this));
			$.showByIndex(click_index);
		});
	});
	
	$.extend({
		showByIndex : function(click_index){
			if(click_index == 0){
				$.ajax({
					type:"post",
					url:"<c:url value='/manager/queryuserlist.do'/>",
					dataType:"text",
					cache : false,
// 					async: false,
					success:function(result){
						var arrayuser = JSON.parse(result);
						var userlisthtml = "";
						
						for(var i=0;i<arrayuser.length;i++){
							var user_tr = "<tr><td>"+arrayuser[i].uid + "</td><td>"+arrayuser[i].name + "</td><td>"+arrayuser[i].password + "</td><td>"+arrayuser[i].sex + "</td><td>"+arrayuser[i].phone + "</td><td>"+arrayuser[i].QQ + "</td><td>"+arrayuser[i].email + "</td><td>"+arrayuser[i].addr + "</td><td>"+ arrayuser[i].regDateshow + "</td><td>"+arrayuser[i].IP + "</td></tr>";
							userlisthtml += user_tr;
							
						}
						
						
					$(".tab-content").eq(click_index).find("tbody").html(userlisthtml);
					
					$(".tab-content").eq(click_index).find("tbody tr").hover(function(){
						$(this).append('<div><a class="add" href="javascript:;"><span>新增</span></a><a class="del" href="javascript:;"><span>删除</span></a><a class="mod" href="javascript:;"><span>修改</span></a></div>');
						var $current_row = $(this);
						
						$(this).find("a.add").click(function(){
							$(".shade,.adduser").show();

						});
						
						$(this).find("a.mod").click(function(){
							$(".shade,.updateuser").show();	
							var user_uid_mod = $current_row.children("td").eq(0).text().trim();
							var user_name_mod = $current_row.children("td").eq(1).text().trim();
							var user_password_mod = $current_row.children("td").eq(2).text().trim();
							var user_sex_mod = $current_row.children("td").eq(3).text().trim();
							var user_phone_mod = $current_row.children("td").eq(4).text().trim();
							var user_QQ_mod = $current_row.children("td").eq(5).text().trim();
							var user_email_mod = $current_row.children("td").eq(6).text().trim();
							var user_addr_mod = $current_row.children("td").eq(7).text().trim();
							
							$(".modify .uid").val(user_uid_mod);
							$(".modify .name").val(user_name_mod);
							$(".modify .password").val(user_password_mod);
							$(".modify .confirmpwd").val(user_password_mod);
							$('.modify input[name="sex"][value='+user_sex_mod+']').attr("checked","checked");
							$(".modify .phone").val(user_phone_mod);
							$(".modify .QQ").val(user_QQ_mod);
							$(".modify .email").val(user_email_mod);
							$(".modify .addr").val(user_addr_mod);
							
						});
						
						
						$(this).find("a.del").click(function(){
							var msg = "是否要删除该条用户信息\n\n请确认！";
							
							if (confirm(msg)==true){
								//根据用户的电话号码信息进行删除
								var cur_user_uid = $current_row.children("td").eq(0).text().trim();
								$.ajax({
									type:"post",
									url:"<c:url value='/manager/deleteuser.do'/>",
									data:"cur_user_uid="+cur_user_uid,
									dataType:"text",
									cache : false,
									async: false,
									success : function(result){
										$current_row.remove();
										$.removeUserSuccessAfter();
									}
									
									
								});
							}
						});
					},function(){
						$(this).find("div").remove();
					});
					
					
					},
					error:function(){
						alert("查询失败");
					}
				});
				
			}		
		
	},
		removeUserSuccessAfter : function(result){
			var arrayuser = JSON.parse(result);
			var userlisthtml = "";
			
			for(var i=0;i<arrayuser.length;i++){
				
				var user_tr = "<tr><td>"+arrayuser[i].uid + "</td><td>"+arrayuser[i].name + "</td><td>"+arrayuser[i].password + "</td><td>"+arrayuser[i].sex + "</td><td>"+arrayuser[i].phone + "</td><td>"+arrayuser[i].QQ + "</td><td>"+arrayuser[i].email + "</td><td>"+arrayuser[i].addr + "</td><td>"+ arrayuser[i].regDateshow + "</td><td>"+arrayuser[i].IP + "</td></tr>";
				userlisthtml += user_tr;
				
			}
			
		$(".tab-content").eq(click_index).find("tbody").html(userlisthtml);
		
		$(".tab-content").eq(click_index).find("tbody tr").hover(function(){
			$(this).append('<div><a class="add" href="javascript:;"><span>新增</span></a><a class="del" href="javascript:;"><span>删除</span></a><a class="modify" href="javascript:;"><span>修改</span></a></div>');
			var $current_row = $(this);
			$(this).find("a.add").click(function(){
				$(".shade,.adduser").show();				
			});
			
			$(this).find("a.mod").click(function(){
				$(".shade,.updateuser").show();	
				var user_uid_mod = $current_row.children("td").eq(0).text().trim();
				var user_name_mod = $current_row.children("td").eq(1).text().trim();
				var user_password_mod = $current_row.children("td").eq(2).text().trim();
				var user_sex_mod = $current_row.children("td").eq(3).text().trim();
				var user_phone_mod = $current_row.children("td").eq(4).text().trim();
				var user_QQ_mod = $current_row.children("td").eq(5).text().trim();
				var user_email_mod = $current_row.children("td").eq(6).text().trim();
				var user_addr_mod = $current_row.children("td").eq(7).text().trim();
				
				$(".modify .uid").val(user_uid_mod);
				$(".modify .name").val(user_name_mod);
				$(".modify .password").val(user_password_mod);
				$(".modify .confirmpwd").val(user_password_mod);
				$('.modify input[name="sex"][value='+user_sex_mod+']').attr("checked","checked");
				$(".modify .phone").val(user_phone_mod);
				$(".modify .QQ").val(user_QQ_mod);
				$(".modify .email").val(user_email_mod);
				$(".modify .addr").val(user_addr_mod);
				
			});
			
			
			$(this).find("a.del").click(function(){
				var msg = "是否要删除该条用户信息\n\n请确认！";
				if (confirm(msg)==true){
					//根据用户的电话号码信息进行删除
					var cur_user_uid = $current_row.children("td").eq(0).text().trim();
					$.ajax({
						type:"post",
						url:"<c:url value='/manager/deleteuser.do'/>",
						data:"cur_user_uid="+cur_user_uid,
						dataType:"text",
						cache : false,
						async: false,
						success : function(result){
							$current_row.remove();
						}
						
					});
				}

			});
		},function(){
			$(this).find("div").remove();
		});
		}
	
	});
	
});


$(function(){
	var isregstering = false;
	var name_isvalid = false, password_isvalid = false, email_isvalid = false, phone_isvalid = false, QQ_isvalid = false;
	
	$('form[name="userReg"]').submit(function(){
		//跳转回到当前页面
		if(name_isvalid && password_isvalid && email_isvalid && phone_isvalid && QQ_isvalid){
   		isregstering = true;
   	}
		return isregstering;
	});
	
	$(".register .name").blur(function(){  
	    var v=$(".register .name").val(),  
	       len=v.replace(/[^\x00-\xff]/g, 'xx').length,  
	       isName=/[^\u4e00-\u9fa50-9a-zA-Z]/ig.test(v),  
	       isNum=/^\d+$/.test(v);
	    var o=$(".register .name");  
	    if(!v){   
	           $('.register .name_label').html('用户名不能为空!');
			   $('.register .name_label').css("color", "red");
			   name_isvalid = false;
	    }else if(len<6||len>12){  
			 $('.register .name_label').html('用户名必须是6-12位');
			  $('.register .name_label').css("color", "red");
			  name_isvalid = false;

	    }else if(isNum){  
			 $('.register .name_label').html('用户名不能以纯数字组成');
			  $('.register .name_label').css("color", "red"); 
			  name_isvalid = false;

	    }else if(isName){  
			 $('.register .name_label').html('用户名不能有特殊字符存在');
			  $('.register .name_label').css("color", "red"); 
			  name_isvalid = false;

	    }else{ 
	    	 $.ajax({
	    		url:"${pageContext.request.contextPath}/user/ajaxcheckusername.do",
	    		async:false,
	    		dataType : "text",
				data : "username=" + v,
				type : "post",
			    success : function(data) {
			    	if(data.trim() == "ok"){
			    		 $('.register .name_label').html('用户名已存在');
	        			 $('.register .name_label').css("color", "red");
	        			name_isvalid = false;

			    	}else{
			    		$('.register .name_label').html('该用户名可以使用');
	        			 $('.register .name_label').css("color", "green");
	        			 name_isvalid = true;

			    	}
			    	
			    },
			    
			    error : function(data){
			    	alert("查询失败");
			    }
			    
		});
	    	 
	    	 
		} 
	});
	
	
	//密码的确认
	$('.register .password').blur(function () {
		 var v=$(".register .password").val(),  
      		len=v.replace(/[^\x00-\xff]/g, 'xx').length,  
      		isNum=/^\d+$/.test(v); 
		if(!v){
			  $('.register .pwd_label').html('！密码不能为空');
			  $('.register .pwd_label').css("color", "red");
			  password_isvalid = false;

  	    }else if(len<6||len>12){  
		 	$('.register .pwd_label').html('！密码必须是6-12位');
		    $('.register .pwd_label').css("color", "red");
		    password_isvalid = false;

	    }else if(isNum){  
		      $('.register .pwd_label').html('！密码不能以纯数字组成');
		      $('.register .pwd_label').css("color", "red");	
		      password_isvalid = false;

		}else{
			 $('.register .pwd_label').html('&nbsp;&nbsp;密码输入正确！');
			 $('.register .pwd_label').css("color", "green");	
			 password_isvalid = true;

			 
		}
      
   });
	//确认密码的验证
	$(".register .confirmpwd, .register .password").blur(function(){  
  			 var v = $(".register .confirmpwd").val();  
  			 var c = $(".register .password").val();  
		if(!v){
			  	$('.register .confirmpwd_label').html('密码不能为空');
			    $('.register .confirmpwd_label').css("color", "red");
			    password_isvalid = false;

		}
 	   if( v != c){   
			  $('.register .confirmpwd_label').html('两次密码不一致');
			  $('.register .confirmpwd_label').css("color", "red");	
			  password_isvalid = false;

  		 }else{
  			 $('.register .confirmpwd_label').html('密码一致');
			  	 $('.register .confirmpwd_label').css("color", "green");
			  	password_isvalid = true;

  		 }
	});	
	
	//邮箱的确认
	 $('.register .email').blur(function () {
      var email = $.trim($(this).val().trim());
      var reg = /^[_a-zA-Z\d\-\.]+@[_a-zA-Z\d\-]+(\.[_a-zA-Z\d\-]+)+$/; //邮箱验证正则表达式

      if (email == "") {
          $('.register .email_label').html('邮箱不能为空');
		       $('.register .email_label').css("color", "red");	
		    	email_isvalid = false;

      }
      else if (!reg.test($(this).val())) {
          $('.register .email_label').html('邮箱格式不正确');
			   $('.register .email_label').css("color", "red");	
				email_isvalid = false;

      }
      else {
   	   
   	   $.ajax({
	    		url:"${pageContext.request.contextPath}/user/ajaxcheckemail.do",
	    		async:false,
	    		dataType : "text",
				data : "email=" + email,
				type : "post",
			    success : function(data) {
			    	if(data.trim() == "ok"){
			    		 $('.register .email_label').html('邮箱已存在');
	        			 $('.register .email_label').css("color", "red");
	        			 email_isvalid = false;

			    	}else{
			    		$('.register .email_label').html('该邮箱可以使用');
	        			 	$('.register .email_label').css("color", "green");
	        			    email_isvalid = true;

			    	}
			    	
			    },
			    
			    error : function(data){
			    	alert("查询失败");
			    }
			    
		});
      }
  });
	
	 $('.register .phone').blur(function () {
    var phone = $.trim($(this).val().trim());
    var reg= /^(13[0-9]{9})|(15[89][0-9]{8})$/;
    if (phone == "") {
        $('.register .phone_label').html('手机号码不能为空');
		$('.register .phone_label').css("color", "red");	
		phone_isvalid = false;

    }
    else if (!reg.test(phone)) {
        $('.register .phone_label').html('手机号格式不正确');
		$('.register .phone_label').css("color", "red");	
		phone_isvalid = false;

    }
    else {
 	   
 	   $.ajax({
	    		url:"${pageContext.request.contextPath}/user/ajaxcheckphone.do",
	    		async:false,
	    		dataType : "text",
				data : "phone=" + phone,
				type : "post",
			    success : function(data) {
			    	if(data.trim() == "ok"){
			    		 $('.register .phone_label').html('手机号已存在');
	        			 $('.register .phone_label').css("color", "red");
	        			phone_isvalid = false;

			    	}else{
			    		$('.register .phone_label').html('该手机号可以使用');
        			$('.register .phone_label').css("color", "green");
        			phone_isvalid = true;

			    	}
			    	
			    },
			    
			    error : function(data){
			    	alert("查询失败");
			    }
			    
		});
    }
});
	
	 
	$('.register .QQ').blur(function () {
   var QQ = $.trim($(this).val().trim());
   var reg = RegExp(/^[1-9][0-9]{4,9}$/);
   if (QQ == "") {
       $('.register .QQ_label').html('QQ号码不能为空');
		$('.register .QQ_label').css("color", "red");	
		QQ_isvalid = false;

   }
   else if (!reg.test(QQ)) {
       $('.register .QQ_label').html('QQ号格式不正确');
		$('.register .QQ_label').css("color", "red");	
		QQ_isvalid = false;

   }
   else {
	   
	   $.ajax({
    		url:"${pageContext.request.contextPath}/user/ajaxcheckQQ.do",
    		async:false,
    		dataType : "text",
			data : "QQ=" + QQ,
			type : "post",
		    success : function(data) {
		    	if(data.trim() == "ok"){
		    		 $('.register .QQ_label').html('QQ号已存在');
        			 $('.register .QQ_label').css("color", "red");
        			QQ_isvalid = false;

		    	}else{
		    		$('.register .QQ_label').html('该QQ号可以使用');
       			$('.register .QQ_label').css("color", "green");
       			QQ_isvalid = true;

		    	}
		    	
		    },
		    
		    error : function(data){
		    	alert("查询失败");
		    }
		    
	});
	   
	   
	   
   }
});
	
	 $(".register .close a").click(function(){
         $(".register, .shade, .adduser").hide();
     });
	 
	 
}); 


// 修改用户信息

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
	<div class="adduser">
		
    <div class="register">
        <div class="close"><a href="javascript:;">
        	<img alt="" src="${pageContext.request.contextPath}/images/closebutton.png">
        </a></div>
        <form action="${pageContext.request.contextPath}/manager/adminuserreg.do" id ="form" method="post" name="userReg">
            <table>
                <tr>
                    <th >用户名：</th>
                    <td><input type="text" class="name textInput" name="name"/><label class ="name_label"></label></td>
                </tr>
                <tr>
                    <th > 密&nbsp;&nbsp;码：</th>
                    <td><input id="password" type="password" name="password" class="password textInput" /> <label class="pwd_label">&nbsp;&nbsp;6-12位数字字母组合</label> </td>
                </tr>
               
                <tr>
                    <th >确认密码：</th>
                    <td><input type="password" class="confirmpwd textInput" /><label class="confirmpwd_label">&nbsp;</label></td>
                </tr>
                <tr>
                    <th >性&nbsp;&nbsp;别：</th>
                    <td><input name="sex" class="male" type="radio" value="male" checked="checked"/>男&nbsp;&nbsp;<input class="female" name="sex" value="female" type="radio" /> 女</td>
                </tr>
                <tr>
                    <th >电&nbsp;&nbsp;话：</th>
                    <td><input name="phone" type ="text" class="phone textInput"/><label class ="phone_label"></label></td>
                </tr>
                <tr>
                    <th >Q&nbsp;&nbsp;Q：</th>
                    <td><input name="QQ" type="text" class="QQ textInput"/><label class ="QQ_label"></label></td>
                </tr>
                <tr>
                    <th>邮&nbsp;&nbsp;箱：</th>
                    <td><input name="email" type ="text" class="email textInput"/><label class ="email_label"></label></td>
                </tr>
                <tr>
                    <th >联系地址：</th>
                    <td><input name="addr" type ="text" class="addr textInput"/></td>
                </tr>

            </table>
            <input type="submit" value="注册" class="btn submit"  />
        </form>
    </div>
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
                    <td><input type="text" class="uid textInput" name="uid" readonly="readonly"/><label class ="uid_label"></label></td>
                </tr>
                <tr>
                    <th >用户名：</th>
                    <td><input type="text" class="name textInput" name="name" readonly="readonly"/><label class ="name_label"></label></td>
                </tr>
                <tr>
                    <th > 密&nbsp;&nbsp;码：</th>
                    <td><input id="password" type="password" name="password" class="password textInput" /> <label class="pwd_label">&nbsp;&nbsp;6-12位数字字母组合</label> </td>
                </tr>
               
                <tr>
                    <th >确认密码：</th>
                    <td><input type="password" class="confirmpwd textInput" /><label class="confirmpwd_label">&nbsp;</label></td>
                </tr>
                <tr>
                    <th >性&nbsp;&nbsp;别：</th>
                    <td><input name="sex" class="male" type="radio" value="male"/>男&nbsp;&nbsp;<input class="female" name="sex" value="female" type="radio" /> 女</td>
                </tr>
                <tr>
                    <th >电&nbsp;&nbsp;话：</th>
                    <td><input name="phone" type ="text" class="phone textInput"/><label class ="phone_label"></label></td>
                </tr>
                <tr>
                    <th >Q&nbsp;&nbsp;Q：</th>
                    <td><input name="QQ" type="text" class="QQ textInput"/><label class ="QQ_label"></label></td>
                </tr>
                <tr>
                    <th>邮&nbsp;&nbsp;箱：</th>
                    <td><input name="email" type ="text" class="email textInput"/><label class ="email_label"></label></td>
                </tr>
                <tr>
                    <th >联系地址：</th>
                    <td><input name="addr" type ="text" class="addr textInput"/></td>
                </tr>

            </table>
            <input type="submit" value="修改" class="btn submit"  />
        </form>
    </div>
	</div>
	
	
	<p class="main-title">管理员操作界面</p>
	<div id="tab-wrap" class="clearfix">
		<div id="tab">
			<a class="manager">用户管理</a> <a class="manager">词库管理</a> <a class="manager">权限管理</a>
			<div class="tab-content">
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>用户Id</th>
							<th>用户名</th>
							<th>密码</th>
							<th>性别</th>
							<th>电话</th>
							<th>QQ</th>
							<th>邮箱</th>
							<th>联系地址</th>
							<th>注册日期</th>
							<th>IP</th>
						</tr>
					</thead>
					<tbody>

					</tbody>

				</table>
				
			</div>
			<div class="tab-content">
			
				<div class="lexiconmanager">
					<ul>
						<li><a href="javascript:;" class="createLexicon">1、生成词库</a></li>
						<li><a href="javascript:;" class="deleeLexicon">2、删除词库</a></li>
					</ul>
				
				</div>
			
			</div>
			<div class="tab-content">
			
			<div style="padding-top:190px;padding-left: 180px ;width:70%;text-align:left;font-size:18px;line-height:40px;">
				1、用户不能使用管理员的创建词库和删除词库的功能；<br />
				2、非用户不能使用本词库的增删改查功能。<br />
			</div>
			
			</div>
		</div>
	</div>
</body>
</html>
