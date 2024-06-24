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

  <div><!--등록할 정보 입력받아 controller로 보내기.-->
    <form id="register-form" action="${contextPath}/register.do" method="post">
      <div><input type="text" name="name" id="name" placeholder="이름"></div>
      <div><input type="text" name="kor" id="kor" placeholder="국어"></div>
      <div><input type="text" name="eng" class="score" placeholder="영어"></div>
      <div><input type="text" name="math" class="score" placeholder="수학"></div>

      <div><button type="submit">작성완료</button></div>
      <button type="button" onclick="history.back()">다시작성</button>
      <div><button type="button" onclick="listgo()">목록보기</button></div>
    </form>
  </div>
  
  <script>//입력확인
  //스코어 부분 걱정스럽다.
    const registerForm = document.getElementById('register-form');
    const name = document.getElementById('name');
    const score = document.getElementsByClassName('score');
    
    const checkSubmit = ()=>{      
      registerForm.addEventListener('submit', (evt)=>{
        if(name.value === ''){
          alert('이름은 반드시 입력해야 합니다.');
          name.focus();
          evt.preventDefault();
          return;
        }
        const regscore = /^$|[^0-9]/;
        if(regscore.each.test(score.value)){
          alert('점수는 숫자만 입력할 수 있습니다.');
          score.focus();
          evt.preventDefault();
          return;
        }
      })
    }
    
    checkSubmit();
    ///////////////////////////////////////////아래 함수 맞아??ㅋㅋㅋㅋ
    const listgo = ()=>{
      location.href = '${contextPath}/list.do';
    }


  </script>

</body>
</html>