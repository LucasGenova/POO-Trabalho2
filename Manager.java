public class Manager extends Employee {
    private int yearsOfExperience;

	public int getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

    public Manager(String rg, String name, Date birthdate, Date admissionDate, double salary, int yearsOfExperience) {
        super(rg, name, birthdate, admissionDate, salary);
        this.yearsOfExperience = yearsOfExperience;
    }

	public String show(){
		return (super.show() + " Anos de experiencia: " + this.yearsOfExperience);
	}
}