/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.sic1352018.server.business;

import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ues.occ.sic1352018.libreriacontables.Cuenta;

/**
 *
 * @author kevin
 */
@Stateless
public class CuentaFacade extends AbstractFacade<Cuenta> implements CuentaFacadeLocal {

    @PersistenceContext(unitName = "pucontables")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuentaFacade() {
        super(Cuenta.class);
    }
    
    @Override
    public List<Cuenta> findLast(){
         List<Cuenta> lista = Collections.EMPTY_LIST;
        try {
              lista = em.createQuery("SELECT DISTINCT m FROM Cuenta m WHERE m.codigoCuenta NOT IN(SELECT DISTINCT g.cuentaPadre.codigoCuenta FROM Cuenta g)").getResultList();
              
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN TRANSSACION");
        }
        return lista;
    }
   
    
}
