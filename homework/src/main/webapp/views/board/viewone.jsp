<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 내용 조회</title>
</head>
<body>
	ID : ${boardVO.id}
	<br/>
	SUBJECT : ${boardVO.subject}
	<br/>
	CONTENT : ${boardVO.content}
	<br/>
	EMAIL : ${boardVO.email}
	<br/>
	VIEW_CNT : ${boardVO.viewCnt}
	<br/>
	CRT_DT : ${boardVO.crtDt}
	<br/>
	MDFY_DT : ${boardVO.mdfyDt}
	<br/>
	FILE_NAME : ${boardVO.name}
	<br/>
	ORIGIN_FILE_NAME : ${boardVO.file_name}
</body>
</html>