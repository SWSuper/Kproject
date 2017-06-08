package com.board.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControllerAcdtion
 */
@WebServlet("/ControllerAction")
public class ControllerAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> commandMap = new HashMap<String, Object>(); // 명령어와
																			// 명령어
																			// 처리
																			// 클래스를
																			// 쌍으로
																			// 저장

	public void init(ServletConfig config) throws ServletException {
		// Common properties
		loadProperties("com/board/properties/Command");
		/*
		 * loadProperties("com/board/properties/Command.properties");
		 */ }

	// properties 설정
	private void loadProperties(String path) {
		// 누구를 실행할지를 rbHome에 저장.
		ResourceBundle rbHome = ResourceBundle.getBundle(path);
		Enumeration<String> actionEnumHome = rbHome.getKeys();

		while (actionEnumHome.hasMoreElements()) {
			String command = actionEnumHome.nextElement();
			String className = rbHome.getString(command);
			try {
				Class commandClass = Class.forName(className); // 해당 문자열을 클래스로
																// 만든다.
				Object commandInstance = commandClass.newInstance(); // 해당 클래스의
																		// 객체를
																		// 생성
				commandMap.put(command, commandInstance); // Map 객체인 commandMap에
															// 객체 저장
			} catch (ClassNotFoundException e) {
				continue; // error
				// throw new ServletException(e);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public ControllerAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		requestPro(request, response); // get방식과 post방식을 모두 requestPro로 처리
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		requestPro(request, response);
	}

	// 사용자의 요청을 분석해서 해당 작업을 처리

	private void requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String view = null;
		CommandAction com = null;

		try {
			String command = request.getRequestURI(); // 전체 URI
			if (command.indexOf(request.getContextPath()) == 0) { // root path
				command = command.substring(request.getContextPath().length());// root
																				// path
																				// 이후
			}
			com = (CommandAction) commandMap.get(command);

			if (com == null) {
				System.out.println("not found : " + command);
				return;
			}
			view = com.requestPro(request, response); // CommandAction 인터페이스
														// 상속받은 Action
														// 들의 requestPro 메소드 실행
			// view 에는 .jsp 가 리턴됨.
			if (view == null) {
				return;
			}
		} catch (Throwable e) {
			throw new ServletException(e);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}
