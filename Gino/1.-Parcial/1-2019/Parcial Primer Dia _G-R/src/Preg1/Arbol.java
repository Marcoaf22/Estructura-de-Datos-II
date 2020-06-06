package Preg1;

import java.util.LinkedList;

public class Arbol {

    private Nodo Raiz;

    public Arbol(int padre) {        //Crea el árbol con raíz=padre
        Raiz = new Nodo(padre);
    }

    public void delCuasiHoja(int x) {  //** Preg 1.
        delCuasiHoja(Raiz, x);
    }

    private void delCuasiHoja(Nodo p, int x) {
        if (p != null) {
            if (p.Data == x) {
                if (p.getHI() != null || p.getHD() != null) {
                    if (p.getHD() != null && hoja(p.getHD())) {
                        p.Data = p.getHD().Data;
                        p.setHD(null);
                        return;
                    }
                    if (hoja(p.getHI())){
                        p.setData(p.getHI().getData());
                        p.setHI(null);
                        return;
                    }
                }
                return;
            }
            delCuasiHoja(p.getHD(), x);

        }
    }
    
    public void delCuasiHojaF(int x){
        Raiz=delCuasiHojaF(Raiz,x);
    }
    
    private Nodo delCuasiHojaF(Nodo p,int x){
        if (p!=null){
            if (p.getData()==x){
                if (p.getHD()!=null || p.getHI()!=null){
                    if (p.getHD()!=null && hoja(p.getHD())){
                        return p.getHD();
                    }
                    return p.getHI();
                }
            }else{
                p.setHD(delCuasiHojaF(p.getHD(),x));
                p.setHI(delCuasiHojaF(p.getHI(),x));
            }
        }
        return p;
    }

    private boolean hoja(Nodo T) {
        return (T != null && T.cantHijos() == 0);
    }

    public void add(int padre, int x) { //Inserta x al arbol, como hijo de padre. 
        Nodo p = fetch(Raiz, padre);
        if (p == null || fetch(Raiz, x) != null) {
            return;     //El padre no existe o x ya está en el árbol.
        }
        int i = p.getHijoNull();
        if (i != -1) {
            p.setHijo(i, new Nodo(x));
        }
    }

    private Nodo fetch(Nodo t, int x) {  //Fetch al nodo cuyo Data=x.
        if (t == null || t.getData() == x) {
            return t;
        }

        Nodo hi = fetch(t.getHI(), x);
        if (hi != null) {
            return hi;
        }

        return fetch(t.getHD(), x);
    }

    public void Inorden() {
        if (Raiz == null) {
            System.out.println("(Arbol vacío)");
        } else {
            System.out.print("Inorden : ");
            Inorden(Raiz);
            System.out.println();
        }
    }

    private void Inorden(Nodo T) {
        if (T != null) {
            Inorden(T.getHI());
            System.out.print(T.getData() + " ");
            Inorden(T.getHD());
        }
    }

    public void Preorden() {
        System.out.print("Preorden : ");

        if (Raiz == null) {
            System.out.println("(Arbol vacío)");
        } else {
            Preorden(Raiz);
            System.out.println();
        }
    }

    private void Preorden(Nodo T) {
        if (T != null) {
            System.out.print(T.getData() + " ");
            Preorden(T.getHI());
            Preorden(T.getHD());
        }
    }

    public void niveles() {
        System.out.print("Niveles: ");

        if (Raiz == null) {
            System.out.println("(Arbol vacío)");
        } else {
            niveles(Raiz);
        }
    }

//---------- Métodos auxiliares para mostrar el árbol por niveles --------------
    private void niveles(Nodo T) {   //Pre: T no es null.
        LinkedList<Nodo> colaNodos = new LinkedList<>();
        LinkedList<Integer> colaNivel = new LinkedList<>();

        int nivelActual = 0;
        String coma = "";

        colaNodos.addLast(T);
        colaNivel.addLast(1);

        do {
            Nodo p = colaNodos.pop();       //Sacar nodo de la cola
            int nivelP = colaNivel.pop();

            if (nivelP != nivelActual) { //Se está cambiando de nivel
                System.out.println();
                System.out.print("  Nivel " + nivelP + ": ");
                nivelActual = nivelP;
                coma = "";
            }

            System.out.print(coma + p);
            coma = ", ";

            addHijos(colaNodos, colaNivel, p, nivelP);
        } while (colaNodos.size() > 0);

        System.out.println();
    }

    private void addHijos(LinkedList<Nodo> colaNodos, LinkedList<Integer> colaNivel, Nodo p, int nivelP) {
        for (int i = 1; i <= Nodo.M; i++) {  //Insertar a la cola de nodos los hijos no-nulos de p
            Nodo hijo = p.getHijo(i);

            if (hijo != null) {
                colaNodos.addLast(hijo);
                colaNivel.addLast(nivelP + 1);
            }
        }
    }

}
