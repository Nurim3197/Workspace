<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>게시글 작성하기</title>
    <link rel="stylesheet" type="text/css" href="/css/common.css" />
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
    <h1>게시글 작성</h1>
    
    <form:form modelAttribute="writeBoardVO" method="post" enctype="multipart/form-data">
      <div class="grid">
        <label for="subject">제목</label>
        <div>
        <form:errors path="subject" element="div" cssClass="error"/>
        <input id="subject" type="text" name="subject" value="${writeBoardVO.subject}" />
        </div>
		
        <label for="file">첨부파일</label>
        <input id="file" type="file" name="file" />

        <label for="content">내용</label>
        <textarea id="content" name="content">${writeBoardVO.content}</textarea>


        <div class="btn-group">
          <div class="right-align">
            <input type="submit" value="저장" />
          </div>
        </div>
      </div>
    </form:form>
  </body>
</html>
