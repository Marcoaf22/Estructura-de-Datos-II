package Digraph;

public class Main {
    
    private static final int[] a ={2,8, 3,5, 3,6, 4,0, 5,0, 5,3, 5,4, 6,1, 8,7};
    
    
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
        cargar(A, 10, a);       //Cargar el grafo mostrado en el examen.
                
        A.printListas();
        
        A.printHojas(5);
        System.out.println(A.kAlcanzable(3, 1,2));
        System.out.println(A.kAlcanzable(5, 5,1));
                
      
    }
}
