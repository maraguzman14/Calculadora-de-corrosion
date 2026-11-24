package org.example;

import java.util.Scanner;

public class AreaTriangulo {
    public static void main(String[] args){

        //Definimos Scanner para optener los datos del usuario
        Scanner scanner= new Scanner(System.in);
        System.out.println("--- CALCULA EL ÁREA DE UN TRIÁNGULO---");

        try{
            //Pedimos datos y declaramos variables
            System.out.println("Ingrese la altura:");
            Double altura= scanner.nextDouble();
            System.out.println("Ingrese la base:");
            Double base= scanner.nextDouble();

            if(altura>0  &&  base>0){
                //Realizamos la operación
                Double resul= (base*altura)/2;

                //Imprimimos resultados
                System.out.println("El área del triángulo es: "+resul+" cm²");
            }else{
                System.out.println("El dato no es correcto");
            }

        } catch (Exception e) {
            System.out.println("Tas menso");
        }

        System.out.println("--- GRACIAS POR USAR ESTE PROGRAMA ⸜(｡˃ ᵕ ˂ )⸝♡---");


    }
}
