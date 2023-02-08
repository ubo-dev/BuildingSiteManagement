import javax.swing.text.View;
import java.sql.*;
import java.util.*;
import java.util.Date;


public class DecisionsView implements ViewInterface {

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
                Integer DecisionID = resultSet.getInt("DecisionID");
                Integer ApartmentID = resultSet.getInt("ApartmentID");
                String DecisionDescription = resultSet.getString("DecisionDescription");
                Integer VoteYes = resultSet.getInt("VoteYes");
                Integer VoteNo = resultSet.getInt("VoteNo");
                Date DecisionDate = resultSet.getDate("DecisionDate");
                String isAccepted = resultSet.getString("isAccepted");
                String isFullOrMajority = resultSet.getString("isFullOrMajority");

                // Display values
                System.out.print(DecisionID + "\t");
                System.out.print(ApartmentID + "\t");
                System.out.print(DecisionDescription + "\t");
                System.out.print(VoteYes + "\t");
                System.out.print(VoteNo + "\t");
                System.out.print(DecisionDate + "\t");
                System.out.print(isAccepted + "\t");
                System.out.println(isFullOrMajority + "\t");
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
        Integer DecisionID = getInteger("Decision ID : ", true);
        Integer ApartmentID = getInteger("Apartment ID : ", true);
        String DecisionDescription = getString("Decision Description : ", true);
        Integer VoteYes = getInteger("Vote YES : ", true);
        Integer VoteNo = getInteger("Vote NO : ", true);
        StringBuilder DecisionDate = getDate("Decision Date : ", true);
        String isAccepted = getString("is Accepted : ", true);
        String isFullOrMajority = getString("is Full or Majority : ", true);


        Map<String, Object> whereParameters = new HashMap<>();
        if (DecisionID != null) whereParameters.put("DecisionID", DecisionID);
        if (ApartmentID != null) whereParameters.put("ApartmentID", ApartmentID);
        if (DecisionDescription != null) whereParameters.put("DecisionDescription", DecisionDescription);
        if (VoteYes != null) whereParameters.put("VoteYes", VoteYes);
        if (VoteNo != null) whereParameters.put("VoteNo", VoteNo);
        if (DecisionDate != null) whereParameters.put("DecisionDate", DecisionDate);
        if (isAccepted != null) whereParameters.put("isAccepted", isAccepted);
        if (isFullOrMajority != null) whereParameters.put("isFullOrMajority", isFullOrMajority);
        return whereParameters;
    }

    ViewData selectGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Decisions", "select", parameters);
    }

    ViewData insertGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fieldNames", "ApartmentID, DecisionDescription, VoteYes, VoteNo, DecisionDate, isAccepted, isFullOrMajority");

        List<Object> rows = new ArrayList<>();

        Integer DecisionID, ApartmentID, VoteYes, VoteNo;
        String DecisionDescription,  isAccepted, isFullOrMajority;
        StringBuilder DecisionDate;

        int residentCounter = 0;

        do
        {
            int temp = 0;
            System.out.println("Fields to insert:");
            //DecisionID = getInteger("Apartment Name : ", true);
            ApartmentID = getInteger("Apartment ID : ", true);
            DecisionDescription = getString("Decision Description : ", true);
            VoteYes = getInteger("Vote YES : ", true);
            VoteNo = getInteger("Vote NO : ", true);
            DecisionDate = getDate("Decision Date (YYYY-MM-DD) : ", true);
            isFullOrMajority = getString("Is Decision Full or Majority (write Full or Majority): ", true);
            System.out.println();

            if (ApartmentID != null && DecisionDescription != null && VoteYes != null && VoteNo != null && DecisionDate != null && isFullOrMajority != null) {


                if( isFullOrMajority.equals("Full")){
                    residentCounter = getResidentCounter(ApartmentID);
                    if(residentCounter == (VoteYes + VoteNo)){
                        rows.add(new Decisions(ApartmentID, DecisionDescription, VoteYes, VoteNo, DecisionDate,"YES", "Full"));
                    }
                    else{
                        rows.add(new Decisions(ApartmentID, DecisionDescription, VoteYes, VoteNo, DecisionDate,"NO", "Full"));
                    }
                }else{
                    if(VoteYes > VoteNo){
                        rows.add(new Decisions(ApartmentID, DecisionDescription, VoteYes, VoteNo, DecisionDate,"YES", "Majority"));
                    }
                    else{
                        rows.add(new Decisions(ApartmentID, DecisionDescription, VoteYes, VoteNo, DecisionDate,"NO", "Majority"));
                    }
                }


            }
        }
        while (ApartmentID != null && DecisionDescription != null && VoteYes != null && VoteNo != null && DecisionDate != null && isFullOrMajority != null);

        parameters.put("rows", rows);

        return new ViewData("Decisions", "insert", parameters);
    }

    ViewData updateGUI(ModelData modelData) throws Exception {
        System.out.println("Fields to update:");
        Integer ApartmentID = getInteger("Apartment ID : ", true);
        String DecisionDescription = getString("Decision Description : ", true);
        Integer VoteYes = getInteger("Vote NO : ", true);
        Integer VoteNo = getInteger("Vote YES : ",true);
        StringBuilder DecisionDate = getDate("Decision Date (YYYY-MM-DD) : ",true);
        String isAccepted = getString("Enter YES or NO", false);
        String isFullOrMajority = getString("Is Decision Full or Majority (write Full or Majority): ", true);

        System.out.println();

        Map<String, Object> updateParameters = new HashMap<>();
        if (ApartmentID != null) updateParameters.put("ApartmentID", ApartmentID);
        if (DecisionDescription != null) updateParameters.put("DecisionDescription", DecisionDescription);
        if (VoteYes != null) updateParameters.put("VoteYes", VoteYes);
        if (VoteNo != null) updateParameters.put("VoteNo", VoteNo);
        if (DecisionDate != null) updateParameters.put("DecisionDate", DecisionDate);
        if (isAccepted != null ) updateParameters.put("isAccepted", isAccepted);
        if (isFullOrMajority != null) updateParameters.put("isFullOrMajority", isFullOrMajority);


        Map<String, Object> parameters = new HashMap<>();
        parameters.put("updateParameters", updateParameters);
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Decisions", "update", parameters);
    }

    ViewData deleteGUI(ModelData modelData) throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("whereParameters", getWhereParameters());

        return new ViewData("Decisions", "delete", parameters);
    }

    public static int getResidentCounter(int ApartmentID) throws SQLException {

        int residentCount = 0;

        String query = "SELECT COUNT(*) AS residentCount\nFROM Person\nWHERE apartmentID = " + ApartmentID;
        try {

            Connection conn = DatabaseUtilities.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery(query);
            while ( rs.next() ) {
                residentCount = rs.getInt("residentCount");
            }

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

        return residentCount;


    }

    @Override
    public String toString() {
        return "DecisionsView{}";
    }
}
