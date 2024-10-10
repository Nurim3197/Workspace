<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>게시글 수정</title>
    <link rel="stylesheet" type="text/css" href="/css/modify.css" />
  </head>
  <body>
  <div class="right-align">
			<ul class="horizontal-list">
				<c:choose>
					<c:when test="${empty sessionScope._LOGIN_USER_}">
						<li>
							<a href="/member/regist">회원가입</a>
						</li>
						<li>
							<a href="/member/login">로그인</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="special">
							${sessionScope._LOGIN_USER_.name}
							(${sessionScope._LOGIN_USER_.email})
						</li>
						<li>
							<a href="/member/logout">로그아웃</a>
						</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
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
