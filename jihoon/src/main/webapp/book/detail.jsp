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
<title>Insert title here</title>
</head>
<body>

  <div><!--상세정보 요청한거 화면에 뿌리기.-->
    <div>번호 : ${book.book_no}</div><!--걍 다른 작업없이 전달되어온 forward에서 빼서 뿌림. -->
    <div>제목 : ${book.title}</div>
    <div>저자 : ${book.author}</div>
    <div>가격 : ${book.price}</div>
  </div>
  
  <div><!--걍 메뉴 버튼. .do들은 다들 controller로!!-->
    <a href="${contextPath}/list.do">목록보기</a><!--다시 전체목록보기-->
    <a href="${contextPath}/edit.do?bookNo=${book.book_no}">편집</a><!--책번호 받아서 수정-->
    <a href="javascript:fnDeleteBook()">삭제</a><!--삭제하기. 아래에 스크립트 있겠네.-->
  </div>
  <script>
  
    const fnDeleteBook = ()=>{
      if(confirm('현재 책을 완전히 삭제할까요?')){ //선택 알림창 함 보여주고 
        location.href = '${contextPath}/delete.do?bookNo=${book.book_no}';//책번호를 delete.do로 보내.
      }
    }
    
    const modifyMessage = '${modifyMessage}';//수정끝나고 메세지 출력.
    if(modifyMessage !== '')//메세지 있으면
      alert(modifyMessage);//출력
    
  </script>

</body>
</html>