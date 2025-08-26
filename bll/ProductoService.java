package bll;

import dal.ProductoDAOJDBC;
import java.util.List;

public class ProductoService {
    private ProductoDAOJDBC productoDAO;

    public ProductoService() {
        productoDAO = new ProductoDAOJDBC();
    }

    // Agrega un producto a la BD
    public void agregarProducto(Producto producto) throws Exception {
        productoDAO.insertar(producto);
    }

    // Actualiza un producto existente en la BD
    public void actualizarProducto(Producto producto) throws Exception {
        productoDAO.actualizar(producto);
    }

    // Elimina un producto por ID
    public void eliminarProducto(String id) throws Exception {
        productoDAO.eliminar(id);
    }

    // Busca un producto por ID
    public Producto buscarProducto(String id) throws Exception {
        return productoDAO.buscar(id);
    }

    // Lista todos los productos
    public List<Producto> listarProductos() throws Exception {
        return productoDAO.listar();
    }
    
    public List<Producto> buscarPorNombre(String nombre) throws Exception{
        return productoDAO.buscarPorNombre(nombre);
    }
}
