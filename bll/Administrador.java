package bll;

public class Administrador extends Usuario {
    public Administrador(String id, String nombre, String correo) {
        super(id, nombre, correo);
    }

    @Override
    public String getRol() {
        return "Administrador";
    }
}
