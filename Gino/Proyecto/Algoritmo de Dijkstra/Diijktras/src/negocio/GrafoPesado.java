package negocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class GrafoPesado<T extends Comparable<T>> {

    protected List<T> listaDeVertices;
    protected List<List<AdyacenteConPeso>> listasDeAdyacencia;
    private boolean esDirigido;
    protected static final int POSICION_INVALIDA = -1;
    protected static final float INFINITO = 999999999;

    public GrafoPesado() {
        this(false);
    }

    public int cantidadDeVertices() {
        return listaDeVertices.size();
    }

    public T getVertice(int i) {
        return listaDeVertices.get(i);
    }

    public GrafoPesado(boolean esDirigido) {
        this.esDirigido = esDirigido;
        listaDeVertices = new LinkedList<>();
        listasDeAdyacencia = new LinkedList<>();
    }

    public boolean existeVertice(T vertice) {
        return posicionDelVertice(vertice) != POSICION_INVALIDA;
    }

    public int posicionDelVertice(T vertice) {
        for (int i = 0; i < listaDeVertices.size(); i++) {
            if (listaDeVertices.get(i).compareTo(vertice) == 0) {
                return i;
            }
        }
        return POSICION_INVALIDA;
    }

    public void eliminarAristas() {
        for (List<AdyacenteConPeso> list : listasDeAdyacencia) {
            list.clear();
        }
    }

    public boolean insertarVertice(T vertice) {
        if (existeVertice(vertice)) {
            return false;
        }
        listaDeVertices.add(vertice);
        List<AdyacenteConPeso> nuevaLista = new LinkedList<>();
        listasDeAdyacencia.add(nuevaLista);
        return true;
    }

    public boolean existeArista(int verticeOrigen, int verticeDestino, int peso) {
        List<AdyacenteConPeso> listaDeAdyacenciaDelOrigen = listasDeAdyacencia.get(verticeOrigen);
        return listaDeAdyacenciaDelOrigen.contains(new AdyacenteConPeso(verticeDestino, peso));
    }

    public boolean insertarArista(T verticeOrigen, T verticeDestino, int peso) {
        if (!existeVertice(verticeOrigen) || !existeVertice(verticeDestino)) {
            return false;
        }
        int posicionDelVO = posicionDelVertice(verticeOrigen);
        int posicionDelVD = posicionDelVertice(verticeDestino);
        List<AdyacenteConPeso> listaDeAdyacenciaDelOrigen = listasDeAdyacencia.get(posicionDelVO);
        listaDeAdyacenciaDelOrigen.add(new AdyacenteConPeso(posicionDelVD, peso));
        Collections.sort(listaDeAdyacenciaDelOrigen);
        if (!this.esDirigido && posicionDelVD != posicionDelVO) {
            List<AdyacenteConPeso> listaDeAdyacenciaDelDestino = listasDeAdyacencia.get(posicionDelVD);
            listaDeAdyacenciaDelDestino.add(new AdyacenteConPeso(posicionDelVO, peso));
            Collections.sort(listaDeAdyacenciaDelDestino);
        }
        return true;
    }

    public boolean bloquearArista(int vo, int vd, int peso) {
        if (!existeArista(vo, vd, peso)) {
            return false;
        }
        List<AdyacenteConPeso> listaDeAdyacenciaDelOrigen = listasDeAdyacencia.get(vo);
        for (AdyacenteConPeso adyacenteConPeso : listaDeAdyacenciaDelOrigen) {
            if (adyacenteConPeso.getIndiceDeVertice() == vd && adyacenteConPeso.getPeso() == peso) {
                adyacenteConPeso.inHabilitar();
            }
        }
        return true;
    }

    public boolean desbloquearArista(int vo, int vd, int peso) {
        if (!existeArista(vo, vd, peso)) {
            return false;
        }
        List<AdyacenteConPeso> listaDeAdyacenciaDelOrigen = listasDeAdyacencia.get(vo);
        for (AdyacenteConPeso adyacenteConPeso : listaDeAdyacenciaDelOrigen) {
            if (adyacenteConPeso.getIndiceDeVertice() == vd && adyacenteConPeso.getPeso() == peso) {
                adyacenteConPeso.habilitar();
            }
        }
        return true;
    }

    public boolean desbloqueArista(int vo, int vd, int peso) {
        if (!existeArista(vo, vd, peso)) {
            return false;
        }
        List<AdyacenteConPeso> listaDeAdyacenciaDelOrigen = listasDeAdyacencia.get(vo);
        for (AdyacenteConPeso adyacenteConPeso : listaDeAdyacenciaDelOrigen) {
            if (adyacenteConPeso.getIndiceDeVertice() == vd && adyacenteConPeso.getPeso() == peso) {
                adyacenteConPeso.habilitar();
            }
        }
        return true;
    }

    public List<AdyacenteConPeso> listaDeAdyacencia(int i) {
        return listasDeAdyacencia.get(i);
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < this.listasDeAdyacencia.size(); i++) {
            s += i + ".-" + this.listaDeVertices.get(i) + " --> " + listasDeAdyacencia.get(i).toString() + "\n";
        }
        return s;
    }

    public boolean eliminarVertice(int index) {
        if (index > listaDeVertices.size()) {
            return false;
        }
        this.listaDeVertices.remove(index);
        this.listasDeAdyacencia.remove(index);
        for (List<AdyacenteConPeso> adyacenteDeVertice : this.listasDeAdyacencia) {
            for (int i = 0; i < adyacenteDeVertice.size(); i++) {
                if (adyacenteDeVertice.get(i).getIndiceDeVertice() == index) {
                    adyacenteDeVertice.remove(i);
                }
            }
            for (int i = 0; i < adyacenteDeVertice.size(); i++) {
                int posicionDeAdyacente = adyacenteDeVertice.get(i).getIndiceDeVertice();
                if (posicionDeAdyacente > index) {
                    posicionDeAdyacente--;
                    adyacenteDeVertice.get(i).setIndiceDeVertice(posicionDeAdyacente);
                }
            }
        }
        return true;
    }

    public void eliminarAristas(int vo, int vd, float peso) {
        for (List<AdyacenteConPeso> list : listasDeAdyacencia) {
            list.clear();
        }
    }

    public List<Boolean> inicializarMarcado() {
        List<Boolean> marcado = new ArrayList<>();
        for (int i = 0; i < listaDeVertices.size(); i++) {
            marcado.add(false);
        }
        return marcado;
    }

    public boolean estaMarcado(List<Boolean> marcado, int index) {
        return marcado.get(index);
    }

    public void marcarVertice(List<Boolean> marcado, int index) {
        marcado.set(index, true);
    }

    public float shortestPath(Stack<Integer> camino, int vo, int vd) {
        List<Boolean> marcados = inicializarMarcado();
        List<Float> pesos = new ArrayList<>();
        List<Integer> predecesor = new ArrayList<>();
        for (int i = 0; i < this.cantidadDeVertices(); i++) {
            pesos.add(INFINITO);
            predecesor.add(-1);
        }
        pesos.set(vo, 0f);
        int posDeVerticeEnTurno = -1;
        while (!estaMarcado(marcados, vd)) {//&& verticeDeMenorPeso(pesos, marcados) != -1) {
            posDeVerticeEnTurno = verticeDeMenorPeso(pesos, marcados);
            if (posDeVerticeEnTurno == -1) {
                return -1;
            }
            marcarVertice(marcados, posDeVerticeEnTurno);
            predecesor.add(posDeVerticeEnTurno);
            List<AdyacenteConPeso> adyacentesDeTurno
                    = listasDeAdyacencia.get(posDeVerticeEnTurno);
            for (AdyacenteConPeso adyacenteConPeso : adyacentesDeTurno) {
                int posAdyacente = adyacenteConPeso.getIndiceDeVertice();
                if (!estaMarcado(marcados, posAdyacente)
                        && adyacenteConPeso.estaHabilitado()) {
                    float peso = pesos.get(posDeVerticeEnTurno) + this.getCosto(posDeVerticeEnTurno, posAdyacente);
                    if (peso < pesos.get(posAdyacente)) {
                        pesos.set(posAdyacente, peso);
                        predecesor.set(posAdyacente, posDeVerticeEnTurno);
                    }
                }
            }
        }
        camino.add(vd);
        while (predecesor.get(camino.peek()) != -1) {
            camino.add(predecesor.get(camino.peek()));
        }
        return pesos.get(vd);
    }

    //prueba de examen si no hay camino que tome el contrario pero el valor de ese sera
    //Multiplicado *2
    public float shortestPath2(Stack<Integer> camino, int vo, int vd) {
        List<Boolean> marcados = inicializarMarcado();
        List<Float> pesos = new ArrayList<>();
        List<Integer> predecesor = new ArrayList<>();
        for (int i = 0; i < this.cantidadDeVertices(); i++) {
            pesos.add(INFINITO);
            predecesor.add(-1);
        }
        pesos.set(vo, 0f);
        int posDeVerticeEnTurno = -1;
        while (!estaMarcado(marcados, vd)) {//&& verticeDeMenorPeso(pesos, marcados) != -1) {
            posDeVerticeEnTurno = verticeDeMenorPeso(pesos, marcados);
            if (posDeVerticeEnTurno == -1) {
                return -1;
            }
            marcarVertice(marcados, posDeVerticeEnTurno);
            predecesor.add(posDeVerticeEnTurno);
            List<AdyacenteConPeso> adyacentesDeTurno
                    = listasDeAdyacencia.get(posDeVerticeEnTurno);
            for (AdyacenteConPeso adyacenteConPeso : adyacentesDeTurno) {
                int posAdyacente = adyacenteConPeso.getIndiceDeVertice();
                if (!estaMarcado(marcados, posAdyacente)
                        && adyacenteConPeso.estaHabilitado()) {
                    float peso = pesos.get(posDeVerticeEnTurno) 
                            + this.getCosto(posDeVerticeEnTurno, posAdyacente);
                    if (peso < pesos.get(posAdyacente)) {
                        pesos.set(posAdyacente, peso);
                        predecesor.set(posAdyacente, posDeVerticeEnTurno);
                    }
                }
            }
            for (int i = 0; i < cantidadDeVertices(); i++) {
                if (!estaMarcado(marcados, i)) {
                    if (getady(i, posDeVerticeEnTurno)) {
                        float peso = pesos.get(posDeVerticeEnTurno) + (this.getCosto(i,posDeVerticeEnTurno)*2);
                        if (peso < pesos.get(i)) {
                            pesos.set(i, peso);
                            predecesor.set(i, posDeVerticeEnTurno);
                        }
                    }
                }
            }
        }
        camino.add(vd);
        while (predecesor.get(camino.peek()) != -1) {
            camino.add(predecesor.get(camino.peek()));
        }
        return pesos.get(vd);
    }

    public boolean getady(int vo, int vd) {
        List<AdyacenteConPeso> lista = listasDeAdyacencia.get(vo);
        for (int j = 0; j < lista.size(); j++) {
            if (lista.get(j).estaHabilitado() && lista.get(j).getIndiceDeVertice() == vd) {
                return true;
            }
        }
        return false;
    }

    public float getCosto(int vo, int vd) {
        List<AdyacenteConPeso> lista = listasDeAdyacencia.get(vo);
        for (AdyacenteConPeso adyacenteConPeso : lista) {
            if (adyacenteConPeso.estaHabilitado() && adyacenteConPeso.getIndiceDeVertice() == vd) {
                return adyacenteConPeso.getPeso();
            }
        }
        return POSICION_INVALIDA;
    }

    public int aristasDeshabilitadas(int vo, int vd) {
        List<AdyacenteConPeso> listaAdyacente = listasDeAdyacencia.get(vo);
        int i = 15;
        for (AdyacenteConPeso adyacenteConPeso : listaAdyacente) {
            if (adyacenteConPeso.getIndiceDeVertice() == vd) {
                if (adyacenteConPeso.estaHabilitado()) {
                    return i;
                } else {
                    i += 20;
                }
            }
        }
        return i;
    }

    public int verticeDeMenorPeso(List<Float> pesos, List<Boolean> marcados) {
        int posicionDelMenor = 0;
        float peso = INFINITO;
        for (int i = 0; i < marcados.size(); i++) {
            if (!marcados.get(i) && pesos.get(i) < peso) {
                peso = pesos.get(i);
                posicionDelMenor = i;
            }
        }
        return peso == INFINITO ? -1 : posicionDelMenor;
    }

    public static void main(String[] args) {
        GrafoPesado a = new GrafoPesado(true);
        char[] vertices = {'a', 'b', 'c', 'd'};
        for (char vertice : vertices) {
            a.insertarVertice(vertice);
        }
        String[] arista = {"bc", "cb", "ab", "ba","ac"};
        Integer[] valor = {20, 30, 10, 5,10};
        for (int i = 0; i < arista.length; i++) {
            String string = arista[i];
            int val = valor[i];
            a.insertarArista(string.charAt(0), string.charAt(1), val);
        }
        System.out.println(a.toString());
        a.bloquearArista(2, 1, 30);
        a.bloquearArista(0, 2, 10);
        Stack<Integer> camino = new Stack<>();
        System.out.println(a.toString());
        float peso = a.shortestPath2(camino, 2,0);
        System.out.println(peso);
        while (!camino.empty()) {
            System.out.println(camino.pop());
        }
    }
}
