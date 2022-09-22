import java.util.Random;

public class Macaco implements Runnable{
    Corda corda;
    private int mDir;
    private String [] way = new String[]{"Leste" , "Oeste"};
    Random random = new Random();

    public Macaco(Corda corda, int mDir){
        this.corda = corda;
        this.mDir = mDir;
    }


    public void run() {
        try {
            corda.showUp(mDir, "paulo");
           Thread.sleep((1000));
            System.out.println("cesar" +way[mDir]);
            corda.cruzar(mDir, "2");
            Thread.sleep((4000));
            corda.leave(mDir, "Felipe");
        }catch (InterruptedException e){
            e.printStackTrace();
            System.out.println("Operação interrompida");
        }
    }
}
