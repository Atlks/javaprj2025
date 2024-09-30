package lib;

import java.io.InputStream;

import mapper.Tab1Mapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static lib.BscLogic.encodejson;

import static lib.BscConvert.*;

public class UtilMybatis {

    public static void main(String[] args) throws Exception {

            // Example Map to insert
            Map<String, Object> exampleMap = Map.of(
                    "id", "John Doe",
                    "age", 30,
                    "city", "New York"
            );

            // Example dynamic database URL
            String dbUrl = "c:/db/payx.db";

      //  AddRow(exampleMap, dbUrl);
        AddRowMprMd(exampleMap, dbUrl);

        System.out.println("Map inserted successfully!");

    }


    private static void AddRowMprMd(Map<String, Object> exampleMap, String dbUrl) throws Exception {
        // Insert the Map as JSON into the database

        // Convert the Map to JSON
        String json = encodejson(exampleMap);

        // Get SqlSessionFactory with the dynamic database URL
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory(dbUrl);

        // Use MyBatis to insert the JSON into the database
        SqlSession session = sqlSessionFactory.openSession();

        Tab1Mapper tab1Mapper = session.getMapper(Tab1Mapper.class);

        // 3. 使用 Map 插入数据
        Map<String, Object> params = new HashMap<>();
        params.put("k", "exampleKey");
        params.put("v", "exampleValue");


        session.insert("crtbl");

        tab1Mapper.insertTab1(params);
        session.commit();  // 提交事务
    }



    private static void AddRow(Map<String, Object> exampleMap, String dbUrl) throws Exception {
        // Insert the Map as JSON into the database

        // Convert the Map to JSON
        String json = encodejson(exampleMap);

        // Get SqlSessionFactory with the dynamic database URL
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory(dbUrl);

        // Use MyBatis to insert the JSON into the database
        SqlSession session = sqlSessionFactory.openSession();


        session.insert("crtbl");

        session.insert("insertJson", json);
        session.commit(); // Commit the transaction
    }

//    public static SqlSessionFactory getSqlSessionFactory2(String dbFilePath) {
//        // Create a PooledDataSource with the provided SQLite database file path
//        PooledDataSource dataSource = new PooledDataSource(
//                "org.sqlite.JDBC",  // Driver class
//                "jdbc:sqlite:" + dbFilePath,  // Dynamic URL
//                "",                 // Username (empty for SQLite)
//                ""                  // Password (empty for SQLite)
//        );
//
//        // Create a TransactionFactory
//        TransactionFactory transactionFactory = new JdbcTransactionFactory();
//
//        // Create a Configuration object and set up necessary configurations
//        Configuration configuration = new Configuration();
//     //  configuration.setDataSource(dataSource);
//      //  configuration.setTransactionFactory(transactionFactory);
//
//        // Create and return the SqlSessionFactory
//        return new SqlSessionFactoryBuilder().build(configuration);
//    }

    public static SqlSessionFactory getSqlSessionFactory(String dbFilePath) throws Exception {
        // Ensure the JDBC driver is loaded
        Class.forName("org.sqlite.JDBC");

        // Create a PooledDataSource with the provided SQLite database file path
//        PooledDataSource dataSource = new PooledDataSource(
//                "org.sqlite.JDBC",  // Driver class
//                "jdbc:sqlite:" + dbFilePath,  // Dynamic URL
//                "",                 // Username (empty for SQLite)
//                ""                  // Password (empty for SQLite)
//        );
//
//        // Create a TransactionFactory
//        TransactionFactory transactionFactory = new JdbcTransactionFactory();

        // Load MyBatis configuration
        String resource = "mybatis-config.xml";  // Path to your MyBatis configuration file

        // Read template file
        String template = "";

        template = Files.readString(Paths.get(resource));


        template = template.replaceAll("@dbf@", dbFilePath);
        InputStream inputStream = stringToInputStream(template);

        // Create a SqlSessionFactoryBuilder and build SqlSessionFactory
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream, null, new Properties());


        return sqlSessionFactory;
    }


}
