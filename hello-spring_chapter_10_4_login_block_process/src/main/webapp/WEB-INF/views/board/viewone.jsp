<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>게시물 내용 조회</title>
    <link rel="stylesheet" type="text/css" href="/css/viewstyle.css" />
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
    <h1>게시글</h1>
    <div class="grid">
      <label for="subject">제목</label>
      <div>${boardVO.subject}</div>

      <label for="email">이메일</label>
      <div>${boardVO.email}</div>

      <label for="viewCnt">조회수</label>
      <div>${boardVO.viewCnt}</div>

      <label for="originFileName">첨부파일</label>
      <div>
        <a href="/board/file/download/${boardVO.id}">
          ${boardVO.originFileName}
        </a>
      </div>

      <label for="crtDt">등록일</label>
      <div>${boardVO.crtDt}</div>

      <label for="mdfyDt">수정일</label>
      <div>${boardVO.mdfyDt}</div>

      <label for="content">내용</label>
      <div>${boardVO.content}</div>
      <div class="btn-group">
        <div class="right-align">
          <a href="/board/modifyboard/${boardVO.id}">수정</a>
          <a href="/board/delete/${boardVO.id}">삭제</a>
        </div>
      </div>
    </div>
  </body>
</html>
