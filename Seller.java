public class Seller extends Employee {
    private double remainingTrainingTime;
	private Manager responsibleManager;

    public Manager getResponsibleManager() {
		return responsibleManager;
	}

	public void setResponsibleManager(Manager responsibleManager) {
		this.responsibleManager = responsibleManager;
	}

	public double getRemainingTrainingTime() {
		return remainingTrainingTime;
	}

	public void setRemainingTrainingTime(double remainingTrainingTime) {
		this.remainingTrainingTime = remainingTrainingTime;
	}

    public Seller(String rg, String name, Date birthdate, Date admissiondate, double salary, String password,
			double remainingTrainingTime, Manager responsibleManager) {
		super(rg, name, birthdate, admissiondate, salary, password);
		this.remainingTrainingTime = remainingTrainingTime;
		this.responsibleManager = responsibleManager;
	}

	public Seller() {
        this("", "", null, null, 0, null, 0, null);
    }

	public String toString() {
		return(super.toString() + " Tempo de treinamento restante: " + this.remainingTrainingTime + " Gerente responsável {" + this.responsibleManager.toString() + "}");
	}

	public String serialize() {
		return (this.getRg() + "; " + this.getName() + "; " + this.getBirthdate().serialize() + "; " + this.getAdmissiondate().serialize() + "; " + this.getSalary() + "; " + this.getPassword() + "; " + this.getRemainingTrainingTime() + "; " + this.getResponsibleManager().getRg());
	}

	public static Seller parse(String [] chops){
		return new Seller(chops[0], chops[1], new Date(chops[3]), new Date(chops[4]), Double.parseDouble(chops[5]), chops[6], Double.parseDouble(chops[7]), null);
	}
}