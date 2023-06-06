/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ResponsiRevisiPrakPBO.helper;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ResponsiRevisiPrakPBO.model.RoomModel;
/**
 *
 * @author Naufal
 */
public class helperRoom{
    
    
    public static void update(String room,String namaRenter)throws SQLException{
        Koneksi db = new Koneksi();
        String query = "update rooms set status = \""+namaRenter+"\" where name = \""+room+"\"";
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
    
    public static void setEmptyRoom(String roomName) throws SQLException{
        Koneksi db = new Koneksi();
        String query = "update rooms set status = \"empty\" where name = \""+roomName+"\"";
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
    
    public static List<RoomModel> getRoomList() throws SQLException{
        Koneksi db = new Koneksi();
        List<RoomModel> rooms = new ArrayList<>();
        String query = " select * from rooms where status=\"empty\"";
        try{
            ResultSet rs = db.getStatement().executeQuery(query);
            while(rs.next()){
                RoomModel room=new RoomModel();
                room.setNama(rs.getString("name"));
                room.setPrice(rs.getDouble("price"));
                room.setSize(rs.getString("size"));
                room.setStatus(rs.getString("status"));
                rooms.add(room);
            }
        }
        catch(SQLException ex){
            System.out.println(ex);
        }
        finally{
            db.closeConnection();
            return rooms;
        }
    }
    
    
    
    public static double selectPrice(String room) throws SQLException{
        Koneksi db = new Koneksi();
        String query = "select price from rooms where name = \""+room+"\"";
        double re=0;
        try{
            ResultSet rs = db.getStatement().executeQuery(query);
            if(rs.next()){
                re=Double.parseDouble(rs.getString("price"));
            }
        }
        catch(SQLException ex){
            System.out.println(ex);
        }
        finally{
            db.closeConnection();
            return re;
        }
    }
}
