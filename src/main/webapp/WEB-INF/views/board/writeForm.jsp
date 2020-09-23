<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<% request.setCharacterEncoding("utf-8"); %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<c:choose>
		<c:when test="${parentNo eq 0}">
			<title>게시글 쓰기</title>
		</c:when>
		<c:otherwise>
			<title>답글 쓰기</title>
		</c:otherwise>
	</c:choose>
	<link rel="stylesheet" href="${path}/resources/css/writeForm.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script type="text/javascript" src="${path}/resources/js/writeForm.js"></script>
	<script type="text/javascript" src="${path}/resources/js/common.js"></script>
</head>
<body>
	<div class="wrap">
		<header>
			<a href="${path}/board/"><img id="logo" src="${path}/resources/img/leaf.png"></a>
			<c:choose>
				<c:when test="${not empty sessionScope.memInfo}">
					<button type="button" onclick="logout('${path}');">로그아웃</button>
					<button type="button" onclick="chgInfo('${path}')">회원정보</button>
				</c:when>
				<c:otherwise>
					<button type="button" onclick="loginForm('${path}');">로그인</button>
					<button type="button" onclick="memberForm('${path}');">회원가입</button>
				</c:otherwise>
			</c:choose>
		</header>
		<c:choose>
			<c:when test="${parentNo eq 0}">
				<h1>게시글 쓰기</h1>
			</c:when>
			<c:otherwise>
				<h1>답글 쓰기</h1>
			</c:otherwise>
		</c:choose>
		
		<form action="${path}/board/write.do" method="post" name="board" enctype="multipart/form-data">
			<table>
				<tbody>
					<tr>
						<td class="name">작성자</td>
						<td class="content">
							<input type="text" name="writer" value="${sessionScope.memInfo.m_id}" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td class="name">제목</td>
						<td class="content"><input type="text" name="title" id="title" /></td>
					</tr>
					<tr>
						<td class="name">내용</td>
						<td class="content">
							<textarea rows="10" cols="105" name="contents"></textarea>
						</td>
					</tr>
					<tr>
						<td class="name">파일첨부</td>
						<td class="content"><input type="file" name="File" onchange="readURL(this);" /></td>
					</tr>
					<tr>
						<td class="name">미리보기</td>
						<td class="content">
							<img id="preview" height="300px" src="#" />
						</td>
					</tr>
					<tr>
						<td class="set" colspan="2">
							<button type="button" onclick="backToList();">취소</button>
							<button type="button" onclick="submitBoard();">등록</button>
						</td>
					</tr>
				</tbody>
			</table>
			<input type="hidden" name="parentNo" value="${parentNo}"/>
		</form>
	</div>
</body>
</html>