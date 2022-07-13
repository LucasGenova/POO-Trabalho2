public class Motorcycle extends Vehicle{
    private double engineCapacity;
    private String type;
    
    public Motorcycle(String chassiNumber, String brand, String model, int year, double mileageKm, double weight, String status, double engineCapacity, String type) {
        super(chassiNumber, brand, model, year, mileageKm, weight, status);
        this.engineCapacity = engineCapacity;
        this.type = type;
    }
    
    public Motorcycle() {
        this("", "", "", 0, 0, 0, "", 0.0, "");
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

    public String toString() {
        return(super.toString() + " Capacidade do motor: " + this.engineCapacity + " Tipo: " + this.type);
    }

    public String serialize(){
        return("Moto; " + super.serialize() + "; " + this.getEngineCapacity() + "; " + this.getType());
    }

    public static Motorcycle parse(String [] chops) {
        return new Motorcycle(chops[0], chops[1], chops[2], Integer.parseInt(chops[3]), Double.parseDouble(chops[4]), Double.parseDouble(chops[5]), chops[6], Double.parseDouble(chops[7]), chops[8]);
    }
}