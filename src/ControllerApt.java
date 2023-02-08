public class ControllerApt
{
    private int controllerID;
    private int personID;
    private int residentID;
    private String controllerName;

    public ControllerApt(int personID, int residentID, String controllerName) {
        this.personID = personID;
        this.residentID = residentID;
        this.controllerName = controllerName;
    }

    public Object getByName(String attributeName) {
        return switch (attributeName) {
            case "controllerID" -> controllerID;
            case "personID" -> personID;
            case "residentID" -> residentID;
            case "controllerName" -> controllerName;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return "ControllerApt{" +
                "controllerID=" + controllerID +
                ", personID=" + personID +
                ", residentID=" + residentID +
                ", controllerName='" + controllerName + '\'' +
                '}';
    }
}
