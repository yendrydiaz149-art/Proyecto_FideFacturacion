package dal;

import dal.ClienteDAO;
import dal.DBConnection;
import bll.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOJDBC implements ClienteDAO {

    @Override
    public void insertar(Cliente cliente) throws Exception {
        String sql = "INSERT INTO clientes(nombre, correo, telefono) VALUES(?,?,?)";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getCorreo());
            ps.setString(3, cliente.getTelefono());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) cliente.setId(String.valueOf(rs.getInt(1)));
            }
        }
    }

    @Override
    public void actualizar(Cliente cliente) throws Exception {
        String sql = "UPDATE clientes SET nombre=?, correo=?, telefono=? WHERE id=?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getCorreo());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(String id) throws Exception {
        String sql = "DELETE FROM clientes WHERE id=?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Cliente buscar(String id) throws Exception {
        String sql = "SELECT * FROM clientes WHERE id=?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                        String.valueOf(rs.getInt("id")),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("correo")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Cliente> listar() throws Exception {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Cliente(
                    String.valueOf(rs.getInt("id")),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("correo")
                ));
            }
        }
        return lista;
    }
}

