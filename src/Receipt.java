import java.util.Date;

public class Receipt {
    private int ReceiptID;
    private String ReceiptDescription;
    private int ResidentID;
    private float ReceiptAmount;
    private StringBuilder ReceiptTime;


    public Receipt(int receiptID, String receiptDescription, int residentID, float receiptAmount, StringBuilder receiptTime) {
        this.ReceiptID = receiptID;

        this.ReceiptDescription = receiptDescription;
        this.ResidentID = residentID;
        this.ReceiptAmount = receiptAmount;
        this.ReceiptTime = receiptTime;
    }

    public Receipt(String receiptDescription, int residentID, float receiptAmount, StringBuilder receiptTime) {
        this.ReceiptDescription = receiptDescription;
        this.ResidentID = residentID;
        this.ReceiptAmount = receiptAmount;
        this.ReceiptTime = receiptTime;
    }


    public Receipt(String receiptDescription, int residentID, float receiptAmount) {
        this.ReceiptDescription = receiptDescription;
        this.ResidentID = residentID;
        this.ReceiptAmount = receiptAmount;
    }

    public int getReceiptID() {
        return ReceiptID;
    }

    public void setReceiptID(int receiptID) {
        ReceiptID = receiptID;
    }

    public String getReceiptDescription() {
        return ReceiptDescription;
    }

    public void setReceiptDescription(String receiptDescription) {
        ReceiptDescription = receiptDescription;
    }

    public int getResidentID() {
        return ResidentID;
    }

    public void setResidentID(int residentID) {
        ResidentID = residentID;
    }

    public float getReceiptAmount() {
        return ReceiptAmount;
    }

    public void setReceiptAmount(float receiptAmount) {
        ReceiptAmount = receiptAmount;
    }

    public StringBuilder getReceiptTime() {
        return ReceiptTime;
    }

    public void setReceiptTime(StringBuilder receiptTime) {
        ReceiptTime = receiptTime;
    }



    public Object getByName(String attributeName) {
        return switch (attributeName) {
            case "ReceiptID" -> ReceiptID;
            case "ReceiptDescription" -> ReceiptDescription;
            case "ResidentID" -> ResidentID;
            case "ReceiptAmount" -> ReceiptAmount;
            case "ReceiptTime" -> ReceiptTime;

            default -> null;
        };
    }

    @Override
    public String toString() {
        return ReceiptID+", "+ReceiptDescription+ ", " +ResidentID+", "+ ReceiptAmount+", "+ ReceiptTime;
    }
}
