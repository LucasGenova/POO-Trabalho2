public class Sale {
    private int idSale;
	private Seller seller;
	private Client client;
	private Vehicle vehicle;
	private double price;
	private Date date;
	private Time hour;
	private String paymentMethod;

    public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

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

	public Sale(int idSale, Seller seller, Client client, Vehicle vehicle, double price, Date date, Time hour, String paymentMethod) {
		this.idSale = idSale;
		this.seller = seller;
		this.client = client;
		this.vehicle = vehicle;
		this.price = price;
		this.date = date;
		this.hour = hour;
		this.paymentMethod = paymentMethod;
	}

	public Sale() {
		this(0, null, null, null, 0, null, null, "");
	}

	public String toString() {
		return("ID: " + this.idSale + " Vendedor {" + this.seller.toString() + "} Cliente: " + this.client.toString() + " Veiculo {" + this.vehicle.toString() + "} Preco: " + this.price + " Data: " + this.date.toString() + " Hora: " + this.hour.toString());
	}

	public String serialize() {
		return (this.getIdSale() + "; " + this.getSeller().getRg() + "; " + this.getClient().getCpf() + "; " + this.getVehicle().getChassiNumber() + "; " + this.getPrice() + "; " + this.getDate().serialize() + "; " + this.getHour().toString());
	}

	public static Sale parse(String [] chops){
		return new Sale(Integer.parseInt(chops[0]), null, null, null,Double.parseDouble(chops[chops.length-4]), new Date(chops[chops.length-3]), new Time(chops[chops.length-2]), chops[chops.length-1]);
	}
}