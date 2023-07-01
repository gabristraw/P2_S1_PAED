
public enum MenuOptions {
    Velocitat_Backtracking,
    Velocitat_BandB,
    Flota_Greedy,
    Flota_BandB,
    EXIT;


    public static MenuOptions getOption (int option) throws IndexOutOfBoundsException{
        switch (option){
            case 1:
                return MenuOptions.Velocitat_Backtracking;
            case 2:
                return MenuOptions.Velocitat_BandB;
            case 3:
                return MenuOptions.Flota_Greedy;
            case 4:
                return MenuOptions.Flota_BandB;
            case 5:
                return MenuOptions.EXIT;

        }
        throw new IndexOutOfBoundsException("Opció entre 1-5");
    }
}
