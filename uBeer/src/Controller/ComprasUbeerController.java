/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Cliente;
import Model.DAO.ClienteDAO;
import Model.DAO.DeliveryDAO;
import Model.DAO.PedidoDAO;
import Model.DAO.PedidoxProdutoDAO;
import Model.DAO.ProdutoDAO;
import Model.Delivery;
import Model.Pedido;
import Model.PedidoxProduto;
import Model.Produto;
import View.ComprasUbeer;
import View.ConfigUbeer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 * @author Eduardo C.
 */

public class ComprasUbeerController implements ActionListener, MouseListener, KeyListener {

    private ComprasUbeer frmCompras;
    private PedidoDAO pedidoDAO;
    private ClienteDAO clienteDAO;
    private ProdutoDAO produtoDAO;
    private DeliveryDAO deliveryDAO;
    private PedidoxProdutoDAO pedidoxprodutoDAO;
    private Pedido novoPedido;
    
    private Delivery delivery;
    private Cliente cliente;
    private ArrayList<Produto> produtos;
    private Produto produto;

    public ComprasUbeerController(ComprasUbeer tela) {
        this.frmCompras = tela;
        inicializarvariavel();
        this.pedidoDAO = new PedidoDAO();
        this.frmCompras.btnComprarPicanha.addActionListener(this);
        this.frmCompras.btnComprarMaminha.addActionListener(this);
        this.frmCompras.btnComprarCarvao.addActionListener(this);
        this.frmCompras.btnComprarCerveja.addActionListener(this);
        this.frmCompras.btnFinalizarPedido.addActionListener(this);
        this.frmCompras.btnLimparCarrinho.addActionListener(this);
        this.frmCompras.btnConfig.addMouseListener(this);
        this.frmCompras.lblFechar.addMouseListener(this);
        this.frmCompras.jLabel13.addMouseListener(this);
        this.frmCompras.jLabel14.addMouseListener(this);
        this.frmCompras.listCart.addKeyListener(this);
        
        this.pedidoxprodutoDAO = new PedidoxProdutoDAO();
        this.delivery = new Delivery();
        this.cliente = new Cliente();
        this.produtos = new ArrayList<>();
        this.novoPedido = new Pedido();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
   
        if (e.getSource() == this.frmCompras.btnComprarPicanha) {
            int idProduto = 0;//ADICIONAR LÓGICA PARA PEGAR ID DO PRODUTO DO BANCO WHERE NOME = PICANHA
            this.produto=new Produto();
            this.produto.tentativa("picanha");
            this.novoPedido.adicionarproduto(produto);
            adicionarAoCarrinho(idProduto);
        } else if (e.getSource() == this.frmCompras.btnComprarMaminha) {
            int idProduto = 1;//ADICIONAR LÓGICA PARA PEGAR ID DO PRODUTO DO BANCO WHERE NOME = MAMINHA
            this.produto=new Produto();
            this.produto.tentativa("maminha");
            this.novoPedido.adicionarproduto(produto);
            adicionarAoCarrinho(idProduto);
        } else if (e.getSource() == this.frmCompras.btnComprarCarvao) {
            int idProduto = 2;//ADICIONAR LÓGICA PARA PEGAR ID DO PRODUTO DO BANCO WHERE NOME = CARVAO
            this.produto=new Produto();
            this.produto.tentativa("carvao");
            System.out.println(produto.getValor());
            this.novoPedido.adicionarproduto(produto);
            adicionarAoCarrinho(idProduto);
        } else if (e.getSource() == this.frmCompras.btnComprarCerveja) {
            int idProduto = 3;//ADICIONAR LÓGICA PARA PEGAR ID DO PRODUTO DO BANCO WHERE NOME = CERVEJA
            this.produto=new Produto();
            this.produto.tentativa("cerveja");
            this.novoPedido.adicionarproduto(produto);
            adicionarAoCarrinho(idProduto);
        } else if (e.getSource() == this.frmCompras.btnFinalizarPedido) {
            //Pedido novoPedido = new Pedido();
            Calendar cal = Calendar.getInstance();
            this.novoPedido.setDatacompra(cal.getTime().toString());
            delivery.tentativa();
            this.novoPedido.setIdelivery(delivery.getIddelivery());
            cliente.tentativa(frmCompras.user, frmCompras.psw);
            this.novoPedido.setIdcliente(cliente.getIdcliente());
            this.novoPedido.setIdpagamento(1);
            this.novoPedido.setEntregafeita(0);
            JOptionPane.showMessageDialog(null, "O valor do seu pedido foi de: "+this.novoPedido.valortotal()+"\n Metodo de Pagamento: Dinheiro");
            this.pedidoDAO.addpedidooutro(this.novoPedido, this.novoPedido.valortotal());
            this.pedidoxprodutoDAO.insereprodutosbanco(this.novoPedido);
            
            limparCarrinho();
        } else if (e.getSource() == this.frmCompras.btnLimparCarrinho) {
            limparCarrinho();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == this.frmCompras.lblFechar) {
            System.exit(0);
        } else if (e.getSource() == this.frmCompras.jLabel13) {
            this.frmCompras.cl.next(this.frmCompras.jPanel1);
        } else if (e.getSource() == this.frmCompras.jLabel14) {
            this.frmCompras.cl.previous(this.frmCompras.jPanel1);
        } else if (e.getSource() == this.frmCompras.btnConfig) {
            ConfigUbeer novaTela = new ConfigUbeer(this.frmCompras.userPermission,frmCompras.user,frmCompras.psw);
            ConfigController xhc = new ConfigController(novaTela);
            novaTela.setVisible(true);
            novaTela.setLocation(this.frmCompras.getX(), this.frmCompras.getY());
            this.frmCompras.dispose();
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
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == this.frmCompras.listCart) {
            if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                if (JOptionPane.showConfirmDialog(null, "Tem certeza que quer excluir?") == 0) {
                    removerDoCarrinho(this.frmCompras.listCart.getSelectedIndex());
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void removerDoCarrinho(int selectedIndex) {
        DefaultListModel novaLista = this.frmCompras.listModel;
        novaLista.remove(selectedIndex);
        this.frmCompras.listCart.setModel(novaLista);
    }
    
    public void limparCarrinho(){
        DefaultListModel novaLista = new DefaultListModel();
        this.frmCompras.listModel = novaLista;
        this.frmCompras.listCart.setModel(novaLista);
        this.novoPedido = new Pedido();
    }
    
    public void adicionarAoCarrinho(int idProduto){
        DefaultListModel novaLista = new DefaultListModel();
        switch(idProduto){
            case 0:
                novaLista = this.frmCompras.listModel;
                novaLista.add(novaLista.getSize(), "Picanha");
                this.frmCompras.listCart.setModel(novaLista);
                break;
            case 1:
                novaLista = this.frmCompras.listModel;
                novaLista.add(novaLista.getSize(), "Maminha");
                this.frmCompras.listCart.setModel(novaLista);
                break;
            case 2:
                novaLista = this.frmCompras.listModel;
                novaLista.add(novaLista.getSize(), "Carvão");
                this.frmCompras.listCart.setModel(novaLista);
                break;
            case 3:
                novaLista = this.frmCompras.listModel;
                novaLista.add(novaLista.getSize(), "Cerveja");
                this.frmCompras.listCart.setModel(novaLista);
                break;
        }
    }
    
    public ComprasUbeer getTela() {
        return frmCompras;
    }

    public void setTela(ComprasUbeer tela) {
        this.frmCompras = tela;
    }

    public PedidoDAO getPedidoDAO() {
        return pedidoDAO;
    }

    public void setPedidoDAO(PedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

    private void inicializarvariavel() {
        frmCompras.lblprecocarvao.setText("R$: "+ new ProdutoDAO().buscaPrecoPorNome("Carvao")+ "/KG");
        frmCompras.lblprecomaminha.setText("R$: "+ new ProdutoDAO().buscaPrecoPorNome("Maminha")+" /KG");
        frmCompras.lblprecocerveja.setText("R$: "+ new ProdutoDAO().buscaPrecoPorNome("Cerveja")+" /LATA");
        frmCompras.lblprecopicanha.setText("R$: "+ new ProdutoDAO().buscaPrecoPorNome("Picanha")+" /KG");
    }

}