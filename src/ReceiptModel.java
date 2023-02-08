import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class ReceiptModel implements ModelInterface {
    private Scanner scan = new Scanner(System.in);
    @Override
    public ResultSet select(Map<String, Object> whereParameters) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("	* ");
        sql.append(" FROM dbo.Receipt ");

        List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);
        sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));

        sql.append("ORDER BY ReceiptID");

        // execute constructed SQL statement
        Connection connection = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);

        return preparedStatement.executeQuery();
    }

    @Override
    public int insert(String fieldNames, List<Object> rows) throws Exception {
        StringBuilder sql = new StringBuilder();
        StringBuilder sql2 = new StringBuilder();


        sql.append(" INSERT INTO dbo.Receipt (").append(fieldNames).append(") ");
        sql.append(" VALUES ");

        String[] fieldList = fieldNames.split(",");

        String ReceiptDescription = "";
        int ResidentID = 0;

        //for(String s:fieldList) System.out.println(s);
        int rowCount = 0;
        for (int i=0; i<rows.size(); i++) {
            if (rows.get(i) instanceof Receipt receipt) {
                rowCount++;

                sql.append("(");
                for (int j=0; j<fieldList.length; j++) {
                    String fieldName = fieldList[j].trim();
                    sql.append(DatabaseUtilities.formatField(receipt.getByName(fieldName)));
                    if (j < fieldList.length - 1) {
                        sql.append(", ");
                    }
                }
                ReceiptDescription = receipt.getReceiptDescription();
                ResidentID = receipt.getResidentID();
                sql.append(")");
                if (i < rows.size() - 1) {
                    sql.append(", ");
                }
            }
        }
        if (rowCount > 0) {
            Connection connection = DatabaseUtilities.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            rowCount = preparedStatement.executeUpdate();

            if(ReceiptDescription.equals("Due")){
                StringBuilder sql3 = new StringBuilder();

                sql3.append("UPDATE Resident\n" +
                        "SET paidFlag = 1\n" +
                        "Where residentID = " + ResidentID + "and paidFlag = 0");

                PreparedStatement preparedStatement1 = connection.prepareStatement(sql3.toString());
                preparedStatement1.executeUpdate();

            }

        }

        System.out.println("Is expense or payment? (e,p)");
        String choice = scan.nextLine();
        if(choice.equals("e")){
            sql2.append("INSERT INTO Expense ");
            sql2.append("(ReceiptId, ControllerId) ");
            sql2.append("SELECT top 1 r.ReceiptId, " );
            sql2.append("c.ControllerId ");
            sql2.append("FROM Receipt r, Controller c ");
            sql2.append("order by ReceiptId desc ");

        }

        else if(choice.equals("p")){

            sql2.append("INSERT INTO dbo.Payment ");
            sql2.append("(ResidentId, ReceiptId) ");
            sql2.append("SELECT top 1 ResidentId, ReceiptId ");
            sql2.append("FROM dbo.Receipt ");
            sql2.append("order by ReceiptId desc ");

        }
        else{
            System.out.println("Unrecognized answer.");
        }

        Connection connection = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql2.toString());
        preparedStatement.execute();


        return rowCount;
    }

    @Override
    public int update(Map<String, Object> updateParameters, Map<String, Object> whereParameters) throws Exception {
        // construct SQL statement
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE dbo.Receipt SET ");
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
        sql.append(" DELETE FROM dbo.Receipt ");

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
    public String toString() {
        return "ReceiptModel";
    }
}
