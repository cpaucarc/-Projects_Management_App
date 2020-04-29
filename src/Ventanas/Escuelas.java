package Ventanas;

import Clases.Control;
import com.alerts.Alert;
import com.database.Controller;
import com.ui.Label;
import com.ui.Table;
import com.ui.TableModel;
import com.ui.TextField;
import com.ui.Texts;

public class Escuelas extends javax.swing.JDialog {

    Controller control = Control.getController();
    TableModel model = new TableModel();
    String[] header = new String[]{"Id", "Escuela", "Facultad"};
    Label label = new Label();
    int id = 0;
    
    public Escuelas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        interfaz();
        llenarTabla();
        ocultarErrores();
    }
    private void ocultarErrores(){
        errorEsc.setVisible(false);
        errorFac.setVisible(false);
    }
    private boolean validacion(){
        if (!txEsc.getText().isEmpty()){
            if (cbFac.getSelectedIndex()>-1) return true;
            else{ errorFac.setVisible(true); cbFac.grabFocus(); return false;}
        }else{ errorEsc.setVisible(true); txEsc.grabFocus(); return false;}
    }
    private void interfaz(){
        setSize(570,590);
        setLocationRelativeTo(null);
        label.btnPrimary(btGuardar, 100, 0);
        label.btnSecondary(btEditar, 100, 0);
        Label.buttonIcon(btAdd, null, null, "/Imagenes/Iconos/add.png", "/Imagenes/Iconos/addFilled.png", 0);
        Table.SetModel(tabla, model, header);
        control.FillCombo(cbFac, "select * from facultades order by facultad", 2);
    }
    private void llenarTabla(){
        String sql = Texts.ConcatLike("select * from v_escuelas where ", txBuscar.getText(), header);
        control.FillTable(model, sql, 3);
        Table.HideColumn(tabla, 0);
        Table.TableLight(tabla);
    }
    private void seleccionar(){
        id = Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
        txEsc.setText(tabla.getValueAt(tabla.getSelectedRow(), 1).toString());
        cbFac.setSelectedItem(tabla.getValueAt(tabla.getSelectedRow(), 2).toString());
    }
    private void limpiar(){
        txEsc.setText(null);
        cbFac.setSelectedIndex(-1);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cbFac = new javax.swing.JComboBox<>();
        errorFac = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        errorEsc = new javax.swing.JLabel();
        txEsc = new javax.swing.JTextField();
        btAdd = new javax.swing.JLabel();
        btEditar = new javax.swing.JLabel();
        btGuardar = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(450, 400));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(100, 100, 120));
        jLabel8.setText("Facultad");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 250, 20));

        cbFac.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cbFac.setForeground(new java.awt.Color(64, 69, 78));
        jPanel1.add(cbFac, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 420, 30));

        errorFac.setForeground(new java.awt.Color(255, 51, 51));
        errorFac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/infoRed.png"))); // NOI18N
        errorFac.setText("Seleccione la FACULTAD");
        jPanel1.add(errorFac, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, 20));

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(90, 90, 100));
        jLabel7.setText("Escuela");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 250, 20));

        errorEsc.setForeground(new java.awt.Color(255, 51, 51));
        errorEsc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/infoRed.png"))); // NOI18N
        errorEsc.setText("Seleccione la ESCUELA");
        jPanel1.add(errorEsc, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, 20));

        txEsc.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txEsc.setForeground(new java.awt.Color(64, 69, 78));
        txEsc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txEsc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txEscKeyTyped(evt);
            }
        });
        jPanel1.add(txEsc, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 450, 30));

        btAdd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/add.png"))); // NOI18N
        btAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btAddMouseClicked(evt);
            }
        });
        jPanel1.add(btAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 30, 30));

        btEditar.setText("Editar");
        btEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btEditarMouseClicked(evt);
            }
        });
        jPanel1.add(btEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, 100, -1));

        btGuardar.setText("Guardar");
        btGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btGuardarMouseClicked(evt);
            }
        });
        jPanel1.add(btGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 200, 100, 30));

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(64, 69, 78));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/search.png"))); // NOI18N
        jLabel2.setText("Buscar");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 70, 30));

        txBuscar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txBuscar.setForeground(new java.awt.Color(64, 69, 78));
        txBuscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txBuscarKeyReleased(evt);
            }
        });
        jPanel1.add(txBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, 270, 30));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 300, 500, 230));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txEscKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txEscKeyTyped
        TextField.Capitalize(evt, txEsc);
    }//GEN-LAST:event_txEscKeyTyped

    private void btEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btEditarMouseClicked
        ocultarErrores();
        if (validacion() && id > 0) {
            String[] rsta = control.ReturnMultiData("call sp_escuela("+id+", '"+txEsc.getText()+"', '"+cbFac.getSelectedItem().toString()+"')", 3);
            Alert.showAlertH(rsta[2], rsta[0], rsta[1]);
            if (rsta[1].equals("success")) {
                limpiar();
            }
            llenarTabla();
        }
    }//GEN-LAST:event_btEditarMouseClicked

    private void btGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btGuardarMouseClicked
        ocultarErrores();
        if (validacion()) {
            String[] rsta = control.ReturnMultiData("call sp_escuela(0, '"+txEsc.getText()+"', '"+cbFac.getSelectedItem().toString()+"')", 3);
            Alert.showAlertH(rsta[2], rsta[0], rsta[1]);
            if (rsta[1].equals("success")) {
                limpiar();
            }
            llenarTabla();
        }
    }//GEN-LAST:event_btGuardarMouseClicked

    private void txBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txBuscarKeyReleased
        llenarTabla();
    }//GEN-LAST:event_txBuscarKeyReleased

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        seleccionar();
    }//GEN-LAST:event_tablaMouseClicked

    private void btAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAddMouseClicked
        Facultades.number = 1;
        new Facultades(null, true).setVisible(true);
    }//GEN-LAST:event_btAddMouseClicked

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
            java.util.logging.Logger.getLogger(Escuelas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Escuelas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Escuelas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Escuelas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Escuelas dialog = new Escuelas(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel btAdd;
    private javax.swing.JLabel btEditar;
    private javax.swing.JLabel btGuardar;
    public static javax.swing.JComboBox<String> cbFac;
    private javax.swing.JLabel errorEsc;
    private javax.swing.JLabel errorFac;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txBuscar;
    private javax.swing.JTextField txEsc;
    // End of variables declaration//GEN-END:variables
}
