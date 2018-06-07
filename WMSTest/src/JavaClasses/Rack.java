package JavaClasses;

public class Rack {
    private String id;
    private int sectorId;
    private int rackTypeId;
    private int remainingSpace;
    private int totalSpace;

    public Rack() {
    }

    public Rack(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Rack Type ID:" + rackTypeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSectorId() {
        return sectorId;
    }

    public void setSectorId(int sectorId) {
        this.sectorId = sectorId;
    }

    public int getRackTypeId() {
        return rackTypeId;
    }

    public void setRackTypeId(int rackTypeId) {
        this.rackTypeId = rackTypeId;
    }

    public int getRemainingSpace() {
        return remainingSpace;
    }

    public void setRemainingSpace(int remainingSpace) {
        this.remainingSpace = remainingSpace;
    }

    public int getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(int totalSpace) {
        this.totalSpace = totalSpace;
    }
}
