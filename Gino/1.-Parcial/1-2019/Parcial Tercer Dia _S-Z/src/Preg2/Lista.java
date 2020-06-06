package Preg2;

public class Lista {
    
    private Nodo L;     //Puntero principal de la Linked-List donde se almacenanan los pares.

    public Lista() {
        L = null;
    }

    //Funcion
    public void add(int data, int peso) {   //Usted debe implementar éste método.
        L = addR(L, data, peso);
    }
    
    private Nodo addR(Nodo p, int data, int peso) {
        if (p == null) {
            p = new Nodo(data, peso);
        } else {
            if (data <= p.Data) {
                if (data != p.Data) {
                    Nodo aux = new Nodo(data, peso);
                    aux.setLink(p);
                    p = aux;
                } else {
                    if (peso <= p.Peso) {
                        if (p.Peso != peso) {
                            Nodo aux = new Nodo(data, peso);
                            aux.setLink(p);
                            p = aux;
                        }
                    }
                }
            } else {
                p.setLink(addR(p.getLink(), data, peso));
            }
        }
        return p;
    }
    
    
    @Override
    public String toString() {   //Para usar en el System.out.println 
        String S = "[";
        String coma = "";
        
        Nodo p = L;
        while (p != null) {
            S += coma + p;
            coma = ", ";
            p = p.getLink();
        }
        
        return S + "]";
    }
    
}
