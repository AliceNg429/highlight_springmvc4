<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SSE Demo</title>
</head>
<body>
<div id="msgFrompPush"></div>
<script type="text/javascript" src="
<c:url value="assets/js/jquery.js" />"></script>
<script type="text/javascript">
    if (!!window.EventSource) { //1 EventSource对象只有新式的浏览器才有（Chrome、
        //Firefox）等，EventSource是SSE的客户端
        var source = new EventSource('push');
        var s='';
        //添加SSE客户端监听，在此获得服务器端推送的消息
        source.addEventListener('message', function(e) {//2
            s+=e.data+"<br/>";
            $("#msgFrompPush").html(s);
        });
        source.addEventListener('open', function(e) {
            console.log("连接打开.");
        }, false);
        source.addEventListener('error', function(e) {
            if (e.readyState == EventSource.CLOSED) {
                console.log("连接关闭");
            } else {
                console.log(e.readyState);
            }
        }, false);
    } else {
        console.log("你的浏览器不支持SSE");
    }
</script>
</body>
</html>