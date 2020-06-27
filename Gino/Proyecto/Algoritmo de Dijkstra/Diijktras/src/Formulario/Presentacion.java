package Formulario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Stack;
import javax.swing.JOptionPane;

public class Presentacion extends javax.swing.JFrame {

    protected static Lienzo lienzo;

    public Presentacion() {
        initComponents();

        lienzo = new Lienzo();
        lienzo.setBounds(80, 50, 900, 550);
        //lienzo.setSize(500,500);
        lienzo.setBackground(new Color(19, 122, 134));
        //add(lienzo);
//        PanelPrincipal.add(lienzo,BorderLayout.CENTER);
        PanelPrincipal.add(lienzo);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelPrincipal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Algoritmo de Dijkstra");

        PanelPrincipal.setBackground(new java.awt.Color(0, 0, 51));
        PanelPrincipal.setForeground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Algoritmo de Diijktras");

        javax.swing.GroupLayout PanelPrincipalLayout = new javax.swing.GroupLayout(PanelPrincipal);
        PanelPrincipal.setLayout(PanelPrincipalLayout);
        PanelPrincipalLayout.setHorizontalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addGap(407, 407, 407)
                .addComponent(jLabel1)
                .addContainerGap(455, Short.MAX_VALUE))
        );
        PanelPrincipalLayout.setVerticalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(623, Short.MAX_VALUE))
        );

        jMenu1.setText("Operaciones");

        jMenuItem1.setText("Bloquear Arista");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem4.setText("Desbloquear Arista");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem7.setText("Insertar arista");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuItem6.setText("El Camino mas corto");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Eliminar");

        jMenuItem2.setText("Eliminar Vertice");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Eliminar Arista");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem5.setText("Eliminar Todas las Aristas");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        boolean continuar = true;
        int vo = 0;
        while (continuar) {
            try {
                continuar = false;
                vo = Integer.parseInt(JOptionPane.showInputDialog("Digite el vertice origen"));
            } catch (NumberFormatException sf) {
                continuar = true;
                JOptionPane.showMessageDialog(rootPane, "Solo digite numero del vertice");
            }
        }
        int vd = 0;
        continuar = true;
        while (continuar) {
            try {
                continuar = false;
                vo = Integer.parseInt(JOptionPane.showInputDialog("Digite el vertice origen"));
            } catch (NumberFormatException sf) {
                continuar = true;
                JOptionPane.showMessageDialog(rootPane, "Solo digite numero del vertice");
            }
        }

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    public boolean verticeValido(int v) {
        if (v == -1) {
            return false;
        }
        if (v > lienzo.cantidadDeVertices()) {
            JOptionPane.showMessageDialog(rootPane, "Vertice No Valido!!");
            return false;
        }
        return true;
    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        int vo = pedirNumero("Digite el vertice origen");
//        if (vo == -1 || !verticeValido(vo)) {
//            return;
//        }
        if (verticeValido(vo)) {
            int vd = pedirNumero("Digite el vertice de Destino");
            if (verticeValido(vd)) {
                int peso = pedirNumero("Digite el peso de la Arista");
                if (peso != -1) {
                    lienzo.bloquear(vo-1, vd-1, peso);
  
                }
            }
        }
//        int vd = pedirNumero("Digite el vertice de Destino");
//        if (vo == -1) {
//            return;
//        }
//        int peso = pedirNumero("Digite el peso de la arista");
//        if (vo == -1) {
//            return;
//        }
//        lienzo.bloquear(vo - 1, vd - 1, peso);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
                    JOptionPane.showMessageDialog(rootPane, "Solo Ingrese numeros");
                }
            }
        }
        if (dato < 0) {
            JOptionPane.showMessageDialog(this, "Solo ingrese valores positivos!!");
            dato = -1;
        }
        return dato;
    }


    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
//        int vo = pedirNumero("Digite el vertice origen");
//        if (vo == -1) {
//            return;
//        }
//        int vd = pedirNumero("Digite el vertice destino");
//        if (vo == -1) {
//            return;
//        }
//        int peso = pedirNumero("Digite el peso de la arista");
//        if (vo == -1) {
//            return;
//        }
//        lienzo.desbloquear(vo - 1, vd - 1, peso);
        int vo=pedirNumero("Digete el vertice de Partida");
        if (verticeValido(vo)) {
            int vd = pedirNumero("Digite el vertice de Destino");
            if (verticeValido(vd)) {
                int peso = pedirNumero("Digite el peso de la Arista");
                if (peso != -1) {
                    lienzo.desbloquear(vo-1, vd-1, peso);
                }
            }
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        int vo = pedirNumero("Numero del vertice a eliminar");
        if (vo == -1) {
            return;
        }
        lienzo.eliminarVertice(vo - 1);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        lienzo.eliminarAristas();
        repaint();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        repaint();
        int vo = pedirNumero("Digite el Vertice de partida");
        if (vo > this.lienzo.cantidadDeVertices()) {
            JOptionPane.showMessageDialog(rootPane, "Digite numero de vertices valido!!");
            return;
        }
        int vd = pedirNumero("Digite el Vertice de Destino");
        if (vd > this.lienzo.cantidadDeVertices()) {
            JOptionPane.showMessageDialog(rootPane, "Digite numero de vertices valido!!");
            return;
        }
        Stack<Integer> camino = new Stack<>();

        float peso = lienzo.caminoMasCorto(camino, vo - 1, vd - 1);
        if (peso != -1) {
            JOptionPane.showMessageDialog(rootPane, "La menor distancia de Recorrido de "
                    +lienzo.getVertice(vo-1) + " a " + lienzo.getVertice(vd-1)+" es : " + peso + " Km.");
        } else {
            repaint();
            JOptionPane.showMessageDialog(rootPane, "No es posible llegar al destino indicado");
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        //si es que no funciona correctamente
        //click derecho sobre el vertice destino y vertice origen
        int vo=pedirNumero("Digete el vertice de Partida");
        if (verticeValido(vo)) {
            int vd = pedirNumero("Digite el vertice de Destino");
            if (verticeValido(vd)) {
                int peso = pedirNumero("Digite el peso de la Arista");
                if (peso != -1) {
                    lienzo.insertarArista(vo-1, vd-1, peso);
                }
            }
        }        
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Presentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Presentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Presentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Presentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Presentacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelPrincipal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    // End of variables declaration//GEN-END:variables
}
