public class Manager extends Employee {
    private int yearsOfExperience;

	public int getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public Manager(String rg, String name, Date birthdate, Date admissiondate, double salary, String password,
            int yearsOfExperience) {
        super(rg, name, birthdate, admissiondate, salary, password);
        this.yearsOfExperience = yearsOfExperience;
    }

	public Manager() {
		this("", "", null, null, 0, "", 0);
	}

    public String show(){
		return (super.show() + " Anos de experiencia: " + this.yearsOfExperience);
	}
}