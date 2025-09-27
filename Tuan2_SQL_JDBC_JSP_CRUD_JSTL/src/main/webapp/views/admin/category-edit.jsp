<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<title>Sửa Category</title>
</head>
<body>
	<h2>Sửa Category</h2>
	<form action="${pageContext.request.contextPath}/admin/category"
		method="post" enctype="multipart/form-data">
		<input type="hidden" name="action" value="update" /> <input
			type="hidden" name="id" value="${cate.categoryid}" /> <input
			type="hidden" name="oldImage" value="${cate.images}" />

		<p>
			Tên Category: <input type="text" name="categoryname"
				value="${cate.categoryname}" required />
		</p>

		<p>
			Trạng thái: <select name="status">
				<option value="1" <c:if test="${cate.status == 1}">selected</c:if>>Hiển
					thị</option>
				<option value="0" <c:if test="${cate.status == 0}">selected</c:if>>Ẩn</option>
			</select>
		</p>

		<p>
			Ảnh hiện tại:<br /> <img
				src="${pageContext.request.contextPath}/image?fname=${cate.images}"
				width="150" height="100" alt="Image" />
		</p>

		<p>
			Chọn ảnh mới: <input type="file" name="images" />
		</p>

		<button type="submit">Cập nhật</button>
		<a href="${pageContext.request.contextPath}/admin/category">Hủy</a>
	</form>
</body>
</html>
