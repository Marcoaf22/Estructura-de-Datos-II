package mvias;

public class Main {

    private static final int V[] = {40, 20, 10, 5, 15, 25};

    private static void cargarDatos(ArbolM A) {
        for (int i = 0; i < V.length; i++) {
            A.insertar(V[i]);
        }
    }

    public static void main(String[] args) {
        ArbolM A = new ArbolM();
        cargarDatos(A);
        A.inorden();
        A.niveles();
        System.out.println(A.gemelos(10, 60));
        System.out.println(A.hnoCercano(15, 25));
        System.out.println(A.cantDeDatas());
        System.out.println(A.nivel(25));
    }

}
