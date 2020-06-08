package mvias;

public class Main {
    private static final int a[] = {100,200,300, 40,60,85, 110,150,160, 270, 320,350,400, 5,10,15, 45,50,55, 65,70,80, 130,140, 165,170,190, 305,310, 410,420,500, 74,76, 175,180,185};
    
    
    private static void cargarDatos(ArbolM A, int data[]){
        for (int i=0; i < data.length; i++)
            A.insertar(data[i]);
    }
    
    public static void main(String[] args) {
       ArbolM A = new ArbolM();
       cargarDatos(A, a);   //Cargar el árbol A mostrado en el examen.
    
       A.niveles("A");   
       
        System.out.println("---------------------------------");
       
       A.delHoja(80);
       
       A.niveles("A");   
    }
    
}