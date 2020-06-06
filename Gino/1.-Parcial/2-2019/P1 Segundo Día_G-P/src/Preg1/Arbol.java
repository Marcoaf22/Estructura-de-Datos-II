package Preg1;

import java.util.LinkedList;

public class Arbol {

    private Nodo Raiz;
    private int n;

    public Arbol() {
        Raiz = null;
        n = 0;
    }

    public void cutLeafBunchs(int x) {   //** Preg 1
        Nodo p = Raiz;
        Nodo ant = null;
        while (p != null) {
            if (x>p.Data) {
                ant = p;
                p = p.getHD();
            } else if (x<p.Data) {
                ant = p;
                p = p.getHI();
            } else 
                if (hoja(p))
                    break;
                else
                    return;                           
        }
        if (ant!=null){
            if (p!=null){
                if (ant.getHD()==p){
                    if (hoja(ant.getHI())){
                        ant.HD=null;
                    }
                }else{
                    if (hoja(ant.getHD())){
                        ant.HI=null;
                    }
                }
            }
        }
    }

    public int cantNodos() {
        return n;
    }

    public void add(int x) {
        if (Raiz == null) {
            Raiz = new Nodo(x);
        } else {   //Caso general
            Nodo Ant = null;
            Nodo p = Raiz;

            while (p != null) {
                Ant = p;
                if (x < p.getData()) {
                    p = p.getHI();
                } else if (x > p.getData()) {
                    p = p.getHD();
                } else {
                    return;  //Salir. x ya está en el árbol.
                }
            }

            Nodo N = new Nodo(x);

            if (x < Ant.getData()) {
                Ant.setHI(N);
            } else {
                Ant.setHD(N);
            }
        }

        n++;
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

    private boolean hoja(Nodo T) {
        return (T != null && T.cantHijos() == 0);
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
