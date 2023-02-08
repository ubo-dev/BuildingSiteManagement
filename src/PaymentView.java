import java.sql.ResultSet;
import java.text.ParseException;
import java.util.*;

public class PaymentView implements ViewInterface {

    public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
        return switch (operationName) {
            case "select" -> selectOperation(modelData);
            case "insert" -> insertOperation(modelData);
            case "update" -> updateOperation(modelData);
            case "delete" -> deleteOperation(modelData);
            case "select.gui" -> selectGUI(modelData);
            case "insert.gui" -> insertGUI(modelData);
            case "update.gui" -> updateGUI(modelData);
            case "delete.gui" -> deleteGUI(modelData);
            default -> new ViewData("MainMenu", "");
        };
    }
    ViewData selectOperation(ModelData modelData) throws Exception {
        ResultSet resultSet = modelData.resultSet;

        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                Integer PaymentId = resultSet.getInt("PaymentId");
                Integer ResidentId = resultSet.getInt("ResidentId");
                Integer ReceiptId = resultSet.getInt("ReceiptId");


                // Display values
                System.out.print(PaymentId + "\t");
                System.out.print(ResidentId + "\t");
                System.out.println(ReceiptId);

            }
            resultSet.close();
        }

        return new ViewData("MainMenu", "");
    }

    ViewData insertOperation(ModelData modelData) throws Exception {
        System.out.println("Number of inserted rows is " + modelData.recordCount);

        return new ViewData("MainMenu", "");
    }

    ViewData updateOperation(ModelData modelData) throws Exception {
        System.out.println("Number of updated rows is " + modelData.recordCount);

        return new ViewData("MainMenu", "");
    }

    ViewData deleteOperation(ModelData modelData) throws Exception {
        System.out.println("Number of deleted rows is " + modelData.recordCount);

        return new ViewData("MainMenu", "");
    }
    Map<String, Object> getWhereParameters() throws Exception {
        System.out.println("Filter conditions:");
        Integer PaymentId = getInteger("PaymentId : ", true);
        Integer ResidentId = getInteger("ResidentId : ", true);
        Integer ReceiptId = getInteger("ReceiptId : ", true);


        Map<String, Object> whereParameters = new HashMap<>();
        if (PaymentId != null) whereParameters.put("PaymentId", PaymentId);
        if (ResidentId != null) whereParameters.put("ResidentId", ResidentId);
        if (ReceiptId != null) whereParameters.put("ReceiptId", ReceiptId);

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Payment", "select", parameters);
    }
    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "ResidentId,ReceiptId");

        List<Object> rows = new ArrayList<>();

        Integer ResidentId, ReceiptId;

        do {
            System.out.println("Fields to insert:");

            ResidentId = getInteger("ResidentId  : ", true);
            ReceiptId = getInteger("ReceiptId : ", true);
            System.out.println();

            if (ResidentId != null && ReceiptId != null) {

                rows.add(new Payment(ResidentId, ReceiptId));


            }
        }
        while (ResidentId != null && ReceiptId != null);

        parameters.put("rows", rows);

        return new ViewData("Payment", "insert", parameters);
    }
    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        Integer ResidentId = getInteger("ResidentId  : ", true);
        Integer ReceiptId = getInteger("ReceiptId : ", true);
        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (ResidentId != null) updateParameters.put("ResidentId", ResidentId);
        if (ReceiptId != null) updateParameters.put("ReceiptId", ReceiptId);


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Payment", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Payment", "delete", parameters);
    }
    public String toString() {
        return "PaymentView";
    }
}
