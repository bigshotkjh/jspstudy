package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import dto.BookDTO;

/* DAO : Database Access Object (Singleton Pattern) */

public class BookDAO {

  /* Singleton Pattern */
  private BookDAO() { }  // BookDAO 내부에서만 new BookDAO() 호출 가능함
  private static BookDAO bookDAO = new BookDAO();  // BookDAO 객체를 하나 만들어 둠
  public static BookDAO getInstance() {
    return bookDAO;  // 만들어 둔 BookDAO 객체를 반환함
  }
  
  /* Field : 모든 메소드가 사용할 공통 요소 */
  private Connection conn;       // 접속 담당
  private PreparedStatement ps;  // 쿼리 실행
  
  /* Database Connection */
  public void connection() {
    try {
      // ojdbc6.jar 에 포함되어 있는 오라클 드라이버 클래스 실행
      Class.forName("oracle.jdbc.OracleDriver");
      // 접속 정보
      String url = "jdbc:oracle:thin:@localhost:1521:xe";
      String user = "GREEN";
      String password = "GREEN";
      // 접속 (접속되면 java.sql.Connection 객체 반환됨)
      conn = DriverManager.getConnection(url, user, password);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /* Database Resource Close */
  public void close() {
    try {
      if(conn != null) conn.close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /* 여기서부터 실제 Database 를 처리하는 메소드 */
  
  /* 모든 책 조회하기 */
  
  
  /* 특정 책 조회하기 */
  
  
  /* 신규 책 추가하기 */
  // 매개변수 : BookDTO book (신규 책 정보가 저장되어 있다.)
  // 반환타입 : int (테이블에 추가된 행의 개수가 반환된다. 즉 1은 성공, 0은 실패를 의미한다.)
  public int insertBook(BookDTO book) throws Exception {
    
    int result = 0;
    
    connection();
    
    String sql = "INSERT INTO book_t(book_no, title, author, price) VALUES(book_seq.nextval, ?, ?, ?)";  // ? 자리에 변수 값이 들어갈 예정
    
    ps = conn.prepareStatement(sql);   // 미리 준비한 statement(쿼리문) 전달
    
    ps.setString(1, book.getTitle());  // 1번째 ?에 String title 넣기
    ps.setString(2, book.getAuthor()); // 2번째 ?에 String author 넣기
    ps.setInt(3, book.getPrice());     // 3번째 ?에 int price 넣기
    
    result = ps.executeUpdate();       // 쿼리 실행 후 실행 결과(1 또는 0) 반환
    
    close();
    
    return result;
    
  }
  
  /* 기존 책 수정하기 */
  
  
  /* 기존 책 삭제하기 */
  
  
}
