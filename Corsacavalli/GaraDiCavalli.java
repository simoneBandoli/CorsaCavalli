import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class GaraDiCavalli {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // crear lo scanner sc per leggere input
        List<Cavallo> cavalli = new ArrayList<>();

        //distanza totale della gara
        System.out.print("Inserisci la lunghezza del percorso in metri: ");
        int dist = sc.nextInt();
        sc.nextLine(); // elimina il newline precendente

        // numero di cavalli
        System.out.print("Inserisci il numero di cavalli in gara: ");
        int numCavalli = sc.nextInt();
        sc.nextLine();

        // Creazione dei cavalli
        for (int i = 0; i < numCavalli; i++) {
            System.out.print("Inserisci il nome del cavallo " + (i + 1) + ": ");
            String nome = sc.nextLine();

            System.out.print("Inserisci la velocità del cavallo " + nome + " (metri al secondo): ");
            int vel = sc.nextInt();
            sc.nextLine();

            // Aggiunta del cavallo alla lista
            cavalli.add(new Cavallo(nome, dist, vel));
        }

        // Avvio di tutti i thread
        System.out.println("\nLa gara è iniziata!");
        for (Cavallo c : cavalli) {
            c.start();
        }

        // fine della gara per tutti i cavalli
        for (Cavallo c : cavalli) {   //for each c in cavalli
            try {
                c.join(); // attende la conclusione di di c
            } catch (InterruptedException e) {
                System.out.println("Gara interrotta!");
            }
        }

        // classifica finale senza cavalli monchi
        List<Cavallo> classifica = new ArrayList<>();
        for (Cavallo c : cavalli) {
            if (!c.isInfort()) { // esenta i cavalli infort
                classifica.add(c);
            }
        }

        // Ordinamento della classifica per metri percostisi
        classifica.sort(Comparator.comparingInt(Cavallo::getPerc).reversed());

        // Stampa della classifica dei primi 3
        System.out.println("\nClassifica finale:");
        int pos = 1;
        for (int i = 0; i < Math.min(3, classifica.size()); i++) {
            Cavallo c = classifica.get(i);
            System.out.println(pos + ". " + c.getNome() + " con " + c.getPerc() + " metri percorsi");
            pos++;
        }

        // Salvataggio della classifica su file
        System.out.print("\nInserisci il nome del file.txt dove salvare i risultati: ");
        String fileName = sc.nextLine();

        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write("Classifica finale:\n");
            pos = 1;
            for (int i = 0; i < Math.min(3, classifica.size()); i++) {
                Cavallo c = classifica.get(i);
                writer.write(pos + ". " + c.getNome() + " con " + c.getPerc() + " metri percorsi\n");
                pos++;
            }
            writer.write("\n");
            System.out.println("Risultati salvati con successo nel file " + fileName);
        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio del file: " + e.getMessage());
        }

        sc.close();
        System.out.println("La gara è terminata!");
    }
}