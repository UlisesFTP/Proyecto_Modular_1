package modelo;

public class Barbero {

    private int id;
    private String nombre;
    private String correo;
    private boolean estaDisponible;

    public Barbero(int id, String nombre, String correo, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.estaDisponible = activo;
    }

    public Barbero(String nombre, String correo, boolean activo) {
        this.nombre = nombre;
        this.correo = correo;
        this.estaDisponible = activo;
    }

    public Barbero() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isDisponible() {
        return estaDisponible;
    }

    public void setDisponible(boolean disponible) {
        this.estaDisponible = disponible;
    }

    @Override
    public String toString() {
        return "Barbero{" + "id=" + id + ", nombre=" + nombre + ", correo=" + correo + ", activo=" + estaDisponible + '}';
    }

}
