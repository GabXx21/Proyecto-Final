package modelo;

public class Reparacion {

    private String orden;
    private String cliente;
    private String tipoEquipo;
    private String marca;
    private String modelo;
    private String falla;
    private String estado;

    public Reparacion(String orden,
                      String cliente,
                      String tipoEquipo,
                      String marca,
                      String modelo,
                      String falla,
                      String estado) {

        this.orden = orden;
        this.cliente = cliente;
        this.tipoEquipo = tipoEquipo;
        this.marca = marca;
        this.modelo = modelo;
        this.falla = falla;
        this.estado = estado;
    }

    public String getOrden() {
        return orden;
    }

    public String getCliente() {
        return cliente;
    }

    public String getTipoEquipo() {
        return tipoEquipo;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getFalla() {
        return falla;
    }

    public String getEstado() {
        return estado;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setTipoEquipo(String tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setFalla(String falla) {
        this.falla = falla;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}