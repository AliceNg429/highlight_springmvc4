<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content- Type" content="text/html; charset=UTF-8">
    <title>HttpMessageConverter Demo</title></head>
<body>
<div id="resp"></div>
<input type="button" onclick="req();" value="请求"/>
<script src="assets/js/jquery.js" type="text/javascript"></script>
<script> function req() {
    $.ajax({
        url: "convert",
        data: "1-wangyunfei", //1 注意这里的数据格式，后台处理按此格式处理，用“-”隔 开
        type:"POST",
        contentType:"application/x-wisely", //2 contentType设置的媒体类型是我们自定义的 application/x-wisely
        success: function (data) {
            $("#resp").html(data);
        }
    });
} </script>
</body>
</html>
<!--运行。访问http://localhost:8080/highlight_springmvc4/converter-->