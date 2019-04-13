package DAO;



public class Conexion {

    private String url; 
    private String driver; 
    private String usuario; 
    private String clave;

    public Conexion() {
//        this.url = "sebasucentral.postgres.database.azure.com";
//        this.driver = "jdbc:postgresql://sebasucentral.postgres.database.azure.com:5432/postgres";
//        this.usuario = "adminsebas@sebasucentral";
//        this.clave = "Sebas2018**";
        this.url = "jdbc:postgresql://localhost:5432/tribuna90";
        this.driver = "org.postgresql.Driver";
        this.usuario = "postgres";
        this.clave = "admin";
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the driver
     */
    public String getDriver() {
        return driver;
    }

    /**
     * @param driver the driver to set
     */
    public void setDriver(String driver) {
        this.driver = driver;
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
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

}
