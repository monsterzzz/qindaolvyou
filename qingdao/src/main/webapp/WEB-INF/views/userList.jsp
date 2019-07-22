<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: monster
  Date: 2019/3/29
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户列表</title>
    <!-- Bootstrap -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .table th, .table td {
            text-align: center;
            vertical-align: middle!important;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <p class="navbar-text" style="margin-right: 30px"><a href="/content/0">景点列表</a></p>
        <p class="navbar-text" style="margin-right: 30px">
            欢迎您,
            <c:choose>
                <c:when test="${sessionScope.userSession.admin}">
                    <span> 管理员 </span>
                </c:when>
                <c:otherwise>
                    <span> 游客 </span>
                </c:otherwise>
            </c:choose>
            , ${sessionScope.get("userSession").getName()}
        </p>

        <p class="navbar-text navbar-right" style="margin-right: 30px"><a href="/log_out" class="navbar-link">退出登录</a></p>
        <p class="navbar-text navbar-right" style="margin-right: 30px"><a href="/user/detail" class="navbar-link">个人信息</a></p>
        <c:if test="${sessionScope.userSession.admin}">
            <p class="navbar-text navbar-right" style="margin-right: 30px"><a href="/admin/user/0" class="navbar-link">用户管理</a></p>
            <p class="navbar-text navbar-right" style="margin-right: 30px"><a href="/admin/bigSightSpot" class="navbar-link">大型景点</a></p>
            <p class="navbar-text navbar-right" style="margin-right: 30px"><a href="/admin/SeasonSightSpot/1" class="navbar-link">时令景点</a></p>
            <p class="navbar-text navbar-right" style="margin-right: 30px"><a href="/admin/sight_spot/add" class="navbar-link">新增景点</a></p>
        </c:if>
    </div>
</nav>
<div class="container-fluid" style="margin-top: 30px">
    <div class="row">

        <div class="col-md-8 col-md-offset-2">

            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading">
                    <div style="margin: 0px;padding: 0px;display: inline">用户管理</div>
                    <div style="margin: 0px;padding: 0px;display: inline" class="pull-right">
                        <form>
                            <input type="text" id="search_word">
                            <button type="button" id="search_btn">查找</button>
                        </form>
                    </div>
                </div>
                <!-- Table -->
                <table class="table">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>帐号</th>
                            <th>密码</th>
                            <th>姓名</th>
                            <th>性别</th>
                            <th>年龄</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.users}" var="item" varStatus="status">
                        <tr>
                            <c:if test="${not empty item}">
                                <th scope="row">${status.index + 1}</th>
                            </c:if>
                            <td>${item.account}</td>
                            <td>${item.password}</td>
                            <td>${item.name}</td>
                            <c:choose>
                                <c:when test="${item.sex == 1}">
                                    <td>女</td>
                                </c:when>
                                <c:when test="${item.sex == 0}">
                                    <td>男</td>
                                </c:when>
                            </c:choose>
                            <td>${item.age}</td>
                            <td>
                                <c:if test="${not empty item}">
                                    <button class="btn btn-default btn-sm" onclick="deleteUser('${item.id}')"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <nav aria-label="Page navigation">
                <ul class="pager">
                    <li><a href="/admin/user/${page.first}">首页</a></li>
                    <c:if test="${page.has_pre}">
                        <li><a href="/admin/user/${page.current_page - 1}">上一页</a></li>
                    </c:if>
                    <c:if test="${page.has_next}">
                        <li><a href="/admin/user/${page.current_page + 1}">下一页</a></li>
                    </c:if>
                    <li><a href="/admin/user/${page.end}">尾页</a></li>
                </ul>
            </nav>
        </div>
    </div>

</div>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="/js/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="/js/bootstrap.min.js"></script>
</body>
<script>
    $.fn.serializeObject = function()
    {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function() {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };

    function deleteUser(uid){
        $.ajax({
            type : "GET",
            url : "/admin/user/rest/delete?uid=" + uid,
            success:function (data) {
                alert(data.msg)
                window.location.reload()
            }

        })
    }
    
    $("#search_btn").click(function () {
        window.open("/admin/user/query?account=" + $("#search_word").val())
    })

</script>
</html>