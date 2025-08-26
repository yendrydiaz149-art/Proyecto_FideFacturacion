package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuFrame extends JFrame {
    private JButton btnClientes, btnProductos, btnFacturas;

    public MenuFrame() {
        setTitle("Menú Principal");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        btnClientes = new JButton("Gestión de Clientes");
        btnClientes.addActionListener(this::abrirClientes);
        add(btnClientes);

        btnProductos = new JButton("Gestión de Productos");
        btnProductos.addActionListener(this::abrirProductos);
        add(btnProductos);

        btnFacturas = new JButton("Generar Factura");
        btnFacturas.addActionListener(this::abrirFacturas);
        add(btnFacturas);
    }

    private void abrirClientes(ActionEvent e) {
        new ClienteFrame().setVisible(true);
    }

    private void abrirProductos(ActionEvent e) {
        new ProductoFrame().setVisible(true);
    }

    private void abrirFacturas(ActionEvent e) {
        try{
            new FacturaFrame().setVisible(true);
        }
        catch(Exception ew){
            
        }
        
    }
}
