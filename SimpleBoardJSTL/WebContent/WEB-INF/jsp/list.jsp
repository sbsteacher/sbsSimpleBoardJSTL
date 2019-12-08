<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>보드 리스트</h1>
<style>
	#paging {
		margin-top: 20px;
		display: flex;
		justify-content: center;
		
	}
	
	.pageItem {
		width: 20px;
	}
	
	.pageSelected {
		color: red;
		font-weight: bold;
	}
	
	.hands {
		cursor: pointer;
	}

</style>
<c:choose>
	<c:when test="${list == null}">	
		<div>자료가 없습니다.</div>		
	</c:when>
	<c:otherwise>
		<table>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>등록일시</th>
				<th>조회수</th>
			</tr>		
			<c:forEach var="item" items="${list}">
				<tr class="hands" onclick="goToDetail(${item.i_board})">
					<td class="center">${item.i_board}</td>
					<td>${item.title }</td>
					<td class="center">${item.regdatetime }</td>
					<td class="center">${item.cnt }</td>
				</tr>
			</c:forEach>
		</table>		
		<c:if test="${pagingCnt > 1}">
		<div id="paging">
			<c:forEach var="page" begin="1" end="${pagingCnt}">
				
				<c:choose>
					<c:when test="${page ==  selectedPage}">
						<div class="pageItem pageSelected">${page}</div>	
					</c:when>
					<c:otherwise>
						<div class="pageItem">
							<a href="list?page=${page}">${page}</a>
						</div>
					</c:otherwise>
				</c:choose>				
			</c:forEach>
		</div>
		</c:if>
	</c:otherwise>
</c:choose>
<script>
	function goToDetail(i_board) {
		console.log('i_board : ' + i_board)		
		location.href = 'detail?i_board=' + i_board
	}
</script>

















