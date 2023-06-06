/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ResponsiRevisiPrakPBO.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import ResponsiRevisiPrakPBO.helper.helperRoom;
import ResponsiRevisiPrakPBO.model.RoomModel;
import ResponsiRevisiPrakPBO.view.RoomListView;

/**
 *
 * @author Naufal
 */
public class ControllerRoom implements ActionListener, ListSelectionListener{
    
    ControllerLogin parent;
    RoomListView view;
    public ControllerRoom(ControllerLogin lc){
        this.parent=lc;
        view = new RoomListView();
        view.getTabel().getSelectionModel().addListSelectionListener(this);
        view.getBcancel().addActionListener(this);
        
        DefaultTableModel dtm = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Mengembalikan nilai false untuk mencegah pengeditan sel
            }
        };
        dtm.addColumn("Name");
        dtm.addColumn("Size");
        dtm.addColumn("Price");
        dtm.addColumn("Status");
        
        List<RoomModel> roomList = new ArrayList<>();
        
        try{
            roomList = helperRoom.getRoomList();
            for(RoomModel r :roomList){
                dtm.addRow(new Object[]{r.getNama(),r.getSize(),r.getPrice(),r.getStatus()});
            }
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally{
            view.getTabel().setModel(dtm);
        }
    }
    
    public void updateTable(){
        view.setTableModel(null);
        view.getTabel().clearSelection();
        DefaultTableModel dtm = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Mengembalikan nilai false untuk mencegah pengeditan sel
            }
        };
        dtm.addColumn("Name");
        dtm.addColumn("Size");
        dtm.addColumn("Price");
        dtm.addColumn("Status");
        
        List<RoomModel> roomList = new ArrayList<>();
        
        try{
            roomList = helperRoom.getRoomList();
            for(RoomModel r :roomList){
                dtm.addRow(new Object[]{r.getNama(),r.getSize(),r.getPrice(),r.getStatus()});
            }
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        catch(ArrayIndexOutOfBoundsException ex){
            
        }
        finally{
            view.getTabel().setModel(dtm);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.getBcancel()){
           parent.showPage(true);
           view.dispose();
       }
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()){
            try{
                String name = view.getTabel().getValueAt(view.getTabel().getSelectedRow(), 0).toString();
                ControllerRenter rdc = new ControllerRenter(name,this);
                rdc.view.setOnTop(true);
            }
            catch(ArrayIndexOutOfBoundsException ex){
                
            }
        }
    }
    
}
