public class Corda {

    private int[] numMacacosEsperando;

    private int[]  macacosCruzando;

    private int[] prioridadeDirecao;

    private String [] way = new String[]{"Leste", "Oeste"};
    private Caminho caminho;

    public Corda(Caminho caminho){
        numMacacosEsperando = new int[]{0,0};
        macacosCruzando = new int[]{0,0};
        prioridadeDirecao = new int[]{1, 1};
        this.caminho = caminho;
    }

    public synchronized void showUp(int direcao, String nome) throws InterruptedException{
        System.out.println(nome + " macaco apareceu do "+ way[1 - direcao]+ " esperando para cruzar para o " +way[direcao]);

    }

    public synchronized void cruzar(int direcao, String nome) throws InterruptedException {

        numMacacosEsperando[direcao]++;
        caminho.addWaitMacaco(nome, direcao);
        System.out.println(numMacacosEsperando[direcao] + " macacos esperando para cruzar o "+ way[direcao]);
        caminho.printStatus();
        int auxMessage = 0;

        while((macacosCruzando[1-direcao] > 0)||((prioridadeDirecao[direcao]==0)&&(prioridadeDirecao[1-direcao]==1)))
        {
            try{
                // Get priority (and remove priority for monkeys on the other side)
                if(macacosCruzando[1-direcao] > 0) {
                    prioridadeDirecao[direcao]=1;
                    prioridadeDirecao[1-direcao]=0;
                }

                // Auxiliary messages to see the program execution
                if(auxMessage==0) {
                    if(macacosCruzando[1-direcao] > 0) {
                        System.out.println(nome+" macaco esperando o outro terminar de cruzar a direção oposta");
                    }
                    if((macacosCruzando[direcao] > 0)&&(prioridadeDirecao[1-direcao]==1))
                        System.out.println(nome+" macaco esperando porque há outros macacos no lado opost que já estão esperando");
                    auxMessage++;
                }
                // invoke wait to suspend the current thread until another thread issues a notification
                wait();
            }catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Operacao interrompida.");
            }
        }
        numMacacosEsperando[direcao]--;
        macacosCruzando[direcao]++;
        caminho.addCrossingMacaco(nome, direcao);

        notifyAll();

        System.out.println(numMacacosEsperando[direcao] + " macacos esperando para cruzar o "+ way[direcao]);
        System.out.println(nome+ " macaco cruzando para o "+ way[direcao]+". " + macacosCruzando[direcao] + " macacos cruzando para o "+ way[direcao]);
        caminho.printStatus();
    }

    public synchronized void leave(int direcao, String nome) {
        macacosCruzando[direcao]--;
        caminho.removeMacaco(nome, direcao);
        System.out.println(nome+" macaco terminou de cruzar o "+ way[direcao]+". "+  macacosCruzando[direcao] + " macacos deixando a travessia para o "+ way[direcao]);
        caminho.printStatus();
        notifyAll();
    }

}

