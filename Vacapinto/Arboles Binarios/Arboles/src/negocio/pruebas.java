package negocio;

import excepciones.ExcepcionOrdenInvalido;

public class pruebas {

    public static void main(String[] args) throws ExcepcionOrdenInvalido {
        ArbolB a = new ArbolB(4);
        Integer[] datos1 = {300, 500, 100, 50, 400, 800, 90, 91, 70, 75, 99, 550, 80, 110,
            150, 1000, 350, 400, 600, 85, 87, 92, 95, 1100, 88, 89, 96, 98, 40, 30, 10, 15, 20, 25, 55, 60, 71, 73, 78, 74, 69};//300, 500, 100, 50, 400, 800, 90, 91, 70, 75,350,99,900,50,55,60,68,51,52,69,80,81,85,76,77,87};
        for (Integer dato : datos1) {
            a.insertar(dato);
        }
        a.mostrarArbolMVias();
        Integer[] datos2 = {70,10,30,50,20,40,73,80,87,75,85,78,71,89,100,96,98,95,550,800,1000,90,150,600,400,92,60,350,300,25,1100};
        for (Integer integer : datos2) {
            a.eliminar(integer);
        }
        a.mostrarArbolMVias();
        a.eliminar(110);
        a.mostrarArbolMVias();
    }
}
