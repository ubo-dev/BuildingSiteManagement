public class Person
{
    private int personID;
    private int apartmentID;
    private String personName;
    private int personStatus;

    Person() {}

    public Person(int personID, int apartmentID, String personName, int personStatus) {
        this.personID = personID;
        this.apartmentID = apartmentID;
        this.personName = personName;
        this.personStatus = personStatus;
    }

    public Person(int apartmentID, String personName, int personStatus) {
        this.apartmentID = apartmentID;
        this.personName = personName;
        this.personStatus = personStatus;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(short personID) {
        this.personID = personID;
    }

    public int getApartmentID() {
        return apartmentID;
    }

    public void setApartmentID(short apartmentID) {
        this.apartmentID = apartmentID;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getPersonStatus() {
        return personStatus;
    }

    public void setPersonStatus(short personStatus) {
        this.personStatus = personStatus;
    }

    public Object getByName(String attributeName) {
        return switch (attributeName) {
            case "personID" -> personID;
            case "apartmentID" -> apartmentID;
            case "personName" -> personName;
            case "personStatus" -> personStatus;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return "Person{" +
                "personID=" + personID +
                ", apartmentID=" + apartmentID +
                ", personName='" + personName + '\'' +
                ", personStatus=" + personStatus +
                '}';
    }



}
