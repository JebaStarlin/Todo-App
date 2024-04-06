<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
	<h1>Enter Todo details</h1>
	<form:form method="post" modelAttribute="todo">
		<fieldset class="mb-3">
			<form:label path="description">Description : </form:label>
			<form:input type="text" path="description" required="required"/>
			<form:errors path="description" cssClass="text-warning"/><br>
		</fieldset>
		<fieldset class="mb-3">
			<form:label path="date">Target Date : </form:label>
			<form:input type="date" path="date" required="required"/>
			<form:errors path="date" cssClass="text-warning"/><br>
		</fieldset>
		<form:input type="hidden" path="id"/>
		<form:input type="hidden" path="isDone"/>
		<input type="submit" class="btn btn-success">
	</form:form>
</div>
<script type="text/javascript">
$('.datepicker').datepicker({
    format: 'yyyy-MM-dd'
});
</script>
<%@ include file="common/footer.jspf" %>