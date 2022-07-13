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
        loadFile();

        md.init();

        while(true){
            while(loggedAs == null){
                if(!verifyCredentials(md.loginScreen())){
                    md.panik("Usuario ou senha incorretos!");
                }           
            } 
            md.display("Logado como " + loggedAs.getName() + "!");

            if(loggedAs instanceof Manager){
                int op; 
                managerMenu();
                op = md.optionMenu("Voce deseja: ", new String[]{
                    "Aterar Usuario"
                });
                    loggedAs = null;
                if(op != 0){
                    break;
                }
                
            }
                
            else{
                int op; 
                sellerMenu();
                op = md.optionMenu("Voce deseja: ", new String[]{
                    "Alterar Usuario"
                });
                    loggedAs = null;
                if(op != 0){
                    break;
                }
            }
        }
    
        saveFile();
    }

    public static void saveFile() {
        try {
            File arq = new File("managers.txt");
            FileWriter writer = new FileWriter(arq, false);

            for(int i=0; i<managers.size(); i++) {
                writer.write(managers.get(i).serialize() + "\n");
            }

            writer.close();

        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            File arq = new File("sellers.txt");
            FileWriter writer = new FileWriter(arq, false);

            for(int i=0; i<sellers.size(); i++) {
                writer.write(sellers.get(i).serialize() + "\n");
            }

            writer.close();

        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            File arq = new File("clients.txt");
            FileWriter writer = new FileWriter(arq, false);

            for(int i=0; i<clients.size(); i++) {
                writer.write(clients.get(i).serialize() + "\n");
            }

            writer.close();

        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            File arq = new File("vehicles.txt");
            FileWriter writer = new FileWriter(arq, false);

            for(int i=0; i<vehicles.size(); i++) {
                writer.write(vehicles.get(i).serialize() + "\n");
            }

            writer.close();

        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            File arq = new File("sales.txt");
            FileWriter writer = new FileWriter(arq, false);

            for(int i=0; i<sales.size(); i++) {
                writer.write(sales.get(i).serialize() + "\n");
            }

            writer.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void loadFile(){
        try {
            File arq = new File("managers.txt");
            FileReader fileReader = new FileReader(arq);
            BufferedReader reader = new BufferedReader(fileReader);

            while(reader.ready()) {
                String [] chops = reader.readLine().split("; ");
                managers.add(Manager.parse(chops));
            }

            reader.close();

        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            File arq = new File("sellers.txt");
            FileReader fileReader = new FileReader(arq);
            BufferedReader reader = new BufferedReader(fileReader);

            while(reader.ready()) {
                String [] chops = reader.readLine().split("; ");
                Seller s = Seller.parse(chops);
                
                for(Manager m : managers) {
                    if(m.getRg().equals(chops[chops.length-1])) {
                        s.setResponsibleManager(m);
                        break;
                    }
                }
                
                sellers.add(s);
            }

            reader.close();

        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            File arq = new File("clients.txt");
            FileReader fileReader = new FileReader(arq);
            BufferedReader reader = new BufferedReader(fileReader);

            while(reader.ready()) {
                String [] chops = reader.readLine().split("; ");
                clients.add(Client.parse(chops));
            }

            reader.close();

        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            File arq = new File("vehicles.txt");
            FileReader fileReader = new FileReader(arq);
            BufferedReader reader = new BufferedReader(fileReader);

            while(reader.ready()) {
                String [] chops = reader.readLine().split("; ");

                if(chops[0].equals("Carro")) {
                    vehicles.add(Car.parse(chops));
                }
                else {
                    vehicles.add(Motorcycle.parse(chops));
                }
            }

            reader.close();

        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            File arq = new File("sales.txt");
            FileReader fileReader = new FileReader(arq);
            BufferedReader reader = new BufferedReader(fileReader);

            while(reader.ready()) {
                String [] chops = reader.readLine().split("; ");
                Sale s = Sale.parse(chops);

            
                sales.add(s);
            }

            reader.close();

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

        do {
            op = md.optionMenu(null, new String[]{
                "Menu Veiculos",
                "Menu Clientes",
                "Menu Funcionarios"
            });

            switch(op) {
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
        }while(op!=-1);
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

        do {
            op = md.optionMenu(null, new String[]{
                "Mostrar Veiculos",
                "Mostrar Clientes",
                "Mostrar Vendas",
                "Nova venda"
            });
            
            switch(op) {
                case 0:
                    md.displayList(vehicles);
                    break;
                
                case 1:
                    md.displayList(clients);
                    break;
                
                case 2:
                    md.displayList(sales);
                    break;
                
                case 3:
                    addSale();
                    break;
            }
        }while(op!=-1);
    }

    public static void addSale() {
        Sale s = new Sale();
        String aux;
        
        System.out.println("\nDigite o id da venda: ");
        s.setIdSale(sc.nextInt());
        sc.nextLine();

        System.out.println("\nDigite o RG do vendedor: ");
        aux = sc.nextLine();

        for(Seller search : sellers) {
            if(search.getRg().equals(aux)) {
                s.setSeller(search);
                break;
            }
        }

        if(s.getSeller() == null) {
            System.out.println("\nVendedor nao encontrado");
            return;
        }

        System.out.println("\nDigite o CPF do cliente: ");
        aux = sc.nextLine();

        for(Client search : clients) {
            if(search.getCpf().equals(aux)) {
                s.setClient(search);
                break;
            }
        }

        if(s.getClient() == null) {
            System.out.println("\nCliente nao encontrado");
            return;
        }

        System.out.println("\nDigite o numero do chassi do veiculo: ");
        aux = sc.nextLine();

        for(Vehicle search : vehicles) {
            if(search.getChassiNumber().equals(aux)) {
                s.setVehicle(search);
                break;
            }
        }

        if(s.getVehicle() == null) {
            System.out.println("\nVeiculo nao encontrado");
            return;
        }

        System.out.println("\nDigite o valor da venda: ");
        s.setPrice(sc.nextDouble());
        sc.nextLine();

        System.out.println("\nDigite a data da venda (dd-mm-aa): ");
        Date d = new Date(sc.nextLine());

        s.setDate(d);

        System.out.println("\nDigite a hora da venda (hh:mm): ");
        Time h = new Time(sc.nextLine());

        s.setHour(h);

        sales.add(s);
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
            c.setSeats(sc.nextInt());
            sc.nextLine();

            System.out.println("\nDigite o tipo do carro: ");
            c.setType(sc.nextLine());

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
        

            v = m;
        }
        
        System.out.println("\nDigite o numero do chassi do veiculo: ");
        v.setChassiNumber(sc.nextLine());
        
        System.out.println("\nDigite a marca do veiculo: ");
        v.setBrand(sc.nextLine());

        System.out.println("\nDigite o modelo do veiculo: ");
        v.setModel(sc.nextLine());

        System.out.println("\nDigite o ano do veiculo: ");
        v.setYear(sc.nextInt());
        sc.nextLine();

        System.out.println("\nDigite a quilometragem do veiculo: ");
        v.setMileageKm(sc.nextDouble());
        sc.nextLine();

        System.out.println("\nDigite o peso do veiculo: ");
        v.setWeight(sc.nextDouble());
        sc.nextLine();

        System.out.println("\nDigite o status do veiculo: ");
        v.setStatus(sc.nextLine());

        vehicles.add(v);
    }

    public static void modifyVehicle(){
        int op;
        String chassiNumber;
        int i;

        System.out.println("\nDigite o numero do chassi do veiculo que voce deseja alterar: ");
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

        clients.add(c);
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
        int op, mos;
        String rg;
        int i;

        mos = md.optionMenu("Deseja alterar um: ", new String [] {
            "Gerente",
            "Vendedor"
        });

        if(mos==0) {
            System.out.println("\nDigite o RG do funcionario que deseja modificar: ");
            rg = sc.nextLine();

            for(i=0;i<managers.size();i++) {
                if(managers.get(i).getRg().equals(rg)) {
                    break;
                }
                else if(i==(managers.size()-1)) {
                    System.out.println("\nGerente nao encontrado");
                    return;
                }
            }
            
            op = md.optionMenu("Qual atributo voce deseja modificar?", new String[] {
                "Nome",
                "Salario",
                "Senha",
                "Anos de experiencia"
            });

            switch(op) {
                case -1:
                    return;
                
                case 0:
                    System.out.println("\nDigite o novo nome: ");
                    managers.get(i).setName(sc.nextLine());
                    break;
                
                case 1:
                    System.out.println("\nDigite o novo salario: ");
                    managers.get(i).setSalary(sc.nextDouble());
                    sc.nextLine();
                    break;
                
                case 2:
                    System.out.println("\nDigite a nova senha: ");
                    managers.get(i).setPassword(sc.nextLine());
                    break;

                case 3:
                    System.out.println("\nDigite um novo valor para a quantidade de anos de experiencia: ");
                    
                    managers.get(i).setYearsOfExperience(sc.nextInt());
                    sc.nextLine();
                    break;
            }
        }
        else if(mos==1) {
            System.out.println("\nDigite o RG do funcionario que deseja modificar: ");
            rg = sc.nextLine();

            for(i=0;i<sellers.size();i++) {
                if(sellers.get(i).getRg().equals(rg)) {
                    break;
                }
                else if(i==(sellers.size()-1)) {
                    System.out.println("\nGerente nao encontrado");
                    return;
                }
            }
            
            op = md.optionMenu("Qual atributo voce deseja modificar?", new String[] {
                "Nome",
                "Salario",
                "Senha",
                "Tempo de treinamento restante",
                "Gerente responsavel"
            });

            switch(op) {
                case -1:
                    return;
                
                case 0:
                    System.out.println("\nDigite o novo nome: ");
                    sellers.get(i).setName(sc.nextLine());
                    break;
                
                case 1:
                    System.out.println("\nDigite o novo salario: ");
                    sellers.get(i).setSalary(sc.nextDouble());
                    sc.nextLine();
                    break;
                
                case 2:
                    System.out.println("\nDigite a nova senha: ");
                    sellers.get(i).setPassword(sc.nextLine());
                    break;

                case 3:
                    System.out.println("\nDigite um novo tempo de experiencia restante: ");
                    
                    sellers.get(i).setRemainingTrainingTime(sc.nextDouble());
                    sc.nextLine();
                    break;
                
                case 4:
                    ArrayList<String> managerNames = new ArrayList<>();
                    int n;

                    for(Manager manager : managers) {
                        managerNames.add(manager.getName());
                    }
                    
                    n = md.optionMenu("Qual gerente voce deseja escolher? ", (String []) managerNames.toArray());

                    sellers.get(i).setResponsibleManager(managers.get(n));
            }
        }
    }

    public static void removeEmployee(){
        int op;
        String rg;
        op = md.optionMenu("Deseja remover um: ",new String []{
            "Gerente",
            "Vendedor"
        });

        switch(op){
            
            case 0:
                System.out.println("Digite o RG do Gerente: ");
                rg = sc.nextLine();

                for(int i=0; i<managers.size(); i++){
                    if(managers.get(i).getRg().equals(rg)){
                        managers.remove(i);
                        break;
                    }
                }
                return;
                
                case 1:
                System.out.println("Digite o RG do Vendedor: ");
                rg = sc.nextLine();

                for(int i=0; i<sellers.size(); i++){
                    if(sellers.get(i).getRg().equals(rg)){
                        sellers.remove(i);
                        break;
                    }
                }
                return;
        }
    }
}