import java.sql.ResultSet;
import java.text.ParseException;
import java.util.*;

public class ReceiptView implements ViewInterface{


    ViewData selectOperation(ModelData modelData) throws Exception {
        ResultSet resultSet = modelData.resultSet;

        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                Integer ReceiptID = resultSet.getInt("ReceiptID");
                String ReceiptDescription = resultSet.getString("ReceiptDescription");
                Integer ResidentID = resultSet.getInt("ResidentID");
                Float ReceiptAmount = resultSet.getFloat("ReceiptAmount");
                Date ReceiptTime = resultSet.getDate("ReceiptTime");

                // Display values
                System.out.print(ReceiptID+ "\t");
                System.out.print(ReceiptDescription + "\t");
                System.out.print(ResidentID + "\t");
                System.out.print(ReceiptAmount+ "\t");
                System.out.println(ReceiptTime + "\t");
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
        Integer ReceiptID = getInteger("ReceiptID: ", true);
        String ReceiptDescription = getString("ReceiptDescription : ", true);
        Integer ResidentID = getInteger("ResidentID : ", true);
        Float ReceiptAmount = getFloat("ReceiptAmount : ", true);
        StringBuilder  ReceiptTime = getDate("ReceiptTime : ", true);



        Map<String, Object> whereParameters = new HashMap<>();
        if (ReceiptID != null) whereParameters.put("ReceiptID", ReceiptID);
        if (ReceiptDescription != null) whereParameters.put("ReceiptDescription", ReceiptDescription);
        if (ResidentID != null) whereParameters.put("ResidentID", ResidentID);
        if (ReceiptAmount != null) whereParameters.put("ReceiptAmount", ReceiptAmount);
        if (ReceiptTime != null) whereParameters.put("ReceiptTime", ReceiptTime);

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Receipt", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "ReceiptDescription, ResidentID, ReceiptAmount, ReceiptTime");

        List<Object> rows = new ArrayList<>();

        Integer ResidentID,isExpense;
        String ReceiptDescription;
        Float ReceiptAmount;
        StringBuilder ReceiptTime;
        do
        {
            System.out.println("Fields to insert:");
            ReceiptDescription = getString("Receipt Description : ", true);
            ResidentID = getInteger("Resident ID : ",true);
            ReceiptAmount = getFloat("Receipt Amount : ", true);
            ReceiptTime = getDate("Receipt Time : ",true);
            System.out.println();

            if (ReceiptDescription != null && ResidentID != null && ReceiptAmount != null) {
                rows.add(new Receipt(ReceiptDescription,ResidentID, ReceiptAmount,ReceiptTime));
            }
        }
        while (ReceiptDescription != null && ResidentID != null && ReceiptAmount != null);

        parameters.put("rows", rows);

        return new ViewData("Receipt", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        String ReceiptDescription = getString("ReceiptDescription : ", true);
        Integer ResidentID = getInteger("ResidentID : ", true);
        Float ReceiptAmount = getFloat("ReceiptAmount : ", true);
        StringBuilder  ReceiptTime = getDate("ReceiptTime : ", true);
        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (ReceiptDescription != null) updateParameters.put("ReceiptDescription", ReceiptDescription);
        if (ResidentID != null) updateParameters.put("ResidentID", ResidentID);
        if (ReceiptAmount != null) updateParameters.put("ReceiptAmount", ReceiptAmount);
        if (ReceiptTime != null) updateParameters.put("ReceiptTime", ReceiptTime);


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Receipt", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Receipt", "delete", parameters);
    }

    @Override
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
    public String toString() {
        return "ReceiptView";
    }
}
