<%@page import="java.util.Iterator"%>
<%@page import="com.store.DAO.DAO,java.net.*"%>

<%@page import="java.util.ArrayList,com.store.model.items"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>跳转后的页面</title>
<%-- <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> --%>

   

<style type="text/css">
.left{

	margin:100px auto auto 200px;
	background-color:black;
	width:400px;
	height: 300px;
	float: left;
}
.right{
	margin:100px auto auto 200px;
	background-color:gray;
	width: 200px;
	height:1000px;
	float: left;
}
.out{
	width:200px;
	height: 150px; 
}

</style>

<script type="text/javascript">
	function add(){
		var num = parseInt(document.getElementById("number").value);
		if(num<=100){
			document.getElementById("number").value=++num;
		}
	}
	
	function sub(){
		var num = parseInt(document.getElementById("number").value);
		if(num>1){
			document.getElementById("number").value=--num;
		}
	}
	
</script>

</head>
<body>

<div class="left">
<%	//通过dao对象的item对象传入参数id，得到想要的到的信息；
	DAO dao = new DAO();
	String id = request.getParameter("id");
	items it = dao.item(id);
	if(it!=null){
%>
	<dl style="background: white">
				<dt>
					<img width="400" height="200" alt="老剑天下第一帅" src="images/<%=it.getPicture()%>"> 
				</dt>
				<dd class="dd_name"><%=it.getName()%></dd>
				<dd class="dd_price">
					产地:<%=it.getCity()%>&nbsp;&nbsp;价格:￥
					<%=it.getPrice()%>
				</dd>
				<dd>
					<span id="sub" onclick="sub();" style="cursor: pointer; ">-</span><input type="text" id="number" name="number" value="1" size="2"><span id="add" onclick="add();" style="cursor: pointer; ">
					+</span>
				</dd>	
				<dd>
					<span style="background-color: orange;"><a href="com.jiange.nmb/nm?action=add&number=1&id=<%=it.getId() %>>">加入购物车</a></span>  
					<span style="background-color: orange;"><a href="com.jiange.nmb/nm">显示购物车里的内容</a></span>
				
				</dd>
	</dl>
	<%} %>
	
	
	
</div>
<div class="right">
<%	/*cookie 中存儲的id信息以String类型存储，中间以逗号隔开；
	  先判断是否是第一次访问页面，如果不是，就获得“isCookie"中的值，且将新内容添加进去；
*/
	String list = "";
	Cookie[] cookies = request.getCookies();
	if(cookies!=null&&cookies.length>0){
		for(Cookie c:cookies){
			
			if(URLDecoder.decode(c.getName(),"utf-8").equals("isCookie")){
				list+=c.getValue();
			}
		}
	}
	list+=request.getParameter("id")+",";
	
	//将list中的id信息提取出来转换成字符串数组。
	String[] st = list.split(",");
	/* if(st.length>10){
		list="";
	} */ 
	
	//取得数据重新保存cookie；
	Cookie cookie = new Cookie("isCookie",URLEncoder.encode(list,"utf-8"));
	response.addCookie(cookie);
	
	//根据cookie中的记录，批量读取数据库的商品信息；循环打印
	DAO da = new DAO();
	ArrayList<items> li = new ArrayList<items>();
	li = da.items(st);
	if (li!= null && li.size() > 0) {
		for(items its :li){
	%>
<div class="out">
			<dl style="background: white">
				<dt>
					<a href="dologin.jsp?id=<%=its.getId()%>"><img width="180" height="100" alt="老剑天下第一帅" src="images/<%=its.getPicture()%>"> </a>
				</dt>
				<dd class="dd_name"><%=its.getName()%></dd>
				<dd class="dd_price">
					产地:<%=its.getCity()%>&nbsp;&nbsp;价格:￥
					<%=its.getPrice()%></dd>
			</dl>
		</div>
		<%}
		 }
		%>



</div>




</body>
</html>