/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.User;

/**
 *
 * @author lucasmondini
 */
public class UserDAO {
    
    private Connection con = null;
    
    public UserDAO(){
        con = ConnectionFactory.getConnection();
    }
    
    public boolean save(User user){
        
        String sql = "INSERT INTO user (name, email, password) VALUES (?,?,?)";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Error Create User: "+ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
}
