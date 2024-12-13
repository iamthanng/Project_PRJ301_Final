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

/**
 *
 * @author ADMIN
 */
public class AccountDAO extends DBContext {

    public List<Account> getAll() {
        PreparedStatement stm;
        ResultSet rs;
        List<Account> list = new ArrayList<>();
        try {
            String strSelect = "select * from Account";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Account account = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                list.add(account);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Account checkLogin(String username, String password) {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String query = "select * from account where username = ? and password = ?";
            stm = connection.prepareStatement(query);
            stm.setString(1, username);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if (rs.next()) {
                Account account = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                return account;
            }
        } catch (SQLException e) {
//            System.out.println(e);
        }
        return null;
    }
    
    public Account getAccountById(int account_id){
        PreparedStatement stm;
        ResultSet rs;
        try {
            String query = "select * from account where account_id = ?";
            stm = connection.prepareStatement(query);
            stm.setInt(1, account_id);
            rs = stm.executeQuery();
            if (rs.next()) {
                Account account = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                return account;
            }
        } catch (SQLException e) {
//            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        AccountDAO a = new AccountDAO();
        Account ac = a.checkLogin("user1", "1");
        System.out.println(ac.getRoleId());
        System.out.println(a.getAccountById(1));
    }
}
