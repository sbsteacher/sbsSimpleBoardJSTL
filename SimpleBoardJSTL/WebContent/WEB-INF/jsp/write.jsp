<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<h1>글쓰기 화면</h1>
	<a href="list?page=${selectedPage}">
		<button>리스트로 돌아가기</button>
	</a>
	<form id="frm" action="write" method="post" onsubmit="return check()">
		<div>
			제목 : <input type="text" name="title" id="title"> 
		</div>
		<div>
			내용
			<textarea name="content" id="cont"></textarea>	
		</div>
		<input type="submit" value="글등록">	
	</form>
	<c:if test="${msg != null }">
		<div>
			${msg }
		</div>
	</c:if>
	<script>
		function check() {			
			if(frm.title.value == '') {
				alert('제목을 입력해 주세요!!!')
				title.focus()
				return false
			} else if(frm.content.value.length == 0) {
				alert('내용을 입력해 주세요!!!')
				content.focus()
				return false
			}
		}
	</script>