package org.example;

import java.util.Scanner;

public class SiNo {
    public static void main(String[]args){
        Scanner scanner=new Scanner(System.in);
        /*System.out.println("¿Cuál es tu edad?");
        int edad= scanner.nextInt();

        //if(edad>18 || edad==18){}
        if (edad>=18){
            System.out.println("Eres mayor de edad");
        }
        else if (edad<0){
            System.out.println("Ni siquiera has nacido");
            //Validacion anidada: condicion dentro de una condicion.//
        }
        else{
            System.out.println("Eres menor de edad");
        }
        System.out.println("Introduce tu nombre");
        String nombre= scanner.next();
        if(nombre.equals("Mara")){
            System.out.println("Eres la patrona");
        }
        else{
            System.out.println("¿Quién eres?");
        }
        System.out.println("Cerrando programa");*/
        /* && and
           || or
            ! not
           == solo para valores numericos
         */
        System.out.println("Introduce un número");
        double numUno=scanner.nextDouble();
        System.out.println("Introduce un número");
        double numDos=scanner.nextDouble();
        if (numUno>numDos){
            System.out.println("El número mayor es: "+numUno);
        }
        else if(numUno==numDos){
            System.out.println("Ambos números son iguales");
        }
        else{
            System.out.println("El número mayor es: "+numDos);
        }
    }
}
