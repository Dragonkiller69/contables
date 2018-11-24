/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.sic1352018.server.business;

import java.util.List;

/**
 *
 * @author bryan
 */
public interface GenericFacade<T> {
    
    void create(T t);

    void edit(T t);

    void remove(T t);
    

    T find(Object id);

    List<T> findAll();

    List<T> findRange(int inicio, int tamanio);

    int count();
    
}
