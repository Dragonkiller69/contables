/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.sic1352018.server.rest.service;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import ues.occ.sic1352018.server.business.GenericFacade;

/**
 *
 * @author kevin
 */
public abstract class GenericRest<T> {

    protected abstract GenericFacade<T> getFacadeLocal();

    protected abstract T getNewEntity();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<T> findAll() {
        GenericFacade facadeLocal = getFacadeLocal();
        try {
            if (facadeLocal != null) {
                return facadeLocal.findAll();
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return Collections.EMPTY_LIST;
    }
    

    @GET
    @Path("{inicio}/{tamanio}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<T> findRange(
            @QueryParam("inicio") @DefaultValue("0") int inicio,
            @PathParam("tamanio") @DefaultValue("10") int tamanio) {
        try {
            if (getFacadeLocal() != null) {
                return getFacadeLocal().findRange(inicio, tamanio);
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return Collections.EMPTY_LIST;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public T findById(
            @PathParam("id") String id) {
        GenericFacade facadeLocal = getFacadeLocal();
        try {
            if (facadeLocal != null) {
                return (T) facadeLocal.find(id);
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return getNewEntity();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void crear(T registro) {
        if (registro != null) {
            GenericFacade facadeLocal = getFacadeLocal();
            try {
                if (facadeLocal != null) {
                    facadeLocal.create(registro);
                    System.out.println("AQUI EN EL POST PERROS");

                }
            } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }

//    @PUT
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public T editar(T reg) {
//        if (reg != null) {
//            try {
//                if (getFacadeLocal() != null) {
//                    T u = getFacadeLocal().editar(reg);
//                    if (u != null) {
//                        return u;
//                    }
//                }
//            } catch (Exception e) {
//                System.out.println("ex: " + e);
//            }
//        }
//        return getNewEntity();
//    }
//
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public T eliminar(@PathParam("id") int id) {
        if (id > 0) {
            GenericFacade facadeLocal = getFacadeLocal();
            try {
                if (facadeLocal != null) {
                    facadeLocal.remove(facadeLocal.find(id));
                }
            } catch (Exception e) {
                System.out.println("ex: " + e);
            }
        }
        return getNewEntity();
    }
}
