public class AssistantManager
{
    private int assistantManagerID;
    private int personID;
    private int managerID;
    private int residentID;
    private String assistantManagerName;

    public AssistantManager(int personID, int managerID, int residentID, String assistantManagerName) {
        this.personID = personID;
        this.managerID = managerID;
        this.residentID = residentID;
        this.assistantManagerName = assistantManagerName;
    }

    public Object getByName(String attributeName) {
        return switch (attributeName) {
            case "assistantManagerID" -> assistantManagerID;
            case "personID" -> personID;
            case "managerID" -> managerID;
            case "residentID" -> residentID;
            case "assistantManagerName" -> assistantManagerName;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return "AsistantManager{" +
                "assistantManagerID=" + assistantManagerID +
                ", personID=" + personID +
                ", managerID=" + managerID +
                ", residentID=" + residentID +
                ", assistantManagerName='" + assistantManagerName + '\'' +
                '}';
    }
}
