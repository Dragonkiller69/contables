/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.sic1352018.server.rest.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ues.occ.sic1352018.libreriacontables.Cuenta;
import ues.occ.sic1352018.server.business.BalanceComprobacion;
import ues.occ.sic1352018.server.business.CuentaFacadeLocal;
import ues.occ.sic1352018.server.business.GenericFacade;

/**
 *
 * @author kevin
 */
@Path("cuenta")
public class CuentaRest extends GenericRest<Cuenta> {

    @EJB
    CuentaFacadeLocal cuentaFacade;

    @Override
    protected GenericFacade getFacadeLocal() {
        return cuentaFacade;
    }

    @Override
    protected Cuenta getNewEntity() {
        return new Cuenta();
    }

    @GET
    @Path("ultimas")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cuenta> findLast() {

        try {
            if (cuentaFacade != null) {
                return cuentaFacade.findLast();
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return Collections.EMPTY_LIST;

    }

    @GET
    @Path("balance")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBalance() {
        try {
            if (cuentaFacade != null) {
                return new Gson().toJson((cuentaFacade.createBalance()));

            }
        } catch (Exception e) {
            System.out.println("ERROR EN GET BALANCE");
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return "";
    }

    @GET
    @Path("estadoresultados")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEstadoResultados() {
        try {
            if (cuentaFacade != null) {
                return cuentaFacade.createEstadoResultados();
            }
        } catch (Exception e) {
            System.out.println("ERROR EN GET ESTADO");
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return "";
    }

    @GET
    @Path("estadovariacion")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEstadoVariacion() {
        try {
            if (cuentaFacade != null) {
                return cuentaFacade.createEstadoVariacionCapital();
            }
        } catch (Exception e) {
            System.out.println("ERROR EN GET ESTADOVAR");
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return "";
    }

    @GET
    @Path("balancegeneral")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBal() {
        try {
            if (cuentaFacade != null) {
                return cuentaFacade.createBalanceGeneral();
            }
        } catch (Exception e) {
            System.out.println("ERROR EN GET BALANCE GENERAL");
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return "";
    }
    
    @GET
    @Path("estados")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEstadosFinancieros() {
        try {
            if (cuentaFacade != null) {
                return cuentaFacade.createEstadosFinancieros();
            }
        } catch (Exception e) {
            System.out.println("ERROR EN GET ESTADOS FINANCIEROS");
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return "";
    }

}
