/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DAO.ProdutoDAO;
import Model.Produto;
import View.Login;

/**
 *
 * @author aluno
*/
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Produto produto = new Produto();
//        ProdutoDAO produtoDAO = new ProdutoDAO();
//        produto = produtoDAO.buscaproduto("maminha");
//        System.out.println(produto.getNome());
        Login telaInicial = new Login();
        telaInicial.setVisible(true);
        LoginController lc = new LoginController(telaInicial);
    }
    
}
