<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<script type="text/javascript">
document.oncontextmenu = function(e){
	return false;
};


</script>
</head>

<frameset id="top-bottom-wrap" rows="83,*" cols="*" frameborder="no" border="0" framespacing="0">
	<frame src="top.do" name="topFrame" scrolling="No"
		noresize="noresize">
	<frameset rows="*" cols="560,*" framespacing="0" frameborder="no" border="0">
		<frame name="folder" src="accessInit.do">
		<frame name="quest" src="aboutus.do">
	</frameset>
</frameset>
<noframes>
	<body></body>
</noframes>




</html>