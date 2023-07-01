package Flota;

import Clases.Centre;

import java.util.*;

import static java.util.Collections.reverseOrder;


public class Flota_Greedy {


    public static ArrayList<Centre> greedyFlota(ArrayList<Centre> centres, ArrayList<String> tipuses){

        if(!minimoTipoBarco(tipuses)){
            System.out.println("No hi ha solució possible amb el fitxer proporcionat");
            return null;
        } else{
            centres.sort(reverseOrder());

            ArrayList<Centre> centresAgafats = new ArrayList<>();
            for(String typeBarco : tipuses){
                Centre centreAgafat = null;

                for (Centre centre : centres) {
                    if (centre.teTipus(typeBarco)) {
                        centreAgafat = centre;
                        break;
                    }
                }
                if(centreAgafat!=null){
                    if(!centresAgafats.contains(centreAgafat)) {
                        centresAgafats.add(centreAgafat);
                    }
                }
            }
            return centresAgafats;
        }
    }

    public static boolean minimoTipoBarco(ArrayList<String> tipuses){
        for(String ignored : tipuses){
            if(!tipuses.contains("HobieCat") || !tipuses.contains("HobieDragoon") || !tipuses.contains("Windsurf") || !tipuses.contains("Patí Català") || !tipuses.contains("Laser") ||!tipuses.contains("Optimist") )
                return false;
        }
        return true;
    }
}
