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

    public String toString(){
		return (super.toString() + " Anos de experiencia: " + this.yearsOfExperience);
	}

	public String serialize() {
		return (this.getRg() + "; " + this.getName() + "; " + this.getBirthdate().serialize() + "; " + this.getAdmissiondate().serialize() + "; " + this.getSalary() + "; " + this.getPassword() + "; " + this.getYearsOfExperience());
	}

	public static Manager parse(String [] chops ){
		return new Manager(chops[0], chops[1], new Date(chops[2]), new Date(chops[3]), Double.parseDouble(chops[4]), chops[5], Integer.parseInt(chops[6]));
	}
}