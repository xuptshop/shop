<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>西邮淘</title>
	<link href="${pageContext.request.contextPath}/css/slider.css" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
	<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css"/>

</head>
<body>

<div class="container header">
	<div class="span5">
		<div class="logo">
			<img src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.gif" alt="西邮淘"  width="100%"/>
		</div>
	</div>
	<div class="span9">
		<div class="headerAd">
			<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="诚信保障" title="诚信保障"/>
		</div>
	</div>

	<%@ include file="menu.jsp" %>

</div>

<div class="container index">


	<div class="span24">
		<div id="hotProduct" class="hotProduct clearfix">
			<div class="title">
				<strong>热门商品</strong>
			</div>
			<ul class="tab"></ul>
			<ul class="tabContent" style="display: block;">
				<s:iterator var="p" value="hList">
					<li>
						<a href="${ pageContext.request.contextPath }/product_findByPid.action?pid=<s:property value="#p.pid"/>" target="_blank"><img src="${pageContext.request.contextPath}/<s:property value="#p.image"/>" data-original="http://storage.shopxx.net/demo-image/3.0/201301/0ff130db-0a1b-4b8d-a918-ed9016317009-thumbnail.jpg" style="display: block;"></a>
					</li>
				</s:iterator>
			</ul>
		</div>
	</div>
	<div class="span24">
		<div id="newProduct" class="newProduct clearfix">
			<div class="title">
				<strong>最新商品</strong>
				<a  target="_blank"></a>
			</div>
			<ul class="tab">
				<li>
				<a  target="_blank"></a>
			</li>
				<li>
					<a target="_blank"></a>
				</li>
			</ul>
			<ul class="tabContent" style="display: block;">
				<s:iterator var="p" value="nList">
					<li>
						<a href="${ pageContext.request.contextPath }/product_findByPid.action?pid=<s:property value="#p.pid"/>" target="_blank"><img src="${pageContext.request.contextPath}/<s:property value="#p.image"/>" data-original="http://storage.shopxx.net/demo-image/3.0/201301/4a51167a-89d5-4710-aca2-7c76edc355b8-thumbnail.jpg" style="display: block;"></a>									</li>
					</li>
				</s:iterator>
			</ul>
		</div>
	</div>
	<div class="span24">
		<div class="friendLink">
			<dl>
				<dt>新手指南</dt>
				<dd>
					<a  target="_blank">支付方式</a>
					|
				</dd>
				<dd>
				<a  target="_blank">售后服务</a>
				|
			</dd>
				<dd>
					<a  target="_blank">购物帮助</a>
					|
				</dd>
				<dd>
					<a  target="_blank">联系卖家</a>
					|
				</dd>
				<dd>
					<a  target="_blank">联系我们</a>
					|
				</dd>
				<dd>
					<a target="_blank">一卡通服务</a>
					|
				</dd>
				<dd>
					<a  target="_blank">积分查看</a>
					|
				</dd>

				<dd class="more">
					<a >更多</a>
				</dd>
			</dl>
		</div>
	</div>
</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
			<img src="${pageContext.request.contextPath}/image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势">
		</div>	</div>
	<div class="span24">
		<ul class="bottomNav">
			<li>
				<a>关于我们</a>
				|
			</li>
			<li>
				<a>联系我们</a>
				|
			</li>
			<li>
				<a>有意合作</a>
				|
			</li>
			<li>
				<a>法律声明</a>
				|
			</li>
			<li>
				<a>友情链接</a>
				|
			</li>
			<li>
				<a target="_blank">支付方式</a>
				|
			</li>
			<li>
			<a>服务声明</a>
			|
		</li>
			<li>
				<a>广告声明</a>

			</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2019-2029 西邮淘 版权所有</div>
	</div>
</div>
</body>
</html>