package com.mycompany.student_management_sys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
public class student_mgmt {
    
    public static boolean deleteStudent(int rollNo) throws SQLException {
    String sql = "DELETE FROM students WHERE roll_no = ?";

    try (Connection con = getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setInt(1, rollNo);

        return pst.executeUpdate() > 0;
    }
}
    
    

public static void loadStudents(
        DefaultTableModel model)
        throws SQLException {

    String sql = """
            SELECT roll_no, name, class,
                   section, gender, dob, phone
            FROM students
            """;

    try (
        Connection con =
                student_mgmt.getConnection();

        PreparedStatement pst =
                con.prepareStatement(sql);

        ResultSet rs =
                pst.executeQuery()
    ) {

        model.setRowCount(0);

        while (rs.next()) {

            model.addRow(new Object[] {

                rs.getInt("roll_no"),

                rs.getString("name"),

                rs.getInt("class"),

                rs.getString("section"),

                rs.getString("gender"),

                rs.getDate("dob"),

                rs.getString("phone")

            });
        }
    }
}    
 
    public static void addStudent(
        int rollNo,
        String name,
        int studentClass,
        String section,
        String gender,
        String dob,
        String phone
) throws SQLException {

    String sql = """
            INSERT INTO students
            (roll_no, name, class, section, gender, dob, phone)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

    try (
        Connection con = student_mgmt.getConnection();

        PreparedStatement pst = con.prepareStatement(sql)
    ) {

        pst.setInt(1, rollNo);

        pst.setString(2, name);

        pst.setInt(3, studentClass);

        pst.setString(4, section);

        pst.setString(5, gender);

        pst.setString(6, dob);

        pst.setString(7, phone);

        pst.executeUpdate();

    }
}

  
    public static void main(String[] args) {

    try {

        Connection con =
                student_mgmt.getConnection();

        System.out.println(
                "Database Connected Successfully!"
        );

        con.close();

    } catch (SQLException e) {

        e.printStackTrace();

    }
}



    public static Connection getConnection()
            throws SQLException {

        String dbURL =
                "jdbc:mysql://localhost:3306/students";

        String username = "root";

        String password = "1234";

        return DriverManager.getConnection(
                dbURL,
                username,
                password
        );
    }
}