package negocio;

public class NodoBinario<T> {
    
    private T dato;
    private NodoBinario<T> hijoIzquierdo;
    private NodoBinario<T> hijoDerecho;

    public NodoBinario() {
    }

    public NodoBinario(T dato) {
        this.dato = dato;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoBinario<T> getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(NodoBinario<T> hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public NodoBinario<T> getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(NodoBinario<T> hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }
    
    public static NodoBinario nodoVacio() {
        return null;
    }
    
    public static boolean esNodoVacio(NodoBinario unNodo) {
        return unNodo == NodoBinario.nodoVacio();
    }
    
    public void setHijoDerechoVacio() {
        this.setHijoDerecho(NodoBinario.nodoVacio());
    }
    
    public void setHijoIzquieroVacio() {
        this.setHijoIzquierdo(NodoBinario.nodoVacio());
    }
    
    public boolean esHijoIzquiedoVacio() {
        return NodoBinario.esNodoVacio(this.getHijoIzquierdo());
    }
    
    public boolean esHijoDerechoVacio() {
        return NodoBinario.esNodoVacio(this.getHijoDerecho());
    }
    
    public boolean esHoja() {
        return this.esHijoIzquiedoVacio() &&
                this.esHijoDerechoVacio();
    }
    
    
    
}
