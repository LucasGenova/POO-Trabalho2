import java.util.*;
import java.io.*; 

import java.util.Scanner;

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

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        loadFile();
        md.init();

        while(true){
            while(loggedAs == null){
                while(managers.size()<=0){
                    md.panik("Nenhum gerente cadastrado!");
                    addEmployee();
                }

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
                            }

                            for(i=0;i<4;i++) {
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
                        }
                    }
                    md.panik("");
                    break;
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
        int n=0, op, resp;
        double respD;
        
        resp = md.getInt("Digite o id da venda: ", null, 0);
        if(resp == 0) return;
            s.setIdSale(resp);

        aux = md.getString("Digite o RG do vendedor: ", "Digite um RG valido", "0");
        if(aux.equals("0")) return;

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
                if(n==-1) return; 
            } 
            else {
                n=-1;
            }
        }while(n!=-1); 

        while(true){
        aux = md.getString("Digite o CPF do cliente: ", "Digite um CPF valido", "0");
        if(aux.equals("0")) return;

            for(Client search : clients) {
                if(search.getCpf().equals(aux)) {
                    s.setClient(search);
                    break;
                }
            }

            if(s.getClient() == null) {
                md.panik("\nCliente nao encontrado");
            }
            else
                break;
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

        respD = md.getDouble("Digite o valor da venda: ", null, 0);
        if(respD == 0) return;
            s.setPrice(respD);

        Date d = md.getDate("Digite a data da venda (dd-mm-aaaa): ", "Digite uma data valida!",  "0");
        
        if(d == null) return;

        s.setDate(d); 

        Time h = md.getTime("Digite a hora da venda (hh:mm): ", "Digite uma hora valida", "0"); //todo

        if(h == null) return;
        
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

        s.getVehicle().setStatus("Vendido"); 
    }

    public static void addVehicle(){
        int op, opStatus, resp;
        double respD;
        String aux;
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
            
            resp = md.getInt("Digite a potencia do carro:", null, 0);
            if(resp == 0) return;
                c.setHp(resp);

            resp = md.getInt("Digite a cilindrada do carro: ", null, 0);
            if(resp == 0) return;
                c.setCilinders(resp);

            resp = md.getInt("Digite a quantidade de assentos do carro: ", null, 0);
            if(resp == 0) return;
                c.setSeats(resp);
 
            c.setType(carTypes[md.optionMenu("Escolha o tipo do veiculo", "", carTypes)]);

            respD = md.getDouble("Digite a altura do carro: ", null, 0.0);
            if(respD == 0.0) return;
                d.setHeightM(respD);

            respD = md.getDouble("Digite a largura do carro: ", null, 0.0);
            if(respD == 0.0) return;
                d.setWidthM(respD);

            respD = md.getDouble("Digite o comprimento do carro: ", null, 0.0);
            if(respD == 0.0) return;
                d.setLenghtM(respD);

            c.setDimensions(d);

            v = c;

        }
        else {
            Motorcycle m = new Motorcycle();

            respD = md.getDouble("Digite a capacidade do motor da moto: ", null, 0.0);
            if(respD == 0.0) return;
                m.setEngineCapacity(respD);

            m.setType(motorcycleTypes[md.optionMenu("Escolha o tipo do veiculo: ", "", motorcycleTypes)]);
            v = m;
        }

        aux = md.getString("Digite o numero do chassi do veiculo: ", null, "0");
        if(aux.equals("0")) return;
            v.setChassiNumber(aux);
        
        aux = md.getString("Digite a marca do veiculo: ", null, "0");
        if(aux.equals("0")) return;
            v.setBrand(aux);

        aux = md.getString("Digite o modelo do veiculo: ", null, "0");
        if(aux.equals("0")) return;
            v.setModel(aux);

        resp = md.getInt("Digite o ano do veiculo: ", null, 0);
        if(resp == 0) return;
            v.setYear(resp);

        respD = md.getDouble("Digite a quilometragem do veiculo: ", null, 0.0);
        if(respD == 0.0) return;
            v.setMileageKm(respD);

        respD = md.getDouble("Digite o peso do veiculo: ", null, 0.0);
        if(respD == 0.0) return;
            v.setWeight(respD);

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
        double respD;
        String chassiNumber, aux;
        int i;

        System.out.println();
        chassiNumber = md.getString("\nDigite o numero do chassi do veiculo que voce deseja alterar: ", null, "0");
        if(chassiNumber.equals("0")) return;

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
                    aux = md.getString("Digite o novo valor do Chassi: ", null, "0");
                    vehicles.get(i).setChassiNumber(aux);
                    break;
                
                case 1:
                    respD = md.getDouble("Digite o novo valor da quilometragem: ", null, 0.0);
                    if(respD == 0.0) return;
                        vehicles.get(i).setMileageKm(respD);
                    break;
                
                case 2:
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

        chassiNumber = md.getString("\nDigite o numero do chassi do veiculo a ser removido: ", null, "0");
        if(chassiNumber.equals("0")) return;

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
        md.panik("Veiculo removido com sucesso!");
    }
    
    public static void addClient(){
        int resp;
        double respD;
        String aux;
        Client c = new Client();
        Address a = new Address();
        Date d;
        
        aux = md.getString("Digite o cpf do cliente: ", null, "0");
        if(aux.equals("0")) return;
        c.setCpf(aux);

        aux = md.getString("Digite o nome do cliente: ", null, "0");
        if(aux.equals("0")) return;
        c.setName(aux);

        d = md.getDate("Digite a data de aniversario do cliente: ", null, "0");
        if(d == null) return;
        c.setBirthdate(d);

        aux = md.getString("Digite o endereco do cliente:\nDigite a rua: ", null, "0");
        if(aux.equals("0")) return;
        a.setStreet(aux);

        aux = md.getString("Digite o endereco do cliente:\nDigite o bairro: ", null, "0");
        if(aux.equals("0")) return;
        a.setDistrict(aux);

        aux = md.getString("Digite o endereco do cliente:\nDigite a cidade: ", null, "0");
        if(aux.equals("0")) return;
        a.setCity(aux);
        c.setAddress(a);

        respD = md.getDouble("Digite a renda do cliente: ", null, -1.0);
        if(respD == -1.0) return;
            c.setIncome(respD);

        resp = md.getInt("Digite o numero de dependentes do cliente: ", null, -1);
        if(resp == -1) return;
            c.setDependents(resp);

        clients.add(c);
    }

    public static void modifyClient(){
        int op, i, resp;
        double respD;
        String cpf, aux;
        Date d;

        cpf = md.getString("Digite o cpf do cliente que voce deseja alterar: ", null, "0");
        if(cpf.equals("0")) return;

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
                    aux = md.getString("Digite o novo cpf: ", null, "0");
                    if(aux.equals("0")) return;
                    clients.get(i).setCpf(aux);
                    break;
                
                case 1:
                    aux = md.getString("Digite o novo nome: ", null, "0");
                    if(aux.equals("0")) return;
                    clients.get(i).setName(aux);
                    break;
                
                case 2:
                    d = md.getDate("Digite a nova data de aniversario: ", null, "0");
                    if(d == null) return;
                    clients.get(i).setBirthdate(d);
                    break;

                case 3:
                    Address a = new Address();

                    aux = md.getString("Digite o endereco do cliente:\nDigite a rua: ", null, "0");
                    if(aux.equals("0")) return;
                    a.setStreet(aux);

                    aux = md.getString("Digite o endereco do cliente:\nDigite o bairro: ", null, "0");
                    if(aux.equals("0")) return;
                    a.setDistrict(aux);

                    aux = md.getString("Digite o endereco do cliente:\nDigite a cidade: ", null, "0");
                    if(aux.equals("0")) return;
                    a.setCity(aux);
                    clients.get(i).setAddress(a);
                    break;

                case 4:
                    respD = md.getDouble("Digite a nova renda: ", null, -1.0);
                    if(respD == -1.0) return;
                        clients.get(i).setIncome(respD);
                    break;

                case 5:
                    resp = md.getInt("Digite o novo numero de dependentes: ", null, 0);
                    if(resp==0) return;
                    clients.get(i).setDependents(resp);
                    break;
            }
        }while(op!=-1);
    }

    public static void removeClient(){
        String cpf;
        int i;

        cpf = md.getString("\nDigite o cpf do cliente que voce deseja remover: ", null, "0");
        if(cpf.equals("0")) return;

        for(i=0;i<clients.size();i++) {
            if(clients.get(i).getCpf().equals(cpf)) {
                break;
            }
            else if(i==(clients.size()-1)) {
                System.out.println("\nCliente nao encontrado");
                return;
            }
        }

        clients.remove(i);
        md.panik("Cliente removido com sucesso!");
    }

    public static void addEmployee(){
        int op, resp;
        double respD;
        String aux;
        Employee e;
        Date d;

        op = md.optionMenu(null, "", new String[] {
            "Adicionar Gerente",
            "Adicionar Vendedor"
        });

        if(op==-1) {
            return;
        }
        else if(op==0) {
            Manager m = new Manager();
            
            resp = md.getInt("Digite quantos anos de experiencia o gerente possui: ", null, -1);
            if(resp == -1) return;
                m.setYearsOfExperience(resp);

            e = m;
        }
        else {
            Seller s = new Seller();
            String rg;
            ArrayList<String> managerNames = new ArrayList<>();
            int i, selectedManager;

            respD = md.getDouble("Digite o tempo de treinamento restante do vendedor: ", null, 0.0);
            if(respD == 0.0) return;
                s.setRemainingTrainingTime(respD);

            for(Manager manager : managers){
                managerNames.add(manager.getRg() + "-" + manager.getName());
            }

            selectedManager = md.optionMenu("Escolha o gerente responsavel: ", "Nenhum gerente cadastrado", managerNames.toArray(new String [managerNames.size()]));

            if(selectedManager==-1)
                return;

            rg = (managerNames.get(selectedManager).split("-")[0]).trim();

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

        aux = md.getString("Digite o RG do funcionario: ", null, "0");
        if(aux.equals("0")) return;
        e.setRg(aux);

        aux = md.getString("Digite o nome do funcionario: ", null, "0");
        if(aux.equals("0")) return;
        e.setName(aux);

        d = md.getDate("Digite a data de nascimento do funcionario (dd-mm-aaaa): ", null, "0");
        if(d == null) return;
        e.setBirthdate(d);

        d = md.getDate("Digite a data de admissao do funcionario (dd-mm-aaaa): ", null, "0");
        if(d == null) return;
        e.setAdmissiondate(d);

        respD = md.getDouble("Digite o salario do funcionario: ", null, -1.0);
        if(respD == -1.0) return;
            e.setSalary(respD);

        aux = md.getString("Digite a senha do funcionario: ", null, "0");
        if(aux.equals("0")) return;
        e.setPassword(aux);

        if(e instanceof Manager) {
            managers.add(((Manager) e));
        }
        else {
            sellers.add(((Seller) e));
        }
    }

    public static void modifyEmployee(){
        int op, mos, resp;
        double respD;
        String rg, aux;
        int i;

        mos = md.optionMenu("Deseja alterar um: ", "", new String [] {
            "Gerente",
            "Vendedor"
        });

        if(mos==0) {
            rg = md.getString("Digite o RG do funcionario que deseja modificar: ", null, "0");
            if(rg.equals("0")) return;

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
                        aux = md.getString("Digite o novo nome: ", null, "0");
                        if(aux.equals("0")) return;
                        managers.get(i).setName(aux);
                        break;
                    
                    case 1:
                        respD = md.getDouble("Digite o novo salario: ", null, -1.0);
                        if(respD == -1.0) return;
                            managers.get(i).setSalary(respD);
                        break;
                    
                    case 2:
                        aux = md.getString("Digite a nova senha: ", null, "0");
                        if(aux.equals("0")) return;
                        managers.get(i).setPassword(aux);
                        break;

                    case 3:
                        resp = md.getInt("Digite um novo valor para a quantidade de anos de experiencia: ", null, -1);
                        if(resp == -1) return;
                            managers.get(i).setYearsOfExperience(resp);
                        break;
                }
            }while(op!=-1);
        }
        else if(mos==1) {
            rg = md.getString("Digite o RG do funcionario que deseja modificar: ", null, "0");
            if(rg.equals("0")) return;

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
                        aux = md.getString("Digite o novo nome: ", null, "0");
                        if(aux.equals("0")) return;
                        sellers.get(i).setName(aux);
                        break;
                    
                    case 1:
                        respD = md.getDouble("Digite o novo salario: ", null, 0.0);
                        if(respD == 0.0) return;
                            sellers.get(i).setSalary(respD);
                        break;
                    
                    case 2:
                        aux = md.getString("Digite a nova senha: ", null, "0");
                        if(aux.equals("0")) return;
                        sellers.get(i).setPassword(aux);
                        break;

                    case 3:
                        respD = md.getDouble("Digite um novo tempo de experiencia restante: ", null, -1);
                        if(respD == -1) return;
                            sellers.get(i).setRemainingTrainingTime(respD);
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
                rg = md.getString("Digite o RG do Gerente: ", null, "0");
                if(rg.equals("0")) return;

                for(int i=0; i<managers.size(); i++){
                    if(managers.get(i).getRg().equals(rg)){
                        managers.remove(i);
                        md.panik("Gerente removido com sucesso!");
                        break;
                    }
                }
                return;
                
                case 1:
                rg = md.getString("Digite o RG do Vendedor: ", null, "0");
                if(rg.equals("0")) return;

                for(int i=0; i<sellers.size(); i++){
                    if(sellers.get(i).getRg().equals(rg)){
                        sellers.remove(i);
                        md.panik("Vendedor removido com sucesso!");
                        break;
                    }
                }
                return;
        }
    }
}