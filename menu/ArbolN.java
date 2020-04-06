package com.sfp.menu;

import javax.annotation.PostConstruct;
import org.primefaces.model.menu.DefaultSubMenu;

/**
 * Clase que arma el árbol con los nodos
 */
//@Named(value = "arbolN")
//@Dependent
//@SessionScoped
public class ArbolN {
    private DefaultSubMenu Raiz;
    Menu dato;
    NodoNario NodoNarioR;
    // establece el nodo raiz
    public ArbolN() {
        Raiz = new DefaultSubMenu();
        dato = new Menu("0", "", "R", "Sin URL", "Raiz principal");
        dato.raiz = Raiz;
        Raiz.setLabel("Raiz principal");
        NodoNarioR = new NodoNario(dato);
    }
    
    //@PostConstruct
    public void init() { 
       //Raiz = new DefaultMenuModel();
         Raiz = new DefaultSubMenu();
         dato = new Menu("0","","R","Sin URL","Raiz principal");
         dato.raiz=Raiz;
         Raiz.setLabel("Raiz principal");
         NodoNarioR=new NodoNario(dato); 
    }
    
   // Obtiene el nodo raiz
   //public DefaultMenuModel getModel() {
   public DefaultSubMenu getModel() {    
        return Raiz;
    }
   
   public NodoNario getRaiz() {        
         return NodoNarioR;
    }
   
   // inserta un nodo al árbol n-ario
   public void insertar (NodoNario raiz, NodoNario nuevo){
       //mira si el padre es la raiz
       if (raiz.getHijo().equals(nuevo.getPadre())){
           raiz.aumentarHijo(nuevo);
       } else {
           //busca entre los hijos el padre
           for (int i=0; i<raiz.getHijos();i++){
               if(raiz.hijos.get(i).getHijo().equals(nuevo.getPadre())){
                   //se coloca el hijo en el nodo
                   raiz.hijos.get(i).aumentarHijo(nuevo);
               } else {
                   //busca el padre en los hijos del nodo
                   insertar(raiz.hijos.get(i),nuevo);
               }
           }
       }
   } 


   //Recorre el árbol de raíz a hijos
   public void recorrer(NodoNario raiz){
       raiz.verInfo();
       for (int i=0; i<raiz.getHijos();i++){
           recorrer(raiz.hijos.get(i));
       }
   }
      
   //Recorre el árbol de hijos a raíz
   public void recorrerHijosRaiz(NodoNario raiz){
       for(int i=0; i<raiz.getHijos();i++){
           recorrerHijosRaiz(raiz.hijos.get(i));
       }   
       raiz.verInfo();
   }
   
   //busca un dato en el árbol y regresa si lo encontró o no
   public boolean buscar (NodoNario raiz, String buscar, boolean encontrado){
       if(raiz.getDato().equals(buscar)){
           encontrado=true;
       }
       for(int i=0; i<raiz.getHijos(); i++){
           encontrado=buscar(raiz.hijos.get(i), buscar, encontrado);
       }
       return encontrado;
   }
   //cuenta el número de nodos en el árbol
   public int cantidadNodos (NodoNario raiz){
       int mayor=0;
       if (raiz==null){
           return 0;           
       }else{
           for(int i=0; i<raiz.getHijos();i++){
               mayor+= cantidadNodos(raiz.hijos.get(i));
           }
           return mayor+1;
       }
   }
   //calcula la altura del árbol nario
   public int altura (NodoNario raiz){
       int mayor=0; 
       int tempo=0;
       if (raiz==null){
           return 0;
       }else{
           for(int i=0;i<raiz.getHijos();i++){
               tempo=altura(raiz.hijos.get(i));
               if(tempo>mayor){
                   mayor=tempo;
               }
           }
           return mayor+1;
       }
   }
   
   //regresa el número de nodods sin hijos
   public int numeroHojas(NodoNario raiz){
       int acum=0;
       if (raiz.getHijos()==0){
           return 1;
       }else{
           for (int i=0; i<raiz.getHijos();i++){
               acum+=numeroHojas(raiz.hijos.get(i));
           }
           return acum;
       }
   }
   //devuelve el nivel del nodo que se busque sin contar la raíz
   public int nivelElemento (NodoNario raiz, String elemento, int nivel){
       int tempo=0;
       if(raiz==null){
           return -1;
       }else if (raiz.getDato().equals(elemento)){
           return nivel;
       }else{
           //se busca en los hijos
           for(int i=0; i<raiz.getHijos();i++){
               tempo=nivelElemento(raiz.hijos.get(i),elemento,nivel+1);
               if(tempo!=-1){
                   return tempo;
               }
           }
           return -1;
       }
   }
   //Borra un nodo del árbol
   public void borrarNodo(NodoNario raiz, String borrar, boolean rama){
       for(int i=0;i<raiz.getHijos();i++){
           //se borra con rama y todo
           if(raiz.hijos.get(i).dato.equals(borrar)
                   &&raiz.hijos.get(i).getHijos()!=0 &&rama){
               raiz.hijos.remove(i);
               raiz.actualizarNohijos();
               break;
           }else if(raiz.hijos.get(i).getDato().equals(borrar)&&
                   raiz.hijos.get(i).getHijos()==0){
               raiz.hijos.remove(i);
               raiz.actualizarNohijos();
               break;
           }
           borrarNodo(raiz.hijos.get(i),borrar,rama);
       }
   }             
}
