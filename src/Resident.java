public class Resident
{
    private int residentID;
    private int personID;
    private int apartmentID;
    private String residentName;
    private String residentPhoneNum;
    private int paidFlag;
    private int flatId;

    public int getResidentID() {
        return residentID;
    }

    public void setResidentID(int residentID) {
        this.residentID = residentID;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public int getApartmentID() {
        return apartmentID;
    }

    public void setApartmentID(int apartmentID) {
        this.apartmentID = apartmentID;
    }

    public String getResidentName() {
        return residentName;
    }

    public void setResidentName(String residentName) {
        this.residentName = residentName;
    }

    public String getResidentPhoneNum() {
        return residentPhoneNum;
    }

    public void setResidentPhoneNum(String residentPhoneNum) {
        this.residentPhoneNum = residentPhoneNum;
    }

    public int getPaidFlag() {
        return paidFlag;
    }

    public void setPaidFlag(int paidFlag) {
        this.paidFlag = paidFlag;
    }

    public int getFlatId() {
        return flatId;
    }

    public void setFlatId(int flatId) {
        this.flatId = flatId;
    }

    public Resident(int personID, int apartmentID, String residentName, String residentPhoneNum, int paidFlag, int flatId) {
        this.personID = personID;
        this.apartmentID = apartmentID;
        this.residentName = residentName;
        this.residentPhoneNum = residentPhoneNum;
        this.paidFlag = paidFlag;
        this.flatId = flatId;
    }

    public Object getByName(String attributeName) {
        return switch (attributeName) {
            case "residentID" -> residentID;
            case "personID" -> personID;
            case "apartmentID" -> apartmentID;
            case "residentName" -> residentName;
            case "residentPhoneNum" -> residentPhoneNum;
            case "paidFlag" -> paidFlag;
            case "flatId" -> flatId;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return "Resident{" +
                "residentID=" + residentID +
                ", personID=" + personID +
                ", apartmentID=" + apartmentID +
                ", residentName='" + residentName + '\'' +
                ", residentPhoneNum='" + residentPhoneNum + '\'' +
                ", paidFlag=" + paidFlag +
                ", flatId=" + flatId +
                '}';
    }
}
