package vista;

import javax.swing.*;
import java.awt.*;
import controlador.AppController;
import controlador.LoginController;
import modelo.Usuario;

public class LoginForm {

    private JPanel panelPrincipal;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JCheckBox chkMostrarPassword;
    private JLabel lblIntentos;
    private JButton btnIngresar;
    private JButton btnSalir;
    private JLabel lblMensaje;

    private final AppController appController;
    private final LoginController loginController;

    private int intentos = 3;

    public LoginForm() {

        appController = new AppController();

        loginController =
                appController.getLoginController();

        chkMostrarPassword.addActionListener(e -> {

            if (chkMostrarPassword.isSelected()) {
                txtPassword.setEchoChar((char) 0);
            } else {
                txtPassword.setEchoChar('•');
            }

        });

        // Salir
        btnSalir.addActionListener(e -> System.exit(0));

        // Por ahora el botón ingresar no hace nada
        btnIngresar.addActionListener(e -> iniciarSesion());

    }
    public JPanel getPanelPrincipal() {

        return panelPrincipal;

    }

    private void iniciarSesion() {

        String usuario =
                txtUsuario.getText().trim();

        String password =
                String.valueOf(txtPassword.getPassword());

        if(usuario.isEmpty() || password.isEmpty()){

            lblMensaje.setText("Ingrese usuario y contraseña.");

            return;

        }

        Usuario u =
                loginController.iniciarSesion(
                        usuario,
                        password
                );

        if(u != null){

            JFrame frame =
                    new JFrame("ComputabCell");

            ComputabCellForm form =
                    new ComputabCellForm();

            frame.setContentPane(form.getPanelPrincipal());

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.pack();

            frame.setLocationRelativeTo(null);

            frame.setVisible(true);

            Window ventana =
                    SwingUtilities.getWindowAncestor(panelPrincipal);

            ventana.dispose();

            return;

        }

        intentos--;

        lblIntentos.setText(
                "Intentos restantes: " + intentos
        );

        lblMensaje.setText(
                "Usuario o contraseña incorrectos."
        );

        if(intentos == 0){

            JOptionPane.showMessageDialog(
                    null,
                    "Ha agotado los intentos."
            );

            System.exit(0);

        }

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame frame =
                    new JFrame("Login");

            LoginForm login =
                    new LoginForm();

            frame.setContentPane(
                    login.getPanelPrincipal()
            );

            frame.setDefaultCloseOperation(
                    JFrame.EXIT_ON_CLOSE
            );

            frame.pack();

            frame.setLocationRelativeTo(null);

            frame.setResizable(false);

            frame.setVisible(true);

        });

    }

}