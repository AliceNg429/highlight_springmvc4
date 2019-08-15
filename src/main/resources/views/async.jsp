<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content￾Type" content="text/html; charset=UTF-8">
    <title>servlet async support</title>
</head>
<body>
<script type="text/javascript" src="assets/js/jquery.js">
</script>
<script type="text/javascript">
    deferred();//1
    function deferred() {
        $.get('defer', function (data) {
            console.log(data); //2
            deferred(); //3 一次请求完成后再向后台发送请求
        });
    }
</script>
</body>
</html>