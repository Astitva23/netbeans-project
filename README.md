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

# Extra Syntax Used

1. Inheritance: extends JFrame
public class gui_main extends javax.swing.JFrame

Answer:

extends is used for inheritance.

Our class inherits the features of JFrame, so it behaves like a graphical window.

2.private variables
private DefaultTableModel studentModel;

Answer:

private provides encapsulation. It means the variable can only be accessed directly inside its own class.

3. Exception handling: try-catch
try {
    // database operation
} catch (SQLException ex) {
    JOptionPane.showMessageDialog(
            this,
            "Database error: " + ex.getMessage()
    );
}

Answer:

try-catch handles runtime errors without abruptly crashing the program.

We use:

SQLException for database errors

NumberFormatException when the user enters text instead of a number

Example:

int rollNo = Integer.parseInt(jTextField1.getText());

If the user enters abc, it causes NumberFormatException.


4. throws SQLException
public static boolean deleteStudent(int rollNo)
        throws SQLException

Answer:

throws SQLException tells Java that this method may produce a database-related exception. The method calling it must handle that exception using try-catch or declare it further.


5. PreparedStatement
PreparedStatement pst =
        con.prepareStatement(sql);

Answer:

PreparedStatement is used to execute SQL queries with values safely inserted through placeholders.

Example:

String sql =
    "DELETE FROM students WHERE roll_no = ?";

Then:

pst.setInt(1, rollNo);

The ? is replaced with the roll number.

Why use it?

Cleaner SQL

Prevents SQL injection

Handles values properly


7. try-with-resources
try (Connection con = getConnection();
     PreparedStatement pst = con.prepareStatement(sql)) {

    pst.executeUpdate();
}

Answer:

Try-with-resources automatically closes resources such as:

Database connections

Prepared statements

Result sets

This prevents resource leaks.

-----------

Why did you use MySQL?

MySQL is a relational database system. It stores student records permanently, unlike normal variables that lose data when the program closes.

Why did you use roll number as the primary key?

Roll number uniquely identifies each student, so it prevents duplicate student records and helps us find, edit, or delete a specific student.

Why did you use separate classes?

To separate responsibilities. The GUI handles user interaction, while student_mgmt handles database operations.

What happens if the roll number does not exist?

The SELECT query returns no row. We check whether the result is null or whether rs.next() returns false, and display “Student not found.”

What happens if the user enters letters for roll number?

Integer.parseInt() throws NumberFormatException, which is handled using catch.

Why use PreparedStatement instead of directly joining strings?

It safely inserts values into SQL queries and helps prevent SQL injection.

What is the difference between JFrame and JTable?

JFrame is the main application window. JTable displays data in rows and columns inside the window.

What is the purpose of initComponents()?

It is generated by NetBeans GUI Builder. It initializes and arranges the Swing components such as buttons, labels, text fields, and tables.

What is dispose()?
dispose();

It closes the current JFrame window and releases its window resources.

--------------
# SQL db\

CREATE DATABASE IF NOT EXISTS students;

USE students;

CREATE TABLE IF NOT EXISTS students (
    roll_no INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    class INT NOT NULL,
    section VARCHAR(10),
    gender VARCHAR(20),
    dob DATE,
    phone VARCHAR(15)
);

INSERT INTO students
    (roll_no, name, class, section, gender, dob, phone)
VALUES
    (101, 'Aarav Sharma', 12, 'A', 'Male', '2008-04-15', '9876543210'),
    (102, 'Ananya Verma', 12, 'B', 'Female', '2008-07-22', '9876543211'),
    (103, 'Rohan Mehta', 12, 'A', 'Male', '2008-02-10', '9876543212'),
    (104, 'Priya Singh', 12, 'C', 'Female', '2008-11-03', '9876543213'),
    (105, 'Kabir Khan', 12, 'B', 'Male', '2008-06-18', '9876543214'),
    (106, 'Ishita Gupta', 12, 'A', 'Female', '2008-09-27', '9876543215'),
    (107, 'Aditya Joshi', 12, 'C', 'Male', '2008-01-30', '9876543216'),
    (108, 'Meera Patel', 12, 'B', 'Female', '2008-12-12', '9876543217');

