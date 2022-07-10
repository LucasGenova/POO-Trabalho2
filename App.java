import java.util.ArrayList;
import java.io.*;

public class App {
    private static Employee loggedAs = null;
    private static IOMedium md = new Console();

    private static ArrayList<Manager> managers = new ArrayList<>();
    private static ArrayList<Seller> sellers = new ArrayList<>();
    private static ArrayList<Client> clients = new ArrayList<>();
    private static ArrayList<Car> cars = new ArrayList<>();
    private static ArrayList<Motorcycle> motorcycles = new ArrayList<>();
    private static ArrayList<Sale> sales = new ArrayList<>();


    public static void main(String[] args) {
        Manager m1 = new Manager("a", "a", new Date(1, 1, 1950), new Date(1, 1, 1951), 1, "244466666", 1);
        Manager m2 = new Manager("b", "b", new Date(2, 2, 2000), new Date(2, 2, 2020), 2, "password1", 2);

        managers.add(m1);
        managers.add(m2);
        
        saveManagers();

        md.init();
        
        while(loggedAs == null){
            if(!verifyCredentials(md.loginScreen())){
                md.panik("Usuario ou senha incorretos!");
            }           
        } 
        md.display("Logado como " + loggedAs.getName() + "!");
    }

    public static void saveManagers(){
        try {
            File arq = new File("managers.txt");
            FileWriter writer = new FileWriter(arq, false);

            for(int i=0; i<managers.size(); i++) {
                writer.write(managers.get(i).show() + "\n");
            }

            writer.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static boolean verifyCredentials(String [] credentials){
        for(Manager manager : managers){
            if(manager.getName().equals(credentials[0]) && manager.getPassword().equals(credentials[1])){
                //logged sucessfully
                loggedAs = manager;

                return true;
            }
        }
        return false;
    }
}