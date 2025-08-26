package gui;

import bll.ClienteService;
import bll.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ClienteFrame extends JFrame {
    private ClienteService clienteService = new ClienteService();
    private JTable table;
    private DefaultTableModel modelo;

    private JTextField txtNombre, txtTelefono, txtCorreo;

    public ClienteFrame() {
        setTitle("Gestión de Clientes");
        setSize(600,400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel panelForm = new JPanel(new GridLayout(4,2,5,5));
        panelForm.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelForm.add(txtNombre);

        panelForm.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panelForm.add(txtTelefono);

        panelForm.add(new JLabel("Correo:"));
        txtCorreo = new JTextField();
        panelForm.add(txtCorreo);

        JButton btnAgregar = new JButton("Agregar Cliente");
        btnAgregar.addActionListener(this::agregarCliente);
        panelForm.add(btnAgregar);

        add(panelForm, BorderLayout.NORTH);

        modelo = new DefaultTableModel(new String[]{"ID","Nombre","Teléfono","Correo"},0);
        table = new JTable(modelo);
        add(new JScrollPane(table), BorderLayout.CENTER);

        cargarClientes();
    }

    private void cargarClientes() {
        try {
            modelo.setRowCount(0);
            for(Cliente c : clienteService.listarClientes()){
                modelo.addRow(new Object[]{c.getId(), c.getNombre(), c.getTelefono(), c.getCorreo()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar clientes: "+e.getMessage());
        }
    }

    private void agregarCliente(ActionEvent e){
        try {
            Cliente c = new Cliente(null, txtNombre.getText(), txtTelefono.getText(), txtCorreo.getText());
            clienteService.agregarCliente(c);
            cargarClientes();
            txtNombre.setText(""); txtTelefono.setText(""); txtCorreo.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar cliente: "+ex.getMessage());
        }
    }
}
