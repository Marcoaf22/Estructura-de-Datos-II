package Digraph;

public class Main {
    
    private static final int[] a ={0,3, 1,3, 1,7, 2,6,  4,7, 5,0, 7,5, 8,1};
    
    
    private static void cargar(Grafo G, int cantVertices, int aristas[]){
        int i=1;
        for (; i <= cantVertices; i++){    //Cargar cantVertices vertices
             G.addVertice();
        }
              
        for (i=0; i < aristas.length; i += 2) { //Cargar cada 2 valores: VerticeOrigen, VerticeDestino
            G.addArista(aristas[i], aristas[i+1]);
        }
    }

    
    public static void main(String[] args){
        Grafo A = new Grafo();
        cargar(A, 9, a);
        
        System.out.println( A.kAlcanzable(8, 5, 6));    //Pruebe aquí su método.
        
        A.printListas();
      
    }
}
