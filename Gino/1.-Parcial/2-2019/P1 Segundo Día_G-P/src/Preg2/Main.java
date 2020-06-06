package Preg2;

public class Main {

    private static final int data[] = {9, 6, 7, 3, 9, 8, 1, 6, 7, 5};

    public static void main(String[] args) {
        Lista P = new Lista();

        for (int i = 0; i < data.length; i++) {  //Cargar los datos de la Lista, mostrados en el examen
            P.add(data[i]);
        }

        System.out.println("La lista inicial es " + P);

        P.del(7, 13);

        P.del(2, 9);

        P.del(1, 8);
        System.out.println(P);

        P.del(9, 6);

        P.del(5, 7);
        System.out.println(P);

        P.del(6, 9);
        System.out.println(P);
    }

}
