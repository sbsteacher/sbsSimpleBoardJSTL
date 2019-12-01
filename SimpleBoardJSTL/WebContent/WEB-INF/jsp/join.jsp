<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
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
			<form id="frm" action="join" method="post" onsubmit="return check()">
				<div>아이디 : <input type="text" name="uid" value="${uid}"></div>
				<div id="uidmsg" class="f_red"></div>
				
				<div>비밀번호 : <input type="password" name="upw"></div>
				<div>확인비밀번호 : <input type="password" name="reupw"></div>
				<div id="upwmsg" class="f_red"></div>
				
				<div>이름 : <input type="text" name="nm" value="${nm }"></div>
				<div id="nmmsg" class="f_red"></div>
				
				<div>
					<input type="submit" value="회원가입">
					<button onclick="return goToLogin()">로그인 화면</button>
				</div>
			</form>
		</div>		
		<c:if test="${msg != null}">
		<div>
			${msg}
		</div>
		</c:if>
	</div>
	<script>
		function goToLogin() {
			location.href='login'
			return false
		}
	
		function check() {			
			var uidmsg = document.getElementById('uidmsg');
			var upwmsg = document.getElementById('upwmsg');
			var nmmsg = document.getElementById('nmmsg');
			
			uidmsg.innerHTML = '';
			upwmsg.innerHTML = '';
			nmmsg.innerHTML = '';
			
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
			if(frm.upw.value != frm.reupw.value) {
				frm.upw.focus()				
				upwmsg.innerHTML = '비밀번호를 확인해 주세요.'
				isOk = false
			}
			if(frm.nm.value == '') {
				frm.nm.focus()				
				nmmsg.innerHTML = '이름을 입력해 주세요.'
				isOk = false
			}
			return isOk
			
		}
	</script>
</body>
</html>



