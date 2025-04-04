package interfaces;

import DTOEntrada.CrearClienteDTO;
import DTOSalida.ClienteDTO;
import exception.NegocioException;
import java.util.List;

/**
 *
 * @author Sebastian Moreno
 */
public interface IClienteBO {

    public ClienteDTO registrarCliente(CrearClienteDTO clienteDTO) throws NegocioException;

    public List<ClienteDTO> buscarClientes(ClienteDTO clienteFiltroDTO) throws NegocioException;
    
    public List<ClienteDTO> buscarClienteReporte(ClienteDTO clienteFiltroClienteDTO) throws NegocioException;
}
