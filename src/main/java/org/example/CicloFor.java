package org.example;

import java.util.Scanner;

//Funciona con contadores. Iteración: cada vez que se cumple el código. Declaración, condición e incremento.
//++ incrementa la variable en un0, -- disminuye la variable en uno.
public class CicloFor {
    public static void main(String[]args) {
        Scanner scanner= new Scanner(System.in);
        try{
            double sumaCalificacion=0;
            for( int i=10; i!=0; i--){
                System.out.println("Ingrese la calificación: "+(11-i)+": ");
                double calificacion= scanner.nextDouble();
                sumaCalificacion=sumaCalificacion+calificacion;
                if(calificacion<0){
                    System.out.println("No hay calificaciones negativas");
                    break;
                }
                if((11-i)==10){
                    double promedio=sumaCalificacion/10;
                    System.out.println("Tu promedio es: "+promedio);
                }

            }
        } catch (Exception e) {
            System.out.println("Ingresa un valor númerico");
        }
    }

}
