package com.jiange.nmb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.DAO.DAO;
import com.store.model.Cart;
import com.store.model.items;

/**
 * Servlet implementation class nm
 */
@WebServlet("/nm")
public class nm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String action;
       
    public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public nm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		this.action = request.getParameter("action");
		
		if(action.equals("add")){
			if(addCart(request, response)){
				request.getRequestDispatcher("/success.jsp").forward(request, response);
			}
			else{
				out.print("shib");
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	//实例化Cart购物车对象，将request获得的信息给，Crat中的addGoodsCart（）方法传参；
	private boolean addCart(HttpServletRequest request, HttpServletResponse response){
		
		String id = request.getParameter("id");
		String num = request.getParameter("number");
		System.out.println(num);
		DAO dao = new DAO();
		items item = dao.item(id);
		
		//判断是否是第一次给购物车添加商品
		Cart ca =(Cart) request.getSession().getAttribute("cart");
		if(ca==null){
			Cart cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		Cart cart =(Cart) request.getSession().getAttribute("cart");
		if(cart.addGoodsCart(item, Integer.parseInt(num))){
			return true;
		}else{
			return false;
		}
	}

}
