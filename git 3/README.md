# School Cafeteria — Admin (Web) v2

Java Servlet + JSP admin module (Maven WAR) with MySQL (JDBC).
This v2 adds an Admin Reply-to-Student feature for orders and a clearer dashboard/mainframe.

**New Features in v2**
- Admin can reply to a student's order; replies are saved in `order_messages` table.
- Dashboard shows messages per order and a reply form.
- Mainframe layout (header + nav) via `WEB-INF/includes` JSPs for consistency.

**Setup**
1. Create database (e.g., cafeteria_db) and run `sql/create_tables_v2.sql`:
   ```
   mysql -u root -p cafeteria_db < sql/create_tables_v2.sql
   ```
2. Update `src/main/java/com/school/cafeteria/util/Database.java` with DB URL/user/pass.
3. Build with Maven: `mvn clean package` and deploy `target/admin-web-v2.war`.
4. Access: http://localhost:8080/admin-web-v2/admin/login.jsp
