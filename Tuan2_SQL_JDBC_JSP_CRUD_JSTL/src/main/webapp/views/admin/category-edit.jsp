<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Sửa danh mục</title>
</head>
<body>
<h2>Sửa danh mục</h2>
<form action="${pageContext.request.contextPath}/admin/category" method="post" enctype="multipart/form-data">
    <input type="hidden" name="action" value="update" />
    <input type="hidden" name="id" value="${cate.categoryid}" />
    <input type="hidden" name="oldImage" value="${cate.images}" />

    <label>Tên danh mục:</label>
    <input type="text" name="categoryname" value="${cate.categoryname}" required /><br/>

    <label>Trạng thái:</label>
    <select name="status">
        <option value="1" ${cate.status==1 ? 'selected' : ''}>Hiển thị</option>
        <option value="0" ${cate.status==0 ? 'selected' : ''}>Ẩn</option>
    </select><br/>

    <label>Ảnh hiện tại:</label><br/>
    <img src="${pageContext.request.contextPath}/images/uploads/${cate.images}" width="150" height="100"/><br/>

    <label>Chọn ảnh mới:</label>
    <input type="file" name="images" /><br/>

    <button type="submit">Cập nhật</button>
</form>
</body>
</html>
