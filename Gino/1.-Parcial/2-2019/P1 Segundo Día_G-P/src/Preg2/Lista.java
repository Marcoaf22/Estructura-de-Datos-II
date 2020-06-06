package Preg2;

public class Lista {

    private Nodo L;

    public Lista() {     //Usted debe implementar éste método.
        L = null;
    }

    public void del(int a, int b) {  //** Preg2
        delR(L, a, b);
    }

    private void delR(Nodo p, int a, int b) {
        if (p != null) {
            if (L.Data == a && (L.getLink() != null && L.getLink().getData() == b)) {
                L = L.getLink().getLink();
            } else if (p.getLink()!=null && p.getLink().Data == a) {
                if (p.getLink().getLink()!=null && p.getLink().getLink().Data == b) {
                    p.setLink(p.getLink().getLink().getLink());
                }else
                delR(p.getLink(), a, b);
            }else{
                delR(p.getLink(),a,b);
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
