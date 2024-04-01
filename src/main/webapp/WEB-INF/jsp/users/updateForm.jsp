<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>수정화면</title>
    <style>
        label {
            display: inline-block;
            width: 120px;
        }
        input {
            margin-bottom: 10px; 
        }
    </style>
</head>
<body>
    <h1>
        회원정보 수정양식 
    </h1>
    <form id =rForm  action="users.do" method="post">
    	<input type="hidden" name="action" value="update">
        <label>아이디 : </label> <input type="text" id="userid" name="userid" value="${users.userid}" readonly="readonly"> <br/>
        <label>비밀번호 : </label>   <input type="password" id="password" name="password" required="required"><br/>
        <label>비밀번호확인 : </label>   <input type="password" id="password2" name="password2" required="required"><br/>
        <label>이름 : </label>   <input type="text" id="name" name="name" value="${users.name}"><br/>
        <label>성별: </label>    <input type="text" id="gender" name="gender" value="${users.gender}"><br/>
        <label>이메일: </label>  <input type="text" id="useremail" name="email" value="${users.email}"><br/>
         <label>전화번호: </label>  <input type="text" id="phone" name="phone" value="${users.phone}"><br/>
    <div>
        <input type="submit" value="수정">
        <a href="users.do?action=view&userid=${users.userid}">취소</a>
    </div>
    
    
    
    </form>
    
    
    <script type="text/javascript" src="<c:url value='/resource/js/common.js'/>"></script>
    
    <script type="text/javascript">	
const rForm = document.getElementById("rForm");
rForm.addEventListener("submit", e => {
	//서버에 form data를 전송하지 않는다 
	e.preventDefault();
	
	myFetch("users.do", "rForm", json => {
		if(json.status == 0) {
			//성공
			alert("아이디 정보 수정을 성공 하였습니다");
			location = "users.do?action=view&userid=" + userid.value;
		} else {
			alert(json.statusMessage);
		}
	});
});
</script>
    
</body>
</html>
