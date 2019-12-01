<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		justify-content: center;
		align-items: center;
	}
</style>
</head>
<body>
	<div id="container">
		<div>
			<form id="frm" action="login" method="post" onsubmit="return check()">
				<div>아이디 : <input type="text" name="uid" value=""></div>
				<div id="uidmsg"></div>
				<div>비밀번호 : <input type="password" name="upw"></div>
				<div id="upwmsg"></div>
				<div>
					<input type="submit" value="로그인">
				</div>
			</form>
		</div>
	</div>
	<script>
		function check() {
			if(frm.uid.value == '') {
				frm.uid.focus()
				var msg = document.getElementById('uidmsg')
				msg.innerHTML = '아이디를 입력해 주세요.'				
				return false
			}
			if(frm.upw.value == '') {
				frm.upw.focus()
				var msg = document.getElementById('upwmsg')
				msg.innerHTML = '비밀번호를 입력해 주세요.'
				return false
			}
		}
	</script>
</body>
</html>



