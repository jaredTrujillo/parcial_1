import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

import modelos.*;

public class Frmenvios extends JFrame {

    private JTextField txtCliente, txtCodigo, txtPeso, txtDistancia;
    private JComboBox<String> comboTipo;
    private JTable tabla;
    private DefaultTableModel modelo;

    private ArrayList<Envio> listaEnvios;

    public Frmenvios() {

        listaEnvios = new ArrayList<>();

        setTitle("Sistema de Envíos");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 🔹 PANEL FORMULARIO
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));

        panel.add(new JLabel("Cliente:"));
        txtCliente = new JTextField();
        panel.add(txtCliente);

        panel.add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        panel.add(txtCodigo);

        panel.add(new JLabel("Peso (Kg):"));
        txtPeso = new JTextField();
        panel.add(txtPeso);

        panel.add(new JLabel("Distancia (Km):"));
        txtDistancia = new JTextField();
        panel.add(txtDistancia);

        panel.add(new JLabel("Tipo de Envío:"));
        comboTipo = new JComboBox<>(new String[] {
                "Terrestre", "Aéreo", "Marítimo"
        });
        panel.add(comboTipo);

        add(panel, BorderLayout.NORTH);

        // 🔹 TABLA
        modelo = new DefaultTableModel();
        modelo.addColumn("Cliente");
        modelo.addColumn("Código");
        modelo.addColumn("Tipo");
        modelo.addColumn("Peso");
        modelo.addColumn("Distancia");
        modelo.addColumn("Tarifa");

        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // 🔹 BOTONES (SOLO 2)
        JPanel panelBotones = new JPanel();

        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);

        add(panelBotones, BorderLayout.SOUTH);

        // 🔹 EVENTOS
        btnAgregar.addActionListener(e -> agregarEnvio());
        btnEliminar.addActionListener(e -> eliminarEnvio());
    }

    // 🔴 AGREGAR Y MOSTRAR INMEDIATAMENTE
    private void agregarEnvio() {

        try {
            String cliente = txtCliente.getText();
            String codigo = txtCodigo.getText();
            double peso = Double.parseDouble(txtPeso.getText());
            double distancia = Double.parseDouble(txtDistancia.getText());
            String tipo = comboTipo.getSelectedItem().toString();

            Envio envio = null;

            switch (tipo) {
                case "Terrestre":
                    envio = new EnvioTerrestre(cliente, codigo, peso, distancia);
                    break;

                case "Aéreo":
                    envio = new EnvioAereo(cliente, codigo, peso, distancia);
                    break;

                case "Marítimo":
                    envio = new EnvioMaritimo(cliente, codigo, peso, distancia);
                    break;
            }

            // Guardar en lista
            listaEnvios.add(envio);

            // 🔥 MOSTRAR INMEDIATAMENTE EN LA TABLA
            modelo.addRow(new Object[] {
                    envio.getCliente(),
                    envio.getCodigo(),
                    envio.getClass().getSimpleName(),
                    envio.getPesoKg(),
                    envio.getDistanciaKm(),
                    envio.calcularTarifa()
            });

            JOptionPane.showMessageDialog(this, "Envío agregado correctamente");

            limpiarCampos();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en los datos");
        }
    }

    // 🔴 ELIMINAR
    private void eliminarEnvio() {

        int fila = tabla.getSelectedRow();

        if (fila >= 0) {

            String codigo = modelo.getValueAt(fila, 1).toString();

            listaEnvios.removeIf(e -> e.getCodigo().equalsIgnoreCase(codigo));

            modelo.removeRow(fila);

            JOptionPane.showMessageDialog(this, "Envío eliminado");

        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un envío");
        }
    }

    private void limpiarCampos() {
        txtCliente.setText("");
        txtCodigo.setText("");
        txtPeso.setText("");
        txtDistancia.setText("");
    }
}