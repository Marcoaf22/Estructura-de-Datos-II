package Preg2;

public class Main {
    private static final int data[]={9, 6, 7, 10, 11, 8, 3, 2, 1, 5};       //NO TIENE REPETIDOS !!
    
    
    public static void main(String[] args) {
        Lista P = new Lista();
        
        for (int i=0; i<data.length; i++){  //Cargar los datos de la Lista, mostrados en el examen
            P.add( data[i] );
        }
        
        System.out.println(P);
        
        P.delInto(7,11);

        P.delInto (20,9);

        P.delInto (6,8);
        System.out.println(P);

        P.delInto(8,10);
        System.out.println(P);

        P.delInto(3,7);
        System.out.println(P);

        P.delInto(5,2);
        System.out.println(P);
        P.delInto(5,3);
        System.out.println(P);
        
        
    }
    
}
