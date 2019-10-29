/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.DAO.ClienteDAO;
import Model.DAO.DeliveryDAO;
import Model.Delivery;
import View.DeliverUbeer;
import View.MotoboyConfigUbeer;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Eduardo C.
 */
public class MotoboyConfigController implements MouseListener{
    MotoboyConfigUbeer frmConfig;
    Delivery delivery;

    public MotoboyConfigController(MotoboyConfigUbeer mcu) {
        this.frmConfig = mcu;
        System.out.println(this.frmConfig.user+this.frmConfig.psw);
        this.delivery = new DeliveryDAO().dadosdelivery(frmConfig.user, frmConfig.psw);
        this.frmConfig.btnCart.addMouseListener(this);
        this.frmConfig.btnattcad.addMouseListener(this);
        preenchecampos();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == this.frmConfig.btnCart) {
            DeliverUbeer novaTela = new DeliverUbeer(this.frmConfig.userPermission);
            DeliverController dc = new DeliverController(novaTela);
            novaTela.setVisible(true);
            novaTela.setLocation(this.frmConfig.getX(), this.frmConfig.getY());
            this.frmConfig.dispose();
        }
        if(e.getSource() == this.frmConfig.btnattcad){
            this.delivery.setEmail(this.frmConfig.lblattemail.getText());
            this.delivery.setSenha(this.frmConfig.lblattsenha.getText());
            System.out.println(this.delivery.getIddelivery());
            new DeliveryDAO().atualizadelivery(this.delivery);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public void preenchecampos(){
        frmConfig.lblattemail.setText(this.delivery.getEmail());
        frmConfig.lblattsenha.setText(this.delivery.getSenha());
    }
}
