public class App {
    public static void main(String[] args) {

        Cotor cotor = new Cotor(args[0]);

        cotor.scale(Double.parseDouble(args[1])).save(args[2]);
        System.out.println(">> Immagine " + args[2] + " salvata con successo! Che rotocotor!");
    }
}
