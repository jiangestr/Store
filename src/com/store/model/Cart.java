package com.store.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.store.DAO.DAO;

public class Cart {
	private HashMap<items, Integer> goods;
	private double totalPrice;
	
	public Cart(){
		super();
		this.goods = new HashMap<items,Integer>();
		totalPrice = 0.0;
	}
	
	//��Ʒ������,���ж�����У�ʵ���ظ���Ʒ��������ӡ���ֹ��Ʒ�ظ���
	public boolean addGoodsCart(items item, Integer num){
		if(goods.containsKey(item)){
			num += goods.get(item);
			goods.remove(item);
			goods.put(item, num);
		}
		else{
		
			goods.put(item, num);
		}

		totalCart();                              //���¼�����Ʒ�ܶ��������totalPrice
		return true;
		
	}
	
	//��Ʒ��ɾ��
	public boolean deleteGoodsCart(items item){
		goods.remove(item);
		totalCart();							//���¼�����Ʒ�ܶ��������totalPrice
		return false;
	}
	
	//������Ʒ���ܼ�
	public double totalCart(){
		double count = 0.0;
		Set<items> key = goods.keySet();
		Iterator<items> item = key.iterator() ;
		if(item!=null){
			System.out.println("����");
		while(item.hasNext()){	
			items i = item.next();
	    	count += i.getPrice()* goods.get(i);
		}
		}else{
			System.out.println("�յ�");
		}
		return totalPrice=count;
	}

	
	//getter/setter����
	public HashMap<items, Integer> getGoods() {
		return goods;
	}

	public void setGoods(HashMap<items, Integer> goods) {
		this.goods = goods;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public static void main(String[] args) {
		DAO dao = new DAO();
		items item1 = dao.item("1");
		items item2 = dao.item("2");
		Cart cart = new Cart();
		cart.addGoodsCart(item1, 1);
		cart.addGoodsCart(item2, 2);
		System.out.println(cart.getTotalPrice());
	}
}
