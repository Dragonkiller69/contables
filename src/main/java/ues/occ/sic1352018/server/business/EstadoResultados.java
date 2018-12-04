/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.sic1352018.server.business;

/**
 *
 * @author kevin
 */
public class EstadoResultados {
    
    private String cuenta;
    private Double saldo;

    public EstadoResultados(String cuenta, Double saldo) {
        this.cuenta = cuenta;
        this.saldo = saldo;
    }
    
    public EstadoResultados(String cuenta, Double saldo, Double fake) {
        this.cuenta = cuenta;
        if (saldo != null ) {
            this.saldo = saldo;
        }else{
            this.saldo = fake;
        }
        
    }
    
    public EstadoResultados(String cuenta, Double saldo, Double fake, String masfake) {
        this.cuenta = cuenta;
        if (saldo != 0 ) {
            this.saldo = saldo;
        }else{
            this.saldo = fake;
        }
        
    }

    @Override
    public String toString() {
        return "EstadoResultados{" + "cuenta=" + cuenta + ", saldo=" + saldo + '}';
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }


    
    
    
}
