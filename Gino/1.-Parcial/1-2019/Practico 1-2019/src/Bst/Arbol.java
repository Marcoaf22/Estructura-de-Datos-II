 package Bst;

import java.util.LinkedList;

public class Arbol {
    private Nodo Raiz;
    private int n;
    
    public Arbol(){
        Raiz = null;
        n    = 0;
    }
    
    public int cantNodos(){
        return n;
    }
    
    
    public void Insertar(int x){
        if (Raiz==null)
            Raiz = new Nodo(x);
        else{   //Caso general
            Nodo Ant=null;
            Nodo p=Raiz;
            
            while (p!=null){
                Ant=p;
                if (x < p.getData())  
                    p = p.getHI();
                else
                    if (x > p.getData())
                        p=p.getHD();
                    else
                        return;  //Salir. x ya está en el árbol.
            }
            
            Nodo nuevo = new Nodo(x);
            
            if (x < Ant.getData()) 
                Ant.setHI(nuevo);
            else
                Ant.setHD(nuevo);
        }
        
        n++;
    }
    
    public void Inorden(){
        System.out.print("Inorden : ");
        
        if (Raiz == null)
            System.out.println("(Arbol vacío)");
        else{
            Inorden(Raiz, "");
            System.out.println();
        }
    } 
     
    private void Inorden(Nodo T, String coma){
        if (T != null){
            Inorden(T.getHI(), ", ");
            System.out.print(coma + T.getData());
            Inorden(T.getHD(), ", ");
        }
    }
    
    private boolean hoja(Nodo T){   //Devuelve true sii T es una hoja.
        return (T != null && T.cantHijos() == 0);
    }
    
    public void niveles(){
        niveles("");
    }
    
    public void niveles(String nombreVar){
        if (nombreVar != null && nombreVar.length()>0)
            nombreVar = " del Arbol "+nombreVar;
        else
            nombreVar = "";
                    
        System.out.print("Niveles"+nombreVar+": ");
        
        if (Raiz == null)
            System.out.println("(Arbol vacío)");
        else
            niveles(Raiz);
    }
    
    
//---------- Métodos auxiliares para mostrar el árbol por niveles --------------
    private void niveles(Nodo T){   //Pre: T no es null.
        LinkedList <Nodo> colaNodos   = new LinkedList<>();
        LinkedList<Integer> colaNivel = new LinkedList<>();
        
        int nivelActual = 0;
        String coma="";
        
        colaNodos.addLast(T);
        colaNivel.addLast(1);
        
        do{
            Nodo p = colaNodos.pop();       //Sacar un nodo p de la cola
            int nivelP = colaNivel.pop();   //Sacar el nivel donde se encuentra p.
            
            if (nivelP != nivelActual){ //Se está cambiando de nivel
                System.out.println();
                System.out.print("  Nivel "+nivelP+": ");
                nivelActual = nivelP;
                coma = "";
            }
            
            System.out.print(coma + p.getData());
            coma = ", ";
            
            addHijos(colaNodos, colaNivel, p, nivelP);   
        }while (colaNodos.size() > 0);
        
        System.out.println();
    }
    
    private void addHijos(LinkedList <Nodo> colaNodos, LinkedList<Integer> colaNivel,  Nodo p, int nivelP){
        for (int i=1; i<=Nodo.M; i++){  //Insertar a la cola de nodos los hijos no-nulos de p
            Nodo hijo = p.getHijo(i);
            
            if (hijo != null){
                colaNodos.addLast(hijo);
                colaNivel.addLast(nivelP+1);
            }
        }
    }
}
