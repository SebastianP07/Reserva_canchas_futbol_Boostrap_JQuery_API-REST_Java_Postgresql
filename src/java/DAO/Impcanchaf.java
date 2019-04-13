package DAO;

import Objetos.Canchaf;
import Objetos.Establecimiento;
import Objetos.Respuesta;
import Objetos.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Impcanchaf implements InterfaceOperaciones{

    Conexion conexion = new Conexion();
    Connection conn;
    PreparedStatement p;
    ResultSet rs;
    Statement st = null;
    
    @Override
    public boolean insertar(Object obj) {
    boolean as = false;
    Canchaf canf = (Canchaf) obj;
    String  a = canf.getDiresta();
    int b = canf.getValor();
        String sql = "INSERT INTO canchaf (usuario,idestablecimiento,foto,diresta,valor,caracteristica) VALUES (?,?,?,?,?,?)";
        String respuesta = "";
        try {
            Class.forName(conexion.getDriver());
            conn = DriverManager.getConnection(conexion.getUrl(), conexion.getUsuario(), conexion.getClave());

            p = conn.prepareStatement(sql);
            
            p.setString(1, canf.getUsuario());
            p.setString(2, canf.getIdestablecimiento());
            p.setString(3, canf.getDiresta());
            p.setString(4, canf.getFoto());      
            p.setInt(5,canf.getValor());
            p.setString(6, canf.getCaracteristica());
          
            int filas = p.executeUpdate();
            respuesta = "se registraron" + filas + "nuevo elemento";
            this.conn.close();

        } catch (ClassNotFoundException | SQLException e) {
                respuesta = "NO fue posible el ingreso de la informacion a la base de datos";
        }
        return as;   
    }

    @Override
    public List<Canchaf> consultarDatosUsuario(Object obj) {    
        Canchaf canf = (Canchaf) obj;
        ArrayList<Canchaf> datos = new ArrayList<>();

        String sql = "SELECT * FROM canchaf";
        try {
            Class.forName(conexion.getDriver());
            conn = DriverManager.getConnection(conexion.getUrl(), conexion.getUsuario(), conexion.getClave());
            p = conn.prepareStatement(sql);
            rs = p.executeQuery();

//            while (rs.next()) {
//                datos.add(new Canchaf(
//                        rs.getString("usuario"),
//                        rs.getString("idestablecimiento"),
//                        rs.getString("foto"),
//                        rs.getString("diresta"),
//                        rs.getInt("valor"),
//                        rs.getString("caracteristica")));
//            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {

        }
        return datos;

    }

    @Override
    public boolean editarDataUsuario(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario validarUsuario(Object obj) {
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
    public String validarusu(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Establecimiento> postdev(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
