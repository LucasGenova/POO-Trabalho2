public class Time {
    private int hour;
    private int min;
    
    public Time(int hour, int min) {
        this.hour = hour;
        this.min = min;
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

    public static boolean validateHour(int hour){
        return (hour >= 0) && (hour <= 23);
    }

    public static boolean validateMin(int hour){
        return (hour >= 0) && (hour <= 23);
    }

    public String show(){
        return(this.hour + ":" + this.min);
    }
}
