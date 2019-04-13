package DAO;

import DAO.Conexion;
import Objetos.Contactenos;
import Objetos.Establecimiento;
import Objetos.Reserva;
import Objetos.Respuesta;
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

public class ImplementOperaciones implements DAO.InterfaceOperaciones {

    Conexion conexion = new Conexion();
    Connection conn;
    PreparedStatement p;
    ResultSet rs;
    Statement st = null;

    @Override
    public List<?> consultar(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String modificar(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String validarusu(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Establecimiento> postdev(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean insertar(Object obj) {
        boolean registro = false;
        Usuario u = (Usuario) obj;
        String sql = "INSERT INTO public.usuario (genero, pnombre, snombre, papellido, sapellido, fecha, contrasena, usuario, correo, perfil) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            Class.forName(conexion.getDriver());
            conn = DriverManager.getConnection(conexion.getUrl(), conexion.getUsuario(), conexion.getClave());
            p = conn.prepareStatement(sql);
            p.setString(1, u.getGenero());
            p.setString(2, u.getPnombre());
            p.setString(3, u.getSnombre());
            p.setString(4, u.getPapellido());
            p.setString(5, u.getSapellido());
            p.setString(6, u.getFechan());
            p.setString(7, u.getContrasena());
            p.setString(8, u.getUsuario());
            p.setString(9, u.getCorreo());
            p.setString(10, "Usuario");
            p.executeUpdate();
            registro = true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al insertar el usuario "+u.getUsuario());
        }finally{
            try {
                this.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ImplementOperaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registro;
    }

    @Override
    public List<Usuario> consultarDatosUsuario(Object obj) {
        Usuario usu = (Usuario) obj;
        ArrayList<Usuario> datos = new ArrayList<>();

        String sql = "SELECT * FROM usuario";
        try {
            Class.forName(conexion.getDriver());
            conn = DriverManager.getConnection(conexion.getUrl(), conexion.getUsuario(), conexion.getClave());
            p = conn.prepareStatement(sql);
            rs = p.executeQuery();

//            while (rs.next()) {
//                datos.add(new Usuario(
//                        rs.getString("pnombre"),
//                        rs.getString("snombre"),
//                        rs.getString("papellido"),
//                        rs.getString("sapellido"),
//                        rs.getString("fecha"),
//                        rs.getString("genero"),
//                        rs.getString("usuario"),
//                        rs.getString("contrasena"),
//                        rs.getString("correo"),
//                        rs.getString("perfil")));
//            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {

        }
        return datos;
    }

    @Override
    public boolean editarDataUsuario(Object obj) {
        Usuario u = (Usuario) obj;
        String sql = "UPDATE usuario SET pnombre=?,snombre=?,papellido=?,sapellido=?,fecha=?,genero=?,correo=?,contrasena=? WHERE usuario=?";
        boolean respuesta = false;
        try {
            Class.forName(conexion.getDriver());
            conn = DriverManager.getConnection(conexion.getUrl(), conexion.getUsuario(), conexion.getClave());
            p = conn.prepareStatement(sql);
            p.setString(1, u.getPnombre());
            p.setString(2, u.getSnombre());
            p.setString(3, u.getPapellido());
            p.setString(4, u.getSapellido());
            p.setString(5, u.getFechan());
            p.setString(6, u.getGenero());
            p.setString(7, u.getCorreo());
            p.setString(8, u.getContrasena());
            p.setString(9, u.getUsuario());
            p.executeUpdate();
            respuesta = true;
        } catch (ClassNotFoundException | SQLException e) {
            respuesta = false;
        }finally{
            try {
                this.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ImplementOperaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return respuesta;
    }

    @Override
    public boolean eliminar(Object obj) {
        Usuario usu = (Usuario) obj;

        String sql = "DELETE FROM usuario WHERE usuario=?";
        boolean respuesta = false;

        try {
            Class.forName(conexion.getDriver());
            conn = DriverManager.getConnection(conexion.getUrl(), conexion.getUsuario(), conexion.getClave());
            p = conn.prepareStatement(sql);
            p.setString(1, usu.getUsuario());
            int filas = p.executeUpdate();
            respuesta = true;
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
        }
        return respuesta;
    }

    @Override
    public Usuario validarUsuario(Object obj) {
        Usuario rtaUsuario = new Usuario();
        Usuario usu = (Usuario) obj;
        String usuario = usu.getUsuario();
        String pass = usu.getContrasena();

        try {
            String sql = "select * from public.usuario where usuario = ? and contrasena = ?";
            Class.forName(conexion.getDriver());
            conn = DriverManager.getConnection(conexion.getUrl(), conexion.getUsuario(), conexion.getClave());
            p = conn.prepareStatement(sql);
            p.setString(1, usuario);
            p.setString(2, pass);
            rs = p.executeQuery();
            while (rs.next()) {
                rtaUsuario.setUsuario(rs.getString("usuario"));
                rtaUsuario.setContrasena(rs.getString("contrasena"));
                rtaUsuario.setCorreo(rs.getString("correo"));
                rtaUsuario.setPerfil(rs.getString("perfil"));
            }
            
        } catch (Exception e) {
            System.out.println("No ingreso error "+e);
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ImplementOperaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return rtaUsuario;
    }
    
    public ArrayList<Usuario> consultDataPerfil(Object obj) {
        Usuario usu = (Usuario) obj;
        ArrayList<Usuario> datos = new ArrayList<>();
        try {
            Class.forName(conexion.getDriver());
            conn = DriverManager.getConnection(conexion.getUrl(), conexion.getUsuario(), conexion.getClave());
            String sql = "Select * from usuario where usuario=?";
            p = conn.prepareStatement(sql);
            p.setString(1,usu.getUsuario());
            rs = p.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setPnombre(rs.getString("pnombre"));
                usuario.setSnombre(rs.getString("snombre"));
                usuario.setPapellido(rs.getString("papellido"));
                usuario.setSapellido(rs.getString("sapellido"));
                usuario.setFechan(rs.getString("fecha"));
                usuario.setGenero(rs.getString("genero"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setPerfil(rs.getString("perfil"));
                datos.add(usuario);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al consultar el Usuario "+usu.getUsuario());
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ImplementOperaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return datos;
    }
    
    public String contact(Object obj) {
        Contactenos con = (Contactenos) obj;
        String sql = "INSERT INTO contactenos (nombre,email,mensaje) VALUES (?,?,?)";
        
        String respuesta = "";
        try {
            Class.forName(conexion.getDriver());
            conn = DriverManager.getConnection(conexion.getUrl(), conexion.getUsuario(), conexion.getClave());
            System.out.println("Conectado a Base de datos");
            p = conn.prepareStatement(sql);
            String a = con.getNombre();
            String b = con.getEmail();
            p.setString(1, con.getNombre());
            p.setString(2, con.getEmail());
            p.setString(3, con.getMensaje());
            int filas = p.executeUpdate();
            String sql1 = "select codigo from contactenos where nombre='"+a+"' and email='"+b+"'";
            p = conn.prepareStatement(sql1);
            rs = p.executeQuery();
            while (rs.next()) {
            //sql = "INSERT INTO contactenos (nombre,email,mensaje) VALUES (?,?,?)";
            
            respuesta =rs.getString("codigo");
            }
            
            this.conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            respuesta = "NO fue posible el ingreso de la informacion a la base de datos";
        }
        return respuesta;
     }
    
    public Reserva consultaResevas(Object obj){
        Reserva reservaData = new Reserva();
        ArrayList<Reserva> arrayReservas = new ArrayList<>();
        Usuario usu = (Usuario) obj;

        try {
            Class.forName(conexion.getDriver());
            conn = DriverManager.getConnection(conexion.getUrl(), conexion.getUsuario(), conexion.getClave());
            String sql = "SELECT * FROM public.reserva where usuario = ? and fecha::date >= current_date::date;";
            p = conn.prepareStatement(sql);
            p.setString(1,usu.getUsuario());
//            p.setDate(2,usu.getFechaActual());
            rs = p.executeQuery();
            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setIdreserva(rs.getString("idreserva"));
                reserva.setIdestablecimiento(rs.getString("idestablecimiento"));
                reserva.setIdcancha(rs.getString("idcancha"));
                reserva.setObservaciones(rs.getString("observaciones"));
                reserva.setUsuario(rs.getString("usuario"));
                reserva.setAnio(rs.getString("anio"));
                reserva.setMes(rs.getString("mes"));
                reserva.setDia(rs.getString("dia"));
                reserva.setFecha(rs.getString("fecha"));
                reserva.setHora(rs.getString("hora"));
                arrayReservas.add(reserva);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al consultar las reservas del usuario "+usu.getUsuario()+" "+e);
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ImplementOperaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        reservaData.setArregloReserva(arrayReservas);
        return reservaData;
    }
    
    public Reserva consultarReservasHistorico(Object obj){
        Reserva reservaData = new Reserva();
        ArrayList<Reserva> arrayReservas = new ArrayList<>();
        Usuario usu = (Usuario) obj;
        try {
            Class.forName(conexion.getDriver());
            conn = DriverManager.getConnection(conexion.getUrl(), conexion.getUsuario(), conexion.getClave());
            String sql = "SELECT * FROM public.reserva where usuario = ?;";
            p = conn.prepareStatement(sql);
            p.setString(1,usu.getUsuario());
            rs = p.executeQuery();
            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setIdreserva(rs.getString("idreserva"));
                reserva.setFecha(String.valueOf(rs.getDate("fecha")));
                reserva.setHora(rs.getString("hora"));
                reserva.setIdestablecimiento(rs.getString("idestablecimiento"));
                reserva.setIdcancha(rs.getString("idcancha"));
                reserva.setObservaciones(rs.getString("observaciones"));
                reserva.setUsuario(rs.getString("usuario"));
                arrayReservas.add(reserva);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al consultar las reservas del usuario "+usu.getUsuario());
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ImplementOperaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        reservaData.setArregloReserva(arrayReservas);
        return reservaData;
    }
    
    public boolean envioFormularioContactenos(Object obj){
        Contactenos contact = (Contactenos) obj;
        boolean registro = false;
        try {
            String sql = "INSERT INTO public.contactenos (nombre, email, mensaje) VALUES(?, ?, ?);";
            Class.forName(conexion.getDriver());
            conn = DriverManager.getConnection(conexion.getUrl(), conexion.getUsuario(), conexion.getClave());
            p = conn.prepareStatement(sql);
            p.setString(1, contact.getNombre());
            p.setString(2, contact.getEmail());
            p.setString(3, contact.getMensaje());
            p.executeUpdate();
            registro = true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al enviar el formulario de Contacto");
        }finally{
            try {
                this.conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ImplementOperaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registro;
    }

    
}
