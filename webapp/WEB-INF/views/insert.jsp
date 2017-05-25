<!-- 인서트 한 후에는 반드시 다른 곳으로 창을 옮겨준다 -->
<%@page import="com.jx372.emaillist.dao.EmailListDao"%>
<%@page import="com.jx372.emaillist.vo.EmailListVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <% 
    	request.setCharacterEncoding("utf-8");
    	
    	String firstName=request.getParameter("fn");
    	String lastName=request.getParameter("ln");
    	String email=request.getParameter("email");
    	
    	EmailListVo vo=new EmailListVo();
    	vo.setFirstName(firstName);
    	vo.setLastName(lastName);
    	vo.setEmail(email);
    	
    	new EmailListDao().insert(vo);
    	
    	//redirect 응답
    	response.sendRedirect("/emaillist01");  //아래에 있는 html코드는 실행되지 않고 다시 돌아간다.
    	
    %>
