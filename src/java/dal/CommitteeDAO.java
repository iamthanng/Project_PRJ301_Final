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
import model.Committee;

/**
 *
 * @author ADMIN
 */
public class CommitteeDAO extends DBContext {

    public List<Committee> getAll() {
        PreparedStatement stm;
        ResultSet rs;
        List<Committee> lists = new ArrayList<>();
        try {
            String strSelect = "select * from Committee";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            while (rs.next()) {
                Committee c = new Committee();
                c.setCommittee_id(rs.getInt(1));
                c.setName(rs.getString(2));
                c.setThesis_id(rs.getInt(3));
                c.setDeparment_id(rs.getInt(4));
                lists.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return lists;
    }

    public void addCommittee() {
        PreparedStatement stm;
        try {
            int maxId = maxCommittee_id() + 1;
            String strSelect = "insert into Committee(name)"
                    + "values(?)";
            stm = connection.prepareStatement(strSelect);
            stm.setString(1, "Committee " + maxId);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void removeCommittee(int committee_id) {
        PreparedStatement stm;
        try {
            String strSelect1 = """
                                delete CommitteeDetail
                                where committee_id = ?
                                
                                delete Committee
                                where committee_id = ?""";
            stm = connection.prepareStatement(strSelect1);
            stm.setInt(1, committee_id);
            stm.setInt(2, committee_id);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void assignThesis(int committee_id, int thesis_id) {
        PreparedStatement stm;
        try {
            String strSelect1 = """
                                UPDATE Committee
                                SET thesis_id = ?
                                WHERE committee_id = ?""";
                                
            stm = connection.prepareStatement(strSelect1);
            stm.setInt(1, thesis_id);
            stm.setInt(2, committee_id);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int maxCommittee_id() {
        PreparedStatement stm;
        ResultSet rs;
        try {
            String strSelect = """
                               SELECT max(committee_id)
                               FROM Committee""";
            stm = connection.prepareStatement(strSelect);
            rs = stm.executeQuery();
            if (rs.next()) {
                int maxId = rs.getInt(1);
                if (rs.wasNull()) {
                    return 1; // Return 1 if the max value is null
                } else {
                    return maxId;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 1;
    }

    public static void main(String[] args) {
        CommitteeDAO c = new CommitteeDAO();
        List<Committee> lists = c.getAll();
        for(Committee cc:lists){
            System.out.println(cc.getThesis_id());
        }
        
    }
}
