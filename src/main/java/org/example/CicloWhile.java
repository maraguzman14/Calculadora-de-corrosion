package org.example;
/*
@Version: 1.0.0
@Autor: Aymara Guzmán Velázquez
@Date: 05/11/26
 */
import java.util.Scanner;

public class CicloWhile {
    public static void main(String[]args){

        boolean continuar = true;
        double total = 0;
        Scanner scanner = new Scanner(System.in);

        while (continuar) {
            String nombre = "";
            boolean nombreInvalido = true;

            while (nombreInvalido) {
                System.out.println("Ingrese el nombre del producto:");
                nombre = scanner.nextLine().trim();
                if (nombre.isEmpty()) {
                    System.out.println("El nombre no puede estar vacío.");
                    continue;
                }
                if (nombre.matches(".*[A-Za-zÁÉÍÓÚáéíóúÑñ].*")) {
                    nombreInvalido = false;
                } else {
                    System.out.println("El nombre no puede tener solo números o símbolos.");
                }
            }
            double precio = 0;
            boolean precioInvalido = true;
            while (precioInvalido) {
                System.out.println("Ingrese el precio del producto:");
                try {
                    precio = scanner.nextDouble();
                    if (precio > 0) {
                        precioInvalido = false;
                    } else {
                        System.out.println("El precio debe ser mayor a 0.");
                    }
                } catch (Exception e) {
                    System.out.println("Ingrese números, por favor.");
                    scanner.nextLine();
                }
            }
            scanner.nextLine();
            int cantidad = 0;
            boolean cantidadInvalida = true;
            while (cantidadInvalida) {
                System.out.println("Ingrese la cantidad del producto:");
                try {
                    cantidad = scanner.nextInt();
                    if (cantidad > 0) {
                        cantidadInvalida = false;
                    } else {
                        System.out.println("La cantidad debe ser mayor a 0.");
                    }
                } catch (Exception e) {
                    System.out.println("Ingrese un número entero, por favor.");
                    scanner.nextLine();
                }
            }
            scanner.nextLine();
            double subTotal = precio * cantidad;
            total = total + subTotal;
            System.out.println("Producto: " + nombre);
            System.out.println("El subtotal es: $" + subTotal);
            int eleccion = 0;
            boolean eleccionInvalida = true;
            while (eleccionInvalida) {
                System.out.println("¿Deseas ingresar otro producto? 1 para sí, 2 para no:");
                try {
                    eleccion = scanner.nextInt();
                    if (eleccion == 1 || eleccion == 2) {
                        eleccionInvalida = false;
                    } else {
                        System.out.println("Opción inválida. Ingresa 1 o 2.");
                    }
                } catch (Exception e) {
                    System.out.println("Ingrese un número 1 o 2, por favor.");
                    scanner.nextLine();
                }
            }
            scanner.nextLine();

            if (eleccion == 2) {
                continuar = false;
            }
        }
        System.out.println("El total es: $" + total);
        scanner.close();
    }
}