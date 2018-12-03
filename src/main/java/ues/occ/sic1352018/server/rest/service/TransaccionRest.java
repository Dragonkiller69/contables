/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.sic1352018.server.rest.service;

import com.google.gson.Gson;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ues.occ.sic1352018.libreriacontables.Transaccion;
import ues.occ.sic1352018.server.business.GenericFacade;
import ues.occ.sic1352018.server.business.TransaccionFacadeLocal;

/**
 *
 * @author kevin
 */
@Path("transaccion")
@Produces(MediaType.APPLICATION_JSON)
public class TransaccionRest extends GenericRest<Transaccion> {

    @EJB
    TransaccionFacadeLocal transaccionFacade;

    @Override
    protected GenericFacade<Transaccion> getFacadeLocal() {
        return transaccionFacade;
    }

    @Override
    protected Transaccion getNewEntity() {
        return new Transaccion();
    }
    
    @GET
    @Path("short")
    @Produces(MediaType.APPLICATION_JSON)
    public String findTransaccion() {

        try {
            if (transaccionFacade != null) {
                return new Gson().toJson(transaccionFacade.findShort());
            }
        } catch (Exception e) {
            System.out.println("ex: " + e);
        }

        return "";
    }

    

}
