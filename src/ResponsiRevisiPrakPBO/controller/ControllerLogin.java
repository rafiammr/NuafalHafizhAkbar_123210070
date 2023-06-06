/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ResponsiRevisiPrakPBO.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import ResponsiRevisiPrakPBO.model.AccountModel;
import ResponsiRevisiPrakPBO.view.LoginPageView;
import ResponsiRevisiPrakPBO.helper.accHelper;

/**
 *
 * @author Naufal
 */
public class ControllerLogin implements ActionListener {
    
    LoginPageView view;
    public ControllerLogin(){
        view = new LoginPageView();
        view.getBlogin().addActionListener(this);
    }
    
    public void showPage(boolean stat){
        view.setVisible(stat);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.getBlogin()){
            
            AccountModel acc = new AccountModel();
            acc.setUsername(view.getFusername().getText());
            acc.setPassword(view.getFpassword().getText());
            try{
                accHelper.Login(acc);
                if(acc.getRole()==null){
                    System.out.println("Akun tidak tersedia");
                }
                else if("ADMIN".equals(acc.getRole().toUpperCase())){
                    //navigate to admin page;
                    ControllerAdmin admin = new ControllerAdmin(this);
                    showPage(false);
                }
                else{
                    ControllerRoom renter = new ControllerRoom(this);
                    showPage(false);
                }
            }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
    
}
