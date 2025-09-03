<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="fn" uri="jakarta.tags.functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách Category</title>
<style>
table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	border: 1px solid #ccc;
	padding: 8px;
	text-align: center;
}

img {
	border-radius: 6px;
	object-fit: cover;
}
</style>
</head>
<body>
	<h2 style="text-align: center; margin: 20px 0;">Danh sách Category</h2>

	<table>
		<tr>
			<th>STT</th>
			<th>Images</th>
			<th>CategoryID</th>
			<th>CategoryName</th>
			<th>Status</th>
			<th>Action</th>
		</tr>

		<c:forEach items="${listcase}" var="cate" varStatus="stt">
			<tr>
				<td>${stt.index + 1}</td>

				<td><c:choose>
						<c:when test="${fn:startsWith(cate.images, 'http')}">
							<img src="${cate.images}" height="100" width="150" alt="Image" />
						</c:when>

						<c:otherwise>
							<img
								src="${pageContext.request.contextPath}/load-image?fname=${cate.images}"
								height="100" width="150" alt="Image" />
						</c:otherwise>
					</c:choose></td>

				<td>${cate.categoryid}</td>
				<td>${cate.categoryname}</td>
				<td><c:choose>
						<c:when test="${cate.status == 1}">Hiển thị</c:when>
						<c:otherwise>Ẩn</c:otherwise>
					</c:choose></td>

				<td><a
					href="<c:url value='/admin/category?action=edit&id=${cate.categoryid}'/>">Sửa</a>
					| <a
					href="<c:url value='/admin/category?action=delete&id=${cate.categoryid}'/>"
					onclick="return confirm('Bạn có chắc muốn xóa không?')">Xóa</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
