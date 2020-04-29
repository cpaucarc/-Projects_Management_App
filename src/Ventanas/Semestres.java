package Ventanas;

import Clases.Control;
import com.alerts.Alert;
import com.database.Controller;
import com.ui.DateChooser;
import com.ui.Label;
import com.ui.Table;
import com.ui.TableModel;
import com.ui.TextField;
import com.ui.Texts;
import javax.swing.JLabel;


public class Semestres extends javax.swing.JDialog {

    JLabel[] errores;
    Controller control = Control.getController();
    TableModel model = new TableModel();
    String[] header = new String[]{"Id", "Semestre", "Inicio", "Fin"};
    Label label = new Label();
    int id = 0;
    
    public Semestres(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        errores = new JLabel[]{ errorSem, errorInicio, errorFin };
        ocultarErrores();
        Table.SetModel(tabla, model, header);
        llenarTabla();
        label.btnPrimary(btGuardar, 100, 0);
        label.btnSecondary(btEditar, 100, 0);
        setTitle("Semestres");
    }
    
    private void ocultarErrores(){
        for ( JLabel error : errores ) {
            error.setVisible(false);
        }
    }
    private void llenarTabla(){
        String sql = Texts.ConcatLike("select * from semestres where ", txBuscar.getText(), header);
        control.FillTable(model, sql, 4);
        Table.HideColumn(tabla, 0);
        Table.TableLight(tabla);
    }
    
    private boolean validacion(){
        if (!txSem.getText().isEmpty()) {
            if ( inicio.getDate() != null ) {
                if ( fin.getDate() != null ) {
                    return true;
                }else { errorFin.setVisible(true); fin.grabFocus(); return false; }
            }else { errorInicio.setVisible(true); inicio.grabFocus(); return false; }
        }else{ errorSem.setVisible(true); txSem.grabFocus(); return false; }
    }
    private void seleccionar(){
        txSem.setText(tabla.getValueAt(tabla.getSelectedRow(), 1).toString());
        inicio.setDate(DateChooser.getDate(tabla.getValueAt(tabla.getSelectedRow(), 2).toString()));
        fin.setDate(DateChooser.getDate(tabla.getValueAt(tabla.getSelectedRow(), 3).toString()));
    }
    private void limpiar(){
        txSem.setText(null);
        inicio.setDate(null);
        fin.setDate(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txBuscar = new javax.swing.JTextField();
        errorSem = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        errorInicio = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        errorFin = new javax.swing.JLabel();
        fin = new com.toedter.calendar.JDateChooser();
        inicio = new com.toedter.calendar.JDateChooser();
        txSem = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btGuardar = new javax.swing.JLabel();
        btEditar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(590, 620));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(100, 100, 120));
        jLabel2.setText("Nombre del semestre");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 250, 20));

        txBuscar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txBuscar.setForeground(new java.awt.Color(64, 69, 78));
        txBuscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txBuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txBuscarKeyTyped(evt);
            }
        });
        jPanel1.add(txBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, 270, 30));

        errorSem.setForeground(new java.awt.Color(255, 51, 51));
        errorSem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/infoRed.png"))); // NOI18N
        errorSem.setText("Coloque aqui el nombre del SEMESTRE");
        jPanel1.add(errorSem, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, 20));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 500, 230));

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(100, 100, 120));
        jLabel4.setText("Inicio del semestre");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 255, 20));

        errorInicio.setForeground(new java.awt.Color(255, 51, 51));
        errorInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/infoRed.png"))); // NOI18N
        errorInicio.setText("Seleccione la fecha de INICIO del semestre\n");
        jPanel1.add(errorInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 255, 20));

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(100, 100, 120));
        jLabel5.setText("Fin del semestre");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(315, 110, 225, 20));

        errorFin.setForeground(new java.awt.Color(255, 51, 51));
        errorFin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/infoRed.png"))); // NOI18N
        errorFin.setText("Seleccione la fecha de FIN del semestre");
        jPanel1.add(errorFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(315, 160, 225, 20));

        fin.setForeground(new java.awt.Color(255, 0, 0));
        fin.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jPanel1.add(fin, new org.netbeans.lib.awtextra.AbsoluteConstraints(315, 130, 225, 30));

        inicio.setBackground(new java.awt.Color(255, 255, 255));
        inicio.setForeground(new java.awt.Color(64, 69, 78));
        inicio.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jPanel1.add(inicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 225, 30));

        txSem.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txSem.setForeground(new java.awt.Color(64, 69, 78));
        txSem.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(txSem, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 500, 30));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(64, 69, 78));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/search.png"))); // NOI18N
        jLabel1.setText("Buscar");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 70, 30));

        btGuardar.setText("Guardar");
        btGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btGuardarMouseClicked(evt);
            }
        });
        jPanel1.add(btGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 200, 100, 30));

        btEditar.setText("Editar");
        btEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btEditarMouseClicked(evt);
            }
        });
        jPanel1.add(btEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 200, 100, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btGuardarMouseClicked
        ocultarErrores();
        if (validacion()) {
            String _sem = txSem.getText();
            String _ini = DateChooser.AMD(inicio.getDate());
            String _fin = DateChooser.AMD(fin.getDate());
            String[] rsta = control.ReturnMultiData("call sp_semestre(0, '"+_sem+"', '"+_ini+"', '"+_fin+"');", 3);
            Alert.showAlertH(rsta[2], rsta[0], rsta[1]);
            llenarTabla();
            if (rsta[1].equals("success"))
                limpiar();
        }
    }//GEN-LAST:event_btGuardarMouseClicked

    private void txBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txBuscarKeyReleased
        llenarTabla();
    }//GEN-LAST:event_txBuscarKeyReleased

    private void txBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txBuscarKeyTyped
        llenarTabla();
    }//GEN-LAST:event_txBuscarKeyTyped

    private void btEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btEditarMouseClicked
        ocultarErrores();
        if (validacion() && id > 0) {
            String _sem = txSem.getText();
            String _ini = DateChooser.AMD(inicio.getDate());
            String _fin = DateChooser.AMD(fin.getDate());
            String[] rsta = control.ReturnMultiData("call sp_semestre("+id+", '"+_sem+"', '"+_ini+"', '"+_fin+"');", 3);
            Alert.showAlertH(rsta[2], rsta[0], rsta[1]);
            llenarTabla();
            if (rsta[1].equals("success"))
                limpiar();
        }
    }//GEN-LAST:event_btEditarMouseClicked

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        id = Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
        seleccionar();
    }//GEN-LAST:event_tablaMouseClicked

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
            java.util.logging.Logger.getLogger(Semestres.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Semestres.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Semestres.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Semestres.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Semestres dialog = new Semestres(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel errorFin;
    private javax.swing.JLabel errorInicio;
    private javax.swing.JLabel errorSem;
    private com.toedter.calendar.JDateChooser fin;
    private com.toedter.calendar.JDateChooser inicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txBuscar;
    private javax.swing.JTextField txSem;
    // End of variables declaration//GEN-END:variables
}
