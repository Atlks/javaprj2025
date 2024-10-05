import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.*;
import java.util.Map;
import static lib.BscEnc.*;

public class sqltUtil {
    private static final String DATABASE_URL = "jdbc:sqlite:sample222.db";


    public static void main(String[] args) {
        // Example Map to insert
        Map<String, Object> exampleMap = Map.of(
                "id", "111",
                "age", 30

        );
        createNewDatabase();
        // Example dynamic database URL
        String dbUrl = "/sqlt222.db";

        AddRow(exampleMap, dbUrl);
    }

    private static void AddRow(Map<String, Object> exampleMap, String dbUrl) {
        String sql = "INSERT INTO tab1(id,data) VALUES(?,?)";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String json=encodejson(exampleMap);
            pstmt.setString(1, exampleMap.get("id").toString());
            pstmt.setString(2, json);
            pstmt.executeUpdate();
            System.out.println("User inserted: " + json);
        } catch (SQLException | JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void createNewDatabase() {
        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS tab1 (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                         "data TEXT NOT NULL" +
                         ");";
            stmt.execute(sql);
            System.out.println("Database and table created.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
