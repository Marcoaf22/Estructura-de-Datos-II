package negocio;

import java.util.List;
import excepciones.ExcepcionOrdenInvalido;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ArbolMViasBusqueda<T extends Comparable<T>>
        implements IArbolBusqueda<T> {

    protected NodoMVias<T> raiz;
    protected int orden;

    /**
     * <strong>constructor de Oficio</strong><br>
     * por defecto hace que se construya un arbol de orden 3
     */
    public ArbolMViasBusqueda() {
        this.orden = 3;
    }

    /**
     * <strong>constructor parametrizado</strong><br>
     * construye un arbol con el orden deseado
     *
     * @param orden del arbol a construir
     * @throws ExcepcionOrdenInvalido si el orden es menor que 3<br>
     * se lanzara un caso de excepcion diciendo que el orden ingresado<br>
     * no es valido
     */
    public ArbolMViasBusqueda(int orden) throws ExcepcionOrdenInvalido {
        if (orden < 3) {
            throw new ExcepcionOrdenInvalido("El orden de su"
                    + " arbol deber ser mayor a tres");
        }
        this.orden = orden;

    }

    @Override
    public boolean insertar(T datoAInsertar) {
        if (this.esArbolVacio()) {
            this.raiz = new NodoMVias<>(this.orden, datoAInsertar);
            return true;
        }
        NodoMVias<T> nodoActual = this.raiz;
        while (!NodoMVias.esNodoVacio(nodoActual)) {
            if (this.existeDatoEnNodo(nodoActual, datoAInsertar)) {
                return false;
            }
            if (nodoActual.esHoja()) {
                if (nodoActual.estanDatosLlenos()) {
                    int posicionDeNuevoHijo
                            = this.porDondeBajar(nodoActual, datoAInsertar);
                    NodoMVias<T> nuevoNodo = new NodoMVias<>(this.orden, datoAInsertar);
                    nodoActual.setHijo(posicionDeNuevoHijo, nuevoNodo);
                } else {
                    this.insertarEnOrden(nodoActual, datoAInsertar);
                }
                nodoActual = NodoMVias.nodoVacio();
            } else {
                int posicion = this.porDondeBajar(nodoActual, datoAInsertar);
                //no hay hijo
                if (nodoActual.esHijoVacio(posicion)) {
                    NodoMVias<T> nuevoNodo = new NodoMVias<>(this.orden, datoAInsertar);
                    nodoActual.setHijo(posicion, nuevoNodo);
                    nodoActual = NodoMVias.nodoVacio();
                } else {
                    nodoActual = nodoActual.getHijo(posicion);
                }
            }
        }//fin While
        return false;
    }

    /**
     * recorre todos los datos del nodo, buscando si el dato existe o no
     *
     * @param nodoActual nodo a recorrer
     * @param datoABuscar dato que se quiere buscar
     * @return <strong>Verdadero:</strong> si el dato se encuentra en el
     * nodo<br>
     * Falso: si el dato no se encuentra en el nodo
     */
    protected boolean existeDatoEnNodo(NodoMVias<T> nodoActual, T datoABuscar) {
        int cantidadDeDatosNoVacios = nodoActual.cantidadDeDatosNoVacios();
        for (int i = 0; i < cantidadDeDatosNoVacios; i++) {
            if (nodoActual.getDato(i).compareTo(datoABuscar) == 0) { //funciona con ==
                return true;
            }
        }
        return false;
    }

    /**
     * recorre todos los datos del nodo buscando la posicion en la que<br>
     * deberia bajar<br>
     * <strong>Sirve para aquellos casos:</strong><br>
     * <ul>
     * <li type = "cicle">para saber en que hijo tengo que bajar</li>
     * <li type = "cicle">para saber cual es la posicion del datoAbuscar en el
     * nodo</li>
     * </ul>
     *
     * @param nodoActual nodo a recorrer
     * @param datoABuscar dato a buscar en el nodo
     * @return posicion en la que debemos bajar
     */
    protected int porDondeBajar(NodoMVias<T> nodoActual, T datoABuscar) {
        int cantidadDeDatos = nodoActual.cantidadDeDatosNoVacios();
        int i = 0;
        while (i < cantidadDeDatos) {
            if (nodoActual.getDato(i).compareTo(datoABuscar) >= 0) {
                return i;
            }
            i++;
        }
        return i;
    }

    /**
     * inserta el dato en el nodo haciendo que los datos sigan en orden
     * <strong>Importante:</strong><br>
     * se supone que hay espacio para que se inserte el dato
     *
     * @param nodoActual nodo en que se insertara el dato
     * @param datoAInsertar el dato a insertar
     */
    protected void insertarEnOrden(NodoMVias<T> nodoActual, T datoAInsertar) {
        try {
            int posicionAInsertar = this.porDondeBajar(nodoActual, datoAInsertar);//posicion real
            int cantidadDeDatos = nodoActual.cantidadDeDatosNoVacios(); //se convertira en posicion final con el -1
            for (int i = cantidadDeDatos - 1; i >= posicionAInsertar; i--) {
                nodoActual.setDato(i + 1, nodoActual.getDato(i));
                nodoActual.setHijo(i + 1, nodoActual.getHijo(i));
            }
            nodoActual.setDato(posicionAInsertar, datoAInsertar); //insertamos el dato (puede provocar un error)
            nodoActual.setHijo(posicionAInsertar, NodoMVias.nodoVacio()); //con su hijo vacio
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public boolean eliminar(T dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T buscar(T datoABuscar) {
        NodoMVias<T> nodoActual = this.raiz;
        while (!NodoMVias.esNodoVacio(nodoActual)) {
            NodoMVias<T> nodoAnterior = nodoActual;
            for (int i = 0; i < nodoActual.cantidadDeDatosNoVacios()
                    && nodoAnterior == nodoActual; i++) {
                T datoActual = nodoActual.getDato(i);
                if (datoABuscar.compareTo(datoActual) == 0) {
                    return datoActual;
                }
                if (datoABuscar.compareTo(datoActual) < 0) {
                    if (nodoActual.esHijoVacio(i)) {
                        return (T) NodoMVias.datoVacio();
                    }
                    //caso contrario
                    //el hijo no es vacio
                    nodoActual = nodoActual.getHijo(i);
                }
            }//fin for
            if (nodoAnterior == nodoActual) {
                nodoActual = nodoActual.getHijo(orden - 1); //pasamos al ultimo hijo
            }
        }
        return (T) NodoMVias.datoVacio();
    }

    @Override
    public boolean contiene(T dato) {
        return this.buscar(dato) != NodoMVias.datoVacio();
    }

    /**
     * recorrido inOrden
     *
     * @return una lista con el recorrido correspondiente al procedimiento
     */
    @Override
    public List<T> recorridoEnInOrden() {
        List<T> recorrido = new ArrayList<>();
        this.recorridoEnInOrden(this.raiz, recorrido);
        return recorrido;
    }

    private void recorridoEnInOrden(NodoMVias<T> nodoActual, List<T> recorrido) {
        //simulamos n = 0
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return;
        }
        for (int i = 0; i < nodoActual.cantidadDeDatosNoVacios(); i++) {
            recorridoEnInOrden(nodoActual.getHijo(i), recorrido);
            recorrido.add(nodoActual.getDato(i));
        }
        recorridoEnInOrden(nodoActual.getHijo(this.orden - 1), recorrido);
    }

    /**
     * recorrido En PreOrden
     *
     * @return una lista con el recorrido correspondiente al procedimiento
     */
    @Override
    public List<T> recorridoEnPreOrden() {
        List<T> recorrido = new ArrayList<>();
        this.recorridoEnPreOrden(this.raiz, recorrido);
        return recorrido;
    }

    private void recorridoEnPreOrden(NodoMVias<T> nodoActual, List<T> recorrido) {
        //simulamos n = 0
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return;
        }
        for (int i = 0; i < nodoActual.cantidadDeDatosNoVacios(); i++) {
            recorrido.add(nodoActual.getDato(i));
            recorridoEnPreOrden(nodoActual.getHijo(i), recorrido);
        }
        recorridoEnPreOrden(nodoActual.getHijo(this.orden - 1), recorrido);
    }

    /**
     * recorrido en PostOrden
     *
     * @return una lista con el recorrido correspondiente al procedimiento
     */
    @Override
    public List<T> recorridoEnPostOrden() {
        List<T> recorrido = new ArrayList<>();
        this.recorridoEnPostOrden(this.raiz, recorrido);
        return recorrido;
    }

    private void recorridoEnPostOrden(NodoMVias<T> nodoActual, List<T> recorrido) {
        //simulamos n = 0
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return;
        }
        recorridoEnPostOrden(nodoActual.getHijo(0), recorrido);
        for (int i = 0; i < nodoActual.cantidadDeDatosNoVacios(); i++) {
            recorridoEnPostOrden(nodoActual.getHijo(i + 1), recorrido);
            recorrido.add(nodoActual.getDato(i));
        }
    }

    /**
     * recorrido PorNiveles
     *
     * @return una lista con los datos del arbol
     */
    @Override
    public List<T> recorridoPorNiveles() { //En InOrden
        List<T> recorrido = new ArrayList<>();
        if (this.esArbolVacio()) {
            return recorrido;
        }

        Queue<NodoMVias<T>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        while (!colaDeNodos.isEmpty()) {
            NodoMVias<T> nodoActual = colaDeNodos.poll();
            for (int i = 0; i < nodoActual.cantidadDeDatosNoVacios(); i++) {
                recorrido.add(nodoActual.getDato(i));
                if (!nodoActual.esHijoVacio(i)) {
                    colaDeNodos.offer(nodoActual.getHijo(i));
                }
            }
            if (!nodoActual.esHijoVacio(orden - 1)) {
                colaDeNodos.offer(nodoActual.getHijo(orden - 1));
            }
        }
        return recorrido;
    }

    /**
     * proporciona la cantidad de nodos que contiene el arbolMVias
     *
     * @return el numero de nodos;
     */
    @Override
    public int size() {
        return this.size(this.raiz);
    }

    private int size(NodoMVias<T> nodoActual) {
        //sumulamos n = 0
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return 0;
        }
        int cantidad = 0;
        for (int i = 0; i < this.orden; i++) {
            cantidad += size(nodoActual.getHijo(i));
        }
        cantidad += 1;
        return cantidad;
    }

    /**
     * proporciona al altura del arbol
     *
     * @return altura total
     */
    @Override
    public int altura() {
        return this.altura(this.raiz);
    }

    private int altura(NodoMVias<T> nodoActual) {
        //simulamos n = 0
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return 0;
        }
        int alturaMayor = 0;
        for (int i = 0; i < orden; i++) {
            int alturaDelHijo = altura(nodoActual.getHijo(i));
            if (alturaDelHijo > alturaMayor) {
                alturaMayor = alturaDelHijo;
            }
        }
        return alturaMayor + 1;
    }

    /**
     * elimina todos los nodos del arbol
     */
    @Override
    public void vaciar() {
        this.raiz = NodoMVias.nodoVacio();
    }

    /**
     * verifica si el arbol es vacio o no.
     *
     * @return <strong>Verdadero:</strong> si el arbol esta vacio<br>
     * <strong>Falso:</strong> si el arbol tiene minimo un nodo
     */
    @Override
    public boolean esArbolVacio() {
        return NodoMVias.esNodoVacio(this.raiz);
    }

    /**
     * proporciona el nivel total de un arbol
     *
     * @return
     */
    @Override
    public int nivel() {
        return this.altura() - 1;
    }

    /**
     * cuenta la cantidad total de hojas que contiene el arbolMVias
     *
     * @return la cantidad de hojas
     */
    public int numeroTotalDeHojas() {
        if (this.esArbolVacio()) {
            return 0;
        }

        Queue<NodoMVias<T>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        int cantidadDeHojas = 0;
        while (!colaDeNodos.isEmpty()) {
            NodoMVias<T> nodoActual = colaDeNodos.poll();
            if (nodoActual.esHoja()) {
                cantidadDeHojas++;
            } else {
                for (int i = 0; i < this.orden; i++) {
                    if (!nodoActual.esHijoVacio(i)) {
                        colaDeNodos.offer(nodoActual.getHijo(i));
                    }
                }
            }
        }
        return cantidadDeHojas;
    }

    //Hacer un metodo que cuente el numero de Hojas a partir de un cierto nivel
    public int numeroTotalDeHojasAPartirDeUnNivel(int nivelDesdeDondeContar) {
        if (this.esArbolVacio()) {
            return 0;
        }
        int NivelActual = 0;
        int cantidadDeHojas = 0;
        Queue<NodoMVias<T>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        while (!colaDeNodos.isEmpty()) {
            int nroDeNodosDelNivel = colaDeNodos.size();
            while (nroDeNodosDelNivel > 0) {
                NodoMVias<T> nodoActual = colaDeNodos.poll();
                if (nodoActual.esHoja()) {
                    if (NivelActual >= nivelDesdeDondeContar) {
                        cantidadDeHojas++;
                    }
                } else {
                    for (int i = 0; i < this.orden; i++) {
                        if (!nodoActual.esHijoVacio(i)) {
                            colaDeNodos.offer(nodoActual.getHijo(i));
                        }
                    }
                }
                nroDeNodosDelNivel--;
            }
            NivelActual++;
        }
        return cantidadDeHojas;
    }

    @Override
    public String toString() {
        return this.recorridoEnInOrden().toString();
    }

    protected void mostrarArbolMVias() {
        LinkedList<NodoMVias> colaNodos = new LinkedList<>();
        LinkedList<Integer> colaNivel = new LinkedList<>();
        int nivelActual = 0;
        colaNodos.addLast(raiz);
        colaNivel.addLast(1);
        int x = altura();
        int nroHijo = 1;
        int nroPadre = 1;
        String inicio="";
        while (colaNivel.peek()<=x) {
            NodoMVias p = colaNodos.pop();       //Sacar nodo de la cola
            int nivelP = colaNivel.pop();
            if (nivelP != nivelActual) { //Se está cambiando de nivel
                System.out.println();
                System.out.print("Nivel " + nivelP + ": ");
                nivelActual = nivelP;
                nroPadre = 1;
                nroHijo = 1;
                inicio=nVeces(x-nivelActual);
            }

            if (p != null) {
                System.out.print(nroPadre + "." + nroHijo + p.getListaDeDatos().toString() + " ");
                addHijos(colaNodos, colaNivel, p, nivelP);
            } 
            if (nroHijo == orden) {
                nroPadre++;
                nroHijo = 1;
            } else {
                nroHijo++;
            }
        }
        System.out.println("");
    }

    private String nVeces(int x) {
        String veces = "";
        int i = 0;
        while (i < x) {
            veces = veces + "\t";
            i++;
        }
        return veces;
    }

    private void addHijos(LinkedList<NodoMVias> colaNodos, LinkedList<Integer> colaNivel, NodoMVias p, int nivelP) {
        for (int i = 0; i < orden; i++) {  //Insertar a la cola de nodos los hijos no-nulos de p
            NodoMVias hijo = p.getHijo(i);
            colaNodos.addLast(hijo);
            colaNivel.addLast(nivelP + 1);
        }
    }

    public static void main(String[] args) {
        ArbolMViasBusqueda a = new ArbolMViasBusqueda();
        a.insertar(10);
        a.insertar(20);
        a.insertar(15);

        a.mostrarArbolMVias();
        System.out.println(a.toString());
    }



}
