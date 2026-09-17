# netbeans-project
Open GitHub repository.

Click Code → Download ZIP.

Extract it.

In NetBeans choose File → Open Project.

Select the extracted project folder.



# Questions


1. Connection con = student_mgmt.getConnection();
This line establishes a connection to the database. student_mgmt here refers to an object (likely of a custom class or a DataSource) that has a getConnection() method, which returns a Connection object — this object represents the active session/link between the Java application and the database. Without this, no SQL operations can be performed.

2. PreparedStatement pst = con.preparedStatement(sql);
This creates a PreparedStatement object using the connection con. A PreparedStatement is used to execute parameterized SQL queries (queries with placeholders ?). It's precompiled by the database, which makes it faster on repeated execution and — importantly — safe against SQL injection, unlike a plain Statement.

3. pst.setInt(1, rollNo);
This sets the value of the first placeholder (?) in the SQL query to the integer value stored in rollNo. The index 1 refers to the position of the parameter in the query (JDBC indexing starts at 1, not 0). setInt() is used specifically because the column being set expects an integer type.

4. pst.executeUpdate();
This executes the SQL statement — but only for queries that modify data: INSERT, UPDATE, or DELETE. It returns an int representing the number of rows affected. It should not be used for SELECT queries.

5. ResultSet rs = pst.executeQuery();
This executes a SELECT query and returns the result in a ResultSet object, rs. The ResultSet acts like a table/cursor that you iterate over (using rs.next()) to read the returned rows and columns. executeQuery() is used only for queries that retrieve data, not for updates.

Likely follow-up viva questions to be ready for:

Difference between Statement and PreparedStatement? → PreparedStatement is precompiled, faster for repeated use, and prevents SQL injection.
Difference between executeUpdate() and executeQuery()? → executeUpdate() returns an int (rows affected) for DML operations; executeQuery() returns a ResultSet for SELECT.
Why parameter index starts from 1 in JDBC? → It's a JDBC API design convention, unlike array indexing in Java which starts from 0.
