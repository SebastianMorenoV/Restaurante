
package interfaces;

import DTOEntrada.CrearClienteDTO;
import DTOSalida.ClienteDTO;
import exception.NegocioException;

/**
 *
 * @author Sebastian Moreno
 */
public interface IClienteBO {
    public ClienteDTO registrarCliente(CrearClienteDTO clienteDTO) throws NegocioException;
}
