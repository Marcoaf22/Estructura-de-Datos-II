package lista;


public class Main {
    private static final int[] datos = {10, 8, 12, 3, 12, 8};
    
    private static void cargarDatos(Lista L){
        for (int i = 0; i < datos.length; i++) {
            L.add(datos[i]);
        }
    }
    
    
    public static void main(String[] args) {
        Lista p = new Lista();
        cargarDatos(p);
        System.out.println(p);  //Aqui se llama a p.toString()
    }  
}
