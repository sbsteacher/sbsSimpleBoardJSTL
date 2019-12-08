<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:if test="${data == null}">
	<div>
		자료가 없습니다.
	</div>	
</c:if>
<c:if test="${data != null}">
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
					<td colspan="5">${msg }</td>
				</tr>
			</c:if>
		</table>	
	</div>
	
	<div class="mt-30">
		<!-- action을 생략하면 현재 주소창에 적혀있는 주소로 post를 날립니다. -->
		<form method="post" id="frm" onsubmit="return check()">
			<input type="hidden" name="i_comment" value="0">
			<input type="hidden" name="i_board" value="${i_board }">
			<div>			
				댓글 : <input type="text" name="cmt">
				<input type="submit" value="댓글달기">
			</div>
		</form>
	</div>
	
	<c:if test="${fn:length(cmts) > 0}">
		<div>
			<table>
				<tr>
					<th>번호</th>
					<th>댓글</th>
					<th>작성자</th>
					<th>등록일시</th>
					<th>삭제</th>
				</tr>		
				<c:forEach var="item" items="${cmts}">
					<tr>
						<td>${item.i_comment}</td>
						<td>${item.cmt}</td>
						<td>${item.nm}</td>
						<td>${item.r_datetime}</td>
						<td>
							<c:if test="${loginUser.uid == item.uid }">
								<button onclick="delCmt(${item.i_comment})">삭제</button>	
							</c:if>							
						</td>
					</tr>
				</c:forEach>	
			</table>
		</div>
	</c:if>
</c:if>


<script>
	//댓글삭제
	function delCmt(i_cmt) {
		frm.i_comment.value = i_cmt
		frm.submit()
	}
	
	function check() {	
		if(frm.comment.value == '') {
			alert('댓글 내용이 없습니다')
			frm.comment.focus()
			return false
		}
	}
</script>










