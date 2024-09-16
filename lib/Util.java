package lib;

import java.io.InputStream;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSource;

import java.io.InputStream;
import java.sql.DriverManager;
import java.util.Map;
import java.util.Properties;

public class Util {

    public static void main(String[] args) {
        try {
            // Example Map to insert
            Map<String, Object> exampleMap = Map.of(
                    "name", "John Doe",
                    "age", 30,
                    "city", "New York"
            );

            // Example dynamic database URL
            String dynamicDbUrl = "jdbc:sqlite:dynamic_db_path.sqlt.db";

            // Insert the Map as JSON into the database
            insertMapAsJson(dynamicDbUrl, exampleMap);
            System.out.println("Map inserted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory2(String dbFilePath) {
        // Create a PooledDataSource with the provided SQLite database file path
        PooledDataSource dataSource = new PooledDataSource(
                "org.sqlite.JDBC",  // Driver class
                "jdbc:sqlite:" + dbFilePath,  // Dynamic URL
                "",                 // Username (empty for SQLite)
                ""                  // Password (empty for SQLite)
        );

        // Create a TransactionFactory
        TransactionFactory transactionFactory = new JdbcTransactionFactory();

        // Create a Configuration object and set up necessary configurations
        Configuration configuration = new Configuration();
        configuration.setDataSource(dataSource);
        configuration.setTransactionFactory(transactionFactory);

        // Create and return the SqlSessionFactory
        return new SqlSessionFactoryBuilder().build(configuration);
    }

    public static SqlSessionFactory getSqlSessionFactory(String dbFilePath) throws Exception {
        // Ensure the JDBC driver is loaded
        Class.forName("org.sqlite.JDBC");

        // Create a PooledDataSource with the provided SQLite database file path
        PooledDataSource dataSource = new PooledDataSource(
                "org.sqlite.JDBC",  // Driver class
                "jdbc:sqlite:" + dbFilePath,  // Dynamic URL
                "",                 // Username (empty for SQLite)
                ""                  // Password (empty for SQLite)
        );

        // Create a TransactionFactory
        TransactionFactory transactionFactory = new JdbcTransactionFactory();

        // Load MyBatis configuration
        String resource = "mybatis-config.xml";  // Path to your MyBatis configuration file
        InputStream inputStream = Resources.getResourceAsStream(resource);

        // Create a SqlSessionFactoryBuilder and build SqlSessionFactory
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream, null, new Properties());

        // Set the data source and transaction factory
        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.setDataSource(dataSource);
        configuration.setTransactionFactory(transactionFactory);

        return sqlSessionFactory;
    }

    public static void insertMapAsJson(String dbUrl, Map<String, Object> map) throws Exception {
        // Convert the Map to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(map);

        // Get SqlSessionFactory with the dynamic database URL
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory(dbUrl);

        // Use MyBatis to insert the JSON into the database
        try (SqlSession session = sqlSessionFactory.openSession()) {

            session.selectList("insertJson",json);
            session.commit(); // Commit the transaction
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error inserting data into database", e);
        }
    }


}
