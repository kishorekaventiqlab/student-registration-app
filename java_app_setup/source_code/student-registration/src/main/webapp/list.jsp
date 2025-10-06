<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.gana.student.model.Student" %>
<html>
<head><title>Registered Students</title></head>
<body>
<h2>Registered Students</h2>
<table border="1" cellpadding="5">
  <tr><th>ID</th><th>Name</th><th>Email</th><th>Course</th><th>Registered At</th></tr>
  <%
    List<Student> students = (List<Student>) request.getAttribute("students");
    if (students != null) {
        for (Student s : students) {
  %>
    <tr>
      <td><%= s.getId() %></td>
      <td><%= s.getName() %></td>
      <td><%= s.getEmail() %></td>
      <td><%= s.getCourse() %></td>
      <td><%= s.getRegisteredAt() %></td>
    </tr>
  <%
        }
    } else {
  %>
    <tr><td colspan="5">No students found.</td></tr>
  <%
    }
  %>
</table>
<br/><a href="register">Register New Student</a>
</body>
</html>
