import java.util.Scanner;

public class Console implements IOMedium {
    private Scanner sc = new Scanner(System.in);

    public final static void clearConsole(){
        System.out.print("\033[H\033[2J");   
        System.out.flush(); 
    }

    public void init(){
        clearConsole();
        System.out.println("Concessionária José Motors\n");
    }
    
    public String [] loginScreen(){
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
}
