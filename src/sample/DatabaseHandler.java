package sample;

import java.sql.*;

public class DatabaseHandler {

    static Connection connection;

    public void ConnectionSQLite() {
        Connection connection = getConnection();
        System.out.println("Connection");
    }

    private Connection getConnection() {
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(
                    "jdbc:sqlite:D:\\Azamat\\Javaitschool\\firstFX\\sqlite3.db");
            //System.out.println("Connection");
            return connection;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void close() {
        try{
            connection.close();
            System.out.println("Close connection");
        }catch(Exception e)
        {
            System.out.print(e.getMessage());
        }
    }

    public void signUpUser(User user) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO "+Const.USER_TABLE+"("+
                Const.USERS_FIRSTNAME+","+Const.USERS_SECONDNAME+","+
                Const.USERS_USERNAME+","+Const.USERS_PASSWORD+","+
                Const.USERS_LOCATION+","+Const.USERS_GENDER+")"+
                " VALUES(?,?,?,?,?,?)";

        PreparedStatement prSt = getConnection().prepareStatement(insert);
        prSt.setString(1, user.getFirstname());
        prSt.setString(2, user.getSecondname());
        prSt.setString(3, user.getUsername());
        prSt.setString(4, user.getPassword());
        prSt.setString(5, user.getLocation());
        prSt.setString(6, user.getGender());
        prSt.executeUpdate();
    }

    public ResultSet getUser(User user) {

        try {
            Statement statement  = connection.createStatement ();
            String query = "SELECT username, password FROM users " +
                    " WHERE username='"+user.getUsername()+"' AND password='"+user.getPassword()+"' ";
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                String userName = rs.getString("username");
                String passwordRS = rs.getString("password");
            }

            statement.close();
            return rs;
        }catch (Exception e){
            //System.out.println(e.getMessage());
            throw new RuntimeException("GET USER LOGIN and PASSWORD");
        }
    }

}
