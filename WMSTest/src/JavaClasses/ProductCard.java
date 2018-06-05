package JavaClasses;

public class ProductCard {

    private int id;
    private int productId;
    private String placeId;
    private String name;

    public ProductCard(int id, int productId, String placeId, String name) {
        this.id = id;
        this.productId = productId;
        this.placeId = placeId;
        this.name = name;
    }

    public ProductCard() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
    @Override
    public String toString(){
        return name + "#" + id + " " + placeId;
    }
}
