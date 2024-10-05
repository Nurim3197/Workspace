<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/css/write.css" />
  </head>
  <body>
    <div class="container">
      <h1>Todo 항목 추가</h1>
      <form modelAttribute="writeTodoVO" method="post">
        <table>
          <tbody>
            <tr>
              <td class="content"><label for="content">제목</label></td>
              <td class="input-box">
                <input
                  id="content"
                  type="text"
                  name="content"
                  value="${writeTodoVO.content}"
                />
              </td>
            </tr>
            <tr>
              <td class="deadline"><label for="deadLine">이메일</label></td>
              <td>
                <input
                  id="deadLine"
                  type="date"
                  name="deadLine"
                  value="${writeTodoVO.deadLine}"
                />
              </td>
            </tr>
          </tbody>
        </table>
        <div class="right">
          <input type="submit" value="저장" />
        </div>
      </form>
    </div>
  </body>
</html>
