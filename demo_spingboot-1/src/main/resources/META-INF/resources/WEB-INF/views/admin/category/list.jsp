<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:if test="${message!=null}">

	${message}

</c:if>

`<a href="/admin/categorires/add">Add</a>



<table>
	<tr>
		<th>STT</th>
		<th>Images</th>
		<th>Status</th>
		<th>Action</th>
	</tr>


	<c:forEach items="${list}" var = "cate" varStatus="stt">
		<tr>
			<td>${stt.index+1}</td>
			<td>${cate.images}</td>
			<td>${cate.name }</td>
			<td>${cate.status}</td>
			<td>
				<a href="/admin/categories/edit/${cate.id}">Edit</a>
				<a href="/admin/categories/delete/${cate.id}">Delete</a>
			
			</td>
		</tr>

	</c:forEach>



</table>