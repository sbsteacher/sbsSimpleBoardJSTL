<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
	html, body {
		height: 100%;
		padding: 0;
		margin: 0;
	}
	
	#container {
		height: 100%;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}
	
	.f_red {
		color: red;
	}
</style>
</head>
<body>
	<div id="container">
		<div>
			<form id="frm" action="login" method="post" onsubmit="return check()">
				<div>아이디 : <input type="text" name="uid" value="${uid}"></div>
				<div id="uidmsg" class="f_red"></div>
				<div>비밀번호 : <input type="password" name="upw"></div>
				<div id="upwmsg" class="f_red"></div>
				<div>
					<input type="submit" value="로그인">
					<button onclick="return goToJoin()">회원가입</button>
				</div>
			</form>
		</div>		
		<c:if test="${msg != null}">
		<div>
			${msg}
		</div>
		</c:if>
		${alert}
	</div>
	<script>
		// TODO  해결!!!
		<c:if test="${alert != null}">
			alert(${alert})
		</c:if>
	
		function goToJoin() {
			location.href='join'
			return false
		}
	
		function check() {
			/*
			var f_reds = document.getElementsByClassName('f_red')			
			f_reds[0].innerHTML = '진짜???'
			f_reds[1].innerHTML = 'ㅋㅋㅋㅋㅋ'
			return false;
			*/
			var uidmsg = document.getElementById('uidmsg')
			var upwmsg = document.getElementById('upwmsg')
			uidmsg.innerHTML = ''
			upwmsg.innerHTML = ''
			
			var isOk = true
			
			if(frm.uid.value == '') {
				frm.uid.focus()				
				uidmsg.innerHTML = '아이디를 입력해 주세요.'
				isOk = false
			}
			if(frm.upw.value == '') {
				frm.upw.focus()				
				upwmsg.innerHTML = '비밀번호를 입력해 주세요.'
				isOk = false
			}
			
			return isOk
			
		}
	</script>
</body>
</html>



