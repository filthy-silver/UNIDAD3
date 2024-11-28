package org.example;

import java.util.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final int[][] espacioCartesiano = new int[11][11];

    static {
        for (int[] ints : espacioCartesiano) {
            Arrays.fill(ints, 0);
        }
        for (int i = 0; i < espacioCartesiano.length; i++) {
            espacioCartesiano[i][espacioCartesiano.length/2] = -1;
            espacioCartesiano[espacioCartesiano.length/2][i] = -1;
        }
    }

    public static void main(String[] args) {
        boolean check = true;

        int[][] puntos = new int[2][2];

        System.out.println("Espacio cartesiano");
        System.out.println("Introduce el primer punto en el espacio cartesiano:");

        do{
            System.out.println("Formato: x,y");
            String aux = sc.nextLine();
            if (aux.matches("\\d,\\d")) {
                String[] coordenadas = aux.split(",");

                if (Integer.parseInt(coordenadas[0]) < -5 || Integer.parseInt(coordenadas[1]) > 5) {
                    System.out.println("Coordenadas fuera del rango.");
                    continue;
                }
                puntos[0][0] = Integer.parseInt(coordenadas[0]);
                puntos[0][1] = Integer.parseInt(coordenadas[1]);
                check = false;

            } else if (aux.matches("-\\d,-\\d")) {
                String[] coordenadas = aux.split(",");

                if (Integer.parseInt(coordenadas[0]) < -5 || Integer.parseInt(coordenadas[1]) > 5) {
                    System.out.println("Coordenadas fuera del rango.");
                    continue;
                }
                puntos[0][0] = Integer.parseInt(coordenadas[0]);
                puntos[0][1] = Integer.parseInt(coordenadas[1]);
                check = false;
            } else {
                System.out.println("Formato no válido.");
            }
        } while (check);

        check = true;

        System.out.println("Introduce el segundo punto en el espacio cartesiano:");

        do{
            System.out.println("Formato: x,y");
            String aux = sc.nextLine();
            if (aux.matches("\\d,\\d")) {
                String[] coordenadas = aux.split(",");

                if (Integer.parseInt(coordenadas[0]) < -5 || Integer.parseInt(coordenadas[1]) > 5) {
                    System.out.println("Coordenadas fuera del rango.");
                    continue;
                }
                puntos[1][0] = Integer.parseInt(coordenadas[0]);
                puntos[1][1] = Integer.parseInt(coordenadas[1]);
                check = false;

            }  else if (aux.matches("-\\d,-\\d")) {
                String[] coordenadas = aux.split(",");

                if (Integer.parseInt(coordenadas[0]) < -5 || Integer.parseInt(coordenadas[1]) > 5) {
                    System.out.println("Coordenadas fuera del rango.");
                    continue;
                }
                puntos[1][0] = Integer.parseInt(coordenadas[0]);
                puntos[1][1] = Integer.parseInt(coordenadas[1]);
                check = false;
            } else {
                System.out.println("Formato no válido.");
            }
        } while (check);

        System.out.println("\nPunto 1: (" + puntos[0][0] + "," + puntos[0][1] + ")\nPunto 2: (" + puntos[1][0] + "," + puntos[1][1] + ")");

        int [] vector1 = new int[2];
        int [] vector2 = new int[2];

        vector1[0] = puntos[0][0] + 5; vector1[1] =  puntos[0][1] + 5;
        vector2[0] = puntos[1][0] + 5; vector2[1] = puntos[1][1] + 5;

        espacioCartesiano[vector1[0]][vector1[1]] = 1;
        espacioCartesiano[vector2[0]][vector2[1]] = 1;

        int length = 0;
        if (Math.abs(Math.abs(vector2[0]) - Math.abs(vector1[0])) > Math.abs(Math.abs(vector2[1]) - Math.abs(vector1[1]))){
            length = Math.abs(Math.abs(vector2[0]) - Math.abs(vector1[0]));
        } else {
            length = Math.abs(Math.abs(vector2[1]) - Math.abs(vector1[1]));
        }
        int [][]  vector3 = new int [length][2];

        vector3[0][0] = vector1[0]; vector3[0][1] = vector1[1];

        if (vector1[0] < vector2[0]) {
            for (int i = 1; i < vector3.length; i++) {
                vector3[i][0] = vector3[i-1][0] + 1;
                vector3[i][1] = vector3[i-1][1] + 1;
            }
        } else if (vector1[0] > vector2[0]) {
            for (int i = 1; i < vector3.length; i++) {
                vector3[i][0] = vector3[i-1][0] - 1;
                vector3[i][1] = vector3[i-1][1] - 1;
            }
        }

        for (int[] ints : vector3) {
            espacioCartesiano[ints[0]][ints[1]] = 1;
        }

        System.out.println("Espacio cartesiano\n" +
                "   -5 -4 -3 -2 -1  0  1  2  3  4  5");

        for (int i= 0; i < espacioCartesiano.length; i++) {
            if (i - 5 >= 0) {
                System.out.print(" " + (i - 5) + " ");
            } else {
                System.out.print((i - 5) + " ");
            }

            for (int j = 0; j < espacioCartesiano[i].length; j++) {
                if (espacioCartesiano[i][j] == 0) {
                    System.out.print(" + ");
                } else if (espacioCartesiano[i][j] == -1) {
                    System.out.print(" * ");
                } else {
                    System.out.print(" ✪ ");
                }
            }
            System.out.print("\n");
        }
    }
}