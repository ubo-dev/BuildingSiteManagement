import java.util.Date;

public class Decisions {
    private int DecisionID;
    private int ApartmentID;
    private String DecisionDescription;
    private int VoteYes;
    private int VoteNo;
    private StringBuilder DecisionDate;
    private String isAccepted;
    private String isFullOrMajority;
    Decisions(){}

    public Decisions(int apartmentID, String decisionDescription, int voteYes, int voteNo, StringBuilder decisionDate, String isAccepted, String isFullOrMajority) {
        ApartmentID = apartmentID;
        DecisionDescription = decisionDescription;
        VoteYes = voteYes;
        VoteNo = voteNo;
        DecisionDate = decisionDate;
        this.isAccepted = isAccepted;
        this.isFullOrMajority = isFullOrMajority;
    }

    public int getDecisionID() {
        return DecisionID;
    }

    public void setDecisionID(int decisionID) {
        DecisionID = decisionID;
    }

    public int getApartmentID() {
        return ApartmentID;
    }

    public void setApartmentID(int apartmentID) {
        ApartmentID = apartmentID;
    }

    public String getDecisionDescription() {
        return DecisionDescription;
    }

    public void setDecisionDescription(String decisionDescription) {
        DecisionDescription = decisionDescription;
    }

    public int getVoteYes() {
        return VoteYes;
    }

    public void setVoteYes(int voteYes) {
        VoteYes = voteYes;
    }

    public int getVoteNo() {
        return VoteNo;
    }

    public void setVoteNo(int voteNo) {
        VoteNo = voteNo;
    }

    public String getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(String isAccepted) {
        this.isAccepted = isAccepted;
    }

    public StringBuilder getDecisionDate() {
        return DecisionDate;
    }

    public void setDecisionDate(StringBuilder decisionDate) {
        DecisionDate = decisionDate;
    }

    public String getIsFullOrMajority() {
        return isFullOrMajority;
    }

    public void setIsFullOrMajority(String isFullOrMajority) {
        this.isFullOrMajority = isFullOrMajority;
    }

    public Object getByName (String attributeName){
        return switch (attributeName){
            case "DecisionID" -> DecisionID;
            case "ApartmentID" -> ApartmentID;
            case "DecisionDescription" -> DecisionDescription;
            case "VoteYes" -> VoteYes;
            case "VoteNo" -> VoteNo;
            case "DecisionDate" -> DecisionDate;
            case "isAccepted" -> isAccepted;
            case "isFullOrMajority" -> isFullOrMajority;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return "Decisions{" +
                "DecisionID=" + DecisionID +
                ", ApartmentID=" + ApartmentID +
                ", DecisionDescription='" + DecisionDescription + '\'' +
                ", VoteYes=" + VoteYes +
                ", VoteNo=" + VoteNo +
                ", DecisionDate=" + DecisionDate +
                ", isAccepted='" + isAccepted + '\'' +
                ", isFullOrMajority='" + isFullOrMajority + '\'' +
                '}';
    }
}
