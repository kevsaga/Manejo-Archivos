package manejoarchivos;

import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManejoArchivos {
    //Verificamos que exista el archivo
    public static void VerificarExistencia() {
        File archivo = new File("estudiantes.txt");
        if (archivo.exists()) {
            System.out.println("El archivo existe");
        } else {
            System.out.println("No existe el archivo");
            try {
                //si el archivo no existe creamos 3 datos iniciales
                PrintWriter pw = new PrintWriter(archivo);
                pw.println("Kevin, 19, 89");
                pw.println("Sara, 18, 91");
                pw.println("Luna, 19, 85");
                pw.close();
                System.out.println("Se ha creado un nuevo archivo");
            } catch (Exception e) {
                System.out.println("Error al crear el archivo");
            }
        }
    }
    //Muestra el contenido del archivo
    public static void MostrarContenido() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("estudiantes.txt"));
            String linea;
            System.out.println("Contenido:");
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error al mostar el rchivo");
        }
    }
    //Funsion que permite al usuario mirar el contenido de  una linea especifica
    public static void MostrarLinea() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese la linea que desea mostrar: ");
        int numeroLinea = sc.nextInt();
        try (BufferedReader br = new BufferedReader(new FileReader("estudiantes.txt"))) {
            String linea;
            int contador = 1;
            while ((linea = br.readLine()) != null) {
                if (contador == numeroLinea) {
                    System.out.println("Linea seleccionada: 3" + linea);
                    return;
                }
                contador++;
            }
            System.out.println("Línea no encontrada.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Funsion para fusionar  2 bloques de datos
   public static void FusionBloques() {
        int[] bloque1 = {10, 30, 50};
        int[] bloque2 = {20, 40, 60};
        int[] resultado = mezclar(bloque1, bloque2);
        System.out.println("\nResultado mezcla directa:");
        for (int num : resultado) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    public static int[] mezclar(int[] a, int[] b) {
        int[] resultado = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                resultado[k++] = a[i++];
            } else {
                resultado[k++] = b[j++];
            }
        }
        while (i < a.length) {
            resultado[k++] = a[i++];
        }
        while (j < b.length) {
            resultado[k++] = b[j++];
        }
        return resultado;
    }
    // Detectar corridas
    public static void DetectarCorridas(int[] arreglo) {
        System.out.println("\nCorridas naturales:");
        for (int i = 0; i < arreglo.length - 1; i++) {
            System.out.print(arreglo[i] + " ");
            if (arreglo[i] > arreglo[i + 1]) {
                System.out.println();
            }
        }
        System.out.println(arreglo[arreglo.length - 1]);
    }
    //funsion recursiva para contar la cantidad de estudiantes que sacaron notas > 90
  public static void RecursividadNotas() {
        List<Integer> notas = obtenerNotas();
        int cantidad = contarMayores(notas, 0);
        System.out.println("Cantidad de estudiantes con nota >= 90: " + cantidad);
    }
    public static List<Integer> obtenerNotas() {
        List<Integer> notas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("estudiantes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                notas.add(Integer.parseInt(partes[2].trim()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return notas;
    }
    public static int contarMayores(List<Integer> notas, int indice) {
        if (indice >= notas.size()) {
            return 0; // caso base
        }
        if (notas.get(indice) >= 90) {
            return 1 + contarMayores(notas, indice + 1);
        } else {
            return contarMayores(notas, indice + 1);
        }
    }


    public static void main(String[] args) {
        
        VerificarExistencia();
        MostrarContenido();
        MostrarLinea();
        FusionBloques();
        RecursividadNotas();
        
    }
}
