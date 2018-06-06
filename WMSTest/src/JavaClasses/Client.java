package JavaClasses;

public class Client extends Contractor {

    public Client(String name, String nip, String phone, String street, String buildingNumber, String postCode, String city, String email) {
        super(name, nip, phone, street, buildingNumber, postCode, city, email);
    }

    public Client() {
        super();
    }
}
