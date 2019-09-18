/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.Cliente;
import Model.Delivery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author felip
 */
public class DeliveryDAO {
        
        BancoConnect bd;
        
    public DeliveryDAO(){
        bd = new BancoConnect();
        bd.connect();
    }        
    
    public boolean loginverify(String login, String senha) {
        boolean autenticado = false;
        String sql;
        try {
            Connection conn = bd.getConnection();

            sql = "SELECT iddelivery,usuario,senha FROM delivery WHERE usuario=? and senha=?";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, senha);

            ResultSet rs;
            rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("iddelivery");
                autenticado = true;
            } else {
                ps.close();
                return autenticado;
            }
        } catch (SQLException e) {
            System.out.println("Erro:" + e.getMessage());
        }
        return autenticado;
    }
    public boolean adddelivery(Delivery delivery){
        try {
            Connection conn = bd.getConnection();
            String query = "INSERT INTO delivery (usuario,senha,email,cnh,disponivel,ativo) VALUES("+"'"+delivery.getUsuario()+"','"+delivery.getSenha()+"','"+delivery.getEmail()+"','"+delivery.getCnh()+"',1,0)";
//            PreparedStatement ps;
//            ps = conn.prepareStatement(query);
//            ps.setString(1, delivery.getUsuario());
//            ps.setString(2, delivery.getSenha());
//            ps.setString(3, delivery.getEmail()); 
//            ps.executeUpdate(query);
              bd.getStmt().executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Erro: "+e.getMessage());
        }
        return true;
    }
    
    // Delete Delivery
    
    public int designaentregador(){
        int id = 0;
        try {
            String query = "select * from delivery where ativo=1 and disponivel=1";
            bd.setResult(bd.getStmt().executeQuery(query));
            if(bd.getResult().next()){
              id = bd.getResult().getInt("iddelivery");
            }
            return id;
        } catch (SQLException e) {
            System.out.println("Erro:"+e.getMessage());
        }
        return id;
    }
    public int findelivery(String user, String psw){
        int id = 0;
        try {
            Connection conn = bd.getConnection();
            PreparedStatement ps;   
            String query = "select * from delivery where usuario=? and senha=? ";
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, psw);
            bd.setResult(ps.executeQuery());
            if(bd.getResult().next()){
              id = bd.getResult().getInt("iddelivery");
            }
            return id;
        } catch (SQLException e) {
            System.out.println("Erro:"+e.getMessage());
        }
        return id;
    }
    
    
    
    public boolean removedelvery(int iddelivery){
        try {
            String query = "delete from delivery where iddelivery="+iddelivery+";";
            bd.getStmt().executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
        return false;
    }
    
    public boolean atualizadelivery(Delivery delivery){
        String query ="update delivery set senha='"+delivery.getSenha()+"',email='"+delivery.getEmail()+"' where iddelivery="+delivery.getIddelivery();
        System.out.println(query);
        try {
            bd.getStmt().executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println("Erro:"+ e.getMessage());
        }
        return false;
    }
    
    // Select Delivery
    
    public Delivery dadosdelivery(String user, String psw){
        Delivery delivery = new Delivery();
        try {
            String query= "SELECT * FROM delivery WHERE usuario='"+user+"' and senha='"+psw+"'";
            ResultSet rs = bd.getStmt().executeQuery(query);
            while(rs.next()){
                delivery.setUsuario(rs.getString("usuario"));
                delivery.setCnh(rs.getString("cnh"));
                delivery.setIddelivery(rs.getInt("iddelivery"));
                delivery.setSenha(rs.getString("senha"));
                delivery.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
            System.out.println("Error :"+e.getMessage());
        }
        return delivery;
    }
    
    // Select All Delivery
    
    public ResultSet dadosalldelivery(){
        try {
            String query= "select * from delivery;";
            bd.setResult(bd.getStmt().executeQuery(query));
            return bd.getResult();
        } catch (Exception e) {
            System.out.println("Error :"+e.getMessage());
        }
        return null;
    }

    public int buscaAtivo(String user, String psw) {
            int ativo =0;
        try {
            String query = "Select ativo from delivery where usuario='"+user+"' and senha='"+psw+"'";
            ResultSet rs = bd.getStmt().executeQuery(query);
            while(rs.next()){
                ativo = rs.getInt("ativo");
            }
        } catch (Exception e) {
        }
        return ativo;
    }

    public void mudarEstado(int selectedIndex, String user, String psw) {
        try {
            String query = "Update delivery set ativo="+selectedIndex+" where usuario='"+user+"' and senha='"+psw+"'";
            bd.getStmt().executeUpdate(query);
        } catch (Exception e) {
            
        }
    }
    
}
