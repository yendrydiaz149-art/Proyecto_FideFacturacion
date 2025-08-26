package bll;

public class ProductoConDescuento extends Producto {
    private double descuento; // porcentaje 0-100

    public ProductoConDescuento(String codigo, String nombre, double precio, double descuento) {
        super(codigo, nombre, precio);
        this.descuento = descuento;
    }

    public double getDescuento() { return descuento; }
    public void setDescuento(double descuento) { this.descuento = descuento; }

    @Override
    public double calcularPrecioFinal() {
        return precio - (precio * descuento / 100);
    }
}
