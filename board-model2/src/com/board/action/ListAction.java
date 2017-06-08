package com.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.controller.CommandAction;
import com.board.dao.BoardMyBatisDao;
import com.board.dto.Board;

public class ListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");

		int page = 0; // default page is zero.
		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"))/10;

		ArrayList<Board> articleList = (ArrayList<Board>) BoardMyBatisDao
				.getInstance().getArticleList(page);
		request.setAttribute("articleList", articleList);
		request.setAttribute("page", page);// page번호를 뷰에서 보기위해서 표시

		return "list.jsp";
	}
}
