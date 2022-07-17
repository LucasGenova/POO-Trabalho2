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

    private static String [] carTypes = new String[] {"Utilitário", "Pickup", "Sedan", "Hatch", "Esportivo"};
    private static String [] motorcycleTypes = new String[] {"Trail", "Street", "Esportiva", "Custom"};

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
                op = md.optionMenu("Voce deseja: ", "", new String[]{
                    "Alterar Usuario"
                });
                    loggedAs = null;
                if(op != 0){
                    break;
                }
                
            }
                
            else{
                int op; 
                sellerMenu();
                op = md.optionMenu("Voce deseja: ", "", new String[]{
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
            md.panik(e.toString());
        }

        try {
            File arq = new File("sellers.txt");
            FileWriter writer = new FileWriter(arq, false);

            for(int i=0; i<sellers.size(); i++) {
                writer.write(sellers.get(i).serialize() + "\n");
            }

            writer.close();

        } catch (IOException e) {
            md.panik(e.toString());
        }

        try {
            File arq = new File("clients.txt");
            FileWriter writer = new FileWriter(arq, false);

            for(int i=0; i<clients.size(); i++) {
                writer.write(clients.get(i).serialize() + "\n");
            }

            writer.close();

        } catch (IOException e) {
            md.panik(e.toString());
        }

        try {
            File arq = new File("vehicles.txt");
            FileWriter writer = new FileWriter(arq, false);

            for(int i=0; i<vehicles.size(); i++) {
                writer.write(vehicles.get(i).serialize() + "\n");
            }

            writer.close();

        } catch (IOException e) {
            md.panik(e.toString());
        }

        try {
            File arq = new File("sales.txt");
            FileWriter writer = new FileWriter(arq, false);

            for(int i=0; i<sales.size(); i++) {
                writer.write(sales.get(i).serialize() + "\n");
            }

            writer.close();

        } catch (IOException e) {
            md.panik(e.toString());
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
            md.panik(e.toString());
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
            md.panik(e.toString());
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
            md.panik(e.toString());
        }

        try {
            File arq = new File("vehicles.txt");
            FileReader fileReader = new FileReader(arq);
            BufferedReader reader = new BufferedReader(fileReader);

            while(reader.ready()) {
                String [] chops = reader.readLine().split("; ");

                if(chops[0].equals("Carro")) {
                    vehicles.add(Car.parse(Arrays.copyOfRange(chops, 1, chops.length)));
                }
                else {
                    vehicles.add(Motorcycle.parse(Arrays.copyOfRange(chops, 1, chops.length)));
                }
            }

            reader.close();

        } catch (IOException e) {
            md.panik(e.toString());
        }

        try {
            File arq = new File("sales.txt");
            FileReader fileReader = new FileReader(arq);
            BufferedReader reader = new BufferedReader(fileReader);

            int i;
            while(reader.ready()) {
                String [] chops = reader.readLine().split("; ");
                Sale s = Sale.parse(chops);
                
                for(i=0; i<sellers.size(); i++){
                    if(sellers.get(i).getRg().equals(chops[1]))
                        s.setSeller(sellers.get(i));
                }
                
                for(i=0; i<clients.size(); i++){
                    if(clients.get(i).getCpf().equals(chops[2]))
                        s.setClient(clients.get(i));
                }
                
                for(i=0; i<vehicles.size(); i++){
                    if(vehicles.get(i).getChassiNumber().equals(chops[3]))
                        s.setVehicle(vehicles.get(i));
                }
            
                sales.add(s);
            }

            reader.close();

        } catch (IOException e) {
            md.panik(e.toString());
        }
    }
    public static boolean verifyCredentials(String [] credentials){
        if(managers.size() == 0 && sellers.size() == 0)
            return true;
            
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
            op = md.optionMenu(null, "", new String[]{
                "Menu Veiculos",
                "Menu Clientes",
                "Menu Funcionarios",
                "Nova venda"
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
                
                case 3:
                    addSale();
                    break;
            }
        }while(op!=-1);
    }

    public static void vehicleMenu(){
        int op;
        
        do {
            op = md.optionMenu(null, "", new String[]{
                "Adicionar veículo",
                "Alterar veículo",
                "Remover veículo",
                "Mostrar veículos"
            });

            switch(op) {
                case 0:
                    addVehicle();               
                    break;
                
                case 1:
                    modifyVehicle();
                    break;
                
                case 2:
                    removeVehicle();
                    break;
                case 3: 
                    md.displayList(vehicles);
            }
        }while(op != -1);
    }

    public static void clientMenu(){
        int op;

        do {
            op = md.optionMenu(null, "", new String[]{
                "Adicionar cliente",
                "Alterar cliente",
                "Remover cliente",
                "Mostrar clientes"
            });

            switch(op) {
                case 0:
                    addClient();    
                    break;
                
                case 1:
                    modifyClient();
                    break;
                
                case 2:
                    removeClient();
                    break;

                case 3:
                    md.displayList(clients);
                    break;
            }
        }while(op != -1);
    }

    public static void employeeMenu(){
        int op;

        do {
            op = md.optionMenu(null, "", new String[]{
                "Adicionar funcionario",
                "Alterar funcionario",
                "Remover funcionario",
                "Mostrar informacoes de funcionario"
            });

            switch(op) {
                case 0:
                    addEmployee();
                    break;
                
                case 1:
                    modifyEmployee();
                    break;
                
                case 2:
                    removeEmployee();
                    break;

                case 3:
                    for(Seller seller : sellers) {
                        int qtd=0, higherM=0, higherC=0;
                        int [] typeC = new int[]{0, 0, 0, 0, 0};
                        int [] typeM = new int[]{0, 0, 0, 0};
                        
                        if(loggedAs.getRg().equals(seller.getResponsibleManager().getRg())) {
                            System.out.print(seller.toString());

                            int i=0;
                        
                            for(Sale sale : sales) {
                                System.out.print(" Venda " + (i+1) + ": " + sale.getPaymentMethod());
                                
                                if(sale.getSeller().getRg().equals(seller.getRg())) {
                                    qtd++;

                                    if(sale.getVehicle() instanceof Car) {
                                        switch(sale.getVehicle().getType()) {
                                            case "Utilitario":
                                                typeC[0]++;
                                                break;

                                            case "Pickup":
                                                typeC[1]++;
                                                break;

                                            case "Sedan":
                                                typeC[2]++;
                                                break;
                                            
                                            case "Hatch":
                                                typeC[3]++;
                                                break;
                                            
                                            case "Esportivo":
                                                typeC[4]++;
                                                break;
                                        }
                                    }
                                    else if(sale.getVehicle() instanceof Motorcycle) {
                                        switch(sale.getVehicle().getType()) {
                                            case "Trail":
                                                typeM[0]++;
                                                break;

                                            case "Street":
                                                typeM[1]++;
                                                break;

                                            case "Esportiva":
                                                typeM[2]++;
                                                break;
                                            
                                            case "Custom":
                                                typeM[3]++;
                                                break;
                                        }
                                    }
                                }

                                i++;
                            }

                            for(i=0;i<5;i++) {
                                if(typeC[i]>higherC) {
                                    higherC = typeC[i];
                                }
                                if(typeM[i]>higherM) {
                                    higherM = typeM[i];
                                }
                            }

                            System.out.print(" Quantidade vendida: " + qtd);

                            if(qtd>0) {
                                System.out.print(" Tipo de carro mais vendido: ");

                                for(i=0;i<5;i++) {
                                    if(typeC[i]==higherC && higherC>0) {
                                        System.out.print(carTypes[i] + " ");
                                    }
                                }

                                System.out.print(" Tipo de moto mais vendida: ");

                                for(i=0;i<4;i++) {
                                    if(typeM[i]==higherM && higherM>0) {
                                        System.out.print(motorcycleTypes[i] + " ");
                                    }
                                }
                            }

                            System.out.println("\n");
                        }
                    }
            }
        }while(op!=-1);
    }

    public static void sellerMenu(){
        int op;

        do {
            op = md.optionMenu(null, "", new String[]{
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
        ArrayList<String> vehicleNames = new ArrayList<>();
        String aux;
        int n=0, op;
        
        s.setIdSale(md.getInt("Digite o id da venda: ", null));

        aux = md.getString("Digite o RG do vendedor: ", "Digite um RG valido");

        do {
            for(Seller search : sellers) {
                if(search.getRg().equals(aux)) {
                    s.setSeller(search);
                    break;
                }
            }

            if(s.getSeller() == null) {
                n = md.optionMenu("Vendedor nao encontrado!", "", new String[] {
                    "Digitar o RG novamente"
                });
            }
            else {
                n=-1;
            }
        }while(n!=-1);

        System.out.println();
        aux = md.getString("Digite o CPF do cliente: ", "Digite um CPF valido");

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

        for(Vehicle vehicle : vehicles) {
            if(vehicle.getStatus().equals("A venda")) {
                vehicleNames.add(vehicle.getModel() + " - " + vehicle.getChassiNumber());
            }
        }

        n = md.optionMenu("Qual veiculo voce deseja escolher? ", "Nenhum veiculo foi encontrado", vehicleNames.toArray(new String[vehicleNames.size()]));

        if(n <0) return;

        for(int i=0;i<vehicles.size();i++) {
            if(vehicleNames.get(n).split(" - ")[1].equals(vehicles.get(i).getChassiNumber())) {
                s.setVehicle(vehicles.get(i));
                break;
            }
        }

        s.getVehicle().setStatus("Vendido");

        s.setPrice(md.getDouble("Digite o valor da venda: ", null));

        Date d = new Date(md.getString("Digite a data da venda (dd-mm-aaaa): ", "Digite uma data valida!")); //todo date validation

        s.setDate(d);

        Time h = new Time(md.getString("Digite a hora da venda (hh:mm): ", "Digite uma hora valida")); //todo

        op = md.optionMenu("A venda foi: ", "", new String []{
            "A vista",
            "A prazo"
        });

        switch(op){
            case 0: s.setPaymentMethod("A vista"); break;
            case 1: s.setPaymentMethod("A prazo"); break;
        }
        

        s.setHour(h);

        sales.add(s);
    }

    public static void addVehicle(){
        int op, opStatus;
        Vehicle v;

        op = md.optionMenu(null, "", new String[] {
            "Adicionar Carro",
            "Adicionar Moto"
        });

        if(op==-1) {
            return;
        }
        else if(op==0) {
            Car c = new Car();
            Dimensions d = new Dimensions();
            
            c.setHp(md.getInt("Digite a potencia do carro:" , null));

            c.setCilinders(md.getInt("Digite a cilindrada do carro: " , null));

            c.setSeats(md.getInt("Digite a quantidade de assentos do carro: ", null));
 
            c.setType(carTypes[md.optionMenu("Escolha o tipo do veiculo", "", carTypes)]);

            d.setHeightM(md.getDouble("Digite a altura do carro: ", null));

            d.setWidthM(md.getDouble("Digite a largura do carro: ", null));

            d.setLenghtM(md.getDouble("Digite o comprimento do carro: ", null));

            c.setDimensions(d);

            v = c;

        }
        else {
            Motorcycle m = new Motorcycle();
            
            m.setEngineCapacity(md.getDouble("Digite a capacidade do motor da moto: ", null));

            m.setType(motorcycleTypes[md.optionMenu("Escolha o tipo do veiculo: ", "", motorcycleTypes)]);
            v = m;
        }
        
        v.setChassiNumber(md.getString("Digite o numero do chassi do veiculo: ", null));
        
        System.out.println("\nDigite a marca do veiculo: ");
        v.setBrand(md.getString("Digite a marca do veiculo: ", null));

        v.setModel(md.getString("Digite o modelo do veiculo: ", null));

        v.setYear(md.getInt("Digite o ano do veiculo: " , null));

        v.setMileageKm(md.getDouble("Digite a quilometragem do veiculo: ", null));

        v.setWeight(md.getDouble("Digite o peso do veiculo: ", null));

        opStatus = md.optionMenu("Digite o novo status do veiculo: ", "", new String []{
            "A venda",
            "Vendido"
        });

        switch(opStatus){
            case 0: v.setStatus("A venda"); break;
            case 1: v.setStatus("Vendido"); break;
        }

        vehicles.add(v);
    }

    public static void modifyVehicle(){
        int op, opStatus;
        String chassiNumber;
        int i;

        System.out.println();
        chassiNumber = md.getString("Digite o numero do chassi do veiculo que voce deseja alterar: ", null);

        for(i=0;i<vehicles.size();i++) {
            if(vehicles.get(i).getChassiNumber().equals(chassiNumber)) {
                break;
            }
            else if(i==(vehicles.size()-1)) {
                System.out.println("\nVeiculo nao encontrado");
                return;
            }
        }

        do {
            op = md.optionMenu("Qual atributo voce deseja modificar?", "", new String[] {
                "Numero do chassi",
                "Quilometragem",
                "Status"
            });

            switch(op) {
                case 0:
                    vehicles.get(i).setChassiNumber(md.getString("Digite o novo valor do Chassi: ", null));
                    break;
                
                case 1:
                    vehicles.get(i).setMileageKm(md.getDouble("Digite o novo valor da quilometragem: ", null));
                    break;
                
                case 2:
                    System.out.println("\nDigite o novo status: ");
                    

                    opStatus = md.optionMenu("Digite o novo status do veiculo: ", "", new String []{
                        "A venda",
                        "Vendido"
                    });
            
                    switch(opStatus){
                        case 0: vehicles.get(i).setStatus("A venda"); break;
                        case 1: vehicles.get(i).setStatus("Vendido"); break;
                    }
                    break;
            }
        }while(op!=-1);
    }

    public static void removeVehicle(){
        String chassiNumber;
        int i;

        chassiNumber = md.getString("Digite o numero do chassi do veiculo a ser alterado: ", null);

        for(i=0;i<vehicles.size();i++) {
            if(vehicles.get(i).getChassiNumber().equals(chassiNumber)) {
                break;
            }
            else if(i==(vehicles.size()-1)) {
                md.panik("Veiculo nao encontrado");
                return;
            }
        }

        vehicles.remove(i);
    }
    
    public static void addClient(){
        Client c = new Client();
        Address a = new Address();
        
        c.setCpf(md.getString("Digite o cpf do cliente: ", null));

        c.setName(md.getString("Digite o nome do cliente: ", null));

        c.setBirthdate(new Date(md.getString("Digite a data de aniversario do cliente: ", null)));

        a.setStreet(md.getString("Digite o endereco do cliente:\nDigite a rua: ", null));
        a.setDistrict(md.getString("Digite o endereco do cliente:\nDigite o bairro: ", null));
        a.setCity(md.getString("Digite o endereco do cliente:\nDigite a cidade: ", null));
        c.setAddress(a);

        c.setIncome(md.getDouble("Digite a renda do cliente: " , null));

        c.setDependents(md.getInt("Digite o numero de dependentes do cliente: ", null));

        clients.add(c);
    }

    public static void modifyClient(){
        int op;
        String cpf;
        int i;

        cpf = md.getString("Digite o cpf do cliente que voce deseja alterar: ", null);

        for(i=0;i<clients.size();i++) {
            if(clients.get(i).getCpf().equals(cpf)) {
                break;
            }
            else if(i==(clients.size()-1)) {
                System.out.println("\nCliente nao encontrado");
                return;
            }
        }

        do {
            op = md.optionMenu("Qual atributo voce deseja modificar?", "", new String[] {
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
                    clients.get(i).setCpf(md.getString("Digite o novo cpf: ", null));
                    break;
                
                case 1:
                    clients.get(i).setName(md.getString("Digite o novo nome: ", null));
                    break;
                
                case 2:
                    clients.get(i).setBirthdate(new Date(md.getString("Digite a nova data de aniversario: ", null)));
                    break;

                case 3:
                    Address a = new Address();

                    a.setStreet(md.getString("Digite o endereco do cliente:\nDigite a rua: ", null));
                    a.setDistrict(md.getString("Digite o endereco do cliente:\nDigite o bairro: ", null));
                    a.setCity(md.getString("Digite o endereco do cliente:\nDigite a cidade: ", null));
                    clients.get(i).setAddress(a);
                    break;

                case 4:
                    clients.get(i).setIncome(md.getDouble("Digite a nova renda: ", null));
                    break;

                case 5:
                    clients.get(i).setCpf(md.getString("Digite o novo cpf: ", null));
                    break;
            }
        }while(op!=-1);
    }

    public static void removeClient(){
        String cpf;
        int i;

        cpf = md.getString("Digite o cpf do cliente que voce deseja alterar: ", null);

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

        op = md.optionMenu(null, "", new String[] {
            "Adicionar Gerente",
            "Adicionar Vendedor"
        });

        if(op==-1) {
            return;
        }
        else if(op==0) {
            Manager m = new Manager();
            
            m.setYearsOfExperience(md.getInt("Digite quantos anos de experiencia o gerente possui: ", null));

            e = m;
        }
        else {
            Seller s = new Seller();
            String rg;
            int i;

            s.setRemainingTrainingTime(md.getDouble("Digite o tempo de treinamento restante do vendedor: " , null));

            rg = md.getString("Digite o RG do gerente responsavel: ", null);

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

        e.setRg(md.getString("Digite o RG do funcionario: ", null));

        e.setName(md.getString("Digite o nome do funcionario: ", null));

        date = md.getString("Digite a data de nascimento do funcionario (dd-mm-aaaa): ", null);

        Date db = new Date(date);

        e.setBirthdate(db);

        date = md.getString("Digite a data de admissao do funcionario (dd-mm-aaaa): ", null);

        Date da = new Date(date);

        e.setAdmissiondate(da);

        e.setSalary(md.getDouble("Digite o salario do funcionario: " , null));

        e.setPassword(md.getString("Digite a senha do funcionario: ", null));

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

        mos = md.optionMenu("Deseja alterar um: ", "", new String [] {
            "Gerente",
            "Vendedor"
        });

        if(mos==0) {
            rg = md.getString("Digite o RG do funcionario que deseja modificar: ", null);

            for(i=0;i<managers.size();i++) {
                if(managers.get(i).getRg().equals(rg)) {
                    break;
                }
                else if(i==(managers.size()-1)) {
                    System.out.println("\nGerente nao encontrado");
                    return;
                }
            }
            
            do {
                op = md.optionMenu("Qual atributo voce deseja modificar?", "", new String[] {
                    "Nome",
                    "Salario",
                    "Senha",
                    "Anos de experiencia"
                });

                switch(op) {
                    case -1:
                        return;
                    
                    case 0:
                        managers.get(i).setName(md.getString("Digite o novo nome: ", null));
                        break;
                    
                    case 1:
                        managers.get(i).setSalary(md.getDouble("Digite o novo salario: ", null));
                        break;
                    
                    case 2:
                        managers.get(i).setPassword(md.getString("Digite a nova senha: ", null));
                        break;

                    case 3:
                        managers.get(i).setYearsOfExperience(md.getInt("Digite um novo valor para a quantidade de anos de experiencia: ", null));
                        break;
                }
            }while(op!=-1);
        }
        else if(mos==1) {
            rg = md.getString("Digite o RG do funcionario que deseja modificar: ", null);

            for(i=0;i<sellers.size();i++) {
                if(sellers.get(i).getRg().equals(rg)) {
                    break;
                }
                else if(i==(sellers.size()-1)) {
                    System.out.println("\nGerente nao encontrado");
                    return;
                }
            }
            
            do {
                op = md.optionMenu("Qual atributo voce deseja modificar?", "", new String[] {
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
                        sellers.get(i).setName(md.getString("Digite o novo nome: ", null));
                        break;
                    
                    case 1:
                        sellers.get(i).setSalary(md.getDouble("Digite o novo salario: ", null));
                        break;
                    
                    case 2:
                        sellers.get(i).setPassword(md.getString("Digite a nova senha: ", null));
                        break;

                    case 3:
                        sellers.get(i).setRemainingTrainingTime(md.getDouble("Digite um novo tempo de experiencia restante: ", null));
                        break;
                    
                    case 4:
                        ArrayList<String> managerNames = new ArrayList<>();
                        int n;

                        for(Manager manager : managers) {
                            managerNames.add(manager.getName());
                        }
                        
                        n = md.optionMenu("Qual gerente voce deseja escolher? ", "Nenhum gerente cadastrado", managerNames.toArray(new String[managerNames.size()]));

                        sellers.get(i).setResponsibleManager(managers.get(n));
                }
            }while(op!=-1);
        }
    }

    public static void removeEmployee(){
        int op;
        String rg;
        op = md.optionMenu("Deseja remover um: ", "", new String []{
            "Gerente",
            "Vendedor"
        });

        switch(op){
            
            case 0:
                rg = md.getString("Digite o RG do Gerente: ", null);

                for(int i=0; i<managers.size(); i++){
                    if(managers.get(i).getRg().equals(rg)){
                        managers.remove(i);
                        break;
                    }
                }
                return;
                
                case 1:
                rg = md.getString("Digite o RG do Vendedor: ", null);

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