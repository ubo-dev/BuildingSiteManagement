import java.sql.*;
import java.util.*;
import java.util.Date;
public class MovedFlatView implements  ViewInterface{
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
                Integer oldFlatID = resultSet.getInt("oldFlatID");
                Integer newFlatID = resultSet.getInt("newFlatID");
                Integer apartmentID = resultSet.getInt("apartmentID");
                Date enteranceDate = resultSet.getDate("enteranceDate");
                Date leavingDate = resultSet.getDate("leavingDate");
                //int residentCounter = resultSet.getInt("residentCounter");

                // Display values
                System.out.print(oldFlatID + "\t");
                System.out.print(newFlatID + "\t");
                System.out.print(apartmentID + "\t");
                System.out.print(enteranceDate + "\t");
                System.out.println(leavingDate + "\t");
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
        Integer oldFlatID = getInteger("oldFlat ID : ", true);
        Integer newFlatID = getInteger("newFlat ID : ", true);
        Integer apartmentID = getInteger("Apartment ID : ", true);
        StringBuilder enteranceDate = getDate("Enterance Date (YYYY-MM-DD) : ", true);
        StringBuilder leavingDate = getDate("Leaving Date (YYYY-MM-DD) : ", true);


        

        Map<String, Object> whereParameters = new HashMap<>();
        if (oldFlatID != null) whereParameters.put("oldFlatID", oldFlatID);
        if (newFlatID != null) whereParameters.put("newFlatID", newFlatID);
        if (apartmentID != null) whereParameters.put("apartmentID", apartmentID);
        if (enteranceDate != null) whereParameters.put("enteranceDate", enteranceDate);
        if (leavingDate != null) whereParameters.put("leavingDate", leavingDate);
        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("movedFlat", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "oldFlatID, newFlatID, apartmentID, enteranceDate, leavingDate");

        List<Object> rows = new ArrayList<>();

        Integer oldFlatID, newFlatID, apartmentID;
        StringBuilder enteranceDate, leavingDate;
        do
        {
            System.out.println("Fields to insert:");
            oldFlatID = getInteger("Old Flat ID : ", true);
            newFlatID = getInteger("New Flat ID : ", true);
            apartmentID = getInteger("Apartment ID : ", true);
            enteranceDate = getDate("Enterance Date : ", true);
            leavingDate = getDate("Leaving Date : ", true);
            System.out.println();

            if (oldFlatID != null && newFlatID != null && apartmentID != null && enteranceDate != null && leavingDate != null) {
                rows.add(new MovedFlat(oldFlatID, newFlatID, apartmentID, enteranceDate, leavingDate));
            }
        }
        while (oldFlatID != null && newFlatID != null && apartmentID != null && enteranceDate != null && leavingDate != null);

        parameters.put("rows", rows);

        return new ViewData("movedFlat", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        Integer oldFlatID = getInteger("Old Flat ID : ", true);
        Integer newFlatID = getInteger("New Flat ID : ", true);
        Integer apartmentID = getInteger("Apartment ID : ", true);
        StringBuilder enteranceDate = getDate("Enterance Date : ", true);
        StringBuilder leavingDate = getDate("Leaving Date : ", true);
        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (oldFlatID != null) updateParameters.put("oldFlatID", oldFlatID);
        if (newFlatID != null) updateParameters.put("newFlatID", newFlatID);
        if (apartmentID != null) updateParameters.put("apartmentID", apartmentID);
        if (enteranceDate != null) updateParameters.put("enteranceDate", enteranceDate);
        if (leavingDate != null) updateParameters.put("leavingDate", leavingDate);


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("movedFlat", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("movedFlat", "delete", parameters);
    }

    @Override
    public String toString() {
        return "MovedFlatView";
    }
}
