package Ventanas;

import Clases.Control;
import com.alerts.Message;
import com.database.Controller;
import com.ui.DateChooser;
import com.ui.Label;
import com.ui.List;
import com.ui.Table;
import com.ui.TableModel;
import com.ui.TextField;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class Proyectos extends javax.swing.JFrame {

    Controller control = Control.getController();
    TableModel modelDocentes = new TableModel();
    TableModel modelProyectos = new TableModel();
    DefaultListModel<String> modelList = new DefaultListModel<>();
    String[] header = new String[]{"Id", "Semestre", "Inicio", "Fin"};
    
    String[] idDocentes;
    String[] idDocentesNuevos;
    ArrayList<String> idEditarDocente = new ArrayList<>();
    ArrayList<String> idEliminarDocente = new ArrayList<>();
    ArrayList<String> idInsertarDocente = new ArrayList<>();
    
    Label label = new Label();
    int id = 0;
    public static int idDocente = 0;
    int idProyecto = 0;

    
    public Proyectos() {
        initComponents();
        interfaz();
        ocultarErrores();
        dropdownActive();
        llenarTablaProyectos(cbVerProyectos.getSelectedItem().toString());
    }
    private void ocultarErrores(){
        errorProy.setVisible(false);
        errorInicio.setVisible(false);
        errorFin.setVisible(false);
        errorInst.setVisible(false);
        errorDocente.setVisible(false);
        errorCargo.setVisible(false);
    }
    private void interfaz(){
        setTitle("Proyectos");
        setExtendedState(6);
        label.btnPrimary(btDocente, 150, 0);
        label.btnPrimary(btGuardar, 150, 0);
        Label.buttonIcon(btAddInst, null, null, "/Imagenes/Iconos/add.png", "/Imagenes/Iconos/addFilled.png", 0);
        Label.buttonIcon(btAddDocente, null, null, "/Imagenes/Iconos/add.png", "/Imagenes/Iconos/addFilled.png", 0);
        Label.buttonIcon(btAddCargo, null, null, "/Imagenes/Iconos/add.png", "/Imagenes/Iconos/addFilled.png", 0);
        Label.buttonIcon(btLimpiarProyecto, null, null, "/Imagenes/Iconos/clear1.png", "/Imagenes/Iconos/clear2.png", 0);
        Table.TableLight(tablaDocentes);
        control.FillCombo(cbCargo, "select * from cargos order by cargo;", 2);
        control.FillCombo(cbInst, "select * from instituciones order by institucion;", 2);
        Table.SetModel(tablaDocentes, modelDocentes, new String[]{"Id", "DNI", "Docente", "Escuela", "Cargo"}); 
        Table.SetModel(tablaProyecto, modelProyectos, 
                new String[]{"Id", "Proyecto", "Presentacion", "Inicio", "Fin","Institucion", "Fase"});
        Table.HideColumn(tablaDocentes, 0);
        Table.HideColumn(tablaProyecto, 0);
        Table.WidthColumn(tablaProyecto, 350, 1);
        llenarLista();
        listaProy.setModel(modelList);
        List.Light(listaProy);
    }
    /* PANEL DOCENTE */
    private void buscarDocente(){
        String _dni = txDni.getText();
        String[] docenteDatos = control.ReturnRowData("select id, docente, escuela from v_docentes where dni = '"+_dni+"';", 3);
        idDocente = Integer.parseInt(docenteDatos[0]);
        docente.setText(docenteDatos[1] + " - " + docenteDatos[2]);
    }
    private void limpiarDocente(){
        idDocente = 0;
        docente.setText("");
        cbCargo.setSelectedIndex(-1);
    }
    private void llenarTablaDocente(){
        control.FillTable(modelDocentes, "select id, dni, docente, escuela, cargo from v_docente_proyecto where proyecto = '"+txProyecto.getText()+"';", 5);
        idDocentes = control.ReturnColumnData("select id from v_docente_proyecto where proyecto = '"+txProyecto.getText()+"';");
        Table.TableLight(tablaDocentes);
    }
    private void llenarTablaDocenteNuevos(){
        String _dni = txDni.getText();
        String _docente = docente.getText().split(" - ")[0];
        String _escuela = docente.getText().split(" - ")[1];
        String _cargo = cbCargo.getSelectedItem().toString();
        modelDocentes.addRow(new Object[]{(idDocente+1), _dni, _docente, _escuela, _cargo});
        Table.TableLight(tablaDocentes);
    }
    private boolean validacionDocente(){
        if ( !docente.getText().isEmpty() && txDni.getText().length() == 8 ){
            if ( cbCargo.getSelectedIndex() > -1 )
                return true;
            else{
                errorCargo.setVisible(true);cbCargo.grabFocus(); return false;
            }
        }else{
            errorDocente.setVisible(true);txDni.grabFocus(); return false;
        }
    }
    /* DROPDOWN */
    private void llenarLista(){
        control.FillList(modelList, "select proyecto from proyectos where proyecto like '%"+txProyecto.getText()+"%' order by proyecto;;", 1);
    }
    private void mostrarLista(boolean rsta){
        scrollLista.setVisible(rsta);
    }
    private void dropdownActive(){
        boolean rsta = dropdown.isSelected();
        mostrarLista(rsta);
    }
    private void llenarProyecto(){
        if (control.ExistInDB("select id from proyectos where proyecto = '"+txProyecto.getText()+"'")) {
            String[] _proyecto = control.ReturnRowData("select "
                    + "inicio, fin, (select institucion from instituciones where id = idinstitucion), id "
                    + "from proyectos where proyecto = '"+txProyecto.getText()+"'", 4);
            inicio.setDate(DateChooser.getDate(_proyecto[0]));
            fin.setDate(DateChooser.getDate(_proyecto[1]));
            cbInst.setSelectedItem(_proyecto[2]);
            idProyecto = Integer.parseInt(_proyecto[3]);
        }
    }
    private void limpiarProyecto(){
        txProyecto.setText("");
        inicio.setDate(null);
        fin.setDate(null);
        cbInst.setSelectedIndex(-1);
        idProyecto = 0;
    }
    private boolean buscarEnArray(String id, String[] array){
        for (String item : array) {
            if (id.equals(item))
                return true;            
        }
        return false;
    }
    private void llenarIdDocentesNuevos(){
        int size = modelDocentes.getRowCount();
        idDocentesNuevos = new String[size];
        for (int i = 0; i < size; i++){
            idDocentesNuevos[i] = tablaDocentes.getValueAt(i, 0).toString();
        }
    }
    private void consultarDocentes() {
        llenarIdDocentesNuevos();
        
        int numDocAntiguos = idDocentes.length;
        int numDocNuevos = idDocentesNuevos.length;        
        
        //EditarDocentes (Cuando en ambas arrays estan los elementos)
        for (int j=0; j<numDocAntiguos; j++){
            if (buscarEnArray(idDocentes[j], idDocentesNuevos))
                idEditarDocente.add(idDocentes[j]);
        }        
        //EliminarDocentes (Cuando los antiguos no estan en los nuevos)
        for (int j=0; j<numDocAntiguos; j++){
            if (!buscarEnArray(idDocentes[j], idDocentesNuevos))
                idEliminarDocente.add(idDocentes[j]);
        }
        //InsertarDocentes (Cuando los nuevos no estan en los antiguos)
        for (int j=0; j<numDocNuevos; j++){
            if (!buscarEnArray(idDocentesNuevos[j], idDocentes))
                idInsertarDocente.add(idDocentesNuevos[j]);
        }
    }
    private String buscarEnTabla(String id){
        for(int i = 0; i<tablaDocentes.getRowCount(); i++){
            if (id.equals(tablaDocentes.getValueAt(i, 0).toString()) ){
                //Integer.parseInt(id) == Integer.parseInt(tablaDocentes.getValueAt(i, 0).toString()
                return tablaDocentes.getValueAt(i, 4).toString();
            }
        }
        return "No encontrado";
    }
    private int existeEnTabla(String text, int columna){
        for(int i = 0; i<tablaDocentes.getRowCount(); i++){
            if (text.equals(tablaDocentes.getValueAt(i, columna).toString()) ){
                return i;
            }
        }
        return 0;
    }
    private void limpiarArray(ArrayList<String> array){
        while(array.size() > 0){
            array.remove(0);
        }
    }
    private void guardar(MouseEvent evt){
        
        String rsta;
        ocultarErrores();

        if (validacionProyecto()){
            rsta = control.ReturnData("call sp_proyecto(0, '"+txProyecto.getText()+"', '"+DateChooser.AMD(inicio.getDate())+"',"
                + " '"+DateChooser.AMD(fin.getDate())+"','"+cbInst.getSelectedItem().toString()+"');", 1);
        
            if (modelDocentes.getRowCount() > 2){           

                if (rsta.equals("Ok")){
                    llenarIdDocentesNuevos();
                    String idProy = control.ReturnData("select id from proyectos order by id desc limit 1;", 1);
                    for (String idDN : idDocentesNuevos){
                        control.ExecuteQuery("call sp_docente_proyecto(1, "+idProy+", "+idDN+", '"+buscarEnTabla(idDN)+"')");
                    }
                    Message.Show("Se registro exitosamente el proyecto "+txProyecto.getText(), "question");
                    btLimpiarProyectoMouseClicked(evt);
                    cbVerProyectos.setSelectedIndex(1);
                    llenarTablaProyectos(cbVerProyectos.getSelectedItem().toString());

                }else{ Message.Show(rsta, "error"); }

            }else{ Message.Show("El proyecto debe tener un minimo de 3 docentes", "warning"); }  
        
        }         
    }
    private void editar(MouseEvent evt){
        if (modelDocentes.getRowCount() > 2){
            String rsta = control.ReturnData("call sp_proyecto("+idProyecto+", '"+txProyecto.getText()+"', '"+DateChooser.AMD(inicio.getDate())+"',"
                + " '"+DateChooser.AMD(fin.getDate())+"', '"+cbInst.getSelectedItem().toString()+"')", 1);
            
            System.out.println(rsta);
            if (rsta.equalsIgnoreCase("Ok")){
                consultarDocentes();
                idEditarDocente.forEach((_idEd) -> {
                    control.ExecuteQuery("call sp_docente_proyecto(2, "+idProyecto+", "+_idEd+", '"+buscarEnTabla(_idEd)+"')");
                    System.out.println("Editar: "+_idEd+" - "+buscarEnTabla(_idEd));
                });
                idEliminarDocente.forEach((_idEl) -> {
                    control.ExecuteQuery("call sp_docente_proyecto(3, "+idProyecto+", "+_idEl+", 'null')");
                    System.out.println("Eliminar: "+_idEl+" - "+buscarEnTabla(_idEl));
                });
                idInsertarDocente.forEach((_idIn) -> {
                    control.ExecuteQuery("call sp_docente_proyecto(1, "+idProyecto+", "+_idIn+", '"+buscarEnTabla(_idIn)+"')");
                    System.out.println("Insertar: "+_idIn+" - "+buscarEnTabla(_idIn));
                });
                Message.Show("Se modifico exitosamente el proyecto "+txProyecto.getText(), "question");
                
                limpiarArray(idEditarDocente);
                limpiarArray(idEliminarDocente);
                limpiarArray(idInsertarDocente);
                btLimpiarProyectoMouseClicked(evt);
                cbVerProyectos.setSelectedIndex(1);
                llenarTablaProyectos(cbVerProyectos.getSelectedItem().toString());
                
            }else{
                Message.Show(rsta, "error");
            }
        }else{
            Message.Show("El proyecto debe tener un minimo de 3 docentes", "warning");
        }
    }
    /* TABLA PROYECTOS */
    private void llenarTablaProyectos(String ver){
        control.FillTable(modelProyectos, "select Id, Proyecto, Presentacion, Inicio, Fin, Institucion, Fase from v_proyectos where fase like '%"+ver+"%';", 7);
        Table.TableLight(tablaProyecto);
    }
    /* PANEL PROYECTOS */
    private boolean validacionProyecto(){
        if (!txProyecto.getText().isEmpty()) {
            if (inicio != null) {
                if (fin != null){
                    if (cbInst.getSelectedIndex() > -1) {
                        return true;
                    }else { cbInst.grabFocus(); errorInst.setVisible(true);return false; }
                }else{ fin.grabFocus(); errorFin.setVisible(true);return false; }
            }else{ inicio.grabFocus(); errorInicio.setVisible(true);return false; }
        }else{ txProyecto.grabFocus();errorProy.setVisible(true);return false; }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuDocentes = new javax.swing.JPopupMenu();
        editarDocente = new javax.swing.JMenuItem();
        quitarDocente = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        scrollLista = new javax.swing.JScrollPane();
        listaProy = new javax.swing.JList<>();
        pnDatos = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txProyecto = new javax.swing.JTextField();
        errorProy = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        inicio = new com.toedter.calendar.JDateChooser();
        errorInicio = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fin = new com.toedter.calendar.JDateChooser();
        errorFin = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbInst = new javax.swing.JComboBox<>();
        errorInst = new javax.swing.JLabel();
        btAddInst = new javax.swing.JLabel();
        dropdown = new javax.swing.JToggleButton();
        btLimpiarProyecto = new javax.swing.JLabel();
        pnDocentes = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txDni = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btAddDocente = new javax.swing.JLabel();
        iconLock = new javax.swing.JLabel();
        docente = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        errorDocente = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cbCargo = new javax.swing.JComboBox<>();
        errorCargo = new javax.swing.JLabel();
        btAddCargo = new javax.swing.JLabel();
        btDocente = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDocentes = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProyecto = new javax.swing.JTable();
        btGuardar = new javax.swing.JLabel();
        cbVerProyectos = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();

        editarDocente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/edit.png"))); // NOI18N
        editarDocente.setText("Editar docente");
        editarDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarDocenteActionPerformed(evt);
            }
        });
        menuDocentes.add(editarDocente);

        quitarDocente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/delete.png"))); // NOI18N
        quitarDocente.setText("Quitar docente");
        quitarDocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitarDocenteActionPerformed(evt);
            }
        });
        menuDocentes.add(quitarDocente);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrollLista.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        listaProy.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaProy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaProyMouseClicked(evt);
            }
        });
        scrollLista.setViewportView(listaProy);

        jPanel2.add(scrollLista, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 362, 137));

        pnDatos.setBackground(new java.awt.Color(255, 255, 255));
        pnDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Datos del proyecto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(100, 100, 120))); // NOI18N
        pnDatos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(100, 100, 120));
        jLabel2.setText("Nombre del proyecto");
        pnDatos.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 362, 20));

        txProyecto.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txProyecto.setForeground(new java.awt.Color(64, 69, 78));
        txProyecto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txProyecto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txProyectoKeyReleased(evt);
            }
        });
        pnDatos.add(txProyecto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 342, 30));

        errorProy.setForeground(new java.awt.Color(255, 51, 51));
        errorProy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/infoRed.png"))); // NOI18N
        errorProy.setText("Coloque aqui el nombre del PROYECTO");
        pnDatos.add(errorProy, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 362, 20));

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(100, 100, 120));
        jLabel4.setText("Inicio del proyecto");
        pnDatos.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 30, 181, 20));

        inicio.setBackground(new java.awt.Color(255, 255, 255));
        inicio.setForeground(new java.awt.Color(64, 69, 78));
        inicio.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        pnDatos.add(inicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 50, 181, 30));

        errorInicio.setForeground(new java.awt.Color(255, 51, 51));
        errorInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/infoRed.png"))); // NOI18N
        errorInicio.setText("Seleccione la fecha de INICIO");
        pnDatos.add(errorInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 80, 181, 20));

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(100, 100, 120));
        jLabel5.setText("Fin del proyecto");
        pnDatos.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(663, 30, 181, 20));

        fin.setForeground(new java.awt.Color(255, 0, 0));
        fin.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        pnDatos.add(fin, new org.netbeans.lib.awtextra.AbsoluteConstraints(663, 50, 181, 30));

        errorFin.setForeground(new java.awt.Color(255, 51, 51));
        errorFin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/infoRed.png"))); // NOI18N
        errorFin.setText("Seleccione la fecha de FIN");
        pnDatos.add(errorFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(663, 80, 181, 20));

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(100, 100, 120));
        jLabel8.setText("Institucion");
        pnDatos.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(884, 30, 332, 20));

        cbInst.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cbInst.setForeground(new java.awt.Color(64, 69, 78));
        pnDatos.add(cbInst, new org.netbeans.lib.awtextra.AbsoluteConstraints(884, 50, 332, 30));

        errorInst.setForeground(new java.awt.Color(255, 51, 51));
        errorInst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/infoRed.png"))); // NOI18N
        errorInst.setText("Seleccione la INSTITUCION donde se realizara el proyecto");
        pnDatos.add(errorInst, new org.netbeans.lib.awtextra.AbsoluteConstraints(884, 80, 332, 20));

        btAddInst.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btAddInst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/add.png"))); // NOI18N
        btAddInst.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btAddInstMouseClicked(evt);
            }
        });
        pnDatos.add(btAddInst, new org.netbeans.lib.awtextra.AbsoluteConstraints(1216, 50, 30, 30));

        dropdown.setBackground(new java.awt.Color(255, 255, 255));
        dropdown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/expand.png"))); // NOI18N
        dropdown.setOpaque(true);
        dropdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropdownActionPerformed(evt);
            }
        });
        pnDatos.add(dropdown, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 49, 20, 31));

        btLimpiarProyecto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/clear1.png"))); // NOI18N
        btLimpiarProyecto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btLimpiarProyectoMouseClicked(evt);
            }
        });
        pnDatos.add(btLimpiarProyecto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 30, 30));

        jPanel2.add(pnDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 1286, 110));

        pnDocentes.setBackground(new java.awt.Color(255, 255, 255));
        pnDocentes.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Docentes registrados en el proyecto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(100, 100, 120))); // NOI18N
        pnDocentes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(100, 100, 120));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Buscar docente");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        pnDocentes.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 100, 25));

        txDni.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txDni.setForeground(new java.awt.Color(64, 69, 78));
        txDni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txDniKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txDniKeyTyped(evt);
            }
        });
        pnDocentes.add(txDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 100, 25));

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(100, 100, 120));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("o");
        pnDocentes.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 50, 25));

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(100, 100, 120));
        jLabel3.setText("Seleccionar docente");
        pnDocentes.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 130, 25));

        btAddDocente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btAddDocente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/add.png"))); // NOI18N
        btAddDocente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btAddDocenteMouseClicked(evt);
            }
        });
        pnDocentes.add(btAddDocente, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 40, 30, 25));

        iconLock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/lock.png"))); // NOI18N
        pnDocentes.add(iconLock, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        docente.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        docente.setForeground(new java.awt.Color(64, 69, 78));
        docente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnDocentes.add(docente, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 425, 30));
        pnDocentes.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 425, -1));

        errorDocente.setForeground(new java.awt.Color(255, 51, 51));
        errorDocente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/infoRed.png"))); // NOI18N
        errorDocente.setText("Seleccione el DOCENTE");
        pnDocentes.add(errorDocente, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 150, 20));

        jLabel11.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(100, 100, 120));
        jLabel11.setText("Cargo");
        pnDocentes.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 300, 20));

        cbCargo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cbCargo.setForeground(new java.awt.Color(64, 69, 78));
        pnDocentes.add(cbCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 190, 30));

        errorCargo.setForeground(new java.awt.Color(255, 51, 51));
        errorCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/infoRed.png"))); // NOI18N
        errorCargo.setText("Seleccione el CARGO del docente");
        pnDocentes.add(errorCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, 20));

        btAddCargo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btAddCargo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/add.png"))); // NOI18N
        btAddCargo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btAddCargoMouseClicked(evt);
            }
        });
        pnDocentes.add(btAddCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 30, 30));

        btDocente.setText("Agregar el docente");
        btDocente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btDocenteMouseClicked(evt);
            }
        });
        pnDocentes.add(btDocente, new org.netbeans.lib.awtextra.AbsoluteConstraints(315, 160, 150, 30));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        tablaDocentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"a", null, null, null},
                {"aa", null, null, null},
                {"aa", null, null, null},
                {"aa", null, null, null},
                {"a", null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaDocentes.setComponentPopupMenu(menuDocentes);
        jScrollPane1.setViewportView(tablaDocentes);

        pnDocentes.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(516, 30, 730, 193));

        jPanel2.add(pnDocentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 1286, 240));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        tablaProyecto.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaProyecto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProyectoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaProyecto);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, 1286, 200));

        btGuardar.setText("Guardar Proyecto");
        btGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btGuardarMouseClicked(evt);
            }
        });
        jPanel2.add(btGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1176, 430, 150, 30));

        cbVerProyectos.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cbVerProyectos.setForeground(new java.awt.Color(64, 69, 78));
        cbVerProyectos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "En Evaluacion", "Sin Iniciar", "En Proceso", "Finalizado" }));
        cbVerProyectos.setSelectedIndex(3);
        cbVerProyectos.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbVerProyectosPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jPanel2.add(cbVerProyectos, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 430, 190, 30));

        jLabel12.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(100, 100, 120));
        jLabel12.setText("Ver Proyectos:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, 100, 30));

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btGuardarMouseClicked
        ocultarErrores();
        if ( idProyecto != 0){         
            editar(evt);            
        }else{
            guardar(evt);
        }
    }//GEN-LAST:event_btGuardarMouseClicked

    private void btAddInstMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAddInstMouseClicked
        // TODO
    }//GEN-LAST:event_btAddInstMouseClicked

    private void btAddCargoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAddCargoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btAddCargoMouseClicked

    private void btAddDocenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAddDocenteMouseClicked
        limpiarDocente();
        verDocentes.habilitarSeleccionar = true;
        new verDocentes(null, true).setVisible(true);
    }//GEN-LAST:event_btAddDocenteMouseClicked

    private void txDniKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txDniKeyReleased
        if (txDni.getText().length() == 8)
            if (control.ExistInDB("select iddocente from v_docentes where dni = '"+txDni.getText()+"';"))
                buscarDocente();
        else
            limpiarDocente();      
    }//GEN-LAST:event_txDniKeyReleased

    private void btDocenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btDocenteMouseClicked
        int tb = existeEnTabla(txDni.getText(), 1);
        if ( tb != 0) {
            tablaDocentes.setValueAt(cbCargo.getSelectedItem().toString(), tb, 4);
            limpiarDocente(); 
            txDni.setText("");
        }else{
            if (modelDocentes.getRowCount() < 6) {
                ocultarErrores();
                if (validacionDocente()){
                    llenarTablaDocenteNuevos();
                    limpiarDocente(); 
                    txDni.setText("");
                }
            }else{
                Message.Show("El proyecto solo debe tener como maximo 6 docentes", "warning");
            }
        }
    }//GEN-LAST:event_btDocenteMouseClicked

    private void editarDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarDocenteActionPerformed
        txDni.setText(tablaDocentes.getValueAt(tablaDocentes.getSelectedRow(), 1).toString());
        docente.setText(tablaDocentes.getValueAt(tablaDocentes.getSelectedRow(), 2).toString() +" - "+ tablaDocentes.getValueAt(tablaDocentes.getSelectedRow(), 3).toString());
        cbCargo.setSelectedItem(tablaDocentes.getValueAt(tablaDocentes.getSelectedRow(), 4).toString());
    }//GEN-LAST:event_editarDocenteActionPerformed

    private void quitarDocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitarDocenteActionPerformed
        modelDocentes.removeRow(tablaDocentes.getSelectedRow());
    }//GEN-LAST:event_quitarDocenteActionPerformed

    private void txDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txDniKeyTyped
        TextField.Dni(evt, txDni);
    }//GEN-LAST:event_txDniKeyTyped

    private void dropdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropdownActionPerformed
        dropdownActive();
    }//GEN-LAST:event_dropdownActionPerformed

    private void listaProyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaProyMouseClicked
        dropdown.setSelected(false);
        dropdownActive();
        txProyecto.setText(listaProy.getSelectedValue());
        llenarProyecto();
        llenarTablaDocente();
    }//GEN-LAST:event_listaProyMouseClicked

    private void btLimpiarProyectoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btLimpiarProyectoMouseClicked
        limpiarProyecto();
        control.ClearTable(modelDocentes);
        limpiarDocente();
        txDni.setText("");
        txProyecto.grabFocus();
        ocultarErrores();
    }//GEN-LAST:event_btLimpiarProyectoMouseClicked

    private void txProyectoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txProyectoKeyReleased
        llenarLista();
        dropdown.setSelected((modelList.getSize() != 0));
        dropdownActive();
    }//GEN-LAST:event_txProyectoKeyReleased

    private void cbVerProyectosPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbVerProyectosPopupMenuWillBecomeInvisible
        if (cbVerProyectos.getSelectedIndex() == 0) {
            llenarTablaProyectos("");
        }else{
            llenarTablaProyectos(cbVerProyectos.getSelectedItem().toString());
        }
    }//GEN-LAST:event_cbVerProyectosPopupMenuWillBecomeInvisible

    private void tablaProyectoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProyectoMouseClicked
        if (evt.getClickCount() == 2) {
            String __id = Table.GetDataTable(tablaProyecto, 0).toString();
            control.Print1P("Reportes/verProyecto", "", "idProy", __id);
        }
    }//GEN-LAST:event_tablaProyectoMouseClicked

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
            java.util.logging.Logger.getLogger(Proyectos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Proyectos().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btAddCargo;
    private javax.swing.JLabel btAddDocente;
    private javax.swing.JLabel btAddInst;
    private javax.swing.JLabel btDocente;
    private javax.swing.JLabel btGuardar;
    private javax.swing.JLabel btLimpiarProyecto;
    public static javax.swing.JComboBox<String> cbCargo;
    public static javax.swing.JComboBox<String> cbInst;
    public static javax.swing.JComboBox<String> cbVerProyectos;
    public static javax.swing.JLabel docente;
    private javax.swing.JToggleButton dropdown;
    private javax.swing.JMenuItem editarDocente;
    private javax.swing.JLabel errorCargo;
    private javax.swing.JLabel errorDocente;
    private javax.swing.JLabel errorFin;
    private javax.swing.JLabel errorInicio;
    private javax.swing.JLabel errorInst;
    private javax.swing.JLabel errorProy;
    private com.toedter.calendar.JDateChooser fin;
    private javax.swing.JLabel iconLock;
    private com.toedter.calendar.JDateChooser inicio;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JList<String> listaProy;
    private javax.swing.JPopupMenu menuDocentes;
    private javax.swing.JPanel pnDatos;
    private javax.swing.JPanel pnDocentes;
    private javax.swing.JMenuItem quitarDocente;
    private javax.swing.JScrollPane scrollLista;
    private javax.swing.JTable tablaDocentes;
    private javax.swing.JTable tablaProyecto;
    public static javax.swing.JTextField txDni;
    private javax.swing.JTextField txProyecto;
    // End of variables declaration//GEN-END:variables
}
