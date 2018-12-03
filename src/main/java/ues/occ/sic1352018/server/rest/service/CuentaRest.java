/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.sic1352018.server.rest.service;

import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ues.occ.sic1352018.libreriacontables.Cuenta;
import ues.occ.sic1352018.server.business.CuentaFacadeLocal;
import ues.occ.sic1352018.server.business.GenericFacade;

/**
 *
 * @author kevin
 */
@Path("cuenta")
public class CuentaRest extends GenericRest<Cuenta>{
    
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
    public List<Cuenta> findLast(){
        
        try {
            if (cuentaFacade != null) {
                return cuentaFacade.findLast();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
         return Collections.EMPTY_LIST;
    
    }
    
}
