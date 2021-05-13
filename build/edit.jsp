<%--
  Created by IntelliJ IDEA.
  User: Edge
  Date: 2021/4/27
  Time: 下午 4:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Bootstrap core CSS -->
    <link href="assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="assets/bootstrap/css/dashboard.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/BookListServlet">基于MongoDB的图书管理系统</a>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="${pageContext.request.contextPath}/BookListServlet">首页<span class="sr-only">(current)</span></a>
                </li>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-5 col-md-offset-2 main">
            <h2 class="sub-header" style="text-align: center">修改图书</h2>

            <form action="${pageContext.request.contextPath}/UpdateBookServlet" method="post">
                <div class="form-group" style="display:none">
                    <label>id</label>
                    <input type="text" name="id" class="form-control col-xs-2" value="${book.id}">
                </div>
                <div class="form-group">
                    <label>书名</label>
                    <input type="text" name="name" class="form-control col-xs-2" value="${book.name}">
                </div>
                <div class="form-group">
                    <label>价格</label>
                    <input type="number" name="price" class="form-control" value="${book.price}">
                </div>
                <div class="form-group">
                    <label>数量</label>
                    <input type="number" name="pnum" class="form-control" value="${book.pnum}">
                </div>
                <div class="form-group">
                    <label>类别</label>
                    <input type="text" name="category" class="form-control" value="${book.category}">
                </div>

                <div class="col-md-offset-4 col-lg-offset-4 col-xl-offset-4">
                    <button type="submit" class="btn btn-success">提交</button>
                    <a class="btn btn-info" href="${pageContext.request.contextPath}/BookListServlet">返回</a>
                </div>
            </form>


        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="assets/bootstrap/js/jquery-3.2.1.min.js"></script>
<script src="assets/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>
