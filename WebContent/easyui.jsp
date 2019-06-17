<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 引入JQuery -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("path",path);
%>
    <script type="text/javascript" src="<%=path %>/js/jquery-1.11.2.min.js"></script>
    <!-- 引入EasyUI -->
    <script type="text/javascript" src="<%=path %>/js/jquery.easyui.min.js"></script>
   <!-- 引入EasyUI的中文国际化js，让EasyUI支持中文 -->
   <script type="text/javascript" src="<%=path %>/js/easyui-lang-zh_CN.js"></script>
   <!-- 引入EasyUI的样式文件-->
   <link rel="stylesheet" href="<%=path %>/css/themes/default/easyui.css" type="text/css"/>
   <!-- 引入EasyUI的图标样式文件-->
   <link rel="stylesheet" href="<%=path %>/css/themes/icon.css" type="text/css"/>
    <link rel="stylesheet" href="<%=path %>/css/common.css" type="text/css"/>