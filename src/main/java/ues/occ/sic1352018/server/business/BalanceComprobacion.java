/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.sic1352018.server.business;

public class BalanceComprobacion {

    private String cuenta;
    private Double cargos = 0.0;
    private Double abonos = 0.0;

    public BalanceComprobacion(String cuenta, Double cargos, Double abonos) {
        this.cuenta = cuenta;
        this.abonos = abonos;
        this.cargos = cargos;
        if (cargos == null) {
        this.cargos = 0.0;
        }if (abonos == null) {
        this.abonos = 0.0;
        }

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

    public Double getCargos() {
        return cargos;
    }

    public void setCargos(Double cargos) {
        this.cargos = cargos;
    }

    public Double getAbonos() {
        return abonos;
    }

    public void setAbonos(Double abonos) {
        this.abonos = abonos;
    }

}
