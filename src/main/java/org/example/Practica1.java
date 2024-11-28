package org.example;

public class Practica1 {

        public static void main(String[] args) {

            int[][] matriz = new int [][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
            };

            System.out.println("Introduce un numero: ");
            int num = Integer.parseInt(System.console().readLine());

            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    if (matriz[i][j] == num) {
                        System.out.println("El numero " + num + " se encuentra en la posicion [" + i + "][" + j + "]");
                    }
                }
            }


        }
}
