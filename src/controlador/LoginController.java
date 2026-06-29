package controlador;

import modelo.Usuario;
import util.Sesion;

public class LoginController {

    private final UsuarioController usuarioController;

    public LoginController(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    public Usuario iniciarSesion(String usuario, String password) {

        Usuario u = usuarioController.buscarUsuario(usuario);

        if (u == null) {
            return null;
        }

        if (!u.isActivo()) {
            return null;
        }

        if (!u.getPassword().equals(password)) {
            return null;
        }

        Sesion.iniciarSesion(u);

        return u;
    }
}