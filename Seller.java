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

    public Seller(String rg, String name, Date birthdate, Date admissionDate, double salary, double remainingTrainingTime, Manager responsibleManager) {
        super(rg, name, birthdate, admissionDate, salary);
        this.remainingTrainingTime = remainingTrainingTime;
        this.responsibleManager = responsibleManager;
    }

    public Seller() {
        this("", "", null, null, 0, 0, null);
    }

	public String show() {
		return(super.show() + " Tempo de treinamento restante: " + this.remainingTrainingTime + " Gerente responsável {" + this.responsibleManager.show() + "}");
	}
}