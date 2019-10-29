/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.Pedido;
import Model.Produto;
import com.mysql.cj.protocol.Resultset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aluno
 */
public class PedidoxProdutoDAO {
    
    BancoConnect bd;
    public PedidoxProdutoDAO(){
        bd = new BancoConnect();
        bd.connect();
    }
    
    public ArrayList<Integer> buscaIdProdutos(int idpedido){
        ArrayList<Integer> numeros = new ArrayList<Integer>();
        Pedido pedido;
        int number=0;
        try {
            String query = "SELECT * FROM pedidoxproduto WHERE idpedido="+idpedido;
            ResultSet rs = bd.getStmt().executeQuery(query);
            while(rs.next()){
                number=rs.getInt("idproduto");
                numeros.add(number);
            }
        } catch (SQLException e) {
            System.out.println("Erro:"+ e.getMessage());
        }
        return numeros;
    }
    
    // Implementar Hora da compra;
//    public void metodosemnome(Pedido pedido){
//        try {
//            for (int i = 0; i < pedido.getProdutos().size() ; i++) {
//                String query = "insert into pedidoxproduto (idpedido,idproduto) values(?,?)";
//                bd.ps.setString(1, this.retornaidpedido());
//                bd.ps.setString(2, Integer.toString(pedido.getProdutos().get(i).getIdproduto()));
//                bd.ps.executeUpdate(query);
//            }
//        } catch (SQLException ex) {
//            System.out.println("ERRO:"+ex.getMessage());
//        }
//        
//    }
    
    public void insereprodutosbanco(Pedido pedido){
        try {
            Connection conn = bd.getConnection();
            System.out.println("SIZE: "+pedido.getProdutos().size());
            for(int i=0; i<pedido.getProdutos().size();i++){
                String query = "INSERT INTO pedidoxproduto (idpedido,idproduto) VALUES (?,?) ";
                PreparedStatement ps;   
                ps = conn.prepareStatement(query);
                ps.setInt(1, this.retornaidpedido());
                ps.setInt(2, pedido.getProdutos().get(i).getIdproduto());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Erro:"+e.getMessage());
        }
    }
//    public int retornaidpedido(){
//        String query = "SELECT MAX(idpedido) from pedido";
//        try {
//            bd.setResult(bd.getStmt().executeQuery(query));
//            if(bd.getResult().next()){
//                System.out.println("Idpedido ="+bd.getResult().getInt("idpedido"));
//                return bd.getResult().getInt("idpedido");
//            }
//        } catch (SQLException ex) {
//            System.out.println("Erro: "+ex.getMessage());
//        }
//        return 0;
//        }
//    }
       
    public int retornaidpedido(){
        try {
            int id = 0;
            Connection conn = bd.getConnection();
            String query = "SELECT idpedido FROM pedido where idpedido=(SELECT MAX(idpedido) FROM pedido)";
            PreparedStatement ps;   
            ps = conn.prepareStatement(query);
            bd.setResult(ps.executeQuery());
            if(bd.getResult().next()){
                id = bd.getResult().getInt("idpedido");
                System.out.println("IDPEDIDO:" +id);
            }
            return id;
        } catch (Exception e) {
            System.out.println("Erro: "+e.getMessage());
            return 0;
        }
        }
    }
