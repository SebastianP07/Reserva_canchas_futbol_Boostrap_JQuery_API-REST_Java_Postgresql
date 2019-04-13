/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.util.ArrayList;

/**
 *
 * @author Alberto
 */
public class Reserva {
    private String idreserva;
    private String fecha;
    private String fechaActual;
    private String anio;
    private String mes;
    private String dia;
    private String hora;
    private String idestablecimiento;
    private String idcancha;
    private String observaciones;
    private String usuario;
    private ArrayList<Reserva> arregloReserva;

    public Reserva() {
    }

    public Reserva(String idreserva, String fecha, String fechaActual, String anio, String mes, String dia, String hora, String idestablecimiento, String idcancha, String observaciones, String usuario, ArrayList<Reserva> arregloReserva) {
        this.idreserva = idreserva;
        this.fecha = fecha;
        this.fechaActual = fechaActual;
        this.anio = anio;
        this.mes = mes;
        this.dia = dia;
        this.hora = hora;
        this.idestablecimiento = idestablecimiento;
        this.idcancha = idcancha;
        this.observaciones = observaciones;
        this.usuario = usuario;
        this.arregloReserva = arregloReserva;
    }

        
    
    /**
     * @return the idreserva
     */
    public String getIdreserva() {
        return idreserva;
    }

    /**
     * @param idreserva the idreserva to set
     */
    public void setIdreserva(String idreserva) {
        this.idreserva = idreserva;
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
     * @return the fechaActual
     */
    public String getFechaActual() {
        return fechaActual;
    }

    /**
     * @param fechaActual the fechaActual to set
     */
    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }

    /**
     * @return the anio
     */
    public String getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(String anio) {
        this.anio = anio;
    }

    /**
     * @return the mes
     */
    public String getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(String mes) {
        this.mes = mes;
    }

    /**
     * @return the dia
     */
    public String getDia() {
        return dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setDia(String dia) {
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
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the arregloReserva
     */
    public ArrayList<Reserva> getArregloReserva() {
        return arregloReserva;
    }

    /**
     * @param arregloReserva the arregloReserva to set
     */
    public void setArregloReserva(ArrayList<Reserva> arregloReserva) {
        this.arregloReserva = arregloReserva;
    }
         
}
