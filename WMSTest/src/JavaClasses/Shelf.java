package JavaClasses;

public class Shelf {
    private String id;
    private int rackId;
    private int remainingSpace;
    private int space;

    public Shelf() {
    }

    public Shelf(String id) {
        this.id = id;
    }

    public Shelf(String id, int rackId, int remainingSpace, int space) {
        this.id = id;
        this.rackId = rackId;
        this.remainingSpace = remainingSpace;
        this.space = space;
    }

    public String toString() {
        return String.valueOf(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRemainingSpace() {
        return remainingSpace;
    }

    public void setRemainingSpace(int remainingSpace) {
        this.remainingSpace = remainingSpace;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public int getRackId() {
        return rackId;
    }

    public void setRackId(int rackId) {
        this.rackId = rackId;
    }
}
