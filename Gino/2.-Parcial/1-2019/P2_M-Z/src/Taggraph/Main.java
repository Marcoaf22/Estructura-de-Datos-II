package Taggraph;

public class Main {

    private static final int[] A={0,15,3, 0,25,2, 1,50,6, 3,25,1, 3,5,4, 3,30,6, 4,10,0, 4,35,2, 5,25,0, 5,10,3, 5,20,4, 6,5,1, 6,12,2, 8,10,7};
    
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
        Grafo G = new Grafo();
        cargar(G, 9, A);        //Crear el Grafo mostrado en el examen.
     
        G.printListas();
        
            //Pruebe aquí su método
        System.out.println( G.pesoRecorrido(4,6) );
    }
}
