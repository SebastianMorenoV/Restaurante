/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.ModuloClientesFrecuentes;

import DTOEntrada.CrearClienteDTO;
import DTOSalida.ClienteDTO;
import GUI.Aplicacion;
import exception.NegocioException;
import interfaces.IClienteBO;
import java.awt.Color;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Sebastian Moreno
 */
public class PantallaRegistrarCliente extends javax.swing.JPanel {

    private Aplicacion app;

    public PantallaRegistrarCliente(Aplicacion app) {
        this.app = app;
        initComponents();
        mostrarFecha();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pnlApellidoMaterno = new GUI.PanelRound();
        inputApellidoMaterno = new javax.swing.JTextField();
        pnlCorreo = new GUI.PanelRound();
        inputCorreo = new javax.swing.JTextField();
        pnlApellidoPaterno = new GUI.PanelRound();
        inputApellidoPaterno = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        pnlBtnGuardarCliente = new GUI.PanelRound();
        btnCancelar = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pnlTelefono = new GUI.PanelRound();
        inputTelefono = new javax.swing.JTextField();
        pnlNombre = new GUI.PanelRound();
        inputNombre = new javax.swing.JTextField();
        pnlBtnGuardarCliente1 = new GUI.PanelRound();
        btnBuscarCliente = new javax.swing.JLabel();
        pnlBtnGuardarCliente2 = new GUI.PanelRound();
        btnGuardarCliente = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        icnTiempo = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(216, 202, 179));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlHeader.setBackground(new java.awt.Color(255, 255, 255));
        pnlHeader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Playfair Display", 1, 100)); // NOI18N
        jLabel1.setText("Registrar Cliente");
        pnlHeader.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 840, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/flechaAtras.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        pnlHeader.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

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

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel2.setText("APELLIDO MATERNO (OPCIONAL):");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 400, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel3.setText("CORREO (OPCIONAL):");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 300, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel4.setText("APELLIDO PATERNO*:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, -1, -1));

        pnlApellidoMaterno.setBackground(new java.awt.Color(255, 255, 255));
        pnlApellidoMaterno.setRoundBottomLeft(30);
        pnlApellidoMaterno.setRoundBottomRight(30);
        pnlApellidoMaterno.setRoundTopLeft(30);
        pnlApellidoMaterno.setRoundTopRight(30);
        pnlApellidoMaterno.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inputApellidoMaterno.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        inputApellidoMaterno.setForeground(new java.awt.Color(0, 0, 0));
        inputApellidoMaterno.setText("Apellido Materno (Opcional)");
        inputApellidoMaterno.setBorder(null);
        inputApellidoMaterno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputApellidoMaternoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputApellidoMaternoFocusLost(evt);
            }
        });
        inputApellidoMaterno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inputApellidoMaternoMouseClicked(evt);
            }
        });
        pnlApellidoMaterno.add(inputApellidoMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 320, 30));

        add(pnlApellidoMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 440, 460, 50));

        pnlCorreo.setBackground(new java.awt.Color(255, 255, 255));
        pnlCorreo.setRoundBottomLeft(30);
        pnlCorreo.setRoundBottomRight(30);
        pnlCorreo.setRoundTopLeft(30);
        pnlCorreo.setRoundTopRight(30);
        pnlCorreo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inputCorreo.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        inputCorreo.setForeground(new java.awt.Color(0, 0, 0));
        inputCorreo.setText("Correo Electronico (Opcional)");
        inputCorreo.setBorder(null);
        inputCorreo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputCorreoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputCorreoFocusLost(evt);
            }
        });
        pnlCorreo.add(inputCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 320, 30));

        add(pnlCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 350, 440, 50));

        pnlApellidoPaterno.setBackground(new java.awt.Color(255, 255, 255));
        pnlApellidoPaterno.setRoundBottomLeft(30);
        pnlApellidoPaterno.setRoundBottomRight(30);
        pnlApellidoPaterno.setRoundTopLeft(30);
        pnlApellidoPaterno.setRoundTopRight(30);
        pnlApellidoPaterno.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inputApellidoPaterno.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        inputApellidoPaterno.setForeground(new java.awt.Color(0, 0, 0));
        inputApellidoPaterno.setText("Ingresar apellido");
        inputApellidoPaterno.setBorder(null);
        inputApellidoPaterno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputApellidoPaternoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputApellidoPaternoFocusLost(evt);
            }
        });
        pnlApellidoPaterno.add(inputApellidoPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 320, 30));

        add(pnlApellidoPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 460, 50));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel5.setText("NOMBRE(S)*:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, -1, -1));

        pnlBtnGuardarCliente.setBackground(new java.awt.Color(255, 255, 255));
        pnlBtnGuardarCliente.setRoundBottomLeft(30);
        pnlBtnGuardarCliente.setRoundBottomRight(30);
        pnlBtnGuardarCliente.setRoundTopLeft(30);
        pnlBtnGuardarCliente.setRoundTopRight(30);
        pnlBtnGuardarCliente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCancelar.setFont(new java.awt.Font("Product Sans Infanity", 0, 28)); // NOI18N
        btnCancelar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCancelar.setText("CANCELAR");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarMouseClicked(evt);
            }
        });
        pnlBtnGuardarCliente.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 160, 30));

        add(pnlBtnGuardarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 580, 180, 50));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel6.setText("TELEFONO*:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 200, -1, -1));

        pnlTelefono.setBackground(new java.awt.Color(255, 255, 255));
        pnlTelefono.setRoundBottomLeft(30);
        pnlTelefono.setRoundBottomRight(30);
        pnlTelefono.setRoundTopLeft(30);
        pnlTelefono.setRoundTopRight(30);
        pnlTelefono.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inputTelefono.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        inputTelefono.setForeground(new java.awt.Color(0, 0, 0));
        inputTelefono.setText("Ingresar telefono");
        inputTelefono.setBorder(null);
        inputTelefono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputTelefonoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputTelefonoFocusLost(evt);
            }
        });
        pnlTelefono.add(inputTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 320, 30));

        add(pnlTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 240, 350, 50));

        pnlNombre.setBackground(new java.awt.Color(255, 255, 255));
        pnlNombre.setRoundBottomLeft(30);
        pnlNombre.setRoundBottomRight(30);
        pnlNombre.setRoundTopLeft(30);
        pnlNombre.setRoundTopRight(30);
        pnlNombre.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inputNombre.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        inputNombre.setForeground(new java.awt.Color(0, 0, 0));
        inputNombre.setText("Ingresar nombre(s)");
        inputNombre.setBorder(null);
        inputNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputNombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inputNombreFocusLost(evt);
            }
        });
        pnlNombre.add(inputNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 320, 30));

        add(pnlNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 450, 50));

        pnlBtnGuardarCliente1.setBackground(new java.awt.Color(255, 255, 255));
        pnlBtnGuardarCliente1.setRoundBottomLeft(30);
        pnlBtnGuardarCliente1.setRoundBottomRight(30);
        pnlBtnGuardarCliente1.setRoundTopLeft(30);
        pnlBtnGuardarCliente1.setRoundTopRight(30);
        pnlBtnGuardarCliente1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBuscarCliente.setFont(new java.awt.Font("Product Sans Infanity", 0, 28)); // NOI18N
        btnBuscarCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnBuscarCliente.setText("BUSCAR UN CLIENTE");
        btnBuscarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarClienteMouseClicked(evt);
            }
        });
        pnlBtnGuardarCliente1.add(btnBuscarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 310, 50));

        add(pnlBtnGuardarCliente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 550, 320, 50));

        pnlBtnGuardarCliente2.setBackground(new java.awt.Color(255, 255, 255));
        pnlBtnGuardarCliente2.setRoundBottomLeft(30);
        pnlBtnGuardarCliente2.setRoundBottomRight(30);
        pnlBtnGuardarCliente2.setRoundTopLeft(30);
        pnlBtnGuardarCliente2.setRoundTopRight(30);
        pnlBtnGuardarCliente2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGuardarCliente.setFont(new java.awt.Font("Product Sans Infanity", 0, 28)); // NOI18N
        btnGuardarCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnGuardarCliente.setText("      GUARDAR CLIENTE");
        btnGuardarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGuardarClienteMouseClicked(evt);
            }
        });
        pnlBtnGuardarCliente2.add(btnGuardarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 320, 30));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuario.png"))); // NOI18N
        pnlBtnGuardarCliente2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 40, 50));

        add(pnlBtnGuardarCliente2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 510, 320, 50));

        lblHora.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        lblHora.setText("10:00 PM");
        add(lblHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 610, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/audiencia.png"))); // NOI18N
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 410, -1, -1));

        icnTiempo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tiempo.png"))); // NOI18N
        add(icnTiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 620, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/flecha.png"))); // NOI18N
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 310, -1, 20));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/flecha.png"))); // NOI18N
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, -1, 20));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/flecha.png"))); // NOI18N
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 410, -1, 20));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/flecha.png"))); // NOI18N
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 210, -1, 20));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/flecha.png"))); // NOI18N
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void inputApellidoMaternoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inputApellidoMaternoMouseClicked
        if (inputApellidoMaterno.getText().equalsIgnoreCase("Ingresar nombre de usuario")) {
            inputApellidoMaterno.setText("");
            inputApellidoMaterno.setForeground(Color.black);
        }
    }//GEN-LAST:event_inputApellidoMaternoMouseClicked

    private void btnGuardarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarClienteMouseClicked
        guardarCliente();
    }//GEN-LAST:event_btnGuardarClienteMouseClicked

    private void inputNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputNombreFocusGained
        if (inputNombre.getText().equals("Ingresar nombre(s)")) {
            inputNombre.setText("");
        }
    }//GEN-LAST:event_inputNombreFocusGained

    private void inputNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputNombreFocusLost
        if (inputNombre.getText().trim().isEmpty()) {
            inputNombre.setText("Ingresar nombre(s)");
        }
    }//GEN-LAST:event_inputNombreFocusLost

    private void inputApellidoPaternoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputApellidoPaternoFocusGained
        if (inputApellidoPaterno.getText().equals("Ingresar apellido")) {
            inputApellidoPaterno.setText("");
        }
    }//GEN-LAST:event_inputApellidoPaternoFocusGained

    private void inputApellidoPaternoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputApellidoPaternoFocusLost
        if (inputApellidoPaterno.getText().trim().isEmpty()) {
            inputApellidoPaterno.setText("Ingresar apellido");
        }
    }//GEN-LAST:event_inputApellidoPaternoFocusLost

    private void inputTelefonoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputTelefonoFocusGained
        if (inputTelefono.getText().equals("Ingresar telefono")) {
            inputTelefono.setText("");
        }
    }//GEN-LAST:event_inputTelefonoFocusGained

    private void inputTelefonoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputTelefonoFocusLost
        if (inputTelefono.getText().trim().isEmpty()) {
            inputTelefono.setText("Ingresar telefono");
        }
    }//GEN-LAST:event_inputTelefonoFocusLost

    private void inputApellidoMaternoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputApellidoMaternoFocusGained
        if (inputApellidoMaterno.getText().equals("Apellido Materno (Opcional)")) {
            inputApellidoMaterno.setText("");
        }
    }//GEN-LAST:event_inputApellidoMaternoFocusGained

    private void inputApellidoMaternoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputApellidoMaternoFocusLost
        if (inputApellidoMaterno.getText().trim().isEmpty()) {
            inputApellidoMaterno.setText("Apellido Materno (Opcional)");
        }
    }//GEN-LAST:event_inputApellidoMaternoFocusLost

    private void inputCorreoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputCorreoFocusGained
        if (inputCorreo.getText().equals("Correo Electronico (Opcional)")) {
            inputCorreo.setText("");
        }
    }//GEN-LAST:event_inputCorreoFocusGained

    private void inputCorreoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputCorreoFocusLost
        if (inputCorreo.getText().trim().isEmpty()) {
            inputCorreo.setText("Correo Electronico (Opcional)");
        }
    }//GEN-LAST:event_inputCorreoFocusLost

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        app.mostrarMenuPrincipal();
     }//GEN-LAST:event_jLabel7MouseClicked

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        app.mostrarMenuPrincipal();
    }//GEN-LAST:event_btnCancelarMouseClicked

    private void btnBuscarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarClienteMouseClicked
        app.mostrarPantallaConsultarCliente();
    }//GEN-LAST:event_btnBuscarClienteMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBuscarCliente;
    private javax.swing.JLabel btnCancelar;
    private javax.swing.JLabel btnGuardarCliente;
    private javax.swing.JLabel icnTiempo;
    private javax.swing.JTextField inputApellidoMaterno;
    private javax.swing.JTextField inputApellidoPaterno;
    private javax.swing.JTextField inputCorreo;
    private javax.swing.JTextField inputNombre;
    private javax.swing.JTextField inputTelefono;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblHora;
    private GUI.PanelRound pnlApellidoMaterno;
    private GUI.PanelRound pnlApellidoPaterno;
    private GUI.PanelRound pnlBtnGuardarCliente;
    private GUI.PanelRound pnlBtnGuardarCliente1;
    private GUI.PanelRound pnlBtnGuardarCliente2;
    private GUI.PanelRound pnlCorreo;
    private javax.swing.JPanel pnlHeader;
    private GUI.PanelRound pnlNombre;
    private GUI.PanelRound pnlTelefono;
    // End of variables declaration//GEN-END:variables

    //Metodos que acceden a la base de datos.
    public void guardarCliente() {
        String nombres = inputNombre.getText().trim();
        String apellidoPaterno = inputApellidoPaterno.getText().trim();
        String apellidoMaterno = inputApellidoMaterno.getText().trim();
        String telefono = inputTelefono.getText().trim();
        String correo = inputCorreo.getText().trim();

        if (nombres.isEmpty() || nombres.equals("Ingresar nombre(s)")
                || apellidoPaterno.isEmpty() || apellidoPaterno.equals("Ingresar apellido")
                || telefono.isEmpty() || telefono.equals("Ingresar telefono")) {
            JOptionPane.showMessageDialog(this, "Todos los campos (*) son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(apellidoMaterno.equals("Apellido Materno (Opcional)")){
            apellidoMaterno = "";
        }
        if(correo.equals("Correo Electronico (Opcional)")){
            correo = "";
        }
        
        String nombreCompleto = nombres + " " + apellidoPaterno + " " + apellidoMaterno;
        CrearClienteDTO cliente = new CrearClienteDTO(nombres, apellidoPaterno, apellidoMaterno, telefono, correo);

        try {
            app.registrarCliente(cliente);
            JOptionPane.showMessageDialog(this, "¡Cliente registrado con exito!\n " + nombreCompleto,
                    "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } catch (NegocioException ex) {
            // si algo sale mal
            JOptionPane.showMessageDialog(this, "Error al registrar el cliente : " + ex.getMessage(),
                    "Error en Registro", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Metodos Auxiliares.
    private void mostrarFecha() {
        Timer timer = new Timer(1000, e -> {
            LocalTime horaActual = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
            lblHora.setText(horaActual.format(formatter));
        });
        timer.start();
    }

    public void limpiarCampos() {
        inputNombre.setText("");
        inputApellidoPaterno.setText("");
        inputApellidoMaterno.setText("");
        inputTelefono.setText("");
        inputCorreo.setText("");
    }
}
