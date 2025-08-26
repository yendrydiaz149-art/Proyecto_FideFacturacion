package bll;

public class BackupThread extends Thread {
    private String archivo;
    private Object datos;

    public BackupThread(String archivo, Object datos) {
        this.archivo = archivo;
        this.datos = datos;
    }

    @Override
    public void run() {
        try {
            System.out.println("Iniciando respaldo...");
            SerializacionService.guardarDatos((java.util.List<?>) datos, archivo);
            System.out.println("Respaldo completado en " + archivo);
        } catch (Exception e) {
            System.err.println("Error al realizar respaldo: " + e.getMessage());
        }
    }
}
