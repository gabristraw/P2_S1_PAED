package Velocitat;

import Clases.Barco;
import Clases.Sailor;
import Clases.Configuracio;

import java.util.ArrayList;

public class ForçaBruta {

    private static int[] mejorCombinacion;
    private static float bestVelocitat=-2000;

    public static void bruteForce(Configuracio configuracion, int k,  ArrayList<Barco> barcos, ArrayList<Sailor> sailors) {
        preparaRecorregut(configuracion, k);

        while (hiHaSuccessor(configuracion, k, sailors)) {
            if (solucion(k, configuracion)) {
                if (factible(configuracion.getCombinaciones(), k)) {
                    //Tracta combinacio
                    tratarSolucion(configuracion, barcos, sailors);
                }
            } else {
                bruteForce(configuracion, k + 1, barcos, sailors);
            }
            seguentSuccessor(configuracion, k);
        }
    }


    public static void preparaRecorregut(Configuracio configuracion, int k) {
        configuracion.getCombinaciones()[k] = 0;
    }

    public static boolean hiHaSuccessor(Configuracio configuracion, int k, ArrayList<Sailor> sailors) {
        return configuracion.getCombinaciones()[k] < sailors.size();
    }

    public static boolean solucion( int k, Configuracio configuracion) {
        return k == configuracion.getCombinaciones().length - 1;
    }

    public static void seguentSuccessor(Configuracio configuracion, int k ) {
        configuracion.getCombinaciones()[k]++;

    }

    public static boolean factible(int[] combinaciones,int k) {
        for (int i = 0; i < k; i++) {
            for(int j=i+1;j<k;j++){
                if (combinaciones[i] == combinaciones[j]) {
                    return false;
                }
            }
            if (combinaciones[i] == combinaciones[k]) {
                return false;
            }

        }

        return true;
    }


    public static void mostrarCombinacion(int[] combinaciones, ArrayList<Barco> barcos, ArrayList<Sailor> sailors) {
        int index = 0;

        for (Barco barco : barcos) {
            int capacidad = barco.getCapacitat();

            System.out.println("Barco: " + barco.getNom());
            for (int j = 0; j < capacidad; j++) {
                Sailor sailor = sailors.get(combinaciones[index]);
                System.out.println("\tSailor: " + (j + 1) + "-" + sailor.getNom());
                index++;
            }
        }
        System.out.println("\nVelocidad total: " + bestVelocitat);
        System.out.println();
    }

    public static float getVelocitatTotal(int[] combinaciones, ArrayList<Barco> barcos, ArrayList<Sailor> sailors) {
        float velocidadTotal = 0;
        int index=0;

        for (int i = 0; i < barcos.size(); i++) {
            Barco barco = barcos.get(i);
            int capacidad = barco.getCapacitat();

            ArrayList<Sailor> barcoSailors = new ArrayList<>();

            for (int j = 0; j < capacidad; j++) {
                int sailorIndex = combinaciones[index];
                Sailor sailor = sailors.get(sailorIndex);
                barcoSailors.add(sailor);
                index++;
            }


            velocidadTotal += barco.getVelocitatReal(barcoSailors,barco);
        }

        return velocidadTotal;
    }
    public static void tratarSolucion(Configuracio configuracion, ArrayList<Barco> barcos, ArrayList<Sailor> sailors) {
        float velocidadTotal = getVelocitatTotal(configuracion.getCombinaciones(), barcos, sailors);
        //mostrarCombinacion(configuracion.getCombinaciones(), barcos, sailors);
        if (velocidadTotal > bestVelocitat) {
            mejorCombinacion = configuracion.getCombinaciones();
            bestVelocitat = velocidadTotal;
            mostrarCombinacion(configuracion.getCombinaciones(), barcos, sailors);
        }
    }

}