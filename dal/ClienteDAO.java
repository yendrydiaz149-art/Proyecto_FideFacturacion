package dal;

import bll.Cliente;
import java.util.List;

public interface ClienteDAO {

    void insertar(Cliente cliente) throws Exception;

    void actualizar(Cliente cliente) throws Exception;

    void eliminar(String id) throws Exception;

    Cliente buscar(String id) throws Exception;

    List<Cliente> listar() throws Exception;
}
