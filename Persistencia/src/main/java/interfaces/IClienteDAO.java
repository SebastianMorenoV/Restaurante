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

    // Guardar un Cliente (puede ser normal o frecuente)
    public Cliente guardarCliente(Cliente cliente) throws PersistenciaException;

    // Guardar un ClienteFrecuente (opcional, ya que ClienteFrecuente hereda de Cliente)
    public ClienteFrecuente guardarClienteFrecuente(ClienteFrecuente clienteFrecuente) throws PersistenciaException;

    // Buscar un cliente por ID
    public Cliente buscarPorId(Long id) throws PersistenciaException;

    // Obtener todos los clientes
    public List<Cliente> obtenerTodos() throws PersistenciaException;

    // Obtener solo los clientes frecuentes
    public List<ClienteFrecuente> obtenerClientesFrecuentes(ClienteFrecuente cliente) throws PersistenciaException;

    // Actualizar datos de un cliente
    public Cliente actualizarCliente(Cliente cliente) throws PersistenciaException;

    // Eliminar un cliente por ID
    public boolean eliminar(Long id) throws PersistenciaException;

    public boolean existeTelefono(String telefono) throws PersistenciaException;

    public List<Cliente> buscarClientes(Cliente clienteFiltro) throws PersistenciaException;
    // probablemente tenga que meter asociarClienteAComanda
}
