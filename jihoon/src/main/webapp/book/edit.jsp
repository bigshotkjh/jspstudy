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
  <!--수정할 책 번호를 가지고 DB에 갔다 해당 책정보 가지고 온 페이지 이며 수정을 위한 페이지.-->
  <div><!--수정할 내용 입력받아서 form으로 포장해 modify로 보내기.-->
    <form id="edit-form" action="${contextPath}/modify.do" method="post">
      <!--수정할 내용 입력 후 올바른 입력인지 script로 체크하고 controller로 이동한다.-->
      <input type="hidden" name="bookNo" value="${book.book_no}">
      <!--번호는 pk라 수정할 수 없어. 숨겨두고 있고 modify로 이동해서 db에 가서 책 특정할 때
       번호 써야 하니 일단 걍 가지고는 있어야해.-->
      <div><input type="text" name="title" id="title" value="${book.title}"></div>
      <div><input type="text" name="author" value="${book.author}"></div>
      <div><input type="text" name="price" id="price" value="${book.price}"></div>
      <div>
        <button type="submit">수정하기</button>
        <!--수정버튼에 아래script보면 이벤트리스너가 달려 있는다-->
        <button type="button" onclick="history.back()">취소하기</button>
      </div>
    </form>
  </div>
  
  <script>
  //수정하기 위한 내용을들 올바르게 입력했는지 체크하는 script.
    const editForm = document.getElementById('edit-form');//필요한거 모두 DOM처리.
    const title = document.getElementById('title');
    const price = document.getElementById('price');
    
    const checkSubmit = ()=>{    //체크하는 함수  
      editForm.addEventListener('submit', (evt)=>{ //폼에 submit시 함수 돌게.
        if(title.value === ''){//제목입력했나?
          alert('제목은 반드시 입력해야 합니다.');
          title.focus();//제목입력하라고 입력창에 focus
          evt.preventDefault();//이벤트  중지시켜.
          return;
        }
        const regPrice = /^$|[^0-9]/;
        if(regPrice.test(price.value)){//숫자 입력했나 체크
          alert('가격은 숫자만 입력할 수 있습니다.');
          price.focus();
          evt.preventDefault();
          return;
        }
      })
    }
    
    checkSubmit();
    
  </script>

</body>
</html>