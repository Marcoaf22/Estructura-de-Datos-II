package mvias;

public class Main {
    private static final int a[] = {100,200,300,  40,60,85, 110,150,160, 210,260,280, 320,350,400, 10,25,30, 65,68,80, 115,121, 165,170,190, 305,310, 410,420,500, 12,18, 75, 175,180,185};
    
    
    private static void cargarDatos(ArbolM A, int data[]){
        for (int i=0; i < data.length; i++)
            A.insertar(data[i]);
    }
    
    public static void main(String[] args) {
       ArbolM A = new ArbolM();
       cargarDatos(A, a);   //Cargar el árbol A mostrado en el examen.
    
       A.niveles("A");
    
       System.out.println( A.lastParent(68, 75) );   
    }
    
}
