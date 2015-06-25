<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/reset.css" type="text/css">
<link rel="stylesheet" href="css/addEntity.css" type="text/css">
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
<!--
		$(function(){
			//使用ajax判定，保证同一文件夹下面不能有重复的文件名称
				$('tbody tr').eq(1).children().eq(1).children().first().blur(function(){
					var newEntityName = $(this).val().trim();
					var newEntityBelongClass = $('tbody tr').eq(0).children().eq(1).children().first().val().trim();
					$.ajax({
						type:"post",
						url:"isRepeat.do",
						data : "questingname="+newEntityName+"&thisEntityBelong="+newEntityBelongClass,
						dataType:"text",
						cache : false,
						success:function(data){
							var boo = JSON.parse(data);
							if(boo){
								alert("该词汇可以使用");
							}else{
								alert("该词汇不能使用");
							}
						},
						error:function(){
							alert("查询失败");
						}
					});
					
				});	
			
			$('#addcolumn').click(function(){
				var tr_length = $('table').eq(0).children().eq(1).children().size();
				var newcolumn = '<tr> <td><textarea rows="" cols="" name="">属性名称'+(tr_length + 1)+
				'</textarea></td><td><textarea rows="" cols="" name="">属性值</textarea></td><td><button type="button" class="delcolumn">删除该行</button></td></tr>';
		 		$('table').eq(0).children().eq(1).children().last().after(newcolumn);
		 		//保证每行中的前一个textarea中的值和后一个textarea中name的值一直
		 		$('tbody tr').each(function(){
		 			var attributename = $(this).children().eq(0).children().first().val().trim();
		 			$(this).children().eq(1).children().first().attr({name:attributename});
		 			$('.delcolumn').each(function(){
		 				$(this).click(function(){
		 					$(this).parent().parent().remove();
		 				});
		 			});
		 			
		 		});
			});
		});

		function submitdata(){
			$('tbody tr').each(function(){
	 			var attributename = $(this).children().eq(0).children().first().val().trim();
	 			$(this).children().eq(1).children().first().attr({name:attributename});
	 		});
			
			
			var tr_len = $('tbody tr').size();
			var arr={};
			for(var i=0;i<tr_len;i++){
				arr[i] = $('tbody tr').eq(i).children().first().children().first().val().trim();
			}
			
			if(isRepeat(arr)){
				alert("属性名必须保持唯一,请详细检查后再提交");
			}else{
				alert("添加成功,请重新访问页面试试");
				document.addnewentity.submit();
				window.parent.frames["folder"].location.reload(); 
				
			}
		}
		
		
		
		
			function isRepeat(arr) {
				var hash = {};
				for ( var i in arr) {
					if (hash[arr[i]])
						return true;
					hash[arr[i]] = true;
				}
				return false;
			}
			//-->
			</script>
</head>
<body>
		<div id="insertmessage">
			<form name="addnewentity" action="AddNewEntity"
				method="post">
				<table style="width:100%;height:auto;">
						<thead>
							<tr>
								<th>属性名</th>
								<th>属性值</th>
								<th>是否删除</th>
							</tr>
		
						</thead>
						<tbody>
							<tr>
								<td><textarea rows="" cols="" name="" readonly="readonly">类路径</textarea></td>
								<td><textarea rows="" cols="" name="类路径" readonly="readonly">${filepath}</textarea></td>
								<td>&nbsp;</td>
							</tr>
		
							<tr>
								<td><textarea rows="" cols="" name="" readonly="readonly">实体名称</textarea></td>
								<td><textarea rows="" cols="" name="实体名称"
										style="overflow: visible;">名称</textarea></td>
								<td>&nbsp;</td>		
							</tr>
						</tbody>
					</table>
				<input type="button" value="提交" onclick="submitdata()">
				<input type="reset" value="重置">
				<input type="button" value="添加属性行" id="addcolumn">
			</form>
	</div>	
</body>
</html>