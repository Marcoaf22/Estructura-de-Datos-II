package Preg2;

public class Main {

    public static void main(String[] args) {
        Lista P = new Lista();  //P=(vacía)

        P.add(20, 5);

        P.add(10, 8);

        P.add(30, 1);
        System.out.println(P);
        P.add(20, 1);

        P.add(10, 8);

        System.out.println(P);

        P.add(8, 10);

        System.out.println(P);  //Mostrar la Lista P

    }

}
