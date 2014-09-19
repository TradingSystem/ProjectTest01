package com.mercury.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import oracle.jdbc.OracleTypes;

import com.mercury.beans.User;
import com.mercury.dao.HelloDao;
import com.mercury.utils.JdbcUtil;

public class HelloDaoImpl implements HelloDao {
	private JdbcUtil util;
	
	public HelloDaoImpl() {
		if (util==null) {
			util = new JdbcUtil();
		}
	}
	
	@Override
	public User findByName(String name) {
		// TODO Auto-generated method stub
		String sql = "select * from sample where name=?";
		User user = null;
		try {
			Connection conn = util.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setName(rs.getString("name"));
				user.setAge(rs.getInt("age"));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		String sp = "{?=call saveUser(?,?)}";
		try {
			Connection conn = util.getConnection();
			CallableStatement cs = conn.prepareCall(sp);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, user.getName());
			cs.setInt(3, user.getAge());
			cs.execute();
			cs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		String sql = "update sample set age=? where name=?";
		try {
			Connection conn = util.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getAge());
			ps.setString(2, user.getName());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		String sql = "delete sample where name=? and age=?";
		try {
			Connection conn = util.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setInt(2, user.getAge());
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> queryAll() {
		// TODO Auto-generated method stub
		String sp = "{?=call queryUser()}";
		List<User> users = new ArrayList<User>();
		try {
			Connection conn = util.getConnection();
			CallableStatement cs = conn.prepareCall(sp);
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.execute();
			ResultSet rs = (ResultSet)cs.getObject(1);
			while (rs.next()) {
				User user = new User();
				user.setName(rs.getString("Name"));
				user.setAge(rs.getInt("Age"));
				users.add(user);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

}
