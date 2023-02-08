import java.sql.ResultSet;
import java.util.*;

public class ApartmentView implements  ViewInterface{

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
                Integer apartmentID = resultSet.getInt("apartmentID");
                String apartmentName = resultSet.getString("apartmentName");
                String street = resultSet.getString("street");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                String zip_code = resultSet.getString("zip_code");
                int residentCounter = resultSet.getInt("residentCounter");

                // Display values
                System.out.print(apartmentID + "\t");
                System.out.print(apartmentName + "\t");
                System.out.print(street + "\t");
                System.out.print(city + "\t");
                System.out.print(state + "\t");
                System.out.print(zip_code + "\t");
                System.out.println(residentCounter + "\t");
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
        Integer apartmentID = getInteger("Apartment ID : ", true);
        String apartmentName = getString("Apartment Name : ", true);
        String street = getString("Street : ", true);
        String city = getString("City : ", true);
        String state = getString("State : ", true);
        String zip_code = getString("Zip Code : ", true);


        Map<String, Object> whereParameters = new HashMap<>();
        if (apartmentID != null) whereParameters.put("apartmentID", apartmentID);
        if (apartmentName != null) whereParameters.put("apartmentName", apartmentName);
        if (street != null) whereParameters.put("street", street);
        if (city != null) whereParameters.put("city", city);
        if (state != null) whereParameters.put("state", state);
        if (zip_code != null) whereParameters.put("zip_code", zip_code);
        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Apartment", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "apartmentName, street, city, state, zip_code");

        List<Object> rows = new ArrayList<>();

        String apartmentName, street, city, state, zip_code;
        do
        {
            System.out.println("Fields to insert:");
            apartmentName = getString("Apartment Name : ", true);
            street = getString("Street : ", true);
            city = getString("City : ", true);
            state = getString("State : ", true);
            zip_code = getString("Zip Code : ", true);
            System.out.println();

            if (apartmentName != null && street != null && city != null && state != null && zip_code != null) {
                rows.add(new Apartment(apartmentName, street, city, state, zip_code));
            }
        }
        while (apartmentName != null && street != null && city != null && state != null && zip_code != null);

        parameters.put("rows", rows);

        return new ViewData("Apartment", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        String apartmentName = getString("Apartment Name : ", true);
        String street = getString("Street : ", true);
        String city = getString("City : ", true);
        String state = getString("State : ",true);
        String zip_code = getString("Zip Code : ",true);
        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (apartmentName != null) updateParameters.put("apartmentName", apartmentName);
        if (street != null) updateParameters.put("street", street);
        if (city != null) updateParameters.put("city", city);
        if (state != null) updateParameters.put("state", state);
        if (zip_code != null) updateParameters.put("zip_code", zip_code);


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Apartment", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Apartment", "delete", parameters);
    }
    @Override
    public String toString() {
        return "ApartmentView{}";
    }
}
