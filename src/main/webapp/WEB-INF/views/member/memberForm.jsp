<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${path}/resources/css/memberForm.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script type="text/javascript" src="${path}/resources/js/screen.js"></script>
	<script type="text/javascript" src="${path}/resources/js/memberForm.js"></script>
	<title>회원가입</title>	
</head>
<body>
	<div class="wrap">
		<form action="#" name="memberInfo">
			<table>
				<tbody>
					<tr style="position: relative;">
						<td colspan="3">
							<a href="${path}/board/"><img src="${path}/resources/img/leaf2.png" id="logo" /></a>
							<h1>회원가입</h1>
						</td>
					</tr>
					<tr>
						<td class="col1">아 이 디</td>
						<td class="col2">
							<input type="text" id="id" name="id"/>
							<div id="text"></div>
						</td>
						<td class="col3"><button type="button" onclick="idOverlap();">중복확인</button></td>
					</tr>
					<tr>
						<td class="col1">비밀번호</td>
						<td class="col2">
							<input type="password" id="pw" name="pw" onchange="pwAlgorithm();"/>
							<div id="pwchkalert"></div>
						</td>
						<td class="col3"></td>
					</tr>
					<tr>
						<td class="col1">비밀번호확인</td>
						<td class="col2">
							<input type="password" id="pwchk" onchange="chkPw();"/>
							<div id="pwtext"></div>
						</td>
						<td class="col3"></td>
					</tr>
					<tr>
						<td class="col1">이&nbsp;&nbsp;름</td>
						<td class="col2"><input type="text" id="name" name="name"/></td>
						<td class="col3"></td>
					</tr>
					<!--  <tr>
						<td>비밀번호확인</td>
						<td><input type="password" /></td>
					</tr> -->
					<tr>
						<td class="col1">휴대번호</td>
						<td class="col2" colspan="2">
							<span> 
								<select name="phone1">
									<option value="010">010</option>
									<option value="019">019</option>
									<option value="011">011</option>
									<option value="016">016</option>
									<option value="017">017</option>
								</select> -
								<input type="text" name="phone2" id="phone2"/> -
								<input type="text" name="phone3" id="phone3"/>
							</span>
						</td>
					</tr>
					<tr>
						<td class="col1">이 메 일</td>
						<td class="col2" colspan="2"><span><input id="email1" type="text" name="email1"/> @ <input id="email2" type="text" name="email2"/></span></td>
					</tr>
					<tr>
						<td id="last" colspan="3">
							<input class="chk" type="button" value="취소" onclick="backToList('${path}')">
							<input class="chk" type="submit" value="확인" onclick="insertMember('${path}')">
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>