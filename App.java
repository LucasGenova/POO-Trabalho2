import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class App {
    private static ArrayList<Manager> managers = new ArrayList<>();
    private static ArrayList<Seller> sellers = new ArrayList<>();
    private static ArrayList<Client> clients = new ArrayList<>();
    private static ArrayList<Car> cars = new ArrayList<>();
    private static ArrayList<Motorcycle> motorcycles = new ArrayList<>();
    private static ArrayList<Sale> sales = new ArrayList<>();


    public static void main(String[] args) {
        Manager m1 = new Manager("a", "a", new Date(1, 1, 1950), new Date(1, 1, 1951), 1, 1);
        Manager m2 = new Manager("b", "b", new Date(2, 2, 2000), new Date(2, 2, 2020), 2, 2);

        managers.add(m1);
        managers.add(m2);
        
        saveManagers();
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

    public static void saveSeller(){
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

    public static void saveClient(){
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

    public static void saveCars(){
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


    
}