/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz2;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Norangel Marín & Jenderson Quintero.
 */
public class MostrarArbolAVL extends JPanel {

    private ArbolAVL arbol;
    public static final int d = 30;
    public static final int r = d / 2;
    public static final int a = 55;

    /**
     * Actualiza el arbol.
     * @param a ArbolAVL
     */
    public void setArbol(ArbolAVL a) {
        this.setBackground(Color.getHSBColor(255, 153, 102));
        this.setSize(912, 470);
        arbol = a;
        repaint();
    }

    /**
     * Método para pintar en el panel.
     * @param g Graphics
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        pintar(g, (getWidth()-50) / 2, 20, arbol.getRaiz());
    }

    /**
     * Iniciador de objetos que se pintará en el panel.
     * @param g Graphics
     * @param x int
     * @param y int
     * @param n Nodo
     */
    private void pintar(Graphics g, int x, int y, Nodo n) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3));

        if (n == null) {

        } else {
            int aExtra = n.getNodos(n) * (a / 2);
            g.setColor(Color.MAGENTA);
            g.drawOval(x, y, d, d);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Serif", Font.BOLD, 12));
            g.drawString(String.valueOf(n.getClave()), x + 7, y + 18);
            g2d.setStroke(new BasicStroke(2));

            if (n.getIzq() != null) {
                g.drawLine(x + r - 15, y + r, x - a - aExtra + r + 10, y + a + r - 10);

            }
            if (n.getDer() != null) {
                g.drawLine(x + r + 15, y + r, x + a + aExtra + r - 10, y + a + r - 10);
            }
            pintar(g, x - a - aExtra, y + a, n.getIzq());
            pintar(g, x + a + aExtra, y + a, n.getDer());
        }
    }
}
