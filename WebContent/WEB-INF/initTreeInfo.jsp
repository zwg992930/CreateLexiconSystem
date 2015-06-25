<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="css/reset.css" type="text/css">
	<link rel="stylesheet" href="css/demo.css" type="text/css">
	<link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/jquery.ztree.core-3.5.min.js"></script>
	<script type="text/javascript" src="js/jquery.ztree.excheck-3.5.min.js"></script>
	<script type="text/javascript" src="js/jquery.ztree.exedit-3.5.min.js"></script>
	<script type="text/javascript">
		<!--
		var setting = {
			view: {
				selectedMulti: false,
				dblClickExpand: false
			},
			async: {
				enable: true,
				url:"TreeInfodata.do",
				autoParam:["id"],
				dataFilter: filter
			},
			callback: {
				beforeClick: beforeClick
				,onRightClick: OnRightClick
				
			}
		};

		function filter(treeId, parentNode, childNodes) {
			if (!childNodes) return null;
			for (var i=0, l=childNodes.length; i<l; i++) {
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
			return childNodes;
		}
		function beforeClick(treeId, treeNode) {
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			treeObj.reAsyncChildNodes(null, "refresh");
			return true;
			if (!treeNode.isParent) {
				return false;
			} else {
				return true;
			}
		}
		function refreshNode() {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			type = "refresh",
			silent = false,
			nodes = zTree.getSelectedNodes();
			for (var i=0, l=nodes.length; i<l; i++) {
				zTree.reAsyncChildNodes(nodes[i], type, silent);
				if (!silent) zTree.selectNode(nodes[i]);
			}
		}
		
		
		function OnRightClick(event, treeId, treeNode) {
			if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
				zTree.cancelSelectedNode();
				showRMenu("root", event.clientX, event.clientY);
			} else if (treeNode && !treeNode.noR) {
				zTree.selectNode(treeNode);
				showRMenu("node", event.clientX, event.clientY,treeNode.isParent);
			}
		}
		
		function showRMenu(type, x, y, isParent) {
			$("#rMenu ul").show();
			if (type=="root") {
				$("#m_del").hide();
			} else {
				$("#m_del").show();
			}
			
			
			
			if(isParent==true){
				$("#m_add").show();
				$("#m_del").hide();
			}else{
				$("#m_add").hide();
				$("#m_del").show();
			}
			
			rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

			$("body").bind("mousedown", onBodyMouseDown);
		}
		function hideRMenu() {
			if (rMenu) rMenu.css({"visibility": "hidden"});
			$("body").unbind("mousedown", onBodyMouseDown);
		}
		
		function onBodyMouseDown(event){
			if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
				rMenu.css({"visibility" : "hidden"});
			}
		}
		var addCount = 1;
		function addTreeNode() {
			hideRMenu();
			var copynode = zTree.getSelectedNodes()[0];
			var filepath = copynode.name;
			while(copynode.getParentNode()){
				copynode = copynode.getParentNode();
				filepath = copynode.name +"/"+ filepath;
			}
			
			$("#m_add a").attr({href: ("moni.do" + "?filepath=" + filepath)});
			refreshNode();
		}
		
		function removeTreeNode() {
			hideRMenu();
			var nodes = zTree.getSelectedNodes();
			if (nodes && nodes.length>0) {
				if (nodes[0].children && nodes[0].children.length > 0) {
					var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！";
					if (confirm(msg)==true){
						zTree.removeNode(nodes[0]);
					}
				} else {
					if(confirm("确认删除实体 -- " + nodes[0].name + " 吗？")){
						//通过ajax操作数据库和文件夹中的文件
						var delEntity_id = nodes[0].id;
						var filepath = nodes[0].name;
						var copynode = nodes[0];
						while(copynode.getParentNode()){
							copynode = copynode.getParentNode();
							filepath = copynode.name +"\\"+ filepath;
						}
						$.ajax({
							type:"post",
							url:"<c:url value='/delentitybyid.do'/>",
							data : "id=" + delEntity_id+"&filepath=" +filepath,
							dataType:"text",
							cache : false,
							async: false,
							success:function(result){
								if((result.trim())=="ok"){
									alert("实体已删除");
									window.parent.frames["folder"].location.reload(); 
									window.parent.frames["quest"].document.write(); 
								}else if((result.trim())=="no"){
									alert("实体不存在");
								}
							},
							error:function(){
								alert("查询失败");
							}
						});
						zTree.removeNode(nodes[0]);						
					}
				}
			}
		}
		
		
		var zTree, rMenu;
		
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting);
			zTree = $.fn.zTree.getZTreeObj("treeDemo");
			rMenu = $("#rMenu");
			$('ul.ztree').css({height:(parseInt($(window).height())-10)+"px"});
		});
		//-->
	</script>
	<style type="text/css">
	body{
	position:relative;
	}
	.shade{
		filter:alpha(opacity=50);
        -moz-opacity:0.5;
        -khtml-opacity: 0.5;
        opacity: 0.5;
		position:absolute;
		top:0px;
		left:0px;
		right:0px;
		bottom:0px;
		z-index:100;
		background:#000000;
	}
	
	
	
	</style>
</head>
<body>
<%
if(null == request.getSession().getAttribute("user")){
%>
	<div class="shade">
	&nbsp;
	</div>
<%
}
%>

		<ul id="treeDemo" class="ztree"></ul>
		
		<div id="rMenu">
			<ul>
				<li id="m_add" onclick="addTreeNode();" style="z-index:20000">
				<a href="moni.do" target="quest">增加节点</a></li>
				<li id="m_del" onclick="removeTreeNode();" style="z-index:20000">删除节点</li>
			</ul>
		</div>
</body>
</html>