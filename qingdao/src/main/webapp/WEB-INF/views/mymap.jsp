<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <style type="text/css">
        body, html {width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
        #allmap{width:100%;height:500px;}
        p{margin-left:5px; font-size:14px;}
    </style>

    <script type="text/javascript" src="http://api.map.baidu.com/getscript?v=3.0&ak=M6Z71zOsfQKg7YIxrGW3kjP23uBDzqE0"></script>
    <script>
        if(navigator.geolocation){
            navigator.geolocation.getCurrentPosition(showPosition,showError);
        }else{
            alert('该浏览器不支持地理位置!');
        }

        function showPosition(position){
            var lat=position.coords.latitude;
            var lng=position.coords.longitude;
            alert('纬度'+lat+","+"经度"+lng);
        }
        function showError(error){
            switch(error.code)
            {
                case error.PERMISSION_DENIED:
                    alert("用户拒绝对获取地理位置的请求。")
                    break;
                case error.POSITION_UNAVAILABLE:
                    alert("位置信息是不可用的。")
                    break;
                case error.TIMEOUT:
                    alert("请求用户地理位置超时。")
                    break;
                case error.UNKNOWN_ERROR:
                    alert("未知错误。")
                    break;
            }
        }
    </script>
    <title>根据关键字本地搜索</title>
</head>
<body>
<div id="allmap"></div>
<p>返回北京市“景点”关键字的检索结果，并展示在地图上</p>
</body>
</html>
<script type="text/javascript">

    // 百度地图API功能
    var map = new BMap.Map("allmap");
    map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);
    var local = new BMap.LocalSearch(map, {
        renderOptions:{map: map}
    });
    local.search("长城");
    p = navigator.geolocation
    p.getCurrentPosition(function (position) {
        console.log(">>>>")
        console.log(position)
    })
</script>
