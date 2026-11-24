package org.example;

import java.util.Scanner;

public class Ejemplo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al calculador de ecuaciones cuadráticas");
        try{
            System.out.println("Ingrese un número: ");
            int a = scanner.nextInt();
            System.out.println("Ingrese un segundo número: ");
            int b = scanner.nextInt();
            System.out.println("Ingrese un tercer número: ");
            int c = scanner.nextInt();
            if(a==0){
                System.out.println("Estás mal, tu a no puede ser 0 ");
            }else{
                double discriminante= (Math.pow(b,2)-(4*a*c));
                if(discriminante<0){
                    System.out.println("Estás mal, no existe raíz negativa");
                }else {
                    double xUno= (-b+Math.sqrt(discriminante))/2*a;
                    double xDos= (-b-Math.sqrt(discriminante))/2*a;
                    System.out.println("El resultado de X1 es: "+xUno);
                    System.out.println("El resultado de X2 es: "+xDos);
                }
            }
        } catch (Exception e) {
            System.out.println("Vales vrg usuario ");
        }
    }
}
/*
import java.util.Scanner;

public class HolaMundo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("DAME TU NOMBRE PERSONAL: ");
            String nombre = scanner.next();

            if (nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚÑñ ]+")) {
                if (nombre.length() > 2) {
                    System.out.println("TU NOMBRE ES: " + nombre);
                } else {
                    System.out.println("El nombre es demasiado corto, debe tener almenos 3 caracteres");
                }
            } else {
                System.out.println("El nombre solo debe tener letras");
            }
        } catch (Exception e) {
            System.out.println("ERROR DEL NOMBRE PERSONAL");
        }
    }
}
 */
