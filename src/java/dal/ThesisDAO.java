/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import model.Thesis;

/**
 *
 * @author ADMIN
 */
public class ThesisDAO extends DBContext {

    public List<Thesis> getAllThesis() {
        PreparedStatement stm;
        ResultSet rs;
        List<Thesis> list = new ArrayList<>();
        try {
            String strSelect = "select * from Thesis";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Thesis thesis = new Thesis();
                thesis.setThesisId(rs.getInt("thesis_id"));
                thesis.setTitle(rs.getString("title"));
                thesis.setDescription(rs.getString("description"));
                thesis.setStudentId(rs.getInt("student_id"));
                thesis.setTeacherId(rs.getInt("teacher_id"));
                thesis.setFileName(rs.getString("fileName"));
                thesis.setFilePath(rs.getString("filePath"));
                thesis.setRegister_date(rs.getTimestamp("register_date"));
                thesis.setStatus_id(rs.getInt("status_id"));
                list.add(thesis);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Thesis> getAllThesisById(int student_id) {
        PreparedStatement stm;
        ResultSet rs;
        List<Thesis> list = new ArrayList<>();
        try {
            String strSelect = "select * from Thesis "
                    + "where student_id = ?";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, student_id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Thesis thesis = new Thesis();
                thesis.setThesisId(rs.getInt("thesis_id"));
                thesis.setTitle(rs.getString("title"));
                thesis.setDescription(rs.getString("description"));
                thesis.setStudentId(rs.getInt("student_id"));
                thesis.setTeacherId(rs.getInt("teacher_id"));
                thesis.setFileName(rs.getString("fileName"));
                thesis.setFilePath(rs.getString("filePath"));
                thesis.setRegister_date(rs.getTimestamp("register_date"));
                thesis.setStatus_id(rs.getInt("status_id"));
                thesis.setScore(rs.getDouble("score"));
                list.add(thesis);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Thesis> getThesisGrading(int teacher_id) {
        PreparedStatement stm;
        ResultSet rs;
        List<Thesis> list = new ArrayList<>();
        try {
            String strSelect = """
                               select * from thesis t
                               join Committee c on t.thesis_id = c.thesis_id
                               join CommitteeDetail cd on c.committee_id = cd.committee_id
                               where cd.teacher_id = ?""";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, teacher_id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Thesis thesis = new Thesis();
                thesis.setThesisId(rs.getInt("thesis_id"));
                thesis.setTitle(rs.getString("title"));
                thesis.setDescription(rs.getString("description"));
                thesis.setStudentId(rs.getInt("student_id"));
                thesis.setTeacherId(rs.getInt("teacher_id"));
                thesis.setFileName(rs.getString("fileName"));
                thesis.setFilePath(rs.getString("filePath"));
                thesis.setRegister_date(rs.getTimestamp("register_date"));
                thesis.setStatus_id(rs.getInt("status_id"));
                list.add(thesis);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void addThesis(String title, String description, int student_id, int teacher_id, String fileName, String filePath, Timestamp register_date) {
        PreparedStatement stm;
        try {
            String strSelect = """
                               INSERT INTO Thesis (title, description, student_id, teacher_id, fileName, filePath, register_date, status_id)
                               VALUES (?, ?, ?, ?, ?, ?, ?, ?);""";
            stm = connection.prepareStatement(strSelect);
            stm.setString(1, title);
            stm.setString(2, description);
            stm.setInt(3, student_id);
            stm.setInt(4, teacher_id);
            stm.setString(5, fileName);
            stm.setString(6, filePath);
            stm.setTimestamp(7, register_date);
            stm.setInt(8, 1);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateStatusThesis(int thesis_id, int status_id) {
        PreparedStatement stm;
        try {
            String strSelect = """
                               UPDATE Thesis
                               SET status_id = ?
                               WHERE thesis_id = ?;""";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, status_id);
            stm.setInt(2, thesis_id);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void totalScore(int thesis_id, double totalScore) {
        PreparedStatement stm;
        try {
            String strSelect = """
                               UPDATE Thesis
                               SET score = ?
                               WHERE thesis_id = ?;""";
            stm = connection.prepareStatement(strSelect);
            stm.setDouble(1, totalScore);
            stm.setInt(2, thesis_id);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        ThesisDAO t = new ThesisDAO();
        List<Thesis> list = t.getThesisGrading(4);
        System.out.println(list.get(0).getTitle());
    }
}
