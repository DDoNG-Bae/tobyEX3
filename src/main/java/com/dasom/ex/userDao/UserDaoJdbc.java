package com.dasom.ex.userDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class UserDaoJdbc implements UserDao{
	private JdbcTemplate jdbcTemplate;
	private RowMapper<User> userMapper = new RowMapper<User>() {
		
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			return user;
		}
	};
	public UserDaoJdbc() {};
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	public void deleteAll(){
		this.jdbcTemplate.update("delete from users");
	}
	
	//Spring 3.2부터 queryforInt/queryforLon 메소드 사용불가
	//queryForObject로 대체
	public int getCount(){
		return this.jdbcTemplate.queryForObject("select count(*) from users",Integer.class);
	}
	
	public void add(final User user){	
		this.jdbcTemplate.update("INSERT INTO users(id,name,password) VALUES(?,?,?)", user.getId(),user.getName(),user.getPassword());
	}
	
	public User get(String id){
		return this.jdbcTemplate.queryForObject("select * from users where id = ?", new Object[] {id}, userMapper);
	}
	
	public List<User> getAll(){
		return this.jdbcTemplate.query("select * from users order by id", userMapper);
	}
	
}
