package dal;

import bll.Usuario;
import java.util.List;

public interface UsuarioDAO {
    void insertar(Usuario usuario) throws Exception;
    void actualizar(Usuario usuario) throws Exception;
    void eliminar(String id) throws Exception;
    Usuario buscarPorCorreo(String correo) throws Exception;
    List<Usuario> listar() throws Exception;
}
