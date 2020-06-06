
package Preg1;

public class Main {
    private static final int dataA[][]={{30,60,25},{60,70,15},{25,50,90},{70,35},{15,80,5},{90,20,10}}; 
    private static final int dataB[][]={ {10,20,30}, {20,10}, {30, 40}};
    
    private static Arbol load(int v[][]){
        Arbol t=null;
        
        for (int i=0; i<v.length; i++){
            int padre = v[i][0];            //v[i] = {padre, hijoIzq, hijoDer}
            if (t==null)
                t = new Arbol(padre);
            
            for (int j=1; j<v[i].length; j++){
                t.add(padre, v[i][j]);
            }
        }
        return t;
    }
    
    public static void main(String[] args) {  
        Arbol A = load(dataA);      //Cargar los datos del Arbol A, mostrado en el examen.      
        
        A.Inorden();
        A.niveles();
        
        A.cutLeafBunchs(35);
        
        A.cutLeafBunchs(150);
        
        A.cutLeafBunchs(80);
        
        A.cutLeafBunchs(10);
        
//        Arbol B = load(dataB);  //Otro árbol
//        B.cutLeafBunchs(30);
//        B.niveles();
    }
    
}
