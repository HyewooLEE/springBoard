<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>목록</title>
</head>
<body align="center">
	<table border="1" align="center">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>내용</td>
			<td>날짜</td>
		</tr>
		<c:forEach var="list" items="${list}">
		<tr>
			<td>${list.r}</td>
			<td><a href="update.do?no=${list.no}">${list.subject}</a></td>
			<td>${list.writer}</td>
			<td>${list.contents}</td>
			<td>${list.writeDate}</td>
		</tr>
		</c:forEach>
	</table>
	전체글 : ${count}
	<c:if test="${count > 0 }" >
   <c:set var="curPage" value="${count / pageSize + (count % pageSize == 0 ? 0 : 1)}"/>
   <fmt:parseNumber var="pageCount" type="number" integerOnly="true" value="${curPage}" />
   <c:set var="pageBlock" value="5" />
   <fmt:parseNumber var="result" value="${pageNum / pageBlock}" integerOnly="true" />
   <c:set var="startPage" value="${result * pageBlock + 1 }" />
   <c:set var="endPage" value="${startPage + pageBlock -1 }" />
   <c:if test="${endPage > pageCount }" >
      <c:set var="endPage" value="${pageCount }"  />
   </c:if>
   <c:if test="${startPage > pageBlock - 1 }" >
      <a href="list.do?pageNum=${startPage - pageBlock }" >[이전]</a>
   </c:if>
   <c:forEach var="i" begin="${startPage}" end="${endPage}" >
      <a href="list.do?pageNum=${i}">[${i}]</a> 
   </c:forEach>

   <c:if test="${endPage < pageCount + 1 }">
      <a href="list.do?pageNum=${startPage + pageBlock }" >[다음]</a>
   </c:if>
</c:if>
 <div>
	<a href="list.do?curGroup=${nav.startPageGroup - nav.pagePerGroup }">[이전]</a>
	<c:forEach begin="${nav.startPageGroup }" end="${nav.endPageGroup }" var="counter">
		<a href="list.do?pageNum=${counter }">${counter}</a>    
	</c:forEach>        
	<a href="list.do?curGroup=${nav.startPageGroup +nav.pagePerGroup }">[다음]</a>
</div> 

	<br /><a href="write.do"><input type="button" value="글쓰기"></a>
</body>
</html>