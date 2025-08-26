package dal;

import dal.UsuarioDAO;
import dal.DBConnection;
import bll.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOJDBC implements UsuarioDAO {

    @Override
    public void insertar(Usuario usuario) throws Exception {
        String sql = "INSERT INTO usuarios(nombre, correo, rol, contrasena) VALUES(?,?,?,?)";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getRol());
            ps.setString(4, usuario.getContrasena());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setId(String.valueOf(rs.getInt(1)));
                }
            }
        }
    }

    @Override
    public void actualizar(Usuario usuario) throws Exception {
        String sql = "UPDATE usuarios SET nombre=?, correo=?, rol=?, contrasena=? WHERE id=?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getRol());
            ps.setString(4, usuario.getContrasena());
            ps.setString(5, usuario.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(String id) throws Exception {
        String sql = "DELETE FROM usuarios WHERE id=?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Usuario buscarPorCorreo(String correo) throws Exception {
        String sql = "SELECT * FROM usuarios WHERE correo=?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                        String.valueOf(rs.getInt("id")),
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("rol"),
                        rs.getString("contrasena")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Usuario> listar() throws Exception {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Usuario(
                    String.valueOf(rs.getInt("id")),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getString("rol"),
                    rs.getString("contrasena")
                ));
            }
        }
        return lista;
    }
}
