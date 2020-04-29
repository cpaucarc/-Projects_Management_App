package Ventanas;

import Clases.Control;
import com.database.Controller;
import com.ui.Label;
import com.ui.Table;
import com.ui.TableModel;
import com.ui.Texts;
import javax.swing.JOptionPane;

public class verDocentes extends javax.swing.JDialog {

    Label label = new Label();
    Controller control = Control.getController();
    public static TableModel model = new TableModel();
    public static String[] header = new String[]{"Id","DNI", "Docente","Genero", "Telefono", "Email", "Escuela"};
    public static boolean habilitarSeleccionar = false;
    
    public verDocentes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        label.btnSecondary(btAdd, 150, 0);
        Table.SetModel(tabla, model, header);
        Table.TableLight(tabla);
        llenarTabla();  
        seleccionar.setEnabled(habilitarSeleccionar);
    }

    private void llenarTabla(){
        String sql = Texts.ConcatLike(
                "select id, dni, docente, Genero,telefono, email, Escuela from v_docentes where ", 
                txBuscar.getText(),  header);
        control.FillTable(model, sql, 7);
        Table.HideColumn(tabla, 0);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menutabla = new javax.swing.JPopupMenu();
        editar = new javax.swing.JMenuItem();
        seleccionar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        btAdd = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        menutabla.setBackground(new java.awt.Color(255, 255, 255));

        editar.setBackground(new java.awt.Color(255, 204, 51));
        editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/edit.png"))); // NOI18N
        editar.setText("Editar");
        editar.setPreferredSize(new java.awt.Dimension(77, 30));
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });
        menutabla.add(editar);

        seleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/select.png"))); // NOI18N
        seleccionar.setText("Seleccionar");
        seleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarActionPerformed(evt);
            }
        });
        menutabla.add(seleccionar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btAdd.setText("Agregar docente");
        btAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btAddMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(64, 69, 78));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/search.png"))); // NOI18N
        jLabel1.setText("Buscar");

        txBuscar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txBuscar.setForeground(new java.awt.Color(64, 69, 78));
        txBuscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txBuscarKeyReleased(evt);
            }
        });

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
        tabla.setComponentPopupMenu(menutabla);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btAdd)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAddMouseClicked
        new Docentes(null, true).setVisible(true);
    }//GEN-LAST:event_btAddMouseClicked

    private void txBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txBuscarKeyReleased
        llenarTabla();
    }//GEN-LAST:event_txBuscarKeyReleased

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        if ( tabla.getSelectedRow() > -1){
            Docentes.id = Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
            new Docentes(null, true).setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Selecciona un elemento");
        }
    }//GEN-LAST:event_editarActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if ( evt.getClickCount() == 2 && seleccionar.isEnabled() ){
            Proyectos.idDocente = Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString()); 
            Proyectos.txDni.setText(tabla.getValueAt(tabla.getSelectedRow(), 1).toString());                 
            Proyectos.docente.setText(
                    tabla.getValueAt(tabla.getSelectedRow(), 2).toString() + " - "+ 
                    tabla.getValueAt(tabla.getSelectedRow(), 6).toString() );
            habilitarSeleccionar = false;
            this.dispose();
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void seleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarActionPerformed
        Proyectos.idDocente = Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
        Proyectos.txDni.setText(tabla.getValueAt(tabla.getSelectedRow(), 1).toString());                  
        Proyectos.docente.setText(
                tabla.getValueAt(tabla.getSelectedRow(), 2).toString() + " - "+ 
                tabla.getValueAt(tabla.getSelectedRow(), 6).toString() );
        habilitarSeleccionar = false;
        this.dispose();
    }//GEN-LAST:event_seleccionarActionPerformed

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
            java.util.logging.Logger.getLogger(verDocentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(verDocentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(verDocentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(verDocentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                verDocentes dialog = new verDocentes(new javax.swing.JFrame(), true);
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
    private javax.swing.JMenuItem editar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu menutabla;
    private javax.swing.JMenuItem seleccionar;
    protected static javax.swing.JTable tabla;
    public static javax.swing.JTextField txBuscar;
    // End of variables declaration//GEN-END:variables
}
