package mvias;

public class Main {
    
  private static final int a[][] = {   //a{i]={peso, data1, data2, data3}
       {0, 100,200,300},  {20, 40,60,85} ,  {10, 110,150,160},  {30, 270},  {5, 320,350,400}, 
       {15, 5,10,15}, {25, 45,50,55}, {10, 65,70,80}, {40, 130,140}, {5, 165,170,190}, {15, 305,310}, {50, 410,420,500}, 
       {35, 74,76}, {20, 175,180,185} };

   //private static final int a[][] = {{0,20,30},{5,15,18},{2,50,70}};  //a{i]={peso, data1, data2, data3}
   
   private static void cargarDatos(ArbolM A, int data[][]){
       for (int i=0; i < data.length; i++){
           int v[] = data[i];
           int peso = v[0];
           for (int j=1; j < v.length; j++){
               A.insertar(v[j], peso);
           }           
       }     
   }
   
    
    
    public static void main(String[] args) {
        ArbolM A = new ArbolM();
        cargarDatos(A, a);   //Cargar el árbol A mostrado en el examen.
    
        A.niveles("A");
     //   A.sumPeso(11);
       // System.out.println(A.sumPeso(11));
      A.costof(50);
      System.out.println( A.costof(185) );
       
       
      
    }
    
}
