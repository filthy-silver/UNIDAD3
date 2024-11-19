package org.example;

import java.util.*;

public class Practica2 {

    static Random rdn = new Random();
    static Scanner sc = new Scanner(System.in);

    private static final int[] reintegro = new int[10];     //Definimos los valores de reintegro y bombo
    private static final int[] bombo = new int[49];

    private static int[] boleto = new int[7];               //Definimos el boleto

    static {                                                //Inicializamos los valores de reintegro y bombo
        for (int i = 0; i < reintegro.length; i++) {
            reintegro[i] = i;
        }
        for (int i = 0; i < bombo.length; i++) {
            bombo[i] = i+1;
        }
    }

    public static void main(String[] args) {

    System.out.println(
                    "██╗      █████╗     ██████╗ ██████╗ ██╗███╗   ███╗██╗████████╗██╗██╗   ██╗ █████╗ \n" +
                    "██║     ██╔══██╗    ██╔══██╗██╔══██╗██║████╗ ████║██║╚══██╔══╝██║██║   ██║██╔══██╗\n" +
                    "██║     ███████║    ██████╔╝██████╔╝██║██╔████╔██║██║   ██║   ██║██║   ██║███████║\n" +
                    "██║     ██╔══██║    ██╔═══╝ ██╔══██╗██║██║╚██╔╝██║██║   ██║   ██║╚██╗ ██╔╝██╔══██║\n" +
                    "███████╗██║  ██║    ██║     ██║  ██║██║██║ ╚═╝ ██║██║   ██║   ██║ ╚████╔╝ ██║  ██║\n" +
                    "╚══════╝╚═╝  ╚═╝    ╚═╝     ╚═╝  ╚═╝╚═╝╚═╝     ╚═╝╚═╝   ╚═╝   ╚═╝  ╚═══╝  ╚═╝  ╚═╝\n" +
                    "                                                                                  \n" +
                    "\n"); //Flamante ASCII Art
    boolean check = true;  //Variable de control

    do {                   //Bucle para introducir el boleto
        System.out.println("Introduce tu boleto: ");
        String boleto = sc.nextLine();
        if (!validadarBoleto(boleto)) {         //Si el boleto no es válido, se muestra un mensaje de error
            System.out.println("Formato no válido.");
        } else {
            check = false;                      //Si el boleto es válido, se sale del bucle
        }

    } while (check);

    laLoteria(6); //Llamada a la función laLoteria con 6 repeticiones
    }

    private static void laLoteria(int rep){
        int act = 0; //Variable de control para contar las repeticiones
        do{
            System.out.println("\nBoleto: " + Arrays.toString(boleto));

            Integer [] sorteo = sorteo();
            int complementario = sorteoComplementario(sorteo); //Llamada a la función sorteoComplementario
            int reintegro = reintegro();                        //Llamada a la función reintegro

            System.out.println("Complementario: " + complementario +
                    "\nReintegro: " + reintegro);

            int aciertos = 0;                           //Variable para contar los aciertos
            boolean hayReintegro = false;               //Variables de control para reintegro y complementario
            boolean hayComplementario = false;

//            System.out.println("\nDEBUG: SORTEO");
//            System.out.println("DEBUG: BOLETO → " + Arrays.toString(boleto) + " || SORTEO → " + Arrays.toString(sorteo));
            for (int i = 0; i < boleto.length-1; i++) {
                if (Arrays.asList(sorteo).contains(boleto[i])) {
//                    System.out.println("DEBUG: " + boleto[i] + " " + sorteo[i]);
                    aciertos++; //Si el número del boleto está en el sorteo, se incrementa el contador de aciertos
//                    System.out.println("DEBUG: hit " + aciertos);
                } else {
//                    System.out.println("DEBUG: miss " + boleto[i]);
                }
            }

//            System.out.println("\nDEBUG: COMPLEMENTARIO");
            for (int i = 0; i < boleto.length-1; i++) {
//                System.out.println("DEBUG: " + boleto[i] + " " + complementario);
                if (boleto[i] == complementario) {
                    hayComplementario = true;
                    break;
                }
            }

//            System.out.println("\nDEBUG: REINTEGRO");
            for (int i = 0; i < boleto.length-1; i++) {
//                System.out.println("DEBUG: " + boleto[i] + " " + reintegro);
                if (boleto[i] == reintegro) {
                    hayReintegro = true;
                    break;
                }
            }

            System.out.println("RESULTADOS:" +
                    "\nAciertos: " + aciertos);

            if (hayReintegro) {
                System.out.println("Reintegro.");
            }
            if (hayComplementario) {
                System.out.println("Complementario.");
            }
            act++;
        }while (act < rep);
    }

    private static boolean validadarBoleto(String arg) {

        boolean check = true;
        String [] aux;
        int [] boletoAux = new int[7];

        arg = arg.trim();

        if (arg.matches("\\d{1,2}-\\d{1,2}-\\d{1,2}-\\d{1,2}-\\d{1,2}-\\d{1,2}/\\d"))
        {
            aux = arg.split("[-/]");

            for (int i = 0; i < aux.length; i++) {
                boletoAux[i] = Integer.parseInt(aux[i]);
            }

            for (int i = 0; i < boletoAux.length; i++) {
                if (i != boletoAux.length-1) {
                    if (boletoAux[i] < 1 || boletoAux[i] > 49) {
                        check = false;
                        System.out.println("Error: [0a]");
                        break;
                    }
                } else {
                    if (boletoAux[i] < 0 || boletoAux[i] > 10) {
                        check = false;
                        System.out.println("Error: [1a]");
                        break;
                    }
                }
            }

        } else {
            return false;
        }
        if (check) {
            boleto = boletoAux;
            return true;
        } else {
            System.out.println("Error: valores incorrectos [1]");
            return false;
        }
    }
    private static Integer[] sorteo() {
        Integer [] sorteo = new Integer[6];
        for (int i = 0; i < sorteo.length; i++) {
            Integer aux = bombo();
            while (Arrays.asList(sorteo).contains(aux)) {
                aux = bombo();
            }
                sorteo[i] = aux;
        }
        System.out.println("Sorteo: " + Arrays.toString(sorteo));
        return sorteo;
    }
    private static int sorteoComplementario(Integer[] arg) {

        int complementario;

        do {
            complementario = bombo();

        } while (Arrays.asList(arg).contains(complementario));

        return complementario;
    }

    private static Integer reintegro() {
        return reintegro[rdn.nextInt(reintegro.length)];
    }

    private static Integer bombo() {
        return bombo[rdn.nextInt(bombo.length)];
    }
}


