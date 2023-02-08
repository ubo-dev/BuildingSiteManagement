import javax.swing.text.View;
import java.sql.ResultSet;
import java.util.*;
import java.util.Date;
public class ExpenseView implements ViewInterface {

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
                Integer ExpenseId = resultSet.getInt("ExpenseId");
                Integer ReceiptId = resultSet.getInt("ReceiptId");
                Integer ControllerId = resultSet.getInt("ControllerId");


                // Display values
                System.out.print(ExpenseId + "\t");
                System.out.print(ReceiptId + "\t");
                System.out.println(ControllerId);

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
        Integer ExpenseId = getInteger("ExpenseId : ", true);
        Integer ReceiptId = getInteger("ReceiptId : ", true);
        Integer ControllerId = getInteger("ControllerId : ", true);


        Map<String, Object> whereParameters = new HashMap<>();
        if (ExpenseId != null) whereParameters.put("ExpenseId", ExpenseId);
        if (ReceiptId != null) whereParameters.put("ReceiptId", ReceiptId);
        if (ControllerId != null) whereParameters.put("ControllerId", ControllerId);

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Expense", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "ReceiptId,ControllerId");

        List<Object> rows = new ArrayList<>();

        Integer ReceiptId, ControllerId;

        do {
            System.out.println("Fields to insert:");

            ReceiptId = getInteger("ReceiptId  : ", true);
            ControllerId = getInteger("ControllerId : ", true);
            System.out.println();

            if (ReceiptId != null && ControllerId != null) {

                rows.add(new Expense(ReceiptId, ControllerId));


            }
        }
        while (ReceiptId != null && ControllerId != null);

        parameters.put("rows", rows);

        return new ViewData("Expense", "insert", parameters);
    }
    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        Integer ReceiptId = getInteger("ReceiptId  : ", true);
        Integer ControllerId = getInteger("ControllerId : ", true);
        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (ReceiptId != null) updateParameters.put("ReceiptId", ReceiptId);
        if (ControllerId != null) updateParameters.put("ControllerId", ControllerId);


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Expense", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Expense", "delete", parameters);
    }
    public String toString() {
        return "ExpenseView";
    }

}
