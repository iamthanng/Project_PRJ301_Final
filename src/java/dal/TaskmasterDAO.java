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
import model.Student;
import model.Taskmaster;

/**
 *
 * @author ADMIN
 */
public class TaskmasterDAO extends DBContext {

    public List<Taskmaster> getAllTaskmaster() {
        PreparedStatement stm;
        ResultSet rs;
        List<Taskmaster> list = new ArrayList<>();
        try {
            String strSelect = "select * from Taskmaster";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Taskmaster taskmaster = new Taskmaster();
                taskmaster.setTaskmaster_id(rs.getInt(1));
                taskmaster.setAccount_id(rs.getInt(2));
                taskmaster.setName(rs.getString(3));
                taskmaster.setEmail(rs.getString(4));
                taskmaster.setPhone(rs.getString(5));
                taskmaster.setBirthdate(rs.getDate(6).toLocalDate());
                list.add(taskmaster);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public Taskmaster getTaskMasterByID(int accountId) {
        PreparedStatement stm;
        ResultSet rs;
        Taskmaster taskmaster;
        try {
            String query = "select * from TaskMaster s "
                    + "join Account a on s.account_id = a.account_id "
                    + "where s.account_id = ?";
            stm = connection.prepareStatement(query);
            stm.setInt(1, accountId);
            rs = stm.executeQuery();
            if (rs.next()) {
                taskmaster = new Taskmaster();
                taskmaster.setTaskmaster_id(rs.getInt(1));
                taskmaster.setAccount_id(rs.getInt(2));
                taskmaster.setName(rs.getString(3));
                taskmaster.setEmail(rs.getString(4));
                taskmaster.setPhone(rs.getString(5));
                taskmaster.setBirthdate(rs.getDate(6).toLocalDate());
                return taskmaster;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public void createCommittee(int committee_id, int teacher_id){
        PreparedStatement stm;
        try {
            String strSelect = "insert into ThesisComittee(committee_id, teacher_id)"
                    + "values(?, ?)";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, committee_id);
            stm.setInt(2, teacher_id);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void assign(int committee_id, int thesis_id){
        PreparedStatement stm;
        try {
            String strSelect = "insert into ThesisComittee(thesis_id)"
                    + "values(?)";
            stm = connection.prepareStatement(strSelect);
            stm.setInt(1, thesis_id);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void updateProfileTask(String phone, LocalDate birthdate, int taskmaster_id) {
        PreparedStatement stm;
        try {
            String strSelect = """
                               UPDATE TaskMaster
                               SET phone = ?, birthdate = ?
                               WHERE taskmaster_id = ?;""";
            stm = connection.prepareStatement(strSelect);
            stm.setString(1, phone);
            stm.setObject(2, birthdate);
            stm.setInt(3, taskmaster_id);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) {
        TaskmasterDAO t = new TaskmasterDAO();
        List<Taskmaster> list = t.getAllTaskmaster();
        Taskmaster task = t.getTaskMasterByID(4);
        System.out.println(task.getPhone());
    }
}
