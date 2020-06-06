package Preg1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Arbol {
    private Nodo Raiz;
    
    public Arbol(int padre){        //Crea el árbol con raíz=padre
       Raiz = new Nodo(padre);
    }
    
    public void delHojaCercana(int x){  //** Preg 1.
        if (existe(x)) {
            if (Raiz != null) {
                ArrayList<Integer> hojas = new ArrayList<>();
                ArrayList<Integer> diferencia = new ArrayList<>();
                totalHojas(hojas, diferencia, Raiz, x);
                Collections.sort(diferencia);
                if (!hojas.isEmpty()) {
                    if (hojas.contains(x + diferencia.get(0))) {
                        Raiz = delHoja(Raiz, x + diferencia.get(0));
                    } else {
                        Raiz = delHoja(Raiz, x - diferencia.get(0));
                    }
                }
            }
        }        
    }
    //recolecta todas las hojas en un arraylist
    private void totalHojas(ArrayList<Integer> a, ArrayList<Integer> b, Nodo p, int x) {
        if (p != null) {
            if (hoja(p)) {
                a.add(p.Data);
                b.add(Math.abs(x - p.Data));
            } else {
                totalHojas(a, b, p.getHD(), x);
            }
            totalHojas(a, b, p.getHI(), x);
        }
    }   
   
    public boolean existe(int x){
        return existe(Raiz,x);
    }
    
    private boolean existe(Nodo p,int x){
        if (p==null){
            return false;
        }else{
            if (p.getData()==x){
                return true;
            }
            return existe(p.getHD(),x)||existe(p.getHI(),x);
        }
    }
    
    private Nodo delHoja(Nodo p, int x) {//eliminar una dato que sabemos que es hoja
        if (p != null) {
            if (p.Data == x) {
                return null;
            }
            p.HI = delHoja(p.HI, x);
            p.HD = delHoja(p.HD, x);
        }
        return p;
    }
    
    
    private boolean hoja(Nodo T){
        return (T != null && T.cantHijos() == 0);
    }
    
    
    public void add(int padre, int x){ //Inserta x al arbol, como hijo de padre. 
        Nodo p = fetch(Raiz, padre);
        if (p==null || fetch(Raiz, x) != null)
            return;     //El padre no existe o x ya está en el árbol.
       
        int i = p.getHijoNull();
        if (i != -1)
            p.setHijo(i, new Nodo(x));
    } 
    
    private Nodo fetch(Nodo t, int x){  //Fetch al nodo cuyo Data=x.
        if (t==null || t.getData() == x)
            return t;       
        
        
        Nodo hi = fetch(t.getHI(), x);
        if (hi != null)
            return hi;
        
        return fetch(t.getHD(), x);
    }
    
    public void Inorden(){
        if (Raiz == null)
            System.out.println("(Arbol vacío)");
        else{
            System.out.print("Inorden : ");
            Inorden(Raiz);
            System.out.println();
        }
    } 
     
    private void Inorden(Nodo T){
        if (T != null){
            Inorden(T.getHI());
            System.out.print(T.getData()+" ");
            Inorden(T.getHD());
        }
    }
    
    public void Preorden(){
        System.out.print("Preorden : ");
        
        if (Raiz == null)
            System.out.println("(Arbol vacío)");
        else{
            Preorden(Raiz);
            System.out.println();
        }
    }
    
    private void Preorden(Nodo T){
        if (T != null){
            System.out.print(T.getData() + " ");
            Preorden(T.getHI());
            Preorden(T.getHD());
        }
    }
    
      
    public void niveles(){ 
        System.out.print("Niveles: ");
        
        if (Raiz == null)
            System.out.println("(Arbol vacío)");
        else{
            niveles(Raiz);
        }    
    }
   
    
//---------- Métodos auxiliares para mostrar el árbol por niveles --------------
    private void niveles(Nodo T) {   //Pre: T no es null.
        LinkedList<Nodo> colaNodos = new LinkedList<>();
        LinkedList<Integer> colaNivel = new LinkedList<>();

        int nivelActual = 0;
        String coma = "";

        colaNodos.addLast(T);
        colaNivel.addLast(1);

        int x = nivel(Raiz);

        while (true) {

            Nodo p = colaNodos.pop();       //Sacar nodo de la cola
            int nivelP = colaNivel.pop();
            if (nivelP == x + 1) {
                break;
            }

            if (nivelP != nivelActual) { //Se está cambiando de nivel
                System.out.println();
                System.out.print("  Nivel " + nivelP + ": ");
                nivelActual = nivelP;
                coma = " ";
            }
            if (p != null) {
                System.out.print(coma + p);
                addHijos(colaNodos, colaNivel, p, nivelP);
            } else {
                System.out.print(coma + "=");
            }
            coma = " ";
        }
        System.out.println();
    }

    private void addHijos(LinkedList<Nodo> colaNodos, LinkedList<Integer> colaNivel, Nodo p, int nivelP) {

        for (int i = 1; i <= Nodo.M; i++) {  //Insertar a la cola de nodos los hijos no-nulos de p
            Nodo hijo = p.getHijo(i);
            colaNodos.addLast(hijo);
            colaNivel.addLast(nivelP + 1);
        }
    }

    private int nivel(Nodo p) {
        if (p==null){
            return 0;
        }else{
            int x=nivel(p.getHD());
            int y=nivel(p.getHI());
            if (x>y){
                return x+1;
            }
            return y+1;
        }
    }
    
    

}
