<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정</title>

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
        수정 결과 화면
    </h1>
	
	<div>	   
		<a href="boards?action=list">목록</a>
		<a href="boards?action=view&bno=${param.bno}">상세보기</a>
	
		<form action="board" method="post">
			<input type="hidden" name="action" value="list">
			<input type="submit" value="목록">
		</form>     
		<form action="board" method="post">
			<input type="hidden" name="action" value="view">
			<input type="hidden" name="bno" value="${param.bno}">
			<input type="submit" value="상세보기">
		</form>     
	</div>

</body>
</html>