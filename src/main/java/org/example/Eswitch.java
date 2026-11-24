package org.example;

import java.util.Scanner;

public class Eswitch {
    public static void main(String[]args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el día de la semana");
        int dia= scanner.nextInt();

        switch (dia){
            case 1:
                System.out.println("El día es lunes");
                System.out.println("El día es lunes");
                break;
            case 2:
                System.out.println("El día es martes");
                System.out.println("El día es martes");
                break;
            default:
                System.out.println("No existe el día de la semana");
        }
    }
}
