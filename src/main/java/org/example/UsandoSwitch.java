package org.example;

import java.util.Scanner;

public class UsandoSwitch {
    public static void main(String[]args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hola! que quieres hacer?");
        System.out.println("Opción 1: Quieres que te salude");
        System.out.println("Opción 2: Reprobar a todo el grupo");
        System.out.println("Opción 3: Calcular el área de un cuadrado");
        int opcion = scanner.nextInt();
        try{
            switch (opcion){
                case 1:
                    System.out.println("Dime tu nombre:");
                    String nom= scanner.next();
                    System.out.println("Hola "+nom);
                    break;
                case 2:
                    System.out.println("Todo el grupo reprobó");
                    System.out.println("Nos vemos en el extra");
                    break;
                case 3:
                    System.out.println("Introduce el lado del cuadrado:");
                    double lado=scanner.nextDouble();
                    double resultado= lado*lado;
                    System.out.println("El área del cuadrado es: "+resultado);
                    break;
                default:
                    System.out.println("Introduce una opción entre uno y tres");
                    break;
            }

        } catch (Exception e) {
            System.out.println("Error, introduce un número");
        }
    }
}
