import java.sql.*;
import java.util.Scanner;

public class Database
{
    private Statement statement = null;
    private ResultSet resultSet = null;
    private final String INSERT_APARTMENT = "insert into Apartment values(?,?,?,?,?)";
    private final String INSERT_MANAGER = "insert into Manager values(?,?,?,?)";
    private final String INSERT_ASSISTANTMANAGER = "insert into AssistantManager values(?,?,?,?)";
    private final String INSERT_CONTROLLER = "insert into Controller values(?,?,?)";
    private final String INSERT_RESIDENT = "insert into Resident values(?,?,?,?,?)";
    private final String SELECT_LAST_RESIDENT = "SELECT TOP 1 * FROM Resident ORDER BY residentID DESC";
    private final String SELECT_LAST_Manager = "SELECT TOP 1 * FROM Manager ORDER BY managerID DESC";
    private final String SELECT_LAST_CASHREGISTER = "SELECT TOP 1 * FROM CashRegister ORDER BY transactionID DESC";




    public String getINSERT_APARTMENT() {
        return INSERT_APARTMENT;
    }

    public String getINSERT_PERSON() {
        return "INSERT INTO PERSON VALUES (?,?,?)";
    }
    public Connection getConnection(String url) throws SQLException {
        Connection connection = DriverManager.getConnection(url);
        connection.setAutoCommit(true);
        return connection;
    }

    public void deleteTable(Connection connection,String tablename, String con3,String con4)
    {
        String SQL_DELETE = "DELETE FROM "+tablename+" WHERE "+con3+"='"+con4+"'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTable(Connection connection,String tableName, String con1,String con2,String con3,String con4)
    {
        String SQL_UPDATE = "UPDATE "+tableName+" SET "+con1+"='"+con2+"' WHERE "+con3+"='"+con4+"'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.execute();

           /* if (con2.equals("PersonStatus"))
            {
                if (con3.equals("1") && con4.equals("2"))
                {
                    //insert into asistant manager
                    PreparedStatement psInsert = connection.prepareStatement(INSERT_ASSISTANTMANAGER);
                    insertIntoAssistantManager(connection);
                }
                else if (con3.equals("1") && con4.equals("3"))
                {
                    deleteTable(connection,tableName,con3,con4);
                    PreparedStatement psInsert = connection.prepareStatement(INSERT_CONTROLLER);
                    insertIntoAssistantManager(connection);
                }
                else if (con3.equals("2") && con4.equals("1"))
                {
                    deleteTable(connection,tableName,con3,con4);
                }
                else if (con3.equals("2") && con4.equals("3"))
                {
                    deleteTable(connection,tableName,con3,con4);
                    PreparedStatement psInsert = connection.prepareStatement(INSERT_CONTROLLER);
                    insertIntoController(connection);
                }
                else if (con3.equals("3") && con4.equals("1"))
                {
                    deleteTable(connection,tableName,con3,con4);
                    PreparedStatement psInsert = connection.prepareStatement(INSERT_MANAGER);
                    insertIntoManager(connection);
                }
                else if (con3.equals("3") && con4.equals("2"))
                {
                    deleteTable(connection,tableName,con3,con4);
                    PreparedStatement psInsert = connection.prepareStatement(INSERT_ASSISTANTMANAGER);
                    insertIntoAssistantManager(connection);
                }

            }*/

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertIntoPerson(Connection connection,PreparedStatement psInsert,int apartmentID,String personName,int status,String residentPhone,int paidFlag)
    {
        try {
            psInsert.setInt(1,apartmentID);
            psInsert.setString(2,personName);
            psInsert.setInt(3,status);
            psInsert.execute();

            if (status != 4)
            {
                insertIntoResident(connection,apartmentID, personName,residentPhone,paidFlag);
                if (status == 1) //manager
                {
                    insertIntoManager(connection,apartmentID,personName,status);
                }
                else if (status == 2) //assistant manager
                {
                    insertIntoManager(connection,apartmentID,personName,status);
                }
                else if (status == 3) //controller
                {
                    insertIntoController(connection,personName);
                }
            }
            else
            {
                insertIntoResident(connection,apartmentID, personName,residentPhone,paidFlag);
            }

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

    public void insertIntoResident(Connection connection,int apartmentID,String residentName,String residentPhone,int paidFlag)
    {
        try {
            PreparedStatement psInsertResident = connection.prepareStatement(INSERT_RESIDENT);
            statement = connection.createStatement();
            String SELECT_LAST_PERSON = "SELECT TOP 1 * FROM Person ORDER BY personID DESC";
            resultSet = statement.executeQuery(SELECT_LAST_PERSON);
            int personID = 0;

            while (resultSet.next())
                personID = resultSet.getInt("personID");

            psInsertResident.setInt(1,personID);
            psInsertResident.setInt(2,apartmentID);
            psInsertResident.setString(3,residentName);
            psInsertResident.setString(4,residentPhone);
            psInsertResident.setInt(5,paidFlag);
            psInsertResident.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void insertIntoManager(Connection connection,int apartmentID,String managerName,int status)
    {
        try {
            PreparedStatement psInsertManager = connection.prepareStatement(INSERT_MANAGER);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_LAST_RESIDENT);
            int personID = 0;
            int residentID = 0;
            while (resultSet.next()) {
                personID = resultSet.getInt("personID");
                residentID = resultSet.getInt("residentID");
            }

            psInsertManager.setInt(1,personID);
            psInsertManager.setInt(2,apartmentID);
            psInsertManager.setInt(3,residentID);
            psInsertManager.setString(4,managerName);
            psInsertManager.execute();

            if (status == 2) insertIntoAssistantManager(connection,personID,residentID,managerName);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertIntoAssistantManager(Connection connection,int personID,int residentID,String managerName)
    {
        try {
            PreparedStatement psInsertAsistantManager = connection.prepareStatement(INSERT_ASSISTANTMANAGER);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_LAST_Manager);
            int managerID = 0;
            while (resultSet.next()) {
                managerID = resultSet.getInt("managerID");
            }
            psInsertAsistantManager.setInt(1,personID);
            psInsertAsistantManager.setInt(2,managerID);
            psInsertAsistantManager.setInt(3,residentID);
            psInsertAsistantManager.setString(4,managerName);
            psInsertAsistantManager.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertIntoController(Connection connection,String controllerName)
    {
        try {
            PreparedStatement psInsertController = connection.prepareStatement(INSERT_CONTROLLER);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_LAST_RESIDENT);
            int personID = 0;
            int residentID = 0;
            while (resultSet.next()) {
                personID = resultSet.getInt("personID");
                residentID = resultSet.getInt("residentID");
            }
            psInsertController.setInt(1,personID);
            psInsertController.setInt(2,residentID);
            psInsertController.setString(3,controllerName);
            psInsertController.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertIntoCashRegister(Connection connection,float totalIncomingMoney,float totalExpenseMoney,String transactionDate,int controllerID,int residentID,String paymentDescription,int isMonthlyFLag)
    {
        try {

            String INSERT_CASHREGISTER = "insert into CashRegister values(?,?,?)";
            PreparedStatement psInsert = connection.prepareStatement(INSERT_CASHREGISTER);
            psInsert.setFloat(1,totalIncomingMoney);
            psInsert.setFloat(2,totalExpenseMoney);
            psInsert.setString(3,transactionDate);
            psInsert.execute();

            System.out.println("Is it a payment?(y or n)");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.next();
            if (choice.equals("y"))
            {
                insertIntoPayment(connection,controllerID,residentID,paymentDescription,isMonthlyFLag);
            }
            else
                insertIntoExpense(connection,controllerID,paymentDescription,isMonthlyFLag);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertIntoPayment(Connection connection,int controllerID,int residentID,String paymentDescription,int isMonthlyFlag)
    {
        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_LAST_CASHREGISTER);
            int transactionID = 0;
            float totalIncomingMoney = 0;
            String transactionDate = null;
            while (resultSet.next()) {
                transactionID = resultSet.getInt("transactionID");
                totalIncomingMoney = resultSet.getFloat("totalIncomingMoney");
            }

            String INSERT_PAYMENT = "insert into Payment values(?,?,?,?,?,?)";
            PreparedStatement psInsert = connection.prepareStatement(INSERT_PAYMENT);
            psInsert.setInt(1,transactionID);
            psInsert.setInt(2,controllerID);
            psInsert.setInt(3,residentID);
            psInsert.setFloat(4,totalIncomingMoney);
            psInsert.setString(5,paymentDescription);
            psInsert.setInt(6,isMonthlyFlag);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertIntoExpense(Connection connection,int controllerID,String expenseDescription,int isMonthlyFlag)
    {
        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_LAST_CASHREGISTER);
            int transactionID = 0;
            float totalExpenseMoney = 0;
            while (resultSet.next()) {
                transactionID = resultSet.getInt("transactionID");
                totalExpenseMoney = resultSet.getFloat("totalExpenseMoney");
            }

            String INSERT_EXPENSE = "insert into Expense values(?,?,?,?,?)";
            PreparedStatement psInsert = connection.prepareStatement(INSERT_EXPENSE);
            psInsert.setInt(1,transactionID);
            psInsert.setFloat(2,totalExpenseMoney);
            psInsert.setInt(3,controllerID);
            psInsert.setString(4,expenseDescription);
            psInsert.setInt(5,isMonthlyFlag);


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
