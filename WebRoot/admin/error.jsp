<%--
  Created by IntelliJ IDEA.
  User: striner
  Date: 2019/4/19
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>错误页面</title>
</head>
<body style="background: #278296">
<div style="margin:auto auto;width:400px;height:300px;">
    <div>
        <br>
        <br>
        <h2>页面访问失败，请联系系统管理员。</h2>
        tel:18829526235 穆林
    </div>
    <div>
        <td style="padding-top:30px">
            <br />
            <br />

            <a href="${ pageContext.request.contextPath }/index.action">网站首页</a>
            <a href="${ pageContext.request.contextPath }/user_registPage.action">账号注册</a>
            <a href="${ pageContext.request.contextPath }/adminUser_index.action">后台登录</a>
        </td>
    </div>
</div>


</body>
</html>
