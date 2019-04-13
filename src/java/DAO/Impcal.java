/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Objetos.Calendario;
import Objetos.Establecimiento;
import Objetos.Usuario;
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
public class Impcal implements InterfaceOperaciones{
    Conexion conexion = new Conexion();
    Connection conn;
    PreparedStatement p;
    ResultSet rs;
    Statement st = null;
    
    @Override
    public boolean insertar(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
    public ArrayList<Calendario> consultarDisponibilidadCalendario(Object obj) {
        ArrayList<Calendario> datosCal = new ArrayList<>();
        Calendario cal = (Calendario) obj;
        
        System.out.println(cal.getIdestablecimiento());
        System.out.println(cal.getIdcancha());
        System.out.println(cal.getDisponibilidad());
        System.out.println(cal.getFecha());
        
        try {
            Class.forName(conexion.getDriver());
            conn = DriverManager.getConnection(conexion.getUrl(), conexion.getUsuario(), conexion.getClave());
//            String sql = "SELECT * FROM public.calendario where (idestablecimiento =  ? and idcancha = ? and disponibilidad = ? and fecha = ?);";
            String sql = "SELECT * FROM public.calendario where fecha = '2019-01-07';";
            p = conn.prepareStatement(sql);
//            p.setString(1,cal.getIdestablecimiento());
//            p.setString(2,cal.getIdcancha());
//            p.setString(3,cal.getDisponibilidad());
//            p.setDate(4,Date.valueOf(cal.getFecha()));
            rs = p.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("precio"));
                Calendario objCalendario = new Calendario();
                objCalendario.setHora(String.valueOf(rs.getInt("hora")));
                objCalendario.setDisponibilidad(rs.getString("disponibilidad"));
                objCalendario.setPrecio(rs.getString("precio"));
                datosCal.add(objCalendario);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Eror al consultar "+e);
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Impcal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return datosCal;
    }
}


