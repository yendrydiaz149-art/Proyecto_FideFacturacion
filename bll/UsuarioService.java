package bll;

import dal.UsuarioDAOJDBC;
import java.util.List;
import bll.Usuario;

public class UsuarioService {
    private UsuarioDAOJDBC usuarioDAO;

    public UsuarioService() {
        usuarioDAO = new UsuarioDAOJDBC();
    }

    // Agrega un usuario a la BD
    public void agregarUsuario(Usuario usuario) throws Exception {
        usuarioDAO.insertar(usuario);
    }

    // Actualiza un usuario existente
    public void actualizarUsuario(Usuario usuario) throws Exception {
        usuarioDAO.actualizar(usuario);
    }

    // Elimina un usuario por ID
    public void eliminarUsuario(String id) throws Exception {
        usuarioDAO.eliminar(id);
    }

    // Busca un usuario por correo (útil para login)
    public Usuario buscarPorCorreo(String correo) throws Exception {
        return usuarioDAO.buscarPorCorreo(correo);
    }

    // Lista todos los usuarios
    public List<Usuario> listarUsuarios() throws Exception {
        return usuarioDAO.listar();
    }

    // Método de inicio de sesión
    public boolean iniciarSesion(String correo, String contrasena) throws Exception {
        Usuario u = usuarioDAO.buscarPorCorreo(correo);
        if (u != null && u.getContrasena().equals(contrasena)) {
            return true;
        }
        return false;
    }
}
