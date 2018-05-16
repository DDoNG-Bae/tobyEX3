package com.dasom.ex.userDao;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException,SQLException {
		// TODO Auto-generated method stub
		
		//자바코드
		//ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		
		//XML
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		User user = new User();
		user.setId("ektha7");
		user.setName("다솜");
		user.setPassword("ektha");
		
		dao.add(user);
		
		System.out.println(user.getId()+ "등록 성공");
		
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
	}

}
