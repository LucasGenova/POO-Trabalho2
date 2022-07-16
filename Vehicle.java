import java.util.Scanner;

public abstract class Vehicle{
    String chassiNumber;
    String brand;
    String model;
    int year;
    double mileageKm;
    double weight;
    String status;

    Scanner sc = new Scanner(System.in);

    public Vehicle(String chassiNumber, String brand, String model, int year, double mileageKm, double weight, String status) {
        this.chassiNumber = chassiNumber;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileageKm = mileageKm;
        this.weight = weight;
        this.status = status;
    }

    public Vehicle() {
        this("", "", "", 0, 0.0, 0.0, "");
    }

    public String getChassiNumber() {
        return chassiNumber;
    }

    public void setChassiNumber(String chassiNumber) {
        this.chassiNumber = chassiNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {

        while(year<1950 || year>2050) {
            System.out.println("\nDigite um valor valido: ");
            year = sc.nextInt();
            sc.nextLine();
        }
        
        this.year = year;
    }

    public double getMileageKm() {
        return mileageKm;
    }

    public void setMileageKm(double mileageKm) {
        this.mileageKm = mileageKm;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public abstract String getType();

    public String toString(){
        return ("Chassi: " + this.chassiNumber + " Marca: " + this.brand + " Modelo: " + this.model + " Ano: " + this.year + " Quilometragem: " + this.mileageKm + " Peso: " + this.weight + " Status: " + this.status);
    }

    public String serialize(){
        return (this.getChassiNumber() + "; " + this.getBrand() + "; " + this.getModel() + "; " + this.getYear() + "; " + this.getMileageKm() + "; " + this.getWeight() + "; " + this.getStatus());
    };
}
