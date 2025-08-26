package bll;

import java.io.Serializable;

public class Producto implements Serializable {
    protected String codigo;
    protected String nombre;
    protected double precio;

    public Producto(String codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }

    // Polimorfismo
    public double calcularPrecioFinal() {
        return precio;
    }
}
