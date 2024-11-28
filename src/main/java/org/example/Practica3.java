package org.example;

import java.util.Scanner;

public class Practica3 {

    protected static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
    sopa();
    }

    protected static void sopa() {

        int x = 0, y = 0;
        boolean check = true;

        do {
            System.out.println("Introduce el número de filas: ");
            if (sc.hasNextInt()) {
                x = sc.nextInt();
                sc.nextLine();

                if (x < 1) {
                    System.out.println("El número de filas debe ser mayor que 0");
                    continue;
                }

                check = false;

            } else {
                sc.nextLine();
                System.out.println("Introduce un número válido");
            }

        } while (check);

        check = true;

        do {
            System.out.println("Introduce el número de columnas: ");
            if (sc.hasNextInt()) {
                y = sc.nextInt();
                sc.nextLine();

                if (y < 1) {
                    System.out.println("El número de columnas debe ser mayor que 0");
                    continue;
                }

                check = false;

            } else {
                System.out.println("Introduce un número válido");
                sc.nextLine();
            }

        } while (check);

        char[][] sopa = new char[x][y];

        for (int i = 0; i < sopa.length; i++) {
            System.out.println("Introduce la fila " + (i + 1) + ": ");
            String aux = sc.nextLine();
            aux = aux.trim();

            if (aux.length() != y) {
                System.out.println("ERROR: La fila debe ser de " + y + " caracteres");
                return;

            } else if (!aux.matches("[a-zA-Z]+")) {
                System.out.println("ERROR: La fila solo puede contener letras");
                return;

            } else {
                sopa[i] = aux.toCharArray();
            }
        }

        for (int i = 0; i < sopa.length; i++) {
            for (int j = 0; j < sopa[i].length; j++) {
                System.out.print(sopa[i][j] + " ");
            }
            System.out.println();
        }

        String palabra = "";

        do {
            System.out.println("Introduce la palabra a buscar: ");
            palabra = sc.nextLine();

            if (palabra.isEmpty() || !palabra.matches("[a-zA-Z]+")) {
                System.out.println("La palabra no puede estar vacía ni contener números");
            }

        } while (palabra.isEmpty() || !palabra.matches("[a-zA-Z]+"));
        x = -1; y = -1;

        boolean found = false;

        for (int j = 0; j < sopa.length && !found; j++) {
            for (int k = 0; k < sopa[j].length && !found; k++) {
                for (int i = 0; i < palabra.length(); i++) {

                    if (k + i < sopa[j].length && Character.toLowerCase(sopa[j][k + i]) == Character.toLowerCase(palabra.charAt(i))) {
                        if (i == palabra.length() - 1) {
                        x = j;
                        y = k;
                        found = true;
                        break;
                        }
                } else {
                    break;
                }
            }

        if (!found) {
            for (int i = 0; i < palabra.length(); i++) {

                if (j + i < sopa.length && Character.toLowerCase(sopa[j + i][k]) == Character.toLowerCase(palabra.charAt(i))) {
                    if (i == palabra.length() - 1) {
                        x = j;
                        y = k;
                        found = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
    }
}

        if (!found) {
            System.out.println("La palabra no se encuentra en la sopa de letras");
        } else {
            System.out.println("La palabra se encuentra en la posición [" + x + "][" + y + "]");
        }
    }
}

