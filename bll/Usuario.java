package negocio;

public class Usuario {
    private String id;
    private String nombre;
    private String correo;
    private String rol;
    private String contrasena;

    public Usuario(String id, String nombre, String correo, String rol, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
        this.contrasena = contrasena;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public String getRol() { return rol; }
    public String getContrasena() { return contrasena; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCorreo(String correo) { this.correo = correo; }
    public void setRol(String rol) { this.rol = rol; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
}

