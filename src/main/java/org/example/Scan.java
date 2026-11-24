package org.example;

import java.util.Scanner;

public class Scan {
    public static void main(String[]args){
        //Libreria para capturar datos especificos //
        Scanner scanner= new Scanner(System.in);
        System.out.println("Ingrese el nombre del usuario");
        //Habilita la consola para leer el dato ingresado (nextline), solo para String//
        String nombre= scanner.nextLine();
        System.out.println("Tu nombre es "+nombre);

        System.out.println("Ingrese su edad");
        int edad= scanner.nextInt();
        System.out.println("Tu edad es "+edad);

        System.out.println("Ingrese su estatura");
        double estatura=scanner.nextDouble();
        System.out.println("Tu estatura es "+ estatura);
    }
}
