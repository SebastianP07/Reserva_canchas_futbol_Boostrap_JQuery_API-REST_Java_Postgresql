/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Objetos.Establecimiento;
import Objetos.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImpEsta implements InterfaceOperaciones{
    
    Conexion conexion = new Conexion();
    Connection conn;
    PreparedStatement p;
    ResultSet rs;
    Statement st = null;

    @Override
    public boolean insertar(Object obj) {
         Establecimiento est = (Establecimiento) obj;

        String sql = "INSERT INTO establecimiento (idestablecimiento,foto,descripcion,cantcanchas,facebook,servicio1,idcancha,twiter,instagram,youtube,servicio2,servicio3) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        String respuesta = "";
        try {
            Class.forName(conexion.getDriver());
            conn = DriverManager.getConnection(conexion.getUrl(), conexion.getUsuario(), conexion.getClave());
            p = conn.prepareStatement(sql);
            p.setString(1, est.getIdestablecimiento());
            p.setString(2, est.getFoto());
            p.setString(3, est.getDescripcion());
            p.setInt(4, est.getCantcanchas());
            p.setString(5, est.getFacebook());
            p.setString(6, est.getServicio1());
            p.setString(7, est.getIdcancha());
            p.setString(8, est.getTwitter());
            p.setString(9, est.getInstagram());
            p.setString(10, est.getYoutube());
            p.setString(11, est.getServicio2());
            p.setString(12, est.getServicio3());
            int filas = p.executeUpdate();
            respuesta = "se registraron" + filas + "nuevo elemento";
            this.conn.close();
        } catch (ClassNotFoundException | SQLException e) {
                respuesta = "NO fue posible el ingreso de la informacion a la base de datos";
        }
        return false;  
    }
    
    @Override
    public List<?> consultar(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String modificar(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String validarusu(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Establecimiento> postdev(Object obj) {
        Establecimiento est = (Establecimiento) obj;
        ArrayList<Establecimiento> datos = new ArrayList<>();
        try {
            Class.forName(conexion.getDriver());
            conn = DriverManager.getConnection(conexion.getUrl(), conexion.getUsuario(), conexion.getClave());
            String sql = "Select * from establecimiento where idestablecimiento=?";
            p = conn.prepareStatement(sql);
            p.setString(1, est.getIdestablecimiento());
            rs = p.executeQuery();
            while (rs.next()) {
                est.setIdestablecimiento(rs.getString("idestablecimiento"));
                est.setDescripcion(rs.getString("descripcion"));
                est.setCantcanchas(rs.getInt("cantcanchas"));
                est.setFacebook(rs.getString("facebook"));
                est.setServicio1(rs.getString("servicio1"));
                est.setIdcancha(rs.getString("idcancha"));
                est.setTwitter(rs.getString("twiter"));
                est.setInstagram(rs.getString("instagram"));
                est.setYoutube(rs.getString("youtube"));
                est.setServicio2(rs.getString("servicio2"));
                est.setServicio3(rs.getString("servicio3"));
                est.setFoto(rs.getString("foto"));
                datos.add(est);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al consultar id_establecimiento: "+est.getIdestablecimiento()+" "+e);
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ImpEsta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return datos;
    }

    @Override
    public List<?> consultarDatosUsuario(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editarDataUsuario(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario validarUsuario(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
