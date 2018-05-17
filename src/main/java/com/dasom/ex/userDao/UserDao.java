package com.dasom.ex.userDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

public class UserDao {
	private DataSource dataSource;
	private JdbcContext jdbcContext;
	public UserDao() {};
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void setJdbcContext(JdbcContext jdbcContext) {
		this.jdbcContext = jdbcContext;
	}

	
	public void deleteAll() throws SQLException{
		this.jdbcContext.excuteSql("delete from users");
	}
	
	public int getCount() throws SQLException{
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			c = dataSource.getConnection();
			ps = c.prepareStatement("select count(*) from users");
			rs = ps.executeQuery();
		
			rs.next();
			return rs.getInt(1);
		}catch(SQLException e) {
			throw e;
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch(SQLException e) {
					
				}
			}
			if(ps!=null) {
				try {
					ps.close();
				}catch(SQLException e) {
					
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				}catch(SQLException e) {
					
				}
			}
		}	
	}
	
	public void add(final User user) throws SQLException {	
		this.jdbcContext.excuteSql("insert into users(id,name,password) values(?,?,?)", user.getId(), user.getName(), user.getPassword());
	}
	
	public User get(String id) throws SQLException{
		Connection c = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement(
				"select * from users where id = ?");
		ps.setString(1, id);
		
		ResultSet rs=ps.executeQuery();
		User user=null;
		if(rs.next())
		{
			user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
		}
		rs.close();
		ps.close();
		c.close();
		
		if(user==null) throw new EmptyResultDataAccessException(1);
		
		return user;
	}
	
	
}
