<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>책목록</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<style>
  div {
    box-sizing: border-box;
  }
  .books {
    overflow: hidden;
    width: 360px;
    height: 800px;
    border: 1px solid gray;
    padding: 10px;
    margin: auto;
  }
  .book {
    float: left;
    width: 130px;
    height: 130px;
    border: 1px solid orange;
    margin: 10px;
    cursor: pointer;
  }
  .book:hover {
    background-color: beige;
  }
</style>
</head>
<body>
  <div>학생관리</div>

  <div>
    <a href="${contextPath}/write.do">신규학생등록</a><!--마찬가지로 컨트롤러로 가자.-->
  </div>

  <!--평균 조회할 수 있는 거 만들어야해!!//////////////////////////////////////////////////-->
  <!--상위3명 나오게 해야해. 만들어야해!!//////////////////////////////////////////////////-->

  <div class="books"> <!--전체리스트를 보여줌.-->
    <c:forEach var="book" items="${books}"><!--list.do로 받아온 리스트인 북스를 풀어낸다.-->
      <div class="book" data-book-no="${book.book_no}">
        <div>${book.book_no}</div>
        <div>${book.title}</div>
      </div>
    </c:forEach>
  </div>
  
  <script>
  
    $('.book').on('click', evt=>{//리스트중에 책눌리면 작동.
      location.href = '${contextPath}/detail.do?bookNo=' + evt.currentTarget.dataset.bookNo;
    })//책의 상세표시로 넘어간다. 책넘버를 parameter로 가져가기 위해 data-에 넣어둠. book-no가  스크립트에선  bookNo로 표시. 
    //여튼 번호들고 컨트롤러로 이동.

    const deleteResult = '${deleteResult}';//삭제 결과 받아온거 넣기
    if(deleteResult !== ''){// 확인하고
      if(deleteResult === '1'){ // 상황따라 안내문.
        alert('해당 책이 삭제되었습니다.');
      } else {
        alert('책이 삭제되지 않았습니다.');
      }
    }
    
  </script>

</body>
</html>