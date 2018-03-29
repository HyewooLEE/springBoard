<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>목록</title>
</head>
<body>
	test다 적용되냐vfgjvjhvgjhvgbhv
	<form action="writeAction.do" method="POST" align="center" enctype="multipart/form-data">
	<c:if test="${select.no != null}">
	<input type="hidden" name="no" value="${select.no}">
	</c:if>
	<table border="1" align="center">
		<tr>
			<td>글번호 : ${select.no}</td>
		</tr>
		<tr>
			<td>제목 : <input type="text" name="subject" value="${select.subject}"></td>
		</tr>
		<tr>
			<td>작성자 : <input type="text" name="writer" value="${select.writer}"></td>
		</tr>
		<tr>
			<td>내용 : <input type="text" name="contents" value="${select.contents}"></td>
		</tr>
		<tr>
			<c:if test="${select.fileName != null}">
				<td><img src="${pageContext.request.contextPath}/download/${select.fileName}"></td>
			</c:if>
			<c:if test="${select.fileName == null}">
				<td>사진 : <input type="file" name="photo"></td>
			</c:if>
		</tr>
	</table>
	<c:if test="${select.no == null}">
		<input type="submit" value="글쓰기">
	</c:if>
	<c:if test="${select.no > 0}">
		<input type="submit" value="수정">
	</c:if>
	<a href="delete.do?no=${select.no}"><input type="button" value="삭제"></a>
	
	<table align="center" >
		<tr>
			<td>Comment</td>
		</tr>
		<tr>
			<td>작성자 : <input type="text" name="c_writer"> // 날짜 : <input type="text" name=c_writeDate></td> 
		</tr>
		<tr>
			<td>내용 : <textarea rows="2" cols="40"></textarea></td> 
		</tr>
	</table>
	<input type="submit" value="코멘트 작성">
	</form>
</body>
</html>