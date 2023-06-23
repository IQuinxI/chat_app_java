
package ma.emsi.Database;
import java.sql.Connection;
import java.sql.DriverManager;
    public class DbConnection {

        public Connection dblink;
        public Connection getConnection(){
            String db_name="chat_app_java";
            String db_user="root";
            String db_password="";
            String url = "jdbc:mysql://localhost/"+ db_name;

            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                dblink = DriverManager.getConnection(url,db_user,db_password);

            }catch(Exception e ){
                e.printStackTrace();
            }

            return dblink;
        }
    }

