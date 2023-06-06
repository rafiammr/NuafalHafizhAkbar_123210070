/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ResponsiRevisiPrakPBO.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ResponsiRevisiPrakPBO.helper.renterhelper;
import ResponsiRevisiPrakPBO.helper.helperRoom;
import ResponsiRevisiPrakPBO.model.RenterModel;
import ResponsiRevisiPrakPBO.view.RenterDataView;

/**
 *
 * @author Naufal
 */
public class ControllerRenter implements ActionListener{

    RenterModel renter;
    RenterDataView view;
    ControllerAdmin ac;
    String room2;
    public ControllerRenter(RenterModel r, ControllerAdmin parent){
        this.renter=r;
        this.ac = parent;
        view = new RenterDataView();
        view.setOnTop(true);
        view.setTfContact(renter.getContact());
        view.setTfName(renter.getNama());
        view.setTfRentTime(String.valueOf(renter.getDuration()));
        view.setTfid(String.valueOf(renter.getId()));
        
        //add listener
        view.getBtnAddPanel().addActionListener(this);
        view.getBtnLogout().setVisible(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.getBtnAddPanel()){
            if(view.getBtnLogout().isVisible()==true){
                System.out.println("added clicked");
                RenterModel renter = new RenterModel();
                renter.setNama(view.getTfName().getText());
                renter.setContact(view.getTfContact().getText());
                renter.setDuration(Integer.parseInt(view.getTfRentTime().getText().toString()));
                renter.setId(Integer.parseInt(view.getTfid().getText().toString()));
                renter.setRoom(room2);
                rlc.updateTable();
                try {
                    renterhelper.insert(renter);
                    helperRoom.update(renter.getRoom(),renter.getNama());
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerRenter.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally{
                    view.getWindow().dispose();
                }
            }
            else{
                renter.setContact(view.getTfContact().getText());
                renter.setId(Integer.parseInt(view.getTfid().getText()));
                renter.setDuration(Integer.parseInt(view.getTfRentTime().getText()));
                renter.setNama(view.getTfName().getText());
                try {
                    renter.setBill(renter.calculateTotalBill(helperRoom.selectPrice(renter.getRoom())));
                    renterhelper.update(renter);
                    view.getWindow().dispose();
                    ac.updateTable();
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerRenter.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch(ArrayIndexOutOfBoundsException aex){
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,aex.getCause()+"\n"+aex.getMessage());
                }
            }
        }
        else if(e.getSource()==view.getBtnLogout()){
            view.getWindow().dispose();
        }
    }
    
    ControllerRoom rlc;
    public ControllerRenter(String room, ControllerRoom parent){
        view = new RenterDataView();
        this.room2=room;
        this.rlc = parent;
        view.getBtnAddPanel().addActionListener(this);
        view.getBtnLogout().addActionListener(this);
    }
    
}
