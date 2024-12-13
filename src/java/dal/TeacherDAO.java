/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Teacher;

/**
 *
 * @author ADMIN
 */
public class TeacherDAO extends DBContext {

    public List<Teacher> getAll() {
        PreparedStatement stm;
        ResultSet rs;
        List<Teacher> lists = new ArrayList<>();
        try {
            String strSelect = "select * from Teacher";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Teacher student = new Teacher(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getDate(9).toLocalDate());
                lists.add(student);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return lists;
    }

    public Teacher getTeacherByID(int accountId) {
        PreparedStatement stm;
        ResultSet rs;
        Teacher teacher;
        try {
            String query = "select * from Teacher t "
                    + "join Account a on t.account_id = a.account_id "
                    + "where t.account_id = ?";
            stm = connection.prepareStatement(query);
            stm.setInt(1, accountId);
            rs = stm.executeQuery();
            if (rs.next()) {
                teacher = new Teacher(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getDate(9).toLocalDate());
                return teacher;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Teacher getStudentByCode(String student_code) {
        PreparedStatement stm;
        ResultSet rs;
        Teacher teacher;
        try {
            String query = "select * from Teacher where teacher_code like ?";
            stm = connection.prepareStatement(query);
            stm.setString(1, student_code);
            rs = stm.executeQuery();
            if (rs.next()) {
                teacher = new Teacher(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getDate(9).toLocalDate());
                return teacher;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Teacher> getTeacherByCommittee(int committee_id) {
        PreparedStatement stm;
        ResultSet rs;
        List<Teacher> lists = new ArrayList<>();
        try {
            String strSelect = """
                               select * from teacher t
                               join CommitteeDetail cd on t.teacher_id = cd.teacher_id 
                               where cd.committee_id = ?""";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, committee_id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Teacher student = new Teacher(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getDate(9).toLocalDate());
                lists.add(student);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return lists;
    }

//    public void gradeThesis(int thesis_id, int teacher_id,float score, String comment){
//        PreparedStatement stm;
//        try {
//            String strSelect = """
//                               select * from teacher t
//                               join CommitteeDetail cd on t.teacher_id = cd.teacher_id 
//                               where cd.committee_id = ?""";
//            stm = connection.prepareStatement(strSelect);
//            stm.setInt(1, committee_id);
//            rs = stm.executeQuery();
//            while (rs.next()) {
//                Teacher student = new Teacher(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getDate(9).toLocalDate());
//                lists.add(student);
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//    }
    public void updateProfileTeacher(String phone, LocalDate birthdate, int teacher_id) {
        PreparedStatement stm;
        try {
            String strSelect = """
                               UPDATE Teacher
                               SET phone = ?, birthdate = ?
                               WHERE teacher_id = ?;""";
            stm = connection.prepareStatement(strSelect);
            stm.setString(1, phone);
            stm.setObject(2, birthdate);
            stm.setInt(3, teacher_id);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        TeacherDAO a = new TeacherDAO();
//        Teacher teacher = a.getStudentByCode("ABcsdqwe");

        Teacher t = a.getTeacherByID(2);
        List<Teacher> lists = a.getTeacherByCommittee(4);
        for (Teacher teacher : lists) {
            System.out.println(teacher.getAccountId());
        }
    }
}
