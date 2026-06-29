package modelo;

public class Usuario {

    private String usuario;
    private String password;
    private String nombre;
    private Rol rol;
    private boolean activo;

    public Usuario(String usuario,
                   String password,
                   String nombre,
                   Rol rol,
                   boolean activo) {

        this.usuario = usuario;
        this.password = password;
        this.nombre = nombre;
        this.rol = rol;
        this.activo = activo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return nombre + " (" + rol + ")";
    }
}