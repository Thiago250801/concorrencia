import java.util.LinkedList;
import java.util.List;

public class Caminho {

    public List<String> LS;
    public List<String> OS;
    public List<String> CruzandoLS;
    public List<String> CruzandoOS;

    //delimitar o tamanho do nome do macaco

    private String sep = "           |    ";

    public Caminho(){
        OS = new LinkedList<String>();
        LS = new LinkedList<String>();
        CruzandoOS = new LinkedList<String>();
        CruzandoLS = new LinkedList<String>();
    }

    public void addWaitMacaco (String nomeMacaco, int dir){
        if(dir == 0)
            OS.add(nomeMacaco);
        else
            LS.add(nomeMacaco);
    }

    // Função que adiciona macacos(e remove macacos em espera) cruzando corda para a lista
    // dependendo qual direção estão indo

    public void addCrossingMacaco (String nomeMacaco, int dir){

        if (dir == 0){
            OS.remove(nomeMacaco);
            CruzandoLS.add(nomeMacaco);
        }else {
            LS.remove(nomeMacaco);
            CruzandoOS.add(nomeMacaco);
        }
    }

    public void removeMacaco ( String nomeMacaco, int dir){
        if(dir == 0){
            CruzandoLS.remove(nomeMacaco);
        }

        else {
            CruzandoOS.remove(nomeMacaco);
        }
    }
    // função que print uma tabela com o estado dos macacos ( cruzando ou esperando)
    public void printStatus(){

        // int auxiliar que conta o numero de caracteres para removar da String 'sep'
        int aux = 0;

        int maxRows = Math.max(Math.max(LS.size(),CruzandoLS.size()),CruzandoOS.size());
        maxRows = Math.max(maxRows, OS.size());

        System.out.println("     Esperando >       Cruzando      < Esperando");
        System.out.println(" _______________________________________________");

        for (int i = 0; i <maxRows;i++) {
            System.out.print("|    ");
            aux = 0;
            if(i<LS.size()) {
                System.out.print(LS.get(i) + " >");
                aux= aux + LS.get(i).length()+2;
            }
            System.out.print(sep.substring(aux));
            aux = 0;
            if(i<CruzandoLS.size()) {
                System.out.print(CruzandoLS.get(i) + " >");
                aux= aux + CruzandoLS.get(i).length()+2;
            }
            if(i<CruzandoOS.size()) {
                System.out.print("< "+ CruzandoOS.get(i));
                aux= aux + CruzandoOS.get(i).length()+2;
            }
            System.out.print(sep.substring(aux));

            aux = 0;
            if(i<OS.size()) {
                System.out.print("< "+ OS.get(i));
                aux= aux + OS.get(i).length()+2;
            }
            System.out.println(sep.substring(aux,12));

        }

        if(maxRows == 0){
            aux = 0;
            System.out.print("|    ");
            System.out.print(sep.substring(aux));
            System.out.print(sep.substring(aux));
            System.out.println(sep.substring(aux,12));
        }

        System.out.println(" -----------------------------------------------");
    }

}
