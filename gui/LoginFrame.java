package gui;

import bll.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private UsuarioService usuarioService = new UsuarioService();

    public LoginFrame() {
        setTitle("Login");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3,2,5,5));

        add(new JLabel("Correo:"));
        txtCorreo = new JTextField();
        add(txtCorreo);

        add(new JLabel("Contraseña:"));
        txtContrasena = new JPasswordField();
        add(txtContrasena);

        JButton btnLogin = new JButton("Ingresar");
        btnLogin.addActionListener(this::login);
        add(btnLogin);

        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(e -> System.exit(0));
        add(btnSalir);
    }

    private void login(ActionEvent e) {
        try {
            String correo = txtCorreo.getText();
            String pass = new String(txtContrasena.getPassword());

            if(usuarioService.iniciarSesion(correo, pass)){
                JOptionPane.showMessageDialog(this, "Bienvenido!");
                // Abrir menú principal
                new MenuFrame().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Correo o contraseña incorrectos.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al iniciar sesión: " + ex.getMessage());
        }
    }
}
