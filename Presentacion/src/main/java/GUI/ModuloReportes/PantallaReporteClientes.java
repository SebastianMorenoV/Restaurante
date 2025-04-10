/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.ModuloReportes;

import DTOSalida.ClienteDTO;
import DTOSalida.ComandaDTO;
import GUI.Aplicacion;
import GUI.ModuloClientesFrecuentes.PantallaConsultarClientes;
import exception.NegocioException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import reportes.ReporteService;

/**
 *
 * @author Sebastian Moreno
 */
public class PantallaReporteClientes extends javax.swing.JPanel {

    /**
     * Creates new form PantallaReporteClientes
     */
    Aplicacion app;

    public PantallaReporteClientes(Aplicacion app) {
        this.app = app;
        initComponents();

        cargarMetodosAuxiliares();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBtnImprimirReporte = new GUI.PanelRound();
        btnImprimirReporte = new javax.swing.JLabel();
        linea = new javax.swing.JPanel();
        pnlHeader = new javax.swing.JPanel();
        txtTitulo = new javax.swing.JLabel();
        icnVolver = new javax.swing.JLabel();
        txtTitulo1 = new javax.swing.JLabel();
        pnlTabla = new javax.swing.JScrollPane();
        tablaReporteClientes = new javax.swing.JTable();
        txtBuscarPor = new javax.swing.JLabel();
        icnFlecha = new javax.swing.JLabel();
        pnlNombre = new GUI.PanelRound();
        inputNombres = new javax.swing.JTextField();
        icnFlecha1 = new javax.swing.JLabel();
        pnlApellidoPaterno = new GUI.PanelRound();
        inputVisitasMinimas = new javax.swing.JTextField();
        txtNumeroVisitas = new javax.swing.JLabel();
        txtApellidoPaterno = new javax.swing.JLabel();
        txtNombre = new javax.swing.JLabel();
        pnlNombre1 = new GUI.PanelRound();
        inputApellidoMaterno = new javax.swing.JTextField();
        icnFlecha2 = new javax.swing.JLabel();
        icnFlecha3 = new javax.swing.JLabel();
        icnFlecha4 = new javax.swing.JLabel();
        pnlNombre2 = new GUI.PanelRound();
        inputApellidoPaterno = new javax.swing.JTextField();

        setBackground(new java.awt.Color(216, 202, 179));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlBtnImprimirReporte.setBackground(new java.awt.Color(255, 255, 255));
        pnlBtnImprimirReporte.setRoundBottomLeft(30);
        pnlBtnImprimirReporte.setRoundBottomRight(30);
        pnlBtnImprimirReporte.setRoundTopLeft(30);
        pnlBtnImprimirReporte.setRoundTopRight(30);
        pnlBtnImprimirReporte.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnImprimirReporte.setFont(new java.awt.Font("Product Sans Infanity", 0, 28)); // NOI18N
        btnImprimirReporte.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnImprimirReporte.setText("Imprimir Reporte");
        btnImprimirReporte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImprimirReporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnImprimirReporteMouseClicked(evt);
            }
        });
        pnlBtnImprimirReporte.add(btnImprimirReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 300, 50));

        add(pnlBtnImprimirReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 530, 320, 50));

        linea.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout lineaLayout = new javax.swing.GroupLayout(linea);
        linea.setLayout(lineaLayout);
        lineaLayout.setHorizontalGroup(
            lineaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        lineaLayout.setVerticalGroup(
            lineaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        add(linea, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 1150, 4));

        pnlHeader.setBackground(new java.awt.Color(255, 255, 255));
        pnlHeader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTitulo.setBackground(new java.awt.Color(0, 0, 0));
        txtTitulo.setFont(new java.awt.Font("Playfair Display", 1, 100)); // NOI18N
        txtTitulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtTitulo.setText("Clientes Frecuentes");
        txtTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlHeader.add(txtTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 970, 120));

        icnVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/flechaAtras.png"))); // NOI18N
        icnVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        icnVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icnVolverMouseClicked(evt);
            }
        });
        pnlHeader.add(icnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        txtTitulo1.setBackground(new java.awt.Color(0, 0, 0));
        txtTitulo1.setFont(new java.awt.Font("Playfair Display", 1, 100)); // NOI18N
        txtTitulo1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtTitulo1.setText("Reporte de");
        txtTitulo1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlHeader.add(txtTitulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, -20, 540, 120));

        add(pnlHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 180));

        tablaReporteClientes.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        tablaReporteClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "No. visitas", "Total Gastado", "Puntos de Fidelidad", "Fecha de ultima comanda"
            }
        ));
        tablaReporteClientes.setRowHeight(30);
        pnlTabla.setViewportView(tablaReporteClientes);

        add(pnlTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 740, 310));

        txtBuscarPor.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        txtBuscarPor.setText("BUSCAR POR :");
        add(txtBuscarPor, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 188, 210, 30));

        icnFlecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/flecha.png"))); // NOI18N
        add(icnFlecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 80, 20));

        pnlNombre.setBackground(new java.awt.Color(255, 255, 255));
        pnlNombre.setRoundBottomLeft(30);
        pnlNombre.setRoundBottomRight(30);
        pnlNombre.setRoundTopLeft(30);
        pnlNombre.setRoundTopRight(30);
        pnlNombre.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inputNombres.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        inputNombres.setText("Ingresar nombres");
        inputNombres.setBorder(null);
        inputNombres.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputNombresFocusGained(evt);
            }
        });
        inputNombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputNombresKeyTyped(evt);
            }
        });
        pnlNombre.add(inputNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 270, 30));

        add(pnlNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 300, 50));

        icnFlecha1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/flecha.png"))); // NOI18N
        add(icnFlecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 80, 20));

        pnlApellidoPaterno.setBackground(new java.awt.Color(255, 255, 255));
        pnlApellidoPaterno.setRoundBottomLeft(30);
        pnlApellidoPaterno.setRoundBottomRight(30);
        pnlApellidoPaterno.setRoundTopLeft(30);
        pnlApellidoPaterno.setRoundTopRight(30);
        pnlApellidoPaterno.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inputVisitasMinimas.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        inputVisitasMinimas.setText("Visitas Minimas");
        inputVisitasMinimas.setBorder(null);
        inputVisitasMinimas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inputVisitasMinimasFocusGained(evt);
            }
        });
        inputVisitasMinimas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputVisitasMinimasKeyTyped(evt);
            }
        });
        pnlApellidoPaterno.add(inputVisitasMinimas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 260, 30));

        add(pnlApellidoPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 300, 50));

        txtNumeroVisitas.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        txtNumeroVisitas.setText("NUMERO VISITAS");
        add(txtNumeroVisitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, 250, -1));

        txtApellidoPaterno.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        txtApellidoPaterno.setText("APELLIDO MATERNO");
        add(txtApellidoPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 300, -1));

        txtNombre.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        txtNombre.setText("NOMBRES");
        add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 300, 30));

        pnlNombre1.setBackground(new java.awt.Color(255, 255, 255));
        pnlNombre1.setRoundBottomLeft(30);
        pnlNombre1.setRoundBottomRight(30);
        pnlNombre1.setRoundTopLeft(30);
        pnlNombre1.setRoundTopRight(30);
        pnlNombre1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inputApellidoMaterno.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        inputApellidoMaterno.setText("Ingresar Apellido Materno");
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
        pnlNombre1.add(inputApellidoMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 260, 30));

        add(pnlNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 300, 50));

        icnFlecha2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/flecha.png"))); // NOI18N
        add(icnFlecha2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 80, 20));

        icnFlecha3.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        icnFlecha3.setText("APELLIDO PATERNO");
        add(icnFlecha3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 300, -1));

        icnFlecha4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/flecha.png"))); // NOI18N
        add(icnFlecha4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 80, 20));

        pnlNombre2.setBackground(new java.awt.Color(255, 255, 255));
        pnlNombre2.setRoundBottomLeft(30);
        pnlNombre2.setRoundBottomRight(30);
        pnlNombre2.setRoundTopLeft(30);
        pnlNombre2.setRoundTopRight(30);
        pnlNombre2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inputApellidoPaterno.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        inputApellidoPaterno.setText("Ingresar Apellido Paterno");
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
        pnlNombre2.add(inputApellidoPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 270, 30));

        add(pnlNombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 300, 50));
    }// </editor-fold>//GEN-END:initComponents

    private void icnVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icnVolverMouseClicked
        app.mostrarMenuReportes();
    }//GEN-LAST:event_icnVolverMouseClicked

    private void btnImprimirReporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImprimirReporteMouseClicked
       generarReporte();
    }//GEN-LAST:event_btnImprimirReporteMouseClicked

    private void inputNombresFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputNombresFocusGained
        if (inputNombres.getText().equals("Ingresar nombres")) {
            limpiarCampos();
        }
    }//GEN-LAST:event_inputNombresFocusGained

    private void inputVisitasMinimasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputVisitasMinimasFocusGained
        if (inputVisitasMinimas.getText().equals("Visitas Minimas")) {
            limpiarCampos();
        }
    }//GEN-LAST:event_inputVisitasMinimasFocusGained

    private void inputApellidoMaternoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputApellidoMaternoFocusGained
        if (inputApellidoMaterno.getText().equals("Ingresar Apellido Materno")) {
            limpiarCampos();
        }
    }//GEN-LAST:event_inputApellidoMaternoFocusGained

    private void inputApellidoPaternoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_inputApellidoPaternoFocusGained
        if (inputApellidoPaterno.getText().equals("Ingresar Apellido Paterno")) {
            limpiarCampos();
        }
    }//GEN-LAST:event_inputApellidoPaternoFocusGained

    private void inputNombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputNombresKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_inputNombresKeyTyped

    private void inputApellidoPaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputApellidoPaternoKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_inputApellidoPaternoKeyTyped

    private void inputApellidoMaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputApellidoMaternoKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_inputApellidoMaternoKeyTyped

    private void inputVisitasMinimasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputVisitasMinimasKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_inputVisitasMinimasKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnImprimirReporte;
    private javax.swing.JLabel icnFlecha;
    private javax.swing.JLabel icnFlecha1;
    private javax.swing.JLabel icnFlecha2;
    private javax.swing.JLabel icnFlecha3;
    private javax.swing.JLabel icnFlecha4;
    private javax.swing.JLabel icnVolver;
    private javax.swing.JTextField inputApellidoMaterno;
    private javax.swing.JTextField inputApellidoPaterno;
    private javax.swing.JTextField inputNombres;
    private javax.swing.JTextField inputVisitasMinimas;
    private javax.swing.JPanel linea;
    private GUI.PanelRound pnlApellidoPaterno;
    private GUI.PanelRound pnlBtnImprimirReporte;
    private javax.swing.JPanel pnlHeader;
    private GUI.PanelRound pnlNombre;
    private GUI.PanelRound pnlNombre1;
    private GUI.PanelRound pnlNombre2;
    private javax.swing.JScrollPane pnlTabla;
    private javax.swing.JTable tablaReporteClientes;
    private javax.swing.JLabel txtApellidoPaterno;
    private javax.swing.JLabel txtBuscarPor;
    private javax.swing.JLabel txtNombre;
    private javax.swing.JLabel txtNumeroVisitas;
    private javax.swing.JLabel txtTitulo;
    private javax.swing.JLabel txtTitulo1;
    // End of variables declaration//GEN-END:variables

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

    private void realizarBusqueda() {
        // Obtener y limpiar los datos de entrada
        String nombre = inputNombres.getText().trim();
        String apellidoPaterno = inputApellidoPaterno.getText().trim();
        String apellidoMaterno = inputApellidoMaterno.getText().trim();
        String visitasTexto = inputVisitasMinimas.getText().trim();

        // Validar visitas mínimas
        int visitasMinimas = 0;
        if (!visitasTexto.isEmpty() && !visitasTexto.equalsIgnoreCase("Visitas Minimas")) {
            try {
                visitasMinimas = Integer.parseInt(visitasTexto);
            } catch (NumberFormatException e) {
                visitasMinimas = 0;
            }
        }

        // Crear el filtro
        ClienteDTO clienteFiltro = new ClienteDTO();
        clienteFiltro.setNombre(nombre);
        clienteFiltro.setApellidoPaterno(apellidoPaterno);
        clienteFiltro.setApellidoMaterno(apellidoMaterno);
        clienteFiltro.setVisitasTotales(visitasMinimas);

        // Realizar búsqueda
        List<ClienteDTO> clientesEncontrados = null;
        try {
            clientesEncontrados = app.buscarClienteReporte(clienteFiltro);
            
              List<ComandaDTO> comandas = app.obtenerUltimaComandaClientes(clientesEncontrados);
            
            for (int i = 0; i < clientesEncontrados.size(); i++) {
                ClienteDTO cliente = clientesEncontrados.get(i);
                ComandaDTO comanda = comandas.get(i);
                cliente.setUltimaComanda(comanda.getFechaHora());
            }
            
        } catch (NegocioException ex) {
            Logger.getLogger(PantallaConsultarClientes.class.getName()).log(Level.SEVERE, null, ex);
            clientesEncontrados = new ArrayList<>();
        }

        // Actualizar tabla (muestra mensaje si está vacía)
        actualizarTabla(clientesEncontrados != null ? clientesEncontrados : new ArrayList<>());
    }

    // Método para actualizar la tabla con los resultados de búsqueda
    private void actualizarTabla(List<ClienteDTO> clientes) {
        DefaultTableModel model = (DefaultTableModel) tablaReporteClientes.getModel();
        model.setRowCount(0); // Limpiar tabla existente

        // Llenar la tabla con los resultados
        for (ClienteDTO cliente : clientes) {
            model.addRow(new Object[]{cliente.getNombreCompleto(), cliente.getVisitasTotales(), cliente.getTotalGastado(), cliente.getPuntos(),cliente.getUltimaComandaFormateada()});
        }
    }

    private void setearToolTips() {
        inputNombres.setToolTipText("Introduce el nombre de el cliente. (Puedes escribir uno o varios nombres.)");
        inputApellidoPaterno.setToolTipText("Introduce el apellido paterno de el cliente.");
        inputApellidoMaterno.setToolTipText("Introduce el apellido materno de el cliente. (OPCIONAL)");
        inputVisitasMinimas.setToolTipText("Introduce el numero minimo de visitas por el que deseas buscar (Visitas fuera de ese numero no apareceran.)");

        ToolTipManager.sharedInstance().setInitialDelay(100);     // Espera 100 ms para aparecer
        ToolTipManager.sharedInstance().setDismissDelay(10000);   // Permanece visible 10 segundos
        ToolTipManager.sharedInstance().setReshowDelay(100);
    }

    private void limpiarCampos() {
        inputNombres.setText("");
        inputApellidoPaterno.setText("");
        inputApellidoMaterno.setText("");
        inputVisitasMinimas.setText("");
    }

    public void cargarMetodosAuxiliares() {
        agregarDocumentListener(inputNombres);
        agregarDocumentListener(inputVisitasMinimas);
        agregarDocumentListener(inputApellidoMaterno);
        agregarDocumentListener(inputApellidoPaterno);

        setearToolTips();
    }
    
    public void generarReporte(){
    try {
            // Obtener datos desde los inputs
            String nombre = inputNombres.getText().trim();
            String apellidoPaterno = inputApellidoPaterno.getText().trim();
            String apellidoMaterno = inputApellidoMaterno.getText().trim();
            String visitasTexto = inputVisitasMinimas.getText().trim();

            int visitasMinimas = 0;
            if (!visitasTexto.isEmpty() && !visitasTexto.equalsIgnoreCase("Visitas Minimas")) {
                try {
                    visitasMinimas = Integer.parseInt(visitasTexto);
                } catch (NumberFormatException e) {
                    visitasMinimas = 0;
                }
            }

            // Crear filtro
            ClienteDTO clienteFiltro = new ClienteDTO();
            clienteFiltro.setNombre(nombre);
            clienteFiltro.setApellidoPaterno(apellidoPaterno);
            clienteFiltro.setApellidoMaterno(apellidoMaterno);
            clienteFiltro.setVisitasTotales(visitasMinimas);

            // Consultar
            List<ClienteDTO> clientes = app.buscarClienteReporte(clienteFiltro);
                
            List<ComandaDTO> comandas = app.obtenerUltimaComandaClientes(clientes);
            
            for (int i = 0; i < clientes.size(); i++) {
                ClienteDTO cliente = clientes.get(i);
                ComandaDTO comanda = comandas.get(i);
                cliente.setUltimaComanda(comanda.getFechaHora());
            }
            
            if (clientes == null || clientes.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron clientes para el reporte.", "Sin datos", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Generar reporte
            ReporteService reporteService = new ReporteService();
            Map<String, Object> parametros = new HashMap<>();

            reporteService.generarReporteClientesFrecuentes(clientes, parametros);

        } catch (NegocioException | JRException ex) {
            Logger.getLogger(PantallaConsultarClientes.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al generar el reporte: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
