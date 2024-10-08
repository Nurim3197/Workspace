<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>게시글 수정</title>
    <link rel="stylesheet" type="text/css" href="/css/modify.css" />
  </head>
  <body>
    <h1>게시글 수정</h1>
    
    <form:form modelAttribute="modifyBoardVO" method="post" enctype="multipart/form-data" action="/board/modifyboard/${modifyBoardVO.id}">
      <div class="grid">
        <label for="subject">제목</label>
        <div>
        <form:errors path="subject" element="div" cssClass="error" />
        <input id="subject" type="text" name="subject" value="${modifyBoardVO.subject}"/>
        </div>

        <label for="content">내용</label>
        <textarea name="content" id="content">${modifyBoardVO.content}</textarea>
        <div class="btn-group">
          <div class="right-align">
            <input type="submit" value="수정" />
          </div>
        </div>
      </div>
    </form:form>
  </body>
</html>
