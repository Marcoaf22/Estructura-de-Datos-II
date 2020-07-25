/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author Alejandro
 * @param <T>
 */
public class AVL<T extends Comparable<T>>
        extends ArbolBinarioBusqueda<T> {

    private static final short RANGO_PERMITIDO = 1;

    @Override
    public boolean insertar(T datoAInsertar) {
        try {
            this.raiz = this.insertar(this.raiz, datoAInsertar);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    private NodoBinario<T> insertar(NodoBinario<T> nodoActual, T datoAInsertar)
            throws Exception { //usar una pila parar saber como baje o recursivo
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return new NodoBinario<>(datoAInsertar);
        }
        T datoActual = nodoActual.getDato();
        if (datoAInsertar.compareTo(datoActual) < 0) {
            nodoActual.setHijoIzquierdo(insertar(nodoActual.getHijoIzquierdo(), datoAInsertar));
            return balancear(nodoActual);
            //insertar(nodoActual.getHijoIzquierdo(), datoAInsertar);
            //return nodoActual; //retornar balancear
        }
        if (datoAInsertar.compareTo(datoActual) > 0) {
            //insertar(nodoActual.getHijoDerecho(), datoAInsertar);
            nodoActual.setHijoDerecho(insertar(nodoActual.getHijoDerecho(), datoAInsertar));
            return balancear(nodoActual); //retornar balancear
        }
        throw new Exception(); //el dato ya esta en el arbol
    }

    @Override
    public boolean eliminar(T dato) {
        try {
            this.raiz = eliminar(this.raiz, dato);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private NodoBinario<T> eliminar(NodoBinario<T> nodoActual, T datoAEliminar)
            throws Exception {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            throw new Exception(); //el dato no esta en el arbol
        }
        T datoActual = nodoActual.getDato();
        if (datoAEliminar.compareTo(datoActual) < 0) {
            NodoBinario<T> supuestoHijoIzquierdo
                    = eliminar(nodoActual.getHijoIzquierdo(), datoAEliminar);
            nodoActual.setHijoIzquierdo(supuestoHijoIzquierdo);
            return balancear(nodoActual); //retornar balancear
        }
        if (datoAEliminar.compareTo(datoActual) > 0) {
            NodoBinario<T> supuestoHijoDerecho
                    = eliminar(nodoActual.getHijoDerecho(), datoAEliminar);
            nodoActual.setHijoDerecho(supuestoHijoDerecho);
            return balancear(nodoActual); //retornar balancear
        }

        //caso1
        if (nodoActual.esHoja()) {
            return NodoBinario.nodoVacio();
        }

        //caso2
        if (!nodoActual.esHijoIzquiedoVacio()
                && nodoActual.esHijoDerechoVacio()) {
            return balancear(nodoActual.getHijoIzquierdo());
        }
        if (nodoActual.esHijoIzquiedoVacio()
                && !nodoActual.esHijoDerechoVacio()) {
            return balancear(nodoActual.getHijoDerecho());
        }

        //caso 3
        T datoSucesor = buscarSucesorInOrden(nodoActual.getHijoDerecho()); //busca el dato del sucesor
        NodoBinario<T> supuestoNuevoHijoDerecho
                = eliminar(nodoActual.getHijoDerecho(), datoSucesor); //caera en caso 1 o 2
        nodoActual.setHijoDerecho(supuestoNuevoHijoDerecho);
        nodoActual.setDato(datoSucesor);
        return balancear(nodoActual);
    }

    private NodoBinario<T> balancear(NodoBinario<T> nodoActual) {
        int alturaRamaIzq = altura(nodoActual.getHijoIzquierdo());
        int alturaRamaDer = altura(nodoActual.getHijoDerecho());
        int diferenciaDeAlturas = alturaRamaIzq - alturaRamaDer;
        if (diferenciaDeAlturas > RANGO_PERMITIDO) {
            //mas largo por la izquierda
            NodoBinario<T> hijoIzq = nodoActual.getHijoIzquierdo();
            alturaRamaIzq = altura(hijoIzq.getHijoIzquierdo());
            alturaRamaDer = altura(hijoIzq.getHijoDerecho());
            if (alturaRamaDer > alturaRamaIzq) {
                return rotacionDobleADerecha(nodoActual);
            } else {
                return rotacionSimpleADerecha(nodoActual);
            }
        } else if (diferenciaDeAlturas < -RANGO_PERMITIDO) {
            //mas largo por derecha
            if (diferenciaDeAlturas != 0) { //le añadi esto
                NodoBinario<T> hijoDer = nodoActual.getHijoDerecho();
                alturaRamaIzq = altura(hijoDer.getHijoIzquierdo());
                alturaRamaDer = altura(hijoDer.getHijoDerecho());

                if (alturaRamaIzq > alturaRamaDer) {
                    return rotacionDobleAIzquierda(nodoActual);
                } else {
                    return rotacionSimpleAIzquierda(nodoActual);
                }
            }
        }
        return nodoActual;
    }

    private NodoBinario<T> rotacionDobleADerecha(NodoBinario<T> nodoActual) {
        nodoActual.setHijoIzquierdo(rotacionSimpleAIzquierda(nodoActual.getHijoIzquierdo()));
        return rotacionSimpleADerecha(nodoActual);
    }

    private NodoBinario<T> rotacionSimpleADerecha(NodoBinario<T> nodoActual) {
        NodoBinario<T> A2 = nodoActual.getHijoIzquierdo();
        NodoBinario<T> A3 = A2.getHijoDerecho();
        A2.setHijoDerecho(nodoActual);
        nodoActual.setHijoIzquierdo(A3);
        return A2;
    }

    private NodoBinario<T> rotacionDobleAIzquierda(NodoBinario<T> nodoActual) {
        nodoActual.setHijoDerecho(rotacionSimpleADerecha(nodoActual.getHijoDerecho()));
        return rotacionSimpleAIzquierda(nodoActual);
    }

    private NodoBinario<T> rotacionSimpleAIzquierda(NodoBinario<T> nodoActual) {
        NodoBinario<T> A2 = nodoActual.getHijoDerecho();
        NodoBinario<T> A3 = A2.getHijoIzquierdo();
        A2.setHijoIzquierdo(nodoActual);
        nodoActual.setHijoDerecho(A3);
        return A2;
    }

    public static void main(String[] args) {
        AVL a = new AVL();
        Integer datos[] = {10,20,30,40,50,60,70,80,90,100,110,120,130,140,150   };
        for (Integer dato : datos) {
            a.insertar(dato);
        }
        System.out.println(a.recorridoEnPostOrden().toString());
        System.out.println(a.toString());
    }

}
