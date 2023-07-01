package Ordenacio;

import Clases.Barco;

import java.util.ArrayList;

public class QuickSort {

    public static void quicksort(ArrayList<Barco> barcos, int left, int right) {
        int q;
        if (left<right) {

                q = partition(barcos, left, right); // retorna la posició per on dividir l'arraylist i ordenar

            //dividim en 2 l'arrayList
            quicksort(barcos, left, q - 1);
            quicksort(barcos, q + 1, right);
        }
    }


    static int partition(ArrayList<Barco> barcos, int left, int right) {
        String P = ((barcos.get(right).getNom()));
        int i = left;

        for (int j=left;j<right;j++) { //bucle que recorrer tota l'arrayList
            if (barcos.get(j).getNom().compareTo(P) < 0) { // comprovem que el valor en l'index j sigui més petit que el valor final
                //intercanviem el valor de la posició inicial amb la posició que ens trobem
                Barco barcos1 = barcos.get(j);
                barcos.set(j, barcos.get(i));
                barcos.set(i, barcos1);

                i++;            // incrementem i, ens diu quants valors més petits al últim valor hi ha
            }
        }

        // col·loquem l'últim valor a la seva posició correcta
        Barco barcos1 = barcos.get(i);
        barcos.set(i,barcos.get(right));
        barcos.set(right,barcos1);

        return i;

    }



}
