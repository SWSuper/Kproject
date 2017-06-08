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
	private Map<String, Object> commandMap = new HashMap<String, Object>(); // ��ɾ��
																			// ��ɾ�
																			// ó��
																			// Ŭ������
																			// ������
																			// ����

	public void init(ServletConfig config) throws ServletException {
		// Common properties
		loadProperties("com/board/properties/Command");
		/*
		 * loadProperties("com/board/properties/Command.properties");
		 */ }

	// properties ����
	private void loadProperties(String path) {
		// ������ ���������� rbHome�� ����.
		ResourceBundle rbHome = ResourceBundle.getBundle(path);
		Enumeration<String> actionEnumHome = rbHome.getKeys();

		while (actionEnumHome.hasMoreElements()) {
			String command = actionEnumHome.nextElement();
			String className = rbHome.getString(command);
			try {
				Class commandClass = Class.forName(className); // �ش� ���ڿ��� Ŭ������
																// �����.
				Object commandInstance = commandClass.newInstance(); // �ش� Ŭ������
																		// ��ü��
																		// ����
				commandMap.put(command, commandInstance); // Map ��ü�� commandMap��
															// ��ü ����
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
		requestPro(request, response); // get��İ� post����� ��� requestPro�� ó��
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

	// ������� ��û�� �м��ؼ� �ش� �۾��� ó��

	private void requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String view = null;
		CommandAction com = null;

		try {
			String command = request.getRequestURI(); // ��ü URI
			if (command.indexOf(request.getContextPath()) == 0) { // root path
				command = command.substring(request.getContextPath().length());// root
																				// path
																				// ����
			}
			com = (CommandAction) commandMap.get(command);

			if (com == null) {
				System.out.println("not found : " + command);
				return;
			}
			view = com.requestPro(request, response); // CommandAction �������̽�
														// ��ӹ��� Action
														// ���� requestPro �޼ҵ� ����
			// view ���� .jsp �� ���ϵ�.
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
