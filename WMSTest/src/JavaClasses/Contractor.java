package JavaClasses;

public class Contractor {
    private String name;
    private String nip;
    private String phone;
    private String street;
    private String buildingNumber;
    private String postCode;
    private String city;
    private String email;

    public Contractor(String name, String nip, String phone, String street, String buildingNumber, String postCode, String city, String email) {
        this.name = name;
        this.nip = nip;
        this.phone = phone;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.postCode = postCode;
        this.city = city;
        this.email = email;
    }

    public Contractor() {

    }

    @Override
    public String toString() {
        return name + ", NIP: " + nip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
