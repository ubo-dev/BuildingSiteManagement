import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerView implements ViewInterface
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
                Integer managerID = resultSet.getInt("managerID");
                Integer personID = resultSet.getInt("personID");
                Integer apartmentID = resultSet.getInt("apartmentID");
                Integer residentID = resultSet.getInt("residentID");
                String managerName = resultSet.getString("managerName");

                // Display values
                System.out.print(managerID + "\t");
                System.out.print(personID + "\t");
                System.out.print(apartmentID + "\t");
                System.out.print(residentID + "\t");
                System.out.println(managerName);
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
        Integer managerID = getInteger("Manager ID : ", true);
        Integer personID = getInteger("Person ID : ", true);
        Integer apartmentID = getInteger("Apartment ID : ", true);
        Integer residentID = getInteger("Resident ID : ", true);
        String managerName = getString("Manager Name : ", true);

        Map<String, Object> whereParameters = new HashMap<>();
        if (managerID != null) whereParameters.put("managerID", managerID);
        if (personID != null) whereParameters.put("personID", personID);
        if (apartmentID != null) whereParameters.put("apartmentID", apartmentID);
        if (residentID != null) whereParameters.put("residentID", residentID);
        if (managerName != null) whereParameters.put("managerName", managerName);

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Manager", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "personID,apartmentID,residentID,managerName");

        List<Object> rows = new ArrayList<>();

        Integer personID,apartmentID,residentID;
        String managerName;
        do
        {
            System.out.println("Fields to insert:");
            personID = getInteger("Person ID : ",true);
            apartmentID = getInteger("Apartment ID : ",true);
            residentID = getInteger("Resident ID : ", true);
            managerName = getString("Manager Name : ", true);
            System.out.println();

            if (personID != null && apartmentID != null && managerName != null) {
                rows.add(new Manager(personID,apartmentID,residentID,managerName));
            }
        }
        while (personID != null && apartmentID != null && residentID != null && managerName != null);

        parameters.put("rows", rows);

        return new ViewData("Manager", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        Integer personID = getInteger("Person ID : ",true);
        Integer apartmentID = getInteger("Apartment ID : ",true);
        Integer residentID = getInteger("Resident ID : ",true);
        String managerName = getString("Manager Name : ", true);
        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (personID != null) updateParameters.put("personID", personID);
        if (apartmentID != null) updateParameters.put("apartmentID", apartmentID);
        if (residentID != null) updateParameters.put("residentID", residentID);
        if (managerName != null) updateParameters.put("managerName", managerName);


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Manager", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Manager", "delete", parameters);
    }

    @Override
    public String toString() {
        return "ManagerView";
    }
}
