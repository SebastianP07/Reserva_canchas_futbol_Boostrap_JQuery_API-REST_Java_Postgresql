
package DAO;

import Objetos.Establecimiento;
import Objetos.Respuesta;
import Objetos.Usuario;
import java.util.List;

public interface InterfaceOperaciones {
    public boolean insertar(Object obj);
    public List<?> consultarDatosUsuario(Object obj);
    public List<?> consultar(Object obj);
    public String modificar(Object obj);
    public String validarusu(Object obj);
    public List<Establecimiento> postdev(Object obj);
    public boolean editarDataUsuario(Object obj);
    public boolean eliminar(Object obj);
    public Usuario validarUsuario(Object obj);
}
