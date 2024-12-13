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
import model.Status;

/**
 *
 * @author ADMIN
 */
public class StatusDAO extends DBContext{
    public List<Status> getAll() {
        PreparedStatement stm;
        ResultSet rs;
        List<Status> lists = new ArrayList<>();
        try {
            String strSelect = "select * from Status";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Status status = new Status(rs.getInt(1), rs.getString(2));
                lists.add(status);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return lists;
    }
    
    public static void main(String[] args) {
        StatusDAO s = new StatusDAO();
        List<Status> list = s.getAll();
        System.out.println(list.get(0).getStatusName());
    }
}
