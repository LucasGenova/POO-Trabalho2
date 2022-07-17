import java.util.InputMismatchException;
import java.util.*;
import java.util.Scanner;

public class Console implements IOMedium {
    private Scanner sc = new Scanner(System.in);

    private void header(){
        System.out.println("Concessionária José Motors\n");
    }

    public final void clearConsole(){
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
        System.out.println("(Aperte Enter para continuar)");
        sc.nextLine();
        clearConsole();
    }

    public void display(String message){
        if(message != null)
            System.out.println("\n" + message);
        System.out.println("\n");
            
        System.out.println("(Aperte Enter para continuar)");
        sc.nextLine();
        clearConsole();
    }

    public int optionMenu(String title, String empty, String [] options){
        int i, op;

        do{
            clearConsole();
            header();

            if(title != null){
                System.out.println(title + "\n");
            }

            if(options.length <=0)
                System.out.println(empty + "\n");

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

    public <T> void displayList(ArrayList<T> list){
        for(T member : list)
            System.out.println(member.toString() + "\n");
        
        display(null);
    }

    public int getInt(String title, String error, int exitValue){
        int ans;

        if(error==null)
            error = "Digite um numero valido!";
        
        while(true)
            try {
                clearConsole();
                header();
                System.out.println(title + "(Digite " + exitValue + " para sair)" + "\n");
                ans=sc.nextInt();
                sc.nextLine();
                break;
            } catch (InputMismatchException e) {
                sc.nextLine();
                panik(error);
                clearConsole();
            }

        return ans;
    }

    public double getDouble(String title, String error, double exitValue){
        double ans;

        if(error==null)
            error = "Digite um numero valido!";

        while(true)
            try {
                clearConsole();
                header();
        
                System.out.println(title + "(Digite " + exitValue + " para sair)" + "\n");
                ans=sc.nextDouble();
                sc.nextLine();
                break;
            } catch (InputMismatchException e) {
                sc.nextLine();
                panik(error);
                clearConsole();
            }
        
        return ans;
    }

    public String getString(String title, String error, String exitString){
        String ans="";

        if(error == null)
            error = "Resposta invalida";
                
        while(true){
            clearConsole();
            header();
            System.out.println(title + "(Digite " + exitString + " para sair)" + "\n");
            ans = sc.nextLine();

            if(!(ans == null || ans.equals("") || ans.equals("\n")))
                break;
            else
                panik(error);
                clearConsole();
        }   
        
        return ans;
    }

    public Date getDate(String title, String error){
        Date d;

        if(error == null)
            error = "Digite uma data valida!";

        while(true)
            try {
                clearConsole();
                header();
                System.out.println(title);
                d = new Date(sc.nextLine());

                if(d.isValid())
                    break;
                else
                    panik("Formato de data invalido! Digite uma data valida");
            } catch (Exception e) {
                panik(error);
            }

        return d;
    }
}
