import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResidentView implements ViewInterface{
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
                Integer residentID = resultSet.getInt("residentID");
                Integer personID = resultSet.getInt("personID");
                Integer apartmentID = resultSet.getInt("apartmentID");
                String residentName = resultSet.getString("residentName");
                String residentPhoneNum = resultSet.getString("residentPhoneNum");
                Integer paidFlag = resultSet.getInt("paidFlag");
                Integer flatId = resultSet.getInt("flatId");


                // Display values
                System.out.print(residentID + "\t");
                System.out.print(personID + "\t");
                System.out.print(apartmentID + "\t");
                System.out.print(residentName + "\t");
                System.out.print(residentPhoneNum + "\t");
                System.out.print(paidFlag + "\t");
                System.out.println(flatId);
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
        Integer residentID = getInteger("Resident ID: ",true);
        Integer personID = getInteger("Person ID: ",true);
        Integer apartmentID = getInteger("Apartment ID: ",true);
        String residentName = getString("Resident Name: ",true);
        String residentPhoneNum = getString("Resident Phone: ", true);
        Integer paidFlag = getInteger("Paid Flag: ",true);
        Integer flatId = getInteger("Flat ID: ",true);

        Map<String, Object> whereParameters = new HashMap<>();
        if (residentID != null) whereParameters.put("residentID", personID);
        if (personID != null) whereParameters.put("personID", personID);
        if (apartmentID != null) whereParameters.put("apartmentID", apartmentID);
        if (residentName != null) whereParameters.put("residentName", residentName);
        if (residentPhoneNum != null) whereParameters.put("residentPhoneNum", residentPhoneNum);
        if (paidFlag != null) whereParameters.put("paidFlag", paidFlag);
        if (flatId != null) whereParameters.put("flatId", flatId);

        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Resident", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "apartmentID, personName, personStatus");

        List<Object> rows = new ArrayList<>();

        Integer apartmentID,personStatus;
        String personName;
        do
        {
            System.out.println("Fields to insert for Person:");
            apartmentID = getInteger("Apartment ID : ",true);
            personName = getString("Person Name : ", true);
            personStatus = getInteger("Person Status : ", true);
            System.out.println();

            System.out.println("Fields to insert for Resident:");
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

        return new ViewData("Resident", "insert", parameters);
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

        return new ViewData("Resident", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Resident", "delete", parameters);
    }

    @Override
    public String toString() {
        return "ResidentView";
    }
}
