<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
<p>Welcome ${name}</p>
<hr>
<h2>Your Todos Are </h2>
<table class="table">
	<thead>
		<tr>
			<th>Discription</th>
			<th>Target Date</th>
			<th>Is Done?</th>
			<th></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${todos}" var="todo">
			<tr>
				<td>${todo.description}</td>
				<td>${todo.date}</td>
				<td>${todo.isDone}</td>
				<td><a href="delete-todo?id=${todo.id}" class="btn btn-warning">DELETE </a></td>
				<td><a href="update-todo?id=${todo.id}" class="btn btn-warning">UPDATE </a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a href="add-todo" class="btn btn-success">Add Todo</a>
</div>
<%@ include file="common/footer.jspf" %>