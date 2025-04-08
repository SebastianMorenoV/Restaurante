/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reportes;

import DTOSalida.ClienteDTO;
import java.io.InputStream;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.util.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReporteService {

    public void generarReporteClientesFrecuentes(List<ClienteDTO> clientes, Map<String, Object> parametros) throws JRException {
        // Verificar si la lista está vacía
        if (clientes == null || clientes.isEmpty()) {
            throw new JRException("No se encontraron clientes para generar el reporte.");
        }

        // Ruta del archivo JRXML compilado
        InputStream input = getClass().getResourceAsStream("/reportes/clientesjrxml.jrxml");
        if (input == null) {
            throw new JRException("No se encontró el archivo ClientesFrecuentes.jrxml en la ruta especificada.");
        }

        JasperReport reporte = JasperCompileManager.compileReport(input);

        // Fuente de datos (lista de clientes)
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(clientes);

        // Agregar el parámetro ds al mapa
        parametros.put("ds", dataSource);

        // Rellenar el reporte con datos y parámetros
        JasperPrint print = JasperFillManager.fillReport(reporte, parametros, new JREmptyDataSource());

        // Mostrar el visor
        JasperViewer.viewReport(print, false);
    }
}
