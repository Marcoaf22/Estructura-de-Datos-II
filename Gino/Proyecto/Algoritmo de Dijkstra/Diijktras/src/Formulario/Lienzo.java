package Formulario;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Lienzo extends JPanel implements MouseListener {

    Graficar grafica;
    protected Point p1;
    protected Point p2;
    int paraArista;
    public static final int D = 10;

    public Lienzo() {
        grafica = new Graficar();
        this.addMouseListener(this);
        p1 = null;
        p2 = null;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        grafica.pintarGrafo(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            //para ver si esta haciendo click en un cuadrado donde ya ahi
            //vertice establecido
            for (int i = 0; i < grafica.cantidadDeVertices(); i++) {
                if (new Rectangle(grafica.getPoint(i).x - D / 2, grafica.getPoint(i).y - D / 2, 10,
                        10).contains(e.getPoint())) {
                    return;
                }
            }
            String nombre = JOptionPane.showInputDialog("Ingrese Vertice");
            if (nombre != null) {
                grafica.añadirVertice(nombre, e.getPoint());
                repaint();
            }
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            int posicionDelPunto = grafica.contiene(e.getPoint());
            if (posicionDelPunto != -1) {
                if (p1 == null) {
                    p1 = new Point(grafica.getPoint(posicionDelPunto));
                    paraArista = posicionDelPunto;
                } else {
                    Point aux = grafica.getPoint(posicionDelPunto);
                    if (!new Rectangle(aux.x - 10 / 2, aux.y - 10 / 2, 10, 10).contains(p1)) {
//                        String valor = JOptionPane.showInputDialog("Ingrese el Peso (Km) de la arista");
//                        try {
//                            Integer.parseInt(valor);
//                        } catch (NumberFormatException except) {
//                            JOptionPane.showMessageDialog(this, "Solo Ingrese numeros");
//                            p1 = null;
//                            return;
//                        }
                        float peso = pedirNumero("Ingrese el Peso (Km) de la arista");
//                        if (valor != null) {
//                            grafica.añadirArista(paraArista, posicionDelPunto, Integer.parseInt(valor));
//                            repaint();
//                            p1 = null;
//                        }
                        if (peso != -1) {
                            grafica.añadirArista(paraArista, posicionDelPunto, (int) peso);
                            repaint();
                            p1 = null;
                        }
                    }
                }
            }
        }
    }
    
    public void insertarArista(int vo,int vd,int peso){
        grafica.añadirArista(vo, vd,  peso);
        p1=null;
        repaint();
    }
    
    public String getVertice(int v){
        return grafica.getVertice(v);
    }

    public int pedirNumero(String nota) {
        boolean continuar = true;
        int dato = 0;
        while (continuar) {
            continuar = false;
            String s = JOptionPane.showInputDialog(nota);
            try {
                dato = Integer.parseInt(s);
            } catch (NumberFormatException sf) {
                continuar = true;
                if (s == null) {
                    return -1;
                } else {
                    JOptionPane.showMessageDialog(this, "Solo Ingrese Numeros!!");
                }
            }
        }
        if (dato < 0) {
            JOptionPane.showMessageDialog(this, "Solo ingrese valores positivos!!");
            dato = -1;
        }
        return dato;
    }

    public void bloquear(int vo, int vd, int peso) {
        grafica.bloqueArista(vo, vd, peso);
        repaint();
    }

    public void desbloquear(int vo, int vd, int peso) {
        grafica.desbloqueArista(vo, vd, peso);
        repaint();
    }

    public void eliminarVertice(int index) {
        grafica.eliminarVertice(index);
        repaint();
    }

    public void eliminarAristas(int vo, int vd, float peso) {
        grafica.eliminarAristas(vo, vd, peso);
        repaint();
    }

    public float caminoMasCorto(Stack<Integer> camino, int vo, int vd) {
        return grafica.caminoMasCorto(camino, vo, vd);
    }

    public int cantidadDeVertices() {
        return grafica.cantidadDeVertices();
    }

    public void eliminarAristas() {
        grafica.eliminarAristas();
    }

    public void guardar() {
        try {
            ObjectOutputStream fichero = new ObjectOutputStream(
                    new FileOutputStream("C:/Users/MARCO/Downloads/Programs/fichero.bsk"));
            fichero.writeObject(grafica);
            fichero.close();
        } catch (Exception e) {

        }
    }

    public void cargar() {
        try {
            ObjectInputStream fichero = new ObjectInputStream(
                    new FileInputStream("C:/Users/MARCO/Downloads/Programs/fichero.bsk"));
            //grafica=new Graficar();
            Graficar graficadora = new Graficar();
            graficadora = (Graficar) fichero.readObject();
            fichero.close();
            //grafica = graficadora;
            System.out.println(graficadora.coordenada.toString());
            repaint();
        } catch (Exception e) {

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
