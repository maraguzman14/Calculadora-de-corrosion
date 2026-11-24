package org.example;

import java.util.Scanner;

public class Mod {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa un:");
        int numero = scanner.nextInt();
        if (numero % 2==0){
            System.out.println("El número " +numero+ " es PAR");
        } else{
            System.out.println("El número " +numero+ " es IMPAR");
        }
    }
}
