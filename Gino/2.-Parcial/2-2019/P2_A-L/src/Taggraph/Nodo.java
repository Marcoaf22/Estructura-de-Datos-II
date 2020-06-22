package Taggraph;

public class Nodo {     //Nodo que usa la class Lista
    public int Data;
    public int Peso;
    public Nodo Link;

    public Nodo(){
        this(0, 0);
    }
    
    public Nodo(int Data, int Peso) {
        this.Data = Data;
        this.Peso = Peso;
        this.Link = null;
    }

    public int getData() {
        return Data;
    }

    public void setData(int Data) {
        this.Data = Data;
    }

    public int getPeso() {
        return Peso;
    }

    public void setPeso(int Peso) {
        this.Peso = Peso;
    }
    
    public Nodo getLink() {
        return Link;
    }

    public void setLink(Nodo Link) {
        this.Link = Link;
    }
}
