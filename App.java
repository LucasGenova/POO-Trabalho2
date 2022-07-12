import java.util.*;
import java.io.*;

public class App {
    private static Employee loggedAs = null;
    private static IOMedium md = new Console();

    private static ArrayList<Manager> managers = new ArrayList<>();
    private static ArrayList<Seller> sellers = new ArrayList<>();
    private static ArrayList<Client> clients = new ArrayList<>();
    private static ArrayList<Vehicle> vehicles = new ArrayList<>();
    private static ArrayList<Sale> sales = new ArrayList<>();

    private static Scanner sc = new Scanner(System.in);

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

        if(loggedAs instanceof Manager)
            managerMenu();
        else
            sellerMenu();
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

        for(Seller seller : sellers){
            if(seller.getName().equals(credentials[0]) && seller.getPassword().equals(credentials[1])){
                //logged sucessfully
                loggedAs = seller;

                return true;
            }
        }

        return false;
    }

    public static void managerMenu(){
        int op;
        
        op = md.optionMenu(null, new String[]{
            "Menu Veiculos",
            "Menu Clientes",
            "Menu Funcionarios"
            //ToDo: add manager especific options
        });

        switch(op) {
            case -1: 
                return;
                
            case 0:
                vehicleMenu();
                break;
            
            case 1:
                clientMenu();
                break;
            
            case 2:
                employeeMenu();
                break;
        }
    }

    public static void vehicleMenu(){
        int op;

        op = md.optionMenu(null, new String[]{
            "Adicionar veículo",
            "Alterar veículo",
            "Remover veículo",
        });

        switch(op) {
            case -1:
                return;
            
            case 0:
                addVehicle();               
                break;
            
            case 1:
                modifyVehicle();
                break;
            
            case 2:
                removeVehicle();
                break;
        }
    }

    public static void clientMenu(){
        int op;

        op = md.optionMenu(null, new String[]{
            "Adicionar cliente",
            "Alterar cliente",
            "Remover cliente",
        });

        switch(op) {
            case -1:
                return;
            
            case 0:
                addClient();    
                break;
            
            case 1:
                modifyClient();
                break;
            
            case 2:
                removeClient();
                break;
        }
    }

    public static void employeeMenu(){
        int op;

        op = md.optionMenu(null, new String[]{
            "Adicionar funcionario",
            "Alterar funcionario",
            "Remover funcionario",
            "Mostrar informacoes de funcionario"
        });

        switch(op) {
            case -1:
                return;
            
            case 0:
                addEmployee();
                break;
            
            case 1:
                modifyEmployee();
                break;
            
            case 2:
                removeEmployee();
                break;
        }
    }

    public static void sellerMenu(){
        int op;

        op = md.optionMenu(null, new String[]{
            "Mostrar Veiculos",
            "Mostrar Clientes",
            "Mostrar Vendas",
            "Nova venda"
        });
        
        switch(op) {
            case -1:
                return;
            
            case 0:
                md.displayList();
                break;
            
            case 1:
                modifySale();
                break;
            
            case 2:
                removeSale();
                break;
        }
    }

    public static void addVehicle(){
        int op;
        Vehicle v;

        op = md.optionMenu(null, new String[] {
            "Adicionar Carro",
            "Adicionar Moto"
        });

        if(op==-1) {
            return;
        }
        else if(op==0) {
            Car c = new Car();
            Dimensions d = new Dimensions();
            
            System.out.println("\nDigite a potencia do carro: ");
            c.setHp(sc.nextInt());
            sc.nextLine();

            System.out.println("\nDigite a cilindrada do carro: ");
            c.setCilinders(sc.nextInt());
            sc.nextLine();

            System.out.println("\nDigite a quantidade de assentos do carro: ");
            c.setHp(sc.nextInt());
            sc.nextLine();

            System.out.println("\nDigite a altura do carro: ");
            d.setHeightM(sc.nextDouble());
            sc.nextLine();

            System.out.println("\nDigite a largura do carro: ");
            d.setWidthM(sc.nextDouble());
            sc.nextLine();

            System.out.println("\nDigite o comprimento do carro: ");
            d.setLenghtM(sc.nextDouble());
            sc.nextLine();

            c.setDimensions(d);

            v = c;
        }
        else {
            Motorcycle m = new Motorcycle();
            
            System.out.println("\nDigite a capacidade do motor da moto: ");
            m.setEngineCapacity(sc.nextDouble());
            sc.nextLine();

            System.out.println("\nDigite o tipo da moto: ");
            m.setType(sc.nextLine());
            sc.nextLine();

            v = m;
        }
        
        System.out.println("\nDigite o numero do chassi do veiculo: ");
        v.setChassiNumber(sc.nextLine());
        sc.nextLine();
        
        System.out.println("\nDigite a marca do veiculo: ");
        v.setBrand(sc.nextLine());

        System.out.println("\nDigite o modelo do veiculo: ");
        v.setModel(sc.nextLine());

        System.out.println("\nDigite o ano do veiculo: ");
        v.setYear(sc.nextInt());

        System.out.println("\nDigite a quilometragem do veiculo: ");
        v.setMileageKm(sc.nextDouble());

        System.out.println("\nDigite o peso do veiculo: ");
        v.setWeight(sc.nextDouble());

        System.out.println("\nDigite o status do veiculo: ");
        v.setStatus(sc.nextLine());

        vehicles.add(v);
    }

    public static void modifyVehicle(){
        int op;
        String chassiNumber;
        int i;

        System.out.println("\nDigite a placa do veiculo que voce deseja alterar: ");
        chassiNumber = sc.nextLine();

        for(i=0;i<vehicles.size();i++) {
            if(vehicles.get(i).getChassiNumber().equals(chassiNumber)) {
                break;
            }
            else if(i==(vehicles.size()-1)) {
                System.out.println("\nVeiculo nao encontrado");
                return;
            }
        }

        op = md.optionMenu("Qual atributo voce deseja modificar?", new String[] {
            "Numero do chassi",
            "Quilometragem",
            "Status"
        });

        switch(op) {
            case -1:
                return;
            
            case 0:
                System.out.println("\nDigite o novo valor do Chassi: ");
                vehicles.get(i).setChassiNumber(sc.nextLine());
                break;
            
            case 1:
                System.out.println("\nDigite o novo valor da quilometragem: ");
                vehicles.get(i).setMileageKm(sc.nextDouble());
                break;
            
            case 2:
                System.out.println("\nDigite o novo status: ");
                vehicles.get(i).setStatus(sc.nextLine());
                break;
        }
    }

    public static void removeVehicle(){
        String chassiNumber;
        int i;

        System.out.println("\nDigite a placa do veiculo que voce deseja alterar: ");
        chassiNumber = sc.nextLine();

        for(i=0;i<vehicles.size();i++) {
            if(vehicles.get(i).getChassiNumber().equals(chassiNumber)) {
                break;
            }
            else if(i==(vehicles.size()-1)) {
                System.out.println("\nVeiculo nao encontrado");
                return;
            }
        }

        vehicles.remove(i);
    }
    
    public static void addClient(){
        Client c = new Client();
        Address a = new Address();

        md.clearConsole();
        
        System.out.println("Digite o cpf do cliente: ");
        c.setCpf(sc.nextLine());

        System.out.println("Digite o nome do cliente: ");
        c.setName(sc.nextLine());

        System.out.println("Digite a data de aniversario do cliente: ");
        c.setBirthdate(new Date(sc.nextLine()));

        System.out.println("Digite o endereco do cliente: ");
        System.out.println("Digite a rua: ");
        a.setStreet(sc.nextLine());
        
        System.out.println("Digite o bairro: ");
        a.setDistrict(sc.nextLine());

        System.out.println("Digite a cidade: ");
        a.setCity(sc.nextLine());

        c.setAddress(a);

        System.out.println("Digite a renda do cliente: ");
        c.setIncome(sc.nextDouble());
        sc.nextLine();

        System.out.println("Digite o numero de dependentes do cliente: ");
        c.setDependents(sc.nextInt());
        sc.nextLine();
    }

    public static void modifyClient(){
        int op;
        String cpf;
        int i;

        System.out.println("\nDigite o cpf do cliente que voce deseja alterar: ");
        cpf = sc.nextLine();

        for(i=0;i<clients.size();i++) {
            if(clients.get(i).getCpf().equals(cpf)) {
                break;
            }
            else if(i==(clients.size()-1)) {
                System.out.println("\nCliente nao encontrado");
                return;
            }
        }

        op = md.optionMenu("Qual atributo voce deseja modificar?", new String[] {
            "Cpf",
            "Nome",
            "Data de aniversario",
            "Endereco",
            "Renda",
            "Numero de dependentes"
        });

        switch(op) {
            case -1:
                return;
            
            case 0:
                System.out.println("\nDigite o novo cpf: ");
                clients.get(i).setCpf(sc.nextLine());
                break;
            
            case 1:
                System.out.println("\nDigite o novo nome: ");
                clients.get(i).setName(sc.nextLine());
                break;
            
            case 2:
                System.out.println("\nDigite a nova data de aniversario: ");
                clients.get(i).setBirthdate(new Date(sc.nextLine()));
                break;

            case 3:
                Address a = new Address();
                System.out.println("\nDigite o novo endereco: ");

                System.out.println("Digite a nova rua: ");
                a.setStreet(sc.nextLine());
                
                System.out.println("Digite o novo bairro: ");
                a.setDistrict(sc.nextLine());

                System.out.println("Digite a nova cidade: ");
                a.setCity(sc.nextLine());
                
                clients.get(i).setAddress(a);
                break;

            case 4:
                System.out.println("\nDigite a nova renda: ");
                clients.get(i).setIncome(sc.nextDouble());
                sc.nextLine();
                break;

            case 5:
                System.out.println("\nDigite o novo cpf: ");
                clients.get(i).setCpf(sc.nextLine());
                break;
        }
    }

    public static void removeClient(){
        int op;
        String cpf;
        int i;

        System.out.println("\nDigite o cpf do cliente que voce deseja alterar: ");
        cpf = sc.nextLine();

        for(i=0;i<clients.size();i++) {
            if(clients.get(i).getCpf().equals(cpf)) {
                break;
            }
            else if(i==(clients.size()-1)) {
                System.out.println("\nVeiculo nao encontrado");
                return;
            }
        }

        clients.remove(i);
    }

    public static void addEmployee(){
        int op;
        Employee e;
        String date;

        op = md.optionMenu(null, new String[] {
            "Adicionar Gerente",
            "Adicionar Vendedor"
        });

        if(op==-1) {
            return;
        }
        else if(op==0) {
            Manager m = new Manager();
            
            System.out.println("Digite quantos anos de experiencia o gerente possui: ");
            m.setYearsOfExperience(sc.nextInt());
            sc.nextLine();

            e = m;
        }
        else {
            Seller s = new Seller();
            String rg;
            int i;
            
            System.out.println("\nDigite o tempo de treinamento restante do vendedor: ");
            s.setRemainingTrainingTime(sc.nextDouble());
            sc.nextLine();

            System.out.println("\nDigite o RG do gerente responsavel: ");
            rg = sc.nextLine();

            for(i=0;i<managers.size();i++) {
                if(managers.get(i).getRg().equals(rg)) {
                    break;
                }
                else if(i==managers.size()-1) {
                    System.out.println("\nGerente nao encontrado");
                    return;
                }
            }

            s.setResponsibleManager(managers.get(i));

            e = s;
        }

        System.out.println("\nDigite o RG do funcionario: ");
        e.setRg(sc.nextLine());

        System.out.println("\nDigite o nome do funcionario: ");
        e.setName(sc.nextLine());

        System.out.println("\nDigite a data de nascimento do funcionario (dd-mm-aa): ");
        date = sc.nextLine();

        Date db = new Date(date);

        e.setBirthdate(db);

        System.out.println("\nDigite a data de admissao do funcionario (dd-mm-aa): ");
        date = sc.nextLine();

        Date da = new Date(date);

        e.setAdmissiondate(da);

        System.out.println("\nDigite o salario do funcionario: ");
        e.setSalary(sc.nextDouble());
        sc.nextLine();

        System.out.println("\nDigite a senha do funcionario: ");
        e.setPassword(sc.nextLine());

        if(e instanceof Manager) {
            managers.add(((Manager) e));
        }
        else {
            sellers.add(((Seller) e));
        }
    }

    public static void modifyEmployee(){

    }

    public static void removeEmployee(){
        
    }
}