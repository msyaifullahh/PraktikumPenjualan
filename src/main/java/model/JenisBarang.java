/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import template.MyModelInterface;

/**
 *
 * @author MSyaifullah
 */
public class JenisBarang implements MyModelInterface{

    public JenisBarang(int aInt, String string) {
    }

    Connection con;
    private int id;
    private String namaJenisBarang;

    public JenisBarang(Connection connection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create() {
        boolean berhasil = false;
    
    String insertSQL = "INSERT INTO jenisbarang VALUES (NULL,?)";
    
    try {
        PreparedStatement ps = this.con.prepareStatement(insertSQL);
        ps.setString(1, this.namaJenisBarang);
        ps.execute();
        berhasil = true;
    } catch (SQLException ex) {
        System.out.println(ex.toString());
    }
    
    return berhasil;
    }

    @Override
    public ArrayList<Object> read() {
        ArrayList<Object> list =  new ArrayList<>();
    
    String selectSQL = "SELECT * FROM jenisbarang";
    
    try {
        Statement statement = this.con.createStatement();
        ResultSet resultSet = statement.executeQuery(selectSQL);
        
        while(resultSet.next()){
            JenisBarang jb = new JenisBarang(
                    resultSet.getInt(1),
                    resultSet.getString(2)
            );
            list.add(jb);
        }
        
    } catch (SQLException ex) {
        System.out.println(ex.toString());
    }
    
    return list;
    }

    @Override
    public boolean update() {
        boolean berhasil = false;
    
    String updateSQL = "UPDATE jenisbarang SET namajenisbarang = ? WHERE id = ?";
    
    try {
        PreparedStatement ps = this.con.prepareStatement(updateSQL);
        ps.setString(1, this.namaJenisBarang);
        ps.setInt(2, this.id);

        ps.execute();
        berhasil = true;
    } catch (SQLException ex) {
        System.out.println(ex.toString());
    }
    
    return berhasil;
    }

    @Override
    public boolean delete() {
        boolean berhasil = false;
    
    String deleteSQL = "DELETE FROM jenisbarang WHERE id = ?";
    
    try {
        PreparedStatement ps = this.con.prepareStatement(deleteSQL);
        ps.setInt(1, this.id);

        ps.execute();
        berhasil = true;
    } catch (SQLException ex) {
        System.out.println(ex.toString());
    }
    
    return berhasil;
    }

    @Override
    public ArrayList<Object> search(String keyword) {
         ArrayList<Object> list =  new ArrayList<>();
    
    String searchSQL = "SELECT * FROM jenisbarang WHERE namajenisbarang like ?";
    
    keyword = "%"+keyword+"%";
    
    try {
        PreparedStatement ps = this.con.prepareStatement(searchSQL);
        ps.setString(1, keyword);
        ResultSet resultSet = ps.executeQuery();
        
        while(resultSet.next()){
            JenisBarang jb = new JenisBarang(
                    resultSet.getInt(1),
                    resultSet.getString(2)
            );
            list.add(jb);
        }
        
    } catch (SQLException ex) {
        System.out.println(ex.toString());
    }
    
    return list;
    }

}
