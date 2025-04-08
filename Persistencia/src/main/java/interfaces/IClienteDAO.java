package interfaces;

import Entidades.Cliente;
import Entidades.ClienteFrecuente;
import exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author Sebastian Moreno
 */
public interface IClienteDAO {

   

    // Guardar un ClienteFrecuente (opcional, ya que ClienteFrecuente hereda de Cliente)
    public ClienteFrecuente guardarClienteFrecuente(ClienteFrecuente clienteFrecuente) throws PersistenciaException;

    // Obtener solo los clientes frecuentes
    public List<ClienteFrecuente> obtenerClientesFrecuentes(ClienteFrecuente cliente) throws PersistenciaException;

 
    public boolean existeTelefono(String telefono) throws PersistenciaException;

    public List<Cliente> buscarClientes(Cliente clienteFiltro) throws PersistenciaException;
    // probablemente tenga que meter asociarClienteAComanda
}
