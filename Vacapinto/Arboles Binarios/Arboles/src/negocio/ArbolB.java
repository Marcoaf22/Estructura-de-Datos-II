package negocio;

import excepciones.ExcepcionOrdenInvalido;
import java.util.Stack;

public class ArbolB<T extends Comparable<T>> extends ArbolMViasBusqueda<T> {

    int nroMaxDeDatos;
    int nroMinDeDatos;
    int nroMinDeHijos;

    public ArbolB() throws ExcepcionOrdenInvalido {
        this(3);
    }

    public ArbolB(int orden) throws ExcepcionOrdenInvalido {
        super(orden);
        nroMaxDeDatos = orden - 1;
        nroMinDeDatos = nroMaxDeDatos / 2;
        nroMinDeHijos = nroMinDeDatos + 1;
    }

    @Override
    public boolean insertar(T dato) {
        if (esArbolVacio()) {
            this.raiz = new NodoMVias<>(this.orden + 1, dato);
            return true;
        }
        NodoMVias<T> nodoActual = this.raiz;
        Stack<NodoMVias<T>> pilaDeAncestros = new Stack<>();
        while (!NodoMVias.esNodoVacio(nodoActual)) {
            if (existeDatoEnNodo(nodoActual, dato)) {
                return false;
            }
            if (nodoActual.esHoja()) {
                this.insertarEnOrden(nodoActual, dato);
                if (nodoActual.cantidadDeDatosNoVacios() > this.nroMaxDeDatos) {
                    dividir(nodoActual, pilaDeAncestros);
                }
                nodoActual = NodoMVias.nodoVacio();
            } else {
                int posicion = this.porDondeBajar(nodoActual, dato);
                pilaDeAncestros.push(nodoActual);
                nodoActual = nodoActual.getHijo(posicion);
            }
        }
        return true;
    }

    /**
     * En caso de que el nodo hoja a insertar supere el NroMaxDeDatos dividimos
     * dicho nodo
     *
     * @param nodoActual nodo que supere los maximo de datos
     * @param pilaDeAncestros
     */
    private void dividir(NodoMVias<T> nodoActual, Stack<NodoMVias<T>> pilaDeAncestros) {
        NodoMVias<T> nodoPadre;
        int posicionASubir;
        if (pilaDeAncestros.empty()) {
            nodoPadre = new NodoMVias<>(orden + 1, nodoActual.getDato(nroMinDeDatos));
            raiz = nodoPadre;
            posicionASubir = 0;
            nodoPadre.setHijo(0, nodoActual);
        } else {
            nodoPadre = pilaDeAncestros.pop();
            posicionASubir = porDondeBajar(nodoPadre, nodoActual.getDato(nroMinDeDatos));
            int datosTotales = nodoPadre.cantidadDeDatosNoVacios();
            //los subimos el valor separador y movemos todos los hijo y datos del padre, no movemos el nodoActual
            for (int i = datosTotales - 1; i >= posicionASubir; i--) {
                nodoPadre.setDato(i + 1, nodoPadre.getDato(i));
                nodoPadre.setHijo(i + 2, nodoPadre.getHijo(i + 1));
            }
            nodoPadre.setDato(posicionASubir, nodoActual.getDato(nroMinDeDatos));
        }//el nuevoHijoDerecho tendra los datos e hijos siguientes del valor separador
        NodoMVias<T> nuevoHijoDerecho = new NodoMVias<>(orden + 1);
        for (int i = 0; i < nroMaxDeDatos - nroMinDeDatos; i++) {
            nuevoHijoDerecho.setDato(i, nodoActual.getDato(nroMinDeDatos + i + 1));
            nodoActual.setDato(nroMinDeDatos + i + 1, (T) NodoMVias.datoVacio());
            nuevoHijoDerecho.setHijo(i, nodoActual.getHijo(nroMinDeDatos + i + 1));
            nodoActual.setHijo(nroMinDeDatos + i + 1, NodoMVias.nodoVacio());
        }
        nuevoHijoDerecho.setHijo(nuevoHijoDerecho.cantidadDeDatosNoVacios(), nodoActual.getHijo(orden));
        nodoActual.setHijo(orden, NodoMVias.nodoVacio());
        nodoPadre.setHijo(posicionASubir + 1, nuevoHijoDerecho);
        nodoActual.setDato(nroMinDeDatos, (T) NodoMVias.datoVacio());
        if (nodoPadre.cantidadDeDatosNoVacios() > nroMaxDeDatos) {
            dividir(nodoPadre, pilaDeAncestros);
        }
    }

    @Override
    public boolean eliminar(T datoAEliminar) {
        Stack<NodoMVias<T>> pilaDeAncestros = new Stack<>();
        NodoMVias<T> nodoActual = buscarNodoDelDato(datoAEliminar, pilaDeAncestros);
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return false;
        }
        int posicionDelDatoEnElNodo = this.porDondeBajar(nodoActual, datoAEliminar);
        if (nodoActual.esHoja()) {
            eliminarDatoDeNodo(nodoActual, posicionDelDatoEnElNodo);
            if (nodoActual.cantidadDeDatosNoVacios() < nroMinDeDatos) {
                if (pilaDeAncestros.empty()) {//nodoActual es la Raiz
                    if (nodoActual.cantidadDeDatosNoVacios() == 0) {
                        this.vaciar();
                    }
                } else {
                    prestarOFusionar(nodoActual, pilaDeAncestros);
                }
            } else {
                return true;
            }
        } else {
            pilaDeAncestros.push(nodoActual);
            NodoMVias<T> predecesor = buscarNodoPredecesorInOrden(nodoActual.getHijo(posicionDelDatoEnElNodo),
                    pilaDeAncestros);
            int posicionDelPredecesor = predecesor.cantidadDeDatosNoVacios() - 1;
            T datoDelPredecesor = predecesor.getDato(posicionDelPredecesor);
            eliminarDatoDeNodo(predecesor, posicionDelPredecesor);
            nodoActual.setDato(posicionDelDatoEnElNodo, datoDelPredecesor);
            if (predecesor.cantidadDeDatosNoVacios() < nroMinDeDatos) {
                prestarOFusionar(predecesor, pilaDeAncestros);
            }
        }
        return true;
    }

    private void prestarOFusionar(NodoMVias<T> nodoActual, Stack<NodoMVias<T>> pilaDeAncestros) {
        NodoMVias<T> nodoPadre;
        nodoPadre = pilaDeAncestros.pop();
        int posicionDelHijo = posicionDelHijo(nodoPadre, nodoActual);
        if (!NodoMVias.esNodoVacio(nodoPadre.getHijo(posicionDelHijo + 1))// prestar del hermano siguiente
                && (nodoPadre.getHijo(posicionDelHijo + 1).cantidadDeDatosNoVacios() > nroMinDeDatos)) {
            NodoMVias<T> nodoHermano = nodoPadre.getHijo(posicionDelHijo + 1);
            //pasamos del padre al hijo
            nodoActual.setDato(nroMinDeDatos - 1, nodoPadre.getDato(posicionDelHijo));
            nodoPadre.setDato(posicionDelHijo, nodoHermano.getDato(0));
            nodoActual.setHijo(nroMinDeDatos, nodoHermano.getHijo(0));
            //recorremos lo que nos presto el hermano
            recorrerIzqPro(nodoHermano, 1);
        } else { //prestar del hermano anterior 
            if (posicionDelHijo != 0
                    && (nodoPadre.getHijo(posicionDelHijo - 1).cantidadDeDatosNoVacios() > nroMinDeDatos)) {
                NodoMVias<T> nodoHermano = nodoPadre.getHijo(posicionDelHijo - 1);
                recorrerDer(nodoActual, 1);//recorremos una sola vez ,quitar cant de recorrerDer
                nodoActual.setDato(0, nodoPadre.getDato(posicionDelHijo - 1));
                nodoPadre.setDato(posicionDelHijo - 1,nodoHermano.getDato(nodoHermano.cantidadDeDatosNoVacios() - 1));
                nodoActual.setHijo(0, nodoHermano.getHijo(nodoHermano.cantidadDeDatosNoVacios()));
                //limpiamos de lo que nos hacemos prestar
                nodoHermano.setHijo(nodoHermano.cantidadDeDatosNoVacios(), NodoMVias.nodoVacio());
                nodoHermano.setDato(nodoHermano.cantidadDeDatosNoVacios() - 1, (T) NodoMVias.datoVacio());
            } else {//fusion con el hermano siguiente
                if (!NodoMVias.esNodoVacio(nodoPadre.getHijo(posicionDelHijo + 1))) {
                    NodoMVias<T> nodoHermano = nodoPadre.getHijo(posicionDelHijo + 1);
                    recorrerDer(nodoHermano, nroMinDeDatos);
                    nodoHermano.setDato(nroMinDeDatos - 1, nodoPadre.getDato(posicionDelHijo));
                    nodoHermano.setHijo(nroMinDeDatos - 1, nodoActual.getHijo(nroMinDeDatos - 1));
                    for (int i = nroMinDeDatos - 2; i >= 0; i--) {
                        nodoHermano.setDato(i, nodoActual.getDato(i));
                        nodoHermano.setHijo(i, nodoActual.getHijo(i));
                    }
                    recorrerIzqPro(nodoPadre, posicionDelHijo + 1);
                    nodoPadre.setHijo(posicionDelHijo, nodoHermano);
                } else {//Fusion con el hermano anterior
                    NodoMVias<T> nodoHermano = nodoPadre.getHijo(posicionDelHijo - 1);
                    nodoHermano.setDato(nroMinDeDatos, nodoPadre.getDato(posicionDelHijo - 1));
                    nodoHermano.setHijo(nroMinDeDatos + 1, nodoActual.getHijo(0));
                    for (int i = 0; i <= nroMinDeDatos - 2; i++) {
                        nodoHermano.setDato(nroMinDeDatos + i + 1, nodoActual.getDato(i));
                        nodoHermano.setHijo(nroMinDeDatos + i + 2, nodoActual.getHijo(i + 1));
                    }
                    nodoPadre.setDato(posicionDelHijo - 1, (T) NodoMVias.datoVacio());
                    nodoPadre.setHijo(posicionDelHijo, NodoMVias.nodoVacio());
                }
            }
        }
        if (nodoPadre.cantidadDeDatosNoVacios() < nroMinDeDatos) {
            if (pilaDeAncestros.empty()) {
                if (nodoPadre.cantidadDeDatosNoVacios() == 0) {
                    raiz = nodoPadre.getHijo(0);
                }
            } else {
                prestarOFusionar(nodoPadre, pilaDeAncestros);
            }
        }
    }

    /**
     * A medida que recorrer lo va limpiando el nodo
     *
     * @param nodoActual nodo a recorrer
     * @param cant numero de posiciones a recorrer
     */
    private void recorrerDer(NodoMVias<T> nodoActual, int cant) {
        int cdnv = nodoActual.cantidadDeDatosNoVacios();
        for (int i = cdnv - 1; i >= 0; i--) {
            nodoActual.setDato(i + cant, nodoActual.getDato(i));
            nodoActual.setHijo(i + cant + 1, nodoActual.getHijo(i + 1));
            nodoActual.setDato(i, (T) NodoMVias.datoVacio());
            nodoActual.setHijo(i + 1, NodoMVias.nodoVacio());
        }
        nodoActual.setHijo(cant, nodoActual.getHijo(0));
        nodoActual.setHijo(0, NodoMVias.nodoVacio());
    }

    /**
     * recorre una vez ala izq los elemento de un nodo desde una posicion
     *
     * @param nodoActual : nodo a recorrer
     * @param desde: posicion desde la cual recorrer
     */
    private void recorrerIzqPro(NodoMVias<T> nodoActual, int desde) {
        int cdnv = nodoActual.cantidadDeDatosNoVacios();
        for (int i = desde; i <= cdnv - 1; i++) {
            nodoActual.setDato(i - 1, nodoActual.getDato(i));
            nodoActual.setHijo(i - 1, nodoActual.getHijo(i));
        }
        nodoActual.setHijo(cdnv - 1, nodoActual.getHijo(cdnv));
        nodoActual.setHijo(cdnv, NodoMVias.nodoVacio());
        nodoActual.setDato(cdnv - 1, (T) NodoMVias.datoVacio());
    }

    /**
     * Trabaja a partir de una hoja solo recorrer todos las posiciones
     * siguientes hasta la posicicon a eliminar
     *
     * @param nodoActual
     * @param posicionAEliminar
     */
    private void eliminarDatoDeNodo(NodoMVias<T> nodoActual, int posicionAEliminar) {
        int total = nodoActual.cantidadDeDatosNoVacios();
        for (int i = posicionAEliminar; i < total - 1; i++) {
            nodoActual.setDato(i, nodoActual.getDato(i + 1));
        }
        nodoActual.setDato(total - 1, (T) NodoMVias.datoVacio());
    }

    /**
     * Regresa la posicion de hijo que es nodoActual del nodoPadre
     *
     * @param nodoPadre
     * @param nodoActual hijo del nodoPadre
     * @return -1: en caso de que nodoActual no sea hijo de nodoPadre
     */
    private int posicionDelHijo(NodoMVias<T> nodoPadre, NodoMVias<T> nodoActual) {
        int hasta = nodoPadre.cantidadDeDatosNoVacios();
        for (int i = 0; i <= hasta; i++) {
            if (nodoPadre.getHijo(i) == nodoActual) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Busca el predecesor InOrden , basicamente recorrer por el ultimo hijo de
     * cada nodo hasta llegar a una hoja
     *
     * @param nodoActual
     * @param pilaDeAncestros va apilando los nodos por donde recorrio hasta
     * llegar al ultimo nodo que sera hoja
     * @return
     */
    private NodoMVias<T> buscarNodoPredecesorInOrden(NodoMVias<T> nodoActual, Stack<NodoMVias<T>> pilaDeAncestros) {
        NodoMVias<T> nodoBuscador = nodoActual;
        while (!NodoMVias.esNodoVacio(nodoBuscador.getHijo(nodoBuscador.cantidadDeDatosNoVacios()))) {
            pilaDeAncestros.push(nodoBuscador);
            nodoBuscador = nodoBuscador.getHijo(nodoBuscador.cantidadDeDatosNoVacios());
        }
        return nodoBuscador;
    }

    /**
     * Regresa el Nodo donde se encuentra el dato a buscar
     *
     * @param datoABuscar
     * @param pilaDeAncestros se apilara todo los nodos por donde se recorrio
     * hasta encontrar el Nodo del dato a buscar
     * @return
     */
    private NodoMVias<T> buscarNodoDelDato(T datoABuscar, Stack<NodoMVias<T>> pilaDeAncestros) {
        NodoMVias<T> nodoActual = raiz;
        while (nodoActual != null) {
            if (existeDatoEnNodo(nodoActual, datoABuscar)) {
                return nodoActual;
            } else {
                pilaDeAncestros.push(nodoActual);
                nodoActual = nodoActual.getHijo(this.porDondeBajar(nodoActual, datoABuscar));
            }
        }
        return nodoActual;
    }

}
