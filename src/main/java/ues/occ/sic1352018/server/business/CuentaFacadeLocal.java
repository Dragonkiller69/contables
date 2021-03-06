/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.sic1352018.server.business;

import java.util.List;
import javax.ejb.Local;
import ues.occ.sic1352018.libreriacontables.Cuenta;

/**
 *
 * @author kevin
 */
@Local
public interface CuentaFacadeLocal extends GenericFacade<Cuenta> {

    public List<Cuenta> findLast();

    public List<BalanceComprobacion> createBalance();

    public String createEstadoResultados();

    public String createEstadoVariacionCapital();

    public String createBalanceGeneral();

    public String createEstadosFinancieros();


    

}
