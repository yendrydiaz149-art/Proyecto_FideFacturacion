package dal;

import bll.Factura;
import java.util.List;

public interface FacturaDAO {

    void insertar(Factura f) throws Exception;

    void eliminar(String id) throws Exception;

    Factura buscar(String id) throws Exception;

    List<Factura> listar() throws Exception;
}
