package Preg2;


public class Lista {
    private static final int data[] = {3, 1, 6, 9, 8, 3, 7, 5, 6};
    private Nodo L;
    
    
    public Lista(){     //Usted debe implementar éste método.
       L = null;
       for (int i=0; i < data.length; i++){
           Nodo p = new Nodo(data[i]);
           p.Link = L;
           L=p;
       }
    }
    
   public void add(int nuevoNro, int nroBotar){   //Usted debe implementar éste método.
      
   }		

    @Override
    public String toString(){   //Para usar en el System.out.println 
        String S = "[";
        String coma="";
       
        Nodo p  = L;
        while (p != null){
            S += coma + p.getData();
            coma=", ";
            p = p.getLink();
        }
       
       return S+"]";
    }
}
