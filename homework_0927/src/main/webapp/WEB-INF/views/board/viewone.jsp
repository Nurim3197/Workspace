<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>게시물 내용 조회</title>
    <link rel="stylesheet" type="text/css" href="/css/viewstyle.css" />
  </head>
  <body>
    <h1>게시글 작성</h1>
    <div class="grid">
      <label for="subject">제목</label>
      <div>${boardVO.subject}</div>

      <label for="email">이메일</label>
      <div>${boardVO.email}</div>

      <label for="viewCnt">조회수</label>
      <div>${boardVO.viewCnt}</div>

      <label for="crtDt">등록일</label>
      <div>${boardVO.crtDt}</div>

      <label for="mdfyDt">수정일</label>
      <div>${boardVO.mdfyDt}</div>

      <label for="content">내용</label>
      <div>${boardVO.content}</div>
    </div>
  </body>
</html>
