package controlador;

import modelo.Producto;

import java.util.Collection;
import java.util.HashMap;

public class InventarioController {

    private HashMap<String, Producto> productos;

    public InventarioController() {
        productos = new HashMap<>();
    }

    public boolean agregarProducto(Producto producto) {

        if(productos.containsKey(producto.getCodigo())){
            return false;
        }

        productos.put(producto.getCodigo(), producto);
        return true;
    }

    public Producto buscarProducto(String codigo){
        return productos.get(codigo);
    }

    public boolean actualizarProducto(String codigo,
                                      String nombre,
                                      String categoria,
                                      double precio,
                                      int stock){

        Producto producto = productos.get(codigo);

        if(producto == null){
            return false;
        }

        producto.setNombre(nombre);
        producto.setCategoria(categoria);
        producto.setPrecio(precio);
        producto.setStock(stock);

        return true;
    }

    public boolean eliminarProducto(String codigo){

        if(productos.containsKey(codigo)){
            productos.remove(codigo);
            return true;
        }

        return false;
    }

    public Collection<Producto> listarProductos(){
        return productos.values();
    }
}