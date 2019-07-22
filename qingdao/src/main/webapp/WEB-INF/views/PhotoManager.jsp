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
    <title>更新景点</title>

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
<div class="container-fluid">
    <div class="row" style="margin-top: 30px">
        <div class="col-md-8 col-md-offset-2" id="img_show">
            <c:forEach items="${photos}" var="p">
                <div class="col-md-3">
                    <div  class="thumbnail">
                        <img src="${p.path}" alt="...">
                        <div class="caption">
                            <p class="text-center"><span onclick="deletePhoto(${p.id})" class="glyphicon glyphicon-remove" aria-hidden="true"></span></p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="col-md-8 col-md-offset-2">
            <div class="col-md-12">
                <form id="uploadForm" enctype="multipart/form-data">
                    文件:<input id="file" type="file" name="file" onchange="uploadImg()"/>
                </form>
                <form id="addPhoto">
                    <div class="col-md-2">
                        <div class="thumbnail" style="display: none" id="img_div">
                            <img id="upImg">
                        </div>
                    </div>
                    <div class="col-md-12">
                        <input type="hidden" name="path" id="upImgIpt">
                    </div>
                </form>
            </div>
        </div>
        <div class="col-md-8 col-md-offset-2">
            <button class="btn btn-default center-block" onclick="addImg()">添加图片</button>
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

    function deletePhoto(pid) {
        $.ajax({
            type: "GET",
            url : "/admin/photo/manager/" + pid + "/remove",
            success:function (data) {
                alert(data.msg)
                window.location.reload()
            }

        })
    }

    function uploadImg() {
        var data = new FormData($("#uploadForm")[0])
        $.ajax({
            type: "POST",
            url: "/admin/photo/add",
            processData: false,
            contentType: false,
            data:data ,
            success: function (data) {
                $("#upImg").attr("src",data.msg)
                $("#upImgIpt").val(data.msg)
                $("#img_div").show()
            },

        })
    }
    console.log(window.location.host)
    function addImg() {
        var sid = window.location.href.split("/")
        var sid = sid[sid.length - 1]
        var url = "http://" + window.location.host + "/admin/photo/manager/" + sid + "/add"
        console.log(url)
        $.ajax({
            type: "POST",
            url: url,
            data: $("#addPhoto").serialize() ,
            success: function (data) {
                alert(data.msg)
                window.location.reload()
            },

        })
    }

</script>
</html>