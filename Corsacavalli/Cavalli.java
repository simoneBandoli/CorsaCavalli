
import java.util.Random;

class Cavallo extends Thread {
    private String nome;      
    private int dist;        
    private int perc = 0;     // Metri percorsi 
    private int vel;          // Velocità del cavallo (mps)
    private boolean infort = false; // se fatto male
    private static Random rnd = new Random();

    // Costruttore
    public Cavallo(String nome, int dist, int vel) {
        this.nome = nome;
        this.dist = dist;
        this.vel = vel;
    }

    // Metodi getter
    public String getNome() { return nome; }
    public int getPerc() { return perc; }
    public boolean isInfort() { return infort; }

    // Metodo run per la logica del thread
    @Override
    public void run() {
        while (perc < dist && !infort) {
            // 10% che il cavallo si infortuni - 5% che con 10 schioppano troppo
            if (rnd.nextInt(100) < 5) {
                infort = true;
                System.out.println(nome + " si è infortunato e si ritira dalla gara!");
                return;
            }

            // Il cavallo avanza di un passo pari alla sua velocità
            perc += vel;
            System.out.println(nome + " ha percorso " + perc + "/" + dist + " metri");

            // Pausa di 1 secondo per simulare il tempo di gara
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(nome + " è stato interrotto!");
            }
        }

        // Messaggio finale se il cavallo ha completato la gara
        if (!infort) {
            System.out.println(nome + " ha completato la gara!");
        }
    }
}
