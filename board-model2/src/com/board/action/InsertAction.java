package com.board.action;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.controller.CommandAction;
import com.board.dao.BoardMyBatisDao;
import com.board.dto.Board;

public class InsertAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		System.out.println("InsertAction");
		request.setCharacterEncoding("utf-8");
		
		Board board = new Board();
	    board.setTitle(request.getParameter("title"));
	    board.setWriter(request.getParameter("writer"));
	    board.setRegDate(request.getParameter("regdate"));
	    board.setContent(request.getParameter("content"));
	    board.setCount(0);
	    //request��ü�� �ش� ��û�� ���� ������ ip�� ĳġ�� �� �ִ�.
	    //�ٷ� request.getRemoteAddr() �޼ҵ�
	    String regIp = request.getRemoteAddr();
	    board.setRegIp(regIp);
	    
	    System.out.println("title="+board.getTitle());
	    System.out.println("wirter="+board.getWriter());
	    System.out.println("regdate="+board.getRegDate());
	    System.out.println("content="+board.getContent());
	    System.out.println("count="+board.getCount());
	    System.out.println("regIp="+board.getRegIp());
	    
	    if(board.getTitle() == "" ||board.getTitle() == null)
	    	System.out.println("title�� null�Դϴ�.");
	    
	    if(board.getWriter() == "" ||board.getWriter() == null) 
	    	System.out.println("writer�� null�Դϴ�.");   
	    else if(!Pattern.matches("^[_0-9a-zA-Z-]+@[0-9a-zA-Z-]+(.[_0-9a-zA-Z-]+)*$", board.getWriter()))
	    	System.out.println("�̸��� ������ �ƴմϴ�.");
	    
	    if(board.getRegDate() == "" ||board.getRegDate() == null) 
	    	System.out.println("regdate�� null�Դϴ�.");
	    else if(!Pattern.matches("^[0-9]*$", board.getRegDate()))
	    	System.out.println("���������� �ƴմϴ�.");
	    
	    if(board.getContent() == "" ||board.getContent() == null) 
	    	System.out.println("content�� null�Դϴ�.");
	    
	    BoardMyBatisDao.getInstance().insertArticle(board);
	    
		return "insert.jsp";
	}
}
