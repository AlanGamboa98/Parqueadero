/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parqueadero;

/**
 *
 * @author alanp
 */
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Parqueadero {
    private final List<Vehiculo> vehiculos = new ArrayList<>();

    // Método para registrar la entrada de un vehículo al parqueadero
    public void registrarEntrada(Vehiculo vehiculo) {
        vehiculo.setHoraEntrada(LocalDateTime.now()); // Establece la hora de entrada actual
        vehiculos.add(vehiculo); // Añade el vehículo a la lista
    }

    // Método para registrar la salida de un vehículo y calcular el costo
    public double registrarSalida(String placa) {
        Vehiculo vehiculo = null;

        // Buscar el vehículo en la lista por su placa
        for (Vehiculo v : vehiculos) {
            if (v.getPlaca().equals(placa)) {
                vehiculo = v;
                break;
            }
        }

        // Si el vehículo fue encontrado, calcular el costo de la estancia
        if (vehiculo != null) {
            vehiculos.remove(vehiculo); // Elimina el vehículo de la lista
            LocalDateTime ahora = LocalDateTime.now(); // Hora actual para calcular el tiempo
            long horas = java.time.Duration.between(vehiculo.getHoraEntrada(), ahora).toHours() + 1;

            if (vehiculo instanceof Carro) {
                return horas * 2000; // Ejemplo: $2000 por hora para carros
            } else if (vehiculo instanceof Moto) {
                return horas * 1000; // Ejemplo: $1000 por hora para motos
            } else if (vehiculo instanceof Camion) {
                return horas * 5000; // Ejemplo: $5000 por hora para camiones
            }
        }

        return 0.0; // Si no se encuentra el vehículo, retornar 0
    }

    // Método para consultar el estado actual del parqueadero (vehículos presentes)
    public List<Vehiculo> consultarEstado() {
        return vehiculos;
    }
}

