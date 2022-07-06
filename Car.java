public class Car extends Vehicle {
    int hp; //horsepower
    int cilinders;
    int seats;
    String type;
    Dimensions dimensions;
    
    public Car(int chassiNumber, String brand, String model, int year, double mileageKm, double weight, String status, int hp, int cilinders, int seats, String type, Dimensions dimensions) {
        super(chassiNumber, brand, model, year, mileageKm, weight, status);
        this.hp = hp;
        this.cilinders = cilinders;
        this.seats = seats;
        this.type = type;
        this.dimensions = dimensions;
    }
    
    public Car() {
        this(0, "", "", 0, 0, 0, "", 0, 0, 0, "", new Dimensions());
    }

    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    public int getCilinders() {
        return cilinders;
    }
    public void setCilinders(int cilinders) {
        this.cilinders = cilinders;
    }
    public int getSeats() {
        return seats;
    }
    public void setSeats(int seats) {
        this.seats = seats;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Dimensions getDimensions() {
        return dimensions;
    }
    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    } 

    public String show(){
        return(super.show() + " Potencia: " + this.hp + " Numero de cilindros: " + this.cilinders + " Numero de ocupantes: " + this.seats + " Tipo: " + this.type);
    }
}
