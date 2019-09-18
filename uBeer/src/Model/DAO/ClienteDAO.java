/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author felip
 */
public class ClienteDAO {
    BancoConnect bd;
    
    public ClienteDAO(){
        bd = new BancoConnect();
        bd.connect();
    }
    
    public String buscaNomePorId(int idcliente){
        String usuario ="";
        try {
            String query = "select usuario from cliente where idcliente="+idcliente;
            ResultSet rs = bd.getStmt().executeQuery(query);
            while(rs.next()){
                usuario=rs.getString("usuario");
            }
        } catch (SQLException e) {
            System.out.println("Erro:"+e.getMessage());
        }
            return usuario;
    }
    
    public boolean loginverify(String login, String senha) {
        boolean autenticado = false;
        String query;
        try {
            Connection conn = bd.getConnection();

            query = "SELECT idcliente,usuario,senha FROM cliente WHERE usuario=? and senha=?";
            PreparedStatement ps;
            ps = conn.prepareStatement(query);
            ps.setString(1, login);
            ps.setString(2, senha);

            ResultSet rs;
            rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("idcliente");
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
    public int retornaid(String login, String senha){
                String query;
        try {
            Connection conn = bd.getConnection();

            query = "SELECT idcliente,usuario,senha FROM cliente WHERE usuario=? and senha=?";
            PreparedStatement ps;
            ps = conn.prepareStatement(query);
            ps.setString(1, login);
            ps.setString(2, senha);

            ResultSet rs;
            rs = ps.executeQuery();
            int id;
            if(rs.next()){
                id=rs.getInt("idcliente");
                return id;
            }
        } catch (SQLException e) {
            System.out.println("Erro:" + e.getMessage());
        }
        return 0;
    }
    public boolean addcliente(Cliente cliente){
        try {
            String query = "insert into cliente (usuario,senha,cpf,ender,email) values ("+"'"+cliente.getUsuario()+"'"+","+"'"+cliente.getSenha()+"'"+","+"'"+cliente.getCpf()+"'"+","+"'"+cliente.getEnder()+"'"+","+"'"+cliente.getEmail()+"'"+");";
            System.out.println(query);
            bd.getStmt().executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println("Erro: "+e.getMessage());
        }
        return false;
    }
    
        public boolean removecliente(int idcliente){
        try {
            String query = "delete from cliente where idcliente="+idcliente+";";
            bd.getStmt().executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
        return false;
    }
        
    public boolean atualizacliente(Cliente cliente){
        String query = "update cliente set usuario='"+cliente.getUsuario()+"'"+","+"senha='"+cliente.getSenha()+"'"+","+"cpf='"+cliente.getCpf()+"'"+","+"nome='"+cliente.getNome()+"'"+","+"telefone='"+cliente.getTelefone()+"'"+","+"ender='"+cliente.getEnder()+"'"+","+"email='"+cliente.getEmail()+"'"+","+"premium="+cliente.getPremium()+" where idcliente="+cliente.getIdcliente()+";";
        System.out.println(query);
        try {
            bd.getStmt().executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println("Erro: "+ e.getMessage());
        }
        return false;
    }
    
    
    public Cliente dadoscliente(String user, String psw){
        Cliente cliente = new Cliente();
        try {
            String query= "SELECT * FROM cliente WHERE usuario='"+user+"' and senha='"+psw+"'";
            ResultSet rs = bd.getStmt().executeQuery(query);
            while(rs.next()){
                cliente.setUsuario(rs.getString("usuario"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setIdcliente(rs.getInt("idcliente"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setEnder(rs.getString("ender"));
                cliente.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println("Error :"+e.getMessage());
        }
        return cliente;
    }
    
    public void atualizaCliente(Cliente cliente){
        try {
            String query = "UPDATE CLIENTE SET senha='"+cliente.getSenha()+"',ender='"+cliente.getEnder()+"',email='"+cliente.getEmail()+"' where idcliente="+cliente.getIdcliente();
            System.out.println(query);
            bd.getStmt().executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Erro"+e.getMessage());
        }
    }
    
    public ResultSet dadosallcliente(){
        try {
            String query= "select * from cliente;";
            bd.setResult(bd.getStmt().executeQuery(query));
            return bd.getResult();
        } catch (Exception e) {
            System.out.println("Error :"+e.getMessage());
        }
        return null;
    }
    
}
