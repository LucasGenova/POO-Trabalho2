import java.util.Scanner;

public class Date {
    private int day;
	private int month;
    private int year;
    Scanner sc = new Scanner(System.in);

    public int getYear() {
		return year;
	}

	public void setYear(int year) {
		while(year<1950 || year>2050) {
            System.out.println("Digite um valor válido: \n");
            year = sc.nextInt();
            sc.nextLine();
        }

        this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		while(month<1 || month>12) {
            System.out.println("Digite um valor válido: \n");
            month = sc.nextInt();
            sc.nextLine();
        }
        
        this.month = month;
	}

    public int getDay() {
		return day;
	}

	public void setDay(int day) {
		while(day<1 || day>31) {
            System.out.println("Digite um valor válido: \n");
            day = sc.nextInt();
            sc.nextLine();
        }
        
        this.day = day;
	}

    public Date(int day, int month, int year) {
        setDay(day);
        setMonth(month);
        setYear(year);
    }

	public Date(){
		this(1, 1, 2000);
	}

    public Date(String date){
        this(Integer.parseInt(date.split("-")[0]), Integer.parseInt(date.split("-")[1]), Integer.parseInt(date.split("-")[2]));
    }

	public String toString(){
		return(this.day + "-" + this.month + "-" + this.year);
	}

    public String serialize(){
        return(this.day + "-" + this.month + "-" + this.year);
    }
}