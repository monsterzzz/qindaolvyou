<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>景点列表</title>

    <!-- Bootstrap -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

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
            <div class="col-md-1"></div>
            <div class="col-md-3" style="margin-top: 30px;margin-bottom: 30px">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="${word}" id="search_word">
                    <span class="input-group-btn">
                <button class="btn btn-default" type="button" id="search_btn">Go!</button>
            </span>
                </div><!-- /input-group -->
            </div>
            <div class="col-md-3" style="margin-top: 30px;margin-bottom: 30px">
                <div class="input-group">
                    <span class="input-group-addon">出行时间</span>
                    <input id="time_ipt" type="text" class="form-control" aria-label="Amount (to the nearest dollar)">
                    <span class="input-group-addon">:00</span>
                </div><!-- /input-group -->
            </div>
            <div class="col-md-1" style="margin-top: 30px;margin-bottom: 30px">
                <button class="btn btn-default" type="button" id="time_query">查询</button>
            </div>
        </div>
        <% int[] idx = {0,5};
            request.setAttribute("idx",idx);
        %>
        <c:forEach items="${idx}" var="i">
            <div class="col-md-8 col-md-offset-2">
                <div class="col-md-1"></div>
                <c:forEach items="${page.sightSpots}" begin="${i}" end="${i+4}" var ="item">
                    <div class="col-sm-2 col-md-2">
                        <a href="/content/detail/${item.spot_id}">
                            <div class="thumbnail">
                                <c:choose>
                                    <c:when test="${not empty item.small_img}">
                                        <img src="${item.small_img.path}" title="${item.name}">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="/img/true.jpg" title="${item.name}">
                                    </c:otherwise>
                                </c:choose>
                                <div class="caption">
                                    <p class="text-center" style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">${item.name}</p>
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </c:forEach>
        <div class="col-md-8 col-md-offset-2">
            <nav aria-label="Page navigation">
                <ul class="pager">
                    <li><a href="/${page.head_path}/${page.first}">首页</a></li>
                    <c:if test="${page.has_pre}">
                        <li><a href="/${page.head_path}/${page.current_page - 1}">上一页</a></li>
                    </c:if>
                    <li><a href="/${page.head_path}/${page.current_page  - 1}">${page.current_page + 1}</a></li>
                    <c:if test="${page.has_next}">
                        <li><a href="/${page.head_path}/${page.current_page + 1}">下一页</a></li>
                    </c:if>
                    <li><a href="/${page.head_path}/${page.end}">尾页</a></li>
                </ul>
            </nav>
        </div>
        <c:if test="${not empty recommend}">
            <div class="col-md-8 col-md-offset-2">
                <div class="page-header">
                    <h4>猜你喜欢</h4>
                </div>
                <c:forEach items="${recommend}" var="item">
                    <div class="col-sm-2 col-md-2">
                        <a href="/content/detail/${item.spot_id}">
                            <div class="thumbnail">
                                <img src="${item.small_img}" title="${item.name}">
                                <div class="caption">
                                    <p class="text-center" style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;">${item.name}</p>
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </c:if>
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
    $("#login_btn").click(function () {
        $.ajax({
            type:"POST",
            url:"/login",
            data:$("#my_form").serializeObject(),
            success:function (data) {
                console.log(data)
            }
        })
    })

    $("#sign_btn").click(function () {
        $.ajax({
            type:"POST",
            url:"/sign_in",
            data:$("#sign").serializeObject(),
            success:function (data) {
                console.log(data)
            }
        })
    })
    
    $("#search_btn").click(function () {
        window.open("/content/search/place/" + $("#search_word").val() + "/0")
    })
    $("#time_query").click(function () {
        window.open("/content/search/time/" + $("#time_ipt").val() + "/0")
    })

</script>
</html>