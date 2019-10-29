/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Model.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author felip
 */
public class ProdutoDAO {

    BancoConnect bd;

    public ProdutoDAO() {
        bd = new BancoConnect();
        bd.connect();
    }
    
    public double buscaPrecoPorNome(String nome){
        double preco = 0;
        try {
            String query = "SELECT valor FROM produto WHERE nome='"+nome+"'";
            ResultSet rs=bd.getStmt().executeQuery(query);
            while(rs.next()){
                preco=rs.getDouble("valor");
            }
        } catch (SQLException e) {
            
        }
        return preco;
    }

    public ArrayList<Produto> buscaProdutosPorID(ArrayList<Integer> numeros) {
        ArrayList<Produto> produtos = new ArrayList<>();
        Produto produto;
        try {
            for (int i = 0; i < numeros.size(); i++) {
                String query = "SELECT * FROM produto WHERE idproduto=" + numeros.get(i);
                ResultSet rs = bd.getStmt().executeQuery(query);
                while (rs.next()) {
                    produto = new Produto();
                    produto.setIdproduto(rs.getInt("idproduto"));
                    produto.setEspecialidade(rs.getString("especialidade"));
                    produto.setNome(rs.getString("nome"));
                    produto.setQuantidade(rs.getInt("quantidade"));
                    produto.setValor(rs.getDouble("valor"));
                    produtos.add(produto);
                }
            }
        } catch (Exception e) {
        }
        return produtos;
    }

    public Produto buscaproduto(String produtonome) {
        Produto produto = new Produto();
        try {
            String query = "select * from produto where nome='" + produtonome + "'";
            bd.setResult(bd.getStmt().executeQuery(query));
            if (bd.getResult().next()) {
                produto.setNome(bd.getResult().getString("nome"));
                produto.setValor(bd.getResult().getDouble("valor"));
                produto.setEspecialidade(bd.getResult().getString("especialidade"));
                produto.setQuantidade(bd.getResult().getInt("quantidade"));
                produto.setIdproduto(bd.getResult().getInt("idproduto"));
            }
            return produto;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return produto;
    }

    public boolean addproduto(Produto produto) {
        try {
            String query = "insert into produto (nome,quantidade,valor,especialidade) values (" + "'" + produto.getNome() + "'" + "," + produto.getQuantidade() + "," + produto.getValor() + "," + "'" + produto.getEspecialidade() + "'" + ";";
            System.out.println(query);
            bd.getStmt().executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return false;
    }

    // Delete Produto
    public boolean removeproduto(int idproduto) {
        try {
            String query = "delete from produto where idproduto=" + idproduto + ";";
            bd.getStmt().executeUpdate(query);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    // Select Produto 
    public ResultSet dadosproduto(int idproduto) {
        try {
            String query = "select * from produto where idcliente=" + idproduto + ";";
            bd.setResult(bd.getStmt().executeQuery(query));
            return bd.getResult();
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return null;
    }

    // Select All Produtos
    public ResultSet dadosallproduto() {
        try {
            String query = "select * from produto;";
            bd.setResult(bd.getStmt().executeQuery(query));
            return bd.getResult();
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }
        return null;
    }
}
