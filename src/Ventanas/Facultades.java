package Ventanas;

import Clases.Control;
import com.alerts.Alert;
import com.database.Controller;
import com.ui.Label;
import com.ui.Table;
import com.ui.TableModel;
import com.ui.TextField;
import com.ui.Texts;

public class Facultades extends javax.swing.JDialog {

    Controller control = Control.getController();
    TableModel model = new TableModel();
    String[] header = new String[]{"Id", "Facultad"};
    Label label = new Label();
    int id = 0;
    public static int number = 0;
    
    public Facultades(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        interfaz();
        llenarTabla();
    }
    private void interfaz(){
        label.btnPrimary(btGuardar, 100, 0);
        label.btnSecondary(btEditar, 100, 0);
        Table.SetModel(tabla, model, header);
        ocultarErrores();
    }
    private void llenarTabla(){
        String sql = Texts.ConcatLike("select * from facultades where ", txBuscar.getText(), header);
        control.FillTable(model, sql, 2);
        Table.HideColumn(tabla, 0);
        Table.TableLight(tabla);
    }
    private void ocultarErrores(){
        errorFac.setVisible(false);
    }
    private boolean validacion(){
        if (!txFac.getText().isEmpty()) {
            return true;
        }else{
            errorFac.setVisible(true); txFac.grabFocus(); return false;
        }
    }
    private void seleccionar(){
        id = Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
        txFac.setText(tabla.getValueAt(tabla.getSelectedRow(), 1).toString());
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txFac = new javax.swing.JTextField();
        errorFac = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btEditar = new javax.swing.JLabel();
        btGuardar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(100, 100, 120));
        jLabel2.setText("Nombre de la facultad");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 250, 20));

        txFac.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txFac.setForeground(new java.awt.Color(64, 69, 78));
        txFac.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txFac.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txFacKeyTyped(evt);
            }
        });
        jPanel1.add(txFac, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 500, 30));

        errorFac.setForeground(new java.awt.Color(255, 51, 51));
        errorFac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/infoRed.png"))); // NOI18N
        errorFac.setText("Coloque aqui el nombre de la FACULTAD");
        jPanel1.add(errorFac, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, 20));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(64, 69, 78));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/search.png"))); // NOI18N
        jLabel1.setText("Buscar");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 70, 30));

        txBuscar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txBuscar.setForeground(new java.awt.Color(64, 69, 78));
        txBuscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txBuscarKeyReleased(evt);
            }
        });
        jPanel1.add(txBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 270, 30));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 500, 230));

        btEditar.setText("Editar");
        btEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btEditarMouseClicked(evt);
            }
        });
        jPanel1.add(btEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, 100, -1));

        btGuardar.setText("Guardar");
        btGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btGuardarMouseClicked(evt);
            }
        });
        jPanel1.add(btGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 140, 100, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txFacKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txFacKeyTyped
        TextField.Capitalize(evt, txFac);
    }//GEN-LAST:event_txFacKeyTyped

    private void txBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txBuscarKeyReleased
        llenarTabla();
    }//GEN-LAST:event_txBuscarKeyReleased

    private void btGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btGuardarMouseClicked
        ocultarErrores();
        if (validacion()) {
            String[] rsta = control.ReturnMultiData("call sp_facultad(0, '"+txFac.getText()+"')", 3);
            if (number == 1 ){
                control.FillCombo(Escuelas.cbFac, "select * from facultades order by facultad", 2);
                this.dispose();
                number = 0;
            }else{
                Alert.showAlertH(rsta[2], rsta[0], rsta[1]);
                if (rsta[1].equals("success")) {
                    txFac.setText(null);txFac.grabFocus();
                }
                llenarTabla();
            }
        }
    }//GEN-LAST:event_btGuardarMouseClicked

    private void btEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btEditarMouseClicked
        ocultarErrores();
        if (validacion() && id > 0) {
            String[] rsta = control.ReturnMultiData("call sp_facultad("+id+", '"+txFac.getText()+"')", 3);
            if (number == 1 ){
                control.FillCombo(Escuelas.cbFac, "select * from facultades order by facultad", 2);
                this.dispose();
                number = 0;
            }else{
                Alert.showAlertH(rsta[2], rsta[0], rsta[1]);
                if (rsta[1].equals("success")) {
                    txFac.setText(null);txFac.grabFocus();
                }
                llenarTabla();
            }
        }
    }//GEN-LAST:event_btEditarMouseClicked

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        seleccionar();
    }//GEN-LAST:event_tablaMouseClicked

    /**
     * @param args the command line arguments
     */
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Facultades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Facultades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Facultades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Facultades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Facultades dialog = new Facultades(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btEditar;
    private javax.swing.JLabel btGuardar;
    private javax.swing.JLabel errorFac;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txBuscar;
    private javax.swing.JTextField txFac;
    // End of variables declaration//GEN-END:variables
}
