public class Sale {
    private int idSale;
	private Seller seller;
	private Client client;
	private Vehicle vehicle;
	private double price;
	private Date date;
	private Time hour;

    public Time getHour() {
		return hour;
	}

	public void setHour(Time hour) {
		this.hour = hour;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

    public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

    public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

    public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

    public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

    public int getIdSale() {
		return idSale;
	}

	public void setIdSale(int idSale) {
		this.idSale = idSale;
	}

	public String show() {
		return("ID: " + this.idSale + " Vendedor {" + this.seller.show() + "} Cliente: " + this.client.show() + " Veiculo {" + this.vehicle.show() + "} Preco: " + this.price + " Data: " + this.date.show() + " Hora: " + this.hour.show());
	}
}