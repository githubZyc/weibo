<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="easyui.jsp" %>
<script type="text/javascript" src="js/echart.js"></script>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>微博热搜</title>
</head>
<body>
<table id="dg" class="easyui-datagrid" title="微博热搜 " data-options="
                rownumbers:true,
                singleSelect:true,
                autoRowHeight:false
                ">
</table>
</body>
<script type="text/javascript">
    $('#dg').datagrid({
        url: '/wb-hot-search',   //设置访问后台路径和传递参数，如果没有参数可以删除
        dataType: 'json',
        width: "100%", //宽度
        height:"800px",
        striped: true, //把行条纹化（奇偶行背景色不同）
        idField: 'quesID', //标识字段
        loadMsg: '正在加载用户的信息.......', //从远程站点加载数据是，显示的提示消息
        singleSelect: false, //只允许选中一行
        columns: [[ //每页具体内容
            {field: 'content', title: '内容', width: "33%", align: 'center', editor: 'text'},
            {field: 'href', title: '链接', width: "33%", align: 'center', editor: 'text'},
            {field: 'goHref', title: '跳转', width: "33%", align: 'center', editor: 'text',formatter: function(value,row,index){
                return "<a href='"+row.href+"' target='_blank'>"+row.content+"</a>"
                }}
        ]],

        onLoadSuccess: function (data) {
            //表格加载成功后执行的代码，如果不需要可以删除
        }
    })
</script>
</html>