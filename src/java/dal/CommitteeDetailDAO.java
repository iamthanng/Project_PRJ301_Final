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
import model.CommitteeDetail;

/**
 *
 * @author ADMIN
 */
public class CommitteeDetailDAO extends DBContext {

    public List<CommitteeDetail> getAll() {
        PreparedStatement stm;
        ResultSet rs;
        List<CommitteeDetail> list = new ArrayList<>();
        try {
            String strSelect = "select * from CommitteeDetail";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                CommitteeDetail committeeDetail = new CommitteeDetail(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDouble(4), rs.getString(5));
                list.add(committeeDetail);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<CommitteeDetail> getByThesisId(int thesis_id) {
        PreparedStatement stm;
        ResultSet rs;
        List<CommitteeDetail> list = new ArrayList<>();
        try {
            String strSelect = """
                               Select * from CommitteeDetail c 
                               join Committee cd 
                               on c.committee_id = cd.committee_id 
                               where cd.thesis_id = ?""";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, thesis_id);
            rs = stm.executeQuery();
            while (rs.next()) {
                CommitteeDetail committeeDetail = new CommitteeDetail(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDouble(4), rs.getString(5));
                list.add(committeeDetail);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void addMember(int committee_id, int teacher_id) {
        PreparedStatement stm;
        try {
            String strSelect = "insert into CommitteeDetail(committee_id, teacher_id)"
                    + "values(?, ?)";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, committee_id);
            stm.setInt(2, teacher_id);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void editMember(int teacher_id, int teacherOld, int committee_id) {
        PreparedStatement stm;
        try {
            String strSelect = """
                               Update CommitteeDetail
                               set teacher_id = ?
                               where committee_id = ? and teacher_id = ?""";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, teacher_id);
            stm.setInt(2, committee_id);
            stm.setInt(3, teacherOld);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public CommitteeDetail get(int thesis_id, int teacher_id) {
        PreparedStatement stm;
        ResultSet rs;
        CommitteeDetail committeeDetail;
        try {
            String query = """
                           select * from Committee c
                           join CommitteeDetail cd 
                           on c.committee_id = cd.committee_id
                           where c.thesis_id = ? and cd.teacher_id = ?""";
            stm = connection.prepareStatement(query);
            stm.setInt(1, thesis_id);
            stm.setInt(2, teacher_id);
            rs = stm.executeQuery();
            if (rs.next()) {
                committeeDetail = new CommitteeDetail(rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getDouble(8), rs.getString(9));
                return committeeDetail;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public CommitteeDetail getDetail(int id) {
        PreparedStatement stm;
        ResultSet rs;
        CommitteeDetail committeeDetail;
        try {
            String query = """
                           select * from CommitteeDetail 
                           where id = ?""";
            stm = connection.prepareStatement(query);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                committeeDetail = new CommitteeDetail(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDouble(4), rs.getString(5));
                return committeeDetail;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void grading(CommitteeDetail cd, double score, String comment) {
        PreparedStatement stm;
        try {
            String strSelect = """
                               UPDATE CommitteeDetail
                               set score = ?, comment = ?
                               where id = ?""";
            stm = connection.prepareStatement(strSelect);
            stm.setDouble(1, score);
            stm.setString(2, comment);
            stm.setInt(3, cd.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        CommitteeDetailDAO c = new CommitteeDetailDAO();
        List<CommitteeDetail> list = c.getAll();
        for (CommitteeDetail committeeDetail : list) {
            System.out.println(committeeDetail.getTeacher_id());
        }
        
    }
}
