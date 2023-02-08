public class Subscription {
    private int SubscriptionID;
    private String SubscriptionType;
    private int managerID;


    public int getSubscriptionID() {
        return SubscriptionID;
    }

    public void setSubscriptionID(int subscriptionID) {
        SubscriptionID = subscriptionID;
    }

    public String getSubscriptionType() {
        return SubscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        SubscriptionType = subscriptionType;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }


    public Subscription(int subscriptionID, String subscriptionType, int managerID) {
        this.SubscriptionID = subscriptionID;
        this.SubscriptionType = subscriptionType;
        this.managerID = managerID;

    }

    public Subscription(String subscriptionType, int managerID) {
        this.SubscriptionType = subscriptionType;
        this.managerID = managerID;

    }

    public Object getByName(String attributeName){
        return switch(attributeName){
            case "SubscriptionID" -> SubscriptionID;
            case "SubscriptionType" -> SubscriptionType;
            case "managerID" -> managerID;

            default -> null;
        };
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "SubscriptionID=" + SubscriptionID +
                ", SubscriptionType='" + SubscriptionType + '\'' +
                ", managerID=" + managerID +
                '}';
    }
}
