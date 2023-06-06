/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ResponsiRevisiPrakPBO.helper;

import ResponsiRevisiPrakPBO.model.AccountModel;
import java.sql.*;

/**
 *
 * @author Naufal
 */
public class accHelper {
    AccountModel acc;
    
    public static void Login(AccountModel acc) throws SQLException{
        Koneksi db = new Koneksi();
        
        String query = "select * from accounts where username = \""+acc.getUsername()+"\" and password=\""+acc.getPassword()+"\"";
        try{
            ResultSet rs =  db.getStatement().executeQuery(query);
            if(rs.next()){
                acc.setRole(rs.getString("role"));
            }
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally{
            db.closeConnection();
        }
    }
    
}
