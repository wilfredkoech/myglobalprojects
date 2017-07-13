
package myFrames.myPanels;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


class Connecto {
     Connection conn = null;
   public static Connection Connectdb(){
      try{
         Class.forName("com.jdbc.mysql.Driver");
         Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/koech?zeroDateTimeBehavior=convertToNull", "root","");
         System.out.println("Connected");
         return conn;
      }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        return null;
      } 
           
   }
    
}
