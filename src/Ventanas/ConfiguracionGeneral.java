package Ventanas;

import Clases.Configuracion;
import com.files.Document;
import com.ui.Label;


public class ConfiguracionGeneral extends javax.swing.JDialog {

    Label label = new Label();
    String rutaInforme = null;
    
    public ConfiguracionGeneral(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        interfaz();
        asignaciones();
    }

    private void interfaz(){
        setSize(765, 450);
        setTitle("Configuracion general");
        label.btnSecondary(btRuta, 125, 0);
    }
    private void asignaciones(){        
        colocarRutaEnTextField();
    }
    
    /* Configuracion Ruta */
    private void colocarRutaEnTextField(){
        rutaInforme = Configuracion.rutaInformes;
        jTextField1.setText(rutaInforme);
    }
    private void cambiarRuta(){
        
        String _nuevaRuta = Document.SelectDirectory(rutaInforme);
        
        if (_nuevaRuta != null){
            Configuracion.rutaInformes = _nuevaRuta;
        }        
        colocarRutaEnTextField();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnProyecto2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btRuta = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(750, 533));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnProyecto2.setBackground(new java.awt.Color(255, 255, 255));
        pnProyecto2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        pnProyecto2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(100, 100, 120));
        jLabel5.setText("Ruta actual donde se almacenan los informes");
        pnProyecto2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 20));

        btRuta.setText("Cambiar Ruta");
        btRuta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btRutaMouseClicked(evt);
            }
        });
        pnProyecto2.add(btRuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(515, 40, 125, -1));

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(64, 69, 78));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pnProyecto2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 445, 30));

        jPanel1.add(pnProyecto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 670, 200));

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 120, 215));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/setting.png"))); // NOI18N
        jLabel3.setText("Informes");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, 20));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btRutaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btRutaMouseClicked
        cambiarRuta();
    }//GEN-LAST:event_btRutaMouseClicked

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfiguracionGeneral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            ConfiguracionGeneral dialog = new ConfiguracionGeneral(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btRuta;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel pnProyecto2;
    // End of variables declaration//GEN-END:variables
}
