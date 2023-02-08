import java.sql.*;
import java.util.List;
import java.util.Map;
public class DecisionsModel implements ModelInterface{


    @Override
    public ResultSet select(Map<String, Object> whereParameters) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("	* ");
        sql.append(" FROM dbo.Decisions ");

        List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);
        sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));

        sql.append("ORDER BY decisionID");
        //System.out.println(sql.toString() + "\n");

        // execute constructed SQL statement
        Connection connection = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);

        return preparedStatement.executeQuery();
    }

    @Override
    public int insert(String fieldNames, List<Object> rows) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO dbo.Decisions (").append(fieldNames).append(") ");
        sql.append(" VALUES ");
        StringBuilder sql2 = new StringBuilder();

        String[] fieldList = fieldNames.split(",");

        int rowCount = 0;
        String DecisionDescription = "";
        String isAccepted = "";
        int aptID = 0;
        int residentCount = 0;
        for (int i=0; i<rows.size(); i++) {
            if (rows.get(i) instanceof Decisions decisions) {
                rowCount++;

                sql.append("(");
                for (int j=0; j<fieldList.length; j++) {
                    String fieldName = fieldList[j].trim();
                    sql.append(DatabaseUtilities.formatField(decisions.getByName(fieldName)));
                    if (j < fieldList.length - 1) {
                        sql.append(", ");
                    }
                }
                sql.append(")");
                DecisionDescription = decisions.getDecisionDescription();
                isAccepted = decisions.getIsAccepted();
                aptID = decisions.getApartmentID();
                if (i < rows.size() - 1) {
                    sql.append(", ");
                }
            }
        }
        //System.out.println(sql.toString());
        //System.out.println("decision : " + DecisionDescription);
        //System.out.println("apt id = " + aptID);

        // execute constructed SQL statement
        if (rowCount > 0) {
            Connection connection = DatabaseUtilities.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            rowCount = preparedStatement.executeUpdate();
            residentCount = getResidentCounter(aptID, connection);
            if (DecisionDescription.equals("Due") && residentCount >= 9 && isAccepted.equals("YES")){
                sql2.append("UPDATE Resident\n" +
                        "SET paidFlag = 0\n" +
                        "WHERE apartmentID = " +  aptID + " and Resident.residentID IN (Select m.residentID from Manager m Where m.apartmentID = " + aptID + "and Not Exists(SELECT am.residentID from AssistantManager am WHERE m.residentID = am.residentID))\n");
            }
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql2.toString());
            preparedStatement1.executeUpdate();
            preparedStatement.close();
        }

        Connection connection = DatabaseUtilities.getConnection();


        return rowCount;
    }
        //System.out.println(sql.toString());

    @Override
    public int update(Map<String, Object> updateParameters, Map<String, Object> whereParameters) throws Exception {
        // construct SQL statement
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE dbo.Decisions SET ");
        int appendCount = 0;
        for (Map.Entry<String, Object> entry : updateParameters.entrySet()) {
            sql.append(entry.getKey()).append(" = ").append(DatabaseUtilities.formatField(entry.getValue()));
            if (++appendCount < updateParameters.size()) {
                sql.append(", ");
            }
        }
        List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);
        sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));
        //System.out.println(sql.toString());

        // execute constructed SQL statement
        Connection connection = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
        int rowCount = preparedStatement.executeUpdate();
        preparedStatement.close();

        return rowCount;
    }

    @Override
    public int delete(Map<String, Object> whereParameters) throws Exception {
        // construct SQL statement
        StringBuilder sql = new StringBuilder();
        sql.append(" DELETE FROM dbo.Decisions ");

        List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);
        sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));
        //System.out.println(sql.toString());


        // execute constructed SQL statement
        Connection connection = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
        int rowCount = preparedStatement.executeUpdate();
        preparedStatement.close();

        return rowCount;
    }

    public static int getResidentCounter(int ApartmentID, Connection conn) throws SQLException {

        int residentCount = 0;


        String query = "SELECT COUNT(*) AS residentCount\nFROM Person\nWHERE apartmentID = " + ApartmentID;
        try {

            //conn = DatabaseUtilities.getConnection();
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
}
