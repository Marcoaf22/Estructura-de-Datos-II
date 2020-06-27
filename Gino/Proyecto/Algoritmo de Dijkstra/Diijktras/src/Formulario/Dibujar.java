package Formulario;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.util.List;
import java.util.Stack;

public class Dibujar {

    private static final int ARR_SIZE = 6;

    public static void dibujarVertice(Graphics g, Point p1, String nombre) {
        Graphics2D g2 = (Graphics2D) g;
        g2.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        g2.setFont(new Font("Bold", Font.BOLD, 13));
        g2.setColor(new Color(202, 118, 58));
        g.fillOval(p1.x - 10 / 2, p1.y - 10 / 2, 10, 10);
        g2.setColor(Color.BLACK);
        g.drawString(nombre, p1.x + 10, p1.y + 5);
    }

    public static void dibujarFlecha(Graphics g1, int x1, int y1, int x2, int y2, boolean habilitado) {
        Graphics2D g = (Graphics2D) g1.create();
        //Graphics2D g=(Graphics2D)g1;
        g.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx * dx + dy * dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);
        if (!habilitado) {
            Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
            g.setStroke(dashed);
        }
        //Draw horizontal arrow starting in (0, 0)
        g.drawLine(0, 0, len, 0);
        g.setColor(Color.YELLOW);
        g.fillPolygon(new int[]{len, len - ARR_SIZE, len - ARR_SIZE, len},
                new int[]{0, -ARR_SIZE, ARR_SIZE, 0}, 4);
    }

    public static void dibujarArista(Graphics g, Point p1, Point p2, String valor, boolean estaHabilitada, int distancia, Color color) {
        int x1 = p1.x;
        int y1 = p1.y;
        int x2 = p2.x;
        int y2 = p2.y;
        int a = 0;
        int b = 0;
        Point p2f = new Point(x2, y2);
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("Bold", Font.BOLD, 15));
        g2.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        int puntosX[] = new int[3];
        int puntosY[] = new int[3];
        puntosX[0] = p1.x;
        puntosY[0] = p1.y;
        if (x1 < x2 && y1 < y2) {  //lado 4
            obtenerCoordenadas(puntosX, puntosY, p1, p2, 4, distancia);
            //g2.drawString(valor, puntosX[2] - Math.abs((puntosX[1] - puntosX[2]) / 2), puntosY[2] - Math.abs((puntosY[1] - puntosY[2]) / 2));
            reducir(new Point(puntosX[2], puntosY[2]), p2f);
            a = puntosX[2] - Math.abs((puntosX[1] - puntosX[2]) / 2);
            b = puntosY[2] - Math.abs((puntosY[1] - puntosY[2]) / 2);
            // x2 = puntosX[2] - Math.abs((puntosX[1] - puntosX[2]) / 2);
            // y2 = puntosY[2] - Math.abs((puntosY[1] - puntosY[2]) / 2);
        }
        if (x1 > x2 && y1 < y2) {  //lado 3
            obtenerCoordenadas(puntosX, puntosY, p1, p2, 3, distancia);
            //g2.drawString(valor, puntosX[1] - Math.abs((puntosX[1] - puntosX[2]) / 2), puntosY[2] - Math.abs((puntosY[1] - puntosY[2]) / 2));
            reducir(new Point(puntosX[2], puntosY[2]), p2f);
            a = puntosX[1] - Math.abs((puntosX[1] - puntosX[2]) / 2);
            b = puntosY[2] - Math.abs((puntosY[1] - puntosY[2]) / 2);
        }
        if (x1 > x2 && y1 > y2) {  //lado 2
            obtenerCoordenadas(puntosX, puntosY, p1, p2, 2, distancia);
            //g2.drawString(valor, puntosX[1] - Math.abs((puntosX[1] - puntosX[2]) / 2), puntosY[1] - Math.abs((puntosY[1] - puntosY[2]) / 2));
            reducir(new Point(puntosX[2], puntosY[2]), p2f);
            a = puntosX[1] - Math.abs((puntosX[1] - puntosX[2]) / 2);
            b = puntosY[1] - Math.abs((puntosY[1] - puntosY[2]) / 2);
        }
        if (x1 <= x2 && y1 >= y2) { //lado 1
            obtenerCoordenadas(puntosX, puntosY, p1, p2, 1, distancia);
            // g2.drawString(valor, puntosX[2] - Math.abs((puntosX[1] - puntosX[2]) / 2), puntosY[1] - Math.abs((puntosY[1] - puntosY[2]) / 2));
            a = puntosX[2] - Math.abs((puntosX[1] - puntosX[2]) / 2);
            b = puntosY[1] - Math.abs((puntosY[1] - puntosY[2]) / 2);
            reducir(new Point(puntosX[2], puntosY[2]), p2f);
        }
        g2.setColor(color);
        drawDashedLine(g2, puntosX, puntosY, estaHabilitada);
        dibujarFlecha(g2, puntosX[2], puntosY[2], p2f.x, p2f.y, estaHabilitada);
        g2.setColor(new Color(212, 173, 9));
        g2.drawString(valor, a, b);
    }

    public static void drawDashedLine(Graphics g, int[] x, int[] y, boolean habilitada) {
        Graphics2D g2d = (Graphics2D) g.create();
        if (!habilitada) {
            Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{8}, 0);
            g2d.setStroke(dashed);
            g2d.drawPolyline(x, y, 3);
            g2d.dispose();
        } else {
            g2d.drawPolyline(x, y, 3);
        }
    }

    public static double angulo(Point p1, Point p2) {
        int y = p2.y - p1.y;
        int x = p2.x - p1.x;
        double m = x == 0 ? 0 : (double) y / x;
        double angulo = Math.toDegrees(Math.atan(m));
        if (angulo < 0) {
            return angulo;
        }
        return angulo;
    }

    protected static void reducir(Point p1, Point p2) {
        int angulo = (int) Math.abs(Dibujar.angulo(p1, p2));
        double g = Math.toRadians(angulo);
        int masy = (int) (Math.sin(g) * 8);
        int masx = (int) (Math.cos(g) * 8);
        if (p1.x < p2.x && p1.y < p2.y) {  //lado 4
            p2.x -= masx;
            p2.y -= masy;
        }
        if (p1.x > p2.x && p1.y < p2.y) {  //lado 3
            p2.x += masx;
            p2.y -= masy;
        }
        if (p1.x > p2.x && p1.y > p2.y) {  //lado 2
            p2.x += masx;
            p2.y += masy;
        }
        if (p1.x <= p2.x && p1.y >= p2.y) { //lado 1
            p2.x -= masx;
            p2.y += masy;
        }
    }

    private static void obtenerCoordenadas(int[] puntosX, int[] puntosY, Point p1, Point p2, int lado, int distancia) {
        int angulo = (int) Math.abs(Dibujar.angulo(p1, p2));
        double g = Math.toRadians(angulo);
        int masy = (int) (Math.cos(g) * distancia);
        int masx = (int) (Math.sin(g) * distancia);
        int masx1;
        int masy1;
        int masx2;
        int masy2;
        switch (lado) {
            case 1:
                masy1 = p1.y - masy;
                masx1 = p1.x - masx;
                masx2 = p2.x - masx;
                masy2 = p2.y - masy;
                puntosX[1] = masx1 + Math.abs((masx1 - masx2) / 4);
                puntosY[1] = masy1 - Math.abs((masy1 - masy2) / 4);
                puntosX[2] = masx2 - Math.abs((masx1 - masx2) / 4);
                puntosY[2] = masy2 + Math.abs((masy1 - masy2) / 4);
                break;
            case 2:
                masy1 = p1.y + masy;
                masx1 = p1.x - masx;
                masx2 = p2.x - masx;
                masy2 = p2.y + masy;
                puntosX[1] = masx1 - Math.abs((masx1 - masx2) / 4);
                puntosY[1] = masy1 - Math.abs((masy1 - masy2) / 4);
                puntosX[2] = masx2 + Math.abs((masx1 - masx2) / 4);
                puntosY[2] = masy2 + Math.abs((masy1 - masy2) / 4);
                break;
            case 3:
                masy1 = p1.y + masy;
                masx1 = p1.x + masx;
                masx2 = p2.x + masx;
                masy2 = p2.y + masy;
                puntosX[1] = masx1 - Math.abs((masx1 - masx2) / 4);
                puntosY[1] = masy1 + Math.abs((masy1 - masy2) / 4);
                puntosX[2] = masx2 + Math.abs((masx1 - masx2) / 4);
                puntosY[2] = masy2 - Math.abs((masy1 - masy2) / 4);
                break;
            case 4:
                masy1 = p1.y - masy;
                masx1 = p1.x + masx;
                masx2 = p2.x + masx;
                masy2 = p2.y - masy;
                puntosX[1] = masx1 + Math.abs((masx1 - masx2) / 4);
                puntosY[1] = masy1 + Math.abs((masy1 - masy2) / 4);
                puntosX[2] = masx2 - Math.abs((masx1 - masx2) / 4);
                puntosY[2] = masy2 - Math.abs((masy1 - masy2) / 4);
        }
    }

    public static void pintarCamino(Stack<Integer> camino, List<Point> coordenada, List<Integer> distancia, Color color) {
        Graphics2D g2 = (Graphics2D) Presentacion.lienzo.getGraphics();
        int a = camino.pop();
        int x1 = coordenada.get(a).x;
        int y1 = coordenada.get(a).y;
        Point p1 = new Point(x1, y1);
        int i = 0;
        int dist = 0;
        do {
            int b = camino.pop();
            int x2 = coordenada.get(b).x;
            int y2 = coordenada.get(b).y;
            Point p2 = new Point(x2, y2);
            dist = distancia.get(i);
            Dibujar.dibujarArista(g2, p1, p2, "", true, dist, color);
            a = b;
            p1 = new Point(x2, y2);
            i++;
        } while (!camino.isEmpty());
    }

    public static void main(String[] args) {
        Dibujar a = new Dibujar();
        System.out.println(Dibujar.angulo(new Point(200, 300), new Point(400, 300)));
    }
}
