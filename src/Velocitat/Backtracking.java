package Velocitat;

import Clases.Barco;
import Clases.Sailor;
import Clases.Configuracio;

import java.util.ArrayList;

public class Backtracking {

    private static float bestVelocitat=-2000;

    public static void backtracking(Configuracio configuracion, int k, ArrayList<Barco> barcos, ArrayList<Sailor> sailors){
        preparaRecorregut(configuracion,k);

        while(hiHaSuccessor(configuracion,k,sailors)){
            if(solucion(k,configuracion)){
                if(factible(configuracion.getCombinaciones())){
                    //Tracta combinacio
                    tratarSolucion(configuracion,barcos,sailors);
                }
            } else {
                if(!completable(configuracion.getCombinaciones(),k,barcos,sailors)) {
                    backtracking(configuracion, k + 1, barcos, sailors);
                }
                backtracking(configuracion, k + 1, barcos, sailors);

            }
            seguentSuccessor(configuracion,k);
        }

    }
    private static void preparaRecorregut(Configuracio configuracion, int k) {
        configuracion.getCombinaciones()[k] = 0;
    }

    private static boolean hiHaSuccessor(Configuracio configuracion, int k, ArrayList<Sailor> sailors) {
        return configuracion.getCombinaciones()[k] < sailors.size();
    }
    private static boolean solucion(int k, Configuracio configuracion) {
        return k == configuracion.getCombinaciones().length - 1;
    }
    private static void seguentSuccessor(Configuracio configuracion, int k) {
        configuracion.getCombinaciones()[k]++;

    }
    public static boolean factible(int[] combinaciones) {
        for (int i = 0; i < combinaciones.length; i++) {
            if (combinaciones[i] == -1) {
                return false;
            }
            for (int j = i + 1; j < combinaciones.length; j++) {
                if (combinaciones[i] == combinaciones[j]) {
                    return false;
                }
            }
        }
        return true;}

    public static boolean completable(int[] combinaciones, int k,ArrayList<Barco> barcos, ArrayList<Sailor> sailors) {
            for (int i = 0; i < combinaciones.length; i++) {
                    for (int j = i + 1; j < combinaciones.length; j++) {
                        if (combinaciones[i] == combinaciones[j]) {
                            if(combinaciones[i]!=-1){
                                return false;
                            }
                        }
                    }
            }
        float velocitatActual = getVelocitatParcial(combinaciones, k, barcos, sailors);
            return velocitatActual < bestVelocitat;
    }

    public static void mostrarBestCombinacion(int[] combinaciones, ArrayList<Barco> barcos, ArrayList<Sailor> sailors) {
        int index = 0;

        for (Barco barco : barcos) {
            int capacidad = barco.getCapacitat();

            System.out.println("Barco: " + barco.getNom());

            for (int j = 0; j < capacidad; j++) {
                Sailor sailor = sailors.get(combinaciones[index]);
                System.out.println("\tSailor: "+(j+1)+"-" + sailor.getNom());
                index++;
            }
        }
        System.out.println("\nVelocidad total: " + bestVelocitat);

    }

    public static float getVelocitatTotal(int[] combinaciones, ArrayList<Barco> barcos, ArrayList<Sailor> sailors) {
        float velocidadTotal = 0;
        int index = 0;

        for (Barco barco : barcos) {
            int capacidad = barco.getCapacitat();

            ArrayList<Sailor> barcoSailors = new ArrayList<>();

            for (int j = 0; j < capacidad; j++) {
                int sailorIndex = combinaciones[index];
                Sailor sailor = sailors.get(sailorIndex);
                barcoSailors.add(sailor);
                index++;
            }

            velocidadTotal += barco.getVelocitatReal(barcoSailors);
        }

        return velocidadTotal;
    }
    public static float getVelocitatParcial(int[] combinaciones,int k, ArrayList<Barco> barcos, ArrayList<Sailor> sailors) {
        float velocidadTotal = 0;
        int index = 0;
        for (int i = 0; i < k; i++) {
            Barco barco = barcos.get(i);
            int capacidad = barco.getCapacitat();

            ArrayList<Sailor> barcoSailors = new ArrayList<>();

            for (int j = 0; j < capacidad; j++) {
                if(combinaciones[i]!=-1){
                    int sailorIndex = combinaciones[index];
                    Sailor sailor = sailors.get(sailorIndex);
                    barcoSailors.add(sailor);
                }
                index++;
            }

            velocidadTotal += barco.getVelocitatReal(barcoSailors);
        }
        return velocidadTotal;
    }

    public static void tratarSolucion(Configuracio configuracion, ArrayList<Barco> barcos, ArrayList<Sailor> sailors) {
        float velocidadTotal = getVelocitatTotal(configuracion.getCombinaciones(), barcos, sailors);
        if (velocidadTotal > bestVelocitat) {
            bestVelocitat = velocidadTotal;
            mostrarBestCombinacion(configuracion.getCombinaciones(), barcos, sailors);
        }
    }
}
