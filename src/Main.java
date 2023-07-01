import Clases.Barco;
import Clases.Centre;
import Clases.Configuracio;
import Clases.Sailor;
import Flota.Flota_BandB;
import Flota.Flota_Greedy;
import Velocitat.Backtracking;
import Velocitat.ForçaBruta;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String[] OPTIONS_MESSAGES = {
            "1. BACKtracking",
            "2. ORDENAR PER NOM",
            "3. Flota.Flota_Greedy",
            "4. Flota BandB",
            "5. SORTIR"
    };

    public static final String MENU_MESSAGE = "Menu";
    public static final String OPTION_MESSAGE = "Escull una opció";
    public static final String ERROR = "Opció no valida";


    public static void main(String[] args) throws IOException {
        boolean run=true;

        ArrayList<Barco> barcos = new ArrayList<>();
        ArrayList<Sailor> sailors= new ArrayList<>();
        ArrayList<Centre> centres = new ArrayList<>();
        ArrayList<String> tipus = new ArrayList<>();

        readBarcos(barcos,centres,tipus);
        readSailors(sailors);

        while(run){
            MenuOptions option = showMenu();
            run = runOption(option,barcos,sailors,centres,tipus);
        }
    }

    public static MenuOptions showMenu(){
        Scanner scanner = new Scanner(System.in);

        do{
            System.out.println(MENU_MESSAGE);
            for(String message : OPTIONS_MESSAGES){
                System.out.println("\t"+message);
            }
            System.out.println(System.lineSeparator()+OPTION_MESSAGE);

            try{
                int option = scanner.nextInt();
                return MenuOptions.getOption(option);
            } catch (InputMismatchException | IndexOutOfBoundsException e){
                System.out.println(ERROR);
            }
        } while (true);
    }

    private static boolean runOption(MenuOptions option, ArrayList<Barco> barcos, ArrayList<Sailor> sailors, ArrayList<Centre> centres, ArrayList<String> tipus) {

        switch (option){
            case Velocitat_Backtracking:
                int cap=0;
                for (Barco barco : barcos) {
                    cap += barco.getCapacitat();
                }

                Configuracio config = new Configuracio(cap);
                //1ForçaBruta.bruteForce(config,0,barcos,sailors);
                Backtracking.backtracking(config,0,barcos,sailors);
                break;
            case Velocitat_BandB:

                break;
            case Flota_Greedy:
                centres = Flota_Greedy.greedyFlota(centres,tipus);
                centreIsNull(centres);
                break;

            case Flota_BandB:


                centres = Flota_BandB.branchAndBoundFlota(centres,tipus);
                centreIsNull(centres);

                break;

            case EXIT:
                return false;
        }
        return true;
    }

    private static void centreIsNull(ArrayList<Centre> centres) {
        if(centres!=null){
            for (Centre centre : centres) {
                System.out.println(centre.getNom());
                for (int j = 0; j < centre.getBarcos().size(); j++) {
                    System.out.println("\t" + centre.getBarcos().get(j).getNom() + " - " + centre.getBarcos().get(j).getTipus());
                }
            }
        }
    }

    public static void Barcos(String str, ArrayList<Barco> barcos,ArrayList<Centre> centres, ArrayList<String> tipus)  {

        List<String> allLines;

            allLines = List.of(str.split(";"));
            String tipo = allLines.get(2);
            String NameCentre = allLines.get(9);

            Barco b1 = new Barco(Integer.parseInt(allLines.get(0)), allLines.get(1), allLines.get(2), Float.parseFloat((allLines.get(3))),Float.parseFloat((allLines.get(4))),Integer.parseInt(allLines.get(5)),Integer.parseInt(allLines.get(6)),allLines.get(7),Integer.parseInt(allLines.get(8)),allLines.get(9));
            barcos.add(b1);
            Centers(centres,tipus,tipo,NameCentre,b1);
    }
    public static void Centers(ArrayList<Centre> centres, ArrayList<String> tipus, String tipo, String nom, Barco barco){
        // Comprovar si existeixº
        Centre centre = null;
        for(Centre c: centres){
            if (c.getNom().equals(nom)){
                centre = c;
            }
        }
        // Crear si no existeix
        if(centre==null){
            centre = new Centre(nom);
            centres.add(centre);
        }

        // Comprovar si existeix el tipus
        String aux = null ;
        for(String c: tipus){
            if (c.equals(tipo)){
                aux = c;
            }
        }
        // Crear si no existeix
        if(aux==null){
            aux = tipo;
            tipus.add(aux);
        }
        centre.addBarco(barco);

    }
    public static void Sailors(String str, ArrayList<Sailor> sailors)   {

        List<String> allLines;
        allLines = List.of(str.split(";"));

        Sailor b1 = new Sailor(Integer.parseInt(allLines.get(0)), allLines.get(1), Float.parseFloat(allLines.get(2)), Integer.parseInt(allLines.get(3)),Integer.parseInt(allLines.get(4)),Integer.parseInt(allLines.get(5)),Integer.parseInt(allLines.get(6)),Integer.parseInt(allLines.get(7)),Integer.parseInt(allLines.get(8)),Integer.parseInt(allLines.get(9)));

        sailors.add(b1);

    }
    public static void readBarcos(ArrayList<Barco> barcos,ArrayList<Centre> centres,ArrayList<String> tipus ) throws IOException {
        int n;

        BufferedReader bf = new BufferedReader(new FileReader("Datasets/Boats/boatsM.txt"));
        n = Integer.parseInt(bf.readLine());

        String line;
        for(int i=0;i<n;i++){
            line = bf.readLine();
            Barcos(line,barcos,centres,tipus);
        }
        bf.close();

    }
    public static void readSailors(ArrayList<Sailor> sailors) throws IOException {
        int n;

        BufferedReader bf = new BufferedReader(new FileReader("Datasets/Sailors/sailorsM.txt"));

        n = Integer.parseInt(bf.readLine());

        String line;
        for(int i=0;i<n;i++){
            line = bf.readLine();
            Sailors(line,sailors);
        }
        bf.close();

    }
}