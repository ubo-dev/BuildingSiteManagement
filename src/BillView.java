import java.sql.ResultSet;
import java.text.ParseException;
import java.util.*;

public class BillView implements ViewInterface {
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
                Integer billID = resultSet.getInt("billID");
                String billDesc = resultSet.getString("billDesc");

                Integer subscriptionID = resultSet.getInt("subscriptionID");
                Float billAmount = resultSet.getFloat("billAmount");
                String billImage = resultSet.getString(("billImage"));


                // Display values
                System.out.print(billID + "\t");
                System.out.print(billDesc + "\t");
                System.out.print(subscriptionID + "\t");
                System.out.print(billAmount + "\t");
                System.out.println(billImage);

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
        Integer billID = getInteger("billID : ", true);
        String billDesc = getString("billDesc", true);
        Integer subscriptionID = getInteger("subscriptionID : ", true);
        Float billAmount = getFloat("billAmount : ", true);
        String billImage = getString("billImage", true);


        Map<String, Object> whereParameters = new HashMap<>();
        if (billID != null) whereParameters.put("billID", billID);
        if (billDesc != null) whereParameters.put("billDesc", billDesc);
        if (subscriptionID != null) whereParameters.put("subscriptionID", subscriptionID);
        if (billAmount != null) whereParameters.put("billAmount", billAmount);
        if (billImage != null) whereParameters.put("billImage", billImage);

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Bill", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "billDesc,subscriptionID,billAmount,billImage");

        List<Object> rows = new ArrayList<>();

        String billDesc,billImage;
        Float billAmount;
        Integer subscriptionID;
        do {
            System.out.println("Fields to insert:");

            billDesc = getString("billDesc", true);
            subscriptionID = getInteger("subscriptionID : ", true);
            billAmount = getFloat("billAmount : ", true);
            billImage = getString("billImage", true);

            System.out.println();

            if (billDesc != null && subscriptionID != null && billAmount != null && billImage != null) {

                rows.add(new Bill(billDesc, subscriptionID, billAmount, billImage));


            }
        }
        while (billDesc != null && subscriptionID != null && billAmount != null && billImage != null);

        parameters.put("rows", rows);

        return new ViewData("Bill", "insert", parameters);


    }
    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        String billDesc = getString("billDesc", true);
        Integer subscriptionID = getInteger("subscriptionID : ", true);
        Float billAmount = getFloat("billAmount : ", true);
        String billImage = getString("billImage", true);
       System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (billDesc != null) updateParameters.put("billDesc", billDesc);
        if (subscriptionID != null) updateParameters.put("subscriptionID", subscriptionID);
        if (billAmount != null) updateParameters.put("billAmount", billAmount);
        if (billImage != null) updateParameters.put("billImage", billImage);


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Bill", "update", parameters);
    }
    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Bill", "delete", parameters);
    }
    public String toString() {
        return "BillView";
    }

}