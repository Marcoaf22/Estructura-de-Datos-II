package negocio;

public class AdyacenteConPeso implements Comparable<AdyacenteConPeso> {
    //static class AdyacenteConPeso implements Comparable<AdyacenteConPeso> {
    private int indiceDeVertice;
    private int peso;

    public AdyacenteConPeso(int indiceVertice) {
        this.indiceDeVertice = indiceVertice;
    }

    public AdyacenteConPeso(int indiceVertice, int peso) {
        this.indiceDeVertice = indiceVertice;
        this.peso = peso;
    }

    public int getIndiceDeVertice() {
        return indiceDeVertice;
    }

    public void setIndiceDeVertice(int indiceDeVertice) {
        this.indiceDeVertice = indiceDeVertice;
    }

    public float getPeso() {
        return Math.abs(peso);
    }

    public void inHabilitar() {
        this.peso = -(Math.abs(peso));
    }

    public void habilitar() {
        this.peso = Math.abs(peso);
    }

    public boolean estaHabilitado() {
        return this.peso >= 0;
    }

    public void setPeso(int peso) {
        this.peso = Math.abs(peso);
    }

    @Override
    public boolean equals(Object elOtro) {
        if (elOtro == null) {
            return false;
        }
        if (!(elOtro instanceof AdyacenteConPeso)) {
            return false;
        }
        return this.indiceDeVertice == ((AdyacenteConPeso) elOtro).indiceDeVertice
                && Math.abs(peso) == Math.abs(((AdyacenteConPeso)elOtro).peso);
    }

    @Override
    public int compareTo(AdyacenteConPeso elOtro) {
        if (elOtro == null) {//en este caso suponemos que los null son mayores
            return -1;
        }
        if (this.indiceDeVertice > elOtro.indiceDeVertice) {
            return 1;
        }
        if (this.indiceDeVertice < elOtro.indiceDeVertice) {
            return -1;
        }
        if (this.peso>elOtro.peso){
            return 1;
        }
        if (this.peso<elOtro.peso){
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "(" + this.indiceDeVertice + "/" + this.peso + /*","+estaHabilitado()+*/")";
    }
}
