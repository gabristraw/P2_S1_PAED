package Ordenacio;

import Clases.Sailor;

import java.util.ArrayList;

public class MergeSort {



    public static void mergeSort2(ArrayList<Sailor> barcos, int principi, int last){

        if(principi>=last){
            ;
        } else{
            int mig = (principi+last) /2;  // busquem la posició pivot

            //dividim en 2 l'arrayList
            mergeSort2(barcos,principi,mig);
            mergeSort2(barcos,mig+1,last);

            //funció que ordenar
            merge(barcos,principi,mig,last);
        }
    }
    public static void merge(ArrayList<Sailor> barcos, int principi, int mig, int last){
        int l=principi;
        int r= mig + 1;

        //creem una arrayList temporal
        ArrayList<Sailor> tempArray = new ArrayList<>();

        // bucle que mentres l'index incial sigui menor a la meitat de l'array, i el pivot sigui menor al final, comprova si el id 'inicial' és més petit al pivot+1.
        // Guardem al arrayList auxiliar el valor petit i augmentem l'index corresponent
        while (l<=mig && r<=last){
            if(barcos.get(l).getID()<=barcos.get(r).getID()){
                tempArray.add(barcos.get(l));

                l=l+1;
            }else{
                tempArray.add(barcos.get(r));
                r=r+1;
            }

        }
        // bucle que afegeix tots els barcos que no s'han afegit previament entre la posició inicial i el pivot
        while ((l<=mig)){
            tempArray.add(barcos.get(l));
            l=l+1;
        }
        //bucle que afegeix tots els barcos que no s'han afegit previament entre la posició pivot i el fianl
        while ((r<=last)){
            tempArray.add(barcos.get(r));
            r=r+1;
        }
        // bucle que traspassar l'arrayList auxiliar a la correcta.
        for(int k=0; k < tempArray.size();principi++){
            barcos.set(principi, tempArray.get(k++));
        }



        }

    }



