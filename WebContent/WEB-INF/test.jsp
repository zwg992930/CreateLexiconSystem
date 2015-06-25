<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>

<script type="text/javascript">
$(function(){
	$("button").click(function(){
		$.ajax({
			type:"post",
			url:"<c:url value='/manager/testpath.do'/>",
			dataType:"text",
			cache : false,
			success: function(result){
				var managerlist = JSON.parse(result);
				
				for(var i=0;i<managerlist.length;i++){
					alert(managerlist[i].user.name);
					
				}
				
				
			}
			
		});
	});
	
	
});

</script>
</head>
<body>
结束


<button style="height:40px;line-height:40px;">afdafafa
</button>

</body>
</html>