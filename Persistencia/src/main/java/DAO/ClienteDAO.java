package DAO;

import Entidades.Cliente;
import Entidades.ClienteFrecuente;
import conexion.Conexion;
import exception.PersistenciaException;
import interfaces.IClienteDAO;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.crypto.Cipher;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.crypto.spec.SecretKeySpec;

/**
 * DAO del Modulo ClientesFrecuentes
 *
 * @author Sebastian Moreno
 */
public class ClienteDAO implements IClienteDAO {

    private static ClienteDAO instanceClienteDAO;

    private ClienteDAO() {
    }

    public static ClienteDAO getInstanceDAO() {
        if (instanceClienteDAO == null) {
            instanceClienteDAO = new ClienteDAO();
        }
        return instanceClienteDAO;
    }

    /**
     * Metodo para guardar un cliente frecuente . Antes de persistir el cliente
     * , cambio el telefono en formato String a telefono encriptado para
     * proteger la informacion.
     *
     * @param clienteFrecuente el cliente a insertar en la base de datos
     * @return el cliente frecuente insertado con id.
     * @throws PersistenciaException si ocurre un error insertando el cliente en
     * la bd.
     */
    @Override
    public ClienteFrecuente guardarClienteFrecuente(ClienteFrecuente clienteFrecuente) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {

            String telefonoOriginal = clienteFrecuente.getTelefono();
            if (telefonoOriginal != null && !telefonoOriginal.isEmpty()) {
                String telefonoEncriptado = encriptarTelefono(telefonoOriginal);
                clienteFrecuente.setTelefono(telefonoEncriptado);
            }
            em.getTransaction().begin();
            em.persist(clienteFrecuente);
            em.getTransaction().commit();

            if (clienteFrecuente.getId() == null) {
                throw new PersistenciaException("Error: No se generó un ID para el cliente frecuente a guardar");
            }
            return clienteFrecuente;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("No se pudo registrar el cliente frecuente: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    /**
     * Metodo para obtener clientes frecuentes parcialmente por Nombres,Apellido
     * Paterno , Apellido Materno ,Numero minimo de visitas. Utiliza un string
     * builder para filtar por los campos que vengan encapsulados en el DTO.
     * Datos a considerar(Como le comente a la maestra , se utiliza el flush y
     * store mode Refresh) Con el fin de poder actualizar datos en tiempo real y
     * que sean reflejados.
     *
     * @param filtro el DTO con sus respectivos datos insertados por el cliente.
     * @return una lista de Clientes Frecuentes encontrados. Si no una lista
     * nula.
     * @throws PersistenciaException
     */
    @Override
    public List<ClienteFrecuente> obtenerClientesFrecuentes(ClienteFrecuente filtro) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        List<ClienteFrecuente> clientes = new ArrayList<>();

        try {
            em.getTransaction().begin();
            em.flush(); // Asegurar que los datos recientes estén reflejados

            StringBuilder jpql = new StringBuilder("SELECT c FROM ClienteFrecuente c WHERE 1=1");

            if (filtro.getNombre() != null && !filtro.getNombre().trim().isEmpty()) {
                jpql.append(" AND LOWER(c.nombre) LIKE :nombre");
            }
            if (filtro.getVisitas() != null) { // Verifica si visitas NO es null
                jpql.append(" AND c.visitas >= :visitas"); // Buscar clientes con al menos X visitas
            }
            if (filtro.getApellidoPaterno() != null && !filtro.getApellidoPaterno().trim().isEmpty()) {
                jpql.append(" AND LOWER(c.apellidoPaterno) LIKE :apellidoPaterno");
            }
            if (filtro.getApellidoMaterno() != null && !filtro.getApellidoMaterno().trim().isEmpty()) {
                jpql.append(" AND LOWER(c.apellidoMaterno) LIKE :apellidoMaterno");
            }

            TypedQuery<ClienteFrecuente> query = em.createQuery(jpql.toString(), ClienteFrecuente.class)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH");

            if (filtro.getNombre() != null && !filtro.getNombre().trim().isEmpty()) {
                query.setParameter("nombre", "%" + filtro.getNombre().toLowerCase() + "%");
            }
            if (filtro.getVisitas() != null) {
                query.setParameter("visitas", filtro.getVisitas());
            }
            if (filtro.getApellidoPaterno() != null && !filtro.getApellidoPaterno().trim().isEmpty()) {
                query.setParameter("apellidoPaterno", "%" + filtro.getApellidoPaterno().toLowerCase() + "%");
            }
            if (filtro.getApellidoMaterno() != null && !filtro.getApellidoMaterno().trim().isEmpty()) {
                query.setParameter("apellidoMaterno", "%" + filtro.getApellidoMaterno().toLowerCase() + "%");
            }

            clientes = query.getResultList();
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al buscar Clientes Frecuentes: " + e.getMessage(), e);
        } finally {
            em.close();
        }

        return clientes;
    }

    /**
     * Metodo para buscar clientes de forma parcial por los campos
     * Nombres,Apellido Paterno , Apellido Materno ,Telefono, Correo. Utiliza un
     * string builder para filtar por los campos que vengan encapsulados en el
     * DTO. Encripta el telefono desencapsulado para poder evaluarlo con el
     * telefono encriptado de la base de datos. Datos a considerar(Como le
     * comente a la maestra , se utiliza el flush y store mode Refresh) Con el
     * fin de poder actualizar datos en tiempo real y que sean reflejados.
     *
     * @param clienteFiltro Cliente Entity pasado como parametro con los
     * atributos a buscar parcialmente.
     * @return la lista de clientes encontrados por los parametros dados. Si no
     * una lista nula.
     * @throws PersistenciaException Si existe un error buscando los clientes.
     */
    @Override
    public List<Cliente> buscarClientes(Cliente clienteFiltro) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        List<Cliente> clientes = new ArrayList<>();

        try {
            em.getTransaction().begin(); // INICIAR TRANSACCIÓN
            // Asegurar que los cambios en la BD estén reflejados antes de la consulta
            em.flush();

            String telefonoAEvaluar = encriptarTelefono(clienteFiltro.getTelefono());

            StringBuilder jpql = new StringBuilder("SELECT c FROM Cliente c WHERE 1=1");

            if (clienteFiltro.getNombre() != null && !clienteFiltro.getNombre().trim().isEmpty()) {
                jpql.append(" AND LOWER(c.nombre) LIKE :nombre");
            }
            if (clienteFiltro.getApellidoPaterno() != null && !clienteFiltro.getApellidoPaterno().trim().isEmpty()) {
                jpql.append(" AND LOWER(c.apellidoPaterno) LIKE :apellidoPaterno");
            }
            if (clienteFiltro.getApellidoMaterno() != null && !clienteFiltro.getApellidoMaterno().trim().isEmpty()) {
                jpql.append(" AND LOWER(c.apellidoMaterno) LIKE :apellidoMaterno");
            }
            if (clienteFiltro.getTelefono() != null && !clienteFiltro.getTelefono().trim().isEmpty()) {
                jpql.append(" AND c.telefono LIKE :telefono");
            }
            if (clienteFiltro.getCorreo() != null && !clienteFiltro.getCorreo().trim().isEmpty()) {
                jpql.append(" AND c.correo LIKE :correo");
            }

            TypedQuery<Cliente> query = em.createQuery(jpql.toString(), Cliente.class)
                    .setHint("javax.persistence.cache.storeMode", "REFRESH"); // Evita caché esto lo tuve que hacer porque no encontraba los datos actualizados , se necesitaba refrescar la memoria.

            if (clienteFiltro.getNombre() != null && !clienteFiltro.getNombre().trim().isEmpty()) {
                query.setParameter("nombre", "%" + clienteFiltro.getNombre().toLowerCase() + "%");
            }
            if (clienteFiltro.getApellidoPaterno() != null && !clienteFiltro.getApellidoPaterno().trim().isEmpty()) {
                query.setParameter("apellidoPaterno", "%" + clienteFiltro.getApellidoPaterno().toLowerCase() + "%");
            }
            if (clienteFiltro.getApellidoMaterno() != null && !clienteFiltro.getApellidoMaterno().trim().isEmpty()) {
                query.setParameter("apellidoMaterno", "%" + clienteFiltro.getApellidoMaterno().toLowerCase() + "%");
            }

            if (clienteFiltro.getTelefono() != null && !clienteFiltro.getTelefono().trim().isEmpty()) {
                jpql.append(" AND c.telefono LIKE :telefono");
                query.setParameter("telefono", "%" + telefonoAEvaluar + "%");
            }

            if (clienteFiltro.getCorreo() != null && !clienteFiltro.getCorreo().trim().isEmpty()) {
                query.setParameter("correo", "%" + clienteFiltro.getCorreo() + "%");
            }

            clientes = query.getResultList();
            em.getTransaction().commit();

            // Desencriptar el teléfono para cada cliente
            for (Cliente cliente : clientes) {
                if (cliente.getTelefono() != null) {
                    String telefonoDesencriptado = desencriptarTelefono(cliente.getTelefono());
                    cliente.setTelefono(telefonoDesencriptado);
                }
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al buscar clientes: " + e.getMessage(), e);
        } finally {
            em.close();
        }

        return clientes;
    }

    /**
     * Metodo que valida si existe un telefono en la base de datos. Encripta la
     * cadena de texto del telefono pasado como parametro para poder evaluarlo
     * con el telefono encriptado de la base de datos.
     *
     * @param telefono telefono pasado como parametro.
     * @return True si existe , false si no existe.
     * @throws PersistenciaException Si encuentra un error verificando el
     * telefono.
     */
    @Override
    public boolean existeTelefono(String telefono) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            // Encriptar el teléfono antes de buscarlo
            String telefonoEncriptado = encriptarTelefono(telefono);

            Long count = em.createQuery(
                    "SELECT COUNT(c) FROM Cliente c WHERE c.telefono = :telefono", Long.class)
                    .setParameter("telefono", telefonoEncriptado)
                    .getSingleResult();

            return count > 0;
        } catch (Exception e) {
            throw new PersistenciaException("Error al verificar teléfono: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
    /**
     * Metodo para buscar un cliente por su telefono
     * @param telefono String con el telefono de el cliente
     * @return un Cliente consultado
     * @throws PersistenciaException  Si existe un error.
     */
    @Override
    public Cliente buscarClientePorTelefono(String telefono) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            String telefonoEncriptado = encriptarTelefono(telefono);

            TypedQuery<Cliente> query = em.createQuery(
                    "SELECT c FROM Cliente c WHERE c.telefono = :telefono", Cliente.class);
            query.setParameter("telefono", telefonoEncriptado);

            // Esto lanzará NoResultException si no se encuentra ningún cliente
            return query.getSingleResult();

        } catch (Exception e) {
            throw new PersistenciaException("Error al buscar cliente por teléfono: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
    //Metodos auxiliares:
    // Método para desencriptar el teléfono
    /**
     * Metodo auxiliar para desencriptar un telefono dado como parametro.
     * Utiliza una llave default para poder desencriptar. ("1234567890123456")
     *
     * @param telefonoEncriptado el telefono a desencriptar
     * @return cadena de String valida para comparar
     * @throws PersistenciaException
     */
    private String desencriptarTelefono(String telefonoEncriptado) throws PersistenciaException {
        try {
            // Clave utilizada para la encriptación
            String clave = "1234567890123456"; // 🔐 Clave AES
            javax.crypto.spec.SecretKeySpec key = new javax.crypto.spec.SecretKeySpec(clave.getBytes(), "AES");

            // Configurar el cifrador para desencriptar
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES");
            cipher.init(javax.crypto.Cipher.DECRYPT_MODE, key);

            // Decodificar de Base64 a bytes
            byte[] decoded = java.util.Base64.getDecoder().decode(telefonoEncriptado);

            // Desencriptar los bytes
            byte[] decrypted = cipher.doFinal(decoded);

            // Convertir los bytes a String
            return new String(decrypted);
        } catch (Exception e) {
            throw new PersistenciaException("Error al desencriptar el telefono: " + e.getMessage(), e);
        }
    }

    /**
     * Metodo para encriptar un telefono con AES Utiliza una llave default para
     * poder desencriptar. ("1234567890123456")
     *
     * @param telefono es el parametro a encriptar.
     * @return una cadena String encriptada.
     * @throws PersistenciaException si ocurre un error encriptando.
     */
    private String encriptarTelefono(String telefono) throws PersistenciaException {
        try {
            // Clave utilizada para la encriptación
            String clave = "1234567890123456";
            javax.crypto.spec.SecretKeySpec key = new javax.crypto.spec.SecretKeySpec(clave.getBytes(), "AES");

            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES");
            cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, key);

            // Encriptar el teléfono
            byte[] encrypted = cipher.doFinal(telefono.getBytes());

            // Codificar a Base64 para almacenar como String
            return java.util.Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new PersistenciaException("Error al encriptar el telefono: " + e.getMessage(), e);
        }
    }

}
