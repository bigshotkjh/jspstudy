<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <div>
  
    <form method="post" action="/ex1.jsp">
  
      <!-- 입력 상자 -->
      <div>아이디<br>
        <input type="text" name="id" placeholder="아이디">@naver.com
      </div><br>
      <div>비밀번호<br>
        <input type="password" name="pw" placeholder="비밀번호">
      </div><br>
      <div>비밀번호 재확인<br>
        <input type="password" name="checkp" placeholder="비밀번호 재확인">
      </div><br>
      <div>이름<br>
        <input type="text" name="name">
      </div><br>
      <div>생년월일<br>
        <input type="text" name="year" placeholder="년(4)자">
        <select name="month">
          <option >월</option>
          <option value="01">01</option>
          <option value="02">02</option>
          <option value="03">03</option>
          <option value="04">04</option>
          <option value="05">05</option>
          <option value="06">06</option>
          <option value="07">07</option>
          <option value="08">08</option>
          <option value="09">09</option>
          <option value="10">10</option>
          <option value="11">11</option>
          <option value="12">12</option>
        </select>
        <input type="text" name="day" placeholder="일">
      </div><br>
      <div>성별<br>
        <select name="month">
          <option >성별</option>
          <option value="man">man</option>
          <option value="woman">woman</option>
          <option value="none">none</option>
        </select>
      </div><br>
      <div>본인 확인 이메일(선택)<br>
        <input type="text" name="email">
      </div><br>
      <div>휴대전화<br>
        <select name="country">
          <option value="kor">대한민국 +82</option>
        </select><br>
        <input type="text" name="phone" placeholder="전화번호 입력">
        <button>인증번호 받기</button><br>
        <input type="text" name="checkp" placeholder="인증번호 입력">
      </div><br>
      <button type="submit">가입하기</button>
  
    </form>
    
  
  </div>
</body>
</html>