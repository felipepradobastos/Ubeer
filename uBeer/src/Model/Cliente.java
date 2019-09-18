    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.DAO.ClienteDAO;
import java.util.Scanner;

/**
 *
 * @author felip
 */

//Implementar ao banco sistema de descontos mensais para os clientes gold
public class Cliente extends Usuario {
    private int idcliente;
    private String cpf;
    private String ender;
    private int premium;
    private double descontobonus;
    
    public void tentativa(String login, String senha){
        ClienteDAO clienteDAO = new ClienteDAO();
        int id =0;
        id= clienteDAO.retornaid(login, senha);
        this.idcliente=id;
    }
    
    // Getters e Setters

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getCpf() {
        return cpf;
    }

    public boolean setCpf(String cpf) {
        
        String[] parts = cpf.split("");
        int num1 = Integer.parseInt(parts[0]);
        int num2 = Integer.parseInt(parts[1]);
        int num3 = Integer.parseInt(parts[2]);
        int num4 = Integer.parseInt(parts[3]);
        int num5 = Integer.parseInt(parts[4]);
        int num6 = Integer.parseInt(parts[5]);
        int num7 = Integer.parseInt(parts[6]);
        int num8 = Integer.parseInt(parts[7]);
        int num9 = Integer.parseInt(parts[8]);
        int num10 = Integer.parseInt(parts[9]);
        int num11 = Integer.parseInt(parts[10]);
        
        int result=num1+num2+num3+num4+num5+num6+num7+num8+num9+num10+num11;
        if(result==44){
            this.cpf = cpf;
            return true;
        }
        return false;
    }

    public String getEnder() {
        return ender;
    }

    public void setEnder(String ender) {
        if(ender.length()<=100){
            this.ender=ender;
        }
    }

    public int getPremium() {
        return premium;
    }

    public void setPremium(int premium) {
        this.premium = premium;
    }

    public double getDescontobonus() {
        return descontobonus;
    }

    public void setDescontobonus(double descontobonus) {
        this.descontobonus = descontobonus;
    }
    
 
}
