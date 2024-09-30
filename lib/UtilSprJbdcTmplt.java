package lib;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import static lib.BscLogic.encodejson;

import static lib.BscConvert.*;
public class UtilSprJbdcTmplt {


    public static void main(String[] args) throws Exception {

        // Example Map to insert
        Map<String, Object> exampleMap = Map.of(
                "id", "John Doe",
                "age", 30,
                "city", "New York"
        );

        // Example dynamic database URL
        String dbUrl = "aaaa.sqlt.db";

        AddRow(exampleMap, dbUrl);


        System.out.println("Map inserted successfully!");

    }

    private static void AddRow(Map<String, Object> exampleMap, String dbf) {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:"+dbf);
        dataSource.setUsername("");
        dataSource.setPassword("");
        JdbcTemplate jdbcTemplate  = new JdbcTemplate(dataSource);
        jdbcTemplate.update(
                " CREATE TABLE IF NOT EXISTS table1 (\n" +
                "\n" +
                "       k TEXT PRIMARY KEY ,v TEXT\n" +
                "    )");


        Object sql=" INSERT INTO table1 (k,v) VALUES (?,?)";
        jdbcTemplate.update((String) sql, exampleMap.get("id"), encodejson(exampleMap) );


    }
}
