import java.net.ConnectException;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        final String QUERY = "select * from Person";
        final String serverName = "UBO";
        final String dbName = "BuildingSiteManagement";
        final String conUrl = "jdbc:sqlserver://" + serverName + ":1433;DatabaseName=" + dbName + ";encrypt=true;trustServerCertificate=true;integratedSecurity=true;";
        Database db = new Database();
        Connection connection = null;
        Statement statement = null;
        PreparedStatement psInsert = null;
        ResultSet resultSet = null;


        try{
            connection = db.getConnection(conUrl);
            System.out.println("connected");
            psInsert = connection.prepareStatement(db.getINSERT_APARTMENT());
            //inserting new apartment
            //db.insertIntoApartment(connection,"Deniz","Bestekar","Ankara","Cankaya","06510");
            //db.insertIntoApartment(connection,"Beyaz Saray","Ayvalı","Ankara","Keçiören","06780");

            // inserting persons
            // status 1 == manager | 2 == assistant manager | 3 == controller | 4 == regular resident*/
            psInsert = connection.prepareStatement(db.getINSERT_PERSON());
            db.insertIntoPerson(connection,psInsert,1,"Umut Okur",1,"+905639453868",1);
            db.insertIntoPerson(connection,psInsert,1,"Mert Urhan",2,"+905229457431",1);
            db.insertIntoPerson(connection,psInsert,2,"Hilal Kabanlı",3,"+905639453868",1);
            db.insertIntoPerson(connection,psInsert,2,"Fatih Nar",4,"+905133563961",0);

            //db.insertIntoCashRegister(connection,150,0,"2022-10-14",8,35,"due",1);
            //db.insertIntoCashRegister(connection,0,55000,"2022-11-20",6,25,"salary",1);

            //deletion
            db.deleteTable(connection,"Apartment","apartmentName","Deniz");
            //db.deleteTable(connection,"Apartment","apartmentName","Beyaz Saray");


            //update
            //db.updateTable(connection,"Resident","residentPhoneNum","+905444399868","residentName","Mert Urhan");
            //db.updateTable(connection,"Person","PersonStatus","1","PersonName","Hilal Kabanlı");


            //

        } catch (Exception e) {
            System.out.println("Failed...");
            e.printStackTrace();
        } finally {
            db.closeConnection(connection);
            System.out.println();
            System.out.println("Disconnected...");

        }
    }

}
