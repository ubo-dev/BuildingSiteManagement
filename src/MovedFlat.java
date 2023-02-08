import java.util.Date;
public class MovedFlat {

    private int flatHistory;
    private int oldFlatID;
    private int newFlatID;
    private int apartmentID;
    private StringBuilder enteranceDate;
    private StringBuilder leavingDate;

    public MovedFlat(int oldFlatID, int newFlatID, int apartmentID, StringBuilder enteranceDate, StringBuilder leavingDate) {
        this.oldFlatID = oldFlatID;
        this.newFlatID = newFlatID;
        this.apartmentID = apartmentID;
        this.enteranceDate = enteranceDate;
        this.leavingDate = leavingDate;

    }

    public int getFlatHistory() {
        return flatHistory;
    }

    public void setFlatHistory(int flatHistory) {
        this.flatHistory = flatHistory;
    }

    public int getOldFlatID() {
        return oldFlatID;
    }

    public void setOldFlatID(int oldFlatID) {
        this.oldFlatID = oldFlatID;
    }

    public int getNewFlatID() {
        return newFlatID;
    }

    public void setNewFlatID(int newFlatID) {
        this.newFlatID = newFlatID;
    }

    public int getApartmentID() {
        return apartmentID;
    }

    public void setApartmentID(int apartmentID) {
        this.apartmentID = apartmentID;
    }

    public StringBuilder getEnteranceDate() {
        return enteranceDate;
    }

    public void setEnteranceDate(StringBuilder enteranceDate) {
        this.enteranceDate = enteranceDate;
    }

    public StringBuilder getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(StringBuilder leavingDate) {
        this.leavingDate = leavingDate;
    }

    public Object getByName (String attributeName){
        return switch (attributeName){
            case "flatHistory" -> flatHistory;
            case "oldFlatID" -> oldFlatID;
            case "newFlatID" -> newFlatID;
            case "apartmentID" -> apartmentID;
            case "enteranceDate" -> enteranceDate;
            case "leavingDate" -> leavingDate;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return "MovedFlat{" +
                "flatHistory=" + flatHistory +
                ", oldFlatID=" + oldFlatID +
                ", newFlatID=" + newFlatID +
                ", apartmentID=" + apartmentID +
                ", enteranceDate=" + enteranceDate +
                ", leavingDate=" + leavingDate +
                '}';
    }
}
