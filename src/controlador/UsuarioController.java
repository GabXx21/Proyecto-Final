package controlador;

import modelo.Rol;
import modelo.Usuario;

import java.util.Collection;
import java.util.HashMap;

public class UsuarioController {

    private final HashMap<String, Usuario> usuarios;

    public UsuarioController() {

        usuarios = new HashMap<>();

        usuarios.put("admin",
                new Usuario(
                        "admin",
                        "admin123",
                        "Administrador General",
                        Rol.ADMIN,
                        true
                ));

        usuarios.put("tecnico",
                new Usuario(
                        "tecnico",
                        "tec123",
                        "Carlos Pérez",
                        Rol.TECNICO,
                        true
                ));
    }

    // ==========================
    // AGREGAR
    // ==========================

    public boolean agregarUsuario(Usuario usuario){

        if(usuarios.containsKey(usuario.getUsuario())){
            return false;
        }

        usuarios.put(
                usuario.getUsuario(),
                usuario
        );

        return true;
    }

    // ==========================
    // BUSCAR
    // ==========================

    public Usuario buscarUsuario(String usuario){

        return usuarios.get(usuario);

    }

    // ==========================
    // ACTUALIZAR
    // ==========================

    public boolean actualizarUsuario(String usuario,
                                     String password,
                                     String nombre,
                                     Rol rol,
                                     boolean activo){

        Usuario u = usuarios.get(usuario);

        if(u == null){

            return false;

        }

        u.setPassword(password);
        u.setNombre(nombre);
        u.setRol(rol);
        u.setActivo(activo);

        return true;

    }

    // ==========================
    // ELIMINAR
    // ==========================

    public boolean eliminarUsuario(String usuario){

        return usuarios.remove(usuario) != null;

    }

    // ==========================
    // LISTAR
    // ==========================

    public Collection<Usuario> listarUsuarios(){

        return usuarios.values();

    }

}