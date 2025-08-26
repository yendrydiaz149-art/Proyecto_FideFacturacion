package bll;

public class DetalleFactura {
    private Producto producto;
    private int cantidad;
    private double subtotal;

    public DetalleFactura(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.subtotal = producto.getPrecio() * cantidad;
    }

    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public double getSubtotal() { return subtotal; }
}

