package vista;

import controlador.InventarioController;
import controlador.ReparacionController;
import modelo.Producto;
import modelo.Reparacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ComputabCellForm extends JFrame {

    private JPanel panelPrincipal;
    private JTabbedPane tabbedPane1;

    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtStock;
    private JComboBox<String> cbCategoria;
    private JTable tblProductos;
    private JButton btnNuevoProducto;
    private JButton btnAgregarProducto;
    private JButton btnBuscarProducto;
    private JButton btnActualizarProducto;
    private JButton btnEliminarProducto;
    private JButton btnLimpiarProducto;

    private JTextField txtOrden;
    private JTextField txtCliente;
    private JTextField txtMarca;
    private JTextField txtModelo;
    private JTextArea txtAreaFalla;
    private JComboBox<String> cbTipoEquipo;
    private JComboBox<String> cbEstado;
    private JTable tblReparaciones;
    private JButton btnNuevaReparacion;
    private JButton btnRegistrarReparacion;
    private JButton btnBuscarReparacion;
    private JButton btnActualizarReparacion;
    private JButton btnEliminarReparacion;
    private JButton btnLimpiarReparacion;
    private int contadorOrden = 1;

    private final InventarioController inventarioController =
            new InventarioController();

    private final ReparacionController reparacionController =
            new ReparacionController();

    public ComputabCellForm() {

        setTitle("ComputabCell");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200,700);
        setLocationRelativeTo(null);

        txtOrden.setEditable(false);

        txtOrden.setText(
                generarOrden()
        );

        inicializarEventos();

        configurarSeleccionProductos();
        configurarSeleccionReparaciones();

        actualizarTablaProductos();
        actualizarTablaReparaciones();
    }

    private void inicializarEventos() {

        btnNuevoProducto.addActionListener(e -> {

            limpiarProducto();
            txtCodigo.requestFocus();
        });

        btnLimpiarProducto.addActionListener(e ->
                limpiarProducto());

        btnAgregarProducto.addActionListener(e ->
                agregarProducto());

        btnBuscarProducto.addActionListener(e ->
                buscarProducto());

        btnActualizarProducto.addActionListener(e ->
                actualizarProducto());

        btnEliminarProducto.addActionListener(e -> {

            int opcion =
                    JOptionPane.showConfirmDialog(

                            this,

                            "¿Desea eliminar este producto?",

                            "Confirmar",

                            JOptionPane.YES_NO_OPTION
                    );

            if(opcion == JOptionPane.YES_OPTION){

                eliminarProducto();
            }
        });

        btnNuevaReparacion.addActionListener(e -> {

            limpiarReparacion();

            txtOrden.setText(
                    generarOrden()
            );
        });

        btnLimpiarReparacion.addActionListener(e -> {

            limpiarReparacion();

            txtOrden.setText(
                    generarOrden()
            );
        });

        btnRegistrarReparacion.addActionListener(e ->
                registrarReparacion());

        btnBuscarReparacion.addActionListener(e ->
                buscarReparacion());

        btnActualizarReparacion.addActionListener(e ->
                actualizarReparacion());

        btnEliminarReparacion.addActionListener(e ->
                eliminarReparacion());
    }
    private boolean validarProducto() {

        if(txtCodigo.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Ingrese código");
            return false;
        }

        if(txtNombre.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Ingrese nombre");
            return false;
        }

        try{

            double precio =
                    Double.parseDouble(
                            txtPrecio.getText()
                    );

            int stock =
                    Integer.parseInt(
                            txtStock.getText()
                    );

            if(precio < 0){

                JOptionPane.showMessageDialog(
                        this,
                        "Precio inválido"
                );

                return false;
            }

            if(stock < 0){

                JOptionPane.showMessageDialog(
                        this,
                        "Stock inválido"
                );
                return false;
            }

        }catch(Exception ex){

            JOptionPane.showMessageDialog(
                    this,
                    "Precio y stock deben ser numéricos"
            );
            return false;
        }
        return true;
    }

    private void agregarProducto() {

        try {

            if(!validarProducto()) return;

            Producto producto = new Producto(
                    txtCodigo.getText(),
                    txtNombre.getText(),
                    cbCategoria.getSelectedItem().toString(),
                    Double.parseDouble(txtPrecio.getText()),
                    Integer.parseInt(txtStock.getText())
            );

            boolean agregado =
                    inventarioController.agregarProducto(producto);

            if(agregado){

                JOptionPane.showMessageDialog(this,
                        "Producto agregado");

                actualizarTablaProductos();
                limpiarProducto();

            }else{

                JOptionPane.showMessageDialog(this,
                        "Código existente");
            }

        } catch (Exception ex){

            JOptionPane.showMessageDialog(this,
                    "Datos inválidos");
        }
    }

    private void buscarProducto() {

        Producto p =
                inventarioController.buscarProducto(
                        txtCodigo.getText());

        if(p == null){

            JOptionPane.showMessageDialog(this,
                    "Producto no encontrado");
            return;
        }

        txtNombre.setText(p.getNombre());
        txtPrecio.setText(String.valueOf(p.getPrecio()));
        txtStock.setText(String.valueOf(p.getStock()));

        cbCategoria.setSelectedItem(
                p.getCategoria());
    }

    private void actualizarProducto() {

        try {

            boolean actualizado =
                    inventarioController.actualizarProducto(

                            txtCodigo.getText(),

                            txtNombre.getText(),

                            cbCategoria.getSelectedItem().toString(),

                            Double.parseDouble(
                                    txtPrecio.getText()),

                            Integer.parseInt(
                                    txtStock.getText())
                    );

            if(actualizado){

                JOptionPane.showMessageDialog(this,
                        "Producto actualizado");

                actualizarTablaProductos();

            }else{

                JOptionPane.showMessageDialog(this,
                        "Producto no existe");
            }

        } catch (Exception ex){

            JOptionPane.showMessageDialog(this,
                    "Datos inválidos");
        }
    }

    private void actualizarTablaProductos() {

        DefaultTableModel modelo =
                new DefaultTableModel(
                        null,
                        new String[]{
                                "Código",
                                "Nombre",
                                "Categoría",
                                "Precio",
                                "Stock"
                        }
                );

        for(Producto p :
                inventarioController.listarProductos()) {

            modelo.addRow(new Object[]{

                    p.getCodigo(),
                    p.getNombre(),
                    p.getCategoria(),
                    p.getPrecio(),
                    p.getStock()
            });
        }

        tblProductos.setModel(modelo);
    }

    private void configurarSeleccionProductos() {

        tblProductos.getSelectionModel()
                .addListSelectionListener(e -> {

                    if(e.getValueIsAdjusting()){
                        return;
                    }

                    int fila =
                            tblProductos.getSelectedRow();

                    if(fila == -1){
                        return;
                    }

                    txtCodigo.setText(
                            tblProductos.getValueAt(fila,0)
                                    .toString()
                    );

                    txtNombre.setText(
                            tblProductos.getValueAt(fila,1)
                                    .toString()
                    );

                    cbCategoria.setSelectedItem(
                            tblProductos.getValueAt(fila,2)
                    );

                    txtPrecio.setText(
                            tblProductos.getValueAt(fila,3)
                                    .toString()
                    );

                    txtStock.setText(
                            tblProductos.getValueAt(fila,4)
                                    .toString()
                    );
                });
    }

    private void eliminarProducto() {

        boolean eliminado =
                inventarioController.eliminarProducto(
                        txtCodigo.getText());

        if(eliminado){

            JOptionPane.showMessageDialog(this,
                    "Producto eliminado");

            actualizarTablaProductos();
            limpiarProducto();

        }else{

            JOptionPane.showMessageDialog(this,
                    "Producto no encontrado");
        }
    }

    private void limpiarProducto() {

        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        txtStock.setText("");

        cbCategoria.setSelectedIndex(0);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            ComputabCellForm ventana =
                    new ComputabCellForm();

            ventana.setVisible(true);
        });
    }

    // Reparaciones

    private boolean validarReparacion() {

        if(txtOrden.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Ingrese número de orden");
            return false;
        }

        if(txtCliente.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Ingrese cliente");
            return false;
        }

        if(txtMarca.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Ingrese marca");
            return false;
        }

        if(txtModelo.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Ingrese modelo");
            return false;
        }

        if(txtAreaFalla.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Ingrese descripción de la falla");
            return false;
        }

        return true;
    }

    private void registrarReparacion() {

        if(!validarReparacion()){
            return;
        }

        Reparacion reparacion = new Reparacion(

                txtOrden.getText(),

                txtCliente.getText(),

                cbTipoEquipo.getSelectedItem().toString(),

                txtMarca.getText(),

                txtModelo.getText(),

                txtAreaFalla.getText(),

                cbEstado.getSelectedItem().toString()
        );

        boolean agregado =
                reparacionController.agregar(reparacion);

        if(agregado){

            JOptionPane.showMessageDialog(this,
                    "Reparación registrada correctamente");

            actualizarTablaReparaciones();
            limpiarReparacion();

        }else{

            JOptionPane.showMessageDialog(this,
                    "La orden ya existe");
        }
    }

    private void buscarReparacion() {

        Reparacion reparacion =
                reparacionController.buscar(
                        txtOrden.getText()
                );

        if(reparacion == null){

            JOptionPane.showMessageDialog(this,
                    "Orden no encontrada");

            return;
        }

        txtCliente.setText(
                reparacion.getCliente()
        );

        txtMarca.setText(
                reparacion.getMarca()
        );

        txtModelo.setText(
                reparacion.getModelo()
        );

        txtAreaFalla.setText(
                reparacion.getFalla()
        );

        cbTipoEquipo.setSelectedItem(
                reparacion.getTipoEquipo()
        );

        cbEstado.setSelectedItem(
                reparacion.getEstado()
        );
    }

    private void actualizarReparacion() {

        boolean actualizado =
                reparacionController.actualizar(

                        txtOrden.getText(),

                        txtCliente.getText(),

                        cbTipoEquipo.getSelectedItem()
                                .toString(),

                        txtMarca.getText(),

                        txtModelo.getText(),

                        txtAreaFalla.getText(),

                        cbEstado.getSelectedItem()
                                .toString()
                );

        if(actualizado){

            JOptionPane.showMessageDialog(this,
                    "Reparación actualizada");

            actualizarTablaReparaciones();

        }else{

            JOptionPane.showMessageDialog(this,
                    "Orden no encontrada");
        }
    }

    private void eliminarReparacion() {

        int opcion =
                JOptionPane.showConfirmDialog(

                        this,

                        "¿Desea eliminar la orden?",

                        "Confirmar",

                        JOptionPane.YES_NO_OPTION
                );

        if(opcion != JOptionPane.YES_OPTION){
            return;
        }

        boolean eliminado =
                reparacionController.eliminar(
                        txtOrden.getText()
                );

        if(eliminado){

            JOptionPane.showMessageDialog(this,
                    "Orden eliminada");

            actualizarTablaReparaciones();
            limpiarReparacion();

        }else{

            JOptionPane.showMessageDialog(this,
                    "Orden no encontrada");
        }
    }

    private void limpiarReparacion() {

        txtOrden.setText("");
        txtCliente.setText("");
        txtMarca.setText("");
        txtModelo.setText("");
        txtAreaFalla.setText("");

        cbTipoEquipo.setSelectedIndex(0);
        cbEstado.setSelectedIndex(0);
    }

    private void actualizarTablaReparaciones() {

        DefaultTableModel modelo =
                new DefaultTableModel(
                        null,
                        new String[]{
                                "Orden",
                                "Cliente",
                                "Tipo",
                                "Marca",
                                "Modelo",
                                "Estado"
                        }
                );

        for(Reparacion r :
                reparacionController.listar()) {

            modelo.addRow(new Object[]{

                    r.getOrden(),
                    r.getCliente(),
                    r.getTipoEquipo(),
                    r.getMarca(),
                    r.getModelo(),
                    r.getEstado()
            });
        }

        tblReparaciones.setModel(modelo);
    }

    private void configurarSeleccionReparaciones() {

        tblReparaciones.getSelectionModel()
                .addListSelectionListener(e -> {

                    if(e.getValueIsAdjusting()){
                        return;
                    }

                    int fila =
                            tblReparaciones.getSelectedRow();

                    if(fila == -1){
                        return;
                    }

                    txtOrden.setText(
                            tblReparaciones.getValueAt(fila,0)
                                    .toString()
                    );

                    txtCliente.setText(
                            tblReparaciones.getValueAt(fila,1)
                                    .toString()
                    );

                    cbTipoEquipo.setSelectedItem(
                            tblReparaciones.getValueAt(fila,2)
                    );

                    txtMarca.setText(
                            tblReparaciones.getValueAt(fila,3)
                                    .toString()
                    );

                    txtModelo.setText(
                            tblReparaciones.getValueAt(fila,4)
                                    .toString()
                    );

                    cbEstado.setSelectedItem(
                            tblReparaciones.getValueAt(fila,5)
                    );
                });
    }

    private String generarOrden() {

        return String.format(
                "ORD%03d",
                contadorOrden++
        );
    }



}
