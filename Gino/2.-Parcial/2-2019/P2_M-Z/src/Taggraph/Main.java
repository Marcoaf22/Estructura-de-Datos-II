package Taggraph;

public class Main {

    private static final int[] a={6,100,2, 0,20,1, 8,10,0, 8,10,1, 3,5,0, 3,15,1, 4,60,7, 5,20,7};
    private static final int[] b={2,20,6, 6,10,2, 6,50,8, 8,15,2, 5,40,0, 5,5,7, 7,10,0, 7,15,5, 9,65,1, 10,80,3};
    
    private static void cargar(Grafo G, int n, int aristas[]){
        int i=1;
        for (; i <= n; i++){    //Cargar n vertices
             G.addVertice();
        }
              
        for (i=0; i < aristas.length; i += 3) { //Cargar cada 3 valores: VerticeOrigen, Peso-Arista, VerticeDestino
            G.addArista(aristas[i], aristas[i+1], aristas[i+2]);
        }
    }
    
    
    
    public static void main(String[] args){
        Grafo A = new Grafo();
        cargar(A, 9, a);        //Crear el Grafo A mostrado en el examen.
        
        Grafo B = new Grafo();
        cargar(B, 11, b);        //Crear el Grafo B mostrado en el examen.
     
        A.printListas();       
   
        System.out.println( B.costoIsla(4) );
        
       // B.printListas();
        //System.out.println( B.costoIsla(2) );
    }
}
