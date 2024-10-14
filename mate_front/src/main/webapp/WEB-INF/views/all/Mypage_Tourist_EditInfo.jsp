<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" type="text/css" href="/css/Mypage_EditInfo.css" />
    <title>Mypage_Tourist_EditInfo</title>
  </head>
  <body>
    <div class="grid">
      <div class="header">
        <!-- header 공통파일 -->
        <jsp:include page="../header.jsp"></jsp:include>
      </div>
      <div class="content">
        <!-- side-bar 공통파일 -->
        <!--<jsp:include page="../sidebar.jsp"></jsp:include>-->
        <!-- 사이드바 -->
        <div class="sidebar-left">
          <ul class="sidebar-left-list">
            <li class="disabled">캘린더</li>
            <li class="disabled">즐겨찾기</li>
            <li class="disabled">나의 등록 투어</li>
            <li class="disabled">나의 리뷰</li>
            <li class="disabled">결제 내역</li>
            <li class="active">내 정보 수정</li>
            <li class="disabled">메세지</li>
          </ul>
        </div>

        <!-- 본문 내용 -->
        <div class="main-content">
          <div class="info-section">
            <h2>개인 정보 관리</h2>
            <div class="info-item">
              <h3>Rainymoon31</h3>
              <div class="factor">rainymoon31@gmail.com</div>
              <div class="info-item">
                <div class="info-row">
                  <div class="factor">+82 10-9036-9025</div>
                  <button class="edit-btn">수정</button>
                </div>
                <div class="info-row">
                  <div class="factor">비밀번호 변경</div>
                  <button class="edit-btn">수정</button>
                </div>
              </div>
            </div>
          </div>

          <div class="payment-section">
            <h2>결제 수단 관리</h2>
            <div class="info-item">
              <div class="info-row">
                <div class="factor">rainymoon31@gmail.com</div>
                <button class="edit-btn">수정</button>
              </div>
            </div>
          </div>

          <div class="guide-section">
            <div class="info-row">
              <h2>가이드 등록</h2>
              <button class="edit-btn">등록</button>
            </div>
          </div>
        </div>
      </div>
      <div class="footer">
        <!-- footer 공통파일 -->
        <jsp:include page="../footer.jsp"></jsp:include>
      </div>
    </div>
  </body>
</html>
