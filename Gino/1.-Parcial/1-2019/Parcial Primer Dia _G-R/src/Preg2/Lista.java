package Preg2;

public class Lista {    //ADT Lista (Ordenada y sin duplicados).
    private Nodo L;
    private int n;
    
    public Lista(){
        L = null;
        n = 0;
    }
    
    public boolean isVacia() {  //Devuelve true sii esta lista está vacía.
        return (L==null);
    }
    
    public void add(int x){ //Inserta x a la Lista.
        Nodo Ant = null;
        Nodo p   = L;
        
        while (p != null && x >= p.getData()){
            Ant = p;
            p = p.getLink();
        }
        
        Nodo nuevo;
        if (Ant == null){   //x es menor a todos los que están en la Lista (o L==null)
            nuevo = new Nodo(x);
            nuevo.setLink(L);
            L = nuevo;
            n++;
        }
        else
            if (Ant.getData() != x){    //x no está en la lista.  Insertarlo entre Ant y p
                nuevo = new Nodo(x);
                Ant.setLink(nuevo);
                nuevo.setLink(p);
                n++;
            }
    }
    
    public int suma(){
        Nodo p=L;
        int x=0;
        while (p!=null){
            x=x+p.getData();
            p=p.getLink();
        }
        return x;
    }
    
    public void eliminar(int x){
        Nodo Ant = null;
        Nodo p   = L;
        
        while (p != null && x > p.getData()){
            Ant = p;
            p = p.getLink();
        }
        
        if (p != null && p.getData() == x){  //x existe en la Lista 
            if (Ant == null)
                L = L.getLink();    //x era el primero de la Lista
            else
                Ant.setLink(p.getLink());
            
            p.setLink(null);
            n--;    
        }
    }
    
    
    public boolean existe(int x){
        Nodo p = L;
        
        while (p != null && x > p.getData()){
            p = p.getLink();
        }
        
        return (p != null && p.getData() == x);
    }

    
    /**Devuelve el elemento de la posición k de la Lista. Las posiciones se 
     * enumeran desde el 0. <br/><br/>
     * Por ejemplo, si la lista es [4, 7, 9], entonces: <br/>
     * <blockquote>get(0)=4, get(1)=7 y get(2)=9</blockquote>
     * 
     * @param k Posición del elemento a obtener (k debe ser un valor entre 0 y length()-1)
     * @return 
     */
    public int get(int k){
        Nodo p=L;
        int i=0;
        while (p != null){
            if (i==k)
                return p.getData();
            
            p = p.getLink();
            i++;
        }
        
            //La posición k no existe
        String s = (isVacia() ? "la Lista está vacía" : "las posiciones válidas van de 0 a "+(length()-1));
        System.err.println("Lista.get: La posicion "+k+" está fuera de rango ("+ s +").");
        return -1;
    }
    
    public int length(){
        return n;
    }
    
    
    @Override
    public String toString(){ 
        String S = "[";
        String coma="";
       
        Nodo p  = L;
        while (p != null){
            S += coma + p.getData();
            coma=", ";
            p = p.getLink();
        }
       
       return S+"]";
    }
    
}
