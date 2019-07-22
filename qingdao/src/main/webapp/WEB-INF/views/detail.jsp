<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
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
    <title>${sightspot.name}</title>

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
            <p class="navbar-text navbar-right" style="margin-right: 30px"><a href="/content/bigSightSpot" class="navbar-link">大型景点</a></p>
            <p class="navbar-text navbar-right" style="margin-right: 30px"><a href="/content/SeasonSightSpot/1" class="navbar-link">时令景点</a></p>
            <p class="navbar-text navbar-right" style="margin-right: 30px"><a href="/admin/sight_spot/add" class="navbar-link">新增景点</a></p>
        </c:if>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="page-header">
                <h1>${sightspot.name}
                        <c:if test="${sessionScope.userSession.admin == true}">
                            <a class="btn btn-info" href="/admin/sight_spot/manager/${sightspot.spot_id}">管理</a>
                        </c:if>
                </h1>
            </div>
            <div class="panel panel-info">
                <c:choose>
                    <c:when test="${sessionScope.userSession.admin}">
                        <div class="panel-heading"><a href="/admin/photo/manager/${sightspot.spot_id}">相册</a></div>
                    </c:when>
                    <c:otherwise>
                        <div class="panel-heading">相册</div>
                    </c:otherwise>
                </c:choose>
                <div class="panel-body">
                    <div class="row">
                        <c:choose>
                            <c:when test="${fn:length(sightspot.photos) == 0}">
                                <div class="col-xs-6 col-md-3">
                                    <p>暂无图片</p>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${sightspot.photos}" begin="0" end="3" var="p">
                                    <div class="col-xs-6 col-md-3">
                                        <div  class="thumbnail">
                                            <img src="${p.path}" alt="...">
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="page-header">
                    <h4>简介</h4>
                </div>
                <c:choose>
                    <c:when test="${empty sightspot.descri}">
                        <p style="font-size: 16px">暂无</p>
                    </c:when>
                    <c:otherwise>
                        <p style="font-size: 16px">${sightspot.descri}</p>
                    </c:otherwise>
                </c:choose>
                <hr>
                <div class="col-md-6">
                    <p style="font-size: 20px"><span class="label label-info">门票</span></p>
                    <p style="font-size: 20px">${sightspot.money}</p>
                </div>
                <div class="col-md-6">
                    <p style="font-size: 20px"><span class="label label-info">电话</span></p>
                    <c:choose>
                        <c:when test="${empty sightspot.tel}">
                            <p style="font-size: 20px">暂无电话</p>
                        </c:when>
                        <c:otherwise>
                            <p style="font-size: 20px">${sightspot.tel}</p>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="col-md-12">
                    <p style="font-size: 20px"><span class="label label-info">开放时间</span></p>
                    <p style="font-size: 20px">${sightspot.time}</p>
                </div>
                <div class="col-md-12">
                    <p style="font-size: 20px"><span class="label label-info">交通方式</span></p>
                    <c:choose>
                        <c:when test="${empty sightspot.tsf}">
                            <p style="font-size: 20px">暂无交通方式</p>
                        </c:when>
                        <c:otherwise>
                            <p style="font-size: 20px">${sightspot.tsf}</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="row">
                <c:choose>
                    <c:when test="${islike.booleanValue() == false}">
                        <button class="btn btn-lg btn-default center-block" id="like" onclick="like()">喜欢</button>
                        <button class="btn btn-lg btn-default center-block" id="cancellike" onclick="cancellike()" style="display: none">取消喜欢</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-lg btn-default center-block" id="like" onclick="like()" style="display: none">喜欢</button>
                        <button class="btn btn-lg btn-default center-block" id="cancellike" onclick="cancellike()" >取消喜欢</button>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="row" style="margin-top: 20px">
                <div class="page-header">
                    <h4>去过这里的人也喜欢去</h4>
                </div>
                <c:forEach items="${recommend}" var="s">
                    <a href="/content/detail/${s.spot_id}">
                        <div class="col-md-2">
                            <div class="thumbnail">
                                <img src="${s.small_img}" class="img-rounded">
                                <div class="caption">
                                    <p class="text-center">${s.name}</p>
                                </div>
                            </div>
                        </div>
                    </a>
                </c:forEach>
            </div>
            <div class="row">
                <div class="page-header">
                    <h4>精选评论</h4>
                </div>
                <c:forEach items="${sightspot.comments}" var="comments">
                    <div class="panel panel-primary">
                        <div class="panel-heading">用户: ${comments.name}</div>
                        <div class="panel-body">
                            ${comments.content}
                        </div>
                    </div>
                </c:forEach>
            </div>
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

    function like() {
        $.ajax({
            type : "GET",
            url : "/action/add/${sightspot.spot_id}",
            success:function (data) {
                alert(data.msg)
                if(data.code == 70000){
                    console.log("!!!!!!!!")
                    $("#like").hide();
                    $("#cancellike").show();
                    console.log("!!!!!!!!")

                }
            }
        })
    }

    function cancellike() {
        $.ajax({
            type : "GET",
            url : "/action/remove/${sightspot.spot_id}",
            success:function (data) {
                alert(data.msg)
                if(data.code == 71000){
                    $("#cancellike").hide();
                    $("#like").show();
                }
            }
        })
    }

</script>
</html>