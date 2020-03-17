/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.controler;
import com.sfp.ejb.CauDepartamentosFacadeLocal;
import com.sfp.model.CauDepartamentos;
import com.sfp.model.CauSolicitudes;
import com.sfp.model.CauUsuarios;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author jleal
 */
@Named(value = "deptoControler")
//@SessionScoped 
@ViewScoped
public class DeptoControler implements Serializable {

    public DeptoControler() {
        // super(CauDepartamentos.class);
    }

    @EJB
    private CauDepartamentosFacadeLocal EJBDepartamentos;
    
    //@Inject private SolicControler SolicControler1;
    
    @Inject private MantTrabControler MantTrabControler1;
    @Inject private Login01Controler Login01Controler1;

    private CauDepartamentos selected;
    private Collection<CauDepartamentos> items;
    private CauUsuarios Usua;
    
    private String EntradaSolic ;
    
    //private String SalidaSolic;
    //private String SalidaDepto;
    //private String SalidaDescDepto;
    private String OrigenLlamada;

    public String getEntradaSolic() {
        return EntradaSolic;
    }

    public void setEntradaSolic(String EntradaSolic) {
        this.EntradaSolic = EntradaSolic;
    }

    
    @PostConstruct
    private void init() {
       FacesContext facesContext = FacesContext.getCurrentInstance();
       Map params = facesContext.getExternalContext().getRequestParameterMap();
       ObtenParametros();
    }

    private void ObtenParametros(){
         OrigenLlamada = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Origen");
         if (OrigenLlamada.equals("CAU_SolicConstruc")){//             
             if (Login01Controler1 != null){
                 EntradaSolic = Login01Controler1.getSolicitudLogin_Parametro();
             }
         } else if (OrigenLlamada.equals("CAU_MantTrab")){
            if (MantTrabControler1 != null){
                EntradaSolic = MantTrabControler1.getSalidaSolic();
             }
             else if (Login01Controler1 != null){
                 EntradaSolic = Login01Controler1.getSolicitudLogin_Parametro();
             }            
         } else if (OrigenLlamada.equals("CAU_Tipotrabajo")){
             EntradaSolic = Login01Controler1.getSolicitudLogin_Parametro();
         }        
    }
    
    
    public CauDepartamentos getSelected() {
        return selected;
    }
    
    public void setSelected(CauDepartamentos selected) {
        this.selected = selected;
    }

    public Collection<CauDepartamentos> getItems() {
        if (items == null) {
            ObtenSolicitudes();
        }
        return items; 
    }

    public void setItems(Collection<CauDepartamentos> items) {
        this.items = items;
    }
    
    
    public void  ObtenSolicitudes(){    
       Date Fecha = new Date();
       String fechaS;
       String Mesaservicio;
       DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
       fechaS = dateFormat.format(Fecha);
       Mesaservicio = Login01Controler1.getMesaDeServicio_parametro();
       String consulta1 = "SELECT c FROM CauDepartamentos c " 
                        + " WHERE c.idMesaserv.idMesaserv = "
                        +  Mesaservicio 
                        + " and c.despliegue = 'SI'"
                        + " and ?1 between c.fechaviginicdep and c.fechavigfindep ";   
       setItems(EJBDepartamentos.Consulta_Depto(consulta1,Fecha));
    }
    
   public String RedirecTraba(){    
       // Procede a dar de alta un trabajo
       ObtenParametros();
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Origen", "CAU_Deptos");
       // SalidaSolic = EntradaSolic;
       Login01Controler1.setDepartamentoLogin_Parametro(selected.getIdDepartamentos().toString());
       Login01Controler1.setDescripcionDeptoLogin_Parametro(selected.getDescripcion());       
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Origen", "CAU_Deptos");
       return "/PrimeFaces/cauTipotrabajo/index?faces-redirect=true";
   } 
   
    public String Salir() {       
        ObtenParametros();            
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Origen", "CAU_Deptos");
        if (EntradaSolic == null || EntradaSolic.equals("0")) {           
            return "/PrimeFaces/cauSolicitudes/index?faces-redirect=true";
        } else {            
            return "/PrimeFaces/MantTrab/index?faces-redirect=true";
        }       
    }
}
