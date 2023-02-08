public class Manager
{
    private int managerID;
    private int personID;
    private int apartmentID;
    private int residentID;
    private String managerName;

    public Manager(int personID, int apartmentID, int residentID, String managerName) {
        this.personID = personID;
        this.apartmentID = apartmentID;
        this.residentID = residentID;
        this.managerName = managerName;
    }

    public Manager(int managerID, int personID, int apartmentID, int residentID, String managerName) {
        this.managerID = managerID;
        this.personID = personID;
        this.apartmentID = apartmentID;
        this.residentID = residentID;
        this.managerName = managerName;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
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

    public int getResidentID() {
        return residentID;
    }

    public void setResidentID(int residentID) {
        this.residentID = residentID;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Object getByName(String attributeName) {
        return switch (attributeName) {
            case "managerID" -> managerID;
            case "personID" -> personID;
            case "apartmentID" -> apartmentID;
            case "residentID" -> residentID;
            case "managerName" -> managerName;
            default -> null;
        };
    }


    @Override
    public String toString() {
        return "Manager{" +
                "managerID=" + managerID +
                ", personID=" + personID +
                ", apartmentID=" + apartmentID +
                ", residentID=" + residentID +
                ", managerName='" + managerName + '\'' +
                '}';
    }
}
