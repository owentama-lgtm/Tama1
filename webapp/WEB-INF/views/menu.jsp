<html><body>
<h2>Menu Management</h2>
<form method="post">
Name:<input name="name"/> 
Price:<input name="price" type="number" step="0.01"/>
<button>Add</button>
</form>
<table border="1">
<tr><th>ID</th><th>Name</th><th>Price</th></tr>
<c:forEach var="i" items="${items}">
<tr><td>${i.id}</td><td>${i.name}</td><td>${i.price}</td></tr>
</c:forEach>
</table>
</body></html>
