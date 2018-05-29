package JavaClasses;

public class Sector {

    private String id;
    private int remainingSpace;
    private int space;

    @Override
    public String toString() {
        return id;
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
}
