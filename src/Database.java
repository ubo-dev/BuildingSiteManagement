import java.sql.*;
public class Database
{
    private Statement statement = null;
    private ResultSet resultSet = null;
    final String INSERT_PERSON = "INSERT INTO PERSON VALUES (?,?,?)";
    final String INSERT_APARTMENT = "insert into Apartment values(?,?,?,?,?)";
    final String INSERT_MANAGER = "insert into Manager values(?,?,?)";
    final String INSERT_RESIDENT = "insert into Resident values(?,?,?,?,?)";
    final String INSERT_ASSISTANTMANAGER = "insert into AssistantManager values(?,?,?,?)";
    final String INSERT_CONTROLLER = "insert into Controller values(?,?,?)";
    final String SELECT_LAST_PERSON = "SELECT TOP 1 * FROM Person ORDER BY personID DESC";
    final String SELECT_LAST_RESIDENT = "SELECT TOP 1 * FROM Resident ORDER BY residentID DESC";
    final String SELECT_LAST_MANAGER = "SELECT TOP 1 * FROM Manager ORDER BY managerID DESC";
    public Connection getConnection(String url) throws SQLException {
        Connection connection = DriverManager.getConnection(url);
        connection.setAutoCommit(true);
        return connection;
    }

    public void insertIntoPerson(Connection connection,PreparedStatement psInsert,String personName,int apartmentID,int status,String residentPhone,int paidFlag)
    {
        try {
            psInsert.setString(1,personName);
            psInsert.setInt(2,apartmentID);
            psInsert.setInt(3,status);


            if (status != 4)
            {
                PreparedStatement psInsertResident = connection.prepareStatement(INSERT_RESIDENT);
                insertIntoResident(connection,psInsertResident,apartmentID, personName,residentPhone,paidFlag);
                if (status == 1) //manager
                {
                    PreparedStatement psInsertManager = connection.prepareStatement(INSERT_MANAGER);
                    insertIntoManager(connection,psInsertManager);
                }
                else if (status == 2) //assistant manager
                {
                    PreparedStatement psInsertManager = connection.prepareStatement(INSERT_MANAGER);
                    insertIntoManager(connection,psInsertManager);
                    PreparedStatement psInsertAsistantManager = connection.prepareStatement(INSERT_ASSISTANTMANAGER);
                    insertIntoAssistantManager(connection,psInsertAsistantManager);
                }
                else if (status == 3) //controller
                {
                    PreparedStatement psInsertManager = connection.prepareStatement(INSERT_CONTROLLER);
                    insertIntoController(connection,psInsertManager);
                }
            }
            else
            {
                PreparedStatement psInsertResident = connection.prepareStatement(INSERT_RESIDENT);
                insertIntoResident(connection,psInsertResident,apartmentID, personName,residentPhone,paidFlag);
            }

            psInsert.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void insertIntoApartment(Connection connection,String apartmentName,String street,String city,String state,String zipCode)
    {
        try {
            PreparedStatement psInsert = connection.prepareStatement(INSERT_APARTMENT);

            psInsert.setString(1,apartmentName);
            psInsert.setString(2,street);
            psInsert.setString(3,city);
            psInsert.setString(4,state);
            psInsert.setString(5,zipCode);
            psInsert.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void insertIntoResident(Connection connection,PreparedStatement psInsert,int apartmentID,String residentName,String residentPhone,int paidFlag)
    {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_LAST_PERSON);
            int personID = 0;

            while (resultSet.next())
                personID = resultSet.getInt("personID");

            psInsert.setInt(1,personID);
            psInsert.setInt(2,apartmentID);
            psInsert.setString(3,residentName);
            psInsert.setString(4,residentPhone);
            psInsert.setInt(5,paidFlag);
            psInsert.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void insertIntoManager(Connection connection,PreparedStatement psInsert)
    {
        try {
            int personID = 0;
            int residentID = 0;
            String managerName = null;
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_LAST_RESIDENT);
            while (resultSet.next()) {
                personID = resultSet.getInt("personID");
                residentID = resultSet.getInt("residentID");
                managerName = resultSet.getString("residentName");
            }
            psInsert.setInt(1,personID);
            psInsert.setInt(2,residentID);
            psInsert.setString(3,managerName);
            psInsert.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertIntoAssistantManager(Connection connection,PreparedStatement psInsert)
    {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_LAST_MANAGER);
            int managerID = 0;
            int personID = 0;
            int residentID = 0;
            String managerName = null;
            while (resultSet.next()) {
                managerID = resultSet.getInt("managerID");
                personID = resultSet.getInt("personID");
                residentID = resultSet.getInt("residentID");
                managerName = resultSet.getString("managerName");
            }
            psInsert.setInt(1,personID);
            psInsert.setInt(2,managerID);
            psInsert.setInt(3,residentID);
            psInsert.setString(4,managerName);
            psInsert.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertIntoController(Connection connection,PreparedStatement psInsert)
    {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_LAST_RESIDENT);
            int personID = 0;
            int residentID = 0;
            String controllerName = null;
            while (resultSet.next()) {
                personID = resultSet.getInt("personID");
                residentID = resultSet.getInt("residentID");
                controllerName = resultSet.getString("residentName");
            }
            psInsert.setInt(1,personID);
            psInsert.setInt(2,residentID);
            psInsert.setString(3,controllerName);
            psInsert.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void closeConnection (Connection connection)
    {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void closeStatement (Statement statement)
    {
        try {
            if (statement != null)
                statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closePreparedStatement(PreparedStatement preparedStatement)
    {
        try {
            if (preparedStatement != null)
                preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeResultSet (ResultSet resultSet)
    {
        try {
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
