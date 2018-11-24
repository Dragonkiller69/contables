/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.sic1352018.server.rest.service;

import javax.ejb.EJB;
import javax.ws.rs.Path;
import ues.occ.sic1352018.libreriacontables.Transaccion;
import ues.occ.sic1352018.server.business.GenericFacade;
import ues.occ.sic1352018.server.business.TransaccionFacadeLocal;

/**
 *
 * @author kevin
 */
@Path("transaccion")
public class TransaccionRest extends GenericRest<Transaccion>{
    
    @EJB
    TransaccionFacadeLocal transaccionFacade;

    @Override
    protected GenericFacade getFacadeLocal() {
        return transaccionFacade;
    }

    @Override
    protected Transaccion getNewEntity() {
        return new Transaccion();
    }
}
