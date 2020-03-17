package com.sfp.controler;

import com.sfp.ejb.CauConfiguracionFacadeLocal;
import com.sfp.ejb.CauMenuFacadeLocal;
import com.sfp.ejb.CauUsuariosFacadeLocal;
import com.sfp.model.CauUsuarios;
import com.sfp.lib01.*;
import com.sfp.model.CauEmp;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import com.sfp.ejb.CauEmpFacadeLocal;
import com.sfp.ejb.CauMesaservicioFacadeLocal;
import com.sfp.ejb.CauRolxtranxparFacadeLocal;
import com.sfp.ejb.CauRolxusuarioFacadeLocal;
import com.sfp.ejb.CauUsuariotipoFacadeLocal;
import com.sfp.model.CauConfiguracion;
import com.sfp.model.CauMesaservicio;
import com.sfp.model.CauRolxtranxpar;
import com.sfp.model.CauRolxusuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.faces.context.ExternalContext;


@Named(value = "Login01Controler")
//@Dependent
//@ViewScoped
@SessionScoped 
public class Login01Controler implements Serializable {

    @EJB
    private CauUsuariosFacadeLocal EJBUsuario;
    
    @EJB
    private CauEmpFacadeLocal EJBEmpleado;
    
    @EJB 
    private CauMesaservicioFacadeLocal EJBMesaServicio;
    
    @EJB
    private CauRolxusuarioFacadeLocal EJBERolXUsuario;
    
    @EJB
    private CauRolxtranxparFacadeLocal EJBERolXtranxpar;
    
    @EJB
    private CauUsuariotipoFacadeLocal EJBUsuariotipo; 
    
    @EJB
    private  CauMesaservicioFacadeLocal EJBMesaservicio;
    
    @EJB
    private  CauConfiguracionFacadeLocal EJBConfiguracion;
    
    Collection<CauRolxtranxpar> CauRolxtranxparCollection;
    private List<CauMesaservicio> Lista_Mesaserv;        
    private CauUsuarios usuario;
    private String SolicitudLogin_Parametro;
    private String DepartamentoLogin_Parametro;
    private String DescripcionDeptoLogin_Parametro;
    private String OrigenLlamadaLogin_parametro;
    private boolean showUndo=false;
    private CauEmp emp;
    private String MesaDeServicio_parametro="";
    private String EnvioMesa;
    private int CuentaMesa;
    private String MesasServicio_Act= null;
   // int mesaserv = 0;
    
    public Login01Controler() {
    }
       
   @PostConstruct
   private void init(){
       usuario = new CauUsuarios();
       showUndo = false;
       SolicitudLogin_Parametro="";
       DepartamentoLogin_Parametro="";
       DescripcionDeptoLogin_Parametro="";
       OrigenLlamadaLogin_parametro="CAU_Login";
       EnvioMesa = "";
       
   } 

    public String getMesasServicio_Act() {
        return MesasServicio_Act;
    }

    public void setMesasServicio_Act(String MesasServicio_Act) {
        this.MesasServicio_Act = MesasServicio_Act;
    }

   
   
    public Collection<CauRolxtranxpar> getCauRolxtranxparCollection() {
        return CauRolxtranxparCollection;
    }

    public void setCauRolxtranxparCollection(Collection<CauRolxtranxpar> CauRolxtranxparCollection) {
        this.CauRolxtranxparCollection = CauRolxtranxparCollection;
    }

   
   
    public String getEnvioMesa() {
        return EnvioMesa;
    }

    public void setEnvioMesa(String EnvioMesa) {
        this.EnvioMesa = EnvioMesa;
    } 
   
    public String getMesaDeServicio_parametro() {
        return MesaDeServicio_parametro;
    }

    public void setMesaDeServicio_parametro(String MesaDeServicio_parametro) {
        this.MesaDeServicio_parametro = MesaDeServicio_parametro;
    }
   
    public String getSolicitudLogin_Parametro() {
        return SolicitudLogin_Parametro;
    }

    public void setSolicitudLogin_Parametro(String SolicitudLogin_Parametro) {
        this.SolicitudLogin_Parametro = SolicitudLogin_Parametro;
    }

    public String getDepartamentoLogin_Parametro() {
        return DepartamentoLogin_Parametro;
    }

    public void setDepartamentoLogin_Parametro(String DepartamentoLogin_Parametro) {
        this.DepartamentoLogin_Parametro = DepartamentoLogin_Parametro;
    }

    public String getDescripcionDeptoLogin_Parametro() {
        return DescripcionDeptoLogin_Parametro;
    }

    public void setDescripcionDeptoLogin_Parametro(String DescripcionDeptoLogin_Parametro) {
        this.DescripcionDeptoLogin_Parametro = DescripcionDeptoLogin_Parametro;
    }

    public boolean isShowUndo() {
        return showUndo;
    }

    public void setShowUndo(boolean showUndo) {
        this.showUndo = showUndo;
    }

    public String getOrigenLlamadaLogin_parametro() {
        return OrigenLlamadaLogin_parametro;
    }

    public void setOrigenLlamadaLogin_parametro(String OrigenLlamadaLogin_parametro) {
        this.OrigenLlamadaLogin_parametro = OrigenLlamadaLogin_parametro;
    }

    public CauUsuariosFacadeLocal getEJBUsuario() {
        return EJBUsuario;
    }

    public void setEJBUsuario(CauUsuariosFacadeLocal EJBUsuario) {
        this.EJBUsuario = EJBUsuario;
    }

    public CauUsuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(CauUsuarios usuario) {
        this.usuario = usuario;
    }
   
     public boolean doAction(){
      showUndo=!showUndo;
      return showUndo;
    }
    
    public String iniciaSession(){
        CauUsuarios us;
        String redireccion = null;
        boolean esnumero;
        esnumero = Libra01.isInteger(usuario.getClaveusua());
        if (esnumero) {
            try {
                showUndo = false;
                //Se verifica contra la tabla empleados que simula el web service si el empleado existe
                emp = EJBEmpleado.iniciarSessionEmpleado(usuario);
                emp.setNombre("Usuario : " + emp.getNombre() + "   ");
                // si el proceso anterior envia la entidad emp como null se indica que el emplado no existe
                if (emp != null) {
                    // Se almacena la session de jsf guardando el empleado y el usuario
                    // Se procede a consultar el usuario generico de solicitantes
                    // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("empleado", emp);
                    usuario.setNocredencial(Long.parseLong(usuario.getClaveusua()));
                    usuario.setClaveusua("UsuaSolicitante");
                    usuario.setContrasena("SinPassword");                    
                    usuario.setCorreo(emp.getCorreo());
                    us = EJBUsuario.iniciarSession(usuario);
                    if (us != null) {
                        //se almacena la session de jsf guardando el usuario generico para solicitantes 
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
                        // Se establece la pagina principal de del usuario o una en blanco depende del menu
                        redireccion = "/redireccionalapaginaprincipaldelusuario?faces-redirect=true"; 
                        //36 es el numero de transacción para seleccionar mesas de servicio para usuarios 
                        // que realizan solicitudes esta en el rol 1 que esta asignado a el usuario "UsuaSolicitante"
                        ObtenParametrosRolTrans(36); 
                        //if (NumMesas() == 1){
                         mesaServicio(us);
                        if (MesaDeServicio_parametro.split(",").length == 1){
                            // redireccion a la pagina de selección de selección de solicitudes
                             EnvioMesa="False";
                            return "/PrimeFaces/cauSolicitudes/index?faces-redirect=true";
                        } else {
                            // redireccion a la pagina de selección de selección de mesa de trabajo
                             EnvioMesa="True";
                             return "/PrimeFaces/MesaServicio/index?faces-redirect=true";
                        }
                        //return "/Include/template?faces-redirect=true";
                        //return "/PrimeFaces/Menu/index?faces-redirect=true";
                    }
                } else {
                    // se notifica al usuario que el numero de usuario no existe en l sistema
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Credenciales Incorrectas"));
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error grave"));
            }
        } else { // en otro caso no es numerico y se trata de un usuario administrador jefe de departamento y/o tecnico
            try {
                if (showUndo == true) { // esto quiere decir que ya trae el password
                    us = EJBUsuario.iniciarSession(usuario);
                    emp = us.getIdNocredencial();
                    emp.setNombre("Usua Adm: " + emp.getNombre() + "  ");
                    if (us != null) {
                        // se almacena la session del jsf guardando el usuario generico para administradores                        
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
                        // Se establece la pagina principal de del usuario o una en blanco depende del menu                        
                        redireccion = "/redireccionalapaginaprincipaldelosadministradoes?faces-redirect=true"; 
                        //37 es el numero de transacción para seleccionar mesas de servicio para usuarios 
                        // Administradores esta en el rol 2 para administradores
                        // consulta del numero de mesas que tiene el usuario para solicitar servicios
                        // la funcion obtiene en la variable 
                        ObtenParametrosRolTrans(4);
                        mesaServicio(us);
                        if (MesaDeServicio_parametro.split(",").length == 1){
                            // cuando el usuario tiene una mesa asignada entonces se precenta la pagina Admin
                            EnvioMesa="False";
                            return "/PrimeFaces/Admin/index?faces-redirect=true";                            
                        } else {
                            // cuando el usuario tiene una mas de una mesa asignada entonces se pasa a la pagina
                            // MesaServicioAdm para que seleccione la mesa de servicios 
                            EnvioMesa="True";
                            return "/PrimeFaces/MesaServAdmin/index?faces-redirect=true"; 
                        }                       
                        //return "/PrimeFaces/Admin/index?faces-redirect=true";
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Credenciales Incorrectas"));
                    }
                } else { // esto quiere decir que no tiene el password se activan los campos para capturar el password
                    showUndo = !showUndo;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Digite el contraseña del usuario"));
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Usuario o contraseña no valido"));
            }
        }
        return null;
    }   
    
    public String  CerrarSession(){
        String cadena = null;        
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        if (externalContext != null){
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession(); 
        }         
       return "/PrimeFaces/Login_Inicial/index?faces-redirect=true";       
    }
    
    
    //btiene el nombre del usuario autenticado se usa en la pagina 
    // include/encabezado.xhtml
    public String ObtenUsuario(){
        if (emp != null) {
          return emp.getNombre();  
        }
        else
          return "No Autenticado";
    }
   // Obtiene las descripciones de las mesas de servicio seleccionadas para mostrarlo 
   // en el encabezado  include/encabezado.xhtml 
    public String MesaServicio() {
        String[] MesasDesc; 
        String DescMesas = "";
        if (MesaDeServicio_parametro.trim().length() > 0) {
            MesasDesc = MesaDeServicio_parametro.split(",");            
            CauMesaservicio Mesaservicioc;
            for (int i = 0; i < MesasDesc.length; i++) {
                Mesaservicioc = EJBMesaServicio.find(Integer.parseInt(MesasDesc[i]));
                if (DescMesas.length() == 0) {
                    DescMesas = Mesaservicioc.getDescripcion();
                } else {
                    DescMesas = DescMesas + "," + Mesaservicioc.getDescripcion();
                }
            }
        }
        return DescMesas;
    }
    
    // obtiene las mesas a las que tiene acceso el usuario que se especifica en  
   // el el rol que tiene asignado   
    private void ObtenParametrosRolTrans(int transaccion ) {
        // se obtienen los roles del usuario de la tabla CAU_RolXUsuario
        Collection<CauRolxusuario> cauRolxusuarioCollection;
        String RolesUsuario = "";
        String Usuariox = usuario.getClaveusua();
        cauRolxusuarioCollection = EJBERolXUsuario.findxRango(Usuariox);

        for (CauRolxusuario paso : cauRolxusuarioCollection) {
            if (RolesUsuario.length() == 0) {
                RolesUsuario = paso.getIdRol().getIdRol().toString();
            } else {
                RolesUsuario = RolesUsuario + "," + paso.getIdRol().getIdRol();
            }
        }
        if (RolesUsuario.length() == 0){
            RolesUsuario = "0";
        }
        // se obtiene todos los parametros para la transaccion que se pasa por parametro 
        // de los roles del usuario obtenidos en el paso anterior 
        String Cadena1 = "Select c "
                       + " from CauRolxtranxpar c"
                       + " where c.idRolxtrans.idRol.idRol in (" + RolesUsuario + " ) and "
                       + "  c.idRolxtrans.idTrans.idTrans = ?1";
        
//    Cadena1 = "Select c "
//                   + " from CauRolxtranxpar c"
//                   + " where c.idRolxtrans.idRol.idRol = 1 and "
//                   + "  c.idRolxtrans.idTrans.idTrans = 36";
//
//    Cadena1 = "Select c "
//                   + " from CauRolxtranxpar c";
        
        CauRolxtranxparCollection = EJBERolXtranxpar.finxRol(Cadena1, transaccion);
    }
    
    public int NumMesas() {
        CuentaMesa = 0;
        String mesaserv = "";
        for (CauRolxtranxpar paso : CauRolxtranxparCollection) {
            // el objeto parametro de la colleccion tiene una instancia del Objetotipaut que es
            // el objeto de autorización que nos indica de que parametro estamos hablando 
            // en este caso el objeto es la mesa de servicio es 1 que tiene 
            // como tipo 1 "cadena de filtro"
           if (paso.getIdParam().getIdObjtipaut().getIdObjtipaut() == 1){
               if (mesaserv.length() == 0){
                    mesaserv = paso.getValor();
               }
               else {
                   mesaserv = mesaserv + "," + paso.getValor();
               }              
               CuentaMesa++;              
           }
           CuentaMesa = mesaserv.split(",").length;
        }   
        // Si la cuenta es uno entonces solo tiene una mesa de servicio
        MesaDeServicio_parametro = mesaserv;                   
        return CuentaMesa;
    }    

    public static boolean isNumeric(String cadena) {
        boolean resultado;
        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }
        return resultado;
    }
    public int TipoUsuario(){       
       String consulta;
       int tipousuario = 0;
       if (usuario != null) {
           consulta = "Select c from CauUsuariotipo c"
                   + " where c.idUsuario.claveusua = ?1 order by c.idTipousuario.idTipousuario";
           tipousuario = EJBUsuariotipo.Consulta_TipoUsuario(consulta, usuario.getClaveusua());
       }      
       return tipousuario;
   }
    
    public String mesaServicio(CauUsuarios us) {
        String consulta;
        List<CauConfiguracion> ListaConf;
        if (TipoUsuario() == 1) {
            // i. Se consulta la tabla CAU_MesaServicio y se obtienen todos
            // los valores del campo ID_MesaServ y se almacenan en la variable 
            // MesasServicio_Act separados por comas.  
            consulta = "Select c from CauMesaservicio c";
            Lista_Mesaserv = EJBMesaservicio.ConsultaMesa(consulta, 0);
            for (CauMesaservicio paso1 : Lista_Mesaserv) {
                if (MesasServicio_Act == null) {
                    MesasServicio_Act = paso1.getIdMesaserv().toString().trim();
                } else {
                    MesasServicio_Act = MesasServicio_Act + "," + paso1.getIdMesaserv().toString().trim();
                }
            }
        } else if ( TipoUsuario() == 4){
            // obtiene las mesas de servicio que estan abilitadas para los usuarios 
            consulta = "Select c from CauConfiguracion c where c.url = ?1";
            ListaConf = EJBConfiguracion.Consulta_Mesaconf(consulta, "Mesa_conf");
            MesasServicio_Act = "";
            for (CauConfiguracion paso1 : ListaConf){
                if (MesasServicio_Act.length() == 0) {
                    MesasServicio_Act = paso1.getIdMesaserv().getIdMesaserv().toString().trim();
                } else {
                   MesasServicio_Act  = MesasServicio_Act + "," + paso1.getIdMesaserv().getIdMesaserv().toString().trim();
                }
                   
            }
            // MesasServicio_Act = "2";
        } 
        else {
            MesasServicio_Act = us.getIdAreagrupos().getIdMesaserv().getIdMesaserv().toString();
        }
        MesaDeServicio_parametro = MesasServicio_Act;   
        return MesasServicio_Act;
    }
}
