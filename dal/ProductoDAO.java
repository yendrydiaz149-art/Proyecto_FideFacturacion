package dal;

import bll.Producto;
import java.util.List;

public interface ProductoDAO {

    void insertar(Producto p) throws Exception;

    void actualizar(Producto p) throws Exception;

    void eliminar(String id) throws Exception;

    Producto buscar(String id) throws Exception;

    List<Producto> listar() throws Exception;
    
    List<Producto> buscarPorNombre(String nombre) throws Exception;
}
