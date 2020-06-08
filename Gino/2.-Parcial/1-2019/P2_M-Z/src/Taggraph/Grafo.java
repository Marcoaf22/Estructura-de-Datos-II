package Taggraph;

import java.util.LinkedList;

public class Grafo {    //Grafo dirigido y con peso 
    private static final int MAXVERTEX = 49;    //Máximo índice de V[]
  
    private Lista V[];
    private int n;       //"Dimensión" de V[]
   
    public Grafo(){
       V = new Lista[MAXVERTEX+1];      //V[0..MAXVERTEX]
       n = -1;
       
       marca = new boolean[MAXVERTEX+1];    //Iniciar la ED para el marcado de los vértices.
    }
    
    public boolean isAlcanzable(int a,int z,int msj){
        if(isVerticeValido(a,"alcanzable") && isVerticeValido(z,"Alcanzable")){
        desmarcarTodos();
        msj=msj%2;
        return isAlcanzable1(a,z,msj);
        }
        return false;
    }
    private boolean isAlcanzable1(int a,int z,int msj){
        marcar(a);
        boolean sh=a==z;
        if (!sh){
            for (int i=0;i<V[a].length();i++){
                int w=V[a].get(i);
                if (costo(a, w)%2==msj){
                    if (!isMarcado(w)){
                        sh=isAlcanzable1(w, z, msj);
                    }
                }
            }
        }
        return sh;
    }
    
   /* public boolean isAlcanzable(int a, int z, int msj){    //PREGUNTA 2.
        return alcanzable1(a, z, msj);
    }
    
    private boolean alcanzable1(int v, int u, int msj){  //mask-function de void dfs(int)        
        marcar(v);        
        for (int i = 0; i < V[v].length(); i++) {   //for (cada w adyacente a v)
            int w = V[v].get(i);
            if((msj % 2) == (V[v].getPeso(w) % 2) ){
                if( w == u){
                    return true;
                }else
                if (!isMarcado(w))
                    if(alcanzable1(w, u, msj))
                        return true;
            }
        }
        return false;
    }*/
    
    
    public int costo(int u, int v){  //Devuelve el peso de la arista u-->v.  Si esa arista no existe, devuelve 0
        if (!isVerticeValido(u) || !isVerticeValido(v))
            return 0;
        
        return V[u].getPeso(v);
    }
    
    public void addVertice(){
        if (n == MAXVERTEX){
            System.err.println("Grafo.addVertice: Demasiados vértices (solo se permiten "+(MAXVERTEX+1)+")");
            return;
        }    
        
        n++;
        V[n] = new Lista();     //Crear un nuevo vértice sin adyacentes (o sea con su Lista de adyacencia vacía)
    }
    
    public int cantVertices(){
        return n+1;
    }
    
    public boolean isVerticeValido(int v){
        return (0<=v && v<=n);
    }
    
    public void addArista(int u, int peso, int v){  //Crea la arista u-->v, con el peso especificado.
        if (peso <= 0){
            System.err.println("Grafo.addArista:  El peso debe ser mayor que cero");
            return;
        }
        
        String metodo="addArista";
        if (!isVerticeValido(u, metodo) || !isVerticeValido(v, metodo))
            return;     //No existe el vertice u o el vertice v.
        
        V[u].add(v, peso);      //Adicionar (data, peso) a la lista V[u]    
    } 
    
    public void delArista(int u, int v){  //Elimina la arista u-->v
        String metodo="delArista";
        if (!isVerticeValido(u, metodo) || !isVerticeValido(v, metodo))
            return;     //No existe el vertice u o el vertice v.
        
        V[u].del(v);
    } 
    
    
    
    public void dfs(int v){  //Recorrido Depth-First Search (en profundidad).
        if (!isVerticeValido(v, "dfs"))
            return;  //Validación. v no existe en el Grafo.
        
        desmarcarTodos();
        System.out.print("DFS:");
        dfs1(v);
        System.out.println();
    }
    
    private void dfs1(int v){  //mask-function de void dfs(int)
        System.out.print(" "+v);
        marcar(v);
        
        for (int i = 0; i < V[v].length(); i++) {   //for (cada w adyacente a v)
            int w = V[v].get(i);
            
            if (!isMarcado(w))
                dfs1(w);
        }
    }
    
    
    public void bfs(int u){  //Recorrido Breadth-First Search (en anchura).
        if (!isVerticeValido(u, "bfs"))
            return;  //Validación. u no existe en el Grafo. 
       
        desmarcarTodos();
        LinkedList <Integer> cola = new LinkedList<>();  //"cola" = (vacía) = (empty)
        cola.add(u);     //Insertar u a la "cola" (u se inserta al final de la lista).
        marcar(u);
        
        System.out.print("BFS:");
        do{
            int v = cola.pop();         //Obtener el 1er elemento de la "cola".
            System.out.print(" "+v);
            
            for (int i = 0; i < V[v].length(); i++) {   //for (cada w adyacente a v)
                int w = V[v].get(i);
            
                if (!isMarcado(w)){
                    cola.add(w);
                    marcar(w);
                }    
            }
        }while (!cola.isEmpty());  
        
        System.out.println();
    }
    
    
    public void printListas(){  //Muestra las listas del Grafo.  Util para el programador de esta class
        if (cantVertices()==0)
            System.out.println("(Grafo Vacío)");
        else{
            for (int i = 0; i <= n; i++) {
                System.out.println("V["+i+"]-->"+V[i]);  
            }  
        }
    }
    
    @Override
    public String toString(){   //Este método debe ser cambiado si el grafo es dirigido.
        if (cantVertices()==0)
            return "(Grafo Vacío)";
        
        final String SEPARADOR = ", ";
        
           //Mostrar las aristas u-->v del grafo como (u, peso, v)
        desmarcarTodos();
        String S="[";
        String coma="";
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= n; k++) {   
                int peso = costo(i, k);
                if (peso > 0){
                    String arista = "(" + i + "," + peso + "," + k + ")";
                    S += coma + arista;
                    coma = SEPARADOR;
                    marcar(i);
                }            
            }
            
            if (!isMarcado(i)){     //El vertice i no tiene aristas
                S += coma + i;
                coma = SEPARADOR;
            }           
        }
        
        return S+"]";
    } 

    
    private boolean isVerticeValido(int v, String metodo){
        boolean b = isVerticeValido(v);
        if (!b)
            System.err.println("Grafo."+metodo+": " + v + " no es un vértice del Grafo " + getIndicacion());

        return b;
    }
    
    private String getIndicacion(){  //Corrutina de boolean isVerticeValido(int, String)
        switch (cantVertices()){
            case 0  :   return "(el grafo no tiene vértices). ";
            case 1  :   return "(el 0 es el único vértice). ";
            case 2  :   return "(0 y 1 son los únicos vértices). ";
            default :   return "(los vértices van de 0 a " + (cantVertices()-1) + "). "; 
        }
    }

//********* Para el marcado de los vértices
    private boolean marca[];
    
    private void desmarcarTodos(){
        for (int i = 0; i <= n; i++) {
            marca[i] = false;  
        }
    }
    
    private void marcar(int u){
        if (isVerticeValido(u))
           marca[u] = true; 
    }
    
    private void desmarcar(int u){
        if (isVerticeValido(u))
           marca[u] = false; 
    }
    
    private boolean isMarcado(int u){   //Devuelve true sii el vertice u está marcado.
        return marca[u]; 
    }
    private boolean alcanza1(int u,int v){
        boolean sh=false;
        marcar(u);
        if (u==v)
            return true;
        for (int i=0;i<V[u].length();i++){
            int w=V[u].get(i);
            if (!isMarcado(w)){
                sh=sh || alcanza1(w, v) || w==v;
            }
        }
        return sh;
    }
    public boolean alcanza(int u,int v){
        if (isVerticeValido(v)&&isVerticeValido(u)){
            desmarcarTodos();
            return alcanza1(u,v);
        }else{
            return false;
        }
        
    }
    public int pesoRecorrido(int u,int v){
        if (isVerticeValido(v,"pesoRecorrido") && isVerticeValido(u,"pesoRecorrido")){
            if(alcanza(u, v)){
                desmarcarTodos();
                return pesoRecorrido1(u,v);
            }
        }
        return -1;
    }
    private int pesoRecorrido1(int u,int v){
        int cant=0;
        marcar(u);
            for (int i=0;i<V[u].length();i++){
                int w=V[u].get(i);
                if (!isMarcado(w)){
                    cant=pesoRecorrido1(w,v)+costo(u,w);
                    if (v==w)
                        return cant;
                }
            }
        return cant;
    }
}
