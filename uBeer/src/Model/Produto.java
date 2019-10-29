/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.DAO.ProdutoDAO;

/**
 *
 * @author Felip
 */
public class Produto {
    private int idproduto;
    private String nome;
    private int quantidade;
    private double valor;
    private String especialidade;
    
    // Getters e Setters 
   
    public void tentativa(String nome){
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produtovem = new Produto();
        produtovem = produtoDAO.buscaproduto(nome);
        this.nome=produtovem.getNome();
        this.especialidade=produtovem.getEspecialidade();
        this.valor=produtovem.getValor();
        this.idproduto=produtovem.getIdproduto();
    }
    public int getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(int idproduto) {
        this.idproduto = idproduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome){
        this.nome= nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade=especialidade;      
    }
    
    
}
