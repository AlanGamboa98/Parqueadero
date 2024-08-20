/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueadero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

public class Main extends JFrame {
    private Parqueadero parqueadero = new Parqueadero();
    
    public Main() {
        setTitle("Sistema de Parqueadero");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }
    
    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        
        JButton btnIngresarVehiculo = new JButton("Ingresar Vehículo");
        JButton btnRegistrarSalida = new JButton("Registrar Salida");
        JButton btnConsultarEstado = new JButton("Consultar Estado");
        JButton btnGenerarReporte = new JButton("Generar Reporte");
        JButton btnSalir = new JButton("Salir");
        
        btnIngresarVehiculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ingresarVehiculo();
            }
        });
        
        btnRegistrarSalida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarSalida();
            }
        });
        
        btnConsultarEstado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarEstado();
            }
        });
        
        btnGenerarReporte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarReporte();
            }
        });
        
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        panel.add(btnIngresarVehiculo);
        panel.add(btnRegistrarSalida);
        panel.add(btnConsultarEstado);
        panel.add(btnGenerarReporte);
        panel.add(btnSalir);
        
        add(panel);
    }
    
    private void ingresarVehiculo() {
        String tipo = JOptionPane.showInputDialog(this, "Ingrese el tipo de vehículo (Carro, Moto, Camion):");
        String placa = JOptionPane.showInputDialog(this, "Ingrese la placa del vehículo:");
        String marca = JOptionPane.showInputDialog(this, "Ingrese la marca del vehículo:");
        String modelo = JOptionPane.showInputDialog(this, "Ingrese el modelo del vehículo:");
        
        Vehiculo vehiculo = null;
        
        switch (tipo.toLowerCase()) {
            case "carro":
                String tipoCombustible = JOptionPane.showInputDialog(this, "Ingrese el tipo de combustible:");
                vehiculo = new Carro(placa, marca, modelo, LocalDateTime.now(), tipoCombustible);
                break;
            case "moto":
                int cilindraje = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el cilindraje:"));
                vehiculo = new Moto(placa, marca, modelo, LocalDateTime.now(), cilindraje);
                break;
            case "camion":
                double capacidadCarga = Double.parseDouble(JOptionPane.showInputDialog(this, "Ingrese la capacidad de carga (toneladas):"));
                vehiculo = new Camion(placa, marca, modelo, LocalDateTime.now(), capacidadCarga);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Tipo de vehículo no reconocido.");
                return;
        }
        
        parqueadero.registrarEntrada(vehiculo);
        JOptionPane.showMessageDialog(this, "Vehículo ingresado con éxito.");
    }
    
    private void registrarSalida() {
        String placa = JOptionPane.showInputDialog(this, "Ingrese la placa del vehículo que desea registrar la salida:");
        double costo = parqueadero.registrarSalida(placa);
        
        if (costo > 0) {
            JOptionPane.showMessageDialog(this, "Vehículo retirado con éxito. Costo: $" + costo);
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró un vehículo con esa placa.");
        }
    }
    
    private void consultarEstado() {
        List<Vehiculo> vehiculos = parqueadero.consultarEstado();
        
        if (vehiculos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay vehículos en el parqueadero.");
        } else {
            StringBuilder estado = new StringBuilder("Vehículos presentes en el parqueadero:\n");
            for (Vehiculo vehiculo : vehiculos) {
                estado.append("Placa: ").append(vehiculo.getPlaca())
                      .append(", Marca: ").append(vehiculo.getMarca())
                      .append(", Modelo: ").append(vehiculo.getModelo())
                      .append(", Hora de entrada: ").append(vehiculo.getHoraEntrada())
                      .append("\n");
            }
            JOptionPane.showMessageDialog(this, estado.toString());
        }
    }
    
    private void generarReporte() {
        JOptionPane.showMessageDialog(this, "Funcionalidad de reporte aún no implementada.");
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Main ex = new Main();
            ex.setVisible(true);
        });
    }
}
