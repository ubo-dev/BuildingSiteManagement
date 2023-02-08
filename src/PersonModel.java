import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PersonModel implements ModelInterface {
    private Scanner scan = new Scanner(System.in);
    @Override
    public ResultSet select(Map<String, Object> whereParameters) throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("	* ");
        sql.append(" FROM dbo.Person ");

        List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);
        sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));

        sql.append("ORDER BY personID");
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
        StringBuilder sql2 = new StringBuilder();


        sql.append(" INSERT INTO dbo.Person (").append(fieldNames).append(") ");
        sql.append(" VALUES ");

        String[] fieldList = fieldNames.split(",");

        //for(String s:fieldList) System.out.println(s);
        int rowCount = 0;
        int flag = 0;
        int aptID = 0;
        for (int i=0; i<rows.size(); i++) {
            if (rows.get(i) instanceof Person person) {
                rowCount++;

                sql.append("(");
                for (int j=0; j<fieldList.length; j++) {
                    String fieldName = fieldList[j].trim();
                    sql.append(DatabaseUtilities.formatField(person.getByName(fieldName)));
                    if (j < fieldList.length - 1) {
                        sql.append(", ");
                    }
                }
                sql.append(")");
                flag = person.getPersonStatus();
                aptID = person.getApartmentID();
                //System.out.println("apt id = " + aptID);
                if (i < rows.size() - 1) {
                    sql.append(", ");
                }
            }
        }

        // execute constructed SQL statement
        if (rowCount > 0) {
            Connection connection = DatabaseUtilities.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            rowCount = preparedStatement.executeUpdate();
            preparedStatement.close();
        }

        if (flag == 1)
        {
            System.out.println("Is resident? (y,n)");
            String choice = scan.nextLine();
            if (choice.equals("y"))
            {
                //resident
                insertResident(sql2);

                insertManager(sql2);

            }
            else if (choice.equals("n"))
            {
                // manager is not resident
                sql2.append("INSERT INTO Manager ");
                sql2.append("(personID,apartmentID,managerName) ");
                sql2.append("SELECT DISTINCT r.personID ");
                sql2.append(",r.apartmentID ");
                sql2.append(",r.residentID ");
                sql2.append(",r.residentName ");
                sql2.append("FROM Resident r, Person p ");
                sql2.append("WHERE NOT EXISTS(SELECT residentID FROM Manager m ");
                sql2.append("where m.residentID = r.residentID) and p.personStatus = 1) ");
            }
            else System.out.println("Unrecognized answer.");

        }
        else if(flag == 2)
        {
            System.out.println("Is resident? (y,n)");
            String choice = scan.nextLine();
            if (choice.equals("y"))
            {
                //resident
                insertResident(sql2);

                insertManager(sql2);

                sql2.append("INSERT INTO AssistantManager ");
                sql2.append("(personID,managerID,residentID,assistantManagerName) ");
                sql2.append("SELECT TOP 1 r.personID, ");
                sql2.append("m.managerID, ");
                sql2.append("r.residentID, ");
                sql2.append("r.residentName ");
                //sql2.append("'"+residentPhoneNum+"',"+flatID+","+paidFlag+" ");
                sql2.append("FROM Resident r,Manager m ");
                sql2.append("WHERE NOT EXISTS(SELECT personID ");
                sql2.append("FROM AssistantManager a2 ");
                sql2.append("WHERE a2.personID = r.personID) ");
                sql2.append("order by personID desc ");

            }
            else if (choice.equals("n"))
            {
                sql2.append("INSERT INTO Manager ");
                sql2.append("(personID,apartmentID,managerName) ");
                sql2.append("SELECT DISTINCT r.personID ");
                sql2.append(",r.apartmentID ");
                sql2.append(",r.residentName ");
                sql2.append("FROM Resident r, Person p ");
                sql2.append("WHERE NOT EXISTS(SELECT residentID FROM Manager m ");
                sql2.append("where m.residentID = r.residentID) and p.personStatus = 1 ");

                // a manager is not resident
                sql2.append("INSERT INTO AssistantManager ");
                sql2.append("(personID,managerID,assistantManagerName) ");
                sql2.append("SELECT TOP 1 p.personID, ");
                sql2.append("m.managerID, ");
                sql2.append("p.personName ");
                //sql2.append("'"+residentPhoneNum+"',"+flatID+","+paidFlag+" ");
                sql2.append("FROM Person p,Manager m ");
                sql2.append("WHERE NOT EXISTS(SELECT personID ");
                sql2.append("FROM AssistantManager a2 ");
                sql2.append("WHERE a2.personID = p.personID) ");
                sql2.append("order by personID desc ");
            }
            else System.out.println("Unrecognized answer.");
        }
        else if(flag == 3)
        {
            System.out.println("Is resident? (y,n)");
            String choice = scan.nextLine();
            if (choice.equals("y"))
            {
                //resident
                insertResident(sql2);

                sql2.append("INSERT INTO Controller ");
                sql2.append("(personID,residentID,controllerName) ");
                sql2.append("select Top 1 r.personID, ");
                sql2.append("r.residentID, ");
                sql2.append("r.residentName ");
                //sql2.append("'"+residentPhoneNum+"',"+flatID+","+paidFlag+" ");
                sql2.append("from Resident r,Person p ");
                sql2.append("where not exists(select personID ");
                sql2.append("from Controller c ");
                sql2.append("where c.personID = r.personID) ");
                sql2.append("order by personID desc) ");



            }
            else if (choice.equals("n"))
            {
                // controller is not resident
                sql2.append("INSERT INTO Controller ");
                sql2.append("(personID,controllerName) ");
                sql2.append("select Top 1 r.personID, ");
                sql2.append("p.personName ");
                //sql2.append("'"+residentPhoneNum+"',"+flatID+","+paidFlag+" ");
                sql2.append("from Resident r,Person p ");
                sql2.append("where not exists(select personID ");
                sql2.append("from Controller c ");
                sql2.append("where c.personID = r.personID) ");
                sql2.append("order by personID desc) ");
            }
            else System.out.println("Unrecognized answer.");
        }
        else if(flag == 4)
        {
            insertResident(sql2);
        }

        Connection connection = DatabaseUtilities.getConnection();
        int residentCounter = getResidentCounter(aptID, connection);
        sql2.append("\nUPDATE Apartment\n" +
                "SET residentCounter = " + residentCounter + "\n" +
                "WHERE apartmentID = " + aptID + " ");
        PreparedStatement preparedStatement = connection.prepareStatement(sql2.toString());
        preparedStatement.execute();

        return rowCount;
    }

    public void insertResident(StringBuilder sql2)
    {
        System.out.println("Enter phone number: ");
        String residentPhoneNum = scan.nextLine();
        System.out.println("Enter flat ID: ");
        int flatID = scan.nextInt();
        System.out.println("Is monthly due paid: ");
        int paidFlag = scan.nextInt();

        sql2.append("INSERT INTO Resident ");
        sql2.append("(personID,apartmentID,residentName,residentPhoneNum,flatId,paidFlag) ");
        sql2.append("SELECT t1.personID, ");
        sql2.append("t1.apartmentID, ");
        sql2.append("t1.personName, ");
        sql2.append("'"+residentPhoneNum+"',"+flatID+","+paidFlag+" ");
        sql2.append("FROM Person t1 ");
        sql2.append("WHERE NOT EXISTS(SELECT personID ");
        sql2.append("FROM Resident t2 ");
        sql2.append("WHERE t2.personID = t1.personID) ");

        sql2.append("\n");
    }

    public void insertManager(StringBuilder sql2)
    {
        // if manager is resident
        sql2.append("INSERT INTO Manager ");
        sql2.append("(personID,apartmentID,residentID,managerName) ");
        sql2.append("SELECT top 1 r.personID ");
        sql2.append(",r.apartmentID ");
        sql2.append(",r.residentID ");
        sql2.append(",r.residentName ");
        sql2.append("FROM Resident r, Person p ");
        sql2.append("WHERE NOT EXISTS(SELECT residentID FROM Manager m ");
        sql2.append("where m.residentID = r.residentID) and p.personStatus = 1 ");
        sql2.append("order by personID desc ");
    }


    @Override
    public int update(Map<String, Object> updateParameters, Map<String, Object> whereParameters) throws Exception {
        // construct SQL statement
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE dbo.Person SET ");
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
        sql.append(" DELETE FROM dbo.Person ");

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

    @Override
    public String toString() {
        return "PersonModel";
    }
}
