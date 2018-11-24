/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.sic1352018.server.rest.service;

import javax.ejb.EJB;
import javax.ws.rs.Path;
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
    
}
