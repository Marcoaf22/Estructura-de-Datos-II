package Preg2;

public class Main {

    public static void main(String[] args) {
        Juego J = new Juego();		

        J.lanzar(0, 9);	

        J.lanzar(1, 9);	

        J.lanzar(0, 6);	

        J.lanzar(2, 6);
        J.lanzar(2, 3);
        J.lanzar(2, 9);	

        J.lanzar(2, 3);	

        System.out.println(J.gano(2)); 

    }
    
}
