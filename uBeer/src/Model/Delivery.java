/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.DAO.DeliveryDAO;

/**
 *
 * @author felip
 */
public class Delivery extends Usuario {
    private int iddelivery;
    private String cnh;
    private int ativo;

    public String getCnh() {
        return cnh;
    }
    
    public void tentativa(){
        DeliveryDAO deliveryDAO = new DeliveryDAO();
        int id;
        id = deliveryDAO.designaentregador();
        this.iddelivery=id;
    }

    public void setCnh(String cnh) {
        this.cnh=cnh;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public int getIddelivery() {
        return iddelivery;
    }

    public void setIddelivery(int iddelivery) {
        this.iddelivery = iddelivery;
    }
    
    
    
}
