package dal;

import dal.DBConnection;
import dal.ProductoDAO;
import bll.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOJDBC implements ProductoDAO {

    @Override
    public void insertar(Producto p) throws Exception {
        String sql = "INSERT INTO productos(nombre, precio, stock) VALUES(?,?,?)";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getStock());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) p.setId(String.valueOf(rs.getInt(1)));
            }
        }
    }

    @Override
    public void actualizar(Producto p) throws Exception {
        String sql = "UPDATE productos SET nombre=?, precio=?, stock=? WHERE id=?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getStock());
            ps.setString(4, p.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(String id) throws Exception {
        String sql = "DELETE FROM productos WHERE id=?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Producto buscar(String id) throws Exception {
        String sql = "SELECT * FROM productos WHERE id=?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Producto(
                        String.valueOf(rs.getInt("id")),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Producto> listar() throws Exception {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Producto(
                    String.valueOf(rs.getInt("id")),
                    rs.getString("nombre"),
                    rs.getDouble("precio"),
                    rs.getInt("stock")
                ));
            }
        }
        return lista;
    }
    
    @Override
    public List<Producto> buscarPorNombre(String nombre) throws SQLException {
    List<Producto> productos = new ArrayList<>();
    String sql = "SELECT * FROM productos WHERE nombre LIKE ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, "%" + nombre + "%");

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(String.valueOf(rs.getInt("id")));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                productos.add(p);
            }
        }
    }

    return productos;
}

}
