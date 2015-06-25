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
	
</head>
<body>
<div class="top-wrap clearfix">
<div id="project-name">中文知识库建库系统
</div>
	<ul id="log-reg">
    	<li><a id="log" href="login_reg.do?type=login" target="quest">登录</a></li>
    	<li>&nbsp;|&nbsp;</li>
    	<li><a id="reg" href="login_reg.do?type=reg" target="quest">注册</a></li>
	</ul>

	<ul id="have-log">
    	<li><a id="logname" href="javascript:;" title="查看用户信息" target="quest">${user.name}</a><input type="text" value="${user.uid}" name="uid" style="display:none;"></li>
    	<li>&nbsp;|&nbsp;</li>
    	<li><a id="regout" href="top.do" title="退出登录" >退出</a></li>
	</ul>
</div>


<script type="text/javascript">
	$(function(){
		<%
		
        User user = (User)request.getSession().getAttribute("user");
        if(null == user){
        	%>
            $("#have-log").hide();
            $("#log-reg").show();
        	<%
        }else{
        	%>
            $("#have-log").show();
            $("#log-reg").hide();
        	<%
        }
        %>
    	
		$("#regout").click(function(){
	       	  $.ajax({
		    		url:"logout.do",
		    		async:false,
		    		dataType : "text",
					type : "post",
				    success : function(result) {
				    	if(result.trim() == "ok"){
				    		window.parent.frames["folder"].location.reload(); 
				    		window.parent.location.reload(); 
				    	}
				    },
				    error : function(data){
				    	alert("查询失败");
				    }
			});
	       	});
		$("#logname").click(function(){
			var uid = $(this).parent().find('input').eq(0).val();
			$(this).attr("href","<c:url value='/finduser.do?uid="+uid+"'/>");
			
		});
		
		
	});
	</script>
</body>
</html>