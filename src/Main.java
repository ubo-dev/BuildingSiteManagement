import java.net.ConnectException;
import java.sql.*;

public class Main {
    private static final String SQL_UPDATE = "UPDATE ? SET ? = ? WHERE ? = ?";
    private static final String SQL_DELETE = "delete ? from ? where ? = ?";

    public static void main(String[] args) {
        final String QUERY = "select * from Person";
        final String serverName = "UBO";
        final String dbName = "BuildingSiteManagement";
        final String conUrl = "jdbc:sqlserver://" + serverName + ":1433;DatabaseName=" + dbName + ";encrypt=true;trustServerCertificate=true;integratedSecurity=true;";
        Database db = new Database();
        Connection connection = null;
        Statement statement = null;
        PreparedStatement psInsert = null;
        PreparedStatement psUpdate = null;
        PreparedStatement psDelete = null;
        ResultSet resultSet = null;


        try{
            connection = db.getConnection(conUrl);
            System.out.println("connected");
            psInsert = connection.prepareStatement(db.INSERT_PERSON);

            //inserting new apartment
            db.insertIntoApartment(connection,"Deniz","Bestekar","Ankara","Cankaya","06510");

            // inserting persons
            // status 1 == manager | 2 == assistant manager | 3 == controller | 4 == regular resident
            db.insertIntoPerson(connection,psInsert,"umut",2,1,"+905639453868",1);
            db.insertIntoPerson(connection,psInsert,"mert",3,2,"+905229457431",1);
            db.insertIntoPerson(connection,psInsert,"hilal",4,3,"+905639453868",1);
            db.insertIntoPerson(connection,psInsert,"fatih",5,4,"+905133563961",0);




            statement = connection.createStatement();
            statement.execute(QUERY);
            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                int personID = resultSet.getInt(1);
                String personName = resultSet.getString(2);
                int apartmentID = resultSet.getInt(3);
                int personStatus = resultSet.getInt(4);
                System.out.println(personID + " " + personName + " " + apartmentID + " " + personStatus);
            }

        } catch (Exception e) {
            System.out.println("Failed...");
            e.printStackTrace();
        } finally {
            db.closeStatement(statement);
            db.closeConnection(connection);
            System.out.println();
            System.out.println("Disconnected...");

        }
    }

}
