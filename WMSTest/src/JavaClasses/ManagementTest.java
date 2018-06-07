package JavaClasses;

import org.junit.Assert;
import org.junit.Test;

public class ManagementTest {

    private Management management = Management.getInstance();

    @Test
    public void connectToTheServer() {
        Assert.assertEquals(management.connectToTheServer("WMS", "localhost", "1433", "SA", "Patapon0"),
                "Success");
    }

    @Test
    public void getProduct() {
        connectToTheServer();
        Assert.assertEquals(management.getProduct("Lenovo").get(0).getId(), "1");
    }

    @Test
    public void getProvider() {
        connectToTheServer();
        Assert.assertEquals(management.getProvider("0012234401").get(0).getName(), "PolGroup");
    }

    @Test
    public void getClient() {
        connectToTheServer();
        Assert.assertEquals(management.getClient("Acapulco Polska").get(0).getNip(), "2930582403");
    }

    @Test
    public void getProductCard() {
        connectToTheServer();
        Assert.assertEquals(management.getProductCard("Lenovo").get(11).getPlaceId(), "2");
    }

    @Test
    public void getSectors() {
        connectToTheServer();
        Assert.assertEquals(management.getSectors().get(0).toString(), "1");
    }

    @Test
    public void getRack() {
        connectToTheServer();
        Assert.assertEquals(management.getRack(new Sector("1")).get(0).getId(), "1");
    }

    @Test
    public void getRackType() {
        connectToTheServer();
        Assert.assertEquals(management.getRackType().get(0).getSpace(), 10);
    }

    @Test
    public void getShelves() {
        connectToTheServer();
        Assert.assertEquals(management.getShelves(new Rack("1")).get(0).getRemainingSpace(), 0);
    }

    @Test
    public void getAvailability() {
        connectToTheServer();
        Assert.assertEquals(management.getAvailability(new Sector("1")), "0/200 (0.0%)");
        Assert.assertEquals(management.getAvailability(new Rack("5")), "50/50 (100.0%)");
        Assert.assertEquals(management.getAvailability(new Shelf("10")), "0/10 (0.0%)");
    }

    @Test
    public void getProductOnShelf() {
        connectToTheServer();
        Assert.assertEquals(management.getProductOnShelf("1").get(0).getProductId(), 1);
    }

}