<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User insert form</title>
    <style>
        label {
            display: inline-block;
            width: 200px;
        }
        input {
            margin-bottom: 10px; 
        }
    </style>
</head>
<body>
    <h1>
        회원상세보기
    </h1>
   
      <label>아이디 : ${users.userid}</label> <br/>
      <label>이름 : ${users.name}</label><br/>
      <label>성별: ${users.gender}</label><br/>
      <label>이메일: ${users.email}</label><br/>
      <label>전화번호: ${users.phone}</label><br/>
 <script type="text/javascript" src="<c:url value='/resource/js/common.js'/>"></script>
<script>
function jsDelete() {
	if (confirm("정말로 삭제하시겠습니까?")) {
		//서버의 URL을 설정한다 
		action.value = "delete";
	
		//서버의 URL로 전송한다 
		viewForm.submit();
	}
}

function jsUpdateForm() {
	if (confirm("정말로 수정하시겠습니까?")) {
		//서버의 URL을 설정한다 
		action.value = "updateForm";
	
		//서버의 URL로 전송한다 
		viewForm.submit();
	}	
}
</script>
<!-- 두개의 폼을 하나로 합치는 방법 , js를 사용하여 처리  -->
	<form id="viewForm" method="post" action="users.do">
		<input type="hidden" id="action" name="action" value="">
		<input type="hidden" name="userid" value="${users.userid}">
		<input type="button" value="삭제" onclick="jsDelete()">
		<input type="button" value="수정" onclick="jsUpdateForm()">
	</form>     
 
	<form action="users.do" method="post">
		<input type="hidden" name="action" value="delete">
		<input type="hidden" name="userid" value="${user.userid}">
		<input type="submit" value="삭제">
	</form>     
	
	<form action="users.do" method="post">
		<input type="hidden" name="action" value="updateForm">
		<input type="hidden" name="userid" value="${user.userid}">
		<input type="submit" value="수정">
	</form>     
	
    <div>
        <a href="users.do?action=list">목록</a>
        <a href="users.do?action=updateForm&userid=${user.userid}">수정</a>
        <a href="users.do?action=delete&userid=${user.userid}">삭제</a>
    </div>
</body>
</html>

