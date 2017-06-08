package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.controller.CommandAction;
import com.board.dao.BoardMyBatisDao;
import com.board.dto.Board;

public class CountAction implements CommandAction {
	
	
	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int board_seq = Integer.parseInt(request.getParameter("board_seq"));
		Board article = BoardMyBatisDao.getInstance().getArticle(board_seq);
		String regIp = request.getRemoteAddr();
		if(!regIp.equals(article.getRegIp())) {
			int count = article.getCount();
			article.setCount(++count);
			BoardMyBatisDao.getInstance().setArticleCount(article);
		}
		request.setAttribute("url", "content.do?board_seq="+board_seq);

		return "redirect2.jsp";
	}

}
