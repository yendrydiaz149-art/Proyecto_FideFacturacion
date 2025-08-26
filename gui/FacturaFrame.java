package gui;

import bll.Cliente;
import bll.ClienteService;
import bll.DetalleFactura;
import bll.Factura;
import bll.FacturaService;
import bll.Producto;
import bll.ProductoService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class FacturaFrame extends JFrame {

    private JComboBox<String> cmbClientes;
    private JComboBox<String> cmbProductos;
    private JTextField txtCantidad;
    private JTable tblDetalle;
    private DefaultTableModel modeloTabla;
    private JLabel lblTotal;
    private JButton btnAgregar, btnGuardar;
    
    private ClienteService clienteService = new ClienteService();
    private ProductoService productoService = new ProductoService();
    private FacturaService facturaService = new FacturaService();

    public FacturaFrame() throws Exception {
        setTitle("Facturación");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        cargarClientes();
        cargarProductos();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        
        // Panel Superior (Cliente, Producto, Cantidad)
        JPanel panelSuperior = new JPanel(new FlowLayout());
        cmbClientes = new JComboBox<>();
        cmbProductos = new JComboBox<>();
        txtCantidad = new JTextField(5);
        btnAgregar = new JButton("Agregar");
        
        panelSuperior.add(new JLabel("Cliente:"));
        panelSuperior.add(cmbClientes);
        panelSuperior.add(new JLabel("Producto:"));
        panelSuperior.add(cmbProductos);
        panelSuperior.add(new JLabel("Cantidad:"));
        panelSuperior.add(txtCantidad);
        panelSuperior.add(btnAgregar);
        
        // Tabla Detalle
        modeloTabla = new DefaultTableModel(new Object[]{"Producto", "Cantidad", "Precio Unit.", "Subtotal"}, 0);
        tblDetalle = new JTable(modeloTabla);
        
        // Panel Inferior (Total y Guardar)
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lblTotal = new JLabel("Total: 0.00");
        btnGuardar = new JButton("Guardar y Generar PDF");
        panelInferior.add(lblTotal);
        panelInferior.add(btnGuardar);
        
        panel.add(panelSuperior, BorderLayout.NORTH);
        panel.add(new JScrollPane(tblDetalle), BorderLayout.CENTER);
        panel.add(panelInferior, BorderLayout.SOUTH);
        
        add(panel);
        
        // Eventos
        btnAgregar.addActionListener(e -> agregarProducto());
        btnGuardar.addActionListener(e -> guardarFactura());
    }

    private void cargarClientes() throws Exception {
        List<Cliente> clientes = clienteService.listarClientes();
        for (Cliente c : clientes) {
            cmbClientes.addItem(c.getId() + " - " + c.getNombre());
        }
    }

    private void cargarProductos() throws Exception {
        List<Producto> productos = productoService.listarProductos();
        for (Producto p : productos) {
            cmbProductos.addItem(p.getId() + " - " + p.getNombre());
        }
    }

    private void agregarProducto() {
        try {
            String prodSel = (String) cmbProductos.getSelectedItem();
            int prodId = Integer.parseInt(prodSel.split(" - ")[0]);
            Producto p = productoService.buscarProducto(String.valueOf(prodId));
            int cantidad = Integer.parseInt(txtCantidad.getText());

            double subtotal = p.getPrecio() * cantidad;
            modeloTabla.addRow(new Object[]{p.getNombre(), cantidad, p.getPrecio(), subtotal});
            actualizarTotal();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar producto: " + ex.getMessage());
        }
    }

    private void actualizarTotal() {
        double total = 0;
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            total += (double) modeloTabla.getValueAt(i, 3);
        }
        lblTotal.setText("Total: " + total);
    }

    private void guardarFactura() {
        try {
            String clienteSel = (String) cmbClientes.getSelectedItem();
            int clienteId = Integer.parseInt(clienteSel.split(" - ")[0]);

            Factura factura = new Factura();
            factura.setCliente(clienteService.buscarCliente(String.valueOf(clienteId)));

            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                DetalleFactura det = new DetalleFactura();
                String nombreProd = modeloTabla.getValueAt(i, 0).toString();
                System.out.println(nombreProd);
                List<Producto> p = productoService.buscarPorNombre(nombreProd);
                det.setProducto(p.getFirst());
                System.out.println(p.getFirst().getId());
                det.setCantidad((int) modeloTabla.getValueAt(i, 1));
                det.setPrecioUnitario((double) modeloTabla.getValueAt(i, 2));
                factura.agregarDetalle(det);
            }

            facturaService.crearFactura(factura);
            generarPDF(factura);
            JOptionPane.showMessageDialog(this, "Factura guardada y PDF generado");
            modeloTabla.setRowCount(0);
            actualizarTotal();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar factura: " + ex.getMessage());
        }
    }

    private void generarPDF(Factura factura) throws Exception {
    Document doc = new Document();
    String nombreArchivo = "Factura_" + System.currentTimeMillis() + ".pdf";
    PdfWriter.getInstance(doc, new FileOutputStream(nombreArchivo));
    doc.open();

    doc.add(new Paragraph("FACTURA"));
    doc.add(new Paragraph("Cliente: " + factura.getCliente().getNombre()));
    doc.add(new Paragraph("Fecha: " + new java.util.Date()));
    doc.add(new Paragraph(" "));

    PdfPTable table = new PdfPTable(4);
    table.addCell("Producto");
    table.addCell("Cantidad");
    table.addCell("Precio Unit.");
    table.addCell("Subtotal");

    double total = 0;
    for (DetalleFactura det : factura.getDetalles()) {
        table.addCell(det.getProducto().getNombre());
        table.addCell(String.valueOf(det.getCantidad()));
        table.addCell(String.valueOf(det.getPrecioUnitario()));
        double sub = det.getCantidad() * det.getPrecioUnitario();
        table.addCell(String.valueOf(sub));
        total += sub;
    }
    doc.add(table);
    doc.add(new Paragraph("Total: " + total));

    doc.close();
}
}
