<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理员</title>
   
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" type="text/css">
</head>
<body>
<div class="container-fluid">
    <div class="row form-wrap">
        <div class="form-bg col-xs-12 col-sm-offset-2 col-sm-8 col-md-offset-4 col-md-5"></div>
        <div class="form-title col-sm-offset-2 col-sm-8 col-md-offset-4 col-md-5">
            管理员登录
        </div>
        <form class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/manager/loginprocess.do">
            <div class="form-group">
                    <label for="email"
                           class="col-xs-2 col-sm-offset-2 col-md-offset-4 col-sm-2 col-md-1 control-label">邮箱</label>

                    <div class="col-xs-9 col-sm-6 col-md-4">
                        <input type="email" class="form-control" id="email" placeholder="请输入您的邮箱地址" name="mname">
                    </div>
                </div>
                <div class="form-group">
                    <label for="password"
                           class="col-xs-2 col-sm-offset-2 col-md-offset-4 col-sm-2 col-md-1 control-label">密码</label>

                    <div class="col-xs-9 col-sm-6 col-md-4">
                        <input type="password" class="form-control" id="password" placeholder="请输入密码" name="mpassword">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-offset-2 col-xs-9 col-sm-offset-4 col-md-offset-5 col-sm-6 col-md-4">
                        <button type="submit" class="btn btn-default submit-button">登录</button>
                    </div>
                </div>
        </form>
    </div>
</div>

</body>
</html>