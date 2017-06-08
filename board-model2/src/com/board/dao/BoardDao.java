package com.board.dao;

import java.util.List;

import com.board.dto.Board;

public interface BoardDao {
	public List<Board> getArticleList();
	public void insertArticle(Board board);
	public Board getArticle(int boardSeq);
}
