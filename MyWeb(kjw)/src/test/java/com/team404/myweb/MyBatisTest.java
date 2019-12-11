package com.team404.myweb;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/DB-context.xml")
public class MyBatisTest {
	//DB-context에 생성된 빈을 주입
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSessionFactoryBean sqlSessionFactory;
	
	@Test
	public void test() {
		
		try {
			System.out.println("dataSource:  "+dataSource);
			System.out.println("sqlSessionFactory:  "+sqlSessionFactory);
		} catch (Exception e) {
			
		}
	}
}
