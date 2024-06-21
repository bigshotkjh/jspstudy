package com.min.app.controller;

import java.io.IOException;

import com.min.app.common.ActionForward;
import com.min.app.service.BoardService;
import com.min.app.service.BoardServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardController extends HttpServlet {
  
  private static final long serialVersionUID = 1L;
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
    request.setCharacterEncoding("UTF-8");
    
    String requestURI = request.getRequestURI();
    String contextPath = request.getContextPath();
    int beginIndex = requestURI.indexOf(contextPath) + contextPath.length() + 1;
    String urlMapping = requestURI.substring(beginIndex);
  
    BoardService boardService = new BoardServiceImpl();
    
    ActionForward actionForward = null;
    
    switch(urlMapping) {
    // 단순 이동 (서비스가 필요하지 않다. forward 로 이동한다.)
    
    // 비즈니스 로직 처리 (서비스가 필요하다.)
    case "list.do":
      actionForward = boardService.getBoardList(request);
      break;
    }
    
    if(actionForward != null) {
      if(actionForward.isRedirect()) {
        response.sendRedirect(actionForward.getPath());
      } else {
        request.getRequestDispatcher(actionForward.getPath()).forward(request, response);
      }
    }
  
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}
