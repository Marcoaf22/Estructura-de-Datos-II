package Digraph;

public class Main {

    public static void main(String[] args){
       Grafo G = new Grafo();
       
       G.addVertice();
       G.addVertice();
       G.addVertice();
       G.addVertice();
       G.addVertice();
       G.addVertice();
       G.addVertice();
       G.addVertice();
       G.addVertice();
       G.addArista(0, 2);
       G.addArista(0, 3);
       G.addArista(1, 6);
       G.addArista(3, 1);
       G.addArista(3, 6);
       G.addArista(4, 0);
       G.addArista(4, 2);
       G.addArista(5, 4);
       G.addArista(5, 0);
       G.addArista(5, 3);
       G.addArista(6, 1);
       G.addArista(6, 2);
       G.addArista(8, 7);
       G.printListas();
       G.dfs(5);
       System.out.println(G.path(8));
       //G.bfs(0);
    }
}
