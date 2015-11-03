<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
<div align="center">
	<c:url var="addUrl" value="/register"/>
	<form:form action="${addUrl}" method="get" commandName="userForm">
		<table border="0">
			<h1>${message}</h1>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Registration" /></td>
			</tr>
		</table>
	</form:form>
</div>
</body>
</html>