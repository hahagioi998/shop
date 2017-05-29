package cn.edu.zhku.jsj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import cn.edu.zhku.jsj.dao.OrderDao;
import cn.edu.zhku.jsj.domain.Order;
import cn.edu.zhku.jsj.web.utils.JdbcUtil;
import cn.edu.zhku.jsj.web.utils.ResultToBean;

public class OrderDaoImpl implements OrderDao {
	@Override
	public int add(Order order){
		Connection con = null;
		PreparedStatement pres = null;
		ResultSet rs = null;
		/*List<Order> listorder = new LinkedList<Order>();*/
		/*Order order = null;*/
		try{
			con = JdbcUtil.getCon();
			String sql = "insert into Orders values(null,?,?,?,?,?,?,?)";
			pres = con.prepareStatement(sql);
			pres.setInt(1, order.getGood_id());
			pres.setString(2, order.getUser_id());
			pres.setInt(3, order.getStore_id());
			pres.setInt(4, order.getQuantity());
			
			Timestamp time = new Timestamp(order.getOrdertime());
			pres.setTimestamp(5, time);
			
			pres.setInt(6, order.getState());
			pres.setFloat(7, order.getPrice());
			
			int num = pres.executeUpdate();
			return num;
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.release(con, pres, rs);
		}
	}
	@Override
	public List<Map> findAll(){
		//查找 order 表  将 订单项（可能是书 食物或者衣服）
		//将信息存在 map集合 里（关键字是 order表的字段名 value是字段名对应值）然后再将map存在 list中,最后返回list集合
		return null;
	}
	@Override
	public boolean delete(int order_id){
		return false;
	}
	@Override
	public List<Order> getstore_Orders(int store_id) {
		Connection con = null;
		PreparedStatement pres = null;
		ResultSet rs = null;
		List<Order> listorder = new LinkedList<Order>();
		/*Order order = null;*/
		try{
			con = JdbcUtil.getCon();
			String sql = "select * from Orders where store_id=?";
			pres = con.prepareStatement(sql);
			pres.setInt(1, store_id);
			rs = pres.executeQuery();
			listorder = ResultToBean.getBeanList(Order.class, rs);
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.release(con, pres, rs);
		}
		return listorder;
	}
}
