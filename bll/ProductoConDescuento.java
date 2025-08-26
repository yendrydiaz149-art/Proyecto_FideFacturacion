package bll;

public class ProductoConDescuento extends Producto {

    private double porcentajeDescuento;

    public ProductoConDescuento(String id, String nombre, double precio, int stock, double porcentajeDescuento) {
        super(id, nombre, precio, stock);
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public double getPrecioConDescuento() {
        return getPrecio() - (getPrecio() * porcentajeDescuento / 100);
    }
}

