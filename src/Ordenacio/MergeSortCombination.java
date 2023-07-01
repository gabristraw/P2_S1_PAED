package Ordenacio;

import Clases.Barco;

import java.util.ArrayList;

public class MergeSortCombination {


    public static void mergeSortCombination(ArrayList<Barco> barcos, int principi, int last){

        if(principi>=last){
            ;
        } else{
            int mig = (principi+last) /2;

            mergeSortCombination(barcos,principi,mig);
            mergeSortCombination(barcos,mig+1,last);
            merge(barcos,principi,mig,last);
        }
    }
    public static void merge(ArrayList<Barco> barcos, int principi, int mig, int last){
        int l=principi;
        int r= mig + 1;

        ArrayList<Barco> tempArray = new ArrayList<>();

        while (l<=mig && r<=last){
            if( ((barcos.get(l).getCapacitat() * 0.5) + (barcos.get(l).getVelocitat() * 0.3) + (barcos.get(l).getPes() * 0.15) + ( barcos.get(l).getEslora() * 0.05)) <=  ((barcos.get(r).getCapacitat() * 0.5) + (barcos.get(r).getVelocitat() * 0.3) + (barcos.get(r).getPes() * 0.15) + ( barcos.get(r).getEslora() * 0.05))){
                tempArray.add(barcos.get(l));

                l=l+1;
            }else{
                tempArray.add(barcos.get(r));
                r=r+1;
            }

        }
        while ((l<=mig)){
            tempArray.add(barcos.get(l));
            l=l+1;
        }
        while ((r<=last)){
            tempArray.add(barcos.get(r));
            r=r+1;
        }

        for(int k=0; k < tempArray.size();principi++){
            barcos.set(principi, tempArray.get(k++));
        }



        }

    }



