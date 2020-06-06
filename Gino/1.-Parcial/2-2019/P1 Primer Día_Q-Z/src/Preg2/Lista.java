package Preg2;

public class Lista {

    private static final int data[] = {3, 1, 6, 9, 8, 3, 7, 5, 6};
    private Nodo L;

    public Lista() {     //Usted debe implementar éste método.
        L = null;
        for (int i = 0; i < data.length; i++) {
            Nodo p = new Nodo(data[i]);
            p.Link = L;
            L = p;
        }
    }

    public void add(int nuevoNro, int nroBotar) {   //Usted debe implementar éste método.
        if (existe(nroBotar)) {
            if (cant(nuevoNro) == 2) {
                eliminar(L,nuevoNro);
                eliminar(L,nroBotar);
            }else{
                addR(L,nuevoNro,nroBotar);
            }
        }
    }

    private void addR(Nodo p, int nuevo, int borra) {
        if (p!=null){
           if (p.Data==borra){
               p.Data=nuevo;
           }else{
               addR(p.getLink(),nuevo,borra);
           }
        }
    }

    private void eliminar(Nodo p, int x) {
        if (p != null) {
            if (L.Data == x) {
                L = L.getLink();
                eliminar(p.getLink(),x);
            } else {
                if (p.getLink() != null && p.getLink().Data == x) {
                    p.setLink(p.getLink().getLink());
                    eliminar(p,x);
                }else
                eliminar(p.getLink(), x);
            }
        }
    }

    public int cant(int x) {
        int total = 0;
        Nodo p = L;
        while (p != null) {
            if (p.Data == x) {
                total++;
            }
            p = p.getLink();
        }
        return total;
    }

    public boolean existe(int x) {
        Nodo p = L;
        while (p != null) {
            if (p.Data == x) {
                return true;
            }
            p = p.getLink();
        }
        return false;
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
