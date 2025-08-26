package bll;

import dal.ClienteDAOJDBC;
import java.util.List;

public class ClienteService {
    private ClienteDAOJDBC clienteDAO;

    public ClienteService() {
        clienteDAO = new ClienteDAOJDBC();
    }

    // Agrega un cliente a la BD
    public void agregarCliente(Cliente cliente) throws Exception {
        clienteDAO.insertar(cliente);
    }

    // Actualiza los datos de un cliente en la BD
    public void actualizarCliente(Cliente cliente) throws Exception {
        clienteDAO.actualizar(cliente);
    }

    // Elimina un cliente de la BD por ID
    public void eliminarCliente(String id) throws Exception {
        clienteDAO.eliminar(id);
    }

    // Busca un cliente por ID
    public Cliente buscarCliente(String id) throws Exception {
        return clienteDAO.buscar(id);
    }

    // Lista todos los clientes de la BD
    public List<Cliente> listarClientes() throws Exception {
        return clienteDAO.listar();
    }
}
