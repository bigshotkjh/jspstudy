package test;

import static org.junit.Assert.*;

import org.junit.Test;

import dao.BookDAO;
import dto.BookDTO;

public class BookDAOTest {

  @Test
  public void 책넣기테스트() throws Exception {
    
    BookDAO bookDAO = BookDAO.getInstance();
    
    BookDTO book = BookDTO.builder()
        .title("테스트제목")
        .author("테스트저자")
        .price(123456)
        .build();
     
    // bookDAO.insertBook(book) 결과가 1이면 테스트를 성공한다.
    assertEquals(1, bookDAO.insertBook(book));
    
  }

}
