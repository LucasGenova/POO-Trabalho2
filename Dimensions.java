public class Dimensions {
    private double heightM;
    private double widthM;
    private double lenghtM;
  
    public Dimensions(double heightM, double widthM, double lenghtM) {
        this.heightM = heightM;
        this.widthM = widthM;
        this.lenghtM = lenghtM;
    }

    public Dimensions(){
        this(0.0, 0.0, 0.0);
    }

    public double getHeightM() {
        return heightM;
    }
    public void setHeightM(double heightM) {
        this.heightM = heightM;
    }
    public double getWidthM() {
        return widthM;
    }
    public void setWidthM(double widthM) {
        this.widthM = widthM;
    }
    public double getLenghtM() {
        return lenghtM;
    }
    public void setLenghtM(double lenghtM) {
        this.lenghtM = lenghtM;
    }

    public String toString() {
        return("Altura: " + this.heightM + " Largura: " + this.widthM + " Comprimento: " + this.lenghtM);
    }

    public String serialize(){
        return(this.heightM + "; " + this.widthM + "; " + this.lenghtM);
    }

}
