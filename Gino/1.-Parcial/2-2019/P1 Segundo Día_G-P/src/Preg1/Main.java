
package Preg1;

public class Main {
    private static final int dataA[]={60,40,65,30,50,62,70,20,35,55,68,80}; 
    private static final int dataB[]={60, 40, 80, 30, 50};
    
    private static Arbol load(int v[]){
        Arbol t=new Arbol();
        
        for (int i=0; i<v.length; i++){
           t.add(v[i]);
        }
        return t;
    }
    
    public static void main(String[] args) {  
        Arbol A = load(dataA);      //Cargar los datos del Arbol A, mostrado en el examen.      
        
        A.Inorden();
        A.niveles();
        
        A.cutLeafBunchs(55);
        A.cutLeafBunchs(150);
        
        A.cutLeafBunchs(80);
         A.Inorden();
        A.niveles();
        
        A.cutLeafBunchs(20);
        A.Inorden();
        A.niveles();
        
//        Arbol B = load(dataB);  //Otro árbol
//        B.cutLeafBunchs(30);
//        B.niveles();
    }
    
}
