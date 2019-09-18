/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.Cliente;
import Model.DAO.ClienteDAO;
import Model.DAO.ProdutoDAO;
import View.ConfigUbeer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Eduardo C.
 */
public class ConfigController implements ActionListener, MouseListener{
    ConfigUbeer frmConfig;
    Cliente cliente;
    ClienteDAO clienteDAO;

    public ConfigController(ConfigUbeer frmConfig) {
        this.frmConfig = frmConfig;
        this.cliente = new Cliente();
        this.clienteDAO = new ClienteDAO();
        this.cliente=  new ClienteDAO().dadoscliente(frmConfig.user, frmConfig.psw);
        preenchecampos();
        
        this.frmConfig.btnattcad.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.frmConfig.btnattcad){
            this.cliente.setEmail(this.frmConfig.lblattemail.getText());
            this.cliente.setSenha(this.frmConfig.lblattsenha.getText());
            this.cliente.setEnder(this.frmConfig.lblattender.getText());
            System.out.println(this.cliente.getIdcliente());
            new ClienteDAO().atualizaCliente(this.cliente);
        }
    }

    @Override
    public void mouseClicked(MouseEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void preenchecampos(){
        frmConfig.lblattemail.setText(this.cliente.getEmail());
        frmConfig.lblattender.setText(this.cliente.getEnder());
        frmConfig.lblattsenha.setText(this.cliente.getSenha());
    }

}
