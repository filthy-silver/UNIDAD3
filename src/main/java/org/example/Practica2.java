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

            for (int i = 0; i < boleto.length-1; i++) {
                if (Arrays.asList(sorteo).contains(boleto[i])) {
                    aciertos++;         //Si el número del boleto está en el sorteo, se incrementa el contador de aciertos
                }
            }

            for (int i = 0; i < boleto.length-1; i++) {
                if (boleto[i] == complementario) {
                    hayComplementario = true;
                    break;      //Si el número del boleto es igual al complementario, se activa la variable de control y se sale del bucle
                }
            }

            for (int i = 0; i < boleto.length-1; i++) {
                if (boleto[i] == reintegro) {
                    hayReintegro = true;        //Si el número del boleto es igual al reintegro, se activa la variable de control y se sale del bucle
                    break;
                }
            }

            System.out.println("RESULTADOS:" +
                    "\nAciertos: " + aciertos);         //Se muestra el número de aciertos

            if (hayReintegro) {
                System.out.println("Reintegro.");
            }
            if (hayComplementario) {
                System.out.println("Complementario.");  //Si hay complementario o reintegro se indica al usuario
            }
            act++;
        }while (act < rep);
    }

    private static boolean validadarBoleto(String arg) {

        boolean check = true;
        String [] aux;                  //Array auxiliar para almacenar los valores del boleto
        int [] boletoAux = new int[7];  //Array auxiliar para almacenar los valores del boleto

        arg = arg.trim();

        if (arg.matches("\\d{1,2}-\\d{1,2}-\\d{1,2}-\\d{1,2}-\\d{1,2}-\\d{1,2}/\\d"))       //Expresión regular para validar el formato del boleto
        {
            aux = arg.split("[-/]");

            for (int i = 0; i < aux.length; i++) {
                boletoAux[i] = Integer.parseInt(aux[i]); //Se convierten los valores del boleto a malditos Integers
            }

            for (int i = 0; i < boletoAux.length; i++) {
                if (i != boletoAux.length-1) {
                    if (boletoAux[i] < 1 || boletoAux[i] > 49) { //Se comprueba que los valores del boleto estén en el rango correcto
                        check = false;
                        System.out.println("Error: [0a]");
                        break;
                    }
                } else {
                    if (boletoAux[i] < 0 || boletoAux[i] > 10) { //Se comprueba que el valor del reintegro esté en el rango correcto
                        check = false;
                        System.out.println("Error: [1a]");
                        break;
                    }
                }
            }

        } else {        //Si el formato del boleto no es correcto, se activa la variable de control
            return false;
        }
        if (check) {    //Si el boleto es correcto, se asigna a la variable boleto y se devuelve true
            boleto = boletoAux;
            return true;
        } else {        //Si el boleto no es correcto, se muestra un mensaje de error y se devuelve false
            System.out.println("Error: valores incorrectos [1]");
            return false;
        }
    }

    private static Integer[] sorteo() {                     //Función para generar el sorteo
        Integer [] sorteo = new Integer[6];                 //Array para almacenar los valores del sorteo
        for (int i = 0; i < sorteo.length; i++) {           //Bucle para generar los valores del sorteo
            Integer aux = bombo();                          //Variable auxiliar para almacenar los valores que vayamos sacando
            while (Arrays.asList(sorteo).contains(aux)) {   //Bucle para comprobar que no se repitan los valores
                aux = bombo();                              //Si se repite, se vuelve a sacar un valor
            }
                sorteo[i] = aux;                            //Si no se repite, se añade al array
        }
        System.out.println("Sorteo: " + Arrays.toString(sorteo));
        return sorteo;                                      //Se devuelve el array con los valores del sorteo
    }

    private static int sorteoComplementario(Integer[] arg) {

        int complementario;     //Variable para almacenar el complementario

        do {
            complementario = bombo();

        } while (Arrays.asList(arg).contains(complementario));  //Bucle para comprobar que el complementario no esté en el sorteo

        return complementario;
    }

    private static Integer reintegro() {
        return reintegro[rdn.nextInt(reintegro.length)]; //Función para generar el reintegro de forma dinámica según el tamaño del array de reintegro
    }

    private static Integer bombo() {
        return bombo[rdn.nextInt(bombo.length)];        //Función para generar un valor del bombo de forma dinámica según el tamaño del array de bombo
    }
}


