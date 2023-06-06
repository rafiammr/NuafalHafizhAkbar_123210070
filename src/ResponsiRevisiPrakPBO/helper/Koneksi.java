/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ResponsiRevisiPrakPBO.helper;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

/**
 *
 * @author Naufal
 */
public class Koneksi {
    private Connection koneksi;
    private Statement statement;
    public Koneksi(){
        try{
            koneksi = DriverManager.getConnection("jdbc:mysql://localhost/responsipbo", "root", "");
            statement=koneksi.createStatement();
        }
        catch(SQLException ex){
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Statement getStatement(){
        return this.statement;
    }
    public void closeConnection() throws SQLException{
        this.koneksi.close();
    }
}
