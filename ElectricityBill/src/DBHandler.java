import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHandler {
	public Connection establishConnection() throws ClassNotFoundException, SQLException, FileNotFoundException {
		{
			Connection connection = null;
			Properties props = new Properties();
			FileInputStream fileInputStream = null;
			try {

				fileInputStream = new FileInputStream("src/db.properties");

				props.load(fileInputStream);

				Class.forName(props.getProperty("db.classname"));
				connection = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.username"),
						props.getProperty("db.password"));

			}

			catch (Exception e) {
				e.printStackTrace();
			}
			return connection;

		}

	}
}
