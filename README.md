# Food Ordering Admin (School Cafeteria)

Console-based Java admin module using MySQL (JDBC).

**What this includes**
- Java source files (plain Java, no external build tool)
- SQL schema file to create required tables
- `.gitignore`
- Instructions to compile & run

**Features**
- Admin login (simple hard-coded admin for demo)
- CRUD for menu items
- View orders
- Mark orders as completed

**Setup**
1. Install Java (JDK 11+) and MySQL server.
2. Create a MySQL database (e.g. `cafeteria_db`) and run `create_tables.sql`.
   ```
   mysql -u root -p cafeteria_db < create_tables.sql
   ```
3. Update `src/Database.java` JDBC URL, username, and password to match your DB.
4. Compile:
   ```
   javac -d out src/*.java
   ```
5. Run:
   ```
   java -cp out DatabaseSeeder  # optional to seed demo data
   java -cp out AdminApp
   ```

**Notes**
- This is a minimal, educational admin module. For production you should:
  - Use password hashing and proper authentication
  - Use connection pooling (e.g., HikariCP)
  - Add input validation and prepared statements (already used)
  - Move configuration to a properties file or environment variables

