package Clases;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Configuracio {

    private final int[] combinaciones;

    public Configuracio(int capacidades) {
        this.combinaciones = new int[capacidades];

        for(int i=0;i<capacidades;i++){
            combinaciones[i]=-1;
        }
    }

    public int[] getCombinaciones() {
        return combinaciones;
    }
}