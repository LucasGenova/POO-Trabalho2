public class Time {
    private int hour;
    private int min;
    
    public Time(int hour, int min) {
        this.hour = hour;
        this.min = min;
    }

    public Time(String read){
        this(Integer.parseInt(read.split(":")[0]), Integer.parseInt(read.split(":")[1]));
    }

    public Time() {
        this(0, 0);
    }

    public int getHour() {
        return hour;
    }
    public void setHour(int hour) {
        this.hour = hour;
    }
    public int getMin() {
        return min;
    }
    public void setMin(int min) {
        this.min = min;
    }

    public boolean isValid(){
        return ((this.hour>=0 && this.hour<=23) && (this.min>=0 && this.min<=59));
    }

    public String toString(){
        return(this.hour + ":" + this.min);
    }
}
