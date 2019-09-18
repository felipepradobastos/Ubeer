/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author felip
 */
//Verificar Possibilidad disso virar um ENUM;
public class Pagamento {
    private int idpagamento;
    private String tipopagamento;

    public int getIdpagamento() {
        return idpagamento;
    }

    public void setIdpagamento(int idpagamento) {
        this.idpagamento = idpagamento;
    }

    public String getTipopagamento() {
        return tipopagamento;
    }

    public boolean setTipopagamento(String tipopagamento) {
        if(tipopagamento.length()<=100 && tipopagamento.equalsIgnoreCase("cartao de credito") && tipopagamento.equalsIgnoreCase("cartao de debito") && tipopagamento.equalsIgnoreCase("dinheiro")){
            this.tipopagamento = tipopagamento;
            return true;
        }
        return false;
    }
    
    
}
