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
import model.Account;
import model.Department;

/**
 *
 * @author ADMIN
 */
public class DepartmentDAO extends DBContext {

    public List<Department> getAll(){
        PreparedStatement stm;
        ResultSet rs;
        List<Department> lists = new ArrayList<>();
        try {
            String strSelect = "select * from Department";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Department department = new Department();
                department.setDepartmentId(rs.getInt(1));
                department.setName(rs.getString(2));
                lists.add(department);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return lists;
    }
    public Department getDepartmentByAccount(Account account) {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String query = null;
            if (account.getRoleId() == 1) {
                query = "select * from department d join student s "
                        + "on d.department_id = s.department_id "
                        + "where account_id = ?";
            } else if(account.getRoleId() == 2){
                query = "select * from department d join teacher s "
                        + "on d.department_id = s.department_id "
                        + "where account_id = ?";
            }
            stm = connection.prepareStatement(query);
            stm.setInt(1, account.getAccountId());
            rs = stm.executeQuery();
            if (rs.next()) {
                Department d = new Department(rs.getInt(1), rs.getString(2));
                return d;
            }
        } catch (SQLException e) {
//            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        DepartmentDAO d = new DepartmentDAO();
        AccountDAO a = new AccountDAO();
        Account account = a.getAccountById(2);
        System.out.println(account.getUsername());
        System.out.println(d.getDepartmentByAccount(account));
        List<Department> lists = d.getAll();
        System.out.println(lists.get(0).getName());
    }
}
