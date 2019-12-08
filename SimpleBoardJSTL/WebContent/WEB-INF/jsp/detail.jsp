<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="data == null">
	<div>
		자료가 없습니다.
	</div>
	<% return; %>
</c:if>
<div>
	<table>
		<tr>
			<th>제목</th>
			<td colspan="4">${data.title}</td>
			<td>좋아요: ${data.fav_cnt}</td>
		</tr>
		<tr>
			<td>날짜</td>
			<td>${data.regdatetime }</td>
			<td>조회수</td>
			<td>${data.cnt }</td>
			<td>작성자</td>
			<td>${data.nm }</td>
		</tr>
		<tr>
			<td colspan="6">${data.content }</td>
		</tr>
		<c:if test="${data.uid == loginUser.uid}">
			<tr>
				<td colspan="3">
					<a href="del?i_board=${data.i_board}">삭제</a>
				</td>
				<td colspan="3">
					<a href="mod?i_board=${data.i_board}">수정</a>
				</td>
			</tr>
		</c:if>
		<c:if test="${msg != null}">
			<tr>
				<td>메시지</td>
				<td colspan="3">${msg }</td>
			</tr>
		</c:if>
	</table>	
</div>










