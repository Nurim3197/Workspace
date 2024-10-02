<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="/css/common.css" />
    <script type="text/javascript" src="/js/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="/js/member/memberregist.js"></script>
  </head>
  <body>
    <h1>회원가입</h1>
    <form method="post">
      <div class="grid">
        <label for="name">이름</label>
        <div>
          <input id="name" type="text" name="name" />
        </div>

        <label for="email">이메일</label>
        <div>
          <input id="email" type="email" name="email" />
        </div>

        <label for="password">패스워드</label>
        <div>
          <input id="password" type="password" name="password" />
        </div>

        <label for="passwordcheck">패스워드 확인</label>
        <div>
          <input id="passwordcheck" type="password" name="passwordcheck" />
        </div>
      </div>
      <div class="btn-group">
        <div class="right-align">
          <input type="submit" value="저장" />
        </div>
      </div>
    </form>
  </body>
</html>
