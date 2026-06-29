package util;

import modelo.Usuario;

public class Sesion {

    private static Usuario usuarioActual;

    private Sesion() {
    }

    public static void iniciarSesion(Usuario usuario){

        usuarioActual = usuario;

    }

    public static Usuario getUsuarioActual(){

        return usuarioActual;

    }

    public static void cerrarSesion(){

        usuarioActual = null;

    }

    public static boolean haySesion(){

        return usuarioActual != null;

    }

}