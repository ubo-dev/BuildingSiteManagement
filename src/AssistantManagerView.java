import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssistantManagerView implements ViewInterface
{
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

    ViewData selectOperation(ModelData modelData) throws Exception {
        ResultSet resultSet = modelData.resultSet;

        if (resultSet != null) {
            while (resultSet.next()) {
                // Retrieve by column name
                Integer assistantManagerID = resultSet.getInt("assistantManagerID");
                Integer personID = resultSet.getInt("personID");
                Integer managerID = resultSet.getInt("managerID");
                Integer residentID = resultSet.getInt("residentID");
                String assistantManagerName = resultSet.getString("assistantManagerName");

                // Display values
                System.out.print(assistantManagerID + "\t");
                System.out.print(personID + "\t");
                System.out.print(managerID + "\t");
                System.out.print(residentID + "\t");
                System.out.println(assistantManagerName);
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
        Integer assistantManagerID = getInteger("Assistant Manager ID : ", true);
        Integer personID = getInteger("Person ID : ", true);
        Integer managerID = getInteger("Manager ID : ", true);
        Integer residentID = getInteger("Resident ID : ", true);
        String assistantManagerName = getString("Assistant Manager Name : ", true);

        Map<String, Object> whereParameters = new HashMap<>();
        if (assistantManagerID != null) whereParameters.put("assistantManagerID", personID);
        if (personID != null) whereParameters.put("personID", personID);
        if (managerID != null) whereParameters.put("managerID", managerID);
        if (residentID != null) whereParameters.put("residentID", residentID);
        if (assistantManagerName != null) whereParameters.put("assistantManagerName", assistantManagerName);

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("AssistantManager", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "personID, managerID, residentID, assistantManagerName");

        List<Object> rows = new ArrayList<>();

        Integer personID,residentID,managerID;
        String assistantManagerName;
        do
        {
            System.out.println("Fields to insert:");
            personID = getInteger("Person ID : ",true);
            managerID = getInteger("Manager ID : ",true);
            residentID = getInteger("Resident ID : ",true);
            assistantManagerName = getString("Assistant Manager Name : ", true);
            System.out.println();

            if (personID != null && managerID != null && residentID != null && assistantManagerName != null) {
                rows.add(new AssistantManager(personID,managerID,residentID, assistantManagerName));
            }
        }
        while (personID != null && managerID != null && residentID != null && assistantManagerName != null);

        parameters.put("rows", rows);

        return new ViewData("AssistantManager", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        Integer personID = getInteger("Person ID : ",true);
        Integer managerID = getInteger("Resident ID : ",true);
        Integer residentID = getInteger("Resident ID : ",true);
        String assistantManagerName = getString("Assistant Manager Name : ", true);
        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (personID != null) updateParameters.put("personID", personID);
        if (managerID != null) updateParameters.put("managerID", managerID);
        if (residentID != null) updateParameters.put("residentID", residentID);
        if (assistantManagerName != null) updateParameters.put("assistantManagerName", assistantManagerName);


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("AssistantManager", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("AssistantManager", "delete", parameters);
    }

    @Override
    public String toString() {
        return "AssistantManagerView";
    }
}
