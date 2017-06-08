package com.board.db.sqlconfig;

import java.io.IOException;  
import java.io.InputStream; 

import org.apache.ibatis.io.Resources;  
import org.apache.ibatis.session.SqlSessionFactory;  
import org.apache.ibatis.session.SqlSessionFactoryBuilder; 

public class MyBatisConfig {
	 // mybatis.xml À§Ä¡
    private final static String RESOURCE = "com/board/db/sqlconfig/mybatis-config.xml";
	private static SqlSessionFactory sqlSessionFactory;
	
	/*
	public void init() {
		try {
			InputStream inputStream = Resources.getResourceAsStream(RESOURCE);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
	public static SqlSessionFactory getSqlSessionFactory() {
		if(sqlSessionFactory == null) {
			try {
				InputStream inputStream = Resources.getResourceAsStream(RESOURCE);
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sqlSessionFactory;
	}
	
	public static void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		MyBatisConfig.sqlSessionFactory = sqlSessionFactory;
	}
}
