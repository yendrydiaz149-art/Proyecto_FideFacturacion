package gui;

import bll.ProductoService;
import bll.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ProductoFrame extends JFrame {
    private ProductoService productoService = new ProductoService();
    private JTable table;
    private DefaultTableModel modelo;

    private JTextField txtNombre, txtPrecio, txtStock;

    public ProductoFrame() {
        setTitle("Gestión de Productos");
        setSize(600,400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel panelForm = new JPanel(new GridLayout(4,2,5,5));
        panelForm.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelForm.add(txtNombre);

        panelForm.add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        panelForm.add(txtPrecio);

        panelForm.add(new JLabel("Stock:"));
        txtStock = new JTextField();
        panelForm.add(txtStock);

        JButton btnAgregar = new JButton("Agregar Producto");
        btnAgregar.addActionListener(this::agregarProducto);
        panelForm.add(btnAgregar);

        add(panelForm, BorderLayout.NORTH);

        modelo = new DefaultTableModel(new String[]{"ID","Nombre","Precio","Stock"},0);
        table = new JTable(modelo);
        add(new JScrollPane(table), BorderLayout.CENTER);

        cargarProductos();
    }

    private void cargarProductos() {
        try {
            modelo.setRowCount(0);
            for(Producto p : productoService.listarProductos()){
                modelo.addRow(new Object[]{p.getId(), p.getNombre(), p.getPrecio(), p.getStock()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar productos: "+e.getMessage());
        }
    }

    private void agregarProducto(ActionEvent e){
        try {
            String nombre = txtNombre.getText();
            double precio = Double.parseDouble(txtPrecio.getText());
            int stock = Integer.parseInt(txtStock.getText());
            Producto p = new Producto(null, nombre, precio, stock);
            productoService.agregarProducto(p);
            cargarProductos();
            txtNombre.setText(""); txtPrecio.setText(""); txtStock.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar producto: "+ex.getMessage());
        }
    }
}
