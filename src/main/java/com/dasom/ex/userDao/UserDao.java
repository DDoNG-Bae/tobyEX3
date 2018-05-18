package com.dasom.ex.userDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;

public class UserDao {
	private DataSource dataSource;
	private JdbcContext jdbcContext;
	private JdbcTemplate jdbcTemplate;
	public UserDao() {};
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.dataSource = dataSource;
	}
	
	public void setJdbcContext(JdbcContext jdbcContext) {
		this.jdbcContext = jdbcContext;
	}

	
	public void deleteAll() throws SQLException{
		this.jdbcTemplate.update("delete from users");
	}
	
	//Spring 3.2부터 queryforInt/queryforLon 메소드 사용불가
	//queryForObject로 대체
	public int getCount() throws SQLException{
		return this.jdbcTemplate.queryForObject("select count(*) from users",Integer.class);
	}
	
	public void add(final User user) throws SQLException {	
		this.jdbcTemplate.update("INSERT INTO users(id,name,password) VALUES(?,?,?)", user.getId(),user.getName(),user.getPassword());
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
