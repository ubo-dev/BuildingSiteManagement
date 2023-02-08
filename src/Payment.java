public class Payment {

        private int PaymentId;
        private int ResidentId;
        private int ReceiptId;


    public int getPaymentId() {
        return PaymentId;
    }

    public void setPaymentId(int paymentId) {
        PaymentId = paymentId;
    }

    public int getResidentId() {
        return ResidentId;
    }

    public void setResidentId(int residentId) {
        ResidentId = residentId;
    }

    public int getReceiptId() {
        return ReceiptId;
    }

    public void setReceiptId(int receiptId) {
        ReceiptId = receiptId;
    }

    public Payment(int paymentId, int residentId, int receiptId) {
        PaymentId = paymentId;
        ResidentId = residentId;
        ReceiptId = receiptId;
    }

    public Payment(int residentId, int receiptId) {
        ResidentId = residentId;
        ReceiptId = receiptId;
    }

    public Object getByName(String attributeName){
            return switch(attributeName){
                case "PaymentId" -> PaymentId;
                case "ResidentId" -> ResidentId;
                case "ReceiptId" -> ReceiptId;
                default -> null;
            };
    }

    @Override
    public String toString() {
        return "Payment{" +
                "PaymentId=" + PaymentId +
                ", ResidentId=" + ResidentId +
                ", ReceiptId=" + ReceiptId +
                '}';
    }
}


