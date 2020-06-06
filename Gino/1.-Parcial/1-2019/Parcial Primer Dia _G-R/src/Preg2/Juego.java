
package Preg2;

public class Juego {    //Juego de los tarros
    private static final int N=3;   //Los tarros se enumeran de 0 a N-1
    Lista j[];
    
    public Juego(){ //Constructor. Todos los tarros están vacíos.
        j=new Lista[N];
        for (int i = 0; i < j.length; i++) {
             j[i]=new Lista();
        }        
    }
    
    public void lanzar(int i, int moneda){  
        j[i].add(moneda);
         System.out.println("Tarro " +i+": "+j[i].toString()+" Total: " +j[i].suma());
         if (gano(i)){
             System.out.println("Gano");
         }
    }
    
    public boolean gano(int i){
        if (j[i].suma()>=45)
            return true;
        return false;
    }
    
}
