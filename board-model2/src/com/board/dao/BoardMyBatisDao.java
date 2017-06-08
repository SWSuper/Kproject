package com.board.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.board.db.sqlconfig.MyBatisConfig;
import com.board.dto.Board;

public class BoardMyBatisDao implements BoardDao {

	// MyBatis SQL 팩토리 객체
	private SqlSessionFactory sessionFactory = null;
	private static BoardMyBatisDao _instance;

	// 생성 시 MyBatis의 세션을 얻어온다.
	public BoardMyBatisDao() {
		this.sessionFactory = MyBatisConfig.getSqlSessionFactory();
	}

	public static BoardMyBatisDao getInstance() {
		if (_instance == null)
			_instance = new BoardMyBatisDao();
		return _instance;
	}

	// 목록 조회 메소드
	public List<Board> getArticleList(int page) {
		// 생성자에서 얻은 팩토리에서 SqlSession 객체를 얻는다.
		SqlSession session = this.sessionFactory.openSession();
		int start = page * 10;
		int length = 10;
		RowBounds rowBounds = new RowBounds(start, length);
		List<Board> articleList = null;
		try {
			// do work
			articleList = session.selectList("getArticleList", null, rowBounds);
			return articleList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return null;
	}

	@Override
	public List<Board> getArticleList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertArticle(Board board) {
		System.out.println("dao_insertArticle");
		// 생성자에서 얻은 팩토리에서 SqlSession 객체를 얻는다.
		SqlSession session = this.sessionFactory.openSession(true);
		try {
			// do work
			session.insert("insertArticle", board);			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}

	
	public void deleteArticle(String board) {
		System.out.println("dao_insertArticle");
		// 생성자에서 얻은 팩토리에서 SqlSession 객체를 얻는다.
		SqlSession session = this.sessionFactory.openSession(true);
		try {
			// do work
			session.insert("deleteArticle", board);			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}
	
	@Override
	public Board getArticle(int board_seq) {
		// TODO Auto-generated method stub
		SqlSession session = this.sessionFactory.openSession();
		Board board;
		try {
			// do work
			board = session.selectOne("getArticle", board_seq);
			return board;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
		return null;
	}
	
	public void setArticleCount(Board board) {
		SqlSession session = this.sessionFactory.openSession();
		try {
			// do work
			session.update("setArticleCount", board);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
		}
	}
}
