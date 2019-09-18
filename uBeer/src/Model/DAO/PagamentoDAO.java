/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.Pagamento;
import java.sql.ResultSet;

/**
 *
 * @author felip
 */
public class PagamentoDAO {
    BancoConnect bd;
    public PagamentoDAO(){
        bd = new BancoConnect();
        bd.connect();
    }
        public boolean addpagamento(Pagamento pagamento){
        try {
            String query = "insert into pagamento (tipopagamento) values ("+"'"+pagamento.getTipopagamento()+"';";
            System.out.println(query);
            bd.getStmt().executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println("Erro: "+e.getMessage());
        }
        return false;
    }
    
    // Delete Paamento
    
    public boolean removepagamento(int idpagamento){
        try {
            String query = "delete from pagamento where idpagamento="+idpagamento+";";
            bd.getStmt().executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
        return false;
    }
    
    public boolean atualizapagamento(Pagamento pagamento){
        String query ="update pagamento set tipopagamento='"+pagamento.getTipopagamento()+"';";
        System.out.println(query);
        try {
            bd.getStmt().executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println("Erro:"+e.getMessage());
        }
        return false;
    }
    
    // Select Pagamento
    
    public ResultSet dadospagamento(int idpagamento){
        try {
            String query= "select * from pagamento where idpagamento="+idpagamento+";";
            bd.setResult(bd.getStmt().executeQuery(query));
            return bd.getResult();
        } catch (Exception e) {
            System.out.println("Error :"+e.getMessage());
        }
        return null;
    }
    
    // Select All Pagamento
    
    public ResultSet dadosallpagamentos(){
        try {
            String query= "select * from pagamento;";
            bd.setResult(bd.getStmt().executeQuery(query));
            return bd.getResult();
        } catch (Exception e) {
            System.out.println("Error :"+e.getMessage());
        }
        return null;
    }
}
