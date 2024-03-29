<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<style></style>
</head>
<body>
	<!-- 업로드 규칙 1: POST 방식으로 보낸다.  -->
	<!-- 업로드 규칙 2: enctype 설정  -->	
	<form action="upload" method="post" enctype="multipart/form-data">
		<input type="text" name="title"/>
		<input type="file" name="uploadFile"/>
		<input type="submit" value="전송"/>
	</form>
	
	<hr/>
	<form action="multiUpload" method="post" enctype="multipart/form-data">
		<input type="file" name="files" multiple="multiple"/>
		<input type="submit" value="전송"/>
	</form>
</body>
<script></script>
</html>