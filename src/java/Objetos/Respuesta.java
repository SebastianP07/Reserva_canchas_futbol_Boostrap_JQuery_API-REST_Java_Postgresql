/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Objetos.Reserva;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AlbertoRN
 */
@XmlRootElement
public class Respuesta {
    private String usuario;
    private int codigo;
    private String descripcion;
    private Reserva reserva;
    private ArrayList<Reserva> reservasData;
    private Usuario usuarioData;
    private ArrayList<Usuario> usuariosData;

    public Respuesta() {
    }

    public Respuesta(String usuario, int codigo, String descripcion, Reserva reserva, ArrayList<Reserva> reservasData, Usuario usuarioData, ArrayList<Usuario> usuariosData) {
        this.usuario = usuario;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.reserva = reserva;
        this.reservasData = reservasData;
        this.usuarioData = usuarioData;
        this.usuariosData = usuariosData;
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
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the reserva
     */
    public Reserva getReserva() {
        return reserva;
    }

    /**
     * @param reserva the reserva to set
     */
    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    /**
     * @return the reservasData
     */
    public ArrayList<Reserva> getReservasData() {
        return reservasData;
    }

    /**
     * @param reservasData the reservasData to set
     */
    public void setReservasData(ArrayList<Reserva> reservasData) {
        this.reservasData = reservasData;
    }

    /**
     * @return the usuarioData
     */
    public Usuario getUsuarioData() {
        return usuarioData;
    }

    /**
     * @param usuarioData the usuarioData to set
     */
    public void setUsuarioData(Usuario usuarioData) {
        this.usuarioData = usuarioData;
    }

    /**
     * @return the usuariosData
     */
    public ArrayList<Usuario> getUsuariosData() {
        return usuariosData;
    }

    /**
     * @param usuariosData the usuariosData to set
     */
    public void setUsuariosData(ArrayList<Usuario> usuariosData) {
        this.usuariosData = usuariosData;
    }
    
}
