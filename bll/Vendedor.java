package bll;

public class Vendedor extends Usuario {

    public Vendedor(String id, String nombre, String correo) {
        super(id, nombre, correo);
    }

    @Override
    public String getRol() {
        return "Vendedor";
    }
}
