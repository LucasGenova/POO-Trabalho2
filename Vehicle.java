public abstract class Vehicle {
    int chassiNumber;
    String brand;
    String model;
    int year;
    double mileageKm;
    double weight;
    String status;

    public Vehicle(int chassiNumber, String brand, String model, int year, double mileageKm, double weight, String status) {
        this.chassiNumber = chassiNumber;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileageKm = mileageKm;
        this.weight = weight;
        this.status = status;
    }

    public Vehicle() {
        this(0, "", "", 0, 0.0, 0.0, "");
    }

    public int getChassiNumber() {
        return chassiNumber;
    }

    public void setChassiNumber(int chassiNumber) {
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

    public String show(){
        return ("Chassi: " + this.chassiNumber + " Marca: " + this.brand + " Modelo: " + this.model + " Ano: " + this.year + " Quilometragem: " + this.mileageKm + " Peso: " + this.weight + " Status: " + this.status);
    }
}
