package REST;

import DAO.ImpEsta;
import DAO.Impcal;
import DAO.Impcancha;
import DAO.Impcanchaf;
import DAO.ImplementOperaciones;
import DAO.Impreserva;
import Objetos.Calendario;
import Objetos.Cancha;
import Objetos.Contactenos;
import Objetos.Reserva;
import Objetos.Respuesta;
import Objetos.Establecimiento;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import Objetos.Usuario;
import java.util.*;
import javax.ws.rs.*;

@Path("servicios")
public class ServiciosREST {

    ImplementOperaciones oper = new ImplementOperaciones();
    Impcanchaf opercf = new Impcanchaf();
    ImpEsta operest = new ImpEsta();
    Impcancha operCancha = new Impcancha();
    Impcal opercal = new Impcal();
    Impreserva operres = new Impreserva();
//    Imphistorico operhis = new Imphistorico();

    /*VALIDA SI EL USUARIO ESTA REGISTRADO EN LA BD*/
    @POST
    @Path("validarUsuarioRegistrado")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta validarUsuarioRegistrado(Usuario usu) {
        System.out.println("consulta inicio sesion usuario: " + usu.getUsuario());
        Respuesta r = new Respuesta();
        r.setUsuarioData(oper.validarUsuario(usu));

        if (r.getUsuarioData().getUsuario() != null) {
            r.setCodigo(1);
            r.setDescripcion("Consulta Exitosa Usuario: " + r.getUsuarioData().getUsuario());
        } else {
            r.setCodigo(0);
            r.setDescripcion("Usuario no Existente");
        }
        return r;
    }
    
    /*REGISTRA EL USUARIO A LA BD*/
    @POST
    @Path("registroUsuario")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registroUsuario(Usuario usu) {
        System.out.println("Registro Usuario "+usu.getUsuario());
        Respuesta r = new Respuesta();
        if (oper.insertar(usu)) {
            r.setCodigo(1);
            r.setDescripcion("Usuario "+usu.getUsuario()+" Registrado con exito.");
            r.setUsuario(usu.getUsuario());
            r.setUsuarioData(usu);
        }else{
            r.setCodigo(0);
            r.setDescripcion("Error al registrar el Usuario "+usu.getUsuario());
        }
        return r;
    }

    /*CONSULTA LA DATA DEL USUARIO*/
    @POST
    @Path("consultDataPerfil")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario consultDataPerfil(Usuario usu) {
        System.out.println("Consulta data Usuario: " + usu.getUsuario());
        Usuario usuarioRespuesta = new Usuario();
        ImplementOperaciones Oper = new ImplementOperaciones();
        usuarioRespuesta.setArregloUsuario(Oper.consultDataPerfil(usu));
        return usuarioRespuesta;
    }
    
    /*EDITA LOS DATOS DEL USUARIO*/
    @POST
    @Path("editarDataUsuario")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta editarDataUsuario(Usuario usu) {
        System.out.println("Edicion Usuario: "+usu.getPnombre());
        Respuesta r = new Respuesta();
        if (oper.editarDataUsuario(usu)) {
            r.setCodigo(1);
            r.setDescripcion("Edicion Usuario: "+usu.getUsuario());
            
        }
        return r;
    }
    
    /*CONSULTA LAS RESERVAS SEGUN EL USUARIO*/
    @POST
    @Path("consultarReservas")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta consultarReservas(Usuario usu) {
        System.out.println("Consulta Reservas usuario: "+usu.getUsuario());
        Reserva r = new Reserva();
        Respuesta rta = new Respuesta();
        rta.setReserva(oper.consultaResevas(usu));
        if (rta.getReserva().getArregloReserva().size() > 0) {
            rta.setCodigo(1);
            rta.setDescripcion("Consulta Exitosa usuario: "+usu.getUsuario());
        }
        else{
            rta.setCodigo(0);
            rta.setDescripcion("Error al consultar usuario: "+usu.getUsuario());
        }

        return rta;
    }
    
    /*CONSULTA EL HISTOICO LAS RESERVAS SEGUN EL USUARIO*/
    @POST
    @Path("consultarReservasHistorico")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta consultarReservasHistorico(Usuario usu) {
        System.out.println("Consulta Historico usuario: "+usu.getUsuario());
        Reserva r = new Reserva();
        Respuesta rta = new Respuesta();
        rta.setReserva(oper.consultarReservasHistorico(usu));
        if (rta.getReserva().getArregloReserva().size() > 0) {
            rta.setCodigo(1);
            rta.setDescripcion("Consulta Exitosa usuario: "+usu.getUsuario());
        }
        else{
            rta.setCodigo(0);
            rta.setDescripcion("Error al consultar usuario: "+usu.getUsuario());
        }

        return rta;
    }
    
    
    /*ENVIA EL FORMULARIO DE CONTACTENOS*/
    @POST
    @Path("envioFormularioContactenos")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta envioFormularioContactenos(Contactenos contac) {
        Respuesta rta = new Respuesta();
        System.out.println("Contactenos: "+contac.getNombre());
        if (oper.envioFormularioContactenos(contac)) {
            rta.setCodigo(1);
            rta.setDescripcion("Formulario enviado con Exito!");
        }
        else{
            rta.setCodigo(0);
            rta.setDescripcion("Error al enviar el formulario");
        }

        return rta;
    }
    
    @POST
    @Path("consultaEstablecimiento")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public List<Establecimiento> datoest(Establecimiento est) {
        System.out.println("Consulta Establecimiento: "+est.getIdestablecimiento());
        List<Establecimiento> lista = new ArrayList<>();
        Respuesta rta = new Respuesta();
        lista = operest.postdev(est);
        return lista;
    }
    
    @POST
    @Path("consultaCancha")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Cancha datoP(Cancha can) {
        Cancha canchaData = new Cancha();
        canchaData.setArregloCanchas(operCancha.consultaCancha(can));
        return canchaData;
    }
    
    @POST
    @Path("consultarDisponibilidadCalendario")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Calendario> datoc(Calendario cal) {
        ArrayList<Calendario> lista = new ArrayList<>();
        lista = opercal.consultarDisponibilidadCalendario(cal);
        return lista;
    } 
    
    @POST
    @Path("insertarReserva")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)    
    public Respuesta insertar(Reserva res) {
        Respuesta r = new Respuesta();
        if (operres.insertar(res)) {
            r.setCodigo(1);
            r.setDescripcion("Reserva Exitosa");
        }else{
            r.setCodigo(0);
            r.setDescripcion("Error al reservar");
        }
        return r;
    }
    
    @POST
    @Path("eliminarReserva")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)    
    public Respuesta eliminarReserva(Reserva res) {
        Respuesta r = new Respuesta();
        if (operres.eliminar(res)) {
            r.setCodigo(1);
            r.setDescripcion("La Reserva:\n"
                    +"No. "+res.getIdreserva()
                    +"\nFecha: "+res.getFecha()
                    +"\nHora: "+res.getHora()
                    +"\nEstablecimiento: "+res.getIdestablecimiento()
                    +"\nCancha: "+res.getIdcancha()
                    +"\nfue Eliminada con Exito.");
        }else{
            r.setCodigo(0);
            r.setDescripcion("Error al eliminar la Reserva:"+res.getIdreserva());
        }
        return r;
    }
}
