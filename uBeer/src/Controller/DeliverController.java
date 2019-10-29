/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DAO.ClienteDAO;
import Model.DAO.DeliveryDAO;
import Model.DAO.PedidoDAO;
import Model.Pedido;
import Model.Produto;
import View.ConfigUbeer;
import View.DeliverUbeer;
import View.MotoboyConfigUbeer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 * @author Eduardo C.
 */
public class DeliverController implements ActionListener, MouseListener, ItemListener {

    DeliverUbeer frmDeliver;
    private PedidoDAO pedidoDAO;
    private ArrayList<Produto> produtos;
    private ArrayList<Pedido> pedidosdel;
    private DeliveryDAO deliveryDAO;
    
    public DeliverController(DeliverUbeer tela) {
        this.frmDeliver = tela;
        this.pedidoDAO = new PedidoDAO();
        produtos = new ArrayList<>();
        pedidosdel = new ArrayList<>();
        this.deliveryDAO = new DeliveryDAO();
        
        
        int estadomotoboy = this.frmDeliver.estadomotoboy.getSelectedIndex();
        estadomotoboy= new DeliveryDAO().buscaAtivo(frmDeliver.user,frmDeliver.psw);
        this.frmDeliver.estadomotoboy.setSelectedIndex(estadomotoboy);
        this.frmDeliver.estadomotoboy.addItemListener(this);
        this.frmDeliver.btnEntregar.addActionListener(this);
        this.frmDeliver.btnDetalhes.addActionListener(this);
        this.frmDeliver.jLabel14.addMouseListener(this);
        this.frmDeliver.jLabel13.addMouseListener(this);
        this.frmDeliver.lblRefresh.addMouseListener(this);
        this.frmDeliver.lblFechar.addMouseListener(this);
        this.frmDeliver.btnConfig.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frmDeliver.btnEntregar) {
            int selectedIndex = frmDeliver.listPedido.getSelectedIndex();
            String itemselecionado = frmDeliver.listPedido.getSelectedValue();
            String[] itemselecionadosplit=itemselecionado.split(" - Nº: ");
            this.pedidoDAO.entregaPedido(itemselecionadosplit[1]);
            DefaultListModel novaLista = frmDeliver.listModel;
            novaLista.remove(selectedIndex);
            frmDeliver.pnlProdutos.setVisible(false);
        } else if (e.getSource() == frmDeliver.btnDetalhes) { 
            // ADICIONAR LÓGICA PARA PUXAR DO BANCO OS PEDIDOS, E COLOCAR
            //NO ARRAYLIST PEDIDOS E SUBSTITUIR NO FOR O ARRAYLIST FRMDELIVER.PEDIDOS PELO ARRAY QUE VEIO DO BANCO
            String itemselecionado = frmDeliver.listPedido.getSelectedValue();
            int aux=Integer.parseInt(itemselecionado.split(" - Nº: ")[1]);
            int selectedIndex = frmDeliver.listPedido.getSelectedIndex();
            int contPicanha = 0;
            int contMaminha = 0;
            int contCarvao = 0;
            int contCerva = 0;
            for (Pedido pedido : this.pedidosdel) {
                if (pedido.getIdpedido() == aux) {
                    for (int i = 0; i < pedido.getProdutos().size(); i++) {
                        if (pedido.getProdutos().get(i).getNome().equals("Picanha")) {
                            contPicanha++;
                        } else if (pedido.getProdutos().get(i).getNome().equals("Maminha")) {
                            contMaminha++;
                        } else if (pedido.getProdutos().get(i).getNome().equals("Carvão")) {
                            contCarvao++;
                        } else if (pedido.getProdutos().get(i).getNome().equals("Cerveja")) {
                            contCerva++;
                        }
                    }

                }
            }
            frmDeliver.lblQntCarvao.setText("(x" + contCarvao + ")");
            frmDeliver.lblQntCerveja.setText("(x" + contCerva + ")");
            frmDeliver.lblQntMaminha.setText("(x" + contMaminha + ")");
            frmDeliver.lblQntPicanha.setText("(x" + contPicanha + ")");
            frmDeliver.pnlProdutos.setVisible(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == frmDeliver.jLabel14) {
            frmDeliver.cl.previous(frmDeliver.jPanel1);
        }else if (e.getSource() == frmDeliver.jLabel13) {
            frmDeliver.cl.next(frmDeliver.jPanel1);
        }else if (e.getSource() == frmDeliver.lblRefresh) {
            //PEGAR LISTA DE PEDIDOS DO BANCO
            this.pedidosdel=this.pedidoDAO.buscaPedidosDisponiveis();
            int cont = 0;
            frmDeliver.listModel = new DefaultListModel();
            for (int i = 0; i < this.pedidosdel.size(); i++) {
                frmDeliver.listModel.add(cont, new ClienteDAO().buscaNomePorId(this.pedidosdel.get(i).getIdcliente())+" - Nº: "+this.pedidosdel.get(i).getIdpedido());
            }
            frmDeliver.listPedido.setModel(frmDeliver.listModel);
        }else if (e.getSource() == this.frmDeliver.lblFechar) {
            System.exit(0);
        }else if (e.getSource() == this.frmDeliver.btnConfig) {
            System.out.println(frmDeliver.user);
            System.out.println(frmDeliver.psw);
            MotoboyConfigUbeer novaTela = new MotoboyConfigUbeer(this.frmDeliver.userPermission,frmDeliver.user,frmDeliver.psw);
            MotoboyConfigController ttt = new MotoboyConfigController(novaTela);
            novaTela.setVisible(true);
            this.frmDeliver.dispose();
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

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == this.frmDeliver.estadomotoboy){
            new DeliveryDAO().mudarEstado(this.frmDeliver.estadomotoboy.getSelectedIndex(), frmDeliver.user,frmDeliver.psw);
        }
    }
}
