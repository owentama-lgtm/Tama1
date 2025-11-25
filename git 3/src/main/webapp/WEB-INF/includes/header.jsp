<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div style="background:#eee;padding:10px;margin-bottom:10px">
  <h1>School Cafeteria Admin</h1>
  <c:if test="${not empty sessionScope.adminUser}">
    <div>Welcome, ${sessionScope.adminUser} | <a href="${pageContext.request.contextPath}/admin/logout">Logout</a></div>
  </c:if>
</div>
