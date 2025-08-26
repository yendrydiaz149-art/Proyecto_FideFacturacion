package bll;

import java.io.Serializable;

public class DetalleFactura implements Serializable {
    private Producto producto;
    private int cantidad;

    public DetalleFactura(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public double getSubtotal() { return producto.calcularPrecioFinal() * cantidad; }
}
