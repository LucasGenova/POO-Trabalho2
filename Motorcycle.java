public class Motorcycle extends Vehicle{
    private double engineCapacity;
    private String type;
    
    public Motorcycle(int chassiNumber, String brand, String model, int year, double mileageKm, double weight, String status, double engineCapacity, String type) {
        super(chassiNumber, brand, model, year, mileageKm, weight, status);
        this.engineCapacity = engineCapacity;
        this.type = type;
    }
    
    public Motorcycle() {
        this(0, "", "", 0, 0, 0, "", 0.0, "");
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }
    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String show() {
        return(super.show() + " Capacidade do motor: " + this.engineCapacity + " Tipo: " + this.type);
    }
}