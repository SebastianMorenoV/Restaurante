/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.ModuloClientesFrecuentes;

import GUI.Aplicacion;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import DTOSalida.ClienteDTO;
import exception.NegocioException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ToolTipManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sebastian Moreno
 */
public class PantallaConsultarClientes extends javax.swing.JPanel {

    private Aplicacion app;

    public PantallaConsultarClientes(Aplicacion app) {
        this.app = app;
        initComponents();
        cargarMetodosAuxiliares();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblBusquedaCliente = new javax.swing.JLabel();
        lblFrecuente = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        pnlNombre = new GUI.PanelRound();
        inputNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        pnlApellidoPaterno = new GUI.PanelRound();
        inputApellidoPaterno = new javax.swing.JTextField();
        btnBuscarCliente = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        pnlTelefono = new GUI.PanelRound();
        inputTelefono = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        pnlCorreo = new GUI.PanelRound();
        inputCorreo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        pnlApellidoMaterno = new GUI.PanelRound();
        inputApellidoMaterno = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();

        setBackground(new java.awt.Color(216, 202, 179));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlHeader.setBackground(new java.awt.Color(255, 255, 255));
        pnlHeader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/flechaAtras.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        pnlHeader.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        lblBusquedaCliente.setBackground(new java.awt.Color(0, 0, 0));
        lblBusquedaCliente.setFont(new java.awt.Font("Playfair Display", 1, 100)); // NOI18N
        lblBusquedaCliente.setText("Busqueda de Cliente ");
        pnlHeader.add(lblBusquedaCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, -10, 1020, 110));

        lblFrecuente.setBackground(new java.awt.Color(0, 0, 0));
        lblFrecuente.setFont(new java.awt.Font("Playfair Display", 1, 100)); // NOI18N
        lblFrecuente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFrecuente.setText("Frecuente");
        lblFrecuente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFrecuenteMouseClicked(evt);
            }
        });
        pnlHeader.add(lblFrecuente, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 940, 110));

        add(pnlHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 180));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1150, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 1150, 4));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel5.setText("NOMBRE(S):");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 180, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/flecha.png"))); // NOI18N
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 40, 20));

        pnlNombre.setBackground(new java.awt.Color(255, 255, 255));
        pnlNombre.setRoundBottomLeft(30);
        pnlNombre.setRoundBottomRight(30);
        pnlNombre.setRoundTopLeft(30);
        pnlNombre.setRoundTopRight(30);
        pnlNombre.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inputNombre.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        inputNombre.setText("Ingresar nombre(s)");
        inputNombre.setBorder(null);
        inputNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputNombreFocusGained(evt);
            }
        });
        inputNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputNombreKeyTyped(evt);
            }
        });
        pnlNombre.add(inputNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 420, 30));

        add(pnlNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 490, 50));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel4.setText("APELLIDO PATERNO:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 290, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/flecha.png"))); // NOI18N
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 40, 20));

        pnlApellidoPaterno.setBackground(new java.awt.Color(255, 255, 255));
        pnlApellidoPaterno.setRoundBottomLeft(30);
        pnlApellidoPaterno.setRoundBottomRight(30);
        pnlApellidoPaterno.setRoundTopLeft(30);
        pnlApellidoPaterno.setRoundTopRight(30);
        pnlApellidoPaterno.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inputApellidoPaterno.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        inputApellidoPaterno.setText("Ingresar apellido");
        inputApellidoPaterno.setBorder(null);
        inputApellidoPaterno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputApellidoPaternoFocusGained(evt);
            }
        });
        inputApellidoPaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputApellidoPaternoKeyTyped(evt);
            }
        });
        pnlApellidoPaterno.add(inputApellidoPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 320, 30));

        add(pnlApellidoPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 340, 50));

        btnBuscarCliente.setFont(new java.awt.Font("Product Sans Infanity", 0, 28)); // NOI18N
        btnBuscarCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnBuscarCliente.setText("BUSCAR UN CLIENTE");
        btnBuscarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarClienteMouseClicked(evt);
            }
        });
        add(btnBuscarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 50));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel6.setText("SELECCIONA EL CLIENTE");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 360, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/flecha.png"))); // NOI18N
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 280, -1, 20));

        pnlTelefono.setBackground(new java.awt.Color(255, 255, 255));
        pnlTelefono.setRoundBottomLeft(30);
        pnlTelefono.setRoundBottomRight(30);
        pnlTelefono.setRoundTopLeft(30);
        pnlTelefono.setRoundTopRight(30);
        pnlTelefono.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inputTelefono.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        inputTelefono.setText("Ingresar telefono");
        inputTelefono.setBorder(null);
        inputTelefono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputTelefonoFocusGained(evt);
            }
        });
        inputTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputTelefonoKeyTyped(evt);
            }
        });
        pnlTelefono.add(inputTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 250, 30));

        add(pnlTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 310, 290, 50));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/flecha.png"))); // NOI18N
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, -1, 20));

        pnlCorreo.setBackground(new java.awt.Color(255, 255, 255));
        pnlCorreo.setRoundBottomLeft(30);
        pnlCorreo.setRoundBottomRight(30);
        pnlCorreo.setRoundTopLeft(30);
        pnlCorreo.setRoundTopRight(30);
        pnlCorreo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inputCorreo.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        inputCorreo.setText("Correo Electronico (Opcional)");
        inputCorreo.setBorder(null);
        inputCorreo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputCorreoFocusGained(evt);
            }
        });
        inputCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputCorreoKeyTyped(evt);
            }
        });
        pnlCorreo.add(inputCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 460, 30));

        add(pnlCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 220, 490, 50));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel2.setText("APELLIDO MATERNO:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 270, -1, -1));

        pnlApellidoMaterno.setBackground(new java.awt.Color(255, 255, 255));
        pnlApellidoMaterno.setRoundBottomLeft(30);
        pnlApellidoMaterno.setRoundBottomRight(30);
        pnlApellidoMaterno.setRoundTopLeft(30);
        pnlApellidoMaterno.setRoundTopRight(30);
        pnlApellidoMaterno.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inputApellidoMaterno.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        inputApellidoMaterno.setText("Apellido Materno (Opcional)");
        inputApellidoMaterno.setBorder(null);
        inputApellidoMaterno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputApellidoMaternoFocusGained(evt);
            }
        });
        inputApellidoMaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputApellidoMaternoKeyTyped(evt);
            }
        });
        pnlApellidoMaterno.add(inputApellidoMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 320, 30));

        add(pnlApellidoMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 310, 360, 50));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel3.setText("CORREO (OPCIONAL):");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 180, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/flecha.png"))); // NOI18N
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 190, -1, 20));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuario.png"))); // NOI18N
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 360, 40, 40));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuario.png"))); // NOI18N
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 360, 40, 40));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel11.setText("TELEFONO:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 270, -1, -1));

        tablaClientes.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Telefono", "Correo Electronico", "Nombre Completo", "Visitas", "Total Gastado", "Puntos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaClientes.setRowHeight(30);
        tablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaClientes);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 400, 840, 250));
    }// </editor-fold>//GEN-END:initComponents

    private void inputNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputNombreFocusGained
        if (inputNombre.getText().equals("Ingresar nombre(s)")) {
            limpiarCampos();

        }
    }//GEN-LAST:event_inputNombreFocusGained

    private void inputApellidoPaternoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputApellidoPaternoFocusGained
        if (inputApellidoPaterno.getText().equals("Ingresar apellido")) {
            limpiarCampos();
        }
    }//GEN-LAST:event_inputApellidoPaternoFocusGained

    private void btnBuscarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarClienteMouseClicked
        app.mostrarPantallaConsultarCliente();
    }//GEN-LAST:event_btnBuscarClienteMouseClicked

    private void inputTelefonoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputTelefonoFocusGained
        if (inputTelefono.getText().equals("Ingresar telefono")) {
            limpiarCampos();
        }
    }//GEN-LAST:event_inputTelefonoFocusGained

    private void inputCorreoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputCorreoFocusGained
        if (inputCorreo.getText().equals("Correo Electronico (Opcional)")) {
            limpiarCampos();
        }
    }//GEN-LAST:event_inputCorreoFocusGained

    private void inputApellidoMaternoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputApellidoMaternoFocusGained
        if (inputApellidoMaterno.getText().equals("Apellido Materno (Opcional)")) {
            limpiarCampos();
        }
    }//GEN-LAST:event_inputApellidoMaternoFocusGained

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        if (app.isSiguienteComanda()) {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Estas seguro de salir sin seleccionar cliente?", "Confirmar selección", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                app.mostrarPantallaComanda();
                app.setSiguienteComanda(false);
                //probablemente tenga que tener la logica para obtener los productos o detalles comanda.
            } else {
            }
        } else {
            app.mostrarPantallaRegistrarCliente();
        }
    }//GEN-LAST:event_jLabel7MouseClicked

    private void lblFrecuenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFrecuenteMouseClicked
        realizarBusqueda();
    }//GEN-LAST:event_lblFrecuenteMouseClicked

    private void tablaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClientesMouseClicked
        if (evt.getClickCount() == 2 && app.isSiguienteComanda()) { // Doble clic
            int fila = tablaClientes.getSelectedRow();

            if (fila != -1) {
                String telefono = String.valueOf(tablaClientes.getValueAt(fila, 0));
                String correo = String.valueOf(tablaClientes.getValueAt(fila, 1));
                String nombre = String.valueOf(tablaClientes.getValueAt(fila, 2));
                String visitas = String.valueOf(tablaClientes.getValueAt(fila, 3));
                String totalGastado = String.valueOf(tablaClientes.getValueAt(fila, 4));
                String puntos = String.valueOf(tablaClientes.getValueAt(fila, 5));
                if(nombre.equals("DATOS PARA")){ 
                    return;
                }
                String mensaje = "¿Deseas seleccionar este cliente?\n\n" + "Nombre completo: " + nombre + "\n" + "Teléfono: " + telefono + "\n"
                        + "Correo: " + correo + "\n" + "Visitas: " + visitas + "\n" + "Total gastado: $" + totalGastado + "\n"
                        + "Puntos acumulados: " + puntos;

                int respuesta = JOptionPane.showConfirmDialog(null, mensaje, "Confirmar selección", JOptionPane.YES_NO_OPTION);

                if (respuesta == JOptionPane.YES_OPTION) {
                    app.setClienteSeleccionado(new ClienteDTO(nombre,telefono)); // O puedes usar un ID si lo manejas // CREACION DE OTRO DTO PARA CLIENTE Y TELEFONO SOLAMENTE?

                    app.setSiguienteComanda(false);

                    // talvez aqui tenga que setear ese dato en algun lado , diferente. porque si se reconstruye y no lo muestra
                    // habra problemas.
                    app.reconstruirPantallaComanda();
                    app.mostrarPantallaComanda();
                }
            }
        }
    }//GEN-LAST:event_tablaClientesMouseClicked

    private void inputNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputNombreKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
            evt.consume();
        }    }//GEN-LAST:event_inputNombreKeyTyped

    private void inputApellidoPaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputApellidoPaternoKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_inputApellidoPaternoKeyTyped

    private void inputCorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputCorreoKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_inputCorreoKeyTyped

    private void inputApellidoMaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputApellidoMaternoKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_inputApellidoMaternoKeyTyped

    private void inputTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputTelefonoKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_inputTelefonoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBuscarCliente;
    private javax.swing.JTextField inputApellidoMaterno;
    private javax.swing.JTextField inputApellidoPaterno;
    private javax.swing.JTextField inputCorreo;
    private javax.swing.JTextField inputNombre;
    private javax.swing.JTextField inputTelefono;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBusquedaCliente;
    private javax.swing.JLabel lblFrecuente;
    private GUI.PanelRound pnlApellidoMaterno;
    private GUI.PanelRound pnlApellidoPaterno;
    private GUI.PanelRound pnlCorreo;
    private javax.swing.JPanel pnlHeader;
    private GUI.PanelRound pnlNombre;
    private GUI.PanelRound pnlTelefono;
    private javax.swing.JTable tablaClientes;
    // End of variables declaration//GEN-END:variables

    //Metodos que acceden a la base de datos.
    // Método para agregar DocumentListener a los JTextFields
    private void agregarDocumentListener(JTextField textField) {
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                realizarBusqueda();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                realizarBusqueda();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                realizarBusqueda();
            }
        });
    }

    // Método para realizar la búsqueda en el clienteBO
    private void realizarBusqueda() {
        // Crear el objeto ClienteDTO con campos separados
        ClienteDTO clienteFiltro = new ClienteDTO();

        String nombre = inputNombre.getText().trim();
        if (!nombre.isEmpty()) {
            clienteFiltro.setNombre(nombre);
        }

        String apellidoPaterno = inputApellidoPaterno.getText().trim();
        if (!apellidoPaterno.isEmpty()) {
            clienteFiltro.setApellidoPaterno(apellidoPaterno);
        }

        String apellidoMaterno = inputApellidoMaterno.getText().trim();
        if (!apellidoMaterno.isEmpty() && !apellidoMaterno.contains("Ingresar apellido")) {
            clienteFiltro.setApellidoMaterno(apellidoMaterno);
        }

        clienteFiltro.setTelefono(inputTelefono.getText().trim());

        String correo = inputCorreo.getText().trim();
        if (!correo.isEmpty()) {
            clienteFiltro.setCorreo(correo);
        }

        // Realizar la búsqueda en el BO
        List<ClienteDTO> clientesEncontrados = null;
        try {
            clientesEncontrados = app.buscarClientes(clienteFiltro);
        } catch (NegocioException ex) {
            Logger.getLogger(PantallaConsultarClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (clientesEncontrados == null) {
            actualizarTabla(clientesEncontrados);
        } else {
            actualizarTabla(clientesEncontrados);
        }

    }

    // Método para actualizar la tabla con los resultados de búsqueda
    private void actualizarTabla(List<ClienteDTO> clientes) {
        DefaultTableModel model = (DefaultTableModel) tablaClientes.getModel();
        model.setRowCount(0); // Limpiar tabla existente

        // Si no hay resultados, mostrar el mensaje en la tabla
        if (clientes == null || clientes.isEmpty()) {
            model.addRow(new Object[]{"      NO SE ", "ENCONTRARON", "DATOS PARA", "  ESTA", "BUSQUEDA.", ""});
        } else {
            // Llenar la tabla con los resultados
            for (ClienteDTO cliente : clientes) {
                model.addRow(new Object[]{
                    cliente.getCorreo(),
                    cliente.getTelefono(),
                    cliente.getNombre() + " " + cliente.getApellidoPaterno() + " " + cliente.getApellidoMaterno(),
                    cliente.getVisitasTotales(),
                    cliente.getTotalGastado(),
                    cliente.getPuntos()
                });
            }
        }
    }

    private void limpiarCampos() {
        inputNombre.setText("");
        inputApellidoPaterno.setText("");
        inputTelefono.setText("");
        inputCorreo.setText("");
        inputApellidoMaterno.setText("");
    }

    private void setearToolTips() {
        inputNombre.setToolTipText("Introduce el nombre de el cliente. (Puedes escribir uno o varios nombres.)");
        inputApellidoPaterno.setToolTipText("Introduce el apellido paterno de el cliente.");
        inputApellidoMaterno.setToolTipText("Introduce el apellido materno de el cliente. (OPCIONAL)");
        inputTelefono.setToolTipText("Introduce el telefono exacto de el cliente. (NO SE PUEDE BUSCAR PARCIALMENTE ESTE CAMPO.)");
        inputCorreo.setToolTipText("Introduce el correo electronico de el cliente (OPCIONAL)");

        ToolTipManager.sharedInstance().setInitialDelay(100);     // Espera 100 ms para aparecer
        ToolTipManager.sharedInstance().setDismissDelay(10000);   // Permanece visible 10 segundos
        ToolTipManager.sharedInstance().setReshowDelay(100);
    }

    public void cargarMetodosAuxiliares() {
        agregarDocumentListener(inputNombre);
        agregarDocumentListener(inputApellidoPaterno);
        agregarDocumentListener(inputApellidoMaterno);
        agregarDocumentListener(inputTelefono);
        agregarDocumentListener(inputCorreo);

        setearToolTips();
    }
}
