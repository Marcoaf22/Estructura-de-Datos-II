package negocio;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class ArbolBinarioBusqueda<T extends Comparable<T>>
        implements IArbolBusqueda<T> {

    protected NodoBinario<T> raiz;

    public ArbolBinarioBusqueda() {
    }

    public ArbolBinarioBusqueda(T dato) {
        insertar(dato);
    }

    /**
     * <strong>constructor de Oficio</strong><br>
     * por defecto hace que se construya un arbol de orden 3
     *
     * recibe dos recorridos en profundidad, uno de ellos si o si es el inOrden
     * y el otro puede ser el recorrido preOrden o PostOrden, para recostruir el
     * arbol binario de busqueda.
     * <strong>preCondicion</strong> : las listas que tienen los recorridos
     * deben tener el mismo tamaño y deben ser correctos
     *
     * @param recorridoInOrden es el recorrido inOrden del arbol a reconstruir
     * @param recorridoPreOPosOrden es el recorrido en preOrden o postOrden del
     * arbol a recostruir
     * @param esConPostOrden <strong>Verdadero</strong> : indica que el segundo
     * parametro es el recorrido postOrden.<br>
     * <strong>Falso</strong> : el segundo parametro es el preOrden
     */
    public ArbolBinarioBusqueda(List<T> recorridoInOrden,
            List<T> recorridoPreOPosOrden, boolean esConPostOrden) {
        if (esConPostOrden) {
            this.raiz = reconstruirConPostOrden(recorridoInOrden,
                    recorridoPreOPosOrden);
        } else {
            this.raiz = reconstruirConPreOrden(recorridoInOrden,
                    recorridoPreOPosOrden);
        }
    }

    private NodoBinario<T> reconstruirConPostOrden(List<T> recorridoInOrden,
            List<T> recorridoPosOrden) {
        if (recorridoInOrden.isEmpty()) {
            return NodoBinario.nodoVacio();
        }
        int posicionDelDatoActualEnPostOrden = recorridoPosOrden.size() - 1; //el dato actual esta en la ultima posicion
        T datoDelNodoActual = recorridoPosOrden.get(posicionDelDatoActualEnPostOrden);
        int posicionDelDatoActualEnInOrden = buscarPosicionDeDatoEnRecorrido(
                recorridoInOrden, datoDelNodoActual);//hacer con el compareTo
        List<T> postOrdenParaLaIzquierda = recorridoPosOrden.subList(
                0, posicionDelDatoActualEnInOrden);
        List<T> inOrdenParaLaIzquierda = recorridoInOrden.subList(
                0, posicionDelDatoActualEnInOrden);
        NodoBinario<T> hijoIzquierdo = reconstruirConPostOrden(
                inOrdenParaLaIzquierda, postOrdenParaLaIzquierda);

        List<T> postOrdenParaLaDerecha = recorridoPosOrden.subList(
                posicionDelDatoActualEnInOrden, recorridoPosOrden.size() - 1);
        List<T> inOrdenParaLaDerecha = recorridoInOrden.subList(
                posicionDelDatoActualEnInOrden + 1, recorridoInOrden.size());
        NodoBinario<T> hijoDerecho = reconstruirConPostOrden(
                inOrdenParaLaDerecha, postOrdenParaLaDerecha);
        NodoBinario<T> nodoActual = new NodoBinario<>(datoDelNodoActual);
        nodoActual.setHijoIzquierdo(hijoIzquierdo);
        nodoActual.setHijoDerecho(hijoDerecho);
        return nodoActual;
    }

    private NodoBinario<T> reconstruirConPreOrden(List<T> recorridoInOrden,
            List<T> recorridoPreOrden) {
        if (recorridoInOrden.isEmpty()) {
            return NodoBinario.nodoVacio();
        }
        int posicionDelDatoActualEnPreOrden = 0; //el dato actual esta en la posicion 1(porque es preorden)
        T datoDelNodoActual = recorridoPreOrden.get(posicionDelDatoActualEnPreOrden);
        int posicionDelDatoActualEnInOrden = buscarPosicionDeDatoEnRecorrido(
                recorridoInOrden, datoDelNodoActual);
        List<T> preOrdenParaLaIzquierda = recorridoPreOrden.subList(
                posicionDelDatoActualEnPreOrden + 1, posicionDelDatoActualEnInOrden + 1);
        List<T> inOrdenParaLaIzquierda = recorridoInOrden.subList(
                posicionDelDatoActualEnPreOrden, posicionDelDatoActualEnInOrden);
        NodoBinario<T> hijoIzquierdo = reconstruirConPreOrden(
                inOrdenParaLaIzquierda, preOrdenParaLaIzquierda);

        List<T> preOrdenParaLaDerecha = recorridoPreOrden.subList(
                posicionDelDatoActualEnInOrden + 1, recorridoPreOrden.size());
        List<T> inOrdenParaLaDerecha = recorridoInOrden.subList(
                posicionDelDatoActualEnInOrden + 1, recorridoInOrden.size());
        NodoBinario<T> hijoDerecho = reconstruirConPreOrden(
                inOrdenParaLaDerecha, preOrdenParaLaDerecha);

        NodoBinario<T> nodoActual = new NodoBinario<>(datoDelNodoActual);
        nodoActual.setHijoIzquierdo(hijoIzquierdo);
        nodoActual.setHijoDerecho(hijoDerecho);
        return nodoActual;
    }

    /**
     * proporciona la posicion del dato que se envia por parametro
     * <strong>importante: </strong> por defecto retorna 0, en caso que lo
     * encuentre retorna otra posicion
     *
     * @param recorridoInOrden lista inOrden
     * @param datoDelNodoActual dato a buscar en la lista
     * @return posicion del dato en la lista
     */
    private int buscarPosicionDeDatoEnRecorrido(List<T> recorridoInOrden, T datoDelNodoActual) {
        int posicionDelDatoEnelRecorridoInOrden = 0;
        int i = 0;
        while ((i < recorridoInOrden.size()) && (posicionDelDatoEnelRecorridoInOrden == 0)) {
            T datoSacadoDeLaLista = recorridoInOrden.get(i);
            if (datoSacadoDeLaLista.compareTo(datoDelNodoActual) == 0) {
                posicionDelDatoEnelRecorridoInOrden = i;
            }
            i++;
        }
        return posicionDelDatoEnelRecorridoInOrden;
    }

    /**
     * Funcion que cuenta la cantidad de nodos que tienen ambos de sus hijos en
     * el arbolBinarioDeBusqueda de forma Recursiva
     *
     * @return  <Strong>int</Strong> :cantidad de nodos con ambos hijos
     */
    public int AmbosHijosR() { //Hecho por mi
        return AmbosHijosR(raiz);
    }

    private int AmbosHijosR(NodoBinario<T> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int ambos = AmbosHijosR(nodoActual.getHijoIzquierdo());
        ambos += AmbosHijosR(nodoActual.getHijoDerecho());
        if (!NodoBinario.esNodoVacio(nodoActual.getHijoIzquierdo())
                && !NodoBinario.esNodoVacio(nodoActual.getHijoDerecho())) {
            ambos++;
        }
        return ambos;
    }

    /**
     * Funcion que cuenta la cantidad de nodos que tienen 2 hijos en el
     * ArbolBinarioDeBusqueda
     *
     * @return La cantidad de Nodos que tiene 2 Hijos
     */
    public int AmbosHijos() { //Hecho por mi
        if (this.esArbolVacio()) {
            return 0; //Arbol vacio
        }
        int ambos = 0;
        Queue<NodoBinario<T>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(raiz);
        while (!colaDeNodos.isEmpty()) {
            NodoBinario<T> nodoActual = colaDeNodos.poll();
            if (!NodoBinario.esNodoVacio(nodoActual.getHijoIzquierdo())
                    && !NodoBinario.esNodoVacio(nodoActual.getHijoDerecho())) {
                ambos++;
            }
            if (!nodoActual.esHijoIzquiedoVacio()) {
                colaDeNodos.offer(nodoActual.getHijoIzquierdo());
            }
            if (!nodoActual.esHijoDerechoVacio()) {
                colaDeNodos.offer(nodoActual.getHijoDerecho());
            }
        }
        return ambos;
    }

    @Override
    public boolean insertar(T datoAInsertar) {
        if (this.esArbolVacio()) {
            this.raiz = new NodoBinario<>(datoAInsertar);
            return true; //El Arbol esta vacio
        }

        NodoBinario<T> nodoAnterior = NodoBinario.nodoVacio();
        NodoBinario<T> nodoActual = this.raiz;

        while (!NodoBinario.esNodoVacio(nodoActual)) {
            T datoActual = nodoActual.getDato();
            if (datoAInsertar.compareTo(datoActual) == 0) {
                return false; //el dato ya existe
            }
            if (datoAInsertar.compareTo(datoActual) < 0) {
                nodoAnterior = nodoActual;
                nodoActual = nodoActual.getHijoIzquierdo();
            } else {
                nodoAnterior = nodoActual;
                nodoActual = nodoActual.getHijoDerecho();
            }
        }
        NodoBinario<T> nuevoNodo = new NodoBinario<>(datoAInsertar);
        T datoDelPadre = nodoAnterior.getDato();
        if (datoAInsertar.compareTo(datoDelPadre) < 0) {
            nodoAnterior.setHijoIzquierdo(nuevoNodo);
        } else {
            nodoAnterior.setHijoDerecho(nuevoNodo);
        }
        return true; //lo inserta
    }

    /**
     * elimina el dato ingresado por parametro
     *
     * @param dato a eliminar
     * @return true: el dato fue eliminado del arbol false: el dato no se
     * encuentra en el arbol
     */
    @Override
    public boolean eliminar(T dato) {
        try {
            this.raiz = eliminar(this.raiz, dato);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private NodoBinario<T> eliminar(NodoBinario<T> nodoActual,
            T datoAEliminar) throws Exception {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            throw new Exception(); //el dato no esta en el arbol
        }
        T datoActual = nodoActual.getDato();
        if (datoAEliminar.compareTo(datoActual) < 0) {
            NodoBinario<T> supuestoHijoIzquierdo
                    = eliminar(nodoActual.getHijoIzquierdo(), datoAEliminar);
            nodoActual.setHijoIzquierdo(supuestoHijoIzquierdo);
            return nodoActual;
        }
        if (datoAEliminar.compareTo(datoActual) > 0) {
            NodoBinario<T> supuestoHijoDerecho
                    = eliminar(nodoActual.getHijoDerecho(), datoAEliminar);
            nodoActual.setHijoDerecho(supuestoHijoDerecho);
            return nodoActual;
        }

        //caso1
        if (nodoActual.esHoja()) {
            return NodoBinario.nodoVacio();
        }

        //caso2
        if (!nodoActual.esHijoIzquiedoVacio()
                && nodoActual.esHijoDerechoVacio()) {
            return nodoActual.getHijoIzquierdo();
        }
        if (nodoActual.esHijoIzquiedoVacio()
                && !nodoActual.esHijoDerechoVacio()) {
            return nodoActual.getHijoDerecho();
        }

        //caso 3
        T datoSucesor = buscarSucesorInOrden(nodoActual.getHijoDerecho()); //busca el dato del sucesor
        NodoBinario<T> supuestoNuevoHijoDerecho
                = eliminar(nodoActual.getHijoDerecho(), datoSucesor); //caera en caso 1 o 2
        nodoActual.setHijoDerecho(supuestoNuevoHijoDerecho);
        nodoActual.setDato(datoSucesor);
        return nodoActual;

    }

    /**
     * Recibe el nodo derecho del eliminar con el caso 3, lo que tiene que hacer
     * es ir todo a la izquierda y retornar (el dato del nodo a eliminar) Si o
     * si caera en un caso 1 o 2.
     *
     * @param hijoDerecho Nodo derecho del caso 3 (pero luego cambiara a los
     * siguientes del esa rama)
     * @return Dato que lo va a reemplazar
     */
    protected T buscarSucesorInOrden(NodoBinario<T> hijoDerecho) {
        if (NodoBinario.esNodoVacio(hijoDerecho.getHijoIzquierdo())) {
            return hijoDerecho.getDato(); //si no tiene mas que ir por la izquierda retorna su dato
        } else {
            return buscarSucesorInOrden(hijoDerecho.getHijoIzquierdo()); //todo izquierda
        }
    }

    /**
     * Funcion que verifica si exite o no el dato en el Arbol Binario
     *
     * @param dato: dato del mismo tipo que el arbol binario
     * @return <Strong>true</Strong>: El dato existe en el Arbol<br>
     * <Strong>False</Strong>: El dato no existe en el Arbol<br>
     */
    @Override
    public boolean contiene(T dato) {
        return this.buscar(dato) != null;
    }

    @Override
    public T buscar(T datoABuscar) {
        NodoBinario<T> nodoActual = this.raiz;

        while (!NodoBinario.esNodoVacio(nodoActual)) {
            T datoActual = nodoActual.getDato();
            if (datoABuscar.compareTo(datoActual) == 0) {
                return datoActual; //Retornamos el dato encontrado
            }
            if (datoABuscar.compareTo(datoActual) < 0) {
                nodoActual = nodoActual.getHijoIzquierdo();
            } else {
                nodoActual = nodoActual.getHijoDerecho();
            }
        }
        return null; //Si el dato no está
    }

    @Override
    public List<T> recorridoEnInOrden() {  //By me
        List<T> recorrido = new LinkedList<>();
        Stack<NodoBinario<T>> pila = new Stack<NodoBinario<T>>();
        NodoBinario<T> nodoActual = raiz;
        while (!pila.empty() || !NodoBinario.esNodoVacio(nodoActual)) {
            while (!NodoBinario.esNodoVacio(nodoActual)) {
                pila.push(nodoActual);
                nodoActual = nodoActual.getHijoIzquierdo();
            }
            if (NodoBinario.esNodoVacio(nodoActual) && !pila.empty()) {
                NodoBinario<T> temp = pila.pop();
                recorrido.add(temp.getDato());
                nodoActual = temp.getHijoDerecho();
            }
        }
        return recorrido;
    }

    public List<T> recorridoEnInOrdenR() {
        List<T> recorrido = new ArrayList<>();
        this.recorridoEnInOrdenVersionR(raiz, recorrido);
        return recorrido;
    }

    private void recorridoEnInOrdenVersionR(NodoBinario<T> nodoActual,
            List<T> recorrido) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return;
        }
        recorridoEnInOrdenVersionR(nodoActual.getHijoIzquierdo(), recorrido);
        recorrido.add(nodoActual.getDato());
        recorridoEnInOrdenVersionR(nodoActual.getHijoDerecho(), recorrido);
    }

    @Override
    public List<T> recorridoEnPostOrden() {  //By me
        List<T> recorrido = new ArrayList<>();
        Stack<NodoBinario<T>> pila = new Stack<NodoBinario<T>>();
        NodoBinario<T> nodoActual = raiz;
        while (!pila.empty() || !NodoBinario.esNodoVacio(nodoActual)) {
            while (!NodoBinario.esNodoVacio(nodoActual)) {
                pila.push(nodoActual);
                if (nodoActual.esHijoIzquiedoVacio()) {
                    nodoActual = nodoActual.getHijoDerecho();
                } else {
                    nodoActual = nodoActual.getHijoIzquierdo();
                }
            }
            nodoActual = pila.pop();
            recorrido.add(nodoActual.getDato());
            if (!pila.empty()) {
                NodoBinario<T> nodoPadre = pila.peek();
                if (!nodoPadre.esHijoDerechoVacio()
                        && nodoPadre.getHijoDerecho() != nodoActual) {
                    nodoActual = nodoPadre.getHijoDerecho();
                } else {
                    nodoActual = NodoBinario.nodoVacio();
                }
            } else {
                nodoActual = NodoBinario.nodoVacio();
            }
        }
        return recorrido;
    }

    @Override
    public List<T> recorridoEnPreOrden() {
        List<T> recorrido = new ArrayList<>();
        Stack<NodoBinario<T>> pila = new Stack<NodoBinario<T>>();
        NodoBinario<T> nodoActual = raiz;
        while (!pila.empty() || !NodoBinario.esNodoVacio(nodoActual)) {
            while (!NodoBinario.esNodoVacio(nodoActual)) {
                recorrido.add(nodoActual.getDato());
                pila.push(nodoActual);
                nodoActual = nodoActual.getHijoIzquierdo();
            }
            if (NodoBinario.esNodoVacio(nodoActual) && !pila.empty()) {
                nodoActual = pila.pop().getHijoDerecho();
            }
        }
        return recorrido;
    }

    public List<T> recorridoEnPreOrdenV2() {
        List<T> recorrido = new ArrayList<>();
        if (this.esArbolVacio()) {
            return recorrido; //Lista vacia
        }
        Stack<NodoBinario<T>> pilaDeNodos = new Stack<>();
        pilaDeNodos.push(raiz);
        while (!pilaDeNodos.isEmpty()) {
            NodoBinario<T> nodoActual = pilaDeNodos.pop();
            recorrido.add(nodoActual.getDato());
            if (!nodoActual.esHijoDerechoVacio()) {
                pilaDeNodos.push(nodoActual.getHijoDerecho());
            }
            if (!nodoActual.esHijoIzquiedoVacio()) {
                pilaDeNodos.push(nodoActual.getHijoIzquierdo());
            }
        }
        return recorrido;
    }

    public List<T> recorridoEnPreOrdenR() {
        List<T> recorrido = new ArrayList<>();
        this.recorridoEnPreOrdenVersionR(raiz, recorrido);
        return recorrido;
    }

    private void recorridoEnPreOrdenVersionR(NodoBinario<T> nodoActual,
            List<T> recorrido) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return;
        }
        recorrido.add(nodoActual.getDato());
        recorridoEnPreOrdenVersionR(nodoActual.getHijoIzquierdo(), recorrido);
        recorridoEnPreOrdenVersionR(nodoActual.getHijoDerecho(), recorrido);
    }

    public List<T> recorridoEnPostOrdenR() {
        List<T> recorrido = new ArrayList<>();
        this.recorridoEnPostOrdenVersionR(raiz, recorrido);
        return recorrido;
    }

    private void recorridoEnPostOrdenVersionR(NodoBinario<T> nodoActual,
            List<T> recorrido) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return;
        }
        recorridoEnPostOrdenVersionR(nodoActual.getHijoIzquierdo(), recorrido);
        recorridoEnPostOrdenVersionR(nodoActual.getHijoDerecho(), recorrido);
        recorrido.add(nodoActual.getDato());
    }

    public List<T> recorridoEnPostOrdenV2() { //con la logica hecha en clase
        List<T> recorrido = new ArrayList<>();  //Lista de datos
        if (this.esArbolVacio()) {
            return recorrido; //Lista vacia
        }

        Stack<NodoBinario<T>> pilaDeNodos = new Stack<>();
        NodoBinario<T> nodoActual = this.raiz;
        meterEnPilaParaPostOrden(nodoActual, pilaDeNodos);
        while (!pilaDeNodos.isEmpty()) {
            nodoActual = pilaDeNodos.pop();
            recorrido.add(nodoActual.getDato());
            if (!pilaDeNodos.isEmpty()) {
                NodoBinario<T> nodoDelTope = pilaDeNodos.peek();
                if (!nodoDelTope.esHijoDerechoVacio()
                        && nodoDelTope.getHijoDerecho() != nodoActual) {
                    meterEnPilaParaPostOrden(nodoDelTope.getHijoDerecho(), pilaDeNodos);
                }
            }
        }
        return recorrido;
    }

    private void meterEnPilaParaPostOrden(NodoBinario<T> nodoActual,
            Stack<NodoBinario<T>> pilaDeNodos) {
        while (!NodoBinario.esNodoVacio(nodoActual)) {
            pilaDeNodos.push(nodoActual);
            if (!nodoActual.esHijoIzquiedoVacio()) {
                nodoActual = nodoActual.getHijoIzquierdo();
            } else {
                nodoActual = nodoActual.getHijoDerecho();
            }
        }
    }

    @Override
    public List<T> recorridoPorNiveles() {
        List<T> recorrido = new ArrayList<>();
        if (this.esArbolVacio()) {
            return recorrido; //Lista vacia
        }
        Queue<NodoBinario<T>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(raiz);
        while (!colaDeNodos.isEmpty()) {
            NodoBinario<T> nodoActual = colaDeNodos.poll();
            recorrido.add(nodoActual.getDato());
            if (!nodoActual.esHijoIzquiedoVacio()) {
                colaDeNodos.offer(nodoActual.getHijoIzquierdo());
            }
            if (!nodoActual.esHijoDerechoVacio()) {
                colaDeNodos.offer(nodoActual.getHijoDerecho());
            }
        }
        return recorrido; //Lista con los datos(En el orden de este recorrido)
    }

    /**
     * Retorna la cantidad de datos que tiene el Arbol
     *
     * @return cantidad de datos total
     */
    @Override
    public int size() {//por Niveles
        if (this.esArbolVacio()) {
            return 0; //Arbol Vacio
        }
        int cantidadDeNodos = 0;
        Queue<NodoBinario<T>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(raiz);
        while (!colaDeNodos.isEmpty()) {
            NodoBinario<T> nodoActual = colaDeNodos.poll();
            cantidadDeNodos++;
            if (!nodoActual.esHijoIzquiedoVacio()) {
                colaDeNodos.offer(nodoActual.getHijoIzquierdo());
            }
            if (!nodoActual.esHijoDerechoVacio()) {
                colaDeNodos.offer(nodoActual.getHijoDerecho());
            }
        }
        return cantidadDeNodos; //Lista con los datos(En el orden de este recorrido)
    }

    public int sizeRecursivo() {
        return sizeRecursivo(this.raiz);
    }

    private int sizeRecursivo(NodoBinario<T> nodoActual) {
        return NodoBinario.esNodoVacio(nodoActual) ? 0
                : sizeRecursivo(nodoActual.getHijoIzquierdo()) + sizeRecursivo(nodoActual.getHijoDerecho()) + 1;
    }

    /**
     * calcula la altura del arbol
     * <strong>Respuesta:</strong><br>
     * con 0 nodo: 0<br>
     * con 1 nodo: 1<br>
     * con 9 nodo: 9
     *
     * @return altura
     */
    @Override
    public int altura() {
        return altura(this.raiz);
    }

    protected int altura(NodoBinario<T> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int alturaXIzquierda = altura(nodoActual.getHijoIzquierdo());
        int alturaXDerecha = altura(nodoActual.getHijoDerecho());
        return alturaXIzquierda > alturaXDerecha ? alturaXIzquierda + 1
                : alturaXDerecha + 1;
    }

    /**
     * Calcula la Altura de un ArbolBinarioDeBusqueda de forma Iterativa
     *
     * @return Altura total del Arbol
     */
    public int alturaInterativa() {
        if (this.esArbolVacio()) {
            return 0;
        }
        int alturaDelArbol = 0;
        Queue<NodoBinario<T>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(raiz);
        while (!colaDeNodos.isEmpty()) {
            int nroDeNodosDelNivel = colaDeNodos.size();
            while (nroDeNodosDelNivel > 0) {
                NodoBinario<T> nodoActual = colaDeNodos.poll();
                if (!nodoActual.esHijoIzquiedoVacio()) {
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                }
                if (!nodoActual.esHijoDerechoVacio()) {
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                }
                nroDeNodosDelNivel--;
            }
            alturaDelArbol++;
        }
        return alturaDelArbol;  //Altura total del arbol
    }

    /**
     * Eliminar la Raiz, haciendo que el arbol que de Vacio
     */
    @Override
    public void vaciar() {
        this.raiz = NodoBinario.nodoVacio();
    }

    /**
     * Funcion que verifica si el Arbol esta Vacio
     *
     * @return <strong>Verdadero:</strong> el arbol esta vacio<br>
     * <strong>falso:</strong> el arbol no esta vacio
     */
    @Override
    public boolean esArbolVacio() {
        return NodoBinario.esNodoVacio(raiz);
    }

    /**
     * Calcula el Nivel del Arbol, partiendo de la Raiz = Nivel 0<br>
     *
     * @return -1 : Si el Arbol esta Vacio   <br>
     * @return n : Nivel del Arbol
     */
    @Override
    public int nivel() {
        return this.altura() - 1;
    }

    /**
     * Funcion que calcula por el metodo Recursivo la cantidad total de Nodos
     * que tiene hijo izquierdo
     *
     * @return Cantidad de Nodos con hijo izquierdo
     */
    public int cantHijosIzquierdosR() {
        return this.cantHijosIzquierdosR(this.raiz);
    }

    private int cantHijosIzquierdosR(NodoBinario<T> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int cantHijosIzq = cantHijosIzquierdosR(nodoActual.getHijoIzquierdo());
        cantHijosIzq += cantHijosIzquierdosR(nodoActual.getHijoDerecho());
        if (!nodoActual.esHijoIzquiedoVacio()) {
            cantHijosIzq++;
        }
        return cantHijosIzq;
    }

    /**
     * Funcion que calcula por el metodo Iterativo la cantidad total de Nodos
     * que tiene hijo izquierdo
     *
     * @return Cantidad de Nodos con hijo izquierdo
     */
    public int cantHijosIzquierdos() {
        if (NodoBinario.esNodoVacio(this.raiz)) {
            return 0;
        }
        int cantHijosIzq = 0;
        Queue<NodoBinario<T>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        while (!colaDeNodos.isEmpty()) {
            NodoBinario<T> nodoActual = colaDeNodos.poll();
            if (!nodoActual.esHijoIzquiedoVacio()) {
                colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                cantHijosIzq++;
            }
            if (!nodoActual.esHijoDerechoVacio()) {
                colaDeNodos.offer(nodoActual.getHijoDerecho());
            }
        }
        return cantHijosIzq;
    }

    /**
     * Devuelve true si p es padre de h. Si p o h no existen, o p no es padre de
     * h, devuelve false.
     *
     * @param padre: Dato del Nodo actual
     * @param hijo: Dato de cualquiera de los hijos de Nodo Actual
     * @return <Strong>true</Strong>: si p es padre de h <br>
     * <Strong>false</Strong>: si p no es padre de h
     */
    public boolean isPadre(T padre, T hijo) {
        return isPadreR(raiz, padre, hijo);
    }

    private boolean isPadreR(NodoBinario<T> p, T padre, T hijo) {
        if (NodoBinario.esNodoVacio(p) || p.esHoja()) {
            return false;
        }
        if (p.getDato() == padre) {
            if (!p.esHijoIzquiedoVacio() && p.getHijoIzquierdo().getDato() == hijo) {
                return true;
            }
            if (!p.esHijoDerechoVacio() && p.getHijoDerecho().getDato() == hijo) {
                return true;
            }
        }
        return isPadreR(p.getHijoIzquierdo(), padre, hijo)
                || isPadreR(p.getHijoDerecho(), padre, hijo);
    }

    /**
     * Devuelve true si x es una hoja. Si x no es una hoja o x no existe,
     * devuelve false.
     *
     * @param x: Dato a comprobar
     * @return <Strong>true</Strong>: si x es hoja <br>
     * <Strong>false</Strong>: si x no es hoja o no existe
     */
    public boolean isHoja(T x) {
        return isHojaR(raiz, x);
    }

    private boolean isHojaR(NodoBinario<T> p, T dato) {
        if (NodoBinario.esNodoVacio(p)) {
            return false;
        }
        if (p.esHoja() && p.getDato() == dato) {
            return true;
        }
        return isHojaR(p.getHijoDerecho(), dato) || isHojaR(p.getHijoIzquierdo(), dato);

    }

    public void insertarParcial(T padre, T hijo) {
        if (!existe(hijo)) {
            insertarParcialR(raiz, padre, hijo);
        }
    }

    private void insertarParcialR(NodoBinario<T> p, T padre, T hijo) {
        if (!NodoBinario.esNodoVacio(p)) {
            if (p.getDato() == padre) {
                if (p.esHoja()) {
                    p.setHijoIzquierdo(new NodoBinario(hijo));
                    return;
                }
                if (NodoBinario.esNodoVacio(p.getHijoDerecho())
                        || NodoBinario.esNodoVacio(p.getHijoIzquierdo())) {
                    if (NodoBinario.esNodoVacio(p.getHijoDerecho())) {
                        p.setHijoDerecho(new NodoBinario(hijo));
                        return;
                    }
                    p.setHijoIzquierdo(new NodoBinario(hijo));
                    return;
                }
                if (p.getHijoIzquierdo().getDato().compareTo(p.getHijoDerecho().getDato()) < 0) {
                    insertarParcialR(p.getHijoIzquierdo(), p.getHijoIzquierdo().getDato(), hijo);
                    return;
                }
                insertarParcialR(p.getHijoDerecho(), p.getHijoDerecho().getDato(), hijo);
                return;
            }
            insertarParcialR(p.getHijoDerecho(), padre, hijo);
            insertarParcialR(p.getHijoIzquierdo(), padre, hijo);
        }
    }

    public boolean existe(T dato) {
        return existe(raiz, dato);
    }

    private boolean existe(NodoBinario<T> p, T dato) {
        if (NodoBinario.esNodoVacio(p)) {
            return false;
        }
        if (p.getDato() == dato) {
            return true;
        }
        return existe(p.getHijoDerecho(), dato) || existe(p.getHijoIzquierdo(), dato);
    }

    public boolean similar(ArbolBinarioBusqueda B) {
        if (B instanceof ArbolBinarioBusqueda) {
            ArbolBinarioBusqueda A = (ArbolBinarioBusqueda) B;
            return similar(raiz, A.raiz);
        }
        return false;
    }

    private boolean similar(NodoBinario<T> nodoActual, NodoBinario<T> nodoComparar) {
        if (NodoBinario.esNodoVacio(nodoActual) && NodoBinario.esNodoVacio(nodoComparar)) {
            return true;
        }
        if (!NodoBinario.esNodoVacio(nodoActual) && !NodoBinario.esNodoVacio(nodoComparar)) {
                boolean d = similar(nodoActual.getHijoDerecho(), nodoComparar.getHijoDerecho());
                boolean i = similar(nodoActual.getHijoIzquierdo(), nodoComparar.getHijoIzquierdo());
                return i && d;          
        }
        return false;
    }

    @Override
    public boolean equals(Object B) {
        if (B instanceof ArbolBinarioBusqueda) {
            ArbolBinarioBusqueda A = (ArbolBinarioBusqueda) B;
            if (NodoBinario.esNodoVacio(raiz) && NodoBinario.esNodoVacio(A.raiz)) {
                return true;
            }
            if (!NodoBinario.esNodoVacio(raiz) && !NodoBinario.esNodoVacio(A.raiz)) {
                if (raiz.getDato().getClass() == A.raiz.getDato().getClass()) {
                    return equals(raiz, A.raiz);
                }
            }
        }
        return false;
    }

    private boolean equals(NodoBinario<T> nodoActual, NodoBinario<T> nodoComparar) {
        if (NodoBinario.esNodoVacio(nodoActual) && NodoBinario.esNodoVacio(nodoComparar)) {
            return true;
        }
        if (!NodoBinario.esNodoVacio(nodoActual) && !NodoBinario.esNodoVacio(nodoComparar)) {
            if (nodoActual.getDato().compareTo(nodoComparar.getDato()) == 0) {
                boolean d = equals(nodoActual.getHijoDerecho(), nodoComparar.getHijoDerecho());
                boolean i = equals(nodoActual.getHijoIzquierdo(), nodoComparar.getHijoIzquierdo());
                return i && d;
            }
        }
        return false;
    }

    private void addHijo(LinkedList<NodoBinario<T>> colaNodos, LinkedList<Integer> colaNivel, NodoBinario b, int nivelP) {
        NodoBinario<T> hijo = b != null ? b.getHijoIzquierdo() : null;
        colaNodos.addLast(hijo);
        colaNivel.addLast(nivelP + 1);
        hijo = b != null ? b.getHijoDerecho() : null;
        colaNodos.addLast(hijo);
        colaNivel.addLast(nivelP + 1);
    }

    public void invertir() {
        invertir(raiz);
    }

    private void invertir(NodoBinario<T> p) {
        if (NodoBinario.esNodoVacio(p)) {
            return;
        }
        invertir(p.getHijoDerecho());
        invertir(p.getHijoIzquierdo());
        NodoBinario<T> hd = p.getHijoDerecho();
        p.setHijoDerecho(p.getHijoIzquierdo());
        p.setHijoIzquierdo(hd);
    }

    @Override
    public String toString() {
        LinkedList<NodoBinario<T>> colaNodos = new LinkedList<>();
        LinkedList<Integer> colaNivel = new LinkedList<>();
        if (NodoBinario.esNodoVacio(raiz)) {
            return "Arbol Vacio";
        }
        int nivelActual = 0;
        colaNodos.addLast(raiz);
        colaNivel.addLast(1);
        int x = altura();
        String e = "";
        String espacios = "";
        while (colaNivel.peek() <= x) {
            NodoBinario<T> p = colaNodos.pop();
            int nivelP = colaNivel.pop();
            if (nivelP != nivelActual) {
                e = e + "\n\n";
                espacios = nVeces((int) Math.pow(2, x - nivelActual - 1) - 1);
                nivelActual = nivelP;
            }
            if (p != null) {
                e = e + espacios + "<" + p.getDato() + ">\t" + espacios + "\t";
            } else {
                e = e + espacios + "<=>\t" + espacios + "\t";
            }
            addHijo(colaNodos, colaNivel, p, nivelActual);
        }
        return e + "\n";
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

    public static void main(String[] args) {
        ArbolBinarioBusqueda a = new ArbolBinarioBusqueda();
        ArbolBinarioBusqueda b = new ArbolBinarioBusqueda();
        String datoS[] = {"MZ","QE","MC","AB","MH","OE"};
        for (String string : datoS) {
            b.insertar(string);
        }
        Integer datos[] = {10, 32, 15, 5, 1, 9};
        for (Integer dato : datos) {
            a.insertar(dato);
           // b.insertar(dato);
        }
        System.out.println("Arbol a"+a.toString());
        System.out.println("Arbol b"+b.toString());
        System.out.println("Son Arboles Equivalente a y b : " + a.equals(b));
        System.out.println("Son Arboles similares a y b: "+a.similar(b));
    }
}
