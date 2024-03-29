<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<style>
img{
	cursor: pointer;
}
</style>
</head>
<body>
	<c:if test="${list.size() == 0}">
	<p>등록된 사진이 없습니다.</p>
	</c:if>
	<c:forEach items="${list}" var="path">
		<p>
			<img src="/photo/${path}" width="250"/>
		</p>
	</c:forEach>
	
	
</body>
<script>
$('img').on('click',function(){
	var path = $(this).attr('src');
	var fileName = path.substring(path.lastIndexOf('/')+1);
	console.log(fileName);
	location.href='delete?file='+fileName;
});
</script>
</html>










