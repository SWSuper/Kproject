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
	    //request객체는 해당 요청을 보낸 유저의 ip를 캐치할 수 있다.
	    //바로 request.getRemoteAddr() 메소드
	    String regIp = request.getRemoteAddr();
	    board.setRegIp(regIp);
	    
	    System.out.println("title="+board.getTitle());
	    System.out.println("wirter="+board.getWriter());
	    System.out.println("regdate="+board.getRegDate());
	    System.out.println("content="+board.getContent());
	    System.out.println("count="+board.getCount());
	    System.out.println("regIp="+board.getRegIp());
	    
	    if(board.getTitle() == "" ||board.getTitle() == null)
	    	System.out.println("title이 null입니다.");
	    
	    if(board.getWriter() == "" ||board.getWriter() == null) 
	    	System.out.println("writer가 null입니다.");   
	    else if(!Pattern.matches("^[_0-9a-zA-Z-]+@[0-9a-zA-Z-]+(.[_0-9a-zA-Z-]+)*$", board.getWriter()))
	    	System.out.println("이메일 형식이 아닙니다.");
	    
	    if(board.getRegDate() == "" ||board.getRegDate() == null) 
	    	System.out.println("regdate가 null입니다.");
	    else if(!Pattern.matches("^[0-9]*$", board.getRegDate()))
	    	System.out.println("숫자형식이 아닙니다.");
	    
	    if(board.getContent() == "" ||board.getContent() == null) 
	    	System.out.println("content가 null입니다.");
	    
	    BoardMyBatisDao.getInstance().insertArticle(board);
	    
		return "insert.jsp";
	}
}
