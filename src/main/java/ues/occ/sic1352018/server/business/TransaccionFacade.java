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
import ues.occ.sic1352018.libreriacontables.Transaccion;

/**
 *
 * @author kevin
 */
@Stateless
public class TransaccionFacade extends AbstractFacade<Transaccion> implements TransaccionFacadeLocal {

    @PersistenceContext(unitName = "pucontables")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransaccionFacade() {
        super(Transaccion.class);
    }
    
    @Override
      public List<TransaccionShort> findShort(){
         List<TransaccionShort> lista = Collections.EMPTY_LIST;
        try {
            System.out.println("en findShort local");
              lista = em.createQuery("SELECT NEW ues.occ.sic1352018.server.business.TransaccionShort(t.idCargo.nombre,t.idAbono.nombre, t.monto, t.descripcion, t.fecha) FROM Transaccion t").getResultList();
              for (TransaccionShort transaccionShort : lista) {
                 transaccionShort.setFecha(transaccionShort.format(transaccionShort.getFecha()));
                  System.out.println(transaccionShort.getFecha());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN TRANSSACION");
        }
        return lista;
        
    }
    
}
