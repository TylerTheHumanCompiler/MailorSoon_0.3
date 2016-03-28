import java.sql.*;
import java.util.List;

/**
 * Created by Skynet on 28.03.2016.
 */
public class Userdata {

public static Statement conSQL() throws ClassNotFoundException, SQLException {
    // load the sqlite-JDBC driver using the current class loader
    Class.forName("org.sqlite.JDBC");
    Connection connection = null;
    try {
        // create a database connection
        connection = DriverManager.getConnection("jdbc:sqlite:userdata.db");
    }catch(SQLException e) {
        // if the error message is "out of memory",
        // it probably means no database file is found
        System.err.println(e.getMessage());}
    finally {try {if(connection != null) connection.close();}
        catch(SQLException e) {System.err.println(e);}}
    Statement statement = connection.createStatement();
    statement.setQueryTimeout(30);  // set timeout to 30 sec.
    return statement;
}




public static void createSQLTable(Statement statement, String tablename, List<String> varlist, List<String> valname) throws SQLException {
    statement.executeUpdate("drop table if exists " + tablename);
    String sqlquerystr = new String(" (");
    int vls = valname.size();
    for(int i = 0; i < vls; i++) {
        sqlquerystr += varlist.get(i) + " " + valname.get(i) + ", ";}
    statement.executeUpdate("create table " + tablename + sqlquerystr + ")");
}





 public static ResultSet getResults(Statement statement) throws SQLException {
        ResultSet rs = statement.executeQuery("select * from contacts");
        while(rs.next())
        {   // read the result set
            System.out.println("name = " + rs.getString("name"));
            System.out.println("id = " + rs.getInt("id"));
        }
     return rs;
    }





public static void addEntry(Statement statement, String tablename, String[] strarray) throws SQLException {
    statement.executeUpdate("insert into contacts values(1, 'Tyler the human Compiler', 'mc.twist@hotmail.com')");
    statement.executeUpdate("insert into contacts values(2, 'Phbips \"the Phitler\" Bader', 'phil.bad@gmx.ch')");
    statement.executeUpdate("insert into contacts values(3, 'Taylor Swift', 'taylor@swift-heil.com')");
    int mxr = statement.getMaxRows() + 1;
    String sqlquerystr = new String();
    for(String value : strarray) {sqlquerystr +=  "\'" + value + "\', ";}
    statement.executeUpdate("insert into " + tablename + " values(" + mxr + ", " + sqlquerystr + ")");
}




}