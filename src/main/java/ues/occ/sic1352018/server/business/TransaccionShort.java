/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.occ.sic1352018.server.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author kevin
 */
public class TransaccionShort {
    
    private String cargo;
    private String abono;
    private float monto;
    private String descripcion;
    private Date fecha;

    public TransaccionShort(String cargo, String abono, float monto, String descripcion, Date fecha)  {
        this.cargo = cargo;
        this.abono = abono;
        this.monto = monto;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }
    
    
    public Date format(Date fecha) {
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        try {
            
              date = formater.parse(formater.format(fecha));
              System.out.println("SI PASA ALV");
        } catch (ParseException e) {
            System.out.println("error en formta"+e.getMessage());
        }
        System.out.println(formater.format(date));
         return date;
        
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getAbono() {
        return abono;
    }

    public void setAbono(String abono) {
        this.abono = abono;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "TransaccionShort{" + "cargo=" + cargo + ", abono=" + abono + ", monto=" + monto + ", descripcion=" + descripcion + ", fecha=" + fecha + '}';
    }

    
    
}
