public class Address {
    private String street;
    private String district;
    private String city;

    public Address(String street, String district, String city) {
        this.street = street;
        this.district = district;
        this.city = city;
    }
    
    public Address() {
        this("", "", "");
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String toString() {
        return("Rua: " + this.street + " Bairro: " + this.district + " Cidade: " + this.city);
    }

    public String serialize(){
        return(this.street + "; " + this.district + "; " + this.city);
    }
}
