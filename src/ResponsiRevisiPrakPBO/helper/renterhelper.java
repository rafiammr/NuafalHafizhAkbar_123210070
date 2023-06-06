/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ResponsiRevisiPrakPBO.helper;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import ResponsiRevisiPrakPBO.model.RenterModel;

/**
 *
 * @author Naufal
 */
public class renterhelper {
    
    
    public static void insert(RenterModel r) throws SQLException{
        Koneksi db = new Koneksi();
        try{  
            String query = "INSERT INTO renter (name, id, contact, duration, bill, room) VALUES ('" + r.getNama() + "', '" + r.getId() + "', '" + r.getContact() + "', '" + r.getDuration() + "', '" + r.calculateTotalBill(helperRoom.selectPrice(r.getRoom())) + "', '" + r.getRoom() + "')";
            db.getStatement().executeUpdate(query);
            System.out.println("inserted");
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally{
            db.closeConnection();
        }
    }
    
    public static List<RenterModel> getListRenter() throws SQLException{
        Koneksi db = new Koneksi();
        String query = "select * from renter";
        List<RenterModel> aw = new ArrayList<>();
        try{
            ResultSet rs = db.getStatement().executeQuery(query);
            while(rs.next()){
                RenterModel r = new RenterModel();
                r.setNama(rs.getString("name"));
                r.setContact(rs.getString("contact"));
                r.setId(rs.getInt("id"));
                r.setDuration(rs.getInt("duration"));
                r.setBill(Double.parseDouble(rs.getString("bill")));
                r.setStatus(rs.getString("status"));
                r.setRoom(rs.getString("room"));
                aw.add(r);
            }
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally{
            db.closeConnection();
            return aw;
        }
        
    }
    
    public static void delete(int id) throws SQLException{
        Koneksi db = new Koneksi();
        String query =  "delete from renter where id = "+id;
        try{
            db.getStatement().executeUpdate(query);
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally{
            db.closeConnection();
        }
    }

    public static void update(int id) throws SQLException {
        Koneksi db = new Koneksi();
        String query = "update renter set status = \"Paid\" where id = "+id;
        try{  
            db.getStatement().executeUpdate(query);
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally{
            db.closeConnection();
        }
    }
    
    public static void update(RenterModel renter) throws SQLException{
        String[] dataRenter = {String.valueOf(renter.getId()),renter.getNama(),renter.getContact(),String.valueOf(renter.getDuration()),String.valueOf(renter.getBill())};
        String query = "update renter set id= "+dataRenter[0]+", "+"name = \""+dataRenter[1]+"\", contact = \""+dataRenter[2]+"\", duration="+dataRenter[3]+", bill="+dataRenter[4];
        query += " where room= \""+renter.getRoom()+"\"";
        System.out.println(query);
        Koneksi db = new Koneksi();
        try{
            db.getStatement().executeUpdate(query);
        }
        catch(SQLException ex){
            System.out.println(ex);
        }
        finally{
            db.closeConnection();
        }
    }
    
    public static RenterModel selectRenter(int id) throws SQLException{
        Koneksi db = new Koneksi();
        RenterModel r = new RenterModel();
        String query = "select * from renter where id = "+id;
        try{
            ResultSet rs = db.getStatement().executeQuery(query);
            if(rs.next()){
                r.setNama(rs.getString("name"));
                r.setContact(rs.getString("contact"));
                r.setId(rs.getInt("id"));
                r.setDuration(rs.getInt("duration"));
                r.setBill(Double.parseDouble(rs.getString("bill")));
                r.setStatus(rs.getString("status"));
                r.setRoom(rs.getString("room"));
            }
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally{
            db.closeConnection();
            return r;
        }
    }
}
