<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<body>
<div align="center">
<h3>회원가입</h3>
<form name="frm"> 
 <table border="1" style="text-align:left;width:800px;height:300px">
<tr>
<td>아이디</td>
<td><input type="text" name="userId" size="30"></td>
</tr>
<tr>
<td>비밀번호</td>
<td><input type="password" name="Password" size="30"></td>
</tr>
<tr>
<td>비밀번호 확인</td>
<td><input type="password" name="Password2" size="30"></td>
</tr>
<tr>
<td>이름</td>
<td><input type="text" name="Name" size="30"></td>
</tr>
<tr>
<td>이메일</td>
<td><input type="email" name="Email" size="30"></td>
</tr>
<tr>
<td>성별</td>
<td>
<input type="radio" name ="Gender" value="M" checked>남
<input type="radio" name ="Gender" value="F">여
</td>
</tr>


<tr>
<td>연락처</td>
<td>
<input type="text" name="Phone" size="30">
</td>
</tr>


 
<tr>
<td>취미</td>
<td>
<input type="checkbox" name ="memberHobby" value="축구" checked>축구
<input type="checkbox" name ="memberHobby" value="농구">농구
<input type="checkbox" name ="memberHobby" value="야구">야구
</td>
</tr>

 

 </table>
 <div>
        <input type="submit" value="등록">
        <a href="users.do?action=list">취소</a>
    </div>
 </form>
 </div>

</body>

</html>