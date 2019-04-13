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
public class Establecimiento {
    private String idestablecimiento;
    private String descripcion;
    private int cantcanchas;
    private String facebook;
    private String servicio1;
    private String idcancha;    
    private String twitter;
    private String instagram;
    private String youtube;
    private String servicio2;
    private String servicio3;
    private String foto;

    public Establecimiento() {
    }

    public Establecimiento(String idestablecimiento, String descripcion, int cantcanchas, String facebook, String servicio1, String idcancha, String twitter, String instagram, String youtube, String servicio2, String servicio3, String foto) {
        this.idestablecimiento = idestablecimiento;
        this.descripcion = descripcion;
        this.cantcanchas = cantcanchas;
        this.facebook = facebook;
        this.servicio1 = servicio1;
        this.idcancha = idcancha;
        this.twitter = twitter;
        this.instagram = instagram;
        this.youtube = youtube;
        this.servicio2 = servicio2;
        this.servicio3 = servicio3;
        this.foto = foto;
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
     * @return the cantcanchas
     */
    public int getCantcanchas() {
        return cantcanchas;
    }

    /**
     * @param cantcanchas the cantcanchas to set
     */
    public void setCantcanchas(int cantcanchas) {
        this.cantcanchas = cantcanchas;
    }

    /**
     * @return the facebook
     */
    public String getFacebook() {
        return facebook;
    }

    /**
     * @param facebook the facebook to set
     */
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    /**
     * @return the servicio1
     */
    public String getServicio1() {
        return servicio1;
    }

    /**
     * @param servicio1 the servicio1 to set
     */
    public void setServicio1(String servicio1) {
        this.servicio1 = servicio1;
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
     * @return the twitter
     */
    public String getTwitter() {
        return twitter;
    }

    /**
     * @param twitter the twitter to set
     */
    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    /**
     * @return the instagram
     */
    public String getInstagram() {
        return instagram;
    }

    /**
     * @param instagram the instagram to set
     */
    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    /**
     * @return the youtube
     */
    public String getYoutube() {
        return youtube;
    }

    /**
     * @param youtube the youtube to set
     */
    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    /**
     * @return the servicio2
     */
    public String getServicio2() {
        return servicio2;
    }

    /**
     * @param servicio2 the servicio2 to set
     */
    public void setServicio2(String servicio2) {
        this.servicio2 = servicio2;
    }

    /**
     * @return the servicio3
     */
    public String getServicio3() {
        return servicio3;
    }

    /**
     * @param servicio3 the servicio3 to set
     */
    public void setServicio3(String servicio3) {
        this.servicio3 = servicio3;
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
    
    
    
    
}
