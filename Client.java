public class Client {
    private String cpf;
    private String name;
    private Date birthdate;
    private Address address;
    private double income;
    private int dependents;

    public Client(String cpf, String name, Date birthdate, Address address, double income, int dependents) {
        this.cpf = cpf;
        this.name = name;
        this.birthdate = birthdate;
        this.address = address;
        this.income = income;
        this.dependents = dependents;
    }

    public Client() {
        this("", "", new Date(), new Address(), 0.0, 0);
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public double getIncome() {
        return income;
    }
    public void setIncome(double income) {
        this.income = income;
    }
    public int getDependents() {
        return dependents;
    }
    public void setDependents(int dependents) {
        this.dependents = dependents;
    }

    public String toString() {
        return("CPF: " + this.cpf + " Nome: " + this.name + " Data de nascimento: " + this.birthdate.toString() + "Endereco {" + this.address.toString() + "} Renda: " + this.income + " Dependentes: " + this.dependents);
    }

    public String serialize(){
        return(this.cpf + "; " + this.name + "; " + this.birthdate.serialize() + "; " + this.address.serialize() + "; " + this.income  + "; " + this.dependents);
    }

    public static Client parse(String [] chops){
        return new Client(chops[0], chops[1], new Date(chops[2]), new Address(chops[3], chops[4], chops[5]), Double.parseDouble(chops[6]), Integer.parseInt(chops[7]));
    }
}
