<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Todo목록</title>
    <link rel="stylesheet" type="text/css" href="/css/common.css" />
  </head>
  <body>
    <div class="container">
      <div>총 ${todoListVO.todoCnt}건의 게시글이 검색되었습니다.</div>
      <div class="header">
        <div>
          <table>
            <thead>
              <tr>
                <th>번호</th>
                <th>완료?</th>
                <th>TODO Subject</th>
                <th>기한</th>
                <th></th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              <c:choose>
                <c:when test="${not empty todoListVO.todoList}">
                  <c:forEach items="${todoListVO.todoList}" var="todo">
                    <tr>
                      <td>${todo.tdId}</td>
                      <c:choose>
                        <c:when test="${todo.tdCmplt eq 'Y'}">
                          <td>DONE</td>
                        </c:when>
                        <c:otherwise>
                          <td></td>
                        </c:otherwise>
                      </c:choose>
                      <td class="right">${todo.tdCntnt}</td>
                      <td>${todo.tdDdDt}</td>
                      <c:choose>
                        <c:when test="${todo.tdCmplt eq 'N'}">
                          <td>
                            <a href="/todo/complete/${todo.tdId}">완료</a>
                          </td>
                        </c:when>
                        <c:otherwise>
                          <td></td>
                        </c:otherwise>
                      </c:choose>
                      <td><a href="/todo/delete/${todo.tdId}">삭제</a></td>
                    </tr>
                  </c:forEach>
                </c:when>
                <c:otherwise>
                  <tr>
                    <td colspan="6">게시글이 없습니다.</td>
                  </tr>
                </c:otherwise>
              </c:choose>
            </tbody>
          </table>
          <div class="right">
            <input
              type="button"
              value="새 아이템 추가"
              onclick="window.location.href ='/todo/write';"
            />
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
