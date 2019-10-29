/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

//import Classes.Cliente;
//import Classes.Delivery;
//import Classes.Pagamento;
//import Classes.Pedido;
//import Classes.Produto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



/**
 *
 * @author felip
 */
public class BancoConnect {
    
    private Connection connection;  
    private Statement stmt;
    PreparedStatement ps;
    private ResultSet result;
    
    // Conexão Com Banco de Dados
    
    public boolean connect(){
        String driver="com.mysql.cj.jdbc.Driver";
        String server="jdbc:mysql://127.0.0.1:3306/ubeerdef?useTimezone=true&serverTimezone=UTC";
        String user= "root";
        String psw = "nvak7a3b";
        
        try {
            Class.forName(driver);
            this.setConnection((Connection) DriverManager.getConnection(server,user,psw));
            this.setStmt((Statement) this.getConnection().createStatement());
        } catch (Exception e) {
            System.out.println("Error: "    +e.getMessage());
        }
        if(this.getConnection() !=null){
            return true;
        }
        return false;
    }
    
//    // CRUD CLIENTE
//    
//    // Add Cliente
//    
//    public boolean addcliente(Cliente cliente){
//        try {
//            String query = "insert into cliente (usuario,senha,cpf,nome,telefone,ender,email,premium) values ("+"'"+cliente.getUsuario()+"'"+","+"'"+cliente.getSenha()+"'"+","+"'"+cliente.getCpf()+"'"+","+"'"+cliente.getNome()+"'"+","+"'"+cliente.getTelefone()+"'"+","+"'"+cliente.getEnder()+"'"+","+"'"+cliente.getEmail()+"'"+","+cliente.getPremium()+");";
//            System.out.println(query);
//            this.stmt.executeUpdate(query);
//            return true;
//        } catch (Exception e) {
//            System.out.println("Erro: "+e.getMessage());
//        }
//        return false;
//    }
//    
//    // Delete Cliente
//    
//    public boolean removecliente(int idcliente){
//        try {
//            String query = "delete from cliente where idcliente="+idcliente+";";
//            this.getStmt().executeUpdate(query);
//            return true;
//        } catch (Exception e) {
//            System.out.println("Error: "+e.getMessage());
//        }
//        return false;
//    }
//    
//    // Select ClienteUnico
//    
//    public ResultSet dadoscliente(int idcliente){
//        try {
//            String query= "select * from cliente where idcliente="+idcliente+";";
//            this.setResult(this.getStmt().executeQuery(query));
//            return this.getResult();
//        } catch (Exception e) {
//            System.out.println("Error :"+e.getMessage());
//        }
//        return null;
//    }
//    
//    // Select All Clientes
//    
//    public ResultSet dadosallcliente(){
//        try {
//            String query= "select * from cliente;";
//            this.setResult(this.getStmt().executeQuery(query));
//            return this.getResult();
//        } catch (Exception e) {
//            System.out.println("Error :"+e.getMessage());
//        }
//        return null;
//    }
//    
//    // Crud Produto
//    
//    // Add Produto
//    
//    public boolean addproduto(Produto produto){
//        try {
//            String query = "insert into produto (nome,quantidade,valor,especialidade) values ("+"'"+produto.getNome()+"'"+","+produto.getQuantidade()+","+produto.getValor()+","+"'"+produto.getEspecialidade()+"'"+";";
//            System.out.println(query);
//            this.getStmt().executeUpdate(query);
//            return true;
//        } catch (Exception e) {
//            System.out.println("Erro: "+e.getMessage());
//        }
//        return false;
//    }
//    
//    // Delete Produto
//    
//    public boolean removeproduto(int idproduto){
//        try {
//            String query = "delete from produto where idproduto="+idproduto+";";
//            this.getStmt().executeUpdate(query);
//            return true;
//        } catch (Exception e) {
//            System.out.println("Error: "+e.getMessage());
//        }
//        return false;
//    }
//    
//    // Select Produto 
//    
//    public ResultSet dadosproduto(int idproduto){
//        try {
//            String query= "select * from produto where idcliente="+idproduto+";";
//            this.setResult(this.getStmt().executeQuery(query));
//            return this.getResult();
//        } catch (Exception e) {
//            System.out.println("Error :"+e.getMessage());
//        }
//        return null;
//    }
//    
//    // Select All Produtos
//    
//    public ResultSet dadosallproduto(){
//        try {
//            String query= "select * from produto;";
//            this.setResult(this.getStmt().executeQuery(query));
//            return this.getResult();
//        } catch (Exception e) {
//            System.out.println("Error :"+e.getMessage());
//        }
//        return null;
//    }
//    
//    // Crud Pagamento
//    
//    // Add Pagamento
//    
//    public boolean addpagamento(Pagamento pagamento){
//        try {
//            String query = "insert into pagamento (tipopagamento) values ("+"'"+pagamento.getTipopagamento()+"';";
//            System.out.println(query);
//            this.getStmt().executeUpdate(query);
//            return true;
//        } catch (Exception e) {
//            System.out.println("Erro: "+e.getMessage());
//        }
//        return false;
//    }
//    
//    // Delete Paamento
//    
//    public boolean removepagamento(int idpagamento){
//        try {
//            String query = "delete from pagamento where idpagamento="+idpagamento+";";
//            this.getStmt().executeUpdate(query);
//            return true;
//        } catch (Exception e) {
//            System.out.println("Error: "+e.getMessage());
//        }
//        return false;
//    }
//    
//    // Select Pagamento
//    
//    public ResultSet dadospagamento(int idpagamento){
//        try {
//            String query= "select * from pagamento where idpagamento="+idpagamento+";";
//            this.setResult(this.getStmt().executeQuery(query));
//            return this.getResult();
//        } catch (Exception e) {
//            System.out.println("Error :"+e.getMessage());
//        }
//        return null;
//    }
//    
//    // Select All Pagamento
//    
//    public ResultSet dadosallpagamentos(){
//        try {
//            String query= "select * from pagamento;";
//            this.setResult(this.getStmt().executeQuery(query));
//            return this.getResult();
//        } catch (Exception e) {
//            System.out.println("Error :"+e.getMessage());
//        }
//        return null;
//    }
//    
//    // Crud Delivery
//    
//    // Add Delivery
//    
//    public boolean adddelivery(Delivery delivery){
//        try {
//            String query = "insert into delivery (usuario,senha,nome,cnh,telefone,email,ativo) values ("+"'"+delivery.getUsuario()+"'"+","+"'"+delivery.getSenha()+"'"+","+"'"+delivery.getNome()+"'"+","+"'"+delivery.getCnh()+"'"+","+"'"+delivery.getTelefone()+"'"+","+"'"+delivery.getEmail()+"'"+","+delivery.getAtivo()+");";
//            System.out.println(query);
//            this.getStmt().executeUpdate(query);
//            return true;
//        } catch (Exception e) {
//            System.out.println("Erro: "+e.getMessage());
//        }
//        return false;
//    }
//    
//    // Delete Delivery
//    
//    public boolean removedelvery(int iddelivery){
//        try {
//            String query = "delete from delivery where iddelivery="+iddelivery+";";
//            this.getStmt().executeUpdate(query);
//            return true;
//        } catch (Exception e) {
//            System.out.println("Error: "+e.getMessage());
//        }
//        return false;
//    }
//    
//    // Select Delivery
//    
//    public ResultSet dadosdelivery(int iddelivery){
//        try {
//            String query= "select * from delivery where idpagamento="+iddelivery+";";
//            this.setResult(this.getStmt().executeQuery(query));
//            return this.getResult();
//        } catch (Exception e) {
//            System.out.println("Error :"+e.getMessage());
//        }
//        return null;
//    }
//    
//    // Select All Delivery
//    
//    public ResultSet dadosalldelivery(){
//        try {
//            String query= "select * from delivery;";
//            this.setResult(this.getStmt().executeQuery(query));
//            return this.getResult();
//        } catch (Exception e) {
//            System.out.println("Error :"+e.getMessage());
//        }
//        return null;
//    }
//    
//    // Crud Pedido
//    
//    // Add Pedido
//    
//    public boolean addpedido(Pedido pedido){
//        try {
//            String query = "insert into pedido (idcliente,iddelivery,idpagamento,datacompra,valortotal,entregafeita) values ("+pedido.getIdcliente()+","+pedido.getIdelivery()+","+pedido.getIdpagamento()+","+"'"+pedido.getDatacompra()+"'"+","+pedido.getValortotal()+","+pedido.getEntregafeita()+");";
//            System.out.println(query);
//            this.getStmt().executeUpdate(query);
//            return true;
//        } catch (Exception e) {
//            System.out.println("Erro: "+e.getMessage());
//        }
//        return false;
//    }
//    
//    // Delete Pedido
//    
//    public boolean removepedido(int idpedido){
//        try {
//            String query = "delete from pedido where idpedido="+idpedido+";";
//            this.getStmt().executeUpdate(query);
//            return true;
//        } catch (Exception e) {
//            System.out.println("Error: "+e.getMessage());
//        }
//        return false;
//    }
//    
//    // Select Pedido
//    
//    public ResultSet dadospedido(int idpedido){
//        try {
//            String query= "select * from pedidowhere idpedido="+idpedido+";";
//            this.setResult(this.getStmt().executeQuery(query));
//            return this.getResult();
//        } catch (Exception e) {
//            System.out.println("Error :"+e.getMessage());
//        }
//        return null;
//    }
//    
//    // Select All Pedidos
//    
//    public ResultSet dadosallpedido(){
//        try {
//            String query= "select * from pedido;";
//            this.setResult(this.getStmt().executeQuery(query));
//            return this.getResult();
//        } catch (Exception e) {
//            System.out.println("Error :"+e.getMessage());
//        }
//        return null;
//    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public ResultSet getResult() {
        return result;
    }

    public void setResult(ResultSet result) {
        this.result = result;
    }
    
    
    
}
