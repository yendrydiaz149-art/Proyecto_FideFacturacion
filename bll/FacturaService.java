package bll;

import dal.FacturaDAOJDBC;
import java.util.List;

public class FacturaService {
    private FacturaDAOJDBC facturaDAO;

    public FacturaService() {
        facturaDAO = new FacturaDAOJDBC();
    }

    // Crea la factura en la BD junto con los detalles
    public void crearFactura(Factura factura) throws Exception {
        facturaDAO.insertar(factura);
    }

    public void eliminarFactura(String id) throws Exception {
        facturaDAO.eliminar(id);
    }

    public List<Factura> listarFacturas() throws Exception {
        return facturaDAO.listar();
    }

    public Factura buscarFactura(String id) throws Exception {
        return facturaDAO.buscar(id);
    }
}
