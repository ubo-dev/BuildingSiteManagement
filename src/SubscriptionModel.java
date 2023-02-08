import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SubscriptionModel implements ModelInterface{
    private Scanner scan = new Scanner(System.in);
    @Override
    public ResultSet select(Map<String, Object> whereParameters) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append(" * ");
        sql.append(" FROM dbo.Subscription ");

        List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);
        sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));

        sql.append("ORDER BY SubscriptionID");


        // execute constructed SQL statement
        Connection connection = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
        ResultSet result = preparedStatement.executeQuery();

        return result;
    }

    @Override
    public int insert(String fieldNames, List<Object> rows) throws Exception {
        // construct SQL statement
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO dbo.Subscription (").append(fieldNames).append(") " );
        sql.append(" VALUES ");

        String[] fieldList = fieldNames.split(",");

        int rowCount = 0;
        for (int i=0; i<rows.size(); i++) {
            if (rows.get(i) instanceof Subscription subscription) {
                rowCount++;

                // Department department = (Department)rows.get(i);

                sql.append("(");
                for (int j=0; j<fieldList.length; j++) {
                    String fieldName = fieldList[j].trim();
                    sql.append(DatabaseUtilities.formatField(subscription.getByName(fieldName)));
                    if (j < fieldList.length - 1) {
                        sql.append(", ");
                    }
                }
                sql.append(")");

                if (i < rows.size() - 1) {
                    sql.append(", ");
                }
            }
        }
        //System.out.println(sql.toString());


        // execute constructed SQL statement
        if (rowCount > 0) {
            Connection connection = DatabaseUtilities.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            rowCount = preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        StringBuilder sql2 = new StringBuilder();
        System.out.print("billDesc: ");
        String billDesc = scan.nextLine();
        System.out.print("billAmount: ");
        double billAmount = scan.nextDouble();
        System.out.println(billAmount);
        System.out.print("Image URL: ");
        String image = scan.next();

        sql2.append("INSERT INTO Bill ");
        sql2.append("(billDesc, subscriptionID, billAmount, billImage) ");
        sql2.append("SELECT top 1 " );
        sql2.append("'").append(billDesc).append("', ");
        sql2.append("subscriptionID, ");
        sql2.append(" ").append(billAmount).append(",'").append(image).append("' ");
        sql2.append("FROM Subscription ");
        sql2.append("order by subscriptionID desc ");

        Connection connection = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql2.toString());
        preparedStatement.execute();


        return rowCount;
    }
    @Override
    public int update(Map<String, Object> updateParameters, Map<String, Object> whereParameters) throws Exception {
            StringBuilder sql = new StringBuilder();
            sql.append(" UPDATE dbo.Subscription SET ");
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
        StringBuilder sql = new StringBuilder();
        sql.append(" DELETE FROM dbo.Subscription ");
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
    public String toString() {
        return "SubscriptionModel{}";
    }
}
