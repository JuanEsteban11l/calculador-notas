package Algoritmia;

import java.util.Scanner;

public class calculador {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        System.out.print("Digite el número de estudiantes: ");
        n = scanner.nextInt();
        
        double notasPrimerCorte[] = rellenarNotasCorte(n, scanner, "primer");
        double notasSegundoCorte[] = rellenarNotasCorte(n, scanner, "segundo");
        double notasTercerCorte[] = rellenarNotasCorte(n, scanner, "tercer");
        
        double notasDefinitivas[] = calcularNotasDefinitivas(notasPrimerCorte, notasSegundoCorte, notasTercerCorte);
        
        System.out.println("Resultados iniciales:");
        imprimirResultados(notasDefinitivas);

        aumentarNotasPerdedores(notasDefinitivas);
        
        System.out.println("Resultados después de aumentar un 10% las notas de los estudiantes que perdieron:");
        imprimirResultados(notasDefinitivas);

        scanner.close();
    }

    public static double[] rellenarNotasCorte(int n, Scanner scanner, String corte) { 
        double notas[] = new double[n]; //Defnine el tamaño
        for (int i = 0; i < notas.length; i++) { //Recorre el arreglo
            do {
                System.out.print("Digite las notas del " + corte + " corte del estudiante " + (i+1) + ": ");
                notas[i] = scanner.nextDouble();
                if (notas[i] > 5 || notas[i] < 0) {
                    System.out.println("Error, las notas deben ser menores o iguales a 5 y mayores o iguales a 0.");
                }
            } while (notas[i] > 5 || notas[i] < 0);
        }
        return notas;
    }

    public static double[] calcularNotasDefinitivas(double[] notasPrimerCorte, double[] notasSegundoCorte, double[] notasTercerCorte) {
        int n = notasPrimerCorte.length;
        double[] notasFinales = new double[n];
        for (int i = 0; i < n; i++) {
            notasFinales[i] = notasPrimerCorte[i] * 0.30 + notasSegundoCorte[i] * 0.30 + notasTercerCorte[i] * 0.40;
        }
        return notasFinales;
    }

    public static void imprimirResultados(double[] notasDefinitivas) {
        int n = notasDefinitivas.length;
        int aprobados = 0;
        int perdedores = 0;
        double sumaNotas = 0;
        
        for (double nota : notasDefinitivas) { //Itera sobre cada nota
            if (nota >= 3) {
                aprobados++;
            } else {
                perdedores++;
            }
            sumaNotas += nota;
        }

        double promedio = sumaNotas / n;

        System.out.println("Estudiantes aprobados: " + aprobados);
        System.out.println("Estudiantes perdedores: " + perdedores);
        System.out.println("Promedio del curso: " + promedio);
    }

    public static void aumentarNotasPerdedores(double[] notasDefinitivas) {
        for (int i = 0; i < notasDefinitivas.length; i++) {
            if (notasDefinitivas[i] < 3) {
                notasDefinitivas[i] *= 1.10;
            }
        }
    }
}
