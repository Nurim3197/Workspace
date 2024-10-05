<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Todo 항목 추가</h1>
	<form modelAttribute="writeTodoVO" method="post">
      <div class="grid">
        <label for="content">제목</label>
        <input id="content" type="text" name="content" value="${writeTodoVO.content}" />

        <label for="deadLine">이메일</label>
        <input id="deadLine" type="date" name="deadLine" value="${writeTodoVO.deadLine}"/>
		
        <div class="btn-group">
          <div class="right-align">
            <input type="submit" value="저장" />
          </div>
        </div>
      </div>
    </form>
</body>
</html>