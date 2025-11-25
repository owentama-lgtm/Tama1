<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head><title>Admin Login</title></head>
<body>
<h2>Admin Login</h2>
<form method="post" action="${pageContext.request.contextPath}/admin/login">
  <label>Username: <input name="username" required/></label><br/>
  <label>Password: <input type="password" name="password" required/></label><br/>
  <button type="submit">Login</button>
</form>
<c:if test="${not empty error}">
  <p style="color:red">${error}</p>
</c:if>
</body></html>
