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
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

</head>
<body background="/img/bg.png" style=" background-repeat:no-repeat ;background-size:100% 100%;background-attachment: fixed;">

<div class="container-fluid">

    <div class="col-md-8 col-md-offset-2">
        <div class="jumbotron" style="background-color:rgba(255,255,255,0)">
            <div class="col-md-8 col-md-offset-3">
                <div class="center-block">
                    <h1 style="margin-left: 80px">您好,请登录!</h1>
                </div>
            </div>
        </div>
        <div class="col-md-12">
            <div class="col-md-4 col-md-offset-4" style="background-color: darkgrey;padding: 30px;margin-top: 5%;border-radius:25px;">
                <form id="login_form">
                    <div class="form-group">
                        <label for="name">用户名</label>
                        <input type="text" class="form-control" name="account" id="name" placeholder="用户名">
                    </div>
                    <div class="form-group">
                        <label for="password">密码</label>
                        <input type="password" class="form-control" name="password" id="password" placeholder="Password">
                    </div>
                </form>
                <div>
                    <button type="button" id="login_btn" class="btn btn-default">登录</button>
                    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal">
                        注册
                    </button>

                    <!-- Modal -->
                    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="myModalLabel">注册</h4>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col-md-8 col-md-offset-2">
                                            <form id="sign_form">
                                                <div class="form-group">
                                                    <label>帐号</label>
                                                    <input type="text" class="form-control" name="account" placeholder="帐号">
                                                </div>
                                                <div class="form-group">
                                                    <label>密码</label>
                                                    <input type="password" class="form-control" name="password" placeholder="Password">
                                                </div>
                                                <div class="form-group">
                                                    <label>姓名</label>
                                                    <input type="text" class="form-control" name="name" placeholder="姓名">
                                                </div>
                                                <div class="form-group">
                                                    <label>性别</label>
                                                    <br>
                                                    <input name="sex" type="radio" checked="checked" value="0"/>男
                                                    <input name="sex" type="radio"  value="1"/>女
                                                </div>
                                                <div class="form-group">
                                                    <label>年龄</label>
                                                    <input type="text" class="form-control" name="age" placeholder="年龄">
                                                </div>
                                            </form>
                                        </div>

                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-primary" id="sign_btn">注册</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="js/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="js/bootstrap.min.js"></script>
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
            data:$("#login_form").serializeObject(),
            success:function (data) {
                if(data.code == 10000){
                    console.log(data)
                    window.location.href = "http://" + window.location.host + "/content/0"
                }else {
                    alert(data.msg)
                }
            }
        })
    })

    $("#sign_btn").click(function () {
        $.ajax({
            type:"POST",
            url:"/sign_in",
            data:$("#sign_form").serializeObject(),
            success:function (data) {
                console.log(data)
            }
        })
    })

</script>
</html>