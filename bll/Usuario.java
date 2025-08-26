package bll;

import java.io.Serializable;

public abstract class Usuario implements Serializable {
    protected String id;
    protected String nombre;
    protected String correo;

    public Usuario(String id, String nombre, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCorreo(String correo) { this.correo = correo; }

    // Polimorfismo
    public abstract String getRol();
}
