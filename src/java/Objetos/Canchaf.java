/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author Alberto
 */
public class Canchaf {
  private String usuario;  
  private String idestablecimiento;
  private String diresta;
  private String foto;
  private int valor;
  private String caracteristica;

    public Canchaf() {
    }

    public Canchaf(String usuario, String idestablecimiento, String diresta, String foto, int valor, String caracteristica) {
        this.usuario = usuario;
        this.idestablecimiento = idestablecimiento;
        this.diresta = diresta;
        this.foto = foto;
        this.valor = valor;
        this.caracteristica = caracteristica;
    }

    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getIdestablecimiento() {
        return idestablecimiento;
    }

    public void setIdestablecimiento(String idestablecimiento) {
        this.idestablecimiento = idestablecimiento;
    }

    public String getDiresta() {
        return diresta;
    }

    public void setDiresta(String diresta) {
        this.diresta = diresta;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    
}
