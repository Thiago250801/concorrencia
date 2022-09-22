import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Caminho caminho = new Caminho();

        Corda corda = new Corda(caminho);

        int direcao;

        int numMacacos = 10;
        if((args.length > 0)){
            try {
                if((Integer.parseInt(args[0]) > 0) &&(Integer.parseInt(args[0]) < 11))
                    numMacacos = Integer.parseInt(args[0]);
            }catch (NumberFormatException e){
                System.err.println("Argumento precisa ser um interger.");
                System.exit(1);
            }
        }

        Thread [] threads = new Thread[numMacacos];

        Random random = new Random();

        for (int i = 0; i<numMacacos; i++){

            direcao = random.nextInt(2);

            try{
                Thread.sleep(((random.nextInt(9-1)/numMacacos) + 1)*1000);

                threads[i] = new Thread(new Macaco(corda,direcao));

                threads[i].start();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
