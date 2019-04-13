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
public class Historico {
    private String usuario;
    private String idreserva;
    private int anio;
    private int mes;
    private int dia;
    private int hora;
    private String idestablecimiento;
    private String idcancha;
    private String observaciones;

    public Historico() {
    }

    public Historico(String usuario, String idreserva, int anio, int mes, int dia, int hora, String idestablecimiento, String idcancha, String observaciones) {
        this.usuario = usuario;
        this.idreserva = idreserva;
        this.anio = anio;
        this.mes = mes;
        this.dia = dia;
        this.hora = hora;
        this.idestablecimiento = idestablecimiento;
        this.idcancha = idcancha;
        this.observaciones = observaciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(String idreserva) {
        this.idreserva = idreserva;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public String getIdestablecimiento() {
        return idestablecimiento;
    }

    public void setIdestablecimiento(String idestablecimiento) {
        this.idestablecimiento = idestablecimiento;
    }

    public String getIdcancha() {
        return idcancha;
    }

    public void setIdcancha(String idcancha) {
        this.idcancha = idcancha;
    }
    
    
}
