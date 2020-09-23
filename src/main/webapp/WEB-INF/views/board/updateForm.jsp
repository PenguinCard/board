<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<% request.setCharacterEncoding("utf-8"); %>   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="${path}/resources/css/updateForm.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script type="text/javascript" src="${path}/resources/js/common.js"></script>
</head>
<body>
	<header>
		<a href="${path}/board/"><img id="logo" src="${path}/resources/img/leaf.png"></a>
		<button type="button" onclick="loginForm('${path}');">로그인</button>
		<button type="button" onclick="memberForm('${path}');">회원가입</button>
	</header>
	<h1>수정하기</h1>
<form action="${path}/board/update.do" method="post" enctype="multipart/form-data">
	<table>
		<tbody>
			<tr>
				<td class="name">작성자</td>
				<td class="content"><input type="text" name="writer" value="${detail.writer}" readonly="readonly"/></td>
			</tr>
			<tr>
				<td class="name">제목</td>
				<td class="content"><input type="text" name="title" value="${detail.title}"/></td>
			</tr>
			<tr>
				<td class="name">내용</td>
				<td class="content"><textarea rows="10" cols="105" name="contents">${detail.contents}</textarea></td>
			</tr>
			<tr>
				<td class="name">파일첨부</td>
				<td class="content"><input type="file" name="File" value="${detail.imageFileName}"/></td>
			</tr>
			<tr>
				<td class="set" colspan="2">
					<input type="hidden" value="${detail.articleNo}" name="articleNo"/>
					<a href="${path}/board/detailBoard?articleNo=${detail.articleNo}"><button type="button">취소</button></a>
					<a href="${path}/board/delete.do/${detail.articleNo}"><button type="button">삭제</button></a>
					<button type="submit">완료</button>
				</td>
			</tr>
		</tbody>
	</table>
</form>
</body>
</html>