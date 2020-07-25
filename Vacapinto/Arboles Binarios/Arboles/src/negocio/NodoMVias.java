package negocio;

import java.util.LinkedList;
import java.util.List;

/**
 * <strong>Descripcion:</strong><br>
 * Si n = Orden;<br>
 * tiene:<br> 
 * n-1 datos<br>
 * n hijos<br>
 * <strong>Importante:</strong><br>
 * las posiciones tanto de datos como de hijos se toman - 1
 * @author Alejandro
 * @param <T> dato generico
 */
public class NodoMVias <T>{
    private List<T> listaDeDatos;
    private List<NodoMVias <T>> listaDeHijos;
    
    public NodoMVias(int orden) {
        this.listaDeDatos = new LinkedList<>();
        this.listaDeHijos = new LinkedList<>();
        for (int i = 0; i < orden-1; i++) {
            this.listaDeDatos.add((T)NodoMVias.datoVacio());
            this.listaDeHijos.add(NodoMVias.nodoVacio());
        }
        this.listaDeHijos.add(NodoMVias.nodoVacio());
    }
    
    public NodoMVias(int orden, T dato) {
        this(orden);
        listaDeDatos.set(0, dato);
    }
    
    public static NodoMVias nodoVacio() {
        return null;
    }
    
    public static Object datoVacio() {
        return null;
    }
    
    public T getDato(int posicion) {
        return this.listaDeDatos.get(posicion);
    }
    
    public NodoMVias<T> getHijo(int posicion) {
        return this.listaDeHijos.get(posicion);
    }
    
    public void setDato(int posicion, T dato) {
        this.listaDeDatos.set(posicion, dato);
    }
    
    public void setHijo(int posicion, NodoMVias<T> nodoHijo) {
        this.listaDeHijos.set(posicion, nodoHijo);
    }
    
    public boolean esDatoVacio(int posicion) {
        return this.getDato(posicion) == NodoMVias.datoVacio();
    }
    
    public boolean esHijoVacio(int posicion) {
        return this.getHijo(posicion) == NodoMVias.nodoVacio();
    }
    
    public static boolean esNodoVacio(NodoMVias nodo) {
        return nodo == NodoMVias.nodoVacio();
    }
    
    public boolean esHoja() {
        for (int i = 0; i < this.listaDeHijos.size(); i++) {
            if (!this.esHijoVacio(i)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean estanDatosLlenos() {
        for (T unDato : this.listaDeDatos) {
            if (unDato == NodoMVias.datoVacio()) {
                return false;
            }
        }
        return true;
    }
    
    public boolean estanDatosVacios() {
        for (int i = 0; i < this.listaDeDatos.size(); i++) {
            if (!this.esDatoVacio(i)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 
     * @return la cantidad de datos que contiene el nodo
     */
    public int cantidadDeDatosNoVacios() {
        int cantidadDeDatos = 0;
        for (int i = 0; i < this.listaDeDatos.size(); i++) {
            if (!this.esDatoVacio(i)) {
                cantidadDeDatos++;
            }
        }
        return cantidadDeDatos;
    }

    public List<T> getListaDeDatos() {
        return this.listaDeDatos;
    }
       
    @Override
    public String toString() {
        return "Datos:"+listaDeDatos.toString() + "\n"
                +"Hijos:"+listaDeHijos.toString();
    }
    
    public static void main(String[] args) {
        NodoMVias padre = new NodoMVias(4);
        
        System.out.println(padre.toString());
    }
}
