package Ventanas;

import Clases.Control;
import com.alerts.Message;
import com.database.Controller;
import com.ui.Label;
import com.ui.Table;
import com.ui.TableModel;

public class Certificados extends javax.swing.JFrame {

    Controller control = Control.getController();
    Label label = new Label();
    TableModel modelSinCertificado = new TableModel();
    TableModel modelConCertificado = new TableModel();
    int idProyecto = 0;
    
    public Certificados() {
        initComponents();
        interfaz();
    }

    private void interfaz() {
        setExtendedState(6);
        setTitle("Entrega de proyectos");
        label.btnPrimary(btAgregar, 150, 13);
        control.FillCombo(cbProy, "select proyecto from v_proyectos where fase = 'Finalizado' order by proyecto;", 1);
        Table.SetModel(tablaSinCertificado, modelSinCertificado, new String[]{"Id", "Dni", "Docente", "Escuela", "Cargo"});
        Table.SetModel(tablaConCertificado, modelConCertificado, new String[]{"Id", "Dni", "Docente" ,"Genero", "Escuela", "Situacion", "Fecha", "Hora"});
        Table.TableLight(tablaConCertificado);
        Table.TableLight(tablaSinCertificado);
        Table.HideColumn(tablaSinCertificado, 0);
        Table.HideColumn(tablaConCertificado, 0);
    }
    private void llenarTablaSinCertificado(){
        control.FillTable(modelSinCertificado, "select "
                + "Id, Dni, Docente, Escuela, Cargo "
                + "from v_docente_proyecto "
                + "where id not in (select iddocente from docente_certificado where idproyecto = "+idProyecto+") and IdProyecto = "+idProyecto+";", 5);
    }
    private void llenarTablaConCertificado(){
        control.FillTable(modelConCertificado, "select "
                + "Id, Dni, Docente,Genero, Escuela, Situacion, Fecha, Hora "
                + "from v_docente_certificado where idproyecto = "+idProyecto+";", 8);
    }
    private void entregarCertificado(int idDocente){
        if (idProyecto != 0) {
            control.ExecuteQuery("insert into docente_certificado values (null, curdate(), curtime(), "+idProyecto+", "+idDocente+");");
            llenarTablaSinCertificado();
            llenarTablaConCertificado();
        }
    }
    private String obtenerValorTablaSinCertificado(int column){
        return tablaSinCertificado.getValueAt(tablaSinCertificado.getSelectedRow(), column).toString();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnProyecto = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbProy = new javax.swing.JComboBox<>();
        pnSinCertificado = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaSinCertificado = new javax.swing.JTable();
        btAgregar = new javax.swing.JLabel();
        pnConCertificado = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaConCertificado = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnProyecto.setBackground(new java.awt.Color(255, 255, 255));
        pnProyecto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Datos del proyecto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 14), new java.awt.Color(100, 100, 120))); // NOI18N
        pnProyecto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(100, 100, 120));
        jLabel3.setText("Nombre del proyecto");
        pnProyecto.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 160, 30));

        cbProy.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cbProy.setForeground(new java.awt.Color(64, 69, 78));
        cbProy.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbProyPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        pnProyecto.add(cbProy, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 1046, 30));

        jPanel1.add(pnProyecto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 1286, 100));

        pnSinCertificado.setBackground(new java.awt.Color(255, 255, 255));
        pnSinCertificado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Docentes que aun no reciben su certificado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 14), new java.awt.Color(100, 100, 120))); // NOI18N
        pnSinCertificado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        tablaSinCertificado.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tablaSinCertificado);

        pnSinCertificado.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 1016, 193));

        btAgregar.setText("Entregar Certificado");
        btAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btAgregarMouseClicked(evt);
            }
        });
        pnSinCertificado.add(btAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1096, 180, 150, 30));

        jPanel1.add(pnSinCertificado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 1286, 250));

        pnConCertificado.setBackground(new java.awt.Color(255, 255, 255));
        pnConCertificado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Docentes que recibieron sus certificados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 14), new java.awt.Color(100, 100, 120))); // NOI18N
        pnConCertificado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        tablaConCertificado.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaConCertificado);

        pnConCertificado.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 1206, 193));

        jPanel1.add(pnConCertificado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 1286, 250));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbProyPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbProyPopupMenuWillBecomeInvisible
        idProyecto = Integer.parseInt(
                control.ReturnData("select id from proyectos where proyecto = '"+cbProy.getSelectedItem().toString()+"';", 1)
        );
        llenarTablaSinCertificado();
        llenarTablaConCertificado();
    }//GEN-LAST:event_cbProyPopupMenuWillBecomeInvisible

    private void btAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAgregarMouseClicked
        if (tablaSinCertificado.getSelectedRow() > -1) {
            
            int _idDocente = Integer.parseInt(obtenerValorTablaSinCertificado(0));
            String _docente = obtenerValorTablaSinCertificado(2);
            
            boolean rsta = Message.Confirm("¿Desea entregar certificado a "+_docente+"?");
            if ( rsta ){
                entregarCertificado(_idDocente);
            }
            
        }else{
            Message.Show("Seleccione el docente que recibira el certificado", "warning");
        }
    }//GEN-LAST:event_btAgregarMouseClicked

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
            java.util.logging.Logger.getLogger(Certificados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Certificados().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btAgregar;
    public static javax.swing.JComboBox<String> cbProy;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnConCertificado;
    private javax.swing.JPanel pnProyecto;
    private javax.swing.JPanel pnSinCertificado;
    private javax.swing.JTable tablaConCertificado;
    private javax.swing.JTable tablaSinCertificado;
    // End of variables declaration//GEN-END:variables
}
