public class Expense {
    private int ExpenseId;
    private int ReceiptId;
    private int ControllerId;

    public Expense(int expenseId, int receiptId, int controllerId) {
        this.ExpenseId = expenseId;
        this.ReceiptId = receiptId;
        this.ControllerId = controllerId;
    }

    public Expense(Integer receiptId, Integer controllerId) {
        this.ReceiptId=receiptId;
        this.ControllerId=controllerId;
    }

    public int getExpenseId() {
        return ExpenseId;
    }

    public void setExpenseId(int expenseId) {
        ExpenseId = expenseId;
    }

    public int getReceiptId() {
        return ReceiptId;
    }

    public void setReceiptId(int receiptId) {
        ReceiptId = receiptId;
    }

    public int getControllerId() {
        return ControllerId;
    }

    public void setControllerId(int controllerId) {
        ControllerId = controllerId;
    }
    public Object getByName(String attributeName){
        return switch(attributeName){
            case "ExpenseId" -> ExpenseId;
            case "ReceiptId" -> ReceiptId;
            case "ControllerId" -> ControllerId;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return "Expense{" +
                "ExpenseId=" + ExpenseId +
                ", ReceiptId=" + ReceiptId +
                ", ControllerId=" + ControllerId +
                '}';
    }
}
