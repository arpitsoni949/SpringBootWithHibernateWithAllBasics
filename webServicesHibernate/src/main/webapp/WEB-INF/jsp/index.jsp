
<!DOCTYPE html>
<html lang="en" xmlns:th="https://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<title>Spring Session JDBC Example</title>
</head>
<body>
	<div>
		<form th:action="@{/saveMessage}" method="post">
			<textarea name="msg" cols="40" rows="2"></textarea>
			<br> <input type="submit" value="Save Message" />
		</form>
	</div>
	<div>
		<h2>Messages</h2>
		<ul th:each="m : ${messages}">
			<li th:text="${m}">msg</li>
		</ul>
	</div>
	<div>
		<form th:action="@{/invalidate}" method="post">
			<input type="submit" value="Destroy Session" />
		</form>
	</div>
</body>
</html>
