package Graph;

public class Main {

    private static final int[] a={2,6, 0,1,0,3,0,8, 1,8,1,3,  4,7,5,7};
    private static final int[] b={2,6, 2,8,6,8,  0,5,0,7,5,7, 1,9, 3,10};
    
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
       cargar(A, 9, a);         //Cargar el grafo A, mostrado en el examen.
       
       Grafo B = new Grafo();
       cargar(B, 11, b);        //Cargar el grafo B, mostrado en el examen.
   
       A.printListas();
       B.printListas();
       
       System.out.println("Isla menor de A "+A.islaMenor());
       System.out.println("Isla menor de B "+B.islaMenor());
        System.out.println(A.cantAlcanzable(0));
    }
}
