public class App {
    public static void main(String[] args) {

        Cotor cotor;
        try {
            cotor = new Cotor(args[0]);
        } catch(Exception e) {
            e.fillInStackTrace();
            System.out.println("Sembra che l'opium non soprano... sicuro di aver scritto bene il file?");
            return;
        }

        cotor.scale(Double.parseDouble(args[1])).save(args[2]);
        System.out.println(">> Immagine " + args[2] + " salvata con successo! Che rotocotor!");
    }
}

