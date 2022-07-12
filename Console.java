import java.util.InputMismatchException;
import java.util.Scanner;

public class Console implements IOMedium {
    private Scanner sc = new Scanner(System.in);

    private void header(){
        System.out.println("Concessionária José Motors\n");
    }

    private final static void clearConsole(){
        System.out.print("\033[H\033[2J");   
        System.out.flush(); 
    }

    private int readInt(){
        int op;

        while(true){
            try{
                op = sc.nextInt();
                sc.nextLine();
                return op;
            }catch(InputMismatchException e){
                sc.nextLine();
                panik("Digite apenas numeros!");
            }
        }
    }

    public void init(){
        clearConsole();
    }
    
    public String [] loginScreen(){
        header();

        String [] credentials = new String[2];
        
        System.out.print("Nome de usuario: ");
        credentials[0] = sc.nextLine();

        System.out.print("Senha: ");
        credentials[1] = sc.nextLine();

        return credentials;
    }

    public void panik(String error){
        System.out.println("\n" + error + "\n");
        sc.nextLine();
        clearConsole();
    }

    public void display(String message){
        System.out.println("\n" + message + "\n");
        sc.nextLine();
        clearConsole();
    }

    public int optionMenu(String [] options){
        int i, op;

        do{
            header();

            for(i = 0; i < options.length; i++)
                System.out.println("[" + (i+1) + "] " + options[i]);

            System.out.println("[0] Sair\n");

            op = readInt();
            
            if(op>=0 && op<=options.length)
                return op-1;
            else
                panik("Opcao invalida!");
        }while(true);

    }
}
