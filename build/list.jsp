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
    <script type="text/javascript">
        function addBook() {
            window.location.href = "${pageContext.request.contextPath}/add.jsp";
        }

        function deleteBook(id) {
            window.location.href = "${pageContext.request.contextPath}/DeleteBookServlet?id=" + id;
        }
    </script>
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
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h2 class="sub-header" style="text-align: center">图书信息</h2>

            <div class="col-md-offset-4 col-lg-offset-4 col-xl-offset-4">
                <form action="${pageContext.request.contextPath}/SearchBookServlet" method="post" class="form-inline">
                    <div class="form-group">
                        <input type="text" name="name" class="form-control" placeholder="请输入书名">
                    </div>
                    <button type="submit" class="btn btn-success">查询</button>
                    <button type="button" class="btn btn-primary" onclick="addBook()">添加</button>
                </form>
            </div>

            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <td style="display: none">id</td>
                        <td>序号</td>
                        <td>书名</td>
                        <td>价格</td>
                        <td>数量</td>
                        <td>类别</td>
                        <td>操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pageResult.list}" var="book" varStatus="vs">
                        <tr>
                            <td style="display: none">${book.id}</td>
                            <td>${vs.count}</td>
                            <td>${book.name}</td>
                            <td>${book.price}</td>
                            <td>${book.pnum}</td>
                            <td>${book.category}</td>
                            <td>
                                <a class="btn btn-primary"
                                   href="${pageContext.request.contextPath}/FindBookByIdServlet?id=${book.id}">修改</a>
                                <a class="btn btn-danger"
                                   href="${pageContext.request.contextPath}/DeleteBookServlet?id=${book.id}">删除
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <nav aria-label="Page navigation" class="col-md-offset-4 col-lg-offset-4 col-xl-offset-4">
                <ul class="pagination">

                    <li><a href="${pageContext.request.contextPath}/BookListServlet?page=1">首页</a></li>
                    <c:if test="${pageResult.currentPage-1!=0}">
                        <li>
                            <a href="${pageContext.request.contextPath}/BookListServlet?page=${pageResult.currentPage-1}"
                               aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>

                    <c:forEach begin="1" end="${pageResult.totalPage}" var="p">
                        <c:set var="active" value="${p==pageResult.currentPage?'active':''}"/>
                        <li class="${active}"><a href="${pageContext.request.contextPath}/BookListServlet?page=${p}">${p}</a></li>
                    </c:forEach>

                    <c:if test="${pageResult.currentPage+1<=pageResult.totalPage}">
                    <li>
                        <a href="${pageContext.request.contextPath}/BookListServlet?page=${pageResult.currentPage+1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                    </c:if>
                    <li><a href="${pageContext.request.contextPath}/BookListServlet?page=${pageResult.totalPage}">尾页</a></li>
                </ul>
            </nav>

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
