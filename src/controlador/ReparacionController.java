package controlador;

import modelo.Reparacion;

import java.util.Collection;
import java.util.HashMap;

public class ReparacionController {

    private HashMap<String, Reparacion> reparaciones;

    public ReparacionController() {
        reparaciones = new HashMap<>();
    }

    public boolean agregar(Reparacion reparacion){

        if(reparaciones.containsKey(reparacion.getOrden())){
            return false;
        }

        reparaciones.put(
                reparacion.getOrden(),
                reparacion
        );

        return true;
    }

    public Reparacion buscar(String orden){
        return reparaciones.get(orden);
    }

    public boolean actualizar(String orden,
                              String cliente,
                              String tipo,
                              String marca,
                              String modelo,
                              String falla,
                              String estado){

        Reparacion r = reparaciones.get(orden);

        if(r == null){
            return false;
        }

        r.setCliente(cliente);
        r.setTipoEquipo(tipo);
        r.setMarca(marca);
        r.setModelo(modelo);
        r.setFalla(falla);
        r.setEstado(estado);

        return true;
    }

    public boolean eliminar(String orden){

        if(reparaciones.containsKey(orden)){
            reparaciones.remove(orden);
            return true;
        }

        return false;
    }

    public Collection<Reparacion> listar(){
        return reparaciones.values();
    }
}