package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.controller.CommandAction;
import com.board.dao.BoardMyBatisDao;
import com.board.dto.Board;

public class ContentAction implements CommandAction {

	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub		
		request.setCharacterEncoding("utf-8");
		System.out.println("1234");
		int board_seq = Integer.parseInt(request.getParameter("board_seq"));
		Board article = BoardMyBatisDao.getInstance().getArticle(board_seq);

		request.setAttribute("article", article);

		return "content.jsp";
	}

}
