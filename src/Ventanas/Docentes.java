package Ventanas;

import Clases.Control;
import com.alerts.Alert;
import com.database.Controller;
import com.ui.Label;
import com.ui.Table;
import com.ui.TextField;
import com.ui.Texts;
import javax.swing.JLabel;

public final class Docentes extends javax.swing.JDialog {

    Controller control = Control.getController();
    Label label = new Label();
    JLabel[] errores;
    public static int id = 0;
    public static boolean cerrar = true;

    public Docentes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        errores = new JLabel[]{errorDni, errorApe, errorNom, errorGen, errorFac, errorEsc, errorSit};
        interfaz();
        Limpiar();
        if (id != 0 ) {
            buscarDocente(id);
            btCTA.setText("Editar datos");
        }
    }

    private void ocultarErrores() {
        for (JLabel error : errores) {
            error.setVisible(false);
        }
    }

    private void interfaz() {
        setSize(750, 700);
        setLocationRelativeTo(null);
        ocultarErrores();
        label.btnPrimary(btCTA, 150, 0);
        control.FillCombo(cbFac, "select * from facultades", 2);
    }
    private boolean validacion() {
        if ( !txApe.getText().isEmpty() ) {
            if ( !txNom.getText().isEmpty() ) {
                if ( txDni.getText().length() == 8 ) {
                    if ( cbEsc.getSelectedIndex() > -1 ) {
                        if ( txTel.getText().isEmpty() && txEmail.getText().isEmpty() ){ return true; }
                        else{
                            if ( txTel.getText().length() == 9 ) {
                                if (txEmail.getText().isEmpty()) { return true; }
                                else{ return TextField.IsMail(txEmail.getText()); }
                            }else{ txTel.grabFocus(); return false;}
                        }
                    } else { errorEsc.setVisible(true); cbEsc.grabFocus(); return false;}                        
                } else { errorDni.setVisible(true); txDni.grabFocus(); return false; }
            } else { errorNom.setVisible(true); txNom.grabFocus(); return false; }
        } else { errorApe.setVisible(true); txApe.grabFocus(); return false; }
    }
    private void guardar(int id,String dni,String ape,String nom,String gen,String tel,String mail,String sit,String esc) {
        //Sirve para guardar(id = 0) y editar(id != 0)
        String rsta = control.ReturnData("call sp_docente("+ id +",'"+ dni + "','"+ 
                ape + "', '"+ nom + "', '"+ gen + "', '"+ 
                tel + "', '"+ mail + "', '"+ sit + "', '"+ esc + "')", 1);
        Alert.showAlertH("Mensaje", rsta, "success");
    }
    private void Limpiar(){
        txDni.setText(null);
        txApe.setText(null);
        txNom.setText(null);
        femenino.setSelected(true);
        contratado.setSelected(true);
        txTel.setText(null);
        txEmail.setText(null);
    }
    private void buscarDocente(int _id){
        String sql = "select dni,"
                + "(select apellidos from personas where id = "+_id+"), "
                + "(select nombres from personas where id = "+_id+"), "
                + "genero, facultad, escuela,situacion,telefono, email "
                + "from v_docentes where id ="+_id+";";
        String[] docente = control.ReturnMultiData(sql, 9);
        
        txDni.setText(docente[0]);
        txApe.setText(docente[1]);
        txNom.setText(docente[2]);
        if (docente[3].equals("Femenino")) { femenino.setSelected(true); }
            else {masculino.setSelected(true); }
        cbFac.setSelectedItem(docente[4]);
        llenarEscuelas();
        cbEsc.setSelectedItem(docente[5]);
        if (docente[6].equals("Contratado")) { contratado.setSelected(true); }
            else {nombrado.setSelected(true); }
        txTel.setText(docente[7]);
        txEmail.setText(docente[8]);
    }
    private void llenarEscuelas(){
        control.FillCombo(cbEsc, "select * from escuelas where idfacultad = "
            + "(select id from facultades where facultad = '" + cbFac.getSelectedItem().toString() + "')", 2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoGenero = new javax.swing.ButtonGroup();
        grupoSit = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txApe = new javax.swing.JTextField();
        errorApe = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txNom = new javax.swing.JTextField();
        errorNom = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txDni = new javax.swing.JTextField();
        errorDni = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        femenino = new javax.swing.JRadioButton();
        errorGen = new javax.swing.JLabel();
        masculino = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txTel = new javax.swing.JTextField();
        txEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cbFac = new javax.swing.JComboBox<>();
        errorFac = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        contratado = new javax.swing.JRadioButton();
        errorSit = new javax.swing.JLabel();
        nombrado = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        cbEsc = new javax.swing.JComboBox<>();
        errorEsc = new javax.swing.JLabel();
        btCTA = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(100, 300));
        jPanel1.setPreferredSize(new java.awt.Dimension(489, 420));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Datos personales del docente", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 12), new java.awt.Color(100, 100, 120))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(100, 100, 120));
        jLabel2.setText("Apellidos");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 250, 20));

        txApe.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txApe.setForeground(new java.awt.Color(64, 69, 78));
        txApe.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txApe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txApeKeyTyped(evt);
            }
        });
        jPanel3.add(txApe, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 275, 30));

        errorApe.setForeground(new java.awt.Color(255, 51, 51));
        errorApe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/infoRed.png"))); // NOI18N
        errorApe.setText("Coloque aqui sus apellidos PATERNO y MATERNO");
        jPanel3.add(errorApe, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, 20));

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(100, 100, 120));
        jLabel3.setText("Nombres");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 30, 250, 20));

        txNom.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txNom.setForeground(new java.awt.Color(64, 69, 78));
        txNom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txNom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txNomKeyTyped(evt);
            }
        });
        jPanel3.add(txNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 50, 275, 30));

        errorNom.setForeground(new java.awt.Color(255, 51, 51));
        errorNom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/infoRed.png"))); // NOI18N
        errorNom.setText("Coloque aqui sus NOMBRES");
        jPanel3.add(errorNom, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 80, -1, 20));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(100, 100, 120));
        jLabel1.setText("DNI");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 250, 20));

        txDni.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txDni.setForeground(new java.awt.Color(64, 69, 78));
        txDni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txDniKeyTyped(evt);
            }
        });
        jPanel3.add(txDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 275, 30));

        errorDni.setForeground(new java.awt.Color(255, 51, 51));
        errorDni.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/infoRed.png"))); // NOI18N
        errorDni.setText("Coloque aqui su numero de DNI");
        jPanel3.add(errorDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, 20));

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(100, 100, 120));
        jLabel5.setText("Genero");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 110, 250, 20));

        femenino.setBackground(new java.awt.Color(255, 255, 255));
        grupoGenero.add(femenino);
        femenino.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        femenino.setForeground(new java.awt.Color(64, 69, 78));
        femenino.setSelected(true);
        femenino.setText("Femenino");
        jPanel3.add(femenino, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 130, 138, 30));

        errorGen.setForeground(new java.awt.Color(255, 51, 51));
        errorGen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/infoRed.png"))); // NOI18N
        errorGen.setText("Seleccione el GENERO del docente");
        jPanel3.add(errorGen, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 160, -1, 20));

        masculino.setBackground(new java.awt.Color(255, 255, 255));
        grupoGenero.add(masculino);
        masculino.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        masculino.setForeground(new java.awt.Color(64, 69, 78));
        masculino.setText("Masculino");
        jPanel3.add(masculino, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 130, 138, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 670, 210));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Informacion de contacto (OPCIONALES)", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 12), new java.awt.Color(100, 100, 120))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(100, 100, 120));
        jLabel4.setText("Telefono");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 250, 20));

        txTel.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txTel.setForeground(new java.awt.Color(64, 69, 78));
        txTel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txTel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txTelKeyTyped(evt);
            }
        });
        jPanel2.add(txTel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 275, 30));

        txEmail.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txEmail.setForeground(new java.awt.Color(64, 69, 78));
        txEmail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(txEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 50, 275, 30));

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(100, 100, 120));
        jLabel6.setText("Email");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 30, 250, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 670, 110));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Datos laborales del docente", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 12), new java.awt.Color(100, 100, 120))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(100, 100, 120));
        jLabel8.setText("Facultad");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 250, 20));

        cbFac.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cbFac.setForeground(new java.awt.Color(64, 69, 78));
        cbFac.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbFacPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jPanel4.add(cbFac, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 260, 30));

        errorFac.setForeground(new java.awt.Color(255, 51, 51));
        errorFac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/infoRed.png"))); // NOI18N
        errorFac.setText("Seleccione la FACULTAD");
        jPanel4.add(errorFac, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, 20));

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(100, 100, 120));
        jLabel9.setText("Situacion");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 110, 65, 20));

        contratado.setBackground(new java.awt.Color(255, 255, 255));
        grupoSit.add(contratado);
        contratado.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        contratado.setForeground(new java.awt.Color(64, 69, 78));
        contratado.setSelected(true);
        contratado.setText("Contratado");
        jPanel4.add(contratado, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 130, 138, 30));

        errorSit.setForeground(new java.awt.Color(255, 51, 51));
        errorSit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/infoRed.png"))); // NOI18N
        errorSit.setText("Seleccione la SITUACION LABORAL del docente");
        jPanel4.add(errorSit, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 160, -1, 20));

        nombrado.setBackground(new java.awt.Color(255, 255, 255));
        grupoSit.add(nombrado);
        nombrado.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        nombrado.setForeground(new java.awt.Color(64, 69, 78));
        nombrado.setText("Nombrado");
        jPanel4.add(nombrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 130, 138, 30));

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(90, 90, 100));
        jLabel7.setText("Escuela");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 250, 20));

        cbEsc.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cbEsc.setForeground(new java.awt.Color(64, 69, 78));
        cbEsc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cbEscFocusGained(evt);
            }
        });
        jPanel4.add(cbEsc, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 260, 30));

        errorEsc.setForeground(new java.awt.Color(255, 51, 51));
        errorEsc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/infoRed.png"))); // NOI18N
        errorEsc.setText("Seleccione la ESCUELA");
        jPanel4.add(errorEsc, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, 20));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 670, 210));

        btCTA.setText("Registrar Docente");
        btCTA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btCTAMouseClicked(evt);
            }
        });
        jPanel1.add(btCTA, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 610, -1, 30));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbFacPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbFacPopupMenuWillBecomeInvisible
        if (cbFac.getSelectedIndex() > -1) {
            llenarEscuelas();
        }
    }//GEN-LAST:event_cbFacPopupMenuWillBecomeInvisible

    private void txDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txDniKeyTyped
        TextField.Dni(evt, txDni);
    }//GEN-LAST:event_txDniKeyTyped

    private void txApeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txApeKeyTyped
        TextField.Capitalize(evt, txApe);
    }//GEN-LAST:event_txApeKeyTyped

    private void txNomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txNomKeyTyped
        TextField.Capitalize(evt, txNom);
    }//GEN-LAST:event_txNomKeyTyped

    private void txTelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txTelKeyTyped
        TextField.Telephone(evt, txTel);
    }//GEN-LAST:event_txTelKeyTyped

    private void btCTAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btCTAMouseClicked
        ocultarErrores();
        if (validacion()){
            
            String _dni = txDni.getText();
            String _ape = txApe.getText();
            String _nom = txNom.getText();
            String _gen = femenino.isSelected() ? femenino.getText():masculino.getText();
            String _esc = cbEsc.getSelectedItem().toString();
            String _sit = contratado.isSelected() ? contratado.getText() : nombrado.getText();
            String _tel = txTel.getText();
            String _mail = txEmail.getText();            
            guardar(id, _dni, _ape, _nom, _gen, _tel, _mail, _sit, _esc);
            
            if (cerrar) {
                String sql = Texts.ConcatLike("select id, dni, docente, Genero,telefono, email, Escuela from v_docentes where ", 
                        verDocentes.txBuscar.getText(),  verDocentes.header);
                control.FillTable(verDocentes.model, sql, 7);
                Table.HideColumn(verDocentes.tabla, 0);
                id = 0;
                this.dispose();
            }
        }
    }//GEN-LAST:event_btCTAMouseClicked

    private void cbEscFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbEscFocusGained
        if (cbFac.getSelectedIndex() < 0)
            cbFac.grabFocus();
    }//GEN-LAST:event_cbEscFocusGained

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Docentes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            Docentes dialog = new Docentes(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel btCTA;
    private javax.swing.JComboBox<String> cbEsc;
    private javax.swing.JComboBox<String> cbFac;
    private javax.swing.JRadioButton contratado;
    private javax.swing.JLabel errorApe;
    private javax.swing.JLabel errorDni;
    private javax.swing.JLabel errorEsc;
    private javax.swing.JLabel errorFac;
    private javax.swing.JLabel errorGen;
    private javax.swing.JLabel errorNom;
    private javax.swing.JLabel errorSit;
    private javax.swing.JRadioButton femenino;
    private javax.swing.ButtonGroup grupoGenero;
    private javax.swing.ButtonGroup grupoSit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton masculino;
    private javax.swing.JRadioButton nombrado;
    private javax.swing.JTextField txApe;
    private javax.swing.JTextField txDni;
    private javax.swing.JTextField txEmail;
    private javax.swing.JTextField txNom;
    private javax.swing.JTextField txTel;
    // End of variables declaration//GEN-END:variables
}
