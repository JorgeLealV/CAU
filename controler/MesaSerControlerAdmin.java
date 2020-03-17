/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfp.controler;
import com.sfp.ejb.CauMesaservicioFacadeLocal;
import com.sfp.ejb.CauUsuariosFacadeLocal;
import com.sfp.model.CauMesaservicio;
import com.sfp.model.CauRolxtranxpar;
import com.sfp.model.CauUsuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author jleal
 */
@Named(value = "mesaSerControlerAdm")
//@SessionScoped 
@ViewScoped
public class MesaSerControlerAdmin implements Serializable {

    public MesaSerControlerAdmin() {
        // super(CauDepartamentos.class);
    }
   
    @Inject private Login01Controler Login01Controler1;
    
    @EJB
    private CauMesaservicioFacadeLocal EJBMesa;
    
    private CauUsuarios Usua;
    private String OrigenLlamada;
    private String CadenaMesas;
    
    private String[] selectedMesas2;
    private List<String> Mesas;
    
    private String cadena_mesas_valor;
    private String cadena_mesas_Descripción;
    
    Collection<CauRolxtranxpar> CauRolxtranxparCollectionx;


    
    @PostConstruct
    private void init() {
       CauMesaservicio mesax;
       FacesContext facesContext = FacesContext.getCurrentInstance();
       Map params = facesContext.getExternalContext().getRequestParameterMap();
       ObtenParametros();
       Login01Controler1.setEnvioMesa("True");
       CauRolxtranxparCollectionx = Login01Controler1.getCauRolxtranxparCollection();
       cadena_mesas_valor  = ""  ;
       cadena_mesas_Descripción  = ""  ;
       Mesas = new ArrayList<String>();
       //selectedMesas2 = new ArrayList<String>();
       Mesas.clear();
       // recorre objeto rolxtranxpar verificando que sea el objeto
       // de mesa de servicio id_param = 1 de la transacción 37 seleccion de mea de servicio
       for (CauRolxtranxpar paso : CauRolxtranxparCollectionx) {
           // objeto de mesa de servicio
           if (paso.getIdParam().getIdObjtipaut().getIdObjtipaut() == 1){                              
               if (cadena_mesas_valor.length() > 0) {
                   cadena_mesas_valor = cadena_mesas_valor + "," + paso.getValor();
               } else {
                   cadena_mesas_valor = paso.getValor();
               }
           }
        }  
        for (String aa:cadena_mesas_valor.split(",")){
              mesax = EJBMesa.find(Integer.parseInt(aa));
              Mesas.add(aa + "-" + mesax.getDescripcion());
        }
    }
    
    public String SeleccionarMesa(){       
        CadenaMesas = "";
        String[] MesasDesc;
         
        for(int i=0 ; i< selectedMesas2.length;i++){  
            MesasDesc = selectedMesas2[i].split("-"); 
            if (CadenaMesas.length() == 0){
                CadenaMesas = MesasDesc[0];
            }
            else {
                CadenaMesas = CadenaMesas + "," +  MesasDesc[0];
            }
        }
        Login01Controler1.setMesaDeServicio_parametro(CadenaMesas);
        
        if (CadenaMesas.trim().length() == 0){
            return "";
        } else {
            Login01Controler1.setEnvioMesa("False");
            //return "/PrimeFaces/cauSolicitudes/index?faces-redirect=true";
            return "/PrimeFaces/Admin/index?faces-redirect=true";
        }        
    }
    
    public boolean Selectedrec1() {
        return false;
    }
    
    private void ObtenParametros(){
         OrigenLlamada = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Origen");
    }

    public String[] getSelectedMesas2() {
        return selectedMesas2;
    }

    public void setSelectedMesas2(String[] selectedMesas2) {
        this.selectedMesas2 = selectedMesas2;
    }

    public List<String> getMesas() {
        return Mesas;
    }

    public void setMesas(List<String> Mesas) {
        this.Mesas = Mesas;
    }
    
   
   
    
}
