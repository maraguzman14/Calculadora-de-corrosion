package org.example;
/*
@Version: 1.0.0
@Autor: Aymara Guzmán Velázquez
@Date: 19/10/26
 */
import java.util.*;
//Rectángulo y triángulo: perimetro y área
public class EjercicioSwitch {
    public static void main(String[]args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hola! Bienvenido al calculador de área y perimetro");
        System.out.println("Elige una de las opciones");
        System.out.println("Opción 1: Área del rectángulo");
        System.out.println("Opción 2: Perímetro del rectángulo");
        System.out.println("Opción 3: Área del triángulo");
        System.out.println("Opción 4: Perimetro del triángulo");
        int opcion = scanner.nextInt();
        try {
            switch (opcion){
                case 1:
                    System.out.println("Introduce la base del rectángulo: ");
                    double baseR = scanner.nextDouble();
                    System.out.println("Introduce la altura del rectángulo: ");
                    double altura = scanner.nextDouble();
                    if (baseR <= 0 || altura <=0 || baseR == altura){
                        System.out.println("No numeros negativos o ceros");
                    }else{
                        double resultado = baseR * altura;
                        System.out.println("El resultado es " + resultado);}
                    break;
                case 2:
                    System.out.println("Introduce la base del rectángulo: ");
                    double baseRec = scanner.nextDouble();
                    System.out.println("Introduce la altura del rectángulo: ");
                    double alturaRec = scanner.nextDouble();
                    if (baseRec <= 0 || alturaRec <=0 || baseRec == alturaRec){
                        System.out.println("No numeros negativos o ceros");
                    }else{
                        double resultadoPer = (2 * baseRec) + (2 * alturaRec);
                        System.out.println("El resultado es " + resultadoPer);}
                    break;
                case 3:
                    System.out.println("Ingrese la base del triángulo: ");
                    double base = scanner.nextDouble();

                    System.out.println("Ingrese la altura del triángulo: ");
                    double high = scanner.nextDouble();
                    if (base <= 0 || high <=0){
                        System.out.println("No hay valores negativos para los lados");
                    }else{
                        double areaTriangle = (base * high) / 2;
                        double roundedArea = Math.round(areaTriangle * 100) / 100.0;
                        System.out.println("El área de su triángulo es: " + roundedArea);}
                    break;

                case 4:
                    System.out.println("Ingrese un lado del triángulo: ");
                    double l1 = scanner.nextDouble();
                    System.out.println("Ingrese otro lado del triángulo: ");
                    double l2 = scanner.nextDouble();
                    System.out.println("Ingrese otro lado del triángulo: ");
                    double l3 = scanner.nextDouble();
                    if (l1 <= 0 || l2 <=0 || l3 <= 0){
                        System.out.println("No hay valores negativos para los lados");
                    }else {
                        double perimeterTriangle = l1 + l2 + l3;
                        double roundedPerimeter = Math.round(perimeterTriangle * 100) / 100.0;
                        System.out.println("El área de su triángulo es: " + roundedPerimeter);}
                    break;
                default:
                    System.out.println("Introduce una opción entre uno y tres");
                    break;
            }

        } catch (Exception e) {
            System.out.println("Por favor, introduce un número");
        }
    }
}
