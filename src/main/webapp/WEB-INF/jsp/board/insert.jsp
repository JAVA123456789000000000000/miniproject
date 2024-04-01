<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등록</title>

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
        등록 결과 화면
    </h1>
	
	<div>	   
		<a href="board.do?action=list">목록</a>
	
		<form action="board" method="post">
			<input type="hidden" name="action" value="list">
			<input type="submit" value="목록">
		</form>     
	</div>
	
</body>
</html>
