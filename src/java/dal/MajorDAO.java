/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Account;
import model.Major;

/**
 *
 * @author ADMIN
 */
public class MajorDAO extends DBContext {

    public Major getMajor(Account account) {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String query = null;
            if (account.getRoleId() == 1) {
                query = """
                        select *
                        from major m join student s 
                        on m.major_id = s.major_id
                        where s.account_id = ?""";
            } else if (account.getRoleId() == 2) {
                query = """
                        select *
                        from major m join teacher t 
                        on m.major_id = t.major_id
                        where t.account_id = ?""";
            }
            stm = connection.prepareStatement(query);
            stm.setInt(1, account.getAccountId());
            rs = stm.executeQuery();
            if (rs.next()) {
                Major major = new Major(rs.getInt(1), rs.getString(2), rs.getInt(3));
                return major;
            }
        } catch (SQLException e) {
//            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        MajorDAO m = new MajorDAO();
        AccountDAO a = new AccountDAO();
        Account account = a.getAccountById(2);
        System.out.println(account.getRoleId());
        System.out.println(m.getMajor(account));
    }
}
