/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Cliente;
import Model.DAO.ClienteDAO;
import Model.DAO.DeliveryDAO;
import Model.Delivery;
import View.ComprasUbeer;
import View.DeliverUbeer;
import View.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author aluno
 */
public class LoginController implements ActionListener, MouseListener {

    //PARA INICIAR A TELA DE HOME, RECEBER DO CHAMADO UM INTEIRO QUE CORRESPONDE AO NÍVEL DE ACESSO DO USUÁRIO.
    //ESSE CONTROLER VAI REDIRECIONAR PARA A TELA CORRESPONDENTE, CASO 0 = CLIENTE, 1 = MOTOBOY
    Login frmlogin;
    ClienteDAO clienteDAO;
    DeliveryDAO deliveryDAO;

    public LoginController(Login login) {
        this.frmlogin = login;
        this.frmlogin.btnCliente.addActionListener(this);
        this.frmlogin.btnMotoboy.addActionListener(this);
        this.frmlogin.btnSliderLogin.addActionListener(this);
        this.frmlogin.btnSliderVoltar.addActionListener(this);
        this.frmlogin.btnRegistrarConta.addActionListener(this);
        this.frmlogin.btnLogin.addActionListener(this);
        this.frmlogin.lblFechar.addMouseListener(this);
        clienteDAO = new ClienteDAO();
        deliveryDAO = new DeliveryDAO();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == frmlogin.btnCliente) {
            frmlogin.setSize(700, 400);
            frmlogin.setLocationRelativeTo(null);
            frmlogin.clBtn.next(frmlogin.pnlBtnSlider);
            frmlogin.userPermission = 0;
        } else if (ae.getSource() == frmlogin.btnMotoboy) {
            frmlogin.setSize(700, 400);
            frmlogin.setLocationRelativeTo(null);
            frmlogin.clBtn.next(frmlogin.pnlBtnSlider);
            frmlogin.userPermission = 1;
        } else if (ae.getSource() == frmlogin.btnSliderLogin) {
            if (frmlogin.btnSliderLogin.getText().equals("REGISTRAR")) {
                frmlogin.btnSliderLogin.setText("LOGIN");
            } else {
                frmlogin.btnSliderLogin.setText("REGISTRAR");
            }
            frmlogin.clSign.next(frmlogin.pnlSign);
        } else if (ae.getSource() == frmlogin.btnSliderVoltar) {
            frmlogin.clBtn.previous(frmlogin.pnlBtnSlider);
            frmlogin.setSize(300, 400);
            frmlogin.setLocationRelativeTo(null);
        } else if (ae.getSource() == frmlogin.btnRegistrarConta) {
            //ADICIONAR LÓGICA DE CADASTRAR CONTA ANTES DAS LINHAS ABAIXO:
            if(frmlogin.userPermission==0){
                Cliente clientecad = new Cliente();
                clientecad.setEmail(frmlogin.fieldcadastroemail.getText());
                clientecad.setUsuario(frmlogin.fieldcadastrologin.getText());
                clientecad.setSenha(frmlogin.fieldcadastropsw.getText());
                clientecad.setEnder(frmlogin.fieldcadbairro.getText());
                if(clientecad.setCpf(frmlogin.fieldcadcpf.getText())){
                    if(clienteDAO.addcliente(clientecad)){
                        JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso !");
                    }else{
                        JOptionPane.showMessageDialog(null, "Ocorreu um problema :( ");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "CPF INVÁLIDO !");
                }
                    
            }
            if(frmlogin.userPermission==1){
                Delivery deliverycad = new Delivery();
                deliverycad.setEmail(frmlogin.fieldcadastroemail.getText());
                deliverycad.setUsuario(frmlogin.fieldcadastrologin.getText());
                deliverycad.setSenha(frmlogin.fieldcadastropsw.getText());
                deliverycad.setCnh(frmlogin.fieldcadcpf.getText());
                if(deliveryDAO.adddelivery(deliverycad)){
                    JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso !");
                }else{
                    JOptionPane.showMessageDialog(null, "Ocorreu um problema :( ");
                }
                
            }
            limparCampos();
        } else if (ae.getSource() == frmlogin.btnLogin) {
            //ADICIONAR LÓGICA DE LOGIN ANTES DAS LINHAS ABAIXO
            if (frmlogin.userPermission == 0) {
                
                boolean autenticar = clienteDAO.loginverify(frmlogin.fieldlogin.getText(), frmlogin.fieldpsw.getText());
                if(autenticar==true){
                    ComprasUbeer novaTela = new ComprasUbeer(frmlogin.userPermission,frmlogin.fieldlogin.getText(),frmlogin.fieldpsw.getText());
                    ComprasUbeerController hc = new ComprasUbeerController(novaTela);
                    novaTela.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Usúario/Senha Incorretos");
                }
            }
            if (frmlogin.userPermission == 1) {
                
//                boolean autenticardel = deliveryDAO.loginverify(frmlogin.loginfield.getText(), frmlogin.fieldpsw.getText());
                boolean autenticardel = this.deliveryDAO.loginverify(frmlogin.fieldlogin.getText(), frmlogin.fieldpsw.getText());
                if(autenticardel==true){
                    DeliverUbeer novaTela = new DeliverUbeer(frmlogin.userPermission,frmlogin.fieldlogin.getText(),frmlogin.fieldpsw.getText());
                    DeliverController bhc = new DeliverController(novaTela);
                    novaTela.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Usúario/Senha Incorretos");
                }
            }
            frmlogin.dispose();
        }
    }

    private void limparCampos() {
        frmlogin.fieldcadastroemail.setText("");
        frmlogin.fieldcadbairro.setText("");
        frmlogin.fieldcadastrologin.setText("");
        frmlogin.fieldcadastropsw.setText("");
        frmlogin.fieldcadcpf.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == frmlogin.lblFechar) {
            System.exit(0);
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

}
