package service;

import common.ActionForward;
import dao.BookDAO;
import dto.BookDTO;
import jakarta.servlet.http.HttpServletRequest;

public class BookServiceImpl implements BookService {

  // Field
  private BookDAO bookDAO = BookDAO.getInstance();
  
  @Override//전체리스트 확인과 삭제결과 확인이 같이 씀.
  public ActionForward getBooks(HttpServletRequest request) {//전체리스트를 원해서 왔어.
    
    // 데이터베이스에서 가져온 List<BookDTO> 을 JSP 로 보내기 위해서 request 에 저장한 뒤 forward 한다.
    
    request.setAttribute("books", bookDAO.getBooks());//다오로 가보자.
    //다오에 getBooks()에서 북리스트를 만들어 북스로 반환. 
    //여기서 북스를 북스로 리퀘스트에 저장.
    
    // 삭제 후 목록 보기로 갈 때 삭제 결과를 JSP 로 보내기 위해서 request 에 저장한다.
    if(request.getParameter("deleteResult") != null) 
      request.setAttribute("deleteResult", request.getParameter("deleteResult"));
    
    return new ActionForward("/book/list.jsp", false);
    //화면에 보일 list.jsp로 보낸다. forward로.
    
  }

  @Override//책 디테일확인과 수정확인이 같이 쓰고 있어.
  public ActionForward getBookByNo(HttpServletRequest request) {
    
    // 전달된 bookNo 와 일치하는 책 정보를 데이터베이스로부터 가져와서 JSP 로 전달하기 위해 request 에 저장한 뒤 forward 한다.
    
    int bookNo = Integer.parseInt(request.getParameter("bookNo"));
    //파라메터로 들어온 번호 숫자형으로 바꿔서 저장.
    
    if(request.getParameter("modifyResult") != null) {// 파라메터가 있는지에 따라 안내문.
      int modifyResult = Integer.parseInt(request.getParameter("modifyResult"));
      //수정되고 안되고에 따라 1 or 0 반환. 그거 확인하려고 숫자로 바꿈.
      request.setAttribute("modifyMessage", modifyResult == 1 ? "수정되었습니다." : "수정되지 않았습니다.");// 1인지 0인지 확인해서 스트링형으로 수정메시지를 request에 저장. 
    }
    
    request.setAttribute("book", bookDAO.getBookByNo(bookNo));//다오 해당 메소드로 가자.
    
    return new ActionForward("/book/detail.jsp", false);
    
  }

  @Override
  public ActionForward registerBook(HttpServletRequest request) {
    
    // 요청 파라미터 (제목, 저자, 가격) 
    String title = request.getParameter("title");
    String author = request.getParameter("author");
    int price = Integer.parseInt(request.getParameter("price"));
    //request에서 각 정보들 빼서 책만들어.
    // 데이터베이스로 전달할 형식인 BookDTO 타입의 객체 생성
    BookDTO book = BookDTO.builder()
        .title(title)
        .author(author)
        .price(price)
        .build();
    
    // 데이터베이스 실행
    int result = bookDAO.insertBook(book);
    
    // 성공하면 : /book/list.jsp 로 가기 위한 /list.do
    // 실패하면 : /index.jsp     로 가기 위한 /index.do
    String path = request.getContextPath() + (result == 1 ? "/list.do" : "/index.do");
    
    // 이동방식 : redirect (DML : INSERT, UPDATE, DELETE)
    return new ActionForward(path, true);
    
  }
  
  @Override
  public ActionForward removeBook(HttpServletRequest request) {
    
    // 삭제할 책 번호 (요청 파라미터)
    int bookNo = Integer.parseInt(request.getParameter("bookNo"));
    
    // 데이터베이스에서 삭제
    int result = bookDAO.deleteBook(bookNo);
    
    // 성공/실패 모두 /book/list.jsp 로 이동하기 위한 /list.do
    
    return new ActionForward(request.getContextPath() + "/list.do?deleteResult=" + result, true);//결과 0 or 1을 달고 list.do로.
    
  }
  
  @Override
  public ActionForward editBook(HttpServletRequest request) {
    
    // 편집할 책의 정보를 JSP 로 전달하고 forward 한다.
    
    int bookNo = Integer.parseInt(request.getParameter("bookNo"));
    //request에서 bookNo빼서 int형으로 변환.
    
    request.setAttribute("book", bookDAO.getBookByNo(bookNo));
    //책번호 줘서 bookDAO.getBookByNo()함 돌리고 답가져와서 request에 저장
    
    return new ActionForward("/book/edit.jsp", false);
    //ActionForward에 넣어 리턴. (번호에 맞는 책을 가져왔고 이제 수정할수 있는 페이지로 보낸다.)
  }

  @Override
  public ActionForward modifyBook(HttpServletRequest request) {
    
    int book_no = Integer.parseInt(request.getParameter("bookNo"));
    
    // 수정할 책의 정보를 저장한 BookDTO 만들기
    BookDTO book = BookDTO.builder()
        .book_no(book_no)
        .title(request.getParameter("title"))
        .author(request.getParameter("author"))
        .price(Integer.parseInt(request.getParameter("price")))
        .build();
    
    // 수정
    int result = bookDAO.updateBook(book);
    //만든 책 넣어서 다오로 보냈다가 돌아오자.
    
    // 성공/실패 상관 없이 /book/detail.jsp 로 redirect 
    // 성공/실패 여부를 전달해서 /book/detail.jsp 가 메시지를 출력할 수 있도록 처리
    
    return new ActionForward(request.getContextPath() + "/detail.do?bookNo=" + book_no + "&modifyResult=" + result, true);
    //다시 controller의 다른 method로 책번호랑 수정성공 결과 같이 보내고 있음.
    //수정되어진 책의 새로운 상세 정보와 숫자인 수정결과에 맞는 안내문자를 클라이언트에게 보여주기 위해서.
    
  }
  
}
