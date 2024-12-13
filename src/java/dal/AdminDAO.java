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
import model.Admin;
import model.Teacher;

/**
 *
 * @author ADMIN
 */
public class AdminDAO extends DBContext {

    public List<Admin> getAll() {
        PreparedStatement stm;
        ResultSet rs;
        List<Admin> lists = new ArrayList<>();
        try {
            String strSelect = "select * from Admin";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Admin admin = new Admin(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6).toLocalDate());
                lists.add(admin);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return lists;
    }

    public Admin getAdminByID(int accountId) {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String query = "select * from Admin t "
                    + "join Account a on t.account_id = a.account_id "
                    + "where t.account_id = ?";
            stm = connection.prepareStatement(query);
            stm.setInt(1, accountId);
            rs = stm.executeQuery();
            if (rs.next()) {
                Admin admin = new Admin(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6).toLocalDate());
                return admin;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public void updateProfileAdmin(String phone, LocalDate birthdate, int admin_id) {
        PreparedStatement stm;
        try {
            String strSelect = """
                               UPDATE Admin
                               SET phone = ?, birthdate = ?
                               WHERE admin_id = ?;""";
            stm = connection.prepareStatement(strSelect);
            stm.setString(1, phone);
            stm.setObject(2, birthdate);
            stm.setInt(3, admin_id);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) {
        AdminDAO a = new AdminDAO();
        Admin admin = a.getAdminByID(12);
        System.out.println(admin.getAdmin_id());
    }
}
