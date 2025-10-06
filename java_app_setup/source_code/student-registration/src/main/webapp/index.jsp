<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Student Registration</title>
</head>
<body>
<h2>Student Registration</h2>
<c:if test="${not empty error}">
  <div style="color:red">${error}</div>
</c:if>
<form action="register" method="post">
  Name: <input type="text" name="name" required /><br/><br/>
  Email: <input type="email" name="email" required /><br/><br/>
  Course: <input type="text" name="course" required /><br/><br/>
  <button type="submit">Register</button>
</form>
<hr/>
<a href="list">View Registered Students</a>
</body>
</html>
