<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@ page import="com.store.model.Cart,com.store.model.items" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
   %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>success</title>
</head>
<body>
ssss
<table>
	<%
		Cart cart = (Cart)session.getAttribute("cart");
		if(cart!=null){
			HashMap<items, Integer> goods = cart.getGoods();
			Set<items> keys = goods.keySet();
			Iterator<items> iter = keys.iterator();
			while(iter.hasNext()){
				items item  = (items)iter.next();
				Integer num = goods.get(item);
	%>
	<tr>
		<td>商品名称：<%=item.getName() %></td>
		<td>商品数量：<%=num %></td>
		<td>商品单价：<%=item.getPrice() %></td>
		<td><input type="button" value="删除" ></td>
	</tr>
	<%
			}
			
	%>
	<tr>
		<td colspan="4">总金额：<%=cart.getTotalPrice()%></td>
	</tr>
	<%
		}
	%>
</table>


</body>
</html>