package Preg2;

public class Lista {

    private Nodo L;

    public Lista() {     //Usted debe implementar éste método.
        L = null;
    }

    public void delInto(int a, int b) {  //** Preg2
        L = delIntoR(L, a, b);
    }

    private Nodo delIntoR(Nodo p, int a, int b) {
        if (p == null) 
            return p;
        else {    
            if (p.getData() == a) {
                if (p.getLink() != null && p.getLink().getLink() != null) {
                    if (p.getLink().getLink().getData() == b) {
                        p.setLink(p.getLink().getLink());
                        return p;
                    } else {
                        return p;
                    }
                }else{
                    return p;
                }
            } else {
                p.setLink(delIntoR(p.getLink(), a, b));
                return p;
            }
        }

    }

    public void add(int x) {   //Inserta x al principio de la Lista
        Nodo p = new Nodo(x);
        p.Link = L;
        L = p;
    }

    @Override
    public String toString() {   //Para usar en el System.out.println 
        String S = "[";
        String coma = "";

        Nodo p = L;
        while (p != null) {
            S += coma + p.getData();
            coma = ", ";
            p = p.getLink();
        }

        return S + "]";
    }
}
