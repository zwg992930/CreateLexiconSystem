<%@page import="com.zwg2.CreateLexiconSystem.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" href="css/index.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="css/login.css" type="text/css" media="screen" />
    <script src="js/jquery-1.9.1.min.js"></script>
    
    <script>
        $(function(){
            $('.loginfm a').click(function(){
                 $(".loginfm").slideUp();
                 $(".shade").hide();
             }); 


            $(".register .close a").click(function(){
                $(".register").hide();
            });
            
            
            $(".btnRegister").click(function(){
            	 $(".loginfm").hide();
            	 $(".login").hide();
            	 $(".register").slideDown();
            	
            });


      });
        
        function f_islogin(){
        	<%
            User user = (User)request.getSession().getAttribute("user");
        	String str = (String)request.getAttribute("type");
            if(null == user){
            	if(str.equals("login")){
            	%>
            	$(".shade").show();
                $(".loginfm").slideDown();
                $(".login").show();
                $(".register").hide();
            	<%
            	
            	}else if(str.equals("reg")){ %>
            	$(".shade").show();
                $(".loginfm").slideDown();
                $(".login").hide();
                $(".register").show();
            	<%
            		
            	}
            }else{
            	%>
            	$(".shade").hide();
                $(".loginfm").slideUp();
                $(".login").hide();
                $(".register").show();
            	<%
            }
            %>
        	
        }
        
        //检查登录时的用户名
        $(function(){
        f_islogin();
        var islogin = false;
        $("input[name='uname']").bind("change, blur",function(){
        	var $login_user = $(this);
        	var username = $login_user.val().trim();
        	if(username.length == 0){
        		 $login_user.after('<span class="warning">用户名不能为空</span>');
        	}else{
        		
        	$.ajax({
				type:"post",
				url:"<c:url value='/user/ajaxcheckusername.do'/>",
				data : "username=" + username,
				dataType:"text",
				async: false,
				cache : false,
				success:function(result){
					 if((result.trim())=="ok"){
						 $login_user.parent().children("span").remove();
						 $login_user.after('<span class="success">用户名可以使用</span>');
						 islogin = true;
						 
					}else if((result.trim())=="no"){
						 $login_user.parent().children("span").remove();
						$login_user.after('<span class="warning">用户名不存在</span>');
						islogin = false;
					}
				},
				error:function(){
					alert("连接失败");
				}
			});
        	
        	}
        	
        	
        	
        	
        });
        
        $('form[name="userlogin"]').submit(function(){
        	
        	return islogin;
        });
        
        
        
     });
        
         $(function(){
        	var isregstering = false;
        	var name_isvalid = false, password_isvalid = false, email_isvalid = false, phone_isvalid = false, QQ_isvalid = false;
        	
        	$('form[name="userReg"]').submit(function(){
        		if(name_isvalid && password_isvalid && email_isvalid && phone_isvalid && QQ_isvalid){
            		isregstering = true;
            	}
        		return isregstering;
        	});
        	
        	$("#name").blur(function(){  
        		
        	    var v=$("#name").val(),  
        	       len=v.replace(/[^\x00-\xff]/g, 'xx').length,  
        	       isName=/[^\u4e00-\u9fa50-9a-zA-Z]/ig.test(v),  
        	       isNum=/^\d+$/.test(v);
        	    var o=$("#name");  
        	    if(!v){   
        	           $('#name_label').html('用户名不能为空!');
        			   $('#name_label').css("color", "red");
        			   name_isvalid = false;
        	    }else if(len<6||len>12){  
        			 $('#name_label').html('用户名必须是6-12位');
        			  $('#name_label').css("color", "red");
        			  name_isvalid = false;

        	    }else if(isNum){  
        			 $('#name_label').html('用户名不能以纯数字组成');
        			  $('#name_label').css("color", "red"); 
        			  name_isvalid = false;

        	    }else if(isName){  
        			 $('#name_label').html('用户名不能有特殊字符存在');
        			  $('#name_label').css("color", "red"); 
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
         			    		 $('#name_label').html('用户名已存在');
         	        			 $('#name_label').css("color", "red");
         	        			name_isvalid = false;

         			    	}else{
         			    		$('#name_label').html('该用户名可以使用');
        	        			 $('#name_label').css("color", "green");
        	        			 name_isvalid = true;

         			    	}
         			    	
         			    },
         			    
         			    error : function(data){
         			    	alert("连接失败");
         			    }
         			    
         		});
        	    	 
        	    	 
        		} 
        	});
        	
        	
        	//密码的确认
        	$('#password').blur(function () {
        		 var v=$("#password").val(),  
               		len=v.replace(/[^\x00-\xff]/g, 'xx').length,  
               		isNum=/^\d+$/.test(v); 
        		if(!v){
        			  $('#pwd_label').html('密码不能为空');
        			  $('#pwd_label').css("color", "red");
        			  password_isvalid = false;

           	    }else if(len<6||len>12){  
        		 	$('#pwd_label').html('密码必须是6-12位');
        		    $('#pwd_label').css("color", "red");
        		    password_isvalid = false;

        	    }else if(isNum){  
        		      $('#pwd_label').html('密码不能以纯数字组成');
        		      $('#pwd_label').css("color", "red");	
        		      password_isvalid = false;

        		}else{
        			 $('#pwd_label').html('&nbsp;&nbsp;密码输入正确！');
        			 $('#pwd_label').css("color", "green");	
        			 password_isvalid = true;

        			 
        		}
               
            });
        	//确认密码的验证
        	$("#confirmpwd, #password").blur(function(){  
           			 var v = $("#confirmpwd").val();  
           			 var c = $("#password").val();  
        		
          	   if( v != c){   
        			  $('#confirmpwd_label').html('两次密码不一致');
        			  $('#confirmpwd_label').css("color", "red");	
        			  password_isvalid = false;

           		 }else if(v.length == 0){
           			$('#confirmpwd_label').html('密码不能为空');
    			    $('#confirmpwd_label').css("color", "red");
    			    password_isvalid = false;
           		 }else{
           			 $('#confirmpwd_label').html('密码一致');
       			  	 $('#confirmpwd_label').css("color", "green");
       			  	password_isvalid = true;

           		 }
        	});	
        	
        	//邮箱的确认
       	 $('#email').blur(function () {
               var email = $.trim($(this).val().trim());
               var reg = /^[_a-zA-Z\d\-\.]+@[_a-zA-Z\d\-]+(\.[_a-zA-Z\d\-]+)+$/; //邮箱验证正则表达式

               if (email == "") {
                   $('#email_label').html('邮箱不能为空');
       		       $('#email_label').css("color", "red");	
       		    	email_isvalid = false;

               }
               else if (!reg.test($(this).val())) {
                   $('#email_label').html('邮箱格式不正确');
       			   $('#email_label').css("color", "red");	
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
        			    		 $('#email_label').html('邮箱已存在');
        	        			 $('#email_label').css("color", "red");
        	        			 email_isvalid = false;

        			    	}else{
        			    		$('#email_label').html('该邮箱可以使用');
       	        			 	$('#email_label').css("color", "green");
       	        			    email_isvalid = true;

        			    	}
        			    	
        			    },
        			    
        			    error : function(data){
        			    	alert("连接失败");
        			    }
        			    
        		});
               }
           });
        	
       	 $('#phone').blur(function () {
             var phone = $.trim($(this).val().trim());
             var reg= /^(13[0-9]{9})|(15[89][0-9]{8})$/;
             if (phone == "") {
                 $('#phone_label').html('手机号码不能为空');
     			$('#phone_label').css("color", "red");	
     			phone_isvalid = false;

             }
             else if (!reg.test(phone)) {
                 $('#phone_label').html('手机号格式不正确');
     			$('#phone_label').css("color", "red");	
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
      			    		 $('#phone_label').html('手机号已存在');
      	        			 $('#phone_label').css("color", "red");
      	        			phone_isvalid = false;

      			    	}else{
      			    		$('#phone_label').html('该手机号可以使用');
     	        			$('#phone_label').css("color", "green");
     	        			phone_isvalid = true;

      			    	}
      			    	
      			    },
      			    
      			    error : function(data){
      			    	alert("连接失败");
      			    }
      			    
      		});
             }
         });
        	
       	 
       	$('#QQ').blur(function () {
            var QQ = $.trim($(this).val().trim());
            var reg = RegExp(/^[1-9][0-9]{4,9}$/);
            if (QQ == "") {
                $('#QQ_label').html('QQ号码不能为空');
    			$('#QQ_label').css("color", "red");	
    			QQ_isvalid = false;

            }
            else if (!reg.test(QQ)) {
                $('#QQ_label').html('QQ号格式不正确');
    			$('#QQ_label').css("color", "red");	
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
     			    		 $('#QQ_label').html('QQ号已存在');
     	        			 $('#QQ_label').css("color", "red");
     	        			QQ_isvalid = false;

     			    	}else{
     			    		$('#QQ_label').html('该QQ号可以使用');
    	        			$('#QQ_label').css("color", "green");
    	        			QQ_isvalid = true;

     			    	}
     			    	
     			    },
     			    
     			    error : function(data){
     			    	alert("连接失败");
     			    }
     			    
     		});
         	   
         	   
         	   
            }
        });
        }); 
         
    </script>

</head>
<body>

<div class="shade">
        <div class="login">
            <div class="loginfm">
            <div class="close"><a href="javascript:;">
            	<img alt="" src="/images/closebutton.png">
            </a></div>
                <form class="box login" name="userlogin" action="login.do">
                    <fieldset class="boxBody">
                        <label >用户名</label>
                        <input name="uname" type="text" tabindex="1" placeholder="在此输入您的用户名" required>
                        <label>密码</label>
                        <input name="upwd"  type="password" tabindex="2" required>
                    </fieldset>
                    <footer>
                        <input type="submit" class="btnLogin" value="登录" tabindex="4">
                        <input type="button" class="btnRegister" value="注册" tabindex="5">
                    </footer>
                </form>
            </div>
        </div>

    <div class="register">
        <div class="close"><a href="javascript:;">
        	<img alt="" src="images/closebutton.png">
        </a></div>
        <form action="register.do" id ="form" method="post" name="userReg">
            <table >
                <tr>
                    <th >用户名：</th>
                    <td><input id="name" type="text" class="textInput" name="name"/><label id ="name_label"></label></td>
                </tr>
                <tr>
                    <th > 密&nbsp;&nbsp;码：</th>
                    <td><input id="password" type="password" name="password" class="textInput" /> <label id="pwd_label">&nbsp;&nbsp;6-12位数字字母组合</label> </td>
                </tr>
               
                <tr>
                    <th >确认密码：</th>
                    <td><input id="confirmpwd" type="password" class="textInput" /><label id="confirmpwd_label">&nbsp;</label></td>
                </tr>
                <tr>
                    <th >性&nbsp;&nbsp;别：</th>
                    <td><input id="male" name="sex" type="radio" value="male" checked="checked"/>男&nbsp;&nbsp;<input id ="female" name="sex" value="female" type="radio" /> 女</td>
                </tr>
                <tr>
                    <th >电&nbsp;&nbsp;话：</th>
                    <td><input id ="phone" name="phone" type ="text" class="textInput"/><label id ="phone_label"></label></td>
                </tr>
                <tr>
                    <th >Q&nbsp;&nbsp;Q：</th>
                    <td><input id ="QQ" name="QQ" type="text" class="textInput"/><label id ="QQ_label"></label></td>
                </tr>
                <tr>
                    <th>邮&nbsp;&nbsp;箱：</th>
                    <td><input id ="email" name="email" type ="text" class="textInput"/><label id ="email_label"></label></td>
                </tr>
                <tr>
                    <th >联系地址：</th>
                    <td><input id ="addr" name="addr" type ="text" class="textInput"/></td>
                </tr>

            </table>
            <input id ="submit" type="submit" value="注册" class="btn"  />
        </form>
    </div>
    
    
</div>

</body>
</html>