<%@page import="java.util.Iterator"%>
<%@page import="com.store.DAO.DAO"%>
<%@page import="java.util.ArrayList,com.store.model.items"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>购物网站，买不了吃亏，买不了上当</title>
<style type="text/css">
div {
	float: left;
	margin: 10px;
}
.out{
	width:200px;
	height: 150px;
	float: left; 
}

dt{
	width: 180px;
	height: 100px;
	
}

dd {
	font-size: 10;
}

.dd_name {
	color: green;
}

.dd_city {
	color: gray;
}
</style>
</head>
<body>

	<div style="width: 1000px; height: 600px; float: left;background:gray; ">
		<%
			ArrayList<items> list = new ArrayList<items>();
			DAO dao = new DAO();
			list = dao.getAllItems();
			if (list != null && list.size() > 0) {
				Iterator<items> iter = list.iterator();
				while (iter.hasNext()) {
					items it = iter.next();
					out.print("sss");
		%>
		<div class="out">
			<dl style="background: white">
				<dt>
					<a href="dologin.jsp?id=<%=it.getId()%>"><img width="180" height="100" alt="老剑天下第一帅" src="images/<%=it.getPicture()%>"> </a>
				</dt>
				<dd class="dd_name"><%=it.getName()%></dd>
				<dd class="dd_price">
					产地:<%=it.getCity()%>&nbsp;&nbsp;价格:￥
					<%=it.getPrice()%></dd>
			</dl>
		</div>
		<%}
		 }
		%>
	</div>




</body>
</html>