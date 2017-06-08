package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.controller.CommandAction;
import com.board.dao.BoardMyBatisDao;

public class DeleteAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String boardSeq = request.getParameter("board_seq");
		
		 BoardMyBatisDao.getInstance().deleteArticle(boardSeq);
		
		return "delete.jsp";
	}

}
