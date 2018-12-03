/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.sic1352018.server.business;

import java.util.List;
import javax.ejb.Local;
import ues.occ.sic1352018.libreriacontables.Transaccion;

/**
 *
 * @author kevin
 */
@Local
public interface TransaccionFacadeLocal extends GenericFacade<Transaccion>{
    
    public List<TransaccionShort> findShort();
    
    
    
}
