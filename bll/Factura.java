package negocio;

import java.util.ArrayList;
import java.util.List;

public class Factura {
    private String id;
    private Cliente cliente;
    private List<DetalleFactura> detalles;
    private double total;

    public Factura(String id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.detalles = new ArrayList<>();
        this.total = 0;
    }

    public String getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public List<DetalleFactura> getDetalles() { return detalles; }
    public double getTotal() { return total; }

    public void agregarDetalle(DetalleFactura detalle) {
        detalles.add(detalle);
        total += detalle.getSubtotal();
    }
}

