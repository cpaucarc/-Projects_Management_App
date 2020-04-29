package Ventanas;

import Clases.Control;
import com.alerts.Alert;
import com.database.Controller;
import com.ui.Label;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public final class MenuPrincipal extends javax.swing.JFrame {

    Label label = new Label();
    Controller control = Control.getController();
    
    public MenuPrincipal() {
        initComponents(); 
        setExtendedState(6);
        actualizarCards();
    }
    
    private ArrayList<JPanel> createCards_Aprovate(){
        String[] datos;
        String[] idProyecto = control.ReturnColumnData("select id from v_proyectos " 
                    + "where condicion = 'Aprobado' and fase = 'En Proceso' order by inicio;");
        
        ArrayList<JPanel> cards = new ArrayList<>(); 
        JLabel titulo, fecha, ver;
        
        for (String idProyecto1 : idProyecto) {
            JPanel card = new JPanel();
            SettingsCard(card);
            datos = control.ReturnMultiData("select proyecto, inicio, fin from v_proyectos "
                    + "where condicion = 'Aprobado' and fase = 'En Proceso' and id = " + idProyecto1 + ";", 3);
            titulo = new JLabel();
            SettingsLabel(titulo, "<html><center>"+datos[0]+"</html>", new Color(120,120,150), 1);
            fecha = new JLabel();
            SettingsLabel(fecha, datos[1] +"  al  "+datos[2], new Color(120,120,120), 0);
            ver = new JLabel();
            SettingsLabel(ver, "Ver", new Color(120,120,120), 0);
            label.btnNoBorder(ver, new Color(0, 120, 215), 0);
            String id = idProyecto1;
            ver.addMouseListener(new MouseListener() {
                @Override public void mouseClicked(MouseEvent e) {
                    verProyecto(id);
                }
                @Override public void mousePressed(MouseEvent e) {}
                @Override public void mouseReleased(MouseEvent e) {}
                @Override public void mouseEntered(MouseEvent e) {}
                @Override public void mouseExited(MouseEvent e) {}
            });
            card.add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 205, 70));
            card.add(fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 205, 20));
            card.add(ver, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 205, 30));
            cards.add(card);
        }
        return cards;
    }    
    private ArrayList<JPanel> createCards_Evaluate(){
        String aux_sql = "from proyectos where condicion <> 'Aprobado'";
        String[] datos;
        String[] idProyecto = control.ReturnColumnData("select id "+aux_sql+" order by presentacion desc");
        
        ArrayList<JPanel> cards = new ArrayList<>(); 
        JLabel titulo, fecha, aprobar;
        
        for (String idProyecto1 : idProyecto) {
            JPanel card = new JPanel();
            SettingsCard(card);
            datos = control.ReturnMultiData("select "
                    + "proyecto, date_format( adddate(presentacion, interval 4 day), '%d/%m/%Y') date "
                    + aux_sql+" and id = " + idProyecto1 + ";", 2);
            titulo = new JLabel();
            SettingsLabel(titulo, "<html><center>"+datos[0]+"</html>", new Color(120,120,150), 1);
            fecha = new JLabel();
            SettingsLabel(fecha, "Evaluar antes de: "+datos[1], new Color(255,102,102), 1);
            aprobar = new JLabel();
            SettingsLabel(aprobar, "Aprobar", new Color(255,102,102), 1);
            label.btnNoBorder(aprobar, new Color(0, 120, 215), 0);
            String id = idProyecto1;
            String proyecto = datos[0];
            aprobar.addMouseListener(new MouseListener() {
                @Override public void mouseClicked(MouseEvent e) {
                    aprobarProyecto(id, proyecto);
                }
                @Override public void mousePressed(MouseEvent e) {}
                @Override public void mouseReleased(MouseEvent e) {}
                @Override public void mouseEntered(MouseEvent e) {}
                @Override public void mouseExited(MouseEvent e) {}
            });
            card.add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 205, 70));
            card.add(fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 205, 20));
            card.add(aprobar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 205, 30));
            cards.add(card);
        }
        return cards;
    }
        
    private void addCards_Evaluate(){        
        int number = Integer.parseInt(control.ReturnData("select count(*) from proyectos where condicion <> 'Aprobado';", 1));
        if (number > 0) {
            pnProyEv.removeAll();
            ArrayList<JPanel> paneles_Evaluate = createCards_Evaluate();
            for(int i=0; i<paneles_Evaluate.size(); i++){
                pnProyEv.add(paneles_Evaluate.get(i), new org.netbeans.lib.awtextra.AbsoluteConstraints((40+265*i), 40, 225, 140));
            }
        }
        
    }
    private void addCards_Aprovate(){
        pnProyAprov.removeAll();
        ArrayList<JPanel> paneles_Aprovate = createCards_Aprovate();
        for(int i=0; i<paneles_Aprovate.size(); i++){
            pnProyAprov.add(paneles_Aprovate.get(i), new org.netbeans.lib.awtextra.AbsoluteConstraints((40+265*i), 40, 225, 140));
        }
    }
    public void actualizarCards(){
        addCards_Aprovate();
        addCards_Evaluate();
        SwingUtilities.updateComponentTreeUI(this);
        this.repaint();
    }
    private void SettingsCard(JPanel card){
        card.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        card.setBackground(new java.awt.Color(255, 255, 255));
        card.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
    }
    private void SettingsLabel(JLabel label, String titulo, Color color, int bold){
        label.setText(titulo);
        label.setForeground(color);
        label.setFont(new java.awt.Font("SansSerif", bold, 14));
        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);        
    }
    
    private void aprobarProyecto(String id, String proyecto){
        //int i = JOptionPane.showConfirmDialog(null, "msg", "title", JOptionPane.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
       // System.out.println(i+"");
        boolean rsta = Alert.showAlertH("Aprobar", "Desea aprobar el proyecto <b>"+proyecto, "question");
        if (rsta) {
            control.ExecuteQuery("update proyectos set condicion = 'Aprobado' where id = "+id+";");
            actualizarCards();
        }
    }
    private void verProyecto(String id){
        control.Print1P("Reportes/verProyecto", "Proyecto", "idProy", id);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Bottom = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton4 = new javax.swing.JButton();
        btProyecto = new javax.swing.JButton();
        btInformes = new javax.swing.JButton();
        btCertificado = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        Central = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnProyEv = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        pnProyAprov = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem7 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Bottom.setBackground(new java.awt.Color(255, 255, 255));
        Bottom.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 10));

        jToolBar1.setBorder(null);
        jToolBar1.setFloatable(false);
        jToolBar1.setAlignmentY(0.6F);
        jToolBar1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jToolBar1.setOpaque(false);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/homeB.png"))); // NOI18N
        jButton4.setText("  Inicio  ");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setOpaque(false);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jToolBar1.add(jButton4);

        btProyecto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/proyectos.png"))); // NOI18N
        btProyecto.setText("   Proyectos   ");
        btProyecto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btProyecto.setFocusable(false);
        btProyecto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btProyecto.setOpaque(false);
        btProyecto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btProyecto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btProyectoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btProyectoMouseExited(evt);
            }
        });
        btProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProyectoActionPerformed(evt);
            }
        });
        jToolBar1.add(btProyecto);

        btInformes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/informes.png"))); // NOI18N
        btInformes.setText("   Informes   ");
        btInformes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btInformes.setFocusable(false);
        btInformes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btInformes.setOpaque(false);
        btInformes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btInformes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btInformesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btInformesMouseExited(evt);
            }
        });
        btInformes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInformesActionPerformed(evt);
            }
        });
        jToolBar1.add(btInformes);

        btCertificado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/certificado.png"))); // NOI18N
        btCertificado.setText("   Certificados   ");
        btCertificado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCertificado.setFocusable(false);
        btCertificado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCertificado.setOpaque(false);
        btCertificado.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCertificado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btCertificadoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btCertificadoMouseExited(evt);
            }
        });
        btCertificado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCertificadoActionPerformed(evt);
            }
        });
        jToolBar1.add(btCertificado);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/reportes.png"))); // NOI18N
        jButton3.setText("   Reportes   ");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setOpaque(false);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton3MouseExited(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        Bottom.add(jToolBar1);

        getContentPane().add(Bottom, java.awt.BorderLayout.PAGE_END);

        Central.setBackground(new java.awt.Color(204, 204, 255));
        Central.setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1196, 647));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(64, 69, 78));
        jLabel1.setText("Proyectos Aprobados");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, -1, 30));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(64, 69, 78));
        jLabel3.setText("PROYECTOS");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 25, -1, 40));

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(64, 69, 78));
        jLabel4.setText("Proyectos En Evaluación");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, 30));

        jScrollPane1.setBorder(null);

        pnProyEv.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(64, 69, 78));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/info.png"))); // NOI18N
        jLabel5.setText("No existen proyectos que requieran aprobacion");
        pnProyEv.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        jScrollPane1.setViewportView(pnProyEv);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 1246, 220));

        jScrollPane2.setBorder(null);

        pnProyAprov.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(64, 69, 78));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/info.png"))); // NOI18N
        jLabel6.setText("No existen proyectos aprobados ejecutandose actualmente");
        pnProyAprov.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        jScrollPane2.setViewportView(pnProyAprov);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 370, 1246, 220));

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/update.png"))); // NOI18N
        jLabel7.setText("Actualizar");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1226, 70, 80, 20));

        Central.add(jPanel1, "card2");

        jPanel2.setBackground(new java.awt.Color(255, 153, 153));

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addContainerGap(1313, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(583, Short.MAX_VALUE))
        );

        Central.add(jPanel2, "card3");

        getContentPane().add(Central, java.awt.BorderLayout.CENTER);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Docentes");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/addTeacher.png"))); // NOI18N
        jMenuItem1.setText("Agregar docentes");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/showTeacher.png"))); // NOI18N
        jMenuItem2.setText("Ver docentes registrados");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Universidad");

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/escuelas.png"))); // NOI18N
        jMenuItem3.setText("Escuelas");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem3);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/facultad.png"))); // NOI18N
        jMenuItem4.setText("Facultades");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);
        jMenu4.add(jSeparator1);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/semestre.png"))); // NOI18N
        jMenuItem5.setText("Semestres");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Configuracion");

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/settings.png"))); // NOI18N
        jMenuItem6.setText("Configuracion");
        jMenu5.add(jMenuItem6);
        jMenu5.add(jSeparator2);

        jMenuItem7.setBackground(new java.awt.Color(255, 102, 102));
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/logout.png"))); // NOI18N
        jMenuItem7.setText("Cerrar Sesion");
        jMenuItem7.setOpaque(true);
        jMenu5.add(jMenuItem7);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCertificadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCertificadoActionPerformed
        new Certificados().setVisible(true);
    }//GEN-LAST:event_btCertificadoActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        actualizarCards();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Docentes.cerrar = false;
        new Docentes(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new verDocentes(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        new Escuelas(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        new Facultades(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        new Semestres(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        jPanel1.show();
    }//GEN-LAST:event_jButton4MouseClicked

    private void btProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProyectoActionPerformed
        new Proyectos().setVisible(true);
    }//GEN-LAST:event_btProyectoActionPerformed

    private void btCertificadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btCertificadoMouseEntered
        btCertificado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/certificadoB.png")));
    }//GEN-LAST:event_btCertificadoMouseEntered
    private void btCertificadoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btCertificadoMouseExited
        btCertificado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/certificado.png")));
    }//GEN-LAST:event_btCertificadoMouseExited

    private void btProyectoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btProyectoMouseEntered
        btProyecto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/proyectosB.png")));
    }//GEN-LAST:event_btProyectoMouseEntered
    private void btProyectoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btProyectoMouseExited
        btProyecto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/proyectos.png")));
    }//GEN-LAST:event_btProyectoMouseExited

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JOptionPane.showMessageDialog(null, "Click");
    }//GEN-LAST:event_jButton3ActionPerformed
    private void jButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseEntered
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/reportesB.png")));
    }//GEN-LAST:event_jButton3MouseEntered
    private void jButton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseExited
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/reportes.png")));
    }//GEN-LAST:event_jButton3MouseExited

    private void btInformesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btInformesMouseEntered
        btInformes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/informesB.png")));
    }//GEN-LAST:event_btInformesMouseEntered

    private void btInformesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btInformesMouseExited
        btInformes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/informes.png")));
    }//GEN-LAST:event_btInformesMouseExited

    private void btInformesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInformesActionPerformed
        new Informes().setVisible(true);
    }//GEN-LAST:event_btInformesActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MenuPrincipal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Bottom;
    private javax.swing.JPanel Central;
    private javax.swing.JButton btCertificado;
    private javax.swing.JButton btInformes;
    private javax.swing.JButton btProyecto;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel pnProyAprov;
    private javax.swing.JPanel pnProyEv;
    // End of variables declaration//GEN-END:variables
}
