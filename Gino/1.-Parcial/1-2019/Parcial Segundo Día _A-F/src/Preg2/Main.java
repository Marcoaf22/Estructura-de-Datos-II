package Preg2;

public class Main {
    
    public static void main(String[] args) {
        Lista P = new Lista();  //P=(vacía)
        
        P.add(20, 5);  //P=[20/5]
        P.add(10, 2);  //P=[20/5, 10/2]
        P.add(80, 6);  //P=[20/5, 10/2, 80/6]
        P.mostrar();   //(En la consola se muestra):    20/5, 10/2, 80/6 
        
        P.shoot(10);   //P=[20/5, 10/1, 80/6]    (El personaje 10 recibió un disparo, por tanto se le quita una vida)

        P.add(20, 3);  //P=[20/3, 10/1, 80/6]    (El personaje 20 ya existe, por tanto solo se le modifica su cantidad de vidas)

        P.mostrar();
        
        P.shoot(50);   //P=[20/3, 10/1, 80/6]    (El personaje 50  NO existe, por tanto “no pasa nada”)

        P.shoot(10);   //P=[20/3, 80/6]    (El 10 recibió un disparo, por tanto se le quita una vida. Como su cantidad de vidas es cero, el 10                                                    
                                           // es eliminado de la Lista P)     
        P.mostrar();   //(En la consola se muestra):     20/3, 80/6

        P.add(23, 4);
    }
    
}
