
package Bst;

public class Main {
    
    private static final int[] datos = {3, 7, 9, 8, 3, 5};  //Escriba aquí los datos del árbol
      
    private static void cargarDatos(Arbol T){
        for (int i = 0; i < datos.length; i++) {
            T.Insertar(datos[i]);
        }
    }
    
    
    public static void main(String[] args) {
        Arbol A = new Arbol();
        cargarDatos(A);
       
        A.Inorden();
        A.niveles();
    }
    
}
