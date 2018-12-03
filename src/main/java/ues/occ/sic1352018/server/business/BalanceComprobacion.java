/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.sic1352018.server.business;


public class BalanceComprobacion {
    
    private String cuenta;
    private float  cargos;
    private float abonos;

    public BalanceComprobacion(String cuenta, float cargos, float abonos) {
        this.cuenta = cuenta;
        this.cargos = cargos;
        this.abonos = abonos;
    }

    @Override
    public String toString() {
        return "BalanceComprobacion{" + "cuenta=" + cuenta + ", cargos=" + cargos + ", abonos=" + abonos + '}';
    }

    
    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public float getCargos() {
        return cargos;
    }

    public void setCargos(float cargos) {
        this.cargos = cargos;
    }

    public float getAbonos() {
        return abonos;
    }

    public void setAbonos(float abonos) {
        this.abonos = abonos;
    }
    
    
    
}
