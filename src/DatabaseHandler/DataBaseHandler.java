
package DatabaseHandler;

import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author WILFRED KOECH
 */
public class DataBaseHandler {
     private static DataBaseHandler handler=null;

    private static final String DB_URL = "jdbc:derby://localhost:1527/patient"; 
    private static Connection conn = null;
    private static Statement stmt = null;

   public DataBaseHandler() {
    
        createConnection();
        
                           }
       public static DataBaseHandler getInstance() {
         if(handler==null)
        {
            handler = new DataBaseHandler();
        }
        return handler;
    }

    void createConnection() {
        try {
            Class.forName("jdbc:derby://localhost:1527/patient [pat on PAT]").newInstance();
            conn = DriverManager.getConnection(DB_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
            ///////////////////////
  void setupFpayTable() {
        String TABLE_NAME = "AY";
        try {
            stmt = (Statement) conn.createStatement();

            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);

            if (tables.next()) {
                System.out.println("Table " + TABLE_NAME + "already exists. Ready for go!");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + "("
                        + "	rsId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1,INCREMENT BY 1) primary key,\n" 
                        + "	fmodes varchar(200),"
                        + "     finvoice varchar(200),"
                        + "	admiss varchar(25),"
                        + "	form varchar(20),"
                        + "     term varchar(20),"
                        + "     years varchar(20),"
                        + "     classs varchar(200),"
                        + "     fpaid double,"
                        + "     fowed double,"
                        + "     semfee double,"
                        + "     cdate date,"
                        + "     ctime timestamp default CURRENT_TIMESTAMP,"
                        + "	status varchar(1) default '1'"
                        + " )");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage() + " --- setupDatabase");
        } finally {
        }
  }
/////////////////////

       public ResultSet execQuery(String query) {
        ResultSet result;
        try {
            stmt = (Statement) conn.createStatement();
            result = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return null;
        } finally {
        }
        return result;
    }
    public PreparedStatement execSction(String qu) {
        try {
            stmt = (Statement) conn.createStatement();
            stmt.execute(qu);
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error:" + ex.getMessage(), "Error Occured", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            
        } finally {
            return null;
        }
    }
    
    public boolean execAction(String qu) {
        try {
            stmt = (Statement) conn.createStatement();
            stmt.execute(qu);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error:" + ex.getMessage(), "Error Occured", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return false;
        } finally {
        }
    }
}
