/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Objetos.*;
import java.sql.Connection;
import java.sql.Date;
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
public class Impreserva implements InterfaceOperaciones{
    
    Conexion conexion = new Conexion();
    Connection conn;
    PreparedStatement p;
    ResultSet rs;
    Statement st = null;

    @Override
    public boolean insertar(Object obj) {
        Reserva res = (Reserva) obj;
        boolean registro = false;
        String fecha = res.getAnio()+"-"+res.getMes()+"-"+res.getDia();
        try {
            Class.forName(conexion.getDriver());
            conn = DriverManager.getConnection(conexion.getUrl(), conexion.getUsuario(), conexion.getClave());


            String sql = "INSERT INTO reserva (idreserva,idestablecimiento,idcancha,observaciones,usuario,anio,mes,dia,hora,fecha) VALUES (?,?,?,?,?,?,?,?,?,?)";
            p = conn.prepareStatement(sql);
            p.setString(1, res.getIdreserva());
            p.setString(2, res.getIdestablecimiento());
            p.setString(3, res.getIdcancha());
            p.setString(4, res.getObservaciones());
            p.setString(5, res.getUsuario());
            p.setInt(6, Integer.parseInt(res.getAnio()));
            p.setInt(7, Integer.parseInt(res.getMes()));
            p.setInt(8, Integer.parseInt(res.getDia()));
            p.setInt(9, Integer.parseInt(res.getHora()));
            p.setDate(10, Date.valueOf(fecha));
            p.executeUpdate();

            String sql1 = "INSERT INTO historico (usuario,idreserva,anio,mes,dia,hora,idestablecimiento,idcancha,observaciones,fecha) VALUES (?,?,?,?,?,?,?,?,?,?)";
            p = conn.prepareStatement(sql1);
            p.setString(1, res.getUsuario());
            p.setString(2, res.getIdreserva());
            p.setInt(3, Integer.parseInt(res.getAnio()));
            p.setInt(4, Integer.parseInt(res.getMes()));
            p.setInt(5, Integer.parseInt(res.getDia()));
            p.setInt(6, Integer.parseInt(res.getHora()));
            p.setString(7, res.getIdestablecimiento());
            p.setString(8, res.getIdcancha());
            p.setString(9, res.getObservaciones());
            p.setDate(10, Date.valueOf(fecha));
            p.executeUpdate();
            
            String sql2 = "UPDATE calendario SET disponibilidad='NO_Disponible' WHERE idestablecimiento=? and idcancha=? and anio=? and mes=? and dia=? and hora=?";
            p = conn.prepareStatement(sql2);
            p.setString(1, res.getIdestablecimiento());
            p.setString(2, res.getIdcancha());
            p.setInt(3, Integer.parseInt(res.getAnio()));
            p.setInt(4, Integer.parseInt(res.getMes()));
            p.setInt(5, Integer.parseInt(res.getDia()));
            p.setInt(6, Integer.parseInt(res.getHora()));
            p.executeUpdate();

            
            registro = true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al registrar la reserva "+e);
            registro = false;
        }finally{
            try {
                this.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Impreserva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registro;
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
        boolean modific = false;
        Reserva res = (Reserva) obj;
        try {
            String sql = "DELETE FROM public.reserva WHERE idreserva = ? AND usuario = ?;";
            Class.forName(conexion.getDriver());
            conn = DriverManager.getConnection(conexion.getUrl(), conexion.getUsuario(), conexion.getClave());
            p = conn.prepareStatement(sql);
            p.setString(1,res.getIdreserva());
            p.setString(2,res.getUsuario());
            p.executeUpdate();
            modific = true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error SQL al eliminar reserva "+e);
        }finally{
            try {
                this.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Impreserva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        try {
            String sql = "UPDATE calendario SET disponibilidad = 'Disponible' where idestablecimiento=? and idcancha=? and hora=? and fecha=?;";
            Class.forName(conexion.getDriver());
            conn = DriverManager.getConnection(conexion.getUrl(), conexion.getUsuario(), conexion.getClave());
            p = conn.prepareStatement(sql);
            p.setString(1,res.getIdestablecimiento());
            p.setString(2,res.getIdcancha());
            p.setInt(3,Integer.parseInt(res.getHora()));
            p.setDate(4,Date.valueOf(res.getFecha()));
            p.executeUpdate();
            modific = true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error SQL UPDATE "+e);
        }finally{
            try {
                this.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Impreserva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        try {
            String sql = "DELETE FROM public.historico WHERE idreserva = ? AND usuario = ?;";
            Class.forName(conexion.getDriver());
            conn = DriverManager.getConnection(conexion.getUrl(), conexion.getUsuario(), conexion.getClave());
            p = conn.prepareStatement(sql);
            p.setString(1,res.getIdreserva());
            p.setString(2,res.getUsuario());
            p.executeUpdate();
            modific = true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error SQL al eliminar historico "+e);
        }finally{
            try {
                this.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Impreserva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return modific;
    }

    @Override
    public String validarusu(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public ArrayList<Establecimiento> postdev2(Object obj) {
        Reserva res = (Reserva) obj;

        ArrayList<Reserva> datos = new ArrayList<>();

        String sql = "Select * from reserva where usuario=?";
        try {
            Class.forName(conexion.getDriver());
            conn = DriverManager.getConnection(conexion.getUrl(), conexion.getUsuario(), conexion.getClave());
            p = conn.prepareStatement(sql);
            
            p.setString(1,res.getUsuario());
            rs = p.executeQuery();
            
            while (rs.next()) {
//                datos.add(new Reserva(
//                        rs.getString("idreserva"),
//                        rs.getInt("anio"),
//                        rs.getInt("mes"),
//                        rs.getInt("dia"),
//                        rs.getInt("hora"),
//                        rs.getString("idestablecimiento"),
//                        rs.getString("idcancha"),
//                        rs.getString("observaciones"),
//                        rs.getString("usuario")));
            }
        } catch (ClassNotFoundException | SQLException e) {

        }finally{
            try {
                this.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Impreserva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
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

    @Override
    public List<Establecimiento> postdev(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
