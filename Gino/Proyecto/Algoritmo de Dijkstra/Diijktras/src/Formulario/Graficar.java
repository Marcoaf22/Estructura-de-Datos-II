package Formulario;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import negocio.AdyacenteConPeso;
import negocio.GrafoPesado;

public class Graficar<T extends Comparable<T>> implements Serializable {

    GrafoPesado<T> grafo;
    ArrayList<Point> coordenada;

    public Graficar() {
        grafo = new GrafoPesado(true);
        coordenada = new ArrayList<>();
    }

    public void añadirVertice(T vertice, Point p) {
        if (grafo.insertarVertice(vertice)) {
            coordenada.add(p);
        }
    }

    public void añadirArista(int vo, int vd, int Peso) {
        grafo.insertarArista(grafo.getVertice(vo), grafo.getVertice(vd), Peso);
    }

    public void eliminarArista(int vo, int vd) {

    }

    public Point getPoint(int posDelVertice) {
        return coordenada.get(posDelVertice);
    }

    public int cantidadDeVertices() {
        return coordenada.size();
    }

    public void pintarGrafo(Graphics g) {
        pintarAristas(g);
        pintarVertices(g);
    }

    public void pintarVertices(Graphics g) {
        for (int i = 0; i < coordenada.size(); i++) {
            Dibujar.dibujarVertice(g, coordenada.get(i), "" + (i + 1) + ".- " + (String) grafo.getVertice(i));
        }
    }
    
    public String getVertice(int u){
        return (String)grafo.getVertice(u);
    }

    public void pintarAristas(Graphics g) {
        for (int i = 0; i < coordenada.size(); i++) {
            List<AdyacenteConPeso> lista = grafo.listaDeAdyacencia(i);
            int pos = i;
            int distancia = 15;
            for (AdyacenteConPeso adyacenteConPeso : lista) {
                if (adyacenteConPeso.getIndiceDeVertice() == pos) {
                    distancia += 20;
                } else {
                    distancia = 15;
                }
                Dibujar.dibujarArista(g, coordenada.get(i),
                        coordenada.get(adyacenteConPeso.getIndiceDeVertice()),
                        "" + adyacenteConPeso.getPeso(), adyacenteConPeso.estaHabilitado(), distancia, Color.BLACK);
                pos = adyacenteConPeso.getIndiceDeVertice();
            }
        }
    }

    public void eliminarAristas(int vo, int vd, float peso) {
        grafo.eliminarAristas(vo, vd, peso);
    }

    public void eliminarVertice(int pos) {
        grafo.eliminarVertice(pos);
        coordenada.remove(pos);
    }

    public void bloqueArista(int vo, int vd, int peso) {
        grafo.bloquearArista(vo, vd, peso);
    }

    public void desbloqueArista(int vo, int vd, int peso) {
        grafo.desbloquearArista(vo, vd, peso);
    }

    public int contiene(Point p) {
        for (int i = 0; i < coordenada.size(); i++) {
            if (new Rectangle(coordenada.get(i).x - 10 / 2, coordenada.get(i).y - 10 / 2, 10, 10).contains(p)) {
                return i;
            }
        }
        return -1;
    }
    public void eliminarAristas(){
        grafo.eliminarAristas();
    }

    public float caminoMasCorto(Stack<Integer> camino, int vo, int vd) {
        float peso = this.grafo.shortestPath(camino, vo, vd);
        if (peso != -1) {
            List<Integer> distancia = new ArrayList<>();
            int i = camino.size() - 1;
            int a = camino.get(i);
            while (0 < i) {
                i--;
                int b = camino.get(i);
                distancia.add(grafo.aristasDeshabilitadas(a, b));
                a = b;
            }
            Dibujar.pintarCamino(camino, coordenada, distancia, Color.GREEN);
        }
        return peso;
    }
}
