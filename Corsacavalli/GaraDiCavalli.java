
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GaraDiCavalli {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // lunghezza del percorso
        System.out.print("Inserisci la lunghezza del percorso in metri: ");
        int distanza = scanner.nextInt();

        //  numero di cavalli e i loro nomi
        System.out.print("Inserisci il numero di cavalli in gara: ");
        int numeroCavalli = scanner.nextInt();
        scanner.nextLine(); // Consuma il newline

        List<Cavallo> cavalli = new ArrayList<>();
        for (int i = 0; i < numeroCavalli; i++) {
            System.out.print("Inserisci il nome del cavallo " + (i + 1) + ": ");
            String nome = scanner.nextLine();
            Cavallo cavallo = new Cavallo(nome, distanza);
            cavalli.add(cavallo);
        }

        System.out.println("\nLa gara è iniziata!");
        for (Cavallo cavallo : cavalli) {
            cavallo.start();
        }

        for (Cavallo cavallo : cavalli) {
            try {
                cavallo.join();
            } catch (InterruptedException e) {
                System.out.println("Time!");
            }
        }
        
        System.out.println("Fine Gara!");
        scanner.close();
    }
}