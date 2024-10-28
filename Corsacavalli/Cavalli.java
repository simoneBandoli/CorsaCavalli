
import java.util.Random;


class Cavallo extends Thread {
    private String nome;
    private int distanza;
    private int percorso = 0;
    private static Random random = new Random();

    public Cavallo(String nome, int distanza) {
        this.nome = nome;
        this.distanza = distanza;
    }

    @Override
    public void run() {
        while (percorso < distanza) {
            int passo = random.nextInt(10) + 1; 
            percorso += passo;
            System.out.println(nome + " ha percorso " + percorso + "/" + distanza + " metri");
            try {
                Thread.sleep(500); // Pausa di 0.5 secondi
            } catch (InterruptedException e) {
                System.out.println(nome + " è stato interrotto!");
            }
        }
        System.out.println(nome + " ha completato la gara!");
    }
}


