/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import excepciones.ExcepcionOrdenInvalido;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;
import negocio.AVL;
import negocio.ArbolBinarioBusqueda;
import negocio.ArbolMViasBusqueda;
import negocio.IArbolBusqueda;
import negocio.NodoMVias;

/**
 *
 * @author Alejandro
 */
public class main {

    public static void main(String[] args) throws ExcepcionOrdenInvalido {
        /*IArbolBusqueda<Integer> Arbol = new ArbolBinarioBusqueda<>();
        IArbolBusqueda<Integer> ArbolAReconstruir = new ArbolBinarioBusqueda<>();
        Integer []datos = {50,20,70,10,30,90,25,75,100,26,74,99,150,73};
        Integer []datos2 = {100,30,150,20,35,125,190,1,22,34,39,180,69,185,183,184};
        
        for (Integer dato : datos2) {
            Arbol.insertar(dato);
        }
        System.out.println("Datos del arbol original:\n"+Arbol.toString());
        System.out.println("Recorrido enInOrden:\n"+((ArbolBinarioBusqueda<Integer>) Arbol).recorridoEnInOrdenVersionR().toString());
        System.out.println("Recorrido enPostOrden:\n"+Arbol.recorridoEnPostOrden().toString());
        System.out.println("Recorrido en PreOrden:\n"+Arbol.recorridoEnPreOrden().toString());
        
        ArbolAReconstruir = new ArbolBinarioBusqueda<>(((ArbolBinarioBusqueda<Integer>) Arbol).recorridoEnInOrdenVersionR(),
                Arbol.recorridoEnPreOrden(), false);
        System.out.println("datos del arbol a reconstruir:\n" + ArbolAReconstruir.toString());
         */

 /*IArbolBusqueda<String> ArbolDeCadenas = new ArbolBinarioBusqueda<>();
        String []datos = {"NF", "BC", "PX", "AK", "DH", "OK",
            "TT", "BA", "NT", "AX"};
        
        for (String dato : datos) {
            ArbolDeCadenas.insertar(dato);
        }
        
        System.out.println("Recorrido por niveles: " +
                ArbolDeCadenas.recorridoPorNiveles());
        System.out.println("Recorrido en preOrden: " +
                ArbolDeCadenas.recorridoEnPreOrden());
        System.out.println("Altura: " +
                ArbolDeCadenas.altura());
        System.out.println("size: " +
                ArbolDeCadenas.size());
        if (ArbolDeCadenas instanceof ArbolBinarioBusqueda) {
            System.out.println("Altura Interativa: " +
                ((ArbolBinarioBusqueda) ArbolDeCadenas).alturaInterativa());
            System.out.println("Recorrido en postOrdenRec mio" + 
                    ArbolDeCadenas.recorridoEnPostOrden().toString());
            System.out.println("Recorrido en postOrdenRec ing" + 
                    ((ArbolBinarioBusqueda<String>) ArbolDeCadenas).recorridoEnPostOrdenV2().toString());
        }
         */
        ArbolMViasBusqueda<Integer> a1 = new ArbolMViasBusqueda<>(5);
        //a1.insertar(1);
        Integer valores[] = {9, 8, 11, 7, 10, 25, 1, 2, 24, 27, 26};
        Integer valores2[] = {40, 80, 220, 400, 10, 20, 28, 35, 50, 60, 65, 78, 250,
            270, 300, 350, 500, 700, 850, 1000};
        for (Integer valore : valores2) {
            a1.insertar(valore);
        }
        List<Integer> a = new Stack<>();
        Stack<Integer> b = new Stack<>();
        Vector<Integer> c = new Stack<>();

        System.out.println(a1.toString());
//        System.out.println(a1.altura());
//        System.out.println(a1.nivel());
    }
}
