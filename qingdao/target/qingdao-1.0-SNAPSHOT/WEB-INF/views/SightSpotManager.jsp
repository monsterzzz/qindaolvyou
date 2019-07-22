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
    <div class="row" style="margin-top: 10px">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-info">
                <div class="panel-heading">基本信息
                    <a class="pull-right" style="margin-left: 10px" onclick="deleteSightSpot(${sight_spot.spot_id})">删除景点?</a>
                </div>
                <div class="panel-body">
                    <div class="col-md-5">
                        <div class="row">
                            <div class="col-md-5 col-md-offset-1">
                                缩略图:
                                <div href="#" class="thumbnail">
                                    <c:choose>
                                        <c:when test="${empty small_img.path}">
                                            <img id="small_img" src="/img/true.jpg" alt="...">
                                        </c:when>
                                        <c:otherwise>
                                            <img id="small_img" src="${small_img.path}" alt="...">
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <form id="uploadForm" enctype="multipart/form-data">
                                    文件:<input id="file" type="file" name="file" onchange="uploadImg()"/>
                                </form>
                            </div>
                        </div>
                    </div>
                    <form id="detail_msg">
                        <div class="col-md-5">
                            <div class="form-group">
                                <label>id</label>
                                <input class="form-control" value="${sight_spot.id}" readonly>
                            </div>
                            <div class="form-group">
                                <label>spot id</label>
                                <input class="form-control" name="spot_id" value="${sight_spot.spot_id}" readonly>
                            </div>
                            <c:choose>
                                <c:when test="${empty sight_spot.name}">
                                    <div class="form-group">
                                        <label>名称</label>
                                        <input class="form-control" name="name" value="${sight_spot.name}">
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="form-group">
                                        <label>名称</label>
                                        <input class="form-control" name="name" value="${sight_spot.name}" readonly>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                            <div class="form-group">
                                <label>门票</label>
                                <input class="form-control" name="money" value="${sight_spot.money}">
                            </div>
                            <div class="form-group">
                                <label>电话</label>
                                <input class="form-control" name="tel" value="${sight_spot.tel}">
                            </div>
                            <div class="form-group">
                                <label>交通方式</label>
                                <input class="form-control" name="tsf" value="${sight_spot.tsf}">
                            </div>
                            <div class="form-group">
                                <label>花费时间</label>
                                <input class="form-control" name="pay_time" value="${sight_spot.pay_time}">
                            </div>
                            <div class="form-group">
                                <label>开放时间</label>
                                <input class="form-control" name="open_time" value="${sight_spot.open_time}">
                            </div>
                            <div class="form-group">
                                <label>关闭时间</label>
                                <input class="form-control" name="close_time" value="${sight_spot.close_time}">
                            </div>
                            <div class="form-group">
                                <label for="big">大型景点</label>
                                <select id="big" name="big">
                                    <option value="0" ${sight_spot.big == 0 ?'selected':''}>否</option>
                                    <option value="1" ${sight_spot.big == 1 ?'selected':''}>是</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="big">季节</label>
                                <select id="season" name="season">
                                    <option value="1" ${sight_spot.season== 1 ?'selected':''}>春</option>
                                    <option value="2" ${sight_spot.season== 2 ?'selected':''}>夏</option>
                                    <option value="3" ${sight_spot.season== 3 ?'selected':''}>秋</option>
                                    <option value="4" ${sight_spot.season== 4 ?'selected':''}>冬</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group col-md-12">
                            <label>描述</label>
                            <textarea class="form-control" name="descri" style="height: 100px">
                                ${sight_spot.descri}
                            </textarea>
                        </div>
                    </form>
                    <div class="col-md-12">
                        <c:choose>
                            <c:when test="${empty add}">
                                <button id="update_btn" type="button" class="btn btn-info center-block">更新信息</button>
                            </c:when>
                            <c:otherwise>
                                <button id="add_btn" type="button" class="btn btn-info center-block">添加景点</button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="/js/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="/js/bootstrap.min.js"></script>
<script src="/js/ajaxfileupload.js"></script>
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
    $("#update_btn").click(function () {
        var msg = $("#detail_msg").serializeObject();
        msg["small_img"] = {
            path : $("#small_img").attr("src")
        }

        $.ajax({
            type:"POST",
            url:"/admin/sight_spot/rest/update",
            datatype:"json",
            contentType: "application/json;charset=utf-8",
            data:JSON.stringify(msg),
            success:function (data) {
                if(data.code == 40000){
                    alert(data.msg)
                }else{
                    alert("更新失败")
                }
            }
        })
    })

    $("#add_btn").click(function () {
        var msg = $("#detail_msg").serializeObject();
        msg["small_img"] = {
            path : $("#small_img").attr("src")
        }

        $.ajax({
            type:"POST",
            url:"/admin/sight_spot/rest/add",
            datatype:"json",
            contentType: "application/json;charset=utf-8",
            data:JSON.stringify(msg),
            success:function (data) {
                if(data.code == 10001){
                    alert(data.msg)
                }else{
                    alert("更新失败")
                }
            }
        })
    })

    function deleteSightSpot(spot_id) {
        flag = confirm("你确定要删除这个景点吗?")
        if(flag){
            $.ajax({
                type : "get",
                url : "/admin/sight_spot/rest/delete?sid=" + spot_id,
                success : function (data) {
                    if(data.code == 10002){
                        alert(data.msg)
                        window.location.href = "http://" + window.location.host + "/content/0"
                    }else{
                        alert("删除失败")
                    }
                }
            })
        }
    }

    function uploadImg() {
        var data = new FormData($("#uploadForm")[0])
        data.append("sid",${sight_spot.spot_id})
        $.ajax({
            type: "POST",
            url: "/admin/photo/add",
            processData: false,
            contentType: false,
            data:data ,
            success: function (data) {
                $("#small_img").attr("src",data.msg)
                $("#small_ipt").val(data.msg)
            },

        })

    }



</script>
</html>