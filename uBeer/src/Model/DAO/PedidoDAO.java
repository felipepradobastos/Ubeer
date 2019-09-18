/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.Pedido;
import Model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author felip
 */
public class PedidoDAO {
    BancoConnect bd;
    public PedidoDAO(){
        bd = new BancoConnect();
        bd.connect();
    }
    
    public ArrayList<Pedido> buscaPedidosDisponiveis(){
        ArrayList<Produto> produtos;
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try {
            Pedido pedido;
            String query = "SELECT * FROM pedido WHERE entregafeita=0";
            ResultSet rs = bd.getStmt().executeQuery(query);
            while(rs.next()){
                produtos = new ArrayList<>();
                pedido = new Pedido();
                pedido.setIdelivery(rs.getInt("iddelivery"));
                pedido.setIdcliente(rs.getInt("idcliente"));
                pedido.setIdpedido(rs.getInt("idpedido"));
                pedido.setIdpagamento(rs.getInt("idpagamento"));
                pedido.setEntregafeita(rs.getInt("entregafeita"));
                produtos = new ProdutoDAO().buscaProdutosPorID(new PedidoxProdutoDAO().buscaIdProdutos(pedido.getIdpedido()));
                pedido.setProdutos(produtos);
                pedidos.add(pedido);
            }
            
        } catch (Exception e) {
        }
        return pedidos;
    }
    
    
    public ArrayList<Pedido> buscapedidoepreenche(int iddelivery){
        try {
            ArrayList<Pedido> pedidos = new ArrayList<>();
            Pedido pedido;
            Connection conn = bd.getConnection();
            String query = "SELECT * FROM pedido where iddelivery=?";
            PreparedStatement ps;   
            ps = conn.prepareStatement(query);
            ps.setInt(1, iddelivery);
            bd.setResult(ps.executeQuery());
            while(bd.getResult().next()){
               pedido= new Pedido();
               pedido.setIdpedido(bd.getResult().getInt("idpedido"));
               pedido.setProdutos(this.buscaentregas(this.buscaidprodutosentrga(pedido.getIdpedido())));
               pedidos.add(pedido);
            }    
            return pedidos;
        } catch (SQLException e) {
                System.out.println("Erro:"+e.getMessage());
        }
        return null;
    }  
    public int buscadelivery(String user,String psw){
        try {
            int id=0;
            Connection conn = bd.getConnection();
            String query = "SELECT idpedido FROM pedido where iddelivery = (SELECT iddelivery from delivery where usuario=? and senha=?) and entregafeita=0";
            PreparedStatement ps;   
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, psw);
            bd.setResult(ps.executeQuery());
            if (bd.getResult().next()) {
                id=bd.getResult().getInt("idpedido"); 
            }
            return id;
    
        } catch (SQLException e) {
            System.out.println("Erro:"+e.getMessage());
        }
        return 0;
    }
    public ArrayList<Integer> buscaidprodutosentrga(int idpedido){
        try {
            ArrayList<Integer> produtos = new ArrayList<>();
            int id;
            String query = "SELECT idproduto FROM pedidoxproduto WHERE idpedido=?";
            Connection conn = bd.getConnection();
            PreparedStatement ps;   
            ps = conn.prepareStatement(query);
            ps.setInt(1, idpedido);
            bd.setResult(ps.executeQuery());
            while(bd.getResult().next()){
                id=bd.getResult().getInt("idproduto");
                produtos.add(id);
            }
            return produtos;
        } catch (SQLException e) {
            System.out.println("Erro: "+e.getMessage());
        }
        return null;
    }
    public ArrayList<Produto> buscaentregas(ArrayList<Integer> ids){
        try {
            ArrayList<Produto> produtos = new ArrayList<>();
            Produto produto;
            Connection conn = bd.getConnection();
            PreparedStatement ps;   
            for(int i=0;i<ids.size();i++){
                String query = "SELECT * from produto WHERE idproduto=?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, ids.get(i));
                bd.setResult(ps.executeQuery());
                while(bd.getResult().next()){
                    produto = new Produto();
                    produto.setNome(bd.getResult().getString("nome"));
                    produto.setValor(bd.getResult().getDouble("valor"));
                    produto.setEspecialidade(bd.getResult().getString("especialidade"));
                    produtos.add(produto);
                }
            }
            return produtos;
        } catch (SQLException e) {
            System.out.println("Erro:"+e.getMessage());
        }
        return null;
    }
    public boolean addpedido(Pedido pedido,double valortotal){
        try {
            String query = "insert into pedido (idcliente,iddelivery,idpagamento,valortotal,entregafeita) values ("+pedido.getIdcliente()+","+pedido.getIdelivery()+","+pedido.getIdpagamento()+","+valortotal+","+pedido.getEntregafeita()+");";
            System.out.println(query);
            bd.getStmt().executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println("Erro: "+e.getMessage());
        }
        return false;
    }
    
        public void addpedidooutro(Pedido pedido, double valortotal){
            try {
                Connection conn = bd.getConnection();
                String query = "INSERT INTO pedido (idcliente,iddelivery,idpagamento,valortotal,entregafeita) VALUES (?,?,?,?,?) ";
                PreparedStatement ps;   
                ps = conn.prepareStatement(query);
                ps.setInt(1, pedido.getIdcliente());
                ps.setInt(2, pedido.getIdelivery());
                ps.setInt(3, pedido.getIdpagamento());
                ps.setDouble(4, valortotal);
                ps.setInt(5, 0);
                ps.executeUpdate();
                
            } catch (SQLException e) {
                System.out.println("Erro:"+e.getMessage());
            }
        }
    
    // Delete Pedido
    
    public boolean removepedido(int idpedido){
        try {
            String query = "delete from pedido where idpedido="+idpedido+";";
            bd.getStmt().executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
        return false;
    }
    
//    public boolean atualizapedido(Pedido pedido){
//        String query="update pedido set ";
//        try {
//            bd.getStmt().executeUpdate(query);
//        } catch (Exception e) {
//            System.out.println("Erro:"+e.getMessage());
//        }
//        return false;
//    }
//    
    // Select Pedido
    
    public ResultSet dadospedido(int idpedido){
        try {
            String query= "select * from pedidowhere idpedido="+idpedido+";";
            bd.setResult(bd.getStmt().executeQuery(query));
            return bd.getResult();
        } catch (Exception e) {
            System.out.println("Error :"+e.getMessage());
        }
        return null;
    }
    
    // Select All Pedidos
    
    public ResultSet dadosallpedido(){
        try {
            String query= "select * from pedido;";
            bd.setResult(bd.getStmt().executeQuery(query));
            return bd.getResult();
        } catch (Exception e) {
            System.out.println("Error :"+e.getMessage());
        }
        return null;
    }

    public void entregaPedido(String string) {
        try {
            String query = "UPDATE pedido set entregafeita=1 where idpedido="+string;
            bd.getStmt().executeUpdate(query);
        } catch (Exception e) {
        }
    }
}
