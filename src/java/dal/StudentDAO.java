/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import model.Student;

/**
 *
 * @author ADMIN
 */
public class StudentDAO extends DBContext {

    public List<Student> getAll() {
        PreparedStatement stm;
        ResultSet rs;
        List<Student> lists = new ArrayList<>();
        try {
            String strSelect = "select * from Student";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Student student = new Student(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getDate(9));
                lists.add(student);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return lists;
    }

    public Student getStudentByID(int accountId) {
        PreparedStatement stm;
        ResultSet rs;
        Student student;
        try {
            String query = "select * from Student s "
                    + "join Account a on s.account_id = a.account_id "
                    + "where s.account_id = ?";
            stm = connection.prepareStatement(query);
            stm.setInt(1, accountId);
            rs = stm.executeQuery();
            if (rs.next()) {
                student = new Student(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getDate(9));
                return student;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Student getStudentByCode(String student_code) {
        PreparedStatement stm;
        ResultSet rs;
        Student student;
        try {
            String query = "select * from Student where student_code like ?";
            stm = connection.prepareStatement(query);
            stm.setString(1, student_code);
            rs = stm.executeQuery();
            if (rs.next()) {
                student = new Student(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getDate(9));
                return student;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void updateProfileStudent(String phone, LocalDate birthdate, int student_id) {
        PreparedStatement stm;
        try {
            String strSelect = """
                               UPDATE Student
                               SET phone = ?, birthdate = ?
                               WHERE student_id = ?;   
                               """;
            stm = connection.prepareStatement(strSelect);
            stm.setString(1, phone);
            stm.setObject(2, birthdate);
            stm.setInt(3, student_id);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        StudentDAO a = new StudentDAO();
        Student student = a.getStudentByID(1);
        System.out.println(student.getBirthdate());
    }
}
