<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="easyui.jsp" %>
<script type="text/javascript" src="js/echart.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
 <style>
   #userForm{
    position: absolute;
    left: 40%;
    top: 50%;
   }
    #qqq{
    position: absolute;
    left: 30%;
    top: 20%;
   }
   </style>
<!-- <form  action="" id="aaa" style="display:none" method="post">
<table>
<tr>
<td>请输入关键词，用英文逗号隔开:</td>
<td><input name="keyword" id="keyword" class="easyui-textbox"  style="width:100%"></td>
<td>请输入一条微博的ID:</td>
<td><input name="id" id="id" class="easyui-textbox"  style="width:100%"></td>
<td><a id="btn" onclick="ccc();" href="#" class="easyui-linkbutton" >添加</a></td>
</tr>
</table>
</form> -->



<form  action="" id="aaa"  method="post">
关键字&nbsp;&nbsp;&nbsp;:<input name="keyword" id="keyword" class="easyui-textbox"  value="" style="width:300px" >
	
		    <button class="easyui-linkbutton"   value="喜欢 "  onclick="search('喜欢');return false;">喜欢</button>
			<button  class="easyui-linkbutton"  value="支持 "  onclick="search('支持');return false;">支持</button>
			<button  class="easyui-linkbutton"  value="好 "    onclick="search('好');return false;">好</button>
			<button  class="easyui-linkbutton"  value="热搜 "  onclick="search('热搜');return false;">热搜</button>
			<button  class="easyui-linkbutton"  value="反对 "  onclick="search('反对');return false;">反对</button>
			<button  class="easyui-linkbutton"  value="郁闷 "  onclick="search('郁闷');return false;">郁闷</button>
			<button  class="easyui-linkbutton"  value="担忧 "  onclick="search('担忧');return false;">担忧</button>
			<button  class="easyui-linkbutton"  title="心疼 "  onclick="search('心疼');return false;">心疼</button>
			<button  class="easyui-linkbutton"  title="杠精 "  onclick="search('杠精');return false;">杠精</button>
			<button  class="easyui-linkbutton"  title="诈骗 "  onclick="search('诈骗');return false;">诈骗</button>
			</br>
			 </br>
			 历史搜索:<textarea  name="searchhistory" id="searchhistory" class="easyui-textbox"  value="" style="width:40%;height:30%" type="text" cols="40" rows="5"></textarea>
			 <button  class="easyui-linkbutton"  title="获取历史搜索 "  onclick="searchHistory();return false;">获取历史搜索</button>
			 </br></br>
微博Id&nbsp;&nbsp;&nbsp;:<input name="id" id="id" class="easyui-textbox" value='4374710789551900' style="width:300px" ></br></br>
操作:<a id="btn" onclick="ccc();" href="#" class="easyui-linkbutton" style="width:200px">关键字个数查询</a>  
<a id="btn" onclick="repostcount();" href="#" class="easyui-linkbutton" style="width:200px">该微博转发数</a>
<a id="btn" onclick="weiboSender();" href="#" class="easyui-linkbutton" style="width:200px">微博博主信息</a>
<a id="btn" onclick="weiboHot();" href="#" class="easyui-linkbutton" style="width:200px">微博热搜</a>
</form>



<div id="main" style="width: 600px;height:400px;"></div>
<div id="counts" style="float:left; width: 600px;height:400px;"></div>
<div id="sender" style="float:left; width: 600px;height:400px;"></div>

<script>
function setData(data,num){
	var historyTmp=window.sessionStorage.getItem("History");
	var dataNum=data+":条数="+num;
	if(historyTmp==null){
		window.sessionStorage.setItem("History",dataNum);
	}else{
			window.sessionStorage.setItem("History",dataNum+"  "+historyTmp);
	}
}
 function searchHistory(){
	debugger;
	 var str=window.sessionStorage.getItem("History");
		$('#searchhistory').textbox('setValue',str);
	return false;
 }
function search(data){
	$('#keyword').textbox('setValue',data);
	ccc();
}

function ccc(){
	var keyword=$('#keyword').textbox('getValue');
	 $('#aaa').form('submit',      
	            {  
	                url: '${path}/run?methodName=runKeyword',
	                type: 'post',  
	                dataType: 'json',  
	                success: function (data) {
	                	debugger;
	                	console.log(data)
	                	var jsonData=JSON.parse(data);
	                	setData(keyword,jsonData.count);
	                	var json = buildJson(data);
	                	initEchat(json);
	                }
	            });
	
	
}

function repostcount(){
	debugger;
	 $('#aaa').form('submit',      
	            {  
	                url: '${path}/run?methodName=repostcount',
	                type: 'post',  
	                dataType: 'json',  
	                success: function (data) {
	                	$("#counts").html("");
	                	var json = buildJson(data);
	                	console.log(json[0])
	                	$("#counts").append("该微博点赞数："+json[0].attitudes+"</br>")
	                	$("#counts").append("该微博转发数："+json[0].reposts+"</br>")
	                	
	                }
	            });

}
function weiboSender(){
	debugger;
	 $('#aaa').form('submit',      
	            {  
	                url: '${path}/run?methodName=weiboSender',
	                type: 'post',  
	                dataType:'json',
	                success: function (data) {
	                	$("#sender").html("");
	                	var json = buildJson(data);
	                	console.log(json)
	                	$("#sender").append("博主微博名："+json.name+"</br>")
	                	$("#sender").append("粉丝数："+json.followers_count+"</br>")
	                	
	                }
	            });

}


function buildJson(param){
	var json = eval('(' + param + ')');
	return json;
}

function initEchat(json){
    debugger;
    	var myChart = echarts.init(document.getElementById('main'));
        var legend=json.legend;
		var text=json.text;
        var xAxis=json.xAxis.split(",");
        var count=json.count.split(",");
       
        var option = {
            title: {
                text: text
            },
            tooltip: {},
            legend: {
                data:[legend]
            },
            xAxis: {
                data: xAxis
            },
            yAxis: {},
            series: [{
                name: legend,
                type: 'bar',
                data: count
            }]
        };

        
        myChart.setOption(option);
    }


function weiboHot(){
    window.open("http://localhost:8080/wb-hot");
}
    
</script>
</body>
</html>