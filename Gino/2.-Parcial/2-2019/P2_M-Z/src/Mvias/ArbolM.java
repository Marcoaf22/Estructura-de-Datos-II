package mvias;

import java.util.LinkedList;

public class ArbolM {

    private NodoM raiz;
    private int n;          //n=cantidad de nodos.

    public ArbolM() {
        raiz = null;
        n = 0;
    }

    public int costof(int x) {    //PREGUNTA 1
        return costof(raiz, x);
    }

    private int costof(NodoM p, int x) {
        if (p == null) {
            return -1;
        }
        if (p.cantHijos() == 0) {
            if (p.existe(x)) {
                return p.getPeso();
            }
            return -1;
        }
        for (int i = 1; i <= p.cantDatasUsadas() + 1; i++) {
            int costo = costof(p.getHijo(i), x);
            if (costo != -1) {
                return costo + p.getPeso();
            }
        }
        return -1;
    }

    private int costrrf(NodoM T, int x) {
        int total = 0;
        if (T == null) {
            return 0;
        } else {
            for (int i = 1; i <= T.M; i++) {
                if (T.getHijo(i) != null) {
                    if (T.getHijo(i).existe(x)) {
                        return T.getHijo(i).getPeso();
                    }
                } else {
                    int aux = costo(T.getHijo(i), x);
                    total = total + aux;
                    return total + T.getHijo(i).getPeso();
                }
            }
        }

        return 0;

    }

    public int costo(int x) {   //PREGUNTA 1
        return costo(raiz, x);
    }

    private int costo(NodoM T, int x) {
        if (T.existe(x)) {
            return 0;
        } else {

            int h = hijoDesc(T, x);
            int p = T.getHijo(h).getPeso();

            if (T.getHijo(h).existe(x)) {
                return p;
            } else {
                int aux = costo(T.getHijo(h), x);
                return aux + T.getHijo(h).getPeso();
            }
        }

    }

    public int getCantNodos() {
        return n;
    }

    public void insertar(int x, int peso) {
        if (raiz == null) {
            raiz = new NodoM(x);
            raiz.setPeso(0);
        } else {
            int i = 0;
            NodoM ant = null, p = raiz;
            while (p != null) {
                if (!p.isLleno()) {  //Hay espacio en el nodo p. Insertar x ahí.
                    p.insDataInOrden(x);
                    return;
                }

                i = hijoDesc(p, x);
                if (i == -1) {
                    return;     //Salir. x está en el nodo p (El árbol no acepta duplicados)
                }
                ant = p;
                p = p.getHijo(i);
            }

            //p llegó a null.  Crear una nueva hoja y engancharla a ant.
            NodoM nuevo = new NodoM(x);
            nuevo.setPeso(peso);
            ant.setHijo(i, nuevo);
        }

        n++;
    }

    public void inorden() {
        System.out.print("Inorden:");
        if (raiz == null) {
            System.out.println(" (Árbol vacío)");
        } else {
            inorden(raiz);
            System.out.println();
        }

    }

    private void inorden(NodoM T) {
        if (T != null) {
            int z = T.cantDatasUsadas();      //z = índice de la última data usada.
            for (int i = 1; i <= z; i++) {
                inorden(T.getHijo(i));
                System.out.print(" " + T.getData(i));
            }
            inorden(T.getHijo(z + 1));
        }
    }

    private int hijoDesc(NodoM N, int x) {   //Corrutina de insertar.
        int i = 1;
        while (i <= N.cantDatasUsadas()) {
            if (x < N.getData(i)) {
                return i;
            }

            if (x == N.getData(i)) {
                return -1;
            }

            i++;
        }

        return N.cantDatasUsadas() + 1;
    }

    private void print(NodoM N) {
        for (int i = 1; i <= N.cantDatasUsadas(); i++) {
            System.out.print(" " + N.getData(i));
        }
    }

    private boolean hoja(NodoM T) {
        return (T != null && T.cantHijos() == 0);
    }

    public void niveles() {
        niveles("");
    }

    public void niveles(String nombreVar) {
        if (nombreVar != null && nombreVar.length() > 0) {
            nombreVar = " del Arbol " + nombreVar;
        } else {
            nombreVar = "";
        }

        System.out.print("Niveles" + nombreVar + ": ");

        if (raiz == null) {
            System.out.println("(Arbol vacío)");
        } else {
            niveles(raiz);
        }
    }
//---------- Métodos auxiliares para mostrar el árbol por niveles --------------

    private void niveles(NodoM T) {   //Pre: T no es null.
        LinkedList<NodoM> colaNodos = new LinkedList<>();
        LinkedList<Integer> colaNivel = new LinkedList<>();

        int nivelActual = 0;
        String coma = "";

        colaNodos.addLast(T);
        colaNivel.addLast(1);

        do {
            NodoM p = colaNodos.pop();      //Sacar un nodo p de la cola
            int nivelP = colaNivel.pop();   //Sacar el nivel donde se encuentra p.

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

    private void addHijos(LinkedList<NodoM> colaNodos, LinkedList<Integer> colaNivel, NodoM p, int nivelP) {
        for (int i = 1; i <= p.cantDatasUsadas() + 1; i++) {  //Insertar a la cola de nodos los hijos no-nulos de p
            NodoM hijo = p.getHijo(i);

            if (hijo != null) {
                colaNodos.addLast(hijo);
                colaNivel.addLast(nivelP + 1);
            }
        }
    }

    public int sumPeso(int x) {
        return sumPeso(raiz, x, 0);
    }

    private int sumPeso(NodoM T, int x, int sum) {
        if (T.existe(x)) {
            return 0;
        } else {
            int h = hijoDesc(T, x);
            int p = T.getHijo(h).getPeso();
            sum = sum + sumaDatos(T.getHijo(h), p);
            if (T.getHijo(h).existe(x)) {
                return sum;

            }
            int aux = sumPeso(T.getHijo(h), x, sum);
            return aux;
        }
    }

    private int sumaDatos(NodoM T, int p) {
        int s = 0;
        for (int i = 1; i < T.M; i++) {
            s = s + T.getData(i);
        }
        return s * p;
    }

}
