package controller;

import java.io.IOException;

import common.ActionForward;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BookService;
import service.BookServiceImpl;

public class StuController extends HttpServlet {
  
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
    request.setCharacterEncoding("UTF-8");
    
    String requestURI = request.getRequestURI();    /* http://localhost:9090/mvc/list.do */
    String contextPath = request.getContextPath();  /*                      /mvc         */
    int beginIndex = requestURI.indexOf(contextPath) + contextPath.length() + 1;
    String urlMapping = requestURI.substring(beginIndex);   /* list.do */
  
    BookService bookService = new BookServiceImpl();
    
    ActionForward actionForward = null;
    
    switch(urlMapping) {
    // 단순 이동 (서비스가 필요하지 않다. forward 로 이동한다.)
    case "write.do": //새학생 등록위해 왔다. 파라메터는 X
      actionForward = new ActionForward("/book/write.jsp", false);
      break;
    case "index.do": 
      actionForward = new ActionForward("/index.jsp", false);
      break;
    // 비즈니스 로직 처리 (서비스가 필요하다.)
    case "list.do":// 첫화면에서 넘어 왔어. 전체의 리스트를 요구함. 
      actionForward = bookService.getBooks(request);
      break;
    case "detail.do":// 북의 번호가지고 왔다.
      actionForward = bookService.getBookByNo(request);//서비스 다녀오자.
      break;
    case "register.do":// 새로 등록할 새책 상세정보 들고 넘어옴.
      actionForward = bookService.registerBook(request);
      break;
    case "delete.do"://책 삭제
      actionForward = bookService.removeBook(request);
      break;
    case "edit.do"://수정할 책가져오기. (detail.do랑 비슷한 패턴.)
      actionForward = bookService.editBook(request);
      /*bookService.editBook()의 request에 책번호 넣어서 보내서 서비스랑 다오 돌고 
      ActionForward형으로 다시 돌아온거 ActionFward형으로 받아 */
      break;
    case "modify.do": // 수정내용 db에 넣는 과정.
      actionForward = bookService.modifyBook(request); //서비스임플 갔다 오자
      break;
    }
    
    if(actionForward != null) {
      // 위에 .do들이 서비스랑 다오 돌고 ActionForward형으로 뭐 받아온거 전달방식 처리.
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
