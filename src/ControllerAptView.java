import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerAptView implements ViewInterface
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
                Integer controllerID = resultSet.getInt("controllerID");
                Integer personID = resultSet.getInt("personID");
                Integer residentID = resultSet.getInt("residentID");
                String controllerName = resultSet.getString("controllerName");

                // Display values
                System.out.print(controllerID + "\t");
                System.out.print(personID + "\t");
                System.out.print(residentID + "\t");
                System.out.println(controllerName);
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
        Integer controllerID = getInteger("controllerID ID : ", true);
        Integer personID = getInteger("Person ID : ", true);
        Integer residentID = getInteger("Resident ID : ", true);
        String controllerName = getString("Controller Name : ", true);

        Map<String, Object> whereParameters = new HashMap<>();
        if (controllerID != null) whereParameters.put("controllerID", controllerID);
        if (personID != null) whereParameters.put("personID", personID);
        if (residentID != null) whereParameters.put("residentID", residentID);
        if (controllerName != null) whereParameters.put("controllerName", controllerName);

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("ControllerApt", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "personID, residentID, controllerName");

        List<Object> rows = new ArrayList<>();

        Integer personID,residentID;
        String controllerName;
        do
        {
            System.out.println("Fields to insert:");
            personID = getInteger("Person ID : ",true);
            residentID = getInteger("Resident ID : ", true);
            controllerName = getString("Controller Name : ", true);
            System.out.println();

            if (personID != null && residentID != null && controllerName != null) {
                rows.add(new ControllerApt(personID,residentID, controllerName));
            }
        }
        while (personID != null && residentID != null && controllerName != null);

        parameters.put("rows", rows);

        return new ViewData("Controller", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        Integer personID = getInteger("Person ID : ",true);
        Integer residentID = getInteger("Resident ID : ",true);
        String controllerName = getString("Controller Name : ", true);
        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (personID != null) updateParameters.put("personID", personID);
        if (residentID != null) updateParameters.put("residentID", residentID);
        if (controllerName != null) updateParameters.put("controllerName", controllerName);


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Controller", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Controller", "delete", parameters);
    }

    @Override
    public String toString() {
        return "ControllerView";
    }
}
