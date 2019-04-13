/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Objetos.*;
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

/**
 *
 * @author Alberto
 */
public class Impcancha implements InterfaceOperaciones{
    Conexion conexion = new Conexion();
    Connection conn;
    PreparedStatement p;
    ResultSet rs;
    Statement st = null;
    
    @Override
    public boolean insertar(Object obj) {
    Cancha can = (Cancha) obj;
    String esta = can.getIdestablecimiento();
    String canc = can.getIdcancha();
        String sql = "INSERT INTO cancha (capacidad,tgrama,idcancha,idcalendario,caracteristicas,idestablecimiento,foto) VALUES (?,?,?,?,?,?,?)";
        String respuesta = "";
        try {
            Class.forName(conexion.getDriver());
            conn = DriverManager.getConnection(conexion.getUrl(), conexion.getUsuario(), conexion.getClave());

            p = conn.prepareStatement(sql);
            
            p.setString(1, can.getCapacidad());
            p.setString(2, can.getTgrama());
            p.setString(3, can.getIdcancha());
            p.setString(4, can.getIdcalendario());      
            p.setString(5,can.getCaracteristicas());
            p.setString(6, can.getIdestablecimiento());
            p.setString(7, can.getFoto());
          
            int filas = p.executeUpdate();
            
            
            String sql1= "INSERT INTO calendario VALUES (?,?,?,?,?,?,?)";
            respuesta = "se registraron" + filas + "nuevo elemento";
            p=conn.prepareStatement(sql1);
            // LOG.info("Conectado a base de datos");
            for(int i=1;i<=3;i++){
        int d = 0;
        int b = 0;
        int c = 0;
            if(i==1){
                for(int a=0;a<=11;a++){
                    p.setString(1,(esta));
                    p.setString(2,(canc));
                    p.setInt(3,2018);
                    d = d+1;
                    b =b +1;
                    c = c+1;
                    p.setInt(4,(d));                    
                    p.setInt(5,(b));                   
                    p.setInt(6,(c));
                    p.setString(7,"Disponible");
                    System.out.print("mes "+d+" ");
                    System.out.print("dia "+b+" ");
                    System.out.print("hora "+c+"\n");
                    if(c<24){
                    b=b-1;
                    d=d-1;
                    a=a-1;
                    }
                    else if(b<31){
                        a=a-1;
                        d=d-1;
                        c=0;
                    }else{
                        a=a++;
                        b=0;
                        c=0;
                    }
                 int fila1 = p.executeUpdate();
            respuesta="ingreso la nueva cancha"+filas+"nuevo elemento";
            }}
            
            
            
            } 
           
            conn.close();
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

    public ArrayList<Cancha> consultaCancha(Object obj) {
        Cancha canc = (Cancha) obj;
        ArrayList<Cancha> datos = new ArrayList<>();
        try {
            Class.forName(conexion.getDriver());
            conn = DriverManager.getConnection(conexion.getUrl(), conexion.getUsuario(), conexion.getClave());
            String sql = "Select * from cancha where idcancha = ? and idestablecimiento = ?";
            p = conn.prepareStatement(sql);   
            p.setString(1,canc.getIdcancha());
            p.setString(2,canc.getIdestablecimiento());
            rs = p.executeQuery();
            while (rs.next()) {                
                Cancha c = new Cancha();
                c.setCapacidad(rs.getString("capacidad"));
                c.setTgrama(rs.getString("tgrama"));
                c.setIdcancha(rs.getString("idcancha"));
                c.setIdcalendario(rs.getString("idcalendario"));
                c.setCaracteristicas(rs.getString("caracteristicas"));
                c.setIdestablecimiento(rs.getString("idestablecimiento"));
                c.setFoto(rs.getString("foto"));
                datos.add(c);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al consultar id_establecimiento: "+canc.getIdestablecimiento());
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Impcancha.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return datos;
    }
            
    @Override
    public List<Establecimiento> postdev(Object obj){
         return null;
    };

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
