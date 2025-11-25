<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head><title>Admin Dashboard</title></head>
<body>
<%@ include file="/WEB-INF/includes/header.jsp" %>
<%@ include file="/WEB-INF/includes/nav.jsp" %>

<h2 id="menu">Menu Items</h2>
<table border="1" cellpadding="6">
  <tr><th>ID</th><th>Name</th><th>Description</th><th>Price</th><th>Available</th><th>Actions</th></tr>
  <c:forEach var="m" items="${menuList}">
    <tr>
      <td>${m.id}</td><td>${m.name}</td><td>${m.description}</td><td>${m.price}</td><td>${m.available}</td>
      <td>
        <form style="display:inline" method="post" action="${pageContext.request.contextPath}/admin/menu">
          <input type="hidden" name="action" value="delete"/>
          <input type="hidden" name="id" value="${m.id}"/>
          <button type="submit">Delete</button>
        </form>
      </td>
    </tr>
  </c:forEach>
</table>
<h3>Add Menu Item</h3>
<form method="post" action="${pageContext.request.contextPath}/admin/menu">
  <input type="hidden" name="action" value="add"/>
  <label>Name: <input name="name" required/></label><br/>
  <label>Description: <input name="description"/></label><br/>
  <label>Price: <input name="price" required type="number" step="0.01"/></label><br/>
  <label>Available: <input type="checkbox" name="available" checked/></label><br/>
  <button type="submit">Add</button>
</form>

<h2 id="orders">Orders</h2>
<c:forEach var="o" items="${orders}">
  <div style="border:1px solid #ccc;padding:8px;margin-bottom:8px">
    <strong>Order #${o.id}</strong> — ${o.studentName} (${o.studentContact}) — ${o.status} — ${o.createdAt}<br/>
    <form style="display:inline" method="post" action="${pageContext.request.contextPath}/admin/order">
      <input type="hidden" name="action" value="complete"/>
      <input type="hidden" name="id" value="${o.id}"/>
      <button type="submit">Mark Completed</button>
    </form>
    <!-- Messages for this order -->
    <div style="margin-top:8px;padding:6px;background:#fafafa">
      <h4>Messages</h4>
      <c:choose>
        <c:when test="${not empty o}">
          <%
            // We'll fetch messages via a small helper servlet in future iterations; for now messages are loaded by ReplyServlet redirect.
          %>
        </c:when>
        <c:otherwise>No messages yet.</c:otherwise>
      </c:choose>
      <!-- Reply form -->
      <form method="post" action="${pageContext.request.contextPath}/admin/reply">
        <input type="hidden" name="orderId" value="${o.id}"/>
        <label>Reply to student:<br/>
          <textarea name="message" rows="3" cols="50" required></textarea>
        </label><br/>
        <button type="submit">Send Reply</button>
      </form>
    </div>
  </div>
</c:forEach>
</body></html>
