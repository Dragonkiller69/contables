/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.sic1352018.server.business;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public List<Cuenta> findLast() {
        List<Cuenta> lista = Collections.EMPTY_LIST;
        try {
            lista = em.createQuery("SELECT DISTINCT m FROM Cuenta m WHERE m.codigoCuenta NOT IN(SELECT DISTINCT g.cuentaPadre.codigoCuenta FROM Cuenta g)").getResultList();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR EN TRANSSACION");
        }
        return lista;
    }

    @Override
    public List<BalanceComprobacion> createBalance() {
        List<BalanceComprobacion> lista = Collections.EMPTY_LIST;
        try {
            lista = em.createQuery("SELECT DISTINCT NEW ues.occ.sic1352018.server.business.BalanceComprobacion(g.nombre, SUM(t.monto), t.monto) FROM Cuenta g\n"
                    + "LEFT JOIN g.transaccionList1 t\n"
                    + "WHERE g.nombre IN (SELECT DISTINCT t.idAbono.nombre from Transaccion t) OR\n"
                    + " g.nombre IN (SELECT DISTINCT t.idCargo.nombre from Transaccion t)\n"
                    + "GROUP BY g.nombre\n"
                    + "ORDER BY g.codigoCuenta").getResultList();
            lista = agregarAbonos(lista);
        } catch (Exception e) {
            System.out.println("ERROR EN BALANCE");
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return lista;
    }

    public List<BalanceComprobacion> agregarAbonos(List<BalanceComprobacion> lista) {
        List<BalanceComprobacion> abonos;
        Double total;
        try {
            abonos = em.createQuery("SELECT DISTINCT  NEW ues.occ.sic1352018.server.business.BalanceComprobacion(g.nombre, tl.monto, SUM(tl.monto))  FROM Cuenta g\n"
                    + "LEFT JOIN g.transaccionList tl \n"
                    + "WHERE g.nombre IN (SELECT DISTINCT t.idAbono.nombre from Transaccion t) OR\n"
                    + " g.nombre IN (SELECT DISTINCT t.idCargo.nombre from Transaccion t)\n"
                    + "GROUP BY g.nombre\n"
                    + "ORDER BY g.codigoCuenta").getResultList();

            Iterator<BalanceComprobacion> iteradorCargos = lista.iterator();
            Iterator<BalanceComprobacion> iteradorAbonos = abonos.iterator();
            BalanceComprobacion cargo;
            BalanceComprobacion abono;

            while (iteradorAbonos.hasNext() && iteradorCargos.hasNext()) {
                cargo = iteradorCargos.next();
                abono = iteradorAbonos.next();
                total = cargo.getCargos() - abono.getAbonos();
                System.out.println("total " + total);
                if (total > 0) {
                    cargo.setCargos(total);
                    cargo.setAbonos(0.0);
                } else {
                    cargo.setAbonos(total * (-1));
                    cargo.setCargos(0.0);
                }

            }

        } catch (Exception e) {
            System.out.println("ERROR EN abonos");
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return lista;
    }

    /**
     * Genera el estado de Resultados apartir de una consulta JPQL
     *
     * @return Lista con los totales de ingresos y gastos de cada cuenta
     */
    public List<EstadoResultados> getIngresos() {
        List<EstadoResultados> lista = Collections.EMPTY_LIST;
        try {
            lista = em.createQuery("SELECT DISTINCT NEW ues.occ.sic1352018.server.business.EstadoResultados(g.nombre, SUM(tl.monto)) FROM Cuenta g\n"
                    + "LEFT JOIN g.transaccionList tl\n"
                    + "WHERE g.codigoCuenta IN (SELECT DISTINCT c.codigoCuenta FROM Cuenta c WHERE c.codigoCuenta LIKE \"4.%\" )\n"
                    + "GROUP BY g.nombre\n"
                    + "ORDER BY g.codigoCuenta").getResultList();
        } catch (Exception e) {
            System.out.println("ERROR EN ESTADO DE RESULTADOS");
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);

        }
        return lista;
    }

    public List<EstadoResultados> getGastos() {
        List<EstadoResultados> lista = Collections.EMPTY_LIST;
        try {
            lista = em.createQuery("SELECT DISTINCT NEW ues.occ.sic1352018.server.business.EstadoResultados(g.nombre, SUM(t.monto)) FROM Cuenta g\n"
                    + "LEFT JOIN g.transaccionList1 t\n"
                    + "WHERE g.codigoCuenta IN (SELECT DISTINCT c.codigoCuenta FROM Cuenta c WHERE c.codigoCuenta LIKE \"5.%\" )\n"
                    + "GROUP BY g.nombre\n"
                    + "ORDER BY g.codigoCuenta").getResultList();
        } catch (Exception e) {
            System.out.println("ERROR EN ESTADO DE RESULTADOS");
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);

        }
        return lista;
    }

    public Double getUtilidad() {
        Double utilidad = 0.0;
        try {
            utilidad = em.createNamedQuery("Cuenta.findUtilidad", Double.class).getSingleResult();
            System.out.println(utilidad);
        } catch (Exception e) {
            System.out.println("ERROR EN UTILIDAD");
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return utilidad;
    }

    @Override
    public String createEstadoResultados() {
        Gson gson = new Gson();
        Double utilidad = getUtilidad();
        String ingresos = gson.toJson(getIngresos()).replaceAll("\\[", "").replaceAll("\\]", "");
        String gastos = gson.toJson(getGastos()).replaceAll("\\[", "").replaceAll("\\]", "");
        String json = "[{\"ingresos\":[" + ingresos + "]},{\"gastos\":[" + gastos + "]},{\"utilidad\":" + "\"" + utilidad + "\"" + "}]";
        System.out.println(json);
        return json;

    }

    public List<EstadoResultados> getVariacionCapital() {
        List<EstadoResultados> lista = Collections.EMPTY_LIST;
        try {
            lista = em.createQuery("SELECT NEW ues.occ.sic1352018.server.business.EstadoResultados(c.nombre, SUM(t.monto),SUM(tl.monto)) FROM Cuenta c\n"
                    + "LEFT JOIN c.transaccionList tl\n"
                    + "LEFT JOIN c.transaccionList1 t\n"
                    + "WHERE c.codigoCuenta LIKE \"3.%\"\n"
                    + "GROUP BY c.nombre\n"
                    + "").getResultList();
        } catch (Exception e) {
            System.out.println("ERROR EN ESTADO DE RESULTADOS");
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);

        }
        return lista;
    }

    public Double getCapitalFinal() {
        Double utilidad = getUtilidad();
        Double total = 0.0;
        List<EstadoResultados> lista = getVariacionCapital();
        for (EstadoResultados saldo : lista) {
            if (saldo.getCuenta().equals("Retiros")) {
                total -= saldo.getSaldo();
            } else {
                total += saldo.getSaldo();
            }
        }
        return total + utilidad;

    }

    @Override
    public String createEstadoVariacionCapital() {
        Gson gson = new Gson();
        Double total = getCapitalFinal();
        Double utilidad = getUtilidad();
        String capital = gson.toJson(getVariacionCapital()).replaceAll("\\[", "").replaceAll("\\]", "");
        String json = "[" + capital + ",{\"cuenta\":\"utilidad\",\"saldo\":" + utilidad + "},{\"cuenta\":\"total\",\"saldo\":" + total + "}]";
        System.out.println(json);
        return json;
    }

    public List<BalanceComprobacion> prepareBalanceGeneral(String query) {
        List<String> cuentasLista;
        List<BalanceComprobacion> balanceComporbacion = createBalance();
        List<BalanceComprobacion> cuentas = new ArrayList<>();
        try {
            cuentasLista = em.createQuery(query).getResultList();

            for (String nuevaCuenta : cuentasLista) {
                for (BalanceComprobacion cuenta : balanceComporbacion) {
                    if (nuevaCuenta.equals(cuenta.getCuenta())) {
                        cuentas.add(cuenta);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("ERROR EN GENERAR ACTIVO O PASIVO");
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return cuentas;
    }

    @Override
    public String createBalanceGeneral() {
        String queryActivos = "SELECT c.nombre FROM Cuenta c WHERE c.codigoCuenta NOT IN(SELECT c.cuentaPadre.codigoCuenta FROM Cuenta c ) AND c.codigoCuenta LIKE \"1.%\" ORDER BY c.codigoCuenta";
        String queryPasivos = "SELECT c.nombre FROM Cuenta c WHERE c.codigoCuenta NOT IN(SELECT c.cuentaPadre.codigoCuenta FROM Cuenta c ) AND c.codigoCuenta LIKE \"2.%\" ORDER BY c.codigoCuenta";
        List<BalanceComprobacion> activosLista = prepareBalanceGeneral(queryActivos);
        List<BalanceComprobacion> pasivosLista = prepareBalanceGeneral(queryPasivos);
        Double capital = getCapitalFinal();
        Gson gson = new Gson();
        String activos = gson.toJson(activosLista).replaceAll("\\[", "").replaceAll("\\]", "");
        String pasivos = gson.toJson(pasivosLista).replaceAll("\\[", "").replaceAll("\\]", "");
        String json = "[{\"activos\":[" + activos + "]},{\"pasivos\":[" + pasivos + "]},{\"capital\":" + capital + "}]";
        System.out.println(json);
        return json;
    }
    
    @Override
    public String createEstadosFinancieros(){
        String EstadosFinancierosJSON;
        EstadosFinancierosJSON = "[{\"EstadoResultados\":"+createEstadoResultados()+"},{\"EstadoVariacionCapital\":"+createEstadoVariacionCapital()+"},{\"BalanceGeneral\":"+createBalanceGeneral()+"}]";
        return EstadosFinancierosJSON;
    }
}
