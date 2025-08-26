package dal;

import dal.DBConnection;
import dal.FacturaDAO;
import bll.Factura;
import bll.DetalleFactura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAOJDBC implements FacturaDAO {

    @Override
    public void insertar(Factura f) throws Exception {
        String sql = "INSERT INTO facturas(id_cliente, fecha, total) VALUES(?,?,?)";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, Integer.parseInt(f.getCliente().getId()));
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ps.setDouble(3, f.getTotal());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) f.setId(String.valueOf(rs.getInt(1)));
            }

            // Insertar detalles
            for (DetalleFactura d : f.getDetalles()) {
                String sqlDetalle = "INSERT INTO detalle_factura(id_factura, id_producto, cantidad, precio_unitario, subtotal) VALUES(?,?,?,?,?)";
                try (PreparedStatement psDet = cn.prepareStatement(sqlDetalle)) {
                    psDet.setInt(1, Integer.parseInt(f.getId()));
                    psDet.setInt(2, Integer.parseInt(d.getProducto().getId()));
                    psDet.setInt(3, d.getCantidad());
                    psDet.setDouble(4, d.getProducto().getPrecio());
                    psDet.setDouble(5, d.getSubtotal());
                    psDet.executeUpdate();
                }
            }
        }
    }

    @Override
    public void eliminar(String id) throws Exception {
        try (Connection cn = DBConnection.getConnection()) {
            String sqlDet = "DELETE FROM detalle_factura WHERE id_factura=?";
            try (PreparedStatement ps = cn.prepareStatement(sqlDet)) {
                ps.setString(1, id);
                ps.executeUpdate();
            }
            String sqlFac = "DELETE FROM facturas WHERE id=?";
            try (PreparedStatement ps = cn.prepareStatement(sqlFac)) {
                ps.setString(1, id);
                ps.executeUpdate();
            }
        }
    }

    @Override
    public Factura buscar(String id) throws Exception {
        // Implementación opcional: buscar la factura + detalles
        return null;
    }

    @Override
    public List<Factura> listar() throws Exception {
        // Implementación opcional
        return new ArrayList<>();
    }
}
