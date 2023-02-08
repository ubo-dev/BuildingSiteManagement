import java.sql.ResultSet;
import java.util.*;

public class MessageView implements  ViewInterface{
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
                Integer ManagerID = resultSet.getInt("ManagerID");
                Integer ResidentID = resultSet.getInt("ResidentID");
                String Message = resultSet.getString("Message");
                Date SendDate = resultSet.getDate("SendDate");
                //int residentCounter = resultSet.getInt("residentCounter");

                // Display values
                System.out.print(ManagerID + "\t");
                System.out.print(ResidentID + "\t");
                System.out.print(Message + "\t");
                System.out.println(SendDate + "\t");
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
        Integer ManagerID = getInteger("Manager ID : ", true);
        Integer ResidentID = getInteger("Resident ID : ", true);
        String Message = getString("Message : ", true);
        StringBuilder SendDate = getDate("Send Date (YYYY-MM-DD) : ", true);


        Map<String, Object> whereParameters = new HashMap<>();
        if (ManagerID != null) whereParameters.put("ManagerID", ManagerID);
        if (ResidentID != null) whereParameters.put("ResidentID", ResidentID);
        if (Message != null) whereParameters.put("Message", Message);
        if (SendDate != null) whereParameters.put("SendDate", SendDate);
        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Message", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "ManagerID, ResidentID, Message, SendDate");

        List<Object> rows = new ArrayList<>();

        Integer ManagerID, ResidentID;
        String Message;
        StringBuilder SendDate;
        do
        {
            System.out.println("Fields to insert:");
            ManagerID = getInteger("Manager ID : ", true);
            ResidentID = getInteger("Resident ID : ", true);
            Message = getString("Message : ", true);
            SendDate = getDate("Send Date : ", true);
            System.out.println();

            if (ManagerID != null && ResidentID != null && Message != null && SendDate != null) {
                rows.add(new Message(ManagerID, ResidentID, Message, SendDate));
            }
        }
        while (ManagerID != null && ResidentID != null && Message != null && SendDate != null);

        parameters.put("rows", rows);

        return new ViewData("Message", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        Integer ManagerID = getInteger("Manager ID : ", true);
        Integer ResidentID = getInteger("Resident ID : ", true);
        String Message = getString("Message : ", true);
        StringBuilder SendDate = getDate("Send Date : ", true);
        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (ManagerID != null) updateParameters.put("ManagerID", ManagerID);
        if (ResidentID != null) updateParameters.put("ResidentID", ResidentID);
        if (Message != null) updateParameters.put("Message", Message);
        if (SendDate != null) updateParameters.put("SendDate", SendDate);


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Message", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Message", "delete", parameters);
    }

    @Override
    public String toString() {
        return "MessageView{}";
    }
}
