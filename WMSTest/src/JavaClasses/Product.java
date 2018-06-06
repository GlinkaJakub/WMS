package JavaClasses;

public class Product {

    private String name;
    private String id;
    private String maker;
    private int width;
    private int height;
    private int length;
    private int weight;
    private String group;

    public Product(String name, String id, String maker, int width, int height, int length, int weight, String group) {
        this.name = name;
        this.id = id;
        this.maker = maker;
        this.width = width;
        this.height = height;
        this.length = length;
        this.weight = weight;
        this.group = group;
    }

    public Product() {
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
