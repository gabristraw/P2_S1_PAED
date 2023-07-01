package Ordenacio;

import Clases.Barco;
import Ordenacio.MergeSortCombination;

import java.util.ArrayList;

public class BucketSort {

    static void bucketSort(ArrayList<Barco> barcos, int n){  //BUCKET SORT NOMES AMB 1 PARAMETRE

        if(n<=0){
            return;
        }

        ArrayList<Barco>[] bucket = new ArrayList[101];

        for ( int i =0; i < n;i++){          //creamos n buckets
            bucket[i]=new ArrayList<>(barcos);
        }

        for(int i = 0; i<n; i++){
            float idx = (n-1);
            try{
                bucket[(int) Math.floor((barcos.get(i).getCapacitat() * 0.5) + (barcos.get(i).getVelocitat() * 0.3) + (barcos.get(i).getPes() * 0.15) + ( barcos.get(i).getEslora() * 0.05))].add(barcos.get(i));       //nomes esta omplin un barco per bucket
            } catch(NullPointerException e){

            }
        }

        for (int i = 0; i < n; i ++) {
            try{
                MergeSortCombination.mergeSortCombination( bucket[i], 0, bucket[i].size() -1 );
                                                            //ordenem cada bucket de moment nomes un paramentre

            }catch (NullPointerException c){

            }
        }

        int contador  = 0;

        for (int j = 0; j < n; j++) {
            try{
                for (int i  =0; i < bucket[j].size()-1; i ++) {
                    barcos.set(contador,bucket[j].get(i));
                    contador ++;
                }//omplin de nou arraylist de barcos en ordre per els buckets
            }catch (IndexOutOfBoundsException m){

            } catch(NullPointerException pp){

            }


        }

    }




}
