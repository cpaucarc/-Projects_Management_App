package Ventanas;

import Clases.Configuracion;
import Clases.Control;
import com.alerts.Message;
import com.database.Controller;
import com.execute.Open;
import com.files.Document;
import com.ui.Label;
import com.ui.Table;
import com.ui.TableModel;
import java.awt.Color;
import java.io.File;

public class Informes extends javax.swing.JFrame {

    Controller control = Control.getController();
    TableModel model = new TableModel();
    Label label = new Label();
    String currentPath = "";
    int idProyecto = 0;
    File informe = null;
    
    public Informes() {
        initComponents();
        interfaz();
        ocultarErrores();
    }

    private void interfaz(){
        setExtendedState(6);
        setTitle("Presentacion de informes");
        Label.buttonIcon(btLimpiar, null, null, "/Imagenes/Iconos/clear1.png", "/Imagenes/Iconos/clear2.png", 0);
        label.btnNoBorder(lbSeleccionar, new Color(206, 75, 128), 0);
        label.btnPrimary(btGuardar, 150, 0);
        llenarComboProyectos();
        Table.SetModel(tabla, model, new String[]{"Id","Informe", "Fecha", "Hora", "Dni", "Docente", "Escuela", "Proyecto"});
        Table.TableLight(tabla);
        Table.WidthColumn(tabla, 180, 5, 6);
        Table.WidthColumn(tabla, 220, 7 );
        Table.HideColumn(tabla, 0);
        llenarTabla_Todo();
        colocarRuta();
    }
    private void ocultarErrores(){
        errorProy.setVisible(false);
        errorDocente.setVisible(false);
        errorPdf.setVisible(false);
        errorNumero.setVisible(false);
    }
    private boolean validacion(){
        if (cbProy.getSelectedIndex() > -1) {
            if (cbDocente.getSelectedIndex() > -1) {
                if (!txNombreOriginal.getText().isEmpty() || informe != null){
                    if (!txNombreNuevo.getText().isEmpty()){
                        return true;
                    }else {errorNumero.setVisible(true); txNombreNuevo.grabFocus(); return false;}
                }else {errorPdf.setVisible(true); lbSeleccionar.grabFocus(); return false;}
            }else {errorDocente.setVisible(true); cbDocente.grabFocus(); return false;}
        }else{ errorProy.setVisible(true); cbProy.grabFocus(); return false; }
    }
    /* Proyecto */
    private void seleccionarDocumento(){
        informe = Document.SelectDocument(currentPath);
        if (informe != null){
            txNombreOriginal.setText(informe.getName());
        }        
    }
    private void llenarComboProyectos(){
        if (cbFase.getSelectedIndex() > -1) {
            control.FillCombo(cbProy, "select Proyecto from v_proyectos where fase = '"+cbFase.getSelectedItem().toString()+"' order by Proyecto", 1);
        }
    }
    private void llenarTabla_Todo(){
        control.FillTable(model, "select IdProyecto, Informe, Fecha, Hora, Dni, Docente, Escuela, Proyecto from v_informes order by id desc;", 8);
    }
    private void llenarTabla_Proyecto(){
        control.FillTable(model, "select IdProyecto, Informe, Fecha, Hora, Dni, Docente, Escuela, Proyecto from v_informes where proyecto = '"+cbProy.getSelectedItem().toString()+"' order by id desc;", 8);
    }
    private void crearCarpetaProyecto(){
        File _carpetaProyecto = new File(Configuracion.rutaInformes + idProyecto +"\\");
        if(!_carpetaProyecto.exists()){
            _carpetaProyecto.mkdir();
        }
    }
    private String copiarInformeACarpeta(){
        String _ruta = Configuracion.rutaInformes + idProyecto +"\\"+ txNombreNuevo.getText()+".pdf";
        if (new File(_ruta).exists()) {
            return "Ya existe un archivo con este nombre";                       
        }else{            
            return Document.Copy(informe, _ruta) ? "Ok" : "Hubo un error al copiar el archivo";
        }        
                         
    }
    private void guardarInforme(){
        crearCarpetaProyecto();
        String rsta = copiarInformeACarpeta();
        if (rsta.equals("Ok")) {
            String numero = txNombreNuevo.getText();
            control.ExecuteQuery("insert into informes values "
                    + "(null, '"+numero.replace(" ", "_")+"', curdate(), curtime(), "
                    + "(select iddocente from v_docentes where docente = '"+cbDocente.getSelectedItem().toString()+"'),"
                    + idProyecto + ");");
            Message.Show("El informe se guardo con exito", "question");
        }else{
            Message.Show(rsta, "error");
        }
    }
    private void limpiarTodo(){
        cbFase.setSelectedIndex(2);
        llenarComboProyectos();
        cbProy.setSelectedIndex(-1);
        cbDocente.setSelectedIndex(-1);
        informe = null;
        txNombreOriginal.setText("");
        txNombreNuevo.setText("");
        txRutaNombre.setText("");
        llenarTabla_Todo();
        idProyecto = 0;
    }
    private void LimpiarProyecto(){
        cbDocente.setSelectedIndex(-1);
        informe = null;
        txNombreOriginal.setText("");
        txNombreNuevo.setText("");
        txRutaNombre.setText("");
        llenarTabla_Todo();
        idProyecto = 0;
        colocarRuta();
    }
    private void colocarRuta(){
        String _nuevoNombre = txNombreNuevo.getText();
        if ( !_nuevoNombre.isEmpty()) {
            txRutaNombre.setText(Configuracion.rutaInformes +idProyecto +"\\" + _nuevoNombre+".pdf");
        }else{
            txRutaNombre.setText(Configuracion.rutaInformes +idProyecto +"\\");
        }        
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuInformes = new javax.swing.JPopupMenu();
        verInforme = new javax.swing.JMenuItem();
        abrirUbicacion = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        pnProyecto = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbProy = new javax.swing.JComboBox<>();
        cbDocente = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        errorProy = new javax.swing.JLabel();
        errorDocente = new javax.swing.JLabel();
        txNombreOriginal = new javax.swing.JTextField();
        txNombreNuevo = new javax.swing.JTextField();
        errorPdf = new javax.swing.JLabel();
        errorNumero = new javax.swing.JLabel();
        lbSeleccionar = new javax.swing.JLabel();
        btGuardar = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txRutaNombre = new javax.swing.JTextField();
        cbFase = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        pnProyecto2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btLimpiar = new javax.swing.JLabel();

        menuInformes.setBackground(new java.awt.Color(255, 255, 255));
        menuInformes.setOpaque(false);

        verInforme.setBackground(new java.awt.Color(255, 255, 255));
        verInforme.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        verInforme.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/open_pdf.png"))); // NOI18N
        verInforme.setText("Ver Informe");
        verInforme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verInformeActionPerformed(evt);
            }
        });
        menuInformes.add(verInforme);

        abrirUbicacion.setBackground(new java.awt.Color(255, 255, 255));
        abrirUbicacion.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        abrirUbicacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/open_folder.png"))); // NOI18N
        abrirUbicacion.setText("Abrir Ubicacion");
        abrirUbicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirUbicacionActionPerformed(evt);
            }
        });
        menuInformes.add(abrirUbicacion);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnProyecto.setBackground(new java.awt.Color(255, 255, 255));
        pnProyecto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Datos del informe", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 12), new java.awt.Color(100, 100, 120))); // NOI18N
        pnProyecto.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(100, 100, 120));
        jLabel3.setText("Nombre del docente");
        pnProyecto.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(668, 40, 160, 20));

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
        pnProyecto.add(cbProy, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 60, 403, 30));

        cbDocente.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cbDocente.setForeground(new java.awt.Color(64, 69, 78));
        pnProyecto.add(cbDocente, new org.netbeans.lib.awtextra.AbsoluteConstraints(668, 60, 568, 30));

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(100, 100, 120));
        jLabel4.setText("Nombre del archivo seleccionado");
        pnProyecto.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(258, 120, -1, 20));

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(100, 100, 120));
        jLabel5.setText("Fase del proyecto");
        pnProyecto.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, 20));

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(100, 100, 120));
        jLabel6.setText("Numero o nombre del informe");
        pnProyecto.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(668, 120, -1, 20));

        errorProy.setForeground(new java.awt.Color(255, 51, 51));
        errorProy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/infoRed.png"))); // NOI18N
        errorProy.setText("Seleccione el nombre del proyecto");
        pnProyecto.add(errorProy, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 90, -1, 20));

        errorDocente.setForeground(new java.awt.Color(255, 51, 51));
        errorDocente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/infoRed.png"))); // NOI18N
        errorDocente.setText("Seleccione el nombre del docente que presenta el informe");
        pnProyecto.add(errorDocente, new org.netbeans.lib.awtextra.AbsoluteConstraints(668, 90, -1, 20));

        txNombreOriginal.setEditable(false);
        txNombreOriginal.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        txNombreOriginal.setForeground(new java.awt.Color(64, 69, 78));
        txNombreOriginal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pnProyecto.add(txNombreOriginal, new org.netbeans.lib.awtextra.AbsoluteConstraints(258, 140, 350, 30));

        txNombreNuevo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txNombreNuevo.setForeground(new java.awt.Color(64, 69, 78));
        txNombreNuevo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txNombreNuevo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txNombreNuevoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txNombreNuevoKeyTyped(evt);
            }
        });
        pnProyecto.add(txNombreNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(668, 140, 568, 30));

        errorPdf.setForeground(new java.awt.Color(255, 51, 51));
        errorPdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/infoRed.png"))); // NOI18N
        errorPdf.setText("Seleccione el documento que se presentara como informe");
        pnProyecto.add(errorPdf, new org.netbeans.lib.awtextra.AbsoluteConstraints(258, 170, -1, 20));

        errorNumero.setForeground(new java.awt.Color(255, 51, 51));
        errorNumero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/infoRed.png"))); // NOI18N
        errorNumero.setText("<html>\nIndique el nombre del informe. por ejemplo: <b>informe_final</b>.pdf   o   <b>inf_001</b>.pdf\n\n<html>");
        pnProyecto.add(errorNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(668, 170, -1, 20));

        lbSeleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/adobe.png"))); // NOI18N
        lbSeleccionar.setText("Seleccionar informe");
        lbSeleccionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbSeleccionarMouseClicked(evt);
            }
        });
        pnProyecto.add(lbSeleccionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 130, 200, 45));

        btGuardar.setText("Guardar informe");
        btGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btGuardarMouseClicked(evt);
            }
        });
        pnProyecto.add(btGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1086, 210, 150, 30));

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(100, 100, 120));
        jLabel7.setText("Ruta y nombre del archivo");
        pnProyecto.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, 20));

        txRutaNombre.setEditable(false);
        txRutaNombre.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txRutaNombre.setForeground(new java.awt.Color(64, 69, 78));
        txRutaNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pnProyecto.add(txRutaNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 568, 30));

        cbFase.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cbFase.setForeground(new java.awt.Color(64, 69, 78));
        cbFase.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "En Evaluacion", "Sin Iniciar", "En Proceso", "Finalizado" }));
        cbFase.setSelectedIndex(2);
        cbFase.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbFasePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        pnProyecto.add(cbFase, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 125, 30));

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(100, 100, 120));
        jLabel8.setText("Nombre del proyecto");
        pnProyecto.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 40, -1, 20));

        jPanel1.add(pnProyecto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 1286, 260));

        pnProyecto2.setBackground(new java.awt.Color(255, 255, 255));
        pnProyecto2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Informes presentados sobre el proyecto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 12), new java.awt.Color(100, 100, 120))); // NOI18N
        pnProyecto2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabla.setComponentPopupMenu(menuInformes);
        jScrollPane1.setViewportView(tabla);

        pnProyecto2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 1206, 260));

        jPanel1.add(pnProyecto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 1286, 310));

        btLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/clear1.png"))); // NOI18N
        btLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btLimpiarMouseClicked(evt);
            }
        });
        jPanel1.add(btLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 40, 25, 25));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btGuardarMouseClicked
        ocultarErrores();
        if (validacion()) {
            guardarInforme();
            llenarTabla_Proyecto();
            LimpiarProyecto();
        }
    }//GEN-LAST:event_btGuardarMouseClicked

    private void lbSeleccionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSeleccionarMouseClicked
        seleccionarDocumento();
    }//GEN-LAST:event_lbSeleccionarMouseClicked

    private void txNombreNuevoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txNombreNuevoKeyReleased
        colocarRuta();
    }//GEN-LAST:event_txNombreNuevoKeyReleased

    private void cbFasePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbFasePopupMenuWillBecomeInvisible
        llenarComboProyectos();
    }//GEN-LAST:event_cbFasePopupMenuWillBecomeInvisible

    private void cbProyPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbProyPopupMenuWillBecomeInvisible
        if (cbProy.getSelectedIndex() > -1) {
            String _proyecto = cbProy.getSelectedItem().toString();
            System.out.println(_proyecto);
            if ( !_proyecto.equals("No hay datos") ) {                
                control.FillCombo(cbDocente, "select Docente from v_docente_proyecto where proyecto = '" + _proyecto + "' order by Docente;", 1);
                idProyecto = Integer.parseInt(control.ReturnData("select id from proyectos where proyecto = '" + _proyecto + "';", 1));
                llenarTabla_Proyecto();
                colocarRuta();
            }
        }
    }//GEN-LAST:event_cbProyPopupMenuWillBecomeInvisible

    private void txNombreNuevoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txNombreNuevoKeyTyped
        if (evt.getKeyChar() == 32){
            evt.consume();
            txNombreNuevo.setText(txNombreNuevo.getText()+"_");
        }
    }//GEN-LAST:event_txNombreNuevoKeyTyped

    private void btLimpiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btLimpiarMouseClicked
        limpiarTodo();
    }//GEN-LAST:event_btLimpiarMouseClicked

    private void verInformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verInformeActionPerformed
        int filaSeleccionada = tabla.getSelectedRow();
        if ( filaSeleccionada > -1 ){
            String _rutaInforme = Configuracion.rutaInformes + Table.GetDataTable(tabla, 0).toString() +"\\"+ Table.GetDataTable(tabla, 1).toString() + ".pdf";
            Open.OpenDocument(_rutaInforme);
        }
    }//GEN-LAST:event_verInformeActionPerformed

    private void abrirUbicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirUbicacionActionPerformed
        int filaSeleccionada = tabla.getSelectedRow();
        if ( filaSeleccionada > -1 ){
            String _rutaInforme = Configuracion.rutaInformes + Table.GetDataTable(tabla, 0).toString() +"\\";
            Open.OpenDocument(_rutaInforme);
        }
    }//GEN-LAST:event_abrirUbicacionActionPerformed

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
            java.util.logging.Logger.getLogger(Informes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Informes().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem abrirUbicacion;
    private javax.swing.JLabel btGuardar;
    private javax.swing.JLabel btLimpiar;
    public static javax.swing.JComboBox<String> cbDocente;
    public static javax.swing.JComboBox<String> cbFase;
    public static javax.swing.JComboBox<String> cbProy;
    private javax.swing.JLabel errorDocente;
    private javax.swing.JLabel errorNumero;
    private javax.swing.JLabel errorPdf;
    private javax.swing.JLabel errorProy;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbSeleccionar;
    private javax.swing.JPopupMenu menuInformes;
    private javax.swing.JPanel pnProyecto;
    private javax.swing.JPanel pnProyecto2;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txNombreNuevo;
    private javax.swing.JTextField txNombreOriginal;
    private javax.swing.JTextField txRutaNombre;
    private javax.swing.JMenuItem verInforme;
    // End of variables declaration//GEN-END:variables
}
