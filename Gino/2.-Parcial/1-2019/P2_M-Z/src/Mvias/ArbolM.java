package mvias;
import java.util.LinkedList;


public class ArbolM {
    private NodoM raiz;
    private int n;          //n=cantidad de nodos.
    
    public ArbolM(){
        raiz = null;
        n = 0;
    }
    
        private NodoM delHojaPrima(NodoM t,int x){
        if (t==null){
            
        }else{
            if (!hoja(t)){
                if (!t.existe(x)){
                    NodoM padre=t.getHijo(hijoDesc(t, x));
                    if (!padre.existe(x)){
                        NodoM hijo=padre.getHijo(hijoDesc(padre, x));
                        if (hijo.existe(x)){
                            for (int k=1;k<=t.M;k++){
                                NodoM tio=t.getHijo(k);
                                for (int j=1;j<=padre.M;j++){
                                    if (tio!=padre && tio!=null && hoja(tio.getHijo(j))){
                                        tio.setHijo(j,null);
                                    }
                                }
                            }
                        }else{
                            int i=hijoDesc(t, x);
                            t.setHijo(i,delHojaPrima(t.getHijo(i), x));
                        }
                    }
                }
            }
        }
        return t;
    }
    public void delHojaPrima(int x){
        raiz=delHojaPrima(raiz,x);
    }
    
    public int getCantNodos(){
        return n;
    }
    
    public void insertar(int x){
        if (raiz == null){
            raiz = new NodoM(x);
        }
        else{
            int i=0;
            NodoM ant=null, p=raiz;
            while (p != null){
                if (!p.isLleno()){  //Hay espacio en el nodo p. Insertar x ahí.
                    p.insDataInOrden(x);
                    return;
                }
                
                i = hijoDesc(p, x);
                if (i == -1)
                    return;     //Salir. x está en el nodo p (El árbol no acepta duplicados)
                
                ant = p;
                p = p.getHijo(i);
            }
            
                //p llegó a null.  Crear una nueva hoja y engancharla a ant.
            ant.setHijo(i, new NodoM(x));
        }
        
        n++;
    }
    
    
    public void inorden(){
        System.out.print("Inorden:");
        if (raiz == null)
            System.out.println(" (Árbol vacío)");
        else{
            inorden(raiz);
            System.out.println();
        }
            
    }
    
    private void inorden(NodoM T){
        if (T != null){
            int z=T.cantDatasUsadas();      //z = índice de la última data usada.
            for (int i=1; i<=z; i++){
                inorden(T.getHijo(i));
                System.out.print(" "+T.getData(i));
            }
            inorden(T.getHijo(z+1));
        }
    }
    
    private int hijoDesc(NodoM N, int x){   //Corrutina de insertar.
        int i=1;
        while (i <= N.cantDatasUsadas()){
            if (x < N.getData(i))
                return i;
            
            if (x == N.getData(i))
                return -1;
            
            i++;
        }
        
        return N.cantDatasUsadas()+1;
    }
    
    private void print(NodoM N){
       for (int i=1; i<= N.cantDatasUsadas(); i++)
          System.out.print(" "+N.getData(i)); 
    }
    
    private boolean hoja(NodoM T){
        return (T !=null && T.cantHijos()==0);
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
        
        if (raiz == null)
            System.out.println("(Arbol vacío)");
        else
            niveles(raiz);
    }
//---------- Métodos auxiliares para mostrar el árbol por niveles --------------
    private void niveles(NodoM T){   //Pre: T no es null.
        LinkedList <NodoM> colaNodos  = new LinkedList<>();
        LinkedList<Integer> colaNivel = new LinkedList<>();
        
        int nivelActual = 0;
        String coma="";
        
        colaNodos.addLast(T);
        colaNivel.addLast(1);
        
        do{
            NodoM p = colaNodos.pop();      //Sacar un nodo p de la cola
            int nivelP = colaNivel.pop();   //Sacar el nivel donde se encuentra p.
            
            if (nivelP != nivelActual){ //Se está cambiando de nivel
                System.out.println();
                System.out.print("  Nivel "+nivelP+": ");
                nivelActual = nivelP;
                coma = "";
            }
            
            System.out.print(coma + p);
            coma = ", ";
            
            addHijos(colaNodos, colaNivel, p, nivelP);   
        }while (colaNodos.size() > 0);
        
        System.out.println();
    }
    
    private void addHijos(LinkedList <NodoM> colaNodos, LinkedList<Integer> colaNivel,  NodoM p, int nivelP){
        for (int i=1; i<=p.cantDatasUsadas()+1; i++){  //Insertar a la cola de nodos los hijos no-nulos de p
            NodoM hijo = p.getHijo(i);
            
            if (hijo != null){
                colaNodos.addLast(hijo);
                colaNivel.addLast(nivelP+1);
            }
        }
    }    
    /*----------------------------------------------*/
    private boolean lastParent(NodoM t,int p,int h){
        boolean sh;
        if (t==null){
            sh=false;
        }else{
            if (hoja(t)){
                sh=false;
            }else{
                if (t.existe(p)){
                    int i=hijoDesc(t, h);
                    if (i!=-1){
                        NodoM hijo=t.getHijo(i);
                        sh=hijo.existe(h);
                    }else{
                        sh=false;
                    }
                }else{
                    sh=lastParent(t.getHijo(hijoDesc(t, p)),p, h);
                }
            }
        }
        return sh;
    }
    public boolean lastParent(int p,int h){
        return lastParent(raiz, p, h);
    }
    private int sum(NodoM t){
        int sh;
        if (t==null){
            sh=0;
        }else{
            if (hoja(t)){
                sh=0;
            }else{
                sh=0;
                for (int i=1;i<=t.M;i++){
                    sh=sh+sum(t.getHijo(i));
                    if (hoja(t.getHijo(i))){
                        int sum=0;
                        for (int j=1;j<=t.getHijo(i).M-1;j++){
                            sum=sum+t.getHijo(i).getData(j);
                        }
                        sh=sh+sum*i;
                    }
                }
            }
        }
        return sh;
    }
    public int sum(){
        return sum(raiz);
    }
    private NodoM delHoja(NodoM t,int sum){
        if (t!=null){
            if (hoja(t)){
                int acum=0;
                for(int i=1;i<=t.M-1;i++){
                    acum=acum+t.getData(i);
                }
                if (acum==sum){
                    t=null;
                }
            }else{
                for (int i=1;i<=t.M;i++){
                    t.setHijo(i,delHoja(t.getHijo(i), sum));
                }
            }
        }
        return t;
    }
    public void delHoja(int sum){
        raiz=delHoja(raiz,sum);
    }
}


