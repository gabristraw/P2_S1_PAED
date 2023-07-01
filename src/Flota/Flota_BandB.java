package Flota;

import Clases.Barco;
import Clases.Centre;
import Clases.Node;

import java.util.*;

public class Flota_BandB {


    //public static ArrayList<Centre> branchAndBoundFlota(ArrayList<Centre> centres, ArrayList<String> tipuses) {

        /*
        // Ordenar en orden descendente para una mejor búsqueda
        centres.sort(Collections.reverseOrder());

        // Verificar si el archivo contiene una solución posible
        if (!minimoTipoBarco(tipuses)) {
            System.out.println("No hay solución posible con el archivo proporcionado");
            return null;
        } else {
            System.out.println("Hay solución posible");
        }

        // Inicializar variables
        PriorityQueue<Nodo> colaPrioridad = new PriorityQueue<>();
        Nodo mejorSolucion = null;
        Set<ArrayList<Integer>> visitados = new HashSet<>();

        // Agregar el primer nodo a la cola de prioridad
        Nodo primerNodo = new Nodo(new ArrayList<>(), 0);
        primerNodo.setCentrosRestantes(new ArrayList<>(centres));
        colaPrioridad.offer(primerNodo);

        // Realizar búsqueda Branch & Bound
        while (!colaPrioridad.isEmpty()) {
            Nodo nodoActual = colaPrioridad.poll();

            // Verificar si es una solución completa y es mejor que la actual
            if (esSolucionCompleta(nodoActual, tipuses) && esMejorSolucion(nodoActual, mejorSolucion)) {
                mejorSolucion = nodoActual;
            } else {
                // Generar los hijos del nodo actual
                Centre centro = nodoActual.getCentrosRestantes().get(0);
                ArrayList<Integer> barcosFaltantes = centro.getTipoFaltante(tipuses);

                for (int i = 0; i < barcosFaltantes.size(); i++) {
                    int barcosTomados = barcosFaltantes.get(i);
                    Nodo hijo = nodoActual.getHijo(centro, barcosTomados);
                    if (!visitados.contains(hijo.getCentrosTomados())) {
                        visitados.add(hijo.getCentrosTomados());
                        colaPrioridad.offer(hijo);
                    }
                }
            }
        }

        // Construir la lista de centros tomados a partir del mejor nodo solución
        ArrayList<Centre> centrosAgafats = new ArrayList<>();
        for (int i = 0; i < mejorSolucion.getCentrosTomados().size(); i++) {
            if (mejorSolucion.getCentrosTomados().get(i) == 1) {
                centrosAgafats.add(centres.get(i));
            }
        }

        return centrosAgafats;
    }

    private static boolean esSolucionCompleta(Nodo nodo, ArrayList<String> tipuses) {
        Set<String> tiposCentro = new HashSet<>();
        for (Centre centro : nodo.getCentrosRestantes()) {
            for (Barco barco : centro.getBarcos()) {
                tiposCentro.add(barco.getTipus());
            }
        }

        for (String tipo : tipuses) {
            if (!tiposCentro.contains(tipo)) {
                return false;
            }
        }
        return true;
    }

    private static boolean esMejorSolucion(Nodo nodo, Nodo mejorSolucion) {
        return mejorSolucion == null || nodo.getNumCentrosTomados() < mejorSolucion.getNumCentrosTomados();
    }

    public static boolean minimoTipoBarco(ArrayList<String> tipuses) {
        ArrayList<String> tiposRequeridos = new ArrayList<>(Arrays.asList("HobieCat", "HobieDragoon", "Windsurf", "Patí Català", "Laser", "Optimist"));
        return tipuses.containsAll(tiposRequeridos);
    }

    */


    public static ArrayList<Centre> branchAndBoundFlota(ArrayList<Centre> centres, ArrayList<String> tipuses) {

        if(!isFeasible(centres)){
            System.out.println("ni ha solucio");
            return null;
        }

        centres.sort(Comparator.comparing(Centre::getNom)); // Ordenar los centros por nombre (opcional)

        ArrayList<Centre> millorSolucio = new ArrayList<>();
        int millorCost = Integer.MAX_VALUE;

        Queue<Node> liveNodes = new PriorityQueue<>();
        Node rootNode = new Node(new ArrayList<>(), 0, calculateBound(new ArrayList<>(), 0, centres));
        liveNodes.add(rootNode);

        while (!liveNodes.isEmpty()) {
            Node currentNode = liveNodes.poll();

            if (currentNode.bound < millorCost) {
                if (currentNode.level == centres.size()) {
                    if (isFeasible(currentNode.configuracio)) {
                        millorSolucio = new ArrayList<>(currentNode.configuracio);
                        millorCost = currentNode.configuracio.size();
                    }
                } else {
                    int nextLevel = currentNode.level + 1;

                    // Excluir el próximo centro
                    ArrayList<Centre> excludedConfiguracio = new ArrayList<>(currentNode.configuracio);
                    Node excludedNode = new Node(excludedConfiguracio, nextLevel, calculateBound(excludedConfiguracio, nextLevel, centres));
                    if (excludedNode.bound < millorCost) {
                        liveNodes.add(excludedNode);
                    }

                    // Incluir el próximo centro
                    ArrayList<Centre> includedConfiguracio = new ArrayList<>(currentNode.configuracio);
                    includedConfiguracio.add(centres.get(nextLevel - 1));
                    Node includedNode = new Node(includedConfiguracio, nextLevel, calculateBound(includedConfiguracio, nextLevel, centres));
                    if (includedNode.bound < millorCost) {
                        liveNodes.add(includedNode);
                    }
                }
            }
        }

        return millorSolucio;
    }

    /*
    private static boolean isFeasible(ArrayList<Centre> configuracio) {
        Set<String> tiposPendientes = new HashSet<>();
        for (Centre centre : configuracio) {
            tiposPendientes.add(centre.getTipus());
        }

        Set<String> tiposNecesarios = new HashSet<>();
        for (Centre centre : centres) {
            tiposNecesarios.add(centre.getTipus());
        }

        return tiposPendientes.containsAll(tiposNecesarios);
    }
    */

    public static boolean isFeasible(ArrayList<Centre> configuracio) {

        ArrayList<String> tipuses = new ArrayList<String>();
        for(Centre centre : configuracio){

            for(Barco barco: centre.getBarcos()) {
                tipuses.add(barco.getTipus());
            }
        }


        ArrayList<String> tiposRequeridos = new ArrayList<>(Arrays.asList("HobieCat", "HobieDragoon", "Windsurf", "Patí Català", "Laser", "Optimist"));
        return tipuses.containsAll(tiposRequeridos);
    }





    private static int calculateBound(ArrayList<Centre> configuracio, int level, ArrayList<Centre> centres) {
        int bound = configuracio.size();

        //calculamos coste
        // el coste sera la cantidad de centros que se utilitza en ese sitio


        /*

        Set<String> tiposPendientes = new HashSet<>();
        for (Centre centre : centres) {
            tiposPendientes.add(centre.getTipus());
        }

        for (int i = level; i < centres.size(); i++) {
            tiposPendientes.remove(centres.get(i).tipus);
            if (tiposPendientes.isEmpty()) {
                bound = centres.size();
                break;
            }
        }

        */




        return bound;
    }



}
