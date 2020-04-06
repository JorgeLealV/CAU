package com.sfp.menu;

import java.util.ArrayList;


public class NodoNario {
    int cantidadH;
    Menu dato;
    ArrayList <NodoNario> hijos;
   
    public NodoNario (Menu dato) {
        hijos= new ArrayList <> ();
        this.dato= dato;
        this.cantidadH=0;
    }
    //Adiciona un valor
    public void aumentarHijo(NodoNario nodo){
     //   nodo.dato.raiz = this.dato.getRaiz();
        hijos.add(nodo);
        if(dato.tipomenu.equals("R")) {
            if (nodo.dato.tipomenu.equals("AS")){
                dato.raiz.addElement(nodo.dato.getSubmenu());
            } else if (nodo.dato.tipomenu.equals("I")){
                dato.raiz.addElement(nodo.dato.getMenuitem());
            }
            else{
                //thru exepcion
            }
        }
        else if (dato.tipomenu.equals("AS")){
           if (nodo.dato.tipomenu.equals("AS")){
               dato.submenu.addElement(nodo.dato.getSubmenu());
           } else if(nodo.dato.tipomenu.equals("I")){
              dato.submenu.addElement(nodo.dato.getMenuitem());
           }            
           else{
                //thru exepcion
           }
        } else {
                  //thru exepcion        
        }  
        cantidadH= hijos.size();
    }
    public void actualizarNohijos(){
        cantidadH= hijos.size();
    }
    public void verInfo(){
        System.out.println("{ " + dato +" }");
    }
    public void verhijos(){
        System.out.println(cantidadH);
    }
    public void setDato (Menu dato){
        this.dato= dato;
    }
    public Menu getDato(){
        return dato;
    }
    public int getHijos(){
        return cantidadH;
    }
    public void setRestarHijos(){
        cantidadH--;
    }
    public NodoNario retornaNodo(){
        return this;
    }
    public String getHijo(){
        return dato.hijo;
    }
    public String getPadre(){
        return dato.padre;
    }
}
