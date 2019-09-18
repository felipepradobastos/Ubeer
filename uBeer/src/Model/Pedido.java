/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author felip
 */
public class Pedido {
    private ArrayList<Produto> produtos;
    private int idpedido;
    private int idcliente;
    private int idelivery;
    private int idpagamento;
    private String datacompra;
    private double valortotal;
    private int entregafeita;
    
    public Pedido(){
        produtos = new ArrayList();
    }
    
    
//    public void valortotalcalc(){
//        double precototal=0;
//        for(int i=0;i<produtos.size();i++){
//            precototal+=produtos.get(i).getValor()*produtos.get(i).getQuantidade();
//        }
//        this.valortotal=precototal;
//    }
    
    public double valortotal(){
        double precototal=0;
        System.out.println("Size:"+produtos.size());
        for(int i=0;i<produtos.size();i++){
            precototal+=produtos.get(i).getValor();
        }
        return precototal+5;
    }
   
    // Getters e Setters

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }
    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void adicionarproduto(Produto produto) {
        this.produtos.add(produto);
    }
    
    
    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getIdelivery() {
        return idelivery;
    }

    public void setIdelivery(int idelivery) {
        this.idelivery = idelivery;
    }

    public int getIdpagamento() {
        return idpagamento;
    }

    public void setIdpagamento(int idpagamento) {
        this.idpagamento = idpagamento;
    }

    public String getDatacompra() {
        return datacompra;
    }

    public boolean setDatacompra(String datacompra) {
        if(datacompra.length()<=15){
            this.datacompra = datacompra;
            return true;
        }
        return false;
    }

    public double getValortotal() {
        return valortotal;
    }
//    public void setValortotal(double valortotal) {
//        this.valortotal = valortotal;
//    }

    public int getEntregafeita() {
        return entregafeita;
    }

    public void setEntregafeita(int entregafeita) {
        this.entregafeita = entregafeita;
    }

    
    
    
    
    
}
