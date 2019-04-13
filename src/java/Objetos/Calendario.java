/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alberto
 */
@XmlRootElement
public class Calendario {
    private String idestablecimiento;
    private String idcancha;
    private int anio;
    private int mes;
    private int dia;
    private String hora;
    private String disponibilidad;
    private String fecha;
    private String precio;

    public Calendario() {
    }

    public Calendario(String idestablecimiento, String idcancha, int anio, int mes, int dia, String hora, String disponibilidad, String fecha, String precio) {
        this.idestablecimiento = idestablecimiento;
        this.idcancha = idcancha;
        this.anio = anio;
        this.mes = mes;
        this.dia = dia;
        this.hora = hora;
        this.disponibilidad = disponibilidad;
        this.fecha = fecha;
        this.precio = precio;
    }

    
    
    /**
     * @return the idestablecimiento
     */
    public String getIdestablecimiento() {
        return idestablecimiento;
    }

    /**
     * @param idestablecimiento the idestablecimiento to set
     */
    public void setIdestablecimiento(String idestablecimiento) {
        this.idestablecimiento = idestablecimiento;
    }

    /**
     * @return the idcancha
     */
    public String getIdcancha() {
        return idcancha;
    }

    /**
     * @param idcancha the idcancha to set
     */
    public void setIdcancha(String idcancha) {
        this.idcancha = idcancha;
    }

    /**
     * @return the anio
     */
    public int getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

    /**
     * @return the mes
     */
    public int getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     * @return the dia
     */
    public int getDia() {
        return dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /**
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * @return the disponibilidad
     */
    public String getDisponibilidad() {
        return disponibilidad;
    }

    /**
     * @param disponibilidad the disponibilidad to set
     */
    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the precio
     */
    public String getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(String precio) {
        this.precio = precio;
    }
   
}
