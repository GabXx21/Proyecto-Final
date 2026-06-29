package controlador;

public class AppController {

    private final InventarioController inventarioController;
    private final ReparacionController reparacionController;
    private final UsuarioController usuarioController;
    private final LoginController loginController;

    public AppController() {

        inventarioController = new InventarioController();
        reparacionController = new ReparacionController();
        usuarioController = new UsuarioController();

        // El Login utilizará el mismo UsuarioController
        loginController = new LoginController(usuarioController);

    }

    public InventarioController getInventarioController() {
        return inventarioController;
    }

    public ReparacionController getReparacionController() {
        return reparacionController;
    }

    public UsuarioController getUsuarioController() {
        return usuarioController;
    }

    public LoginController getLoginController() {
        return loginController;
    }

}