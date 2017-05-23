package cn.edu.zhku.jsj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import cn.edu.zhku.jsj.dao.FoodDao;
import cn.edu.zhku.jsj.daomain.Cloth;
import cn.edu.zhku.jsj.daomain.Food;
import cn.edu.zhku.jsj.web.utils.JdbcUtil;
import cn.edu.zhku.jsj.web.utils.ResultToBean;

public class FoodDaoImpl implements FoodDao {
	@Override
	public int add(Food food){
		Connection con = null;
		PreparedStatement pres = null;
		ResultSet rs = null;
		con = JdbcUtil.getCon();
		try{
			String sql = "insert into food values(null,?,?,?,?,?,?)";
			pres = con.prepareStatement(sql);
			pres.setString(1, food.getFoodname());
			pres.setFloat(2, food.getPrice());
			pres.setInt(3, food.getTotalnum());
			pres.setString(4,food.getImages());
			pres.setString(5, food.getDescription());
			pres.setInt(6, food.getStore_id());
			int num = pres.executeUpdate();
			return num;
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.release(con, pres, rs);
		}
	}
	
	@Override
	public Food find(String food_name){
		Connection con = null;
		PreparedStatement pres = null;
		ResultSet rs = null;
		con = JdbcUtil.getCon();
		try{
			String sql = "select * from food where foodname=?";
			pres = con.prepareStatement(sql);
			pres.setString(1, food_name);
			rs = pres.executeQuery();
			List<Food> list = ResultToBean.getBeanList(Food.class, rs);
			Food food = null;
			if(!list.isEmpty()){
				food = list.get(0);
			}
			return food;
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.release(con, pres, rs);
		}
	}
	
	@Override
	public List<Food> findAll(){
		Connection con = null;
		PreparedStatement pres = null;
		ResultSet rs = null;
		con = JdbcUtil.getCon();
		List<Food> foodlist;
		try{
			String sql = "select * from food";
			pres = con.prepareStatement(sql);
			rs = pres.executeQuery();
			foodlist = ResultToBean.getBeanList(Food.class, rs); //调工具类 （封装 数据到 bean的工具类）
			return foodlist;
		}catch(Exception e){ 
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.release(con, pres, rs);
		}
	}
	
	@Override
	public boolean update(Food food){
		boolean b = false;
		Connection con = null;
		PreparedStatement pres = null;
		ResultSet rs = null;
		con = JdbcUtil.getCon();
		try{
			String sql = "update food set totalnum=?,price=?,description=? where food_id=?";
			pres = con.prepareStatement(sql);
			pres.setInt(1, food.getTotalnum());
			pres.setFloat(2, food.getPrice());
			pres.setString(3, food.getDescription());
			pres.setInt(4, food.getFood_id());
			int num =  pres.executeUpdate();
			if(num!=0){
				b = true;
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.release(con, pres, rs);
		}
		return b;
	}
	
	@Override
	public boolean delete(int food_id){
		boolean b = false;
		Connection con = null;
		PreparedStatement pres = null;
		ResultSet rs = null;
		con = JdbcUtil.getCon();
		try{
			String sql = "delete from food where food_id=?";
			pres = con.prepareStatement(sql);
			pres.setInt(1, food_id);
			int num = pres.executeUpdate();
			if(num!=0){
				b = true;
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.release(con, pres, rs);
		}
		return b;
	}

	@Override
	public List<Food> findFood(int store_id) {
		Connection con = null;
		PreparedStatement pres = null;
		ResultSet rs = null;
		con = JdbcUtil.getCon();
		try{
			String sql = "select * from food where store_id=?";
			pres = con.prepareStatement(sql);
			pres.setInt(1, store_id);
			rs = pres.executeQuery();
			List<Food> foodlist = ResultToBean.getBeanList(Food.class, rs);
			return foodlist;
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.release(con, pres, rs);
		}
	}
}	
