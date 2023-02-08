import java.sql.ResultSet;
import java.util.*;
public class SubscriptionView implements ViewInterface{
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
                Integer SubscriptionID = resultSet.getInt("SubscriptionID");
                String SubscriptionType = resultSet.getString("SubscriptionType");
                Integer managerID = resultSet.getInt("managerID");


                // Display values
                System.out.print(SubscriptionID + "\t");
                System.out.print(SubscriptionType + "\t");
                System.out.print(managerID + "\t");

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
        Integer SubscriptionID = getInteger("SubscriptionID : ", true);
        String SubscriptionType = getString("SubscriptionType : ", true);
        Integer managerID = getInteger("managerID : ", true);



        Map<String, Object> whereParameters = new HashMap<>();
        if (SubscriptionID != null) whereParameters.put("SubscriptionID", SubscriptionID);
        if (SubscriptionType != null) whereParameters.put("SubscriptionType", SubscriptionType);
        if (managerID != null) whereParameters.put("managerID", managerID);


        return whereParameters;
    }
    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Subscription", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "SubscriptionType,managerID");

        List<Object> rows = new ArrayList<>();
        String SubscriptionType;
        Integer managerID;

        do {
            System.out.println("Fields to insert:");
            SubscriptionType = getString("SubscriptionType : ", true);
            managerID = getInteger("managerID  : ", true);

            System.out.println();

            if (SubscriptionType != null && managerID != null ) {

                rows.add(new Subscription(SubscriptionType, managerID));


            }
        }
        while (SubscriptionType != null && managerID != null );

        parameters.put("rows", rows);

        return new ViewData("Subscription", "insert", parameters);
    }
    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        String SubscriptionType = getString("SubscriptionType : ", true);
        Integer managerID = getInteger("managerID : ", true);

        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (SubscriptionType != null) updateParameters.put("SubscriptionType", SubscriptionType);
        if (managerID != null) updateParameters.put("managerID", managerID);


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Subscription", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Subscription", "delete", parameters);
    }
    public String toString() {
        return "SubscriptionView";
    }

}
