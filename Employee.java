public abstract class Employee {
    protected String rg;
	protected String name;
    protected Date birthdate;
	protected Date admissiondate;
	protected double salary;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

    public Date getAdmissiondate() {
		return admissiondate;
	}
	public void setAdmissiondate(Date admissiondate) {
		this.admissiondate = admissiondate;
	}

    public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}

	public Employee(String rg, String name, Date birthdate, Date admissionDate, double salary) {
		this.rg = rg;
		this.name = name;
		this.birthdate = birthdate;
		this.admissiondate = admissionDate;
		this.salary = salary;
	}

	public String show() {
		return("RG: " + this.rg + " Nome: " + this.name + " Data de nascimento: " + this.birthdate.show() + " Data de admissao: " + this.admissiondate.show() + " Salario: " + this.salary);
	}
}