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
        if (cargos == null) {
            this.cuenta = cuenta;
        this.cargos = 0.0;
        this.abonos = abonos;
        } else if (abonos == null) {
            this.cuenta = cuenta;
        this.cargos = cargos;
        this.abonos = 0.0;
        } else {
            this.cuenta = cuenta;
            this.cargos = cargos;
            this.abonos = abonos;
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
