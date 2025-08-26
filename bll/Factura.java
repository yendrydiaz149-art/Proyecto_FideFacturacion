package bll;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Factura implements Serializable {

    private String numero;
    private Cliente cliente;
    private List<DetalleFactura> detalles;

    public Factura(String numero, Cliente cliente) {
        this.numero = numero;
        this.cliente = cliente;
        this.detalles = new ArrayList<>();
    }

    public void agregarDetalle(DetalleFactura detalle) {
        detalles.add(detalle);
    }

    public String getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<DetalleFactura> getDetalles() {
        return detalles;
    }

    public double calcularTotal() {
        return detalles.stream().mapToDouble(DetalleFactura::getSubtotal).sum();
    }
}
