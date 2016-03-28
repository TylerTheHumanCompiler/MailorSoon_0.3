import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.*;
import java.util.ArrayList;
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
    finally {/*if(connection != null) connection.close();*/}
    Statement statement = connection.createStatement();
    //statement.setQueryTimeout(30);  // set timeout to 30 sec.
    return statement;
}




public static void createSQLTable(Statement statement, String tablename, List<String> varlist, List<String> valname) throws SQLException {
    statement.executeUpdate("drop table if exists " + tablename);
    String sqlquerystr = new String(" (");
    int vls = valname.size();
    for(int i = 0; i < vls; i++) {
        sqlquerystr += valname.get(i) + " " + varlist.get(i) + ", ";}
String sqlqrystr2 = sqlquerystr.substring(0, sqlquerystr.length() - 2) + ')' + "";
    System.out.println("SQLQRY2: " + sqlqrystr2);
    statement.executeUpdate("create table " + tablename + sqlqrystr2);
}





 public static List<String> getResults(Statement statement) throws SQLException {
        ResultSet rs = statement.executeQuery("select * from contacts");
        List<String> newl = new ArrayList<>();
        while(rs.next())
            {   // read the result set
                newl.add(rs.getString("name"));
                System.out.println("name = " + rs.getString("name"));
                newl.add(rs.getString("email"));
                System.out.println("id = " + rs.getInt("id"));
            }
        return newl;
    }





public static void addEntry(Statement statement, String tablename, String[] strarray) throws SQLException {
//    statement.executeUpdate("insert into contacts values(1, 'Tyler the human Compiler', 'mc.twist@hotmail.com')");
  //  statement.executeUpdate("insert into contacts values(2, 'Phbips \"the Phitler\" Bader', 'phil.bad@gmx.ch')");
    //statement.executeUpdate("insert into contacts values(3, 'Taylor Swift', 'taylor@swift-heil.com')");
    int mxr = statement.getMaxRows() + 1;
    String sqlquerystr = new String();
    for(String value : strarray) {sqlquerystr +=  "\'" + value + "\', ";}
    String sqlqrystr2 = sqlquerystr.substring(0, sqlquerystr.length()-2);
    statement.executeUpdate("insert into " + tablename + " values(" + mxr + ", " + sqlqrystr2 + ')' + "");
}




}


