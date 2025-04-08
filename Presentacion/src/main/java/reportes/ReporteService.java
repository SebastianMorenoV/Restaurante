/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reportes;


import DTOSalida.ClienteDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.util.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReporteService {

    public void generarReporteClientesFrecuentes(List<ClienteDTO> clientes, Map<String, Object> parametros) throws JRException {
        // Ruta del archivo JRXML compilado
        JasperReport reporte = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reportes/ClientesFrecuentes.jrxml"));

        // Fuente de datos (lista de clientes)
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(clientes);

        // Rellenar el reporte con datos y parámetros
        JasperPrint print = JasperFillManager.fillReport(reporte, parametros, dataSource);

        // Mostrar el visor
        JasperViewer.viewReport(print, false);
    }
}
