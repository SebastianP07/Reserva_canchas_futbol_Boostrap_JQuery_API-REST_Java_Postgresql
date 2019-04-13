/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.awt.Image;
import java.sql.Date;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;
    
/**
 *
 * @author Alberto
 */
@XmlRootElement
public class Usuario {
    private String pnombre;
    private String snombre;
    private String papellido;
    private String sapellido;
    private String fechan;
    private Date fechaActual;
    private String genero;
    private String usuario;
    private String contrasena;
 //   private Image  imagen;
    private String correo;
    private String perfil;
    private ArrayList<Usuario> arregloUsuario;

    /**
     * @return the pnombre
     */
    public String getPnombre() {
        return pnombre;
    }

    /**
     * @param pnombre the pnombre to set
     */
    public void setPnombre(String pnombre) {
        this.pnombre = pnombre;
    }

    /**
     * @return the snombre
     */
    public String getSnombre() {
        return snombre;
    }

    /**
     * @param snombre the snombre to set
     */
    public void setSnombre(String snombre) {
        this.snombre = snombre;
    }

    /**
     * @return the papellido
     */
    public String getPapellido() {
        return papellido;
    }

    /**
     * @param papellido the papellido to set
     */
    public void setPapellido(String papellido) {
        this.papellido = papellido;
    }

    /**
     * @return the sapellido
     */
    public String getSapellido() {
        return sapellido;
    }

    /**
     * @param sapellido the sapellido to set
     */
    public void setSapellido(String sapellido) {
        this.sapellido = sapellido;
    }

    /**
     * @return the fechan
     */
    public String getFechan() {
        return fechan;
    }

    /**
     * @param fechan the fechan to set
     */
    public void setFechan(String fechan) {
        this.fechan = fechan;
    }

    /**
     * @return the fechaActual
     */
    public Date getFechaActual() {
        return fechaActual;
    }

    /**
     * @param fechaActual the fechaActual to set
     */
    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
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
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the perfil
     */
    public String getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    /**
     * @return the arregloUsuario
     */
    public ArrayList<Usuario> getArregloUsuario() {
        return arregloUsuario;
    }

    /**
     * @param arregloUsuario the arregloUsuario to set
     */
    public void setArregloUsuario(ArrayList<Usuario> arregloUsuario) {
        this.arregloUsuario = arregloUsuario;
    }
    
}
