public class Message {
    private int MessageID;
    private int ManagerID;
    private int ResidentID;
    String Message;
    StringBuilder SendDate;

    public Message(int managerID, int residentID, String message, StringBuilder sendDate) {
        ManagerID = managerID;
        ResidentID = residentID;
        Message = message;
        SendDate = sendDate;
    }

    public void setMessageID(int messageID) {
        MessageID = messageID;
    }

    public void setManagerID(int managerID) {
        ManagerID = managerID;
    }

    public void setResidentID(int residentID) {
        ResidentID = residentID;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public void setSendDate(StringBuilder sendDate) {
        SendDate = sendDate;
    }

    public Object getByName (String attributeName){
        return switch (attributeName){
            case "MessageID" -> MessageID;
            case "ManagerID" -> ManagerID;
            case "ResidentID" -> ResidentID;
            case "Message" -> Message;
            case "SendDate" -> SendDate;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return "Message{" +
                "MessageID=" + MessageID +
                ", ManagerID=" + ManagerID +
                ", ResidentID=" + ResidentID +
                ", Message='" + Message + '\'' +
                ", SendDate=" + SendDate +
                '}';
    }
}
