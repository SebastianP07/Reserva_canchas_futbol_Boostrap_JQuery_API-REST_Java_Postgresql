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
public class Cancha {
    private String capacidad;
    private String tgrama;
    private String idcancha;
    private String idcalendario;
    private String caracteristicas;
    private String idestablecimiento;
    private String foto;
    private Calendario calendario; 
    private ArrayList<Cancha> arregloCanchas;

    public Cancha() {
    }

    public Cancha(String capacidad, String tgrama, String idcancha, String idcalendario, String caracteristicas, String idestablecimiento, String foto, Calendario calendario, ArrayList<Cancha> arregloCanchas) {
        this.capacidad = capacidad;
        this.tgrama = tgrama;
        this.idcancha = idcancha;
        this.idcalendario = idcalendario;
        this.caracteristicas = caracteristicas;
        this.idestablecimiento = idestablecimiento;
        this.foto = foto;
        this.calendario = calendario;
        this.arregloCanchas = arregloCanchas;
    }

    
    
    /**
     * @return the capacidad
     */
    public String getCapacidad() {
        return capacidad;
    }

    /**
     * @param capacidad the capacidad to set
     */
    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * @return the tgrama
     */
    public String getTgrama() {
        return tgrama;
    }

    /**
     * @param tgrama the tgrama to set
     */
    public void setTgrama(String tgrama) {
        this.tgrama = tgrama;
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
     * @return the idcalendario
     */
    public String getIdcalendario() {
        return idcalendario;
    }

    /**
     * @param idcalendario the idcalendario to set
     */
    public void setIdcalendario(String idcalendario) {
        this.idcalendario = idcalendario;
    }

    /**
     * @return the caracteristicas
     */
    public String getCaracteristicas() {
        return caracteristicas;
    }

    /**
     * @param caracteristicas the caracteristicas to set
     */
    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
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
     * @return the foto
     */
    public String getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * @return the calendario
     */
    public Calendario getCalendario() {
        return calendario;
    }

    /**
     * @param calendario the calendario to set
     */
    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }

    /**
     * @return the arregloCanchas
     */
    public ArrayList<Cancha> getArregloCanchas() {
        return arregloCanchas;
    }

    /**
     * @param arregloCanchas the arregloCanchas to set
     */
    public void setArregloCanchas(ArrayList<Cancha> arregloCanchas) {
        this.arregloCanchas = arregloCanchas;
    }
     
}
