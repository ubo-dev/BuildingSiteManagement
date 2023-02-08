import java.sql.ResultSet;
import java.util.*;

public class PersonView implements ViewInterface {
    private ManagerView managerView;
    private Person person;
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
                Integer personID = resultSet.getInt("personID");
                Integer apartmentID = resultSet.getInt("apartmentID");
                String personName = resultSet.getString("personName");
                Integer personStatus = resultSet.getInt("personStatus");

                // Display values
                System.out.print(personID + "\t");
                System.out.print(apartmentID + "\t");
                System.out.print(personName + "\t");
                System.out.println(personStatus);
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
        Integer personID = getInteger("Person ID : ", true);
        Integer apartmentID = getInteger("Apartment ID : ", true);
        String personName = getString("Person Name : ", true);
        Integer personStatus = getInteger("Person Status : ", true);

        Map<String, Object> whereParameters = new HashMap<>();
        if (personID != null) whereParameters.put("personID", personID);
        if (apartmentID != null) whereParameters.put("apartmentID", apartmentID);
        if (personName != null) whereParameters.put("personName", personName);
        if (personStatus != null) whereParameters.put("personStatus", personStatus);

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Person", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "apartmentID, personName, personStatus");

        List<Object> rows = new ArrayList<>();
        Integer apartmentID,personStatus;
        String personName;
        do
        {
            System.out.println("Fields to insert:");
            apartmentID = getInteger("Apartment ID : ",true);
            personName = getString("Person Name : ", true);
            personStatus = getInteger("Person Status : ", true);
            System.out.println();


            if (apartmentID != null && personName != null && personStatus != null) {
                rows.add(new Person(apartmentID,personName, personStatus));
            }
        }
        while (apartmentID != null && personName != null && personStatus != null);


        parameters.put("rows", rows);

        return new ViewData("Person", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        Integer apartmentID = getInteger("Apartment ID : ",true);
        String personName = getString("Person Name : ", true);
        Integer personStatus = getInteger("Person Status : ",true);
        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (apartmentID != null) updateParameters.put("apartmentID", apartmentID);
        if (personName != null) updateParameters.put("personName", personName);
        if (personStatus != null) updateParameters.put("personStatus", personStatus);


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Person", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Person", "delete", parameters);
    }

    @Override
    public String toString() {
        return "PersonView";
    }
}
