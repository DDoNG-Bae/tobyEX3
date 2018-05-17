package com.dasom.ex.userDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class JdbcContext {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException{
		Connection c = null;
		PreparedStatement ps=null;
		try {
			c=dataSource.getConnection();
			
			ps=stmt.makePreparedStatement(c);
			
			ps.executeUpdate();
		}catch(SQLException e) {
			throw e;
		}finally {
			if(ps != null) { try { ps.close(); } catch(SQLException e) {} }
			if(c!=null) { try { c.close(); } catch(SQLException e) {} }
		}
		
	}
	
	public void excuteSql(final String query,final String...strs) throws SQLException{
		workWithStatementStrategy(new StatementStrategy() {
			
			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement ps = c.prepareStatement(query);
				
				for(int i = 0; i<strs.length ; i++) {
					ps.setString((i+1), strs[i].toString());
				}
				
				return ps;
			}
		});
	}
}
