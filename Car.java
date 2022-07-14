public class Car extends Vehicle {
    int hp; //horsepower
    int cilinders;
    int seats;
    String type;
    Dimensions dimensions;
    
    public Car(String chassiNumber, String brand, String model, int year, double mileageKm, double weight, String status, int hp, int cilinders, int seats, String type, Dimensions dimensions) {
        super(chassiNumber, brand, model, year, mileageKm, weight, status);
        this.hp = hp;
        this.cilinders = cilinders;
        this.seats = seats;
        this.type = type;
        this.dimensions = dimensions;
    }
    
    public Car() {
        this("", "", "", 0, 0, 0, "", 0, 0, 0, "", new Dimensions());
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

    public String toString(){
        return(super.toString() + " Potencia: " + this.hp + " Numero de cilindros: " + this.cilinders + " Numero de ocupantes: " + this.seats + " Tipo: " + this.type);
    }

    public String serialize() {
        return ("Carro; " + super.serialize() + "; " + this.getHp() + "; " + this.getCilinders() + "; " + this.getSeats() + "; " + this.getType() + "; " + this.getDimensions().serialize());
    }

    public static Car parse(String [] chops){
        return new Car(chops[0], chops[1], chops[2], Integer.parseInt(chops[3]), Double.parseDouble(chops[4]), Double.parseDouble(chops[5]), chops[6], Integer.parseInt(chops[7]), Integer.parseInt(chops[8]), Integer.parseInt(chops[9]), chops[10], new Dimensions(Double.parseDouble(chops[11]), Double.parseDouble(chops[12]), Double.parseDouble(chops[13])));
    }
}
