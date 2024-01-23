package tw.com.eeit.project;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.FileReader;

public class ArmyInsertData {

    public static void main(String[] args) {
    	try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String urlstr = "jdbc:sqlserver://localhost:1433;databaseName=OliverLiu;user=watcher;password=P@ssw0rd;encrypt=true;trustServerCertificate=true";
            Connection conn = DriverManager.getConnection(urlstr);
            System.out.println("Connection Status:" + !conn.isClosed());

            // Specify the file path
            String filePath = "C:\\Temp\\臺北市110年02月役男徵兵檢查BMI值統計.csv";

            // Prepare the SQL statement
            String sql = "INSERT INTO Army (BMIRange, ArmyType, Number, Per) VALUES (?, ?, ?, ?)";
            try (PreparedStatement state = conn.prepareStatement(sql);
                 BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

                // Skip the header line if exists
                reader.readLine();

                // Read data from CSV and insert into the database
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(",");
                    // Assuming the CSV columns order matches the table columns order
                    state.setString(1, values[0].trim());
                    state.setString(2, values[1].trim());
                    state.setInt(3, Integer.parseInt(values[2].trim()));
                    
                    // Remove the percentage symbol and parse the double value
                    String percentageValue = values[3].trim().replace("%", "");
                    state.setDouble(4, Double.parseDouble(percentageValue));

                    // Execute the SQL statement for each row
                    state.executeUpdate();
                }

                System.out.println("Data loaded into SQL Server successfully.");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
