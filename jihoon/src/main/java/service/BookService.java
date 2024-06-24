package service;

import common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;

public interface BookService {//서비스할 메소드들이 있는 인터페이스
  ActionForward getBooks(HttpServletRequest request);
  ActionForward getBookByNo(HttpServletRequest request);
  ActionForward registerBook(HttpServletRequest request);
  ActionForward removeBook(HttpServletRequest request);
  ActionForward editBook(HttpServletRequest request);
  ActionForward modifyBook(HttpServletRequest request);
}
